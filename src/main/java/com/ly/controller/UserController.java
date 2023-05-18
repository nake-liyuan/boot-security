package com.ly.controller;

import com.ly.common.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: LiYuan
 * @time: 2023/5/17 13:58
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {


    @GetMapping("/info")
    public CommonResult<Authentication> info(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return CommonResult.success(authentication);
    }
}
