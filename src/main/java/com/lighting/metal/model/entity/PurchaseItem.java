package com.lighting.metal.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("purchase_item")
public class PurchaseItem {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long poId;
    private Long productId;
    private Long inquiryItemId;

    // 快照字段
    private String productNameCn;
    private String productNameEn;
    private String customsCode;
    private String categoryCode;
    private String materialCode;
    private String supplierName;

    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private LocalDateTime createTime;
}