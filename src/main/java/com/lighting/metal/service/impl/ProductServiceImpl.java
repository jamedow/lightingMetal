package com.lighting.metal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lighting.metal.exception.BusinessException;
import com.lighting.metal.mapper.ProductMapper;
import com.lighting.metal.model.dto.ProductAddDTO;
import com.lighting.metal.model.dto.ProductQueryDTO;
import com.lighting.metal.model.dto.ProductUpdateDTO;
import com.lighting.metal.model.entity.Product;
import com.lighting.metal.service.ProductService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    private static final String PRODUCT_PREFIX = "HW";

    // 新增商品
    @Override
    public boolean addProduct(ProductAddDTO dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        product.setProductNo(generateProductNo());
        product.setStatus(1);
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        return save(product);
    }

    // 修改商品
    @Override
    public boolean updateProduct(ProductUpdateDTO dto) {
        Product product = getById(dto.getId());
        if (product == null) {
            throw new BusinessException("商品不存在");
        }
        BeanUtils.copyProperties(dto, product);
        product.setUpdateTime(LocalDateTime.now());
        return updateById(product);
    }

    // 分页查询
    @Override
    public IPage<Product> pageList(ProductQueryDTO dto) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(dto.getProductNameCn()), Product::getProductNameCn, dto.getProductNameCn());
        wrapper.eq(dto.getCategoryId() != null, Product::getCategoryId, dto.getCategoryId());
        wrapper.eq(dto.getStatus() != null, Product::getStatus, dto.getStatus());
        wrapper.orderByDesc(Product::getCreateTime);

        Page<Product> page = new Page<>(dto.getCurrent(), dto.getSize());
        return page(page, wrapper);
    }

    // 生成商品编号
    private String generateProductNo() {
        long timestamp = System.currentTimeMillis();
        int random = RandomUtils.nextInt(1, 999);
        String randomStr = StringUtils.leftPad(String.valueOf(random), 3, "0");
        return PRODUCT_PREFIX + timestamp + randomStr;
    }
}