package com.ly.security.dto;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: LiYuan
 * @time: 2023/5/17 0:37
 */
@Data
public class UrlRolesRule {

    private String url;

    private List<String> roles;
}

