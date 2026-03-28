package com.lighting.metal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lighting.metal.mapper.ProductCategoryMapper;
import com.lighting.metal.model.entity.ProductCategory;
import com.lighting.metal.service.ProductCategoryService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    // 查询一级分类
    @Override
    public List<ProductCategory> getParentCategory() {
        LambdaQueryWrapper<ProductCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductCategory::getParentId, 0)
                .eq(ProductCategory::getStatus, 1)
                .orderByAsc(ProductCategory::getSort);
        return list(wrapper);
    }

    // 查询子分类
    @Override
    public List<ProductCategory> getChildCategory(Long parentId) {
        LambdaQueryWrapper<ProductCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductCategory::getParentId, parentId)
                .eq(ProductCategory::getStatus, 1)
                .orderByAsc(ProductCategory::getSort);
        return list(wrapper);
    }
}