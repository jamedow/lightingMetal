import {createRouter, createWebHistory} from 'vue-router'
import MainLayout from '@/layouts/MainLayout.vue'
import InquiryList from '@/views/inquiry/InquiryList.vue'

const routes = [
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