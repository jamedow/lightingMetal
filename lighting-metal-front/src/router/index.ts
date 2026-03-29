import {createRouter, createWebHistory} from 'vue-router'
// 门户首页
import HomePage from '@/views/HomePage.vue'
// 登录、注册（新增）
import Login from '@/views/Login.vue'
import Register from '@/views/Register.vue'
// 后台布局
import MainLayout from '@/layouts/MainLayout.vue'
import InquiryList from '@/views/inquiry/InquiryList.vue'

const routes = [
    // 企业首页
    {
        path: '/',
        name: 'Home',
        component: HomePage
    },
    // 登录
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    },
    // 后台管理
    {
        path: '/admin',
        component: MainLayout,
        children: [
            {
                path: 'inquiry/list',
                component: InquiryList
            }
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router