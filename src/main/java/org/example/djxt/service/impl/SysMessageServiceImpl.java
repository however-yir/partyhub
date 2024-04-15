package org.example.djxt.service.impl;

import org.example.djxt.common.BusinessException;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysMessage;
import org.example.djxt.mapper.SysMessageMapper;
import org.example.djxt.service.SysMessageService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SysMessageServiceImpl implements SysMessageService {

    private final SysMessageMapper messageMapper;

    public SysMessageServiceImpl(SysMessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public PageResult<SysMessage> list(String keyword, Integer receiveUserId, String messageFlag, int page, int size) {
        Example example = new Example(SysMessage.class);
        Example.Criteria criteria = example.createCriteria();

        if (keyword != null && !keyword.trim().isEmpty()) {
            criteria.andLike("messageTitle", "%" + keyword.trim() + "%");
        }
        if (receiveUserId != null) {
            criteria.andEqualTo("receiveUserId", receiveUserId);
        }
        if (messageFlag != null && !messageFlag.trim().isEmpty()) {
            criteria.andEqualTo("messageFlag", messageFlag);
        }

        example.orderBy("messageId").desc();
        List<SysMessage> list = messageMapper.selectByExample(example);
        return PageResult.fromList(list, page, size);
    }

    @Override
    public SysMessage getById(Integer id) {
        SysMessage message = messageMapper.selectByPrimaryKey(id);
        if (message == null) {
            throw new BusinessException("通知不存在: " + id);
        }
        return message;
    }

    @Override
    public SysMessage create(SysMessage message) {
        if (message == null || isBlank(message.getMessageTitle())) {
            throw new BusinessException("通知标题不能为空");
        }
        if (isBlank(message.getMessageFlag())) {
            message.setMessageFlag("0");
        }
        if (isBlank(message.getStatus())) {
            message.setStatus("0");
        }
        if (isBlank(message.getMessageLevel())) {
            message.setMessageLevel("0");
        }

        message.setCreateTime(LocalDateTime.now());
        message.setUpdateTime(LocalDateTime.now());
        messageMapper.insertSelective(message);
        return message;
    }

    @Override
    public SysMessage update(Integer id, SysMessage message) {
        getById(id);
        message.setMessageId(id);
        message.setUpdateTime(LocalDateTime.now());
        messageMapper.updateByPrimaryKeySelective(message);
        return getById(id);
    }

    @Override
    public void delete(Integer id) {
        getById(id);
        messageMapper.deleteByPrimaryKey(id);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
