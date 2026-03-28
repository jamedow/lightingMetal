package com.lighting.metal.model.dto;

import com.lighting.metal.model.dto.base.BasePageDTO;
import lombok.Data;

/**
 * 商品查询条件 (仅业务条件，分页继承公共类)
 */
@Data
public class ProductQueryDTO extends BasePageDTO {
    // 商品中文名称（模糊查询）
    private String productNameCn;
    // 分类ID
    private Long categoryId;
    // 状态 1上架 0下架
    private Integer status;
}