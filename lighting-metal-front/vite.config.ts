import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  // 配置路径别名（方便导入）
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src')
    }
  },
  // 配置后端接口代理（避免跨域）
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // 后端服务地址（需和后端端口一致）
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  }
})