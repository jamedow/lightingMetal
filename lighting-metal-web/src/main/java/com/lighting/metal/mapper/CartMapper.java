package com.lighting.metal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lighting.metal.model.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    // 根据用户ID查询购物车
    List<Cart> selectByUserId(Long userId);
}