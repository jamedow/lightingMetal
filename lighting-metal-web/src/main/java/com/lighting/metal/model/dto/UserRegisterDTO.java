package com.lighting.metal.model.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
public class UserRegisterDTO {

    @NotBlank(message = "登录账号不能为空")
    @Size(min = 4, max = 20, message = "登录账号长度需在4-20位之间")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9@#$%^&*]{6,16}$", message = "密码需为6-16位，支持字母、数字、特殊符号(@#$%^&*)")
    private String password;

    @NotBlank(message = "国家编码不能为空")
    private String countryCode;

    @NotBlank(message = "公司名称不能为空")
    private String companyName;

    @NotBlank(message = "联系人不能为空")
    private String contactPerson;

    // 补充：邮箱格式校验（可选字段用@Pattern，null/空串不触发校验）
    @Pattern(regexp = "^$|^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "邮箱格式错误")
    private String email;

    // 补充：手机号格式校验（适配国际号码，结合countryCode后续可细化）
    @Pattern(regexp = "^$|^[0-9\\-+()]{6,20}$", message = "手机号格式错误")
    private String phone;

    private String companyAddress;
    private String taxNumber;
}