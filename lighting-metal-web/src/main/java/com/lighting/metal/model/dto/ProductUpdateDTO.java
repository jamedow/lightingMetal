package com.lighting.metal.model.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductUpdateDTO {
    @NotNull(message = "商品ID不能为空")
    private Long id;

    private String productNameCn;
    private String productNameEn;
    private String customsCode;
    private BigDecimal price;
    private BigDecimal declareValue;
    private Integer stock;
    private Long categoryId;
    private Integer status;
    private String remark;
    private String supplierCode;  // 3位厂商码
}