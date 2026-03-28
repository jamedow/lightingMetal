package com.lighting.metal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lighting.metal.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}