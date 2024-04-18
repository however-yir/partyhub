package org.example.djxt.service;

import org.example.djxt.common.PageResult;
import org.example.djxt.dto.StarBucketCount;
import org.example.djxt.dto.StarDeptRank;
import org.example.djxt.dto.StarStatsOverview;
import org.example.djxt.dto.StarYearTrend;
import org.example.djxt.domain.sysBranchStar;

import java.util.List;

public interface ISysBranchStarService {
    List<sysBranchStar> listAll();

    PageResult<sysBranchStar> list(String year, Integer process, String keyword, int page, int size);

    sysBranchStar getById(Long id);

    sysBranchStar create(sysBranchStar star);

    sysBranchStar update(Long id, sysBranchStar star);

    sysBranchStar submit(Long id, String submitter);

    sysBranchStar review(Long id, sysBranchStar reviewPayload);

    StarStatsOverview statsOverview(String year);

    List<StarBucketCount> statsByRating(String year);

    List<StarBucketCount> statsByProcess(String year);

    List<StarDeptRank> deptRanking(String year, int limit);

    List<StarYearTrend> trend();

    void delete(Long id);
}
