package com.lighting.metal.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class InquiryCreateDTO {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "国家编码不能为空")
    private String countryCode;

    private String remark;

    @NotEmpty(message = "询价商品不能为空")
    private List<ItemDTO> items;

    @Data
    public static class ItemDTO {
        @NotNull(message = "商品ID不能为空")
        private Long productId;
        @NotNull(message = "采购数量不能为空")
        private Integer quantity;
        private BigDecimal expectUnitPrice;
        private String remark;
    }
}