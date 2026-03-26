package com.lighting.metal.service.impl;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lighting.metal.exception.BusinessException;
import com.lighting.metal.mapper.ProductMapper;
import com.lighting.metal.model.dto.ProductAddDTO;
import com.lighting.metal.model.entity.Product;
import com.lighting.metal.model.vo.ProductVO;
import com.lighting.metal.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品业务实现
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    /**
     * 新增跨境五金商品
     */
    @Override
    public boolean addProduct(ProductAddDTO dto) {
        // 1. 生成商品编号（HW + 雪花ID）
        String productNo = "HW" + IdUtil.getSnowflakeNextIdStr();

        // 2. 转换DTO到实体
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        product.setProductNo(productNo);
        product.setStatus(1); // 默认上架

        // 3. 保存商品
        return save(product);
    }

    /**
     * 查询上架商品列表
     */
    @Override
    public List<ProductVO> listOnSaleProducts() {
        // 查询上架商品
        List<Product> productList = lambdaQuery()
                .eq(Product::getStatus, 1)
                .list();

        // 转换为VO
        return productList.stream().map(product -> {
            ProductVO vo = new ProductVO();
            BeanUtils.copyProperties(product, vo);
            return vo;
        }).collect(Collectors.toList());
    }

    /**
     * 根据ID查询商品
     */
    @Override
    public ProductVO getProductById(Long id) {
        Product product = getById(id);
        if (product == null) {
            throw new BusinessException("商品不存在");
        }
        ProductVO vo = new ProductVO();
        BeanUtils.copyProperties(product, vo);
        return vo;
    }
}