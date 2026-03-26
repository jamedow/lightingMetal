package com.lighting.metal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lighting.metal.model.dto.ProductAddDTO;
import com.lighting.metal.model.dto.ProductQueryDTO;
import com.lighting.metal.model.dto.ProductUpdateDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lighting.metal.model.entity.Product;

public interface ProductService extends IService<Product> {
    boolean addProduct(ProductAddDTO dto);
    // 替换为通用查询类
    IPage<Product> pageProduct(ProductQueryDTO dto);
    boolean updateProduct(ProductUpdateDTO dto);
}