package org.example.djxt.service.impl;

import org.example.djxt.common.BusinessException;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysBranch;
import org.example.djxt.mapper.SysBranchMapper;
import org.example.djxt.service.SysBranchService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
        Example example = new Example(SysBranch.class);
        Example.Criteria criteria = example.createCriteria();

        if (keyword != null && !keyword.trim().isEmpty()) {
            criteria.andLike("partybranchname", "%" + keyword.trim() + "%");
        }
        if (deptId != null) {
            criteria.andEqualTo("deptId", deptId);
        }
        if (status != null && !status.trim().isEmpty()) {
            criteria.andEqualTo("status", status);
        }

        example.orderBy("id").desc();
        List<SysBranch> list = branchMapper.selectByExample(example);
        return PageResult.fromList(list, page, size);
    }

    @Override
    public SysBranch getById(Integer id) {
        SysBranch branch = branchMapper.selectByPrimaryKey(id);
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
        branchMapper.insertSelective(branch);
        return branch;
    }

    @Override
    public SysBranch update(Integer id, SysBranch branch) {
        getById(id);
        branch.setId(id);
        branch.setUpdateTime(LocalDateTime.now());
        branchMapper.updateByPrimaryKeySelective(branch);
        return getById(id);
    }

    @Override
    public void delete(Integer id) {
        getById(id);
        branchMapper.deleteByPrimaryKey(id);
    }
}
