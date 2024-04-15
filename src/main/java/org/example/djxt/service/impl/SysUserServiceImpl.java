package org.example.djxt.service.impl;

import org.example.djxt.common.BusinessException;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysUser;
import org.example.djxt.mapper.SysUserMapper;
import org.example.djxt.service.SysUserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper userMapper;

    public SysUserServiceImpl(SysUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public PageResult<SysUser> list(String keyword, Integer branchId, String status, int page, int size) {
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", "0");

        if (keyword != null && !keyword.trim().isEmpty()) {
            String q = "%" + keyword.trim() + "%";
            criteria.andCondition("(user_name like '" + q + "' or nick_name like '" + q + "')");
        }
        if (branchId != null) {
            criteria.andEqualTo("branchId", branchId);
        }
        if (status != null && !status.trim().isEmpty()) {
            criteria.andEqualTo("status", status);
        }

        example.orderBy("userId").desc();
        List<SysUser> list = userMapper.selectByExample(example);
        return PageResult.fromList(list, page, size);
    }

    @Override
    public SysUser getById(Long id) {
        SysUser user = userMapper.selectByPrimaryKey(id);
        if (user == null || "2".equals(user.getDelFlag())) {
            throw new BusinessException("用户不存在: " + id);
        }
        return user;
    }

    @Override
    public SysUser create(SysUser user) {
        if (user == null || isBlank(user.getUserName()) || isBlank(user.getNickName())) {
            throw new BusinessException("用户账号和昵称不能为空");
        }
        checkUsernameDuplicate(user.getUserName(), null);

        if (isBlank(user.getUserType())) {
            user.setUserType("00");
        }
        if (isBlank(user.getStatus())) {
            user.setStatus("0");
        }
        if (isBlank(user.getDelFlag())) {
            user.setDelFlag("0");
        }
        if (isBlank(user.getPassword())) {
            user.setPassword("123456");
        }

        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insertSelective(user);
        return user;
    }

    @Override
    public SysUser update(Long id, SysUser user) {
        SysUser existing = getById(id);
        if (!isBlank(user.getUserName()) && !existing.getUserName().equals(user.getUserName())) {
            checkUsernameDuplicate(user.getUserName(), id);
        }

        user.setUserId(id);
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateByPrimaryKeySelective(user);
        return getById(id);
    }

    @Override
    public void delete(Long id) {
        SysUser existing = getById(id);
        SysUser deleted = new SysUser();
        deleted.setUserId(id);
        deleted.setDelFlag("2");
        deleted.setStatus("1");
        deleted.setUpdateTime(LocalDateTime.now());
        deleted.setRemark("逻辑删除于 " + LocalDateTime.now());
        userMapper.updateByPrimaryKeySelective(deleted);

        if ("0".equals(existing.getDelFlag())) {
            return;
        }
        throw new BusinessException("删除失败: " + id);
    }

    private void checkUsernameDuplicate(String username, Long excludeId) {
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", username);
        criteria.andEqualTo("delFlag", "0");
        if (excludeId != null) {
            criteria.andNotEqualTo("userId", excludeId);
        }

        int count = userMapper.selectCountByExample(example);
        if (count > 0) {
            throw new BusinessException("用户账号已存在: " + username);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
