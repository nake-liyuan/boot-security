package com.ly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.entity.User;
import com.ly.security.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 
 * @author: LiYuan
 * @time: 2023/5/17 0:30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    UserDto loadUserByUsername(String userName);
}