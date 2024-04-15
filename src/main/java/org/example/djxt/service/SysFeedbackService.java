package org.example.djxt.service;

import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysFeedback;

public interface SysFeedbackService {
    PageResult<SysFeedback> list(Integer messageId, int page, int size);

    SysFeedback getById(Integer id);

    SysFeedback create(SysFeedback feedback);

    SysFeedback update(Integer id, SysFeedback feedback);

    void delete(Integer id);
}
