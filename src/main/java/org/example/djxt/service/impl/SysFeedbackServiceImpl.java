package org.example.djxt.service.impl;

import org.example.djxt.common.BusinessException;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysFeedback;
import org.example.djxt.mapper.SysFeedbackMapper;
import org.example.djxt.service.SysFeedbackService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
        Example example = new Example(SysFeedback.class);
        Example.Criteria criteria = example.createCriteria();
        if (messageId != null) {
            criteria.andEqualTo("messageid", messageId);
        }
        example.orderBy("id").desc();

        List<SysFeedback> list = feedbackMapper.selectByExample(example);
        return PageResult.fromList(list, page, size);
    }

    @Override
    public SysFeedback getById(Integer id) {
        SysFeedback feedback = feedbackMapper.selectByPrimaryKey(id);
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
        feedbackMapper.insertSelective(feedback);
        return feedback;
    }

    @Override
    public SysFeedback update(Integer id, SysFeedback feedback) {
        getById(id);
        feedback.setId(id);
        feedback.setUpdateTime(LocalDateTime.now());
        feedbackMapper.updateByPrimaryKeySelective(feedback);
        return getById(id);
    }

    @Override
    public void delete(Integer id) {
        getById(id);
        feedbackMapper.deleteByPrimaryKey(id);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
