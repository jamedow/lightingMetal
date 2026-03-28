package com.lighting.metal.model.vo;

import lombok.Data;

/**
 * 登录成功返回用户信息
 */
@Data
public class UserLoginVO {
    private Long id;
    private String username;
    private String companyName;
    private String contactPerson;
    private String countryCode;
    private String currency;    // 货币
    private String language;    // 语言
    private String token;      // 登录凭证（后面加JWT用）
}