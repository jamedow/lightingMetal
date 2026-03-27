package com.lighting.metal.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("purchase_order")
public class PurchaseOrder {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String poNo;
    private Long inquiryId;
    private String supplierCode;
    private Long userId;
    private Integer status;
    private BigDecimal totalAmount;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}