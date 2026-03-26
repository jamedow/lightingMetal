package com.lighting.metal.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lighting.metal.model.dto.ProductAddDTO;
import com.lighting.metal.model.dto.ProductQueryDTO;
import com.lighting.metal.model.dto.ProductUpdateDTO;
import com.lighting.metal.model.entity.Product;
import com.lighting.metal.model.vo.Result;
import com.lighting.metal.service.ProductService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 新增
    @PostMapping("/add")
    public Result<Boolean> addProduct(@Valid @RequestBody ProductAddDTO dto) {
        return Result.success(productService.addProduct(dto));
    }

    // 列表
    @GetMapping("/list")
    public Result<List<Product>> list() {
        return Result.success(productService.list());
    }

    // ✅ 通用分页查询（类名改 ProductQueryDTO）
    @PostMapping("/page")
    public Result<IPage<Product>> page(@RequestBody ProductQueryDTO dto) {
        return Result.success(productService.pageProduct(dto));
    }

    // 修改
    @PostMapping("/update")
    public Result<Boolean> updateProduct(@Valid @RequestBody ProductUpdateDTO dto) {
        return Result.success(productService.updateProduct(dto));
    }

    // 删除
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> deleteProduct(@PathVariable Long id) {
        return Result.success(productService.removeById(id));
    }

    // 详情
    @GetMapping("/get/{id}")
    public Result<Product> getById(@PathVariable Long id) {
        return Result.success(productService.getById(id));
    }
}