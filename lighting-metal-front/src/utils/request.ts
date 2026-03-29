import axios from 'axios'
import {ElMessage} from 'element-plus'

const request = axios.create({
    baseURL: '/api', // 固定写 /api，交给代理转发
    timeout: 10000
})

// 响应拦截
request.interceptors.response.use(
    (res) => res.data,
    (err) => {
        ElMessage.error('请求失败：' + (err.message || '网络异常'))
        return Promise.reject(err)
    }
)

export default request