<template>
  <div class="login-page">
    <div class="login-box">
      <h2>用户登录</h2>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="登录账号" prop="username">
          <el-input v-model="form.username" placeholder="请输入登录账号"/>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" type="password"/>
        </el-form-item>
        <el-form-item>
          <el-button :loading="loading" class="submit-btn" type="primary" @click="login">立即登录</el-button>
        </el-form-item>
      </el-form>
      <div class="link-text">没有账号？<span @click="$router.push('/register')">立即注册</span></div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {reactive, ref} from 'vue'
import {useRouter} from 'vue-router'
import {ElMessage} from 'element-plus'
import {userLogin} from '@/api/user'
import {setCookie} from '@/utils/cookie'

const router = useRouter()
const loading = ref(false)
const formRef = ref()

const form = reactive({
  username: '',
  password: ''
})

const rules = {
  username: [{required: true, message: '登录账号不能为空', trigger: 'blur'}],
  password: [{required: true, message: '密码不能为空', trigger: 'blur'}]
}

// 登录方法（修复语法错误 + 导航栏刷新）
const login = async () => {
  await formRef.value?.validate()
  try {
    loading.value = true
    await userLogin(form)

    // 保存用户名到Cookie
    setCookie('username', form.username, 7)
    ElMessage.success('登录成功')

    // 触发导航栏刷新状态
    window.dispatchEvent(new Event('loginSuccess'))
    router.push('/')
  } catch (err) {
    // 修复：删除 : any 类型标注，解决语法错误
    const msg = err?.response?.data?.msg || '登录失败'
    ElMessage.error(msg)
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.login-page {
  min-height: calc(100vh - 60px);
  margin-top: 60px;
  background: #f8f9fa;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
}
.login-box {
  width: 580px;
  background: #fff;
  padding: 50px 40px;
  border-radius: 12px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.06);
}
.login-box h2 {
  text-align: center;
  margin-bottom: 30px;
}

.submit-btn {
  width: 100%;
  height: 46px;
  font-size: 16px;
}

.link-text {
  text-align: center;
  margin-top: 20px;
  cursor: pointer;
  color: #666;
}

.link-text span {
  color: #2d8cf0;
}
</style>