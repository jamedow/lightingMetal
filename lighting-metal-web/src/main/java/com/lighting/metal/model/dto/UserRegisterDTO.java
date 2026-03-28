package com.lighting.metal.model.dto;

import lombok.Data;

// 注意：jakarta 包
import jakarta.validation.constraints.NotBlank;

@Data
public class UserRegisterDTO {

    @NotBlank(message = "登录账号不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "国家编码不能为空")
    private String countryCode;

    @NotBlank(message = "公司名称不能为空")
    private String companyName;

    @NotBlank(message = "联系人不能为空")
    private String contactPerson;

    private String email;
    private String phone;
    private String companyAddress;
    private String taxNumber;
}