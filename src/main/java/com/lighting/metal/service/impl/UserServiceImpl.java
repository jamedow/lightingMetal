package com.lighting.metal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lighting.metal.enums.CurrencyEnum;
import com.lighting.metal.exception.BusinessException;
import com.lighting.metal.mapper.UserMapper;
import com.lighting.metal.model.dto.UserRegisterDTO;
import com.lighting.metal.model.entity.User;
import com.lighting.metal.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 注册逻辑
     */
    @Override
    public boolean register(UserRegisterDTO dto) {
        // 1. 检查账号是否存在
        if (getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, dto.getUsername())) != null) {
            throw new BusinessException("账号已被注册");
        }

        // 2. 构造用户
        User user = new User();
        BeanUtils.copyProperties(dto, user);

        // 3. 密码加密
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // 4. 默认设置：英文 + 美元（跨境默认）
        user.setLanguage("en");
        user.setCurrency(CurrencyEnum.USD.getCode());

        // 5. 默认角色：海外采购商
        user.setRole(1);
        user.setStatus(1);

        return save(user);
    }

    /**
     * 登录逻辑
     */
    @Override
    public User login(String username, String password) {
        // 1. 查询用户
        User user = getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (user == null) {
            throw new BusinessException("账号或密码错误");
        }

        // 2. 校验密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BusinessException("账号或密码错误");
        }

        // 3. 校验状态
        if (user.getStatus() == 0) {
            throw new BusinessException("账号已被禁用");
        }

        return user;
    }
}