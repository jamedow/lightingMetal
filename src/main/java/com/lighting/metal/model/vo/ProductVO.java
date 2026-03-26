package com.lighting.metal.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品返回对象（适配跨境前端展示）
 */
@Data
public class ProductVO {
    /**
     * 商品ID
     */
    private Long id;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 商品名称（根据语言返回，这里先返回双语言）
     */
    private String productNameCn;
    private String productNameEn;

    /**
     * 规格
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
     * 状态：0-下架 1-上架
     */
    private Integer status;
}