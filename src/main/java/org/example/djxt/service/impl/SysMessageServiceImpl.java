package org.example.djxt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.djxt.common.BusinessException;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysMessage;
import org.example.djxt.mapper.SysMessageMapper;
import org.example.djxt.service.SysMessageService;
import org.springframework.stereotype.Service;

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
        QueryWrapper<SysMessage> query = new QueryWrapper<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            query.like("message_title", keyword.trim());
        }
        if (receiveUserId != null) {
            query.eq("receive_user_id", receiveUserId);
        }
        if (messageFlag != null && !messageFlag.trim().isEmpty()) {
            query.eq("message_flag", messageFlag);
        }

        query.orderByDesc("message_id");
        List<SysMessage> list = messageMapper.selectList(query);
        return PageResult.fromList(list, page, size);
    }

    @Override
    public SysMessage getById(Integer id) {
        SysMessage message = messageMapper.selectById(id);
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
        messageMapper.insert(message);
        return message;
    }

    @Override
    public SysMessage update(Integer id, SysMessage message) {
        getById(id);
        message.setMessageId(id);
        message.setUpdateTime(LocalDateTime.now());
        messageMapper.updateById(message);
        return getById(id);
    }

    @Override
    public void delete(Integer id) {
        getById(id);
        messageMapper.deleteById(id);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
