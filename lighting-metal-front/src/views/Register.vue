<template>
  <div class="register-page">
    <div class="register-box">
      <h2>用户注册</h2>
      <el-form ref="formRef" :model="form" label-width="80px" rules="rules">
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入您的邮箱地址"/>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="设置密码（不少于6位）" type="password"/>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPwd">
          <el-input v-model="form.confirmPwd" placeholder="再次确认密码" type="password"/>
        </el-form-item>
        <el-form-item>
          <el-button :loading="loading" class="register-btn" type="primary" @click="register">
            注册
          </el-button>
        </el-form-item>
      </el-form>
      <div class="link">已有账号？<span @click="$router.push('/login')">立即登录</span></div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {reactive, ref} from 'vue'
import {useRouter} from 'vue-router'
import {ElMessage} from 'element-plus'
// 导入注册接口
import {userRegister} from '@/api/user'

const router = useRouter()
// 加载状态
const loading = ref(false)
// 表单数据
const form = reactive({
  email: '',
  password: '',
  confirmPwd: ''
})

// 表单验证规则
const rules = {
  email: [
    {required: true, message: '请输入邮箱', trigger: 'blur'},
    {type: 'email', message: '请输入有效的邮箱地址', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 6, message: '密码长度不少于6位', trigger: 'blur'}
  ],
  confirmPwd: [
    {required: true, message: '请确认密码', trigger: 'blur'},
    {
      validator: (rule, value, callback) => {
        if (value !== form.password) {
          callback(new Error('两次输入密码不一致'))
        } else {
          callback()
        }
      },
      trigger: 'blur'
    }
  ]
}

// 🔥 核心：对接后端注册接口
const register = async () => {
  // 前端验证
  if (!form.email || !form.password || !form.confirmPwd) {
    ElMessage.warning('请完善注册信息')
    return
  }
  if (form.password !== form.confirmPwd) {
    ElMessage.error('两次密码不一致')
    return
  }

  try {
    loading.value = true
    // 调用后端注册接口
    const res = await userRegister({
      email: form.email,
      password: form.password
    })

    // 注册成功
    ElMessage.success('注册成功！请登录')
    router.push('/login')
  } catch (err) {
    // 注册失败（邮箱已存在/服务器错误）
    console.log(err)
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.register-page {
  height: calc(100vh - 60px);
  margin-top: 60px;
  background: #f8f9fa;
  display: flex;
  align-items: center;
  justify-content: center;
}

.register-box {
  width: 420px;
  background: #fff;
  padding: 45px;
  border-radius: 12px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.06);
}

.register-box h2 {
  text-align: center;
  margin-bottom: 35px;
  color: #222;
}

.register-btn {
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