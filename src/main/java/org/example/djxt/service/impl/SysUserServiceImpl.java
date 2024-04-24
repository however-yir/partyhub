package org.example.djxt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.djxt.common.BusinessException;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysUser;
import org.example.djxt.mapper.SysUserMapper;
import org.example.djxt.service.SysUserService;
import org.springframework.stereotype.Service;

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
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.eq("del_flag", "0");

        if (keyword != null && !keyword.trim().isEmpty()) {
            String q = keyword.trim();
            query.and(wrapper -> wrapper.like("user_name", q).or().like("nick_name", q));
        }
        if (branchId != null) {
            query.eq("branch_id", branchId);
        }
        if (status != null && !status.trim().isEmpty()) {
            query.eq("status", status);
        }

        query.orderByDesc("user_id");
        List<SysUser> list = userMapper.selectList(query);
        return PageResult.fromList(list, page, size);
    }

    @Override
    public SysUser getById(Long id) {
        SysUser user = userMapper.selectById(id);
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
        userMapper.insert(user);
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
        userMapper.updateById(user);
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
        userMapper.updateById(deleted);

        if ("0".equals(existing.getDelFlag())) {
            return;
        }
        throw new BusinessException("删除失败: " + id);
    }

    private void checkUsernameDuplicate(String username, Long excludeId) {
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.eq("user_name", username);
        query.eq("del_flag", "0");
        if (excludeId != null) {
            query.ne("user_id", excludeId);
        }

        Long count = userMapper.selectCount(query);
        if (count > 0) {
            throw new BusinessException("用户账号已存在: " + username);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
