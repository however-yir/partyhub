package org.example.djxt.service.impl;

import org.example.djxt.common.BusinessException;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.sysBranchStar;
import org.example.djxt.dto.StarBucketCount;
import org.example.djxt.dto.StarDeptRank;
import org.example.djxt.dto.StarStatsOverview;
import org.example.djxt.dto.StarYearTrend;
import org.example.djxt.mapper.SysBranchStarMapper;
import org.example.djxt.service.ISysBranchStarService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysBranchStarServiceImp implements ISysBranchStarService {

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final SysBranchStarMapper starMapper;

    public SysBranchStarServiceImp(SysBranchStarMapper starMapper) {
        this.starMapper = starMapper;
    }

    @Override
    public List<sysBranchStar> listAll() {
        List<sysBranchStar> stars = starMapper.selectAll();
        return stars.stream()
                .filter(this::isNotDeleted)
                .peek(this::enrichProcessName)
                .collect(Collectors.toList());
    }

    @Override
    public PageResult<sysBranchStar> list(String year, Integer process, String keyword, int page, int size) {
        Example example = new Example(sysBranchStar.class);
        Example.Criteria criteria = example.createCriteria();

        if (year != null && !year.trim().isEmpty()) {
            criteria.andEqualTo("sbYear", year);
        }
        if (process != null) {
            criteria.andEqualTo("process", process);
        }
        if (keyword != null && !keyword.trim().isEmpty()) {
            criteria.andLike("partybranchname", "%" + keyword.trim() + "%");
        }

        example.orderBy("id").desc();
        List<sysBranchStar> stars = starMapper.selectByExample(example);
        List<sysBranchStar> filtered = stars.stream()
                .filter(this::isNotDeleted)
                .peek(this::enrichProcessName)
                .collect(Collectors.toList());
        return PageResult.fromList(filtered, page, size);
    }

    @Override
    public sysBranchStar getById(Long id) {
        sysBranchStar star = starMapper.selectById(id);
        if (star == null || !isNotDeleted(star)) {
            throw new BusinessException("评星记录不存在: " + id);
        }
        enrichProcessName(star);
        return star;
    }

    @Override
    public sysBranchStar create(sysBranchStar star) {
        if (star == null || isBlank(star.getPartybranchname()) || isBlank(star.getSodsacs())) {
            throw new BusinessException("评星记录缺少必填字段: partybranchname/sodsacs");
        }
        if (star.getProcess() == null) {
            star.setProcess(1);
        }
        if (isBlank(star.getDelFlag())) {
            star.setDelFlag("0");
        }
        if (isBlank(star.getSbYear())) {
            star.setSbYear(String.valueOf(LocalDateTime.now().getYear()));
        }

        normalizeScoreAndRating(star, null);
        star.setCreateTime(nowString());
        star.setUpdateTime(nowString());
        starMapper.insertSelective(star);
        enrichProcessName(star);
        return star;
    }

    @Override
    public sysBranchStar update(Long id, sysBranchStar star) {
        sysBranchStar existing = getById(id);
        star.setId(id);
        normalizeScoreAndRating(star, existing);
        star.setUpdateTime(nowString());
        starMapper.updateByPrimaryKeySelective(star);
        return getById(id);
    }

    @Override
    public sysBranchStar submit(Long id, String submitter) {
        sysBranchStar existing = getById(id);
        sysBranchStar update = new sysBranchStar();
        update.setId(id);
        update.setProcess(existing.getProcess() == null ? 2 : Math.max(existing.getProcess(), 2));
        if (!isBlank(submitter)) {
            update.setUpdateBy(submitter);
        }
        update.setUpdateTime(nowString());
        starMapper.updateByPrimaryKeySelective(update);
        return getById(id);
    }

    @Override
    public sysBranchStar review(Long id, sysBranchStar reviewPayload) {
        sysBranchStar existing = getById(id);
        if (reviewPayload == null) {
            throw new BusinessException("审核内容不能为空");
        }

        reviewPayload.setId(id);
        normalizeScoreAndRating(reviewPayload, existing);

        Integer targetProcess = reviewPayload.getProcess();
        if (targetProcess == null) {
            targetProcess = 3;
        }
        reviewPayload.setProcess(Math.min(Math.max(targetProcess, 3), 4));
        reviewPayload.setUpdateTime(nowString());
        starMapper.updateByPrimaryKeySelective(reviewPayload);
        return getById(id);
    }

    @Override
    public StarStatsOverview statsOverview(String year) {
        List<sysBranchStar> stars = fetchByYear(year);
        StarStatsOverview overview = new StarStatsOverview();
        overview.setYear(year);
        overview.setTotalRecords(stars.size());
        overview.setSubmittedRecords(stars.stream().filter(item -> item.getProcess() != null && item.getProcess() >= 2).count());
        overview.setCompletedRecords(stars.stream().filter(item -> item.getProcess() != null && item.getProcess() == 4).count());

        List<Integer> scoreList = stars.stream()
                .map(sysBranchStar::getScore)
                .filter(value -> value != null)
                .collect(Collectors.toList());

        overview.setAvgScore(average(scoreList));
        overview.setMaxScore(scoreList.isEmpty() ? null : Collections.max(scoreList));
        overview.setMinScore(scoreList.isEmpty() ? null : Collections.min(scoreList));

        overview.setFiveStarCount(countByRating(stars, "五星"));
        overview.setFourStarCount(countByRating(stars, "四星"));
        overview.setThreeStarCount(countByRating(stars, "三星"));
        long knownStars = overview.getFiveStarCount() + overview.getFourStarCount() + overview.getThreeStarCount();
        overview.setOtherStarCount(Math.max(0, stars.size() - knownStars));

        return overview;
    }

    @Override
    public List<StarBucketCount> statsByRating(String year) {
        List<sysBranchStar> stars = fetchByYear(year);
        Map<String, Long> grouped = stars.stream()
                .collect(Collectors.groupingBy(item -> normalizeLabel(item.getStarrating(), "未评级"), Collectors.counting()));

        return grouped.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .map(entry -> new StarBucketCount(entry.getKey(), entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<StarBucketCount> statsByProcess(String year) {
        List<sysBranchStar> stars = fetchByYear(year);
        Map<Integer, Long> grouped = stars.stream()
                .collect(Collectors.groupingBy(item -> item.getProcess() == null ? 0 : item.getProcess(), Collectors.counting()));

        return grouped.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> new StarBucketCount(String.valueOf(entry.getKey()), processName(entry.getKey()), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<StarDeptRank> deptRanking(String year, int limit) {
        List<sysBranchStar> stars = fetchByYear(year);
        Map<String, List<sysBranchStar>> grouped = stars.stream()
                .collect(Collectors.groupingBy(item -> normalizeLabel(item.getDeptName(), "未分配学院"), LinkedHashMap::new, Collectors.toList()));

        List<StarDeptRank> rankList = new ArrayList<>();
        for (Map.Entry<String, List<sysBranchStar>> entry : grouped.entrySet()) {
            List<sysBranchStar> records = entry.getValue();
            StarDeptRank rank = new StarDeptRank();
            rank.setDeptName(entry.getKey());
            rank.setDeptId(records.stream()
                    .map(sysBranchStar::getDeptId)
                    .filter(value -> value != null)
                    .findFirst()
                    .orElse(null));
            rank.setRecordCount(records.size());

            List<Integer> scores = records.stream()
                    .map(sysBranchStar::getScore)
                    .filter(value -> value != null)
                    .collect(Collectors.toList());
            rank.setAvgScore(average(scores));

            long completed = records.stream().filter(item -> item.getProcess() != null && item.getProcess() == 4).count();
            rank.setCompletedRate(records.isEmpty() ? 0.0 : keep2((double) completed * 100.0 / (double) records.size()));
            rankList.add(rank);
        }

        rankList.sort(Comparator.comparingDouble(StarDeptRank::getAvgScore).reversed()
                .thenComparing(Comparator.comparingLong(StarDeptRank::getRecordCount).reversed()));

        int safeLimit = Math.max(limit, 1);
        if (rankList.size() > safeLimit) {
            return new ArrayList<>(rankList.subList(0, safeLimit));
        }
        return rankList;
    }

    @Override
    public List<StarYearTrend> trend() {
        List<sysBranchStar> stars = listAll();
        Map<String, List<sysBranchStar>> grouped = stars.stream()
                .collect(Collectors.groupingBy(item -> normalizeLabel(item.getSbYear(), "未标注年份"), Collectors.toList()));

        List<StarYearTrend> trend = new ArrayList<>();
        for (Map.Entry<String, List<sysBranchStar>> entry : grouped.entrySet()) {
            List<Integer> scores = entry.getValue().stream()
                    .map(sysBranchStar::getScore)
                    .filter(value -> value != null)
                    .collect(Collectors.toList());

            StarYearTrend item = new StarYearTrend();
            item.setYear(entry.getKey());
            item.setTotalRecords(entry.getValue().size());
            item.setAvgScore(average(scores));
            trend.add(item);
        }

        trend.sort(Comparator.comparing(StarYearTrend::getYear));
        return trend;
    }

    @Override
    public void delete(Long id) {
        getById(id);
        starMapper.deleteByPrimaryKey(id);
    }

    private List<sysBranchStar> fetchByYear(String year) {
        return listAll().stream()
                .filter(item -> isBlank(year) || year.trim().equals(item.getSbYear()))
                .collect(Collectors.toList());
    }

    private void normalizeScoreAndRating(sysBranchStar target, sysBranchStar existing) {
        int foundation = valueOrDefault(target.getFoundationitem(), existing == null ? null : existing.getFoundationitem());
        int pluses = valueOrDefault(target.getPluses(), existing == null ? null : existing.getPluses());
        int minuses = valueOrDefault(target.getMinuses(), existing == null ? null : existing.getMinuses());
        int commentItems = valueOrDefault(target.getCommentitems(), existing == null ? null : existing.getCommentitems());

        target.setFoundationitem(foundation);
        target.setPluses(pluses);
        target.setMinuses(minuses);
        target.setCommentitems(commentItems);

        int score = foundation + pluses - minuses + commentItems;
        target.setScore(score);

        if (isBlank(target.getStarrating())) {
            target.setStarrating(scoreToRating(score));
        }
    }

    private void enrichProcessName(sysBranchStar branchStar) {
        if (branchStar == null || branchStar.getProcess() == null) {
            branchStar.setProcessName("未提交");
            return;
        }
        branchStar.setProcessName(processName(branchStar.getProcess()));
    }

    private String processName(Integer process) {
        if (process == null || process <= 0) {
            return "未提交";
        }
        if (process == 1) {
            return "支部自评";
        }
        if (process == 2) {
            return "评分专家评分";
        }
        if (process == 3) {
            return "管理员审核";
        }
        if (process == 4) {
            return "评分完成";
        }
        return "未知流程";
    }

    private String scoreToRating(int score) {
        if (score >= 90) {
            return "五星";
        }
        if (score >= 80) {
            return "四星";
        }
        if (score >= 70) {
            return "三星";
        }
        if (score >= 60) {
            return "二星";
        }
        return "一星";
    }

    private long countByRating(List<sysBranchStar> stars, String rating) {
        return stars.stream().filter(item -> rating.equals(item.getStarrating())).count();
    }

    private boolean isNotDeleted(sysBranchStar star) {
        return star != null && !"2".equals(star.getDelFlag());
    }

    private int valueOrDefault(Integer current, Integer backup) {
        if (current != null) {
            return current;
        }
        if (backup != null) {
            return backup;
        }
        return 0;
    }

    private double average(List<Integer> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        double avg = values.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        return keep2(avg);
    }

    private double keep2(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private String normalizeLabel(String value, String fallback) {
        if (isBlank(value)) {
            return fallback;
        }
        return value.trim();
    }

    private String nowString() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
