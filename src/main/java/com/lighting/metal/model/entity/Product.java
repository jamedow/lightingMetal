package com.lighting.metal.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品实体（跨境五金）
 */
@Data
@TableName("product")
public class Product {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品编号（HW前缀）
     */
    private String productNo;

    /**
     * 商品名称（中文）
     */
    private String productNameCn;

    /**
     * 商品名称（英文）
     */
    private String productNameEn;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 五金规格
     */
    private String spec;

    /**
     * 基础价格（美元）
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 海关编码
     */
    private String customsCode;

    /**
     * 申报价值
     */
    private BigDecimal declareValue;

    /**
     * 中文描述
     */
    private String descriptionCn;

    /**
     * 英文描述
     */
    private String descriptionEn;

    /**
     * 状态：0-下架 1-上架
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}