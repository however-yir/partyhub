package org.example.djxt.demos.web;

import org.example.djxt.common.ApiResponse;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.sysBranchStar;
import org.example.djxt.dto.StarBucketCount;
import org.example.djxt.dto.StarDeptRank;
import org.example.djxt.dto.StarStatsOverview;
import org.example.djxt.dto.StarYearTrend;
import org.example.djxt.service.ISysBranchStarService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StarController {

    private final ISysBranchStarService starService;

    public StarController(ISysBranchStarService starService) {
        this.starService = starService;
    }

    @GetMapping("/star")
    public List<sysBranchStar> legacyListAll() {
        return starService.listAll();
    }

    @GetMapping("/star/{id}")
    public sysBranchStar legacyGetById(@PathVariable Long id) {
        return starService.getById(id);
    }

    @GetMapping("/api/stars")
    public ApiResponse<PageResult<sysBranchStar>> list(
            @RequestParam(required = false) String year,
            @RequestParam(required = false) Integer process,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.ok(starService.list(year, process, keyword, page, size));
    }

    @GetMapping("/api/stars/{id}")
    public ApiResponse<sysBranchStar> getById(@PathVariable Long id) {
        return ApiResponse.ok(starService.getById(id));
    }

    @PostMapping("/api/stars")
    public ApiResponse<sysBranchStar> create(@RequestBody sysBranchStar star) {
        return ApiResponse.ok("created", starService.create(star));
    }

    @PutMapping("/api/stars/{id}")
    public ApiResponse<sysBranchStar> update(@PathVariable Long id, @RequestBody sysBranchStar star) {
        return ApiResponse.ok("updated", starService.update(id, star));
    }

    @PutMapping("/api/stars/{id}/submit")
    public ApiResponse<sysBranchStar> submit(@PathVariable Long id, @RequestParam(required = false) String submitter) {
        return ApiResponse.ok("submitted", starService.submit(id, submitter));
    }

    @PutMapping("/api/stars/{id}/review")
    public ApiResponse<sysBranchStar> review(@PathVariable Long id, @RequestBody sysBranchStar reviewPayload) {
        return ApiResponse.ok("reviewed", starService.review(id, reviewPayload));
    }

    @GetMapping("/api/stars/stats/overview")
    public ApiResponse<StarStatsOverview> statsOverview(@RequestParam(required = false) String year) {
        return ApiResponse.ok(starService.statsOverview(year));
    }

    @GetMapping("/api/stars/stats/rating-distribution")
    public ApiResponse<List<StarBucketCount>> statsByRating(@RequestParam(required = false) String year) {
        return ApiResponse.ok(starService.statsByRating(year));
    }

    @GetMapping("/api/stars/stats/process-distribution")
    public ApiResponse<List<StarBucketCount>> statsByProcess(@RequestParam(required = false) String year) {
        return ApiResponse.ok(starService.statsByProcess(year));
    }

    @GetMapping("/api/stars/stats/dept-ranking")
    public ApiResponse<List<StarDeptRank>> deptRanking(
            @RequestParam(required = false) String year,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return ApiResponse.ok(starService.deptRanking(year, limit));
    }

    @GetMapping("/api/stars/stats/trend")
    public ApiResponse<List<StarYearTrend>> trend() {
        return ApiResponse.ok(starService.trend());
    }

    @DeleteMapping("/api/stars/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        starService.delete(id);
        return ApiResponse.ok("deleted", "ok");
    }
}
