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

    private static final String HW_PREFIX = "HW";

    @Override
    public boolean addProduct(ProductAddDTO dto) {
        if (StringUtils.isBlank(dto.getProductNameCn())) {
            throw new BusinessException("商品中文名称不能为空");
        }
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        product.setProductNo(generateProductNo());
        product.setStatus(1);
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        return save(product);
    }

    //  通用分页实现
    @Override
    public IPage<Product> pageProduct(ProductQueryDTO dto) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        // 商品业务条件
        wrapper.like(StringUtils.isNotBlank(dto.getProductNameCn()), Product::getProductNameCn, dto.getProductNameCn());
        wrapper.eq(dto.getCategoryId() != null, Product::getCategoryId, dto.getCategoryId());
        wrapper.eq(dto.getStatus() != null, Product::getStatus, dto.getStatus());
        wrapper.orderByDesc(Product::getCreateTime);

        // 分页参数从 通用BasePageDTO 中获取
        Page<Product> page = new Page<>(dto.getCurrent(), dto.getSize());
        return page(page, wrapper);
    }

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

    private String generateProductNo() {
        long timestamp = System.currentTimeMillis();
        int randomNum = RandomUtils.nextInt(1, 999);
        String randomStr = StringUtils.leftPad(String.valueOf(randomNum), 3, "0");
        return HW_PREFIX + timestamp + randomStr;
    }
}