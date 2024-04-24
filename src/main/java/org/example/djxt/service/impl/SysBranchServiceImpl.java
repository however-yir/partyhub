package org.example.djxt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.djxt.common.BusinessException;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysBranch;
import org.example.djxt.mapper.SysBranchMapper;
import org.example.djxt.service.SysBranchService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SysBranchServiceImpl implements SysBranchService {

    private final SysBranchMapper branchMapper;

    public SysBranchServiceImpl(SysBranchMapper branchMapper) {
        this.branchMapper = branchMapper;
    }

    @Override
    public PageResult<SysBranch> list(String keyword, Integer deptId, String status, int page, int size) {
        QueryWrapper<SysBranch> query = new QueryWrapper<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            query.like("partybranchname", keyword.trim());
        }
        if (deptId != null) {
            query.eq("dept_id", deptId);
        }
        if (status != null && !status.trim().isEmpty()) {
            query.eq("status", status);
        }

        query.orderByDesc("id");
        List<SysBranch> list = branchMapper.selectList(query);
        return PageResult.fromList(list, page, size);
    }

    @Override
    public SysBranch getById(Integer id) {
        SysBranch branch = branchMapper.selectById(id);
        if (branch == null) {
            throw new BusinessException("党支部不存在: " + id);
        }
        return branch;
    }

    @Override
    public SysBranch create(SysBranch branch) {
        if (branch == null || branch.getPartybranchname() == null || branch.getPartybranchname().trim().isEmpty()) {
            throw new BusinessException("党支部名称不能为空");
        }
        if (branch.getStatus() == null || branch.getStatus().trim().isEmpty()) {
            branch.setStatus("0");
        }
        branch.setCreateTime(LocalDateTime.now());
        branch.setUpdateTime(LocalDateTime.now());
        branchMapper.insert(branch);
        return branch;
    }

    @Override
    public SysBranch update(Integer id, SysBranch branch) {
        getById(id);
        branch.setId(id);
        branch.setUpdateTime(LocalDateTime.now());
        branchMapper.updateById(branch);
        return getById(id);
    }

    @Override
    public void delete(Integer id) {
        getById(id);
        branchMapper.deleteById(id);
    }
}
