package org.example.djxt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.djxt.common.BusinessException;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysPost;
import org.example.djxt.mapper.SysPostMapper;
import org.example.djxt.service.SysPostService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SysPostServiceImpl implements SysPostService {

    private final SysPostMapper postMapper;

    public SysPostServiceImpl(SysPostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public PageResult<SysPost> list(String keyword, String status, int page, int size) {
        QueryWrapper<SysPost> query = new QueryWrapper<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            query.like("post_name", keyword.trim());
        }
        if (status != null && !status.trim().isEmpty()) {
            query.eq("status", status);
        }

        query.orderByAsc("post_sort");
        List<SysPost> list = postMapper.selectList(query);
        return PageResult.fromList(list, page, size);
    }

    @Override
    public SysPost getById(Long id) {
        SysPost post = postMapper.selectById(id);
        if (post == null) {
            throw new BusinessException("岗位不存在: " + id);
        }
        return post;
    }

    @Override
    public SysPost create(SysPost post) {
        if (post == null || isBlank(post.getPostCode()) || isBlank(post.getPostName())) {
            throw new BusinessException("岗位编码和岗位名称不能为空");
        }
        if (post.getPostSort() == null) {
            post.setPostSort(1);
        }
        if (isBlank(post.getStatus())) {
            post.setStatus("0");
        }

        post.setCreateTime(LocalDateTime.now());
        post.setUpdateTime(LocalDateTime.now());
        postMapper.insert(post);
        return post;
    }

    @Override
    public SysPost update(Long id, SysPost post) {
        getById(id);
        post.setPostId(id);
        post.setUpdateTime(LocalDateTime.now());
        postMapper.updateById(post);
        return getById(id);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        postMapper.deleteById(id);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
