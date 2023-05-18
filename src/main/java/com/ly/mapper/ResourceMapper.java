package com.ly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.entity.Resource;
import com.ly.security.dto.UrlRolesRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 
 * @author: LiYuan
 * @time: 2023/5/17 0:30
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {
    List<UrlRolesRule> getResourceRolesRules();
}