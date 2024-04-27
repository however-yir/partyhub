package org.example.djxt.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.djxt.domain.SysUser;
import org.example.djxt.mapper.SysUserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final SysUserMapper userMapper;

    public CustomUserDetailsService(SysUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.eq("user_name", username)
                .eq("del_flag", "0")
                .eq("status", "0")
                .last("limit 1");

        SysUser sysUser = userMapper.selectOne(query);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户不存在: " + username);
        }

        // 保持兼容历史明文口令，生产环境建议迁移为 BCrypt 哈希并移除 {noop}
        return User.withUsername(sysUser.getUserName())
                .password("{noop}" + sysUser.getPassword())
                .authorities("ROLE_USER")
                .build();
    }
}
