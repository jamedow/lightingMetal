package com.lighting.metal.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("inquiry")
public class Inquiry {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String inquiryNo;
    private Long userId;
    private String countryCode;
    private Integer status;
    private Integer totalQuantity;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}