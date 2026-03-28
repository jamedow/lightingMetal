package com.lighting.metal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductMaterialEnum {
    STAINLESS_STEEL("SS", "不锈钢"),
    CARBON_STEEL("CS", "碳钢"),
    ALUMINUM("AL", "铝合金"),
    COPPER("CU", "黄铜"),
    ZINC_ALLOY("ZN", "锌合金");

    private final String code;
    private final String name;

    // 【通用方法】根据code获取枚举
    public static ProductMaterialEnum getByCode(String code) {
        for (ProductMaterialEnum enums : values()) {
            if (enums.getCode().equals(code)) {
                return enums;
            }
        }
        return null;
    }
}