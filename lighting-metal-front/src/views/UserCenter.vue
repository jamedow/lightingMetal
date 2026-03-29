<template>
  <div class="center-page">
    <div class="center-box">
      <h2>个人中心</h2>
      <div class="info-item">
        <label>登录账号：</label>
        <span>{{ username }}</span>
      </div>
      <el-button type="primary" @click="$router.push('/')">返回首页</el-button>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import {getCookie} from '@/utils/cookie'

const username = ref('')
onMounted(() => {
  username.value = getCookie('username')
})
</script>

<style scoped>
.center-page {
  min-height: calc(100vh - 60px);
  margin-top: 60px;
  background: #f8f9fa;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
}

.center-box {
  width: 580px;
  background: #fff;
  padding: 50px 40px;
  border-radius: 12px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.06);
  text-align: center;
}

.info-item {
  margin: 20px 0;
  font-size: 16px;
}
</style>