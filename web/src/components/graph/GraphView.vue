<template>
    <div class="graph-options">
        <el-button v-if="route.query.dbId" :icon="CloseBold" circle @click="backPage" />
        <el-button :icon="Check" :loading="saveGraphLoading" circle @click="saveGraph" />
        <el-button :icon="Refresh" :loading="refreshGraphLoading" circle @click="refreshGraph" />
    </div>
    <TableStructureGraph
        ref="graph"
        :tableModels="tableModels"
        :edge-models="edgeModels"
        @dblclickNode="clickNodeCallback"
    />
    <el-dialog v-model="dialogVisible" title="表结构" width="60%" :before-close="handleClose">
        <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
            <el-tab-pane label="基本信息" name="tableBaseInfo">
                <TableBaseInfo />
            </el-tab-pane>
            <el-tab-pane label="列信息" name="tableColumnInfo"><TableColumnInfo /></el-tab-pane>
            <el-tab-pane label="索引" name="tableIndex">
                <TableIndexInfo />
            </el-tab-pane>
        </el-tabs>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogVisible = false">Cancel</el-button>
                <el-button type="primary" @click="dialogVisible = false"> Confirm </el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script setup lang="ts">
import { ref, onMounted, reactive } from 'vue'
import type { TabsPaneContext } from 'element-plus'
import { type ColumnModel, type TableModel, type EdgeModel } from '../base/TableStructureGraph.vue'
import TableStructureGraph from '../base/TableStructureGraph.vue'
import TableBaseInfo from '../db/TableBaseInfo.vue'
import TableColumnInfo from '../db/TableColumnInfo.vue'
import TableIndexInfo from '../db/TableIndexInfo.vue'
import { Refresh, Check, CloseBold } from '@element-plus/icons-vue'
import { useRoute, useRouter } from 'vue-router'
import { queryTableGraphLayout, saveLayout, type Graph } from '@/api/graphApi'
import { Cell } from '@antv/x6/lib/model'

const graph = ref()
const dialogVisible = ref(false)
const saveGraphLoading = ref(false)
const refreshGraphLoading = ref(false)
const activeName = ref('tableBaseInfo')
const tableModels = reactive<TableModel[]>([])
const edgeModels = reactive<EdgeModel[]>([])
const route = useRoute()
const router = useRouter()
const props = defineProps({
    dbId: {
        type: Number,
        required: true
    },
    graphLayoutId: {
        type: Number,
        required: true
    }
})
const emit = defineEmits(['changeView'])

const handleClose = () => {
    dialogVisible.value = false
    console.log('handleClose')
}

const handleClick = (tab: TabsPaneContext, event: Event) => {
    console.log(tab, event, activeName.value)
}

const clickNodeCallback = (nodeId: Number) => {
    console.log(`clickNodeCallback -> ${nodeId}`)
    dialogVisible.value = true
}

const backPage = () => {
    emit('changeView', true, 0, 0)
}

const refreshGraph = async () => {
    refreshGraphLoading.value = true
    let res = await queryTableGraphLayout({ dbId: props.dbId, graphLayoutId: props.graphLayoutId })
    refreshGraphLoading.value = false
    tableModels.length = 0
    for (let table of res.data?.tables || []) {
        let tableModel: TableModel = {
            id: table.tableId,
            name: table.tableName,
            comment: table.tableComment,
            positionX: table.positionX,
            positionY: table.positionY,
            columnModels: []
        }
        for (let column of table.columns) {
            let columnModel: ColumnModel = {
                id: column.id,
                name: column.name,
                type: column.type,
                length: column.length,
                primaryKey: column.primaryKey,
                comment: column.comment,
                ordinalPosition: column.ordinalPosition
            }
            tableModel.columnModels.push(columnModel)
        }
        tableModels.push(tableModel)
    }
    edgeModels.length = 0
    for (let edge of res.data?.graphEdges || []) {
        edgeModels.push({
            sourceCell: edge.sourceCell,
            sourcePort: edge.sourcePort,
            targetCell: edge.targetCell,
            targetPort: edge.targetPort
        })
    }
    console.log(`tableModels -> ${tableModels}`)
    console.log(`edgeModels -> ${edgeModels}`)
}

const saveGraph = async () => {
    console.log(`saveGraph -> ${graph.value.getGraphData()}`)
    let cells = graph.value.getGraphData().cells
    let graphNodes: Array<Graph.CMD.GraphNode> = []
    let graphEdges: Array<Graph.CMD.GraphEdgeRelation> = []
    for (let cell of cells) {
        if (cell.shape == 'edge') {
            graphEdges.push({
                sourceCell: cell.source.cell,
                sourcePort: cell.source.port,
                targetCell: cell.target.cell,
                targetPort: cell.target.port
            })
        } else {
            graphNodes.push({
                nodeKey: cell.id,
                positionX: cell.position.x,
                positionY: cell.position.y
            })
        }
    }
    await saveLayout({
        dbId: props.dbId,
        graphLayoutId: props.graphLayoutId,
        graphEdges: graphEdges,
        graphNodes: graphNodes
    })
}

onMounted(() => {
    refreshGraph()
})
</script>

<style scoped>
.graph-options {
    position: absolute;
    z-index: 1000;
    margin-top: 8px;
    margin-left: 8px;
}
</style>
