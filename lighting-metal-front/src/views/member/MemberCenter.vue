<template>
  <div class="member-center" style="padding: 40px 10%; min-height: 500px; background: #fff">
    <h2 class="title">会员中心</h2>
    <div class="card">
      <h3>欢迎你，{{ userStore.userInfo.username }}！</h3>
      <el-divider />
      <el-button type="primary" @click="toInquiry">我的询价单</el-button>
      <el-button type="success" @click="toCart">我的购物车</el-button>
      <el-button type="danger" @click="logout">退出登录</el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useUserStore } from '@/stores/user'
import { useRouter } from 'vue-router'

const userStore = useUserStore()
const router = useRouter()

// 退出
const logout = () => {
  userStore.logout()
  router.push('/')
}

// 我的询价单
const toInquiry = () => {
  router.push('/admin/inquiry/list')
}

// 我的购物车
const toCart = () => {
  ElMessage.success('跳转到购物车')
}
</script>

<style scoped>
.title {
  font-size: 24px;
  margin-bottom: 20px;
}
.card {
  padding: 30px;
  border: 1px solid #eee;
  border-radius: 10px;
  background: #fafafa;
}
</style>