<template>
    <div id="container" class="graph-container"></div>
</template>

<script setup lang="ts">
import { onMounted, ref, watch } from 'vue'
import { Graph, Shape, Cell } from '@antv/x6'
import primaryKeySvg from '@/assets/images/primary-key.svg'

/**
 * 表模型
 */
export interface TableModel {
    id: Number
    name: String
    comment: String
    positionX: Number
    positionY: Number
    columnModels: Array<ColumnModel>
}

/**
 * 列模型
 */
export interface ColumnModel {
    id: Number
    name: String
    type: String
    length: Number
    primaryKey: Boolean
    comment: String
    ordinalPosition: Number
}

/**
 * 边模型
 */
export interface EdgeModel {
    sourceCell: string
    sourcePort: string
    targetCell: string
    targetPort: string
}
const props = defineProps({
    tableModels: {
        type: Array<TableModel>,
        required: false,
        default: function () {
            return []
        }
    },
    edgeModels: {
        type: Array<EdgeModel>,
        required: false,
        default: function () {
            return []
        }
    }
})
const emit = defineEmits(['dblclickNode'])

const LINE_HEIGHT = 24
const NODE_WIDTH = 220
let graph: Graph
/**
 * 初始化
 */
const init = () => {
    Graph.registerPortLayout(
        'erPortPosition',
        (portsPositionArgs) => {
            return portsPositionArgs.map((_, index) => {
                return {
                    position: {
                        x: 0,
                        y: (index + 1) * LINE_HEIGHT
                    },
                    angle: 0
                }
            })
        },
        true
    )

    Graph.registerNode(
        'er-rect',
        {
            inherit: 'rect',
            markup: [
                {
                    tagName: 'rect',
                    selector: 'body'
                },
                {
                    tagName: 'text',
                    selector: 'tableName'
                }
            ],
            attrs: {
                rect: {
                    strokeWidth: 1,
                    stroke: '#5F95FF',
                    fill: '#5F95FF'
                },
                tableName: {
                    fontWeight: 'bold',
                    fill: '#ffffff',
                    fontSize: 12
                }
            },
            ports: {
                groups: {
                    columns: {
                        markup: [
                            {
                                tagName: 'rect',
                                selector: 'portBody'
                            },
                            {
                                tagName: 'image',
                                selector: 'primaryKey'
                            },
                            {
                                tagName: 'text',
                                selector: 'columnName'
                            },
                            {
                                tagName: 'text',
                                selector: 'columnType'
                            }
                        ],
                        attrs: {
                            portBody: {
                                width: NODE_WIDTH,
                                height: LINE_HEIGHT,
                                strokeWidth: 1,
                                stroke: '#5F95FF',
                                fill: '#EFF4FF',
                                magnet: true
                            },
                            primaryKey: {
                                width: 16,
                                height: 16,
                                x: 4,
                                y: 4
                            },
                            columnName: {
                                ref: 'portBody',
                                refX: 23,
                                refY: 8,
                                fontSize: 10
                            },
                            columnType: {
                                ref: 'portBody',
                                refX: 140,
                                refY: 8,
                                fontSize: 10
                            }
                        },
                        position: 'erPortPosition'
                    }
                }
            }
        },
        true
    )
}
const graphData = ref<any[]>([])
const dataHandler = (tableModels: Array<TableModel>, edgeModels: Array<EdgeModel>) => {
    console.log(`dataHandler -> ${tableModels},     ${edgeModels}`)
    tableModels.forEach((tableModel) => {
        // let width = tableModel.name.length * 9.5
        let width = NODE_WIDTH
        let graphTableData: any = {
            id: tableModel.name,
            shape: 'er-rect',
            width: width,
            height: 24,
            position: {
                x: tableModel.positionX,
                y: tableModel.positionY
            },
            attrs: {
                tableName: {
                    text: !tableModel.comment
                        ? `${tableModel.name}`
                        : `${tableModel.name}(${tableModel.comment})`
                }
            },
            ports: []
        }
        let graphColumnDatas: any[] = []
        tableModel.columnModels.forEach((columnModel) => {
            let graphColumnData = {
                id: columnModel.name,
                group: 'columns',
                attrs: {
                    portBody: {
                        width: width
                    },
                    columnName: {
                        text: columnModel.name
                    },
                    columnType: {
                        text: columnModel.length
                            ? `${columnModel.type}(${columnModel.length})`
                            : columnModel.type
                    }
                }
            }
            if (columnModel.primaryKey) {
                graphColumnData.attrs.primaryKey = {
                    'xlink:href': primaryKeySvg
                }
            }
            graphColumnDatas.push(graphColumnData)
        })
        graphTableData.ports = graphColumnDatas
        graphData.value.push(graphTableData)
    })
    edgeModels.forEach((edge) => {
        graphData.value.push({
            shape: 'edge',
            attrs: {
                line: {
                    stroke: '#A2B1C3',
                    strokeWidth: 2
                }
            },
            source: {
                cell: edge.sourceCell,
                port: edge.sourcePort
            },
            target: {
                cell: edge.targetCell,
                port: edge.targetPort
            }
        })
    })
    console.log(`graphData -> ${graphData.value}`)
}

