package org.example.djxt.controller;

import org.example.djxt.common.ApiResponse;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysPost;
import org.example.djxt.service.SysPostService;
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
@RequestMapping("/api/posts")
public class PostController {

    private final SysPostService postService;

    public PostController(SysPostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ApiResponse<PageResult<SysPost>> list(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.ok(postService.list(keyword, status, page, size));
    }

    @GetMapping("/{id}")
    public ApiResponse<SysPost> get(@PathVariable Long id) {
        return ApiResponse.ok(postService.getById(id));
    }

    @PostMapping
    public ApiResponse<SysPost> create(@RequestBody SysPost post) {
        return ApiResponse.ok("created", postService.create(post));
    }

    @PutMapping("/{id}")
    public ApiResponse<SysPost> update(@PathVariable Long id, @RequestBody SysPost post) {
        return ApiResponse.ok("updated", postService.update(id, post));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        postService.delete(id);
        return ApiResponse.ok("deleted", "ok");
    }
}
