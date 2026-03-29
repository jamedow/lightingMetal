import {createRouter, createWebHistory} from 'vue-router'
import HomePage from '@/views/HomePage.vue'
import Login from '@/views/Login.vue'
import Register from '@/views/Register.vue'
import UserCenter from '@/views/UserCenter.vue'

const routes = [
    {path: '/', name: 'Home', component: HomePage},
    {path: '/login', name: 'Login', component: Login},
    {path: '/register', name: 'Register', component: Register},
    {path: '/user/center', name: 'UserCenter', component: UserCenter}
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router