package com.lighting.metal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lighting.metal.model.dto.UserRegisterDTO;
import com.lighting.metal.model.entity.User;

public interface UserService extends IService<User> {
    boolean register(UserRegisterDTO dto);
    User login(String username, String password);
}