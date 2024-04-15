package org.example.djxt.controller;

import org.example.djxt.common.ApiResponse;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysUser;
import org.example.djxt.service.SysUserService;
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
@RequestMapping("/api/users")
public class UserController {

    private final SysUserService userService;

    public UserController(SysUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse<PageResult<SysUser>> list(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer branchId,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.ok(userService.list(keyword, branchId, status, page, size));
    }

    @GetMapping("/{id}")
    public ApiResponse<SysUser> get(@PathVariable Long id) {
        return ApiResponse.ok(userService.getById(id));
    }

    @PostMapping
    public ApiResponse<SysUser> create(@RequestBody SysUser user) {
        return ApiResponse.ok("created", userService.create(user));
    }

    @PutMapping("/{id}")
    public ApiResponse<SysUser> update(@PathVariable Long id, @RequestBody SysUser user) {
        return ApiResponse.ok("updated", userService.update(id, user));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        userService.delete(id);
        return ApiResponse.ok("deleted", "ok");
    }
}
