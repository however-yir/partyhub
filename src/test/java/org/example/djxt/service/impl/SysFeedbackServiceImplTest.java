package org.example.djxt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.djxt.common.BusinessException;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysFeedback;
import org.example.djxt.mapper.SysFeedbackMapper;
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
class SysFeedbackServiceImplTest {

    @Mock
    private SysFeedbackMapper feedbackMapper;

    @InjectMocks
    private SysFeedbackServiceImpl feedbackService;

    @Test
    void shouldListFeedbackByMessageId() {
        SysFeedback feedback = new SysFeedback();
        feedback.setId(1);
        feedback.setMessageid(1001);
        feedback.setContent("流程很好");
        when(feedbackMapper.selectList(any(QueryWrapper.class))).thenReturn(Collections.singletonList(feedback));

        PageResult<SysFeedback> page = feedbackService.list(1001, 1, 10);

        assertEquals(1, page.getItems().size());
        assertEquals("流程很好", page.getItems().get(0).getContent());
    }

    @Test
    void shouldThrowWhenFeedbackPayloadInvalid() {
        SysFeedback payload = new SysFeedback();
        payload.setMessageid(null);
        payload.setContent(" ");

        BusinessException exception = assertThrows(BusinessException.class, () -> feedbackService.create(payload));

        assertEquals("反馈内容和通知ID不能为空", exception.getMessage());
    }

    @Test
    void shouldCreateFeedbackAndSetTimestamps() {
        SysFeedback payload = new SysFeedback();
        payload.setMessageid(2002);
        payload.setContent("建议增加评分说明");

        SysFeedback created = feedbackService.create(payload);

        assertNotNull(created.getCreateTime());
        assertNotNull(created.getUpdateTime());
        verify(feedbackMapper).insert(payload);
    }

    @Test
    void shouldDeleteFeedbackAfterExistenceCheck() {
        SysFeedback existing = new SysFeedback();
        existing.setId(9);
        when(feedbackMapper.selectById(9)).thenReturn(existing);

        feedbackService.delete(9);

        verify(feedbackMapper).deleteById(9);
    }
}
