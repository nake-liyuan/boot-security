package com.ly.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description: 
 * @author: LiYuan
 * @time: 2023/5/17 0:30
 */
@Data
@TableName(value = "`role`")
public class Role {
    /**
     * 角色ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 后台用户数量
     */
    @TableField(value = "user_count")
    private Integer userCount;

    /**
     * 启用
     */
    @TableField(value = "enabled")
    private Boolean enabled;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;
}