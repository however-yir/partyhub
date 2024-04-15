package org.example.djxt.service;

import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysPost;

public interface SysPostService {
    PageResult<SysPost> list(String keyword, String status, int page, int size);

    SysPost getById(Long id);

    SysPost create(SysPost post);

    SysPost update(Long id, SysPost post);

    void delete(Long id);
}
