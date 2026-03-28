package com.lighting.metal.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品新增入参
 */
@Data
public class ProductAddDTO {
    /**
     * 商品名称（中文）
     */
    @NotBlank(message = "商品中文名称不能为空")
    private String productNameCn;

    /**
     * 商品名称（英文）
     */
    @NotBlank(message = "商品英文名称不能为空")
    private String productNameEn;

    /**
     * 分类ID
     */
    @NotNull(message = "商品分类不能为空")
    private Long categoryId;

    /**
     * 规格
     */
    private String spec;

    /**
     * 价格（美元）
     */
    @NotNull(message = "商品价格不能为空")
    private BigDecimal price;

    /**
     * 库存
     */
    @NotNull(message = "商品库存不能为空")
    private Integer stock;

    @NotBlank(message = "品类编码不能为空")
    private String categoryCode;  // 2位品类码：01=紧固件

    @NotBlank(message = "材质编码不能为空")
    private String materialCode;  // 2位材质码：SS=不锈钢

    /**
     * 海关编码
     */
    @NotBlank(message = "海关编码不能为空")
    private String customsCode;

    /**
     * 申报价值
     */
    @NotNull(message = "申报价值不能为空")
    private BigDecimal declareValue;

    /**
     * 中文描述
     */
    private String descriptionCn;

    /**
     * 英文描述
     */
    private String descriptionEn;

    @NotBlank(message = "厂商编码不能为空")
    private String supplierCode;  // 3位厂商码
}