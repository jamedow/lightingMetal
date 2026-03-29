import axios from 'axios'

const request = axios.create({
    baseURL: '/api',
    timeout: 10000,
    // ✅ 关键：允许携带 SESSION Cookie
    withCredentials: true
})

// 响应拦截
request.interceptors.response.use(
    (res) => res.data,
    (err) => {
        // 未登录 → 后端返回401
        if (err.response?.status === 401) {
            ElMessage.error('请先登录')
            router.push('/login')
        }
        return Promise.reject(err)
    }
)

export default request