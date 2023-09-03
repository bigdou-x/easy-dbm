import { request, type ResultData } from '@/util/request'

export namespace Graph {
    export namespace CMD {
        export interface TableGraphQry {
            dbId: number
            graphLayoutId: number
        }
        export interface GraphListQry {
            dbId: number
        }
        export interface Save {
            id: number
            dbId: number
            name: string
            description: string
        }
        export interface GraphByIdQry {
            id: number
        }
        export interface Remove {
            ids: Array<number>
        }
        export interface SaveLayout {
            dbId: number
            graphLayoutId: number
            graphEdges: Array<GraphEdgeRelation>
            graphNodes: Array<GraphNode>
        }
        export interface GraphEdgeRelation {
            sourceCell: string
            sourcePort: string
            targetCell: string
            targetPort: string
        }
        export interface GraphNode {
            nodeKey: string
            positionX: number
            positionY: number
        }
    }
    export namespace VO {
        export interface TableGraphLayoutVO {
            tables: Array<TableVO>
            graphEdges: Array<GraphEdgeVO>
        }
        export interface TableVO {
            tableId: number
            tableName: string
            tableComment: string
            positionX: number
            positionY: number
            columns: Array<ColumnVO>
        }
        export interface ColumnVO {
            id: number
            name: string
            type: string
            length: number
            primaryKey: boolean
            comment: string
            ordinalPosition: number
        }
        export interface GraphEdgeVO {
            sourceCell: string
            sourcePort: string
            targetCell: string
            targetPort: string
        }
        export interface GraphInfoVO {
            id: number
            name: string
            dbId: number
            description: string
        }
        export interface GraphDetailVO {
            id: number
            name: string
            dbId: number
            description: string
        }
    }
}

export const queryTableGraphLayout = (params: Graph.CMD.TableGraphQry) => {
    return request.get<ResultData<Graph.VO.TableGraphLayoutVO>>('/graph/layout', params)
}

export const listGraph = (params: Graph.CMD.GraphListQry) => {
    return request.get<ResultData<Array<Graph.VO.GraphInfoVO>>>('/graph/list', params)
}

export const detailGraph = (params: Graph.CMD.GraphByIdQry) => {
    return request.get<ResultData<Array<Graph.VO.GraphDetailVO>>>('/graph/detail', params)
}

export const saveGraph = (params: Graph.CMD.Save) => {
    return request.post<ResultData>('/graph/save', params)
}

export const removeGraph = (params: Graph.CMD.Remove) => {
    return request.post<ResultData>('/graph/remove', params)
}

export const saveLayout = (params: Graph.CMD.SaveLayout) => {
    return request.post<ResultData>('/graph/layout/save', params)
}
