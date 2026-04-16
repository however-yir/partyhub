package org.example.djxt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.djxt.common.BusinessException;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysMessage;
import org.example.djxt.mapper.SysMessageMapper;
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
class SysMessageServiceImplTest {

    @Mock
    private SysMessageMapper messageMapper;

    @InjectMocks
    private SysMessageServiceImpl messageService;

    @Test
    void shouldListMessages() {
        SysMessage message = new SysMessage();
        message.setMessageId(1);
        message.setMessageTitle("关于评星填报的通知");
        when(messageMapper.selectList(any(QueryWrapper.class))).thenReturn(Collections.singletonList(message));

        PageResult<SysMessage> page = messageService.list("评星", 3, "0", 1, 10);

        assertEquals(1, page.getItems().size());
        assertEquals("关于评星填报的通知", page.getItems().get(0).getMessageTitle());
    }

    @Test
    void shouldCreateMessageWithDefaults() {
        SysMessage payload = new SysMessage();
        payload.setMessageTitle("系统通知");

        SysMessage created = messageService.create(payload);

        assertEquals("0", created.getMessageFlag());
        assertEquals("0", created.getStatus());
        assertEquals("0", created.getMessageLevel());
        assertNotNull(created.getCreateTime());
        assertNotNull(created.getUpdateTime());
        verify(messageMapper).insert(payload);
    }

    @Test
    void shouldThrowWhenMessageTitleIsBlank() {
        SysMessage payload = new SysMessage();
        payload.setMessageTitle(" ");

        BusinessException exception = assertThrows(BusinessException.class, () -> messageService.create(payload));

        assertEquals("通知标题不能为空", exception.getMessage());
    }

    @Test
    void shouldUpdateMessageAndReturnLatest() {
        SysMessage existing = new SysMessage();
        existing.setMessageId(7);
        existing.setMessageTitle("旧标题");

        SysMessage refreshed = new SysMessage();
        refreshed.setMessageId(7);
        refreshed.setMessageTitle("新标题");

        when(messageMapper.selectById(7)).thenReturn(existing, refreshed);

        SysMessage updatePayload = new SysMessage();
        updatePayload.setMessageTitle("新标题");
        SysMessage updated = messageService.update(7, updatePayload);

        assertEquals("新标题", updated.getMessageTitle());
        assertNotNull(updatePayload.getUpdateTime());
        verify(messageMapper).updateById(updatePayload);
    }

    @Test
    void shouldDeleteMessageAfterExistenceCheck() {
        SysMessage existing = new SysMessage();
        existing.setMessageId(8);
        when(messageMapper.selectById(8)).thenReturn(existing);

        messageService.delete(8);

        verify(messageMapper).deleteById(8);
    }
}
