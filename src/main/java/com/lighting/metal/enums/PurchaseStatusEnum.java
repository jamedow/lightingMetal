package com.lighting.metal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 采购单状态枚举
 */
@Getter
@AllArgsConstructor
public enum PurchaseStatusEnum {
    PENDING(1, "待确认"),
    CONFIRMED(2, "已确认"),
    PRODUCING(3, "生产中"),
    DELIVERING(4, "已发货"),
    FINISHED(5, "已完成");

    private final Integer code;
    private final String desc;
}