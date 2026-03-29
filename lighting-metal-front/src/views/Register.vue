<template>
  <div class="register-page">
    <div class="register-box">
      <h2>用户注册</h2>
      <el-form ref="formRef" :model="form" :rules="rules" class="register-form" label-width="100px">
        <!-- 必填表单项 -->
        <el-form-item label="登录账号" prop="username">
          <el-input v-model="form.username" placeholder="请输入登录账号"/>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" type="password"/>
        </el-form-item>

        <!-- ✅ 新增：确认密码（前端校验，不提交后端） -->
        <el-form-item label="确认密码" prop="confirmPwd">
          <el-input v-model="form.confirmPwd" placeholder="请再次输入密码" type="password"/>
        </el-form-item>

        <el-form-item label="国家编码" prop="countryCode">
          <el-input v-model="form.countryCode" placeholder="如：CN / US / GB"/>
        </el-form-item>

        <el-form-item label="公司名称" prop="companyName">
          <el-input v-model="form.companyName" placeholder="请输入公司名称"/>
        </el-form-item>

        <el-form-item label="联系人" prop="contactPerson">
          <el-input v-model="form.contactPerson" placeholder="请输入联系人"/>
        </el-form-item>

        <!-- 选填信息 -->
        <el-divider>选填信息</el-divider>

        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱"/>
        </el-form-item>

        <el-form-item label="手机号码" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号码"/>
        </el-form-item>

        <el-form-item label="公司地址" prop="companyAddress">
          <el-input v-model="form.companyAddress" placeholder="请输入公司地址" rows="2" type="textarea"/>
        </el-form-item>

        <el-form-item label="税号" prop="taxNumber">
          <el-input v-model="form.taxNumber" placeholder="请输入税号"/>
        </el-form-item>

        <!-- 注册按钮 -->
        <el-form-item>
          <el-button :loading="loading" class="submit-btn" type="primary" @click="register">
            立即注册
          </el-button>
        </el-form-item>
      </el-form>

      <div class="link-text">已有账号？<span @click="$router.push('/login')">去登录</span></div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {reactive, ref} from 'vue'
import {useRouter} from 'vue-router'
import {ElMessage} from 'element-plus'
import {userRegister} from '@/api/user'

const router = useRouter()
const loading = ref(false)
const formRef = ref()

// 1:1 匹配后端DTO + 前端确认密码字段
const form = reactive({
  username: '',
  password: '',
  confirmPwd: '', // 👈 仅前端校验，不提交后端
  countryCode: '',
  companyName: '',
  contactPerson: '',
  email: '',
  phone: '',
  companyAddress: '',
  taxNumber: ''
})

// 校验规则（完全对齐后端 + 前端确认密码）
const rules = {
  username: [{required: true, message: '登录账号不能为空', trigger: 'blur'}],
  password: [{required: true, message: '密码不能为空', trigger: 'blur'}],
  confirmPwd: [
    {required: true, message: '确认密码不能为空', trigger: 'blur'},
    {
      validator: (rule, value, callback) => {
        value === form.password ? callback() : callback(new Error('两次输入密码不一致'))
      },
      trigger: 'blur'
    }
  ],
  countryCode: [{required: true, message: '国家编码不能为空', trigger: 'blur'}],
  companyName: [{required: true, message: '公司名称不能为空', trigger: 'blur'}],
  contactPerson: [{required: true, message: '联系人不能为空', trigger: 'blur'}]
}

// 注册提交
const register = async () => {
  // 前端表单校验
  const valid = await formRef.value?.validate()
  if (!valid) return

  try {
    loading.value = true
    // 👈 关键：提交时排除 confirmPwd，只传后端需要的字段
    const submitData = {...form}
    delete submitData.confirmPwd

    // 调用接口
    await userRegister(submitData)

    // 成功提示
    ElMessage.success('注册成功！即将跳转到登录页')
    setTimeout(() => router.push('/login'), 1000)
  } catch (err: any) {
    // 👈 关键：完整显示后端错误提示（用户能看到具体问题）
    const msg = err?.response?.data?.msg || err?.msg || '注册失败，请检查填写信息'
    ElMessage.error(msg) // 弹出后端返回的真实错误
    console.error('后端报错：', err) // 控制台日志（开发用）
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
/* 统一页面样式，无错乱、适配导航栏 */
.register-page {
  min-height: calc(100vh - 60px);
  margin-top: 60px;
  background: #f8f9fa;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
}
.register-box {
  width: 580px;
  background: #fff;
  padding: 50px 40px;
  border-radius: 12px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.06);
}
.register-box h2 {
  text-align: center;
  margin-bottom: 30px;
  color: #333;
  font-weight: 600;
}

.register-form {
  width: 100%;
}

.submit-btn {
  width: 100%;
  height: 46px;
  font-size: 16px;
  margin-top: 10px;
}

.link-text {
  text-align: center;
  margin-top: 25px;
  color: #666;
  font-size: 14px;
  cursor: pointer;
}

.link-text span {
  color: #2d8cf0;
  font-weight: 500;
}
</style>