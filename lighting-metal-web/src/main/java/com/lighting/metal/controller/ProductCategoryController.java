package com.lighting.metal.controller;

import com.lighting.metal.model.entity.ProductCategory;
import com.lighting.metal.model.vo.Result;
import com.lighting.metal.service.ProductCategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class ProductCategoryController {

    private final ProductCategoryService categoryService;

    public ProductCategoryController(ProductCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 1. 查询所有一级分类（首页导航用）
    @GetMapping("/parent")
    public Result<List<ProductCategory>> parentCategory() {
        return Result.success(categoryService.getParentCategory());
    }

    // 2. 根据父ID查询子分类（筛选商品用）
    @GetMapping("/child/{parentId}")
    public Result<List<ProductCategory>> childCategory(@PathVariable Long parentId) {
        return Result.success(categoryService.getChildCategory(parentId));
    }

    // 3. 新增分类
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody ProductCategory category) {
        return Result.success(categoryService.save(category));
    }

    // 4. 修改分类
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody ProductCategory category) {
        return Result.success(categoryService.updateById(category));
    }

    // 5. 删除分类
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(categoryService.removeById(id));
    }
}