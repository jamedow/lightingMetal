package com.lighting.metal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lighting.metal.model.entity.ProductCategory;
import java.util.List;

public interface ProductCategoryService extends IService<ProductCategory> {
    // 查询一级分类
    List<ProductCategory> getParentCategory();
    // 根据父ID查询子分类
    List<ProductCategory> getChildCategory(Long parentId);
}