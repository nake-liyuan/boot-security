package com.ly.controller;

import com.ly.common.api.CommonResult;
import com.ly.common.constant.AuthConstant;
import com.ly.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: LiYuan
 * @time: 2023/5/17 4:31
 */
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/token")
    public CommonResult<Map<String, String>> login(@RequestParam String userName,
                                                   @RequestParam String password) {
        String token = authService.login(userName, password);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", AuthConstant.JWT_TOKEN_PREFIX);
        return CommonResult.success(tokenMap);
    }

}
