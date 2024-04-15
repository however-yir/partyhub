package org.example.djxt.service;

import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysUser;

public interface SysUserService {
    PageResult<SysUser> list(String keyword, Integer branchId, String status, int page, int size);

    SysUser getById(Long id);

    SysUser create(SysUser user);

    SysUser update(Long id, SysUser user);

    void delete(Long id);
}
