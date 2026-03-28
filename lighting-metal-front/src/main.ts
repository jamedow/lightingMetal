import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
// 引入Element Plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 引入路由
import router from './router'
// 引入Pinia
import { createPinia } from 'pinia'
// 全局样式
import '@/assets/styles/global.scss'

const app = createApp(App)
app.use(ElementPlus)
app.use(router)
app.use(createPinia())
app.mount('#app')