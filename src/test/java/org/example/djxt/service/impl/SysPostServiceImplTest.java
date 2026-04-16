package org.example.djxt.service.impl;

import org.example.djxt.common.BusinessException;
import org.example.djxt.domain.SysPost;
import org.example.djxt.mapper.SysPostMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SysPostServiceImplTest {

    @Mock
    private SysPostMapper postMapper;

    @InjectMocks
    private SysPostServiceImpl postService;

    @Test
    void shouldCreatePostWithDefaultSortAndStatus() {
        SysPost payload = new SysPost();
        payload.setPostCode("BRANCH_LEADER");
        payload.setPostName("支部书记");

        SysPost created = postService.create(payload);

        assertEquals(1, created.getPostSort());
        assertEquals("0", created.getStatus());
        assertNotNull(created.getCreateTime());
        assertNotNull(created.getUpdateTime());
        verify(postMapper).insert(payload);
    }

    @Test
    void shouldThrowWhenPostRequiredFieldsMissing() {
        SysPost payload = new SysPost();
        payload.setPostCode(" ");
        payload.setPostName(" ");

        BusinessException exception = assertThrows(BusinessException.class, () -> postService.create(payload));

        assertEquals("岗位编码和岗位名称不能为空", exception.getMessage());
    }

    @Test
    void shouldUpdatePostAndReturnLatest() {
        SysPost existing = new SysPost();
        existing.setPostId(6L);
        existing.setPostName("旧岗位");

        SysPost refreshed = new SysPost();
        refreshed.setPostId(6L);
        refreshed.setPostName("新岗位");

        when(postMapper.selectById(6L)).thenReturn(existing, refreshed);

        SysPost updatePayload = new SysPost();
        updatePayload.setPostName("新岗位");
        SysPost updated = postService.update(6L, updatePayload);

        assertEquals("新岗位", updated.getPostName());
        assertNotNull(updatePayload.getUpdateTime());
        verify(postMapper).updateById(updatePayload);
    }

    @Test
    void shouldDeletePostAfterExistenceCheck() {
        SysPost existing = new SysPost();
        existing.setPostId(3L);
        when(postMapper.selectById(3L)).thenReturn(existing);

        postService.delete(3L);

        verify(postMapper).deleteById(3L);
    }
}
