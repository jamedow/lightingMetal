package com.lighting.metal.controller;

import com.lighting.metal.model.dto.ProductAddDTO;
import com.lighting.metal.model.vo.ProductVO;
import com.lighting.metal.model.vo.Result;
import com.lighting.metal.service.ProductService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品接口（跨境五金）
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    /**
     * 新增商品
     */
    @PostMapping("/add")
    public Result<Boolean> addProduct(@Valid @RequestBody ProductAddDTO dto) {
        boolean success = productService.addProduct(dto);
        return success ? Result.success(true) : Result.fail("新增商品失败");
    }

    /**
     * 查询上架商品列表
     */
    @GetMapping("/list")
    public Result<List<ProductVO>> listOnSaleProducts() {
        List<ProductVO> list = productService.listOnSaleProducts();
        return Result.success(list);
    }

    /**
     * 根据ID查询商品
     */
    @GetMapping("/{id}")
    public Result<ProductVO> getProductById(@PathVariable Long id) {
        ProductVO vo = productService.getProductById(id);
        return Result.success(vo);
    }
}