onMounted(() => {
    // 创建图
    graph = new Graph({
        container: document.getElementById('container')!,
        connecting: {
            router: {
                name: 'er',
                args: {
                    offset: 25,
                    direction: 'H'
                }
            },
            allowBlank: false,
            highlight: true, //拖动边时，是否高亮显示所有可用的连接桩或节点，默认值为 false 。一般都会与 highlighting 联合使用。
            createEdge() {
                return new Shape.Edge({
                    attrs: {
                        line: {
                            stroke: '#A2B1C3',
                            strokeWidth: 2
                        }
                    }
                })
            },
            validateConnection({ sourceCell, targetCell, sourceMagnet, targetMagnet }) {
                // 不能连接自身
                if (sourceCell === targetCell) {
                    return false
                }

                // 不能重复连线
                const edges = this.getEdges()
                const portId = targetMagnet?.getAttribute('port')
                if (edges.find((edge) => edge.getTargetPortId() === portId)) {
                    return false
                }

                return true
            }
        },
        width: 1400,
        height: 800,
        preventDefaultContextMenu: true, //禁用浏览器默认右键菜单
        grid: {
            // 网格
            visible: true,
            type: 'fixedDot',
            size: 10,
            args: {
                color: '#ddd', // 网格线颜色
                thickness: 2 // 网格线宽度
            }
        },
        background: {
            color: '#F2F7FA' // 背景色
        },
        mousewheel: {
            // 画布缩放
            enabled: true,
            modifiers: ['ctrl', 'meta']
        },
        highlighting: {
            // 连接桩可以被连接时在连接桩外围围渲染一个包围框
            // magnetAvailable: {
            //     name: 'stroke',
            //     args: {
            //         attrs: {
            //         stroke: '#A4DEB1',
            //         strokeWidth: 4,
            //         },
            //     },
            // },
            // 连接桩吸附连线时在连接桩外围围渲染一个包围框
            magnetAdsorbed: {
                name: 'stroke',
                args: {
                    attrs: {
                        stroke: '#31d0c6',
                        strokeWidth: 1
                    }
                }
            }
        },
        panning: {
            // 画布平移
            enabled: true
        }
    })

    // graph.on('node:port:dblclick', ({ node }) => {
    //     // let currentPortPosition = node.getPortsPosition(currentPort.group)
    //     // // let
    //     // // node.removePort(port)
    //     console.log(`node -> ${JSON.stringify(node)}`)
    //     // console.log(`port -> ${JSON.stringify(currentPort)}`)
    //     // console.log(
    //     //     `position -> ${JSON.stringify(
    //     //         node.getPortsPosition(currentPort.group)
    //     //     )}`
    //     // )
    // })
    graph.on('node:dblclick', function ({ node }) {
        console.log(`id -> ${node.id}, node -> ${JSON.stringify(node)}`)
        console.log(`json -> ${JSON.stringify(graph.toJSON())}`)
        emit('dblclickNode', node.id)
    })
})

const getGraphData = () => {
    console.log(`json -> ${JSON.stringify(graph.toJSON())}`)
    return graph.toJSON()
}

defineExpose({
    getGraphData
})

watch(props.tableModels, (news) => {
    const cells: Cell[] = []
    dataHandler(props.tableModels, props.edgeModels)
    graphData.value.forEach((item: any) => {
        if (item.shape === 'edge') {
            cells.push(graph.createEdge(item))
        } else {
            cells.push(graph.createNode(item))
        }
    })
    graph.resetCells(cells)
    graph.zoomToFit({ padding: 10, maxScale: 1 })
})

init()
</script>

<style scoped>
.graph-container {
    width: 100%;
    height: 100%;
    position: relative;
}
</style>
