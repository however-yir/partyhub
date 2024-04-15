package org.example.djxt.controller;

import org.example.djxt.common.ApiResponse;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysBranch;
import org.example.djxt.service.SysBranchService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final SysBranchService branchService;

    public BranchController(SysBranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    public ApiResponse<PageResult<SysBranch>> list(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer deptId,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.ok(branchService.list(keyword, deptId, status, page, size));
    }

    @GetMapping("/{id}")
    public ApiResponse<SysBranch> get(@PathVariable Integer id) {
        return ApiResponse.ok(branchService.getById(id));
    }

    @PostMapping
    public ApiResponse<SysBranch> create(@RequestBody SysBranch branch) {
        return ApiResponse.ok("created", branchService.create(branch));
    }

    @PutMapping("/{id}")
    public ApiResponse<SysBranch> update(@PathVariable Integer id, @RequestBody SysBranch branch) {
        return ApiResponse.ok("updated", branchService.update(id, branch));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Integer id) {
        branchService.delete(id);
        return ApiResponse.ok("deleted", "ok");
    }
}
