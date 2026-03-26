package com.lighting.metal.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lighting.metal.model.entity.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品Mapper
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}