package com.lighting.metal.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("country")
public class Country {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String code2;      // 核心 2位编码
    private String code3;
    private String numericCode;
    private String cnName;
    private String enName;
    private String currencyCode;
    private String languageCode;
    private String phoneCode;
    private Integer status;
}