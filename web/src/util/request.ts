import axios, {
    type AxiosInstance,
    AxiosError,
    type AxiosRequestConfig,
    type AxiosResponse
} from 'axios'
import { ElMessage } from 'element-plus'

// response interface { code, msg, success }
// 不含 data
interface Result {
    errCode: string
    errMessage: string
    success: boolean
}

// request interface，包含 data
export interface ResultData<T = any> extends Result {
    data?: T
}

enum RequestConfigEnum {
    TIMEOUT = 3000 // 请求超时 request timeout
}

enum ResponseCodeEnum {
    OK = 0 // 成功
}

enum ResponseStatusEnum {
    FAILED = 500, // 服务器异常 server error
    NOT_AUTH = 401, // 无权限
    SUCCESS = 200 // 请求成功 request successfully
}

// axios 基础配置
// @ts-ignore
const config = {
    // 默认地址，可以使用 process Node内置的，项目根目录下新建 .env.development
    baseURL: import.meta.env.VITE_API_URL as string,
    timeout: RequestConfigEnum.TIMEOUT as number, // 请求超时时间
    withCredentials: true // 跨越的时候允许携带凭证
}

class Request {
    service: AxiosInstance

    constructor(config: AxiosRequestConfig) {
        // 实例化 serice
        this.service = axios.create(config)

        // @ts-ignore
        /**
         * 请求拦截器
         * request -> { 请求拦截器 } -> server
         */
        this.service.interceptors.request.use(
            (config: AxiosRequestConfig) => {
                const token = localStorage.getItem('token') ?? ''
                return {
                    ...config,
                    headers: {
                        customToken: 'customBearer ' + token
                    }
                }
            },
            (error: AxiosError) => {
                // 请求报错
                Promise.reject(error)
            }
        )

        /**
         * 响应拦截器
         * response -> { 响应拦截器 } -> client
         */
        this.service.interceptors.response.use(
            (response: AxiosResponse) => {
                const { data, config } = response
                console.log(
                    `requestMethod -> ${config.method}, requsetUrl -> ${
                        config.url
                    }, response -> ${JSON.stringify(data)}`
                )
                if (!data.success) {
                    ElMessage.error(`${data.errCode}, ${data.errMessage}`)
                    return Promise.reject(data)
                }
                return data
            },
            (error: AxiosError) => {
                const { code, message, response } = error
                if (response) {
                    this.handleCode(response.status, response.data)
                } else {
                    ElMessage.error(`请求失败 code: ${code}, message: ${message}`)
                    new Error(`请求失败 code: ${code}, message: ${message}`)
                }
            }
        )
    }

    public handleCode = (code: number, data: any): void => {
        switch (code) {
            case 401:
                ElMessage.error('登陆失败，请重新登录')
                break
            case 404:
                ElMessage.error('请求资源或接口不存在')
                break
            case 500:
                ElMessage.error(data.errMessage)
                break
            default:
                ElMessage.error('请求失败')
                break
        }
    }

    // 通用方法封装
    get<T>(url: string, params?: object): Promise<T> {
        return this.service.get(url, { params })
    }

    post<T>(url: string, params?: object): Promise<T> {
        return this.service.post(url, params)
    }
    put<T>(url: string, params?: object): Promise<T> {
        return this.service.put(url, params)
    }
    delete<T>(url: string, params?: object): Promise<T> {
        return this.service.delete(url, { params })
    }
}

export const request = new Request(config)
