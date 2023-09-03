<template>
    <el-row>
        <el-select v-model="selectedDbId" @change="refreshData" placeholder="请选择数据库">
            <el-option
                v-for="item in dbOptions"
                :key="item.id"
                :label="item.name"
                :value="item.id"
            />
        </el-select>
    </el-row>
    <el-row>
        <el-button type="primary" @click="addGraph">新增</el-button>
    </el-row>
    <el-row>
        <el-table :data="tableData" style="width: 100%">
            <el-table-column label="名称" prop="name" />
            <el-table-column label="描述" prop="description" />
            <el-table-column align="right">
                <template #header>
                    <el-switch
                        v-model="isGraphMode"
                        @change="onChangeGraphMode"
                        active-text="图表"
                        inactive-text="列表"
                    />
                </template>
                <template #default="scope">
                    <el-button size="small" @click="openGraph(scope.row)">配置</el-button>
                    <el-button size="small" @click="editGraph(scope.row)">编辑</el-button>
                    <el-button size="small" type="danger" @click="remove(scope.row)"
                        >删除</el-button
                    >
                </template>
            </el-table-column>
        </el-table>
    </el-row>

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="500px">
        <el-form ref="formRef" :model="graphForm" label-width="80px" :rules="rules">
            <el-form-item label="名称" prop="name">
                <el-input v-model="graphForm.name" />
            </el-form-item>
            <el-form-item label="描述" prop="description">
                <el-input
                    v-model="graphForm.description"
                    :autosize="{ minRows: 2, maxRows: 4 }"
                    type="textarea"
                />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm()"> 保存 </el-button>
            </el-form-item>
        </el-form>
    </el-dialog>
</template>

<script lang="ts" setup>
import { reactive, ref, onMounted } from 'vue'
import { type FormRules, ElMessage } from 'element-plus'
import { useRouter, useRoute } from 'vue-router'
import { listGraph, saveGraph, detailGraph, removeGraph, type Graph } from '@/api/graphApi'

import { listOption, type Db } from '@/api/dbApi'

const selectedDbId = ref<number>(0)
const isGraphMode = ref<boolean>(false)
const dbOptions: Array<Db.VO.Option> = reactive([])
const tableData: Array<Graph.VO.GraphInfoVO> = reactive([])
const dialogTitle = ref('')
const dialogVisible = ref(false)
const formRef = ref<any>(null)
const graphForm = reactive<Graph.CMD.Save>({
    id: 0,
    dbId: 0,
    name: '',
    description: ''
})
const router = useRouter()
const route = useRoute()

const emit = defineEmits(['changeView'])
const props = defineProps({
    dbId: {
        type: Number,
        required: false
    },
    graphLayoutId: {
        type: Number,
        required: true
    }
})

const rules = reactive<FormRules>({
    name: [
        {
            required: true,
            message: '请输入名称',
            trigger: 'blur'
        }
    ]
})

onMounted(async () => {
    await queryDbOption()
    if (route.query.dbId) {
        selectedDbId.value = Number(route.query.dbId)
    }
    refreshData()
})

const queryDbOption = async () => {
    let res = await listOption()
    dbOptions.length = 0
    dbOptions.push(...(res.data || []))
    if (dbOptions.length < 1) {
        return
    }
    selectedDbId.value = dbOptions[0].id
}

const refreshData = async () => {
    let res = await listGraph({ dbId: selectedDbId.value })
    tableData.length = 0
    tableData.push(...(res.data || []))
}

const onChangeGraphMode = () => {
    console.log(isGraphMode.value)
}

const submitForm = async () => {
    formRef.value.validate(async (valid: boolean, fields: any) => {
        if (!valid) {
            ElMessage({
                message: '表单校验未通过!',
                type: 'warning'
            })
            return
        }
        graphForm.dbId = selectedDbId.value
        await saveGraph(graphForm)
        dialogVisible.value = false
        refreshData()
    })
}

const addGraph = () => {
    dialogTitle.value = '新增图表'
    dialogVisible.value = true
}

const editGraph = async (row: Graph.VO.GraphInfoVO) => {
    dialogTitle.value = '编辑图表'
    dialogVisible.value = true
    let res = await detailGraph({ id: row.id })
    Object.assign(graphForm, res.data)
}

const openGraph = (row: Graph.VO.GraphInfoVO) => {
    emit('changeView', false, row.dbId, row.id)
}

const remove = async (row: Graph.VO.GraphInfoVO) => {
    await removeGraph({ ids: [row.id] })
    refreshData()
}
</script>

<style></style>
