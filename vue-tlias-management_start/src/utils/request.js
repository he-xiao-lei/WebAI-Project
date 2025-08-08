import axios from "axios"
// 创建axios示例
const request = axios.create({
    baseURL: '/api',
    timeout: 600000
})
// axios响应 配置reponse拦截器
request.interceptors.response.use(
    // 成功回调
    (response) => {
        return response.data
    },
    //失败回调
    (error) => {
        Promise.reject(error)
    }

)

export default request