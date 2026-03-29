<template>
  <div class="login-page">
    <div class="login-box">
      <h2>用户登录</h2>
      <el-form ref="formRef" :model="form" label-width="80px">
        <el-form-item label="邮箱">
          <el-input v-model="form.email" placeholder="请输入您的邮箱地址"/>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" placeholder="请输入密码" type="password"/>
        </el-form-item>
        <el-form-item>
          <el-button class="login-btn" type="primary" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
      <div class="link">还没有账号？<span @click="$router.push('/register')">立即注册</span></div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {ref} from 'vue'
import {useRouter} from 'vue-router'
import {ElMessage} from 'element-plus'

const router = useRouter()
const form = ref({
  email: '',
  password: ''
})

const emailReg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/

const login = () => {
  if (!form.value.email || !emailReg.test(form.value.email)) {
    ElMessage.warning('请输入有效的邮箱地址')
    return
  }
  if (!form.value.password) {
    ElMessage.warning('请输入您的密码')
    return
  }

  ElMessage.success('登录成功')
  router.push('/')
}
</script>

<style scoped>
/* 留出顶部导航栏位置 */
.login-page {
  height: calc(100vh - 60px);
  margin-top: 60px;
  background: #f8f9fa;
  display: flex;
  align-items: center;
  justify-content: center;
}

.login-box {
  width: 420px;
  background: #fff;
  padding: 45px;
  border-radius: 12px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.06);
}

.login-box h2 {
  text-align: center;
  margin-bottom: 35px;
  color: #222;
}

.login-btn {
  width: 100%;
  height: 46px;
  font-size: 16px;
}

.link {
  text-align: center;
  margin-top: 20px;
  color: #666;
  cursor: pointer;
  font-size: 14px;
}

.link span {
  color: #2d8cf0;
  font-weight: 500;
}
</style>