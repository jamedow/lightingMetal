package com.lighting.metal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductCategoryEnum {
    FASTENER("01", "紧固件"),
    HARDWARE_PART("02", "五金配件"),
    TOOL("03", "五金工具"),
    PIPE("04", "金属管材"),
    STAMPING("05", "冲压件"),
    CASTING("06", "铸造件");

    private final String code;
    private final String name;

    // 【通用方法】根据code获取枚举，校验参数用
    public static ProductCategoryEnum getByCode(String code) {
        for (ProductCategoryEnum enums : values()) {
            if (enums.getCode().equals(code)) {
                return enums;
            }
        }
        return null;
    }
}