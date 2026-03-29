<template>
  <div class="nav-bar">
    <div class="nav-logo">Lighting Metal</div>
    <div class="nav-menu">
      <span class="active">首页</span>
      <span>公司介绍</span>
      <span>商品分类</span>
      <span>合作商户</span>
      <span>生产实力</span>
      <span>联系我们</span>
    </div>
    <div class="nav-auth">
      <el-button link @click="goLogin">登录</el-button>
      <el-button type="primary" @click="goRegister">注册</el-button>
    </div>
  </div>
</template>
<script setup lang="ts">
import { useHomeData } from '@/composables/useHomeData'
const { goLogin, goRegister } = useHomeData()
</script>
<style scoped>
.nav-bar {
  display: flex; justify-content: space-between; align-items: center;
  padding: 0 10%; height: 70px; background: #fff;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1); position: sticky; top: 0; z-index: 999;
}
.nav-logo { font-size: 24px; font-weight: bold; color: #2d8cf0; }
.nav-menu span { margin-left: 30px; cursor: pointer; }
.nav-menu .active { color: #2d8cf0; font-weight: bold; }
.nav-auth :deep(.el-button) { margin-left: 10px; }
</style>