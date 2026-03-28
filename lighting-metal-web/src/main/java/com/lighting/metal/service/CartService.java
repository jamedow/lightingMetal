package com.lighting.metal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lighting.metal.model.entity.Cart;
import java.util.List;

public interface CartService extends IService<Cart> {
    // 添加购物车
    boolean addCart(Cart cart);
    // 修改数量
    boolean updateQuantity(Long id, Integer quantity);
    // 删除商品
    boolean deleteCart(Long id);
    // 查询我的购物车
    List<Cart> getCartList(Long userId);
    // 清空购物车
    void clearCartByUser(Long userId);
}