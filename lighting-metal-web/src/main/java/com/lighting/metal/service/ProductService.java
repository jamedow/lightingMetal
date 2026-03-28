package com.lighting.metal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lighting.metal.model.dto.ProductAddDTO;
import com.lighting.metal.model.dto.ProductQueryDTO;
import com.lighting.metal.model.dto.ProductUpdateDTO;
import com.lighting.metal.model.entity.Product;

public interface ProductService extends IService<Product> {
    boolean addProduct(ProductAddDTO dto);
    boolean updateProduct(ProductUpdateDTO dto);
    IPage<Product> pageList(ProductQueryDTO dto);
}