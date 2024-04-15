package org.example.djxt.service;

import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysMessage;

public interface SysMessageService {
    PageResult<SysMessage> list(String keyword, Integer receiveUserId, String messageFlag, int page, int size);

    SysMessage getById(Integer id);

    SysMessage create(SysMessage message);

    SysMessage update(Integer id, SysMessage message);

    void delete(Integer id);
}
