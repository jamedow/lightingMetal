package com.lighting.metal.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("product_category")
public class ProductCategory {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long parentId;       // 父分类ID
    private String categoryName; // 分类名称
    private Integer sort;        // 排序
    private String icon;         // 图标
    private Integer status;      // 状态
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}