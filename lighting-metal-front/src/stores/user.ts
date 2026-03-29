import { defineStore } from 'pinia'
import { ref } from 'vue'

// 用户状态管理
export const useUserStore = defineStore('user', () => {
    // 登录状态
    const isLogin = ref(false)
    // 用户信息
    const userInfo = ref({
        username: ''
    })

    // 登录（模拟）
    const login = (username: string) => {
        isLogin.value = true
        userInfo.value.username = username
        // 实际项目可存token到localStorage
        localStorage.setItem('userInfo', JSON.stringify(userInfo.value))
    }

    // 退出登录
    const logout = () => {
        isLogin.value = false
        userInfo.value.username = ''
        localStorage.removeItem('userInfo')
    }

    // 刷新保持登录状态
    const initUser = () => {
        const info = localStorage.getItem('userInfo')
        if (info) {
            userInfo.value = JSON.parse(info)
            isLogin.value = true
        }
    }

    return {
        isLogin,
        userInfo,
        login,
        logout,
        initUser
    }
})