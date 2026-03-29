import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'

export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, 'src')
    }
  },
  // 🔥 🔥 🔥 绝对生效的代理配置（重点）
  server: {
    port: 5173, // 前端端口不动
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // 后端端口
        changeOrigin: true, // 允许跨域（核心）
        pathRewrite: {'^/api': ''} // 去掉 /api 前缀
      }
    }
  }
})