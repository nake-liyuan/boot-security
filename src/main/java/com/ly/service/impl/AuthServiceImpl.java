package com.ly.service.impl;

import cn.hutool.json.JSONUtil;
import com.ly.common.exception.ApiException;
import com.ly.security.dto.PayloadDto;
import com.ly.security.util.JwtTokenUtil;
import com.ly.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: LiYuan
 * @time: 2023/5/17 4:35
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final HttpServletRequest request;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public String login(String username, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        ((AbstractAuthenticationToken) authentication).setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        authentication = authenticationManager.authenticate(authentication);
        if (authentication != null && authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<String> authorityList = authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            PayloadDto defaultPayloadDto = jwtTokenUtil.getDefaultPayloadDto(username, authorityList);
            return jwtTokenUtil.generateTokenByRSA(JSONUtil.toJsonStr(defaultPayloadDto));
        } else {
            throw new ApiException("无法对用户进行身份验证：" + username);
        }
    }
}
