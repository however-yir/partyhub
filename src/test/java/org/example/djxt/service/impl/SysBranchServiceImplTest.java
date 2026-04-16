package org.example.djxt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.djxt.common.BusinessException;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysBranch;
import org.example.djxt.mapper.SysBranchMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SysBranchServiceImplTest {

    @Mock
    private SysBranchMapper branchMapper;

    @InjectMocks
    private SysBranchServiceImpl branchService;

    @Test
    void shouldListBranchesWithPaging() {
        SysBranch branch = new SysBranch();
        branch.setId(1);
        branch.setPartybranchname("理学院党委");
        when(branchMapper.selectList(any(QueryWrapper.class))).thenReturn(Collections.singletonList(branch));

        PageResult<SysBranch> page = branchService.list("理学院", 10, "0", 1, 10);

        assertEquals(1, page.getItems().size());
        assertEquals("理学院党委", page.getItems().get(0).getPartybranchname());
    }

    @Test
    void shouldThrowWhenBranchNotFound() {
        when(branchMapper.selectById(99)).thenReturn(null);

        BusinessException exception = assertThrows(BusinessException.class, () -> branchService.getById(99));

        assertEquals("党支部不存在: 99", exception.getMessage());
    }

    @Test
    void shouldCreateBranchWithDefaultStatus() {
        SysBranch payload = new SysBranch();
        payload.setPartybranchname("计算机学院党委");

        SysBranch created = branchService.create(payload);

        assertEquals("0", created.getStatus());
        assertNotNull(created.getCreateTime());
        assertNotNull(created.getUpdateTime());
        verify(branchMapper).insert(payload);
    }

    @Test
    void shouldUpdateBranchAndReturnLatest() {
        SysBranch existing = new SysBranch();
        existing.setId(9);
        existing.setPartybranchname("更新前");

        SysBranch refreshed = new SysBranch();
        refreshed.setId(9);
        refreshed.setPartybranchname("更新后");

        when(branchMapper.selectById(9)).thenReturn(existing, refreshed);

        SysBranch updatePayload = new SysBranch();
        updatePayload.setPartybranchname("更新后");
        SysBranch result = branchService.update(9, updatePayload);

        assertEquals(9, result.getId());
        assertEquals("更新后", result.getPartybranchname());
        assertNotNull(updatePayload.getUpdateTime());
        verify(branchMapper).updateById(updatePayload);
    }

    @Test
    void shouldDeleteBranchAfterExistenceCheck() {
        SysBranch existing = new SysBranch();
        existing.setId(5);
        when(branchMapper.selectById(5)).thenReturn(existing);

        branchService.delete(5);

        verify(branchMapper).deleteById(5);
    }
}
