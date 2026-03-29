// src/api/user.ts
import request from '@/utils/request'

// 登录接口
export function userLogin(data) {
    return request({
        url: '/user/login',
        method: 'post',
        data
    })
}

// 注册接口
export function userRegister(data) {
    return request({
        url: '/user/register',
        method: 'post',
        data
    })
}

// 退出登录接口
export function userLogout() {
    return request({
        url: '/user/logout',
        method: 'get'
    })
}