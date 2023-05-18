package com.ly.security.component;

import com.ly.common.constant.MessageConstant;
import com.ly.mapper.UserMapper;
import com.ly.security.dto.SecurityUser;
import com.ly.security.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @description: 我的用户详细信息服务
 * @author: LiYuan
 * @time: 2023/5/17 3:34
 */
@Component
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto userDto = userMapper.loadUserByUsername(username);
        if(userDto==null){
            throw new BadCredentialsException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        SecurityUser securityUser = new SecurityUser(userDto);
        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }
}
