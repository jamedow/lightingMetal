import axios from 'axios'

// 创建Axios实例
const service = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL || '/api', // 代理前缀
    timeout: 5000
})

// 请求拦截器（添加token等）
service.interceptors.request.use(
    (config) => {
        // 示例：添加token
        // const token = localStorage.getItem('token')
        // if (token) config.headers.Authorization = `Bearer ${token}`
        return config
    },
    (error) => Promise.reject(error)
)

// 响应拦截器（统一处理错误）
service.interceptors.response.use(
    (response) => {
        const res = response.data
        // 假设后端返回code=200为成功
        if (res.code !== 200) {
            ElMessage.error(res.msg || '请求失败')
            return Promise.reject(res)
        }
        return res
    },
    (error) => {
        ElMessage.error(error.message || '服务器错误')
        return Promise.reject(error)
    }
)

export default service