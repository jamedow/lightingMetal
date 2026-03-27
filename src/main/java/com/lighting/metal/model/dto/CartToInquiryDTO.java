package com.lighting.metal.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartToInquiryDTO {
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    private String countryCode;
    private String remark;
}