<template>
  <div class="navbar">
    <div class="nav-container">
      <!-- 左侧：首页 -->
      <div class="nav-left">
        <div class="nav-home" @click="goHome">首页</div>
      </div>

      <!-- 右侧：登录 + 注册 按钮 -->
      <div class="nav-right">
        <el-button text @click="goLogin">登录</el-button>
        <el-button type="primary" @click="goRegister">注册</el-button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {useRouter} from 'vue-router'

const router = useRouter()

// 返回首页
const goHome = () => {
  router.push('/')
}
// 去登录页
const goLogin = () => {
  router.push('/login')
}
// 去注册页
const goRegister = () => {
  router.push('/register')
}
</script>

<style scoped>
/* 极简全局导航 固定顶部 */
.navbar {
  height: 60px;
  background: #ffffff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.04);
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 999;
}

.nav-container {
  max-width: 1200px;
  height: 100%;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}

/* 首页按钮 */
.nav-home {
  font-size: 16px;
  font-weight: 500;
  color: #2d8cf0;
  cursor: pointer;
}

.nav-home:hover {
  opacity: 0.8;
}

/* 右侧按钮间距 */
.nav-right {
  display: flex;
  gap: 12px;
}
</style>