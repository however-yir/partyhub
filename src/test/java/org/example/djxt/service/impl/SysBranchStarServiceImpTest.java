package org.example.djxt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.djxt.common.BusinessException;
import org.example.djxt.domain.sysBranchStar;
import org.example.djxt.dto.StarBucketCount;
import org.example.djxt.dto.StarDeptRank;
import org.example.djxt.dto.StarStatsOverview;
import org.example.djxt.dto.StarYearTrend;
import org.example.djxt.mapper.SysBranchStarMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SysBranchStarServiceImpTest {

    @Mock
    private SysBranchStarMapper starMapper;

    @InjectMocks
    private SysBranchStarServiceImp starService;

    @Test
    void shouldCreateStarWithComputedScoreAndDefaults() {
        doAnswer(invocation -> {
            sysBranchStar payload = invocation.getArgument(0);
            payload.setId(100L);
            return 1;
        }).when(starMapper).insert(any(sysBranchStar.class));

        sysBranchStar payload = new sysBranchStar();
        payload.setPartybranchname("计算机学院党委");
        payload.setSodsacs("SODSACS-2024-001");
        payload.setFoundationitem(80);
        payload.setPluses(12);
        payload.setMinuses(2);
        payload.setCommentitems(3);

        sysBranchStar created = starService.create(payload);

        assertEquals(100L, created.getId());
        assertEquals(1, created.getProcess());
        assertEquals("0", created.getDelFlag());
        assertEquals(93, created.getScore());
        assertEquals("五星", created.getStarrating());
        assertNotNull(created.getSbYear());
        assertNotNull(created.getCreateTime());
        assertNotNull(created.getUpdateTime());
    }

    @Test
    void shouldAggregateOverviewByYear() {
        List<sysBranchStar> records = Arrays.asList(
                buildStar(1L, "2024", 95, "五星", 4, "0", "理学院"),
                buildStar(2L, "2024", 82, "四星", 2, "0", "理学院"),
                buildStar(3L, "2024", 68, "二星", 1, "0", "文学院"),
                buildStar(4L, "2023", 88, "五星", 4, "0", "文学院"),
                buildStar(5L, "2024", 99, "五星", 4, "2", "理学院")
        );
        when(starMapper.selectList(any(QueryWrapper.class))).thenReturn(records);

        StarStatsOverview overview = starService.statsOverview("2024");

        assertEquals("2024", overview.getYear());
        assertEquals(3, overview.getTotalRecords());
        assertEquals(2L, overview.getSubmittedRecords());
        assertEquals(1L, overview.getCompletedRecords());
        assertEquals(81.67, overview.getAvgScore());
        assertEquals(95, overview.getMaxScore());
        assertEquals(68, overview.getMinScore());
        assertEquals(1L, overview.getFiveStarCount());
        assertEquals(1L, overview.getFourStarCount());
        assertEquals(0L, overview.getThreeStarCount());
        assertEquals(1L, overview.getOtherStarCount());
    }

    @Test
    void shouldReturnDeptRankingWithLimit() {
        List<sysBranchStar> records = Arrays.asList(
                buildStar(1L, "2024", 90, "五星", 4, "0", "理学院"),
                buildStar(2L, "2024", 80, "四星", 4, "0", "理学院"),
                buildStar(3L, "2024", 96, "五星", 4, "0", "外国语学院"),
                buildStar(4L, "2024", 94, "五星", 3, "0", "外国语学院")
        );
        when(starMapper.selectList(any(QueryWrapper.class))).thenReturn(records);

        List<StarDeptRank> ranking = starService.deptRanking("2024", 1);

        assertEquals(1, ranking.size());
        assertEquals("外国语学院", ranking.get(0).getDeptName());
        assertEquals(2, ranking.get(0).getRecordCount());
        assertEquals(95.0, ranking.get(0).getAvgScore());
    }

    @Test
    void shouldDeleteExistingRecord() {
        sysBranchStar existing = buildStar(10L, "2024", 88, "五星", 4, "0", "理学院");
        when(starMapper.selectById(10L)).thenReturn(existing);

        starService.delete(10L);

        verify(starMapper).deleteById(10L);
    }

    @Test
    void shouldThrowWhenGetDeletedRecordById() {
        sysBranchStar deleted = buildStar(11L, "2024", 75, "三星", 2, "2", "理学院");
        when(starMapper.selectById(11L)).thenReturn(deleted);

        BusinessException exception = assertThrows(BusinessException.class, () -> starService.getById(11L));

        assertEquals("评星记录不存在: 11", exception.getMessage());
    }

    @Test
    void shouldListAllFilterDeletedAndMapProcessName() {
        List<sysBranchStar> records = Arrays.asList(
                buildStar(1L, "2024", 90, "五星", 1, "0", "理学院"),
                buildStar(2L, "2024", 88, "五星", 4, "0", "文学院"),
                buildStar(3L, "2024", 70, "三星", 2, "2", "文学院")
        );
        when(starMapper.selectList(any(QueryWrapper.class))).thenReturn(records);

        List<sysBranchStar> result = starService.listAll();

        assertEquals(2, result.size());
        assertEquals("支部自评", result.get(0).getProcessName());
        assertEquals("评分完成", result.get(1).getProcessName());
    }

    @Test
    void shouldSubmitPromoteProcessToAtLeastTwo() {
        sysBranchStar existing = buildStar(12L, "2024", 80, "四星", 1, "0", "理学院");
        sysBranchStar submitted = buildStar(12L, "2024", 80, "四星", 2, "0", "理学院");
        when(starMapper.selectById(12L)).thenReturn(existing, submitted);

        sysBranchStar result = starService.submit(12L, "branch-admin");

        assertEquals(2, result.getProcess());
        verify(starMapper).updateById(any(sysBranchStar.class));
    }

    @Test
    void shouldReviewClampProcessAndRecalculateScore() {
        sysBranchStar existing = buildStar(13L, "2024", 85, "四星", 2, "0", "文学院");
        existing.setFoundationitem(80);
        existing.setPluses(5);
        existing.setMinuses(0);
        existing.setCommentitems(0);

        sysBranchStar refreshed = buildStar(13L, "2024", 90, "五星", 4, "0", "文学院");
        when(starMapper.selectById(13L)).thenReturn(existing, refreshed);

        sysBranchStar payload = new sysBranchStar();
        payload.setFoundationitem(85);
        payload.setPluses(10);
        payload.setMinuses(2);
        payload.setCommentitems(1);
        payload.setProcess(9);

        sysBranchStar result = starService.review(13L, payload);

        assertEquals(4, result.getProcess());
        verify(starMapper).updateById(payload);
    }

    @Test
    void shouldBuildRatingAndProcessDistributions() {
        List<sysBranchStar> records = Arrays.asList(
                buildStar(21L, "2024", 95, "五星", 4, "0", "理学院"),
                buildStar(22L, "2024", 82, "四星", 2, "0", "理学院"),
                buildStar(23L, "2024", 70, null, null, "0", "文学院")
        );
        when(starMapper.selectList(any(QueryWrapper.class))).thenReturn(records);

        List<StarBucketCount> rating = starService.statsByRating("2024");
        List<StarBucketCount> process = starService.statsByProcess("2024");

        assertEquals(3, rating.size());
        boolean hasUnrated = rating.stream().anyMatch(item -> "未评级".equals(item.getLabel()));
        assertTrue(hasUnrated);
        assertEquals("未提交", process.get(0).getLabel());
    }

    @Test
    void shouldBuildTrendSortedByYear() {
        List<sysBranchStar> records = Arrays.asList(
                buildStar(31L, "2024", 80, "四星", 2, "0", "理学院"),
                buildStar(32L, "2023", 90, "五星", 4, "0", "理学院")
        );
        when(starMapper.selectList(any(QueryWrapper.class))).thenReturn(records);

        List<StarYearTrend> trend = starService.trend();

        assertEquals(2, trend.size());
        assertEquals("2023", trend.get(0).getYear());
        assertEquals("2024", trend.get(1).getYear());
    }

    private sysBranchStar buildStar(Long id, String year, Integer score, String rating, Integer process, String delFlag, String deptName) {
        sysBranchStar star = new sysBranchStar();
        star.setId(id);
        star.setSbYear(year);
        star.setScore(score);
        star.setStarrating(rating);
        star.setProcess(process);
        star.setDelFlag(delFlag);
        star.setDeptName(deptName);
        star.setPartybranchname(deptName + "党委");
        star.setSodsacs("SODSACS-" + id);
        star.setProcessName("未提交");
        return star;
    }
}
