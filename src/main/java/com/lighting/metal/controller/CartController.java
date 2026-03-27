package com.lighting.metal.controller;

import com.lighting.metal.model.entity.Cart;
import com.lighting.metal.model.vo.Result;
import com.lighting.metal.service.CartService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Resource
    private CartService cartService;

    /**
     * 添加商品到购物车
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Cart cart) {
        return Result.success(cartService.addCart(cart));
    }

    /**
     * 修改购物车商品数量
     */
    @PutMapping("/update/{id}")
    public Result<Boolean> update(
            @PathVariable Long id,
            @RequestParam @NotNull Integer quantity) {
        return Result.success(cartService.updateQuantity(id, quantity));
    }

    /**
     * 删除购物车商品
     */
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(cartService.deleteCart(id));
    }

    /**
     * 查询用户购物车列表
     */
    @GetMapping("/list/{userId}")
    public Result<List<Cart>> list(@PathVariable Long userId) {
        return Result.success(cartService.getCartList(userId));
    }
}