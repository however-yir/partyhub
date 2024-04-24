package org.example.djxt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.djxt.common.BusinessException;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysFeedback;
import org.example.djxt.mapper.SysFeedbackMapper;
import org.example.djxt.service.SysFeedbackService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SysFeedbackServiceImpl implements SysFeedbackService {

    private final SysFeedbackMapper feedbackMapper;

    public SysFeedbackServiceImpl(SysFeedbackMapper feedbackMapper) {
        this.feedbackMapper = feedbackMapper;
    }

    @Override
    public PageResult<SysFeedback> list(Integer messageId, int page, int size) {
        QueryWrapper<SysFeedback> query = new QueryWrapper<>();
        if (messageId != null) {
            query.eq("messageid", messageId);
        }
        query.orderByDesc("id");

        List<SysFeedback> list = feedbackMapper.selectList(query);
        return PageResult.fromList(list, page, size);
    }

    @Override
    public SysFeedback getById(Integer id) {
        SysFeedback feedback = feedbackMapper.selectById(id);
        if (feedback == null) {
            throw new BusinessException("反馈不存在: " + id);
        }
        return feedback;
    }

    @Override
    public SysFeedback create(SysFeedback feedback) {
        if (feedback == null || feedback.getMessageid() == null || isBlank(feedback.getContent())) {
            throw new BusinessException("反馈内容和通知ID不能为空");
        }

        feedback.setCreateTime(LocalDateTime.now());
        feedback.setUpdateTime(LocalDateTime.now());
        feedbackMapper.insert(feedback);
        return feedback;
    }

    @Override
    public SysFeedback update(Integer id, SysFeedback feedback) {
        getById(id);
        feedback.setId(id);
        feedback.setUpdateTime(LocalDateTime.now());
        feedbackMapper.updateById(feedback);
        return getById(id);
    }

    @Override
    public void delete(Integer id) {
        getById(id);
        feedbackMapper.deleteById(id);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
