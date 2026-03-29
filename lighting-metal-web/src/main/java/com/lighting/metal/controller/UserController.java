package com.lighting.metal.controller;

import com.lighting.metal.exception.BusinessException;
import com.lighting.metal.model.dto.UserLoginDTO;
import com.lighting.metal.model.dto.UserRegisterDTO;
import com.lighting.metal.model.entity.User;
import com.lighting.metal.model.vo.Result;
import com.lighting.metal.model.vo.UserLoginVO;
import com.lighting.metal.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

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
    public Result<?> register(@Valid @RequestBody UserRegisterDTO registerDTO) {

        // 2. 业务逻辑：调用service层完成注册（示例伪代码）
         userService.register(registerDTO);

        // 3. 返回响应
        return Result.success("注册提交成功，待审核");
    }


    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody @Valid UserLoginDTO dto, HttpSession session) {
        // 1. 校验账号密码
        User user = userService.login(dto.getUsername(), dto.getPassword());

        // 2. 登录成功 → 把用户信息存入 SESSION（后端控制）
        session.setAttribute("loginUser", user);
        session.setAttribute("userId", user.getId());

        // 3. 自动返回 JSESSIONID Cookie
        return Result.success("登录成功");
    }

    @GetMapping("/logout")
    public Result logout(HttpSession session) {
        session.invalidate(); // 销毁session
        return Result.success("退出成功");
    }
}