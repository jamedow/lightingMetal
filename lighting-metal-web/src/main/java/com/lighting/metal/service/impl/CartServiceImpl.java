package com.lighting.metal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lighting.metal.mapper.CartMapper;
import com.lighting.metal.model.entity.Cart;
import com.lighting.metal.service.CartService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addCart(Cart cart) {
        // 去重：用户+商品已存在则累加数量
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getUserId, cart.getUserId())
                .eq(Cart::getProductId, cart.getProductId());
        Cart exist = getOne(wrapper);

        if (exist != null) {
            exist.setQuantity(exist.getQuantity() + cart.getQuantity());
            return updateById(exist);
        }
        return save(cart);
    }

    @Override
    public boolean updateQuantity(Long id, Integer quantity) {
        if (quantity < 1) return false;
        Cart cart = getById(id);
        if (cart == null) return false;
        cart.setQuantity(quantity);
        return updateById(cart);
    }

    @Override
    public boolean deleteCart(Long id) {
        return removeById(id);
    }

    @Override
    public List<Cart> getCartList(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public void clearCartByUser(Long userId) {
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getUserId, userId);
        remove(wrapper);
    }
}