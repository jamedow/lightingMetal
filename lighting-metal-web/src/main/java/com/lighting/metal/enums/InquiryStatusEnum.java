package com.lighting.metal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 询价单状态枚举
 */
@Getter
@AllArgsConstructor
public enum InquiryStatusEnum {
    PENDING(1, "待处理"),
    PROCESSING(2, "处理中"),
    SPLITTED(3, "已拆分采购单"),
    CLOSED(4, "已关闭");

    private final Integer code;
    private final String desc;
}