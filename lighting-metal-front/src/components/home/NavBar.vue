<template>
  <div class="nav-bar">
    <div class="nav-logo">Lighting Metal</div>

    <div class="nav-auth">
      <!-- 已登录：显示 用户名 + 用户中心 + 退出 -->
      <template v-if="isLogin">
        <el-button link @click="goUserCenter">用户中心</el-button>
        <span class="user-name">{{ username }}</span>
        <el-button link type="danger" @click="logout">退出</el-button>
      </template>
      <!-- 未登录：显示 登录/注册 -->
      <template v-else>
        <el-button link @click="goLogin">登录</el-button>
        <el-button type="primary" @click="goRegister">注册</el-button>
      </template>
    </div>
  </div>
</template>

<script setup lang="ts">
import {onMounted, onUnmounted, ref} from 'vue'
import {useRouter} from 'vue-router'
import {ElMessage} from 'element-plus'
import {useHomeData} from '@/composables/useHomeData'
// 引入我们的Cookie工具
import {getCookie, removeCookie} from '@/utils/cookie'
// 引入退出接口
import {userLogout} from '@/api/user'

const {goLogin, goRegister} = useHomeData()
const router = useRouter()

// 登录状态 + 用户名
const isLogin = ref(false)
const username = ref('')

// 检查登录状态（核心：实时读取Cookie）
const checkLoginStatus = () => {
  const name = getCookie('username')
  isLogin.value = !!name
  username.value = name || ''
}

// 监听登录成功事件 → 登录后自动刷新导航
onMounted(() => {
  checkLoginStatus()
  // 监听登录成功事件
  window.addEventListener('loginSuccess', checkLoginStatus)
})

// 销毁监听
onUnmounted(() => {
  window.removeEventListener('loginSuccess', checkLoginStatus)
})

// 前往用户中心
const goUserCenter = () => {
  router.push('/user/center')
}

// 退出登录
const logout = async () => {
  try {
    await userLogout()
    // 清除Cookie
    removeCookie('username')
    // 更新状态
    checkLoginStatus()
    ElMessage.success('退出登录成功')
    router.push('/')
  } catch (error) {
    ElMessage.error('退出失败')
  }
}
</script>

<style scoped>
.nav-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 10%;
  height: 70px;
  background: #fff;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  position: sticky;
  top: 0;
  z-index: 999;
}

.nav-logo {
  font-size: 24px;
  font-weight: bold;
  color: #2d8cf0;
}

.nav-auth :deep(.el-button) {
  margin-left: 10px;
}

/* 用户名样式 */
.user-name {
  margin-left: 10px;
  color: #333;
  font-size: 14px;
}
</style>