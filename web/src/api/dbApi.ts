import { request, type ResultData } from '@/util/request'

export namespace Db {
    export namespace CMD {
        export interface Save {
            id: number
            name: string
            jdbcUrl: string
            username: string
            password: string
        }
        export interface Remove {
            ids: Array<number>
        }
        export interface Connect {
            dbId: number
        }
        export interface DisConnected {
            dbId: number
        }
    }
    export namespace VO {
        export interface Info {
            id: number
            name: string
            jdbcUrl: string
            username: string
            connected: boolean
        }
        export interface Detail {
            id: number
            name: string
            jdbcUrl: string
            username: string
            password: string
        }
        export interface Option {
            id: number
            name: string
        }
    }
}

export const save = (params: Db.CMD.Save) => {
    return request.post<ResultData>('/db/save', params)
}

export const remove = (params: Db.CMD.Remove) => {
    return request.post<ResultData>('/db/remove', params)
}

export const list = () => {
    return request.get<ResultData<Array<Db.VO.Info>>>('/db/list')
}

export const detail = (id: number) => {
    return request.get<ResultData<Array<Db.VO.Detail>>>('/db/detail', {
        id: id
    })
}

export const connect = (params: Db.CMD.Connect) => {
    return request.post<ResultData>('/db/connect', params)
}

export const closeConnected = (params: Db.CMD.DisConnected) => {
    return request.post<ResultData>('/db/closeConnected', params)
}

export const listOption = () => {
    return request.get<ResultData<Array<Db.VO.Option>>>('/db/options')
}
