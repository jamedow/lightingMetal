import request from '@/utils/request'

// 注册接口
export function userRegister(data: {
    email: string
    password: string
}) {
    return request({
        url: '/user/register', // 后端注册接口地址
        method: 'POST',
        data
    })
}