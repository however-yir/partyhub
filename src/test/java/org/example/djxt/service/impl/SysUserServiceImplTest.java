package org.example.djxt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.djxt.common.BusinessException;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysUser;
import org.example.djxt.mapper.SysUserMapper;
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
class SysUserServiceImplTest {

    @Mock
    private SysUserMapper userMapper;

    @InjectMocks
    private SysUserServiceImpl userService;

    @Test
    void shouldListUsersWithDefaultActiveFlagFilter() {
        SysUser user = new SysUser();
        user.setUserId(1L);
        user.setUserName("zhangsan");
        when(userMapper.selectList(any(QueryWrapper.class))).thenReturn(Collections.singletonList(user));

        PageResult<SysUser> page = userService.list("zhang", 2, "0", 1, 10);

        assertEquals(1, page.getItems().size());
        assertEquals("zhangsan", page.getItems().get(0).getUserName());
    }

    @Test
    void shouldCreateUserWithDefaults() {
        when(userMapper.selectCount(any(QueryWrapper.class))).thenReturn(0L);

        SysUser payload = new SysUser();
        payload.setUserName("lisi");
        payload.setNickName("李四");
        SysUser created = userService.create(payload);

        assertEquals("00", created.getUserType());
        assertEquals("0", created.getStatus());
        assertEquals("0", created.getDelFlag());
        assertEquals("123456", created.getPassword());
        assertNotNull(created.getCreateTime());
        assertNotNull(created.getUpdateTime());
        verify(userMapper).insert(payload);
    }

    @Test
    void shouldThrowWhenUsernameDuplicatedOnCreate() {
        when(userMapper.selectCount(any(QueryWrapper.class))).thenReturn(1L);

        SysUser payload = new SysUser();
        payload.setUserName("duplicated");
        payload.setNickName("重复");

        BusinessException exception = assertThrows(BusinessException.class, () -> userService.create(payload));
        assertEquals("用户账号已存在: duplicated", exception.getMessage());
    }

    @Test
    void shouldUpdateUserAndCheckDuplicateWhenUsernameChanged() {
        SysUser existing = new SysUser();
        existing.setUserId(8L);
        existing.setUserName("old-name");
        existing.setDelFlag("0");

        SysUser refreshed = new SysUser();
        refreshed.setUserId(8L);
        refreshed.setUserName("new-name");
        refreshed.setDelFlag("0");

        when(userMapper.selectById(8L)).thenReturn(existing, refreshed);
        when(userMapper.selectCount(any(QueryWrapper.class))).thenReturn(0L);

        SysUser updatePayload = new SysUser();
        updatePayload.setUserName("new-name");
        SysUser updated = userService.update(8L, updatePayload);

        assertEquals("new-name", updated.getUserName());
        assertNotNull(updatePayload.getUpdateTime());
        verify(userMapper).updateById(updatePayload);
    }

    @Test
    void shouldDeleteUserByLogicalDelete() {
        SysUser existing = new SysUser();
        existing.setUserId(10L);
        existing.setUserName("wangwu");
        existing.setDelFlag("0");
        when(userMapper.selectById(10L)).thenReturn(existing);

        userService.delete(10L);

        verify(userMapper).updateById(any(SysUser.class));
    }

    @Test
    void shouldThrowWhenDeletingNonActiveUser() {
        SysUser existing = new SysUser();
        existing.setUserId(11L);
        existing.setDelFlag("1");
        when(userMapper.selectById(11L)).thenReturn(existing);

        BusinessException exception = assertThrows(BusinessException.class, () -> userService.delete(11L));

        assertEquals("删除失败: 11", exception.getMessage());
        verify(userMapper).updateById(any(SysUser.class));
    }
}
