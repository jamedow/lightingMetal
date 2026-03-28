package com.lighting.metal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 国际通用货币枚举
 * 遵循 ISO 4217 标准（跨境贸易、银行、海关通用标准）
 *
 * 用途：
 * 1. 海外采购商登录后，自动显示对应国家货币价格
 * 2. 订单、报价、购物车展示对应货币符号
 * 3. 跨境结算、报关、财务统计使用
 */
@Getter
@AllArgsConstructor
public enum CurrencyEnum {

    /**
     * 美元（美金）
     * 全球通用结算货币，外贸最常用
     */
    USD("USD", "US Dollar", "美元"),

    /**
     * 欧元
     * 欧盟国家通用货币：德国、法国、意大利、西班牙等
     */
    EUR("EUR", "Euro", "欧元"),

    /**
     * 英镑
     * 英国通用货币
     */
    GBP("GBP", "British Pound", "英镑"),

    /**
     * 加元
     * 加拿大通用货币
     */
    CAD("CAD", "Canadian Dollar", "加元");

    // ========================= 字段说明 =========================
    /**
     * 货币编码（ISO 4217 3位大写字母，系统唯一标识）
     * 例：USD / EUR / GBP / CAD
     */
    private final String code;

    /**
     * 英文名称（用于国际版页面展示、海外客户显示）
     */
    private final String enName;

    /**
     * 中文名称（用于后台管理、中文页面展示）
     */
    private final String cnName;

    // 根据货币编码获取枚举（校验前端参数用）
    public static CurrencyEnum getByCode(String code) {
        for (CurrencyEnum currency : values()) {
            if (currency.getCode().equals(code)) {
                return currency;
            }
        }
        return null;
    }
}