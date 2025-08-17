import axios from "axios"
import router from "@/router"
import { ElMessage } from "element-plus";


// 创建axios示例
const request = axios.create({
    baseURL: '/api',
    timeout: 600000
})
// axios响应 配置reponse拦截器
// 响应拦截器
// 拦截服务器响应的401状态
request.interceptors.response.use(
    // 成功回调
    (response) => {
        return response.data
    },
    //失败回调
    (error) => {
        if (error.response.status === 401) {
            // // 清除token
            // localStorage.removeItem("token");
            // 跳转到登录页
            ElMessage.error("登录过期，请重新登录");
            router.push("/login");
        }
        Promise.reject(error)
    }

)
// 配置请求拦截器，配置token
request.interceptors.request.use(
    (config) => {
        // 从localStorage中获取token
        // 将JSON字符串解析为对象

        // const token = JSON.parse(localStorage.getItem("loginUser"));
        const token = localStorage.getItem("token");

        if (token) {

            // 如果token存在，将token添加到请求头中
            config.headers.token = token;


        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);


export default request