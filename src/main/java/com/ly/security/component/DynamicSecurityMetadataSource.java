package com.ly.security.component;

import cn.hutool.core.util.URLUtil;
import com.ly.mapper.ResourceMapper;
import com.ly.security.dto.UrlRolesRule;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @description: 动态权限数据源，用于获取动态权限规则
 * @author: LiYuan
 * @time: 2023/5/17 3:38
 */
@Component
@RequiredArgsConstructor
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static List<UrlRolesRule> resourceRolesRules= null;
    private final ResourceMapper resourceMapper;

    @PostConstruct
    public void loadDataSource() {
        resourceRolesRules = resourceMapper.getResourceRolesRules();
    }

    public void clearDataSource() {
        resourceRolesRules.clear();
        resourceRolesRules = null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (resourceRolesRules == null) this.loadDataSource();
        //获取当前访问的路径
        String url = ((FilterInvocation) object).getRequestUrl();
        String path = URLUtil.getPath(url);
        PathMatcher pathMatcher = new AntPathMatcher();
        Iterator<UrlRolesRule> iterator = resourceRolesRules.iterator();
        //获取访问该路径所需资源
        while (iterator.hasNext()) {
            String pattern = iterator.next().getUrl();
            if (pathMatcher.match(pattern, path)) {
                return SecurityConfig.createList(iterator.next().getRoles().toArray(new String[0]));
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
