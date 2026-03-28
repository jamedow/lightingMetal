package com.lighting.metal.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;

    // 跨境核心字段
    private String countryCode;   // 国家编码
    private String language;      // 语言
    private String currency;      // 货币
    private String timezone;      // 时区

    // B2B企业信息
    private String companyName;   // 公司名
    private String taxNumber;     // 税号
    private String contactPerson; // 联系人
    private String phone;         // 国际电话
    private String email;
    private String companyAddress;// 公司地址

    private Integer role;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}