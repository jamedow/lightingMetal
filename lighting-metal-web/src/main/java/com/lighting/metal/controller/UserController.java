package com.lighting.metal.controller;

import com.lighting.metal.exception.BusinessException;
import com.lighting.metal.model.dto.UserLoginDTO;
import com.lighting.metal.model.dto.UserRegisterDTO;
import com.lighting.metal.model.entity.User;
import com.lighting.metal.model.vo.Result;
import com.lighting.metal.model.vo.UserLoginVO;
import com.lighting.metal.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 海外采购商注册
     */
    @PostMapping("/register")
    public Result<String> register(@Valid @RequestBody UserRegisterDTO dto) {
        boolean success = userService.register(dto);
        return success ? Result.success("注册成功") : Result.fail("注册失败");
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result<UserLoginVO> login(@Valid @RequestBody UserLoginDTO dto) {
        User user = userService.login(dto.getUsername(), dto.getPassword());

        // 构造返回前端的VO
        UserLoginVO vo = new UserLoginVO();
        BeanUtils.copyProperties(user, vo);
        vo.setToken("login-token-" + System.currentTimeMillis()); // 临时token

        return Result.success(vo);
    }
}