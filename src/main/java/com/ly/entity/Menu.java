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
@TableName(value = "menu")
public class Menu {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级ID
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 菜单名称
     */
    @TableField(value = "title")
    private String title;

    /**
     * 菜单级数
     */
    @TableField(value = "level")
    private Integer level;

    /**
     * 菜单排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 前端名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 前端图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 前端隐藏
     */
    @TableField(value = "hidden")
    private Boolean hidden;
}