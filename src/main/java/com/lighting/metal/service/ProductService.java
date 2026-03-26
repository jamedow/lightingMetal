package com.lighting.metal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lighting.metal.model.dto.ProductAddDTO;
import com.lighting.metal.model.entity.Product;
import com.lighting.metal.model.vo.ProductVO;

import java.util.List;

/**
 * 商品业务接口
 */
public interface ProductService extends IService<Product> {
    /**
     * 新增跨境五金商品
     */
    boolean addProduct(ProductAddDTO dto);

    /**
     * 查询上架商品列表（适配跨境展示）
     */
    List<ProductVO> listOnSaleProducts();

    /**
     * 根据ID查询商品（多语言）
     */
    ProductVO getProductById(Long id);
}