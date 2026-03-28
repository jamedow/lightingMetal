import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import MainLayout from '@/layouts/MainLayout.vue'
import InquiryList from '@/views/inquiry/InquiryList.vue'

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        redirect: '/inquiry/list'
    },
    {
        path: '/inquiry',
        component: MainLayout,
        children: [
            {
                path: 'list',
                name: 'InquiryList',
                component: InquiryList,
                meta: { title: '询价单列表' }
            },
            {
                path: 'form/:id?', // 新增（无id）/编辑（有id）
                name: 'InquiryForm',
                component: () => import('@/views/inquiry/InquiryForm.vue'),
                meta: { title: '询价单编辑' }
            }
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router