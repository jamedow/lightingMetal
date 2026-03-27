package com.lighting.metal.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("product")
public class Product {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String productNo;       // 商品编号
    private String productNameCn;  // 中文名称
    private String productNameEn;  // 英文名称
    private String customsCode;    // 海关编码
    private String supplierCode;   // 厂商编码（3位）
    private BigDecimal price;      // 价格
    private BigDecimal declareValue;// 申报价值
    private Integer stock;         // 库存
    private Long categoryId;       // 分类ID
    private String categoryCode;   // 品类编码（2位：01=紧固件）
    private String materialCode;   // 材质编码（2位：SS=不锈钢）
    private Integer status;        // 状态
    private String remark;         // 备注
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}