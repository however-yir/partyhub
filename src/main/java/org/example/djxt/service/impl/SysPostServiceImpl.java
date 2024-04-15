package org.example.djxt.service.impl;

import org.example.djxt.common.BusinessException;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysPost;
import org.example.djxt.mapper.SysPostMapper;
import org.example.djxt.service.SysPostService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
        Example example = new Example(SysPost.class);
        Example.Criteria criteria = example.createCriteria();

        if (keyword != null && !keyword.trim().isEmpty()) {
            criteria.andLike("postName", "%" + keyword.trim() + "%");
        }
        if (status != null && !status.trim().isEmpty()) {
            criteria.andEqualTo("status", status);
        }

        example.orderBy("postSort").asc();
        List<SysPost> list = postMapper.selectByExample(example);
        return PageResult.fromList(list, page, size);
    }

    @Override
    public SysPost getById(Long id) {
        SysPost post = postMapper.selectByPrimaryKey(id);
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
        postMapper.insertSelective(post);
        return post;
    }

    @Override
    public SysPost update(Long id, SysPost post) {
        getById(id);
        post.setPostId(id);
        post.setUpdateTime(LocalDateTime.now());
        postMapper.updateByPrimaryKeySelective(post);
        return getById(id);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        postMapper.deleteByPrimaryKey(id);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
