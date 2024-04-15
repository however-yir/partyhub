package org.example.djxt.service;

import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysBranch;

public interface SysBranchService {
    PageResult<SysBranch> list(String keyword, Integer deptId, String status, int page, int size);

    SysBranch getById(Integer id);

    SysBranch create(SysBranch branch);

    SysBranch update(Integer id, SysBranch branch);

    void delete(Integer id);
}
