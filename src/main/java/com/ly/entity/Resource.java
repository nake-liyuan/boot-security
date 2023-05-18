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
@TableName(value = "`resource`")
public class Resource {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 资源分类ID
     */
    @TableField(value = "category_id")
    private Long categoryId;

    /**
     * 资源名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 资源URL
     */
    @TableField(value = "url")
    private String url;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;
}