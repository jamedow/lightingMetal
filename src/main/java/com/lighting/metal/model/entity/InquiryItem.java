package com.lighting.metal.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("inquiry_item")
public class InquiryItem {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long inquiryId;
    private Long productId;
    private String supplierCode;

    // 商品快照
    private String productNameCn;
    private String productNameEn;
    private String customsCode;
    private String categoryCode;
    private String materialCode;

    // 厂商快照
    private String supplierName;

    private Integer quantity;
    private BigDecimal expectUnitPrice;
    private String remark;
    private LocalDateTime createTime;
}