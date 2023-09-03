<template>
    <el-row>
        <el-space>
            <el-input v-model="dbName" placeholder="请输入数据库名称" />
            <el-button type="primary" @click="query">查询</el-button>
            <el-button @click="add">新增</el-button>
        </el-space>
    </el-row>
    <el-row>
        <el-space wrap>
            <el-card
                v-for="data in datas"
                :key="data.id"
                class="box-card"
                body-style="{ padding: '0px' }"
            >
                <template #header>
                    <div class="card-header">
                        <span>{{ data.name }}</span>
                    </div>
                </template>
                <el-row style="text-align: center">
                    <el-col>
                        <template v-if="!data.connected">
                            <el-button @click="connect(data.id)" title="连接" icon circle>
                                <template #icon>
                                    <svg
                                        t="1693201907020"
                                        class="icon"
                                        viewBox="0 0 1024 1024"
                                        version="1.1"
                                        xmlns="http://www.w3.org/2000/svg"
                                        p-id="5293"
                                        width="200"
                                        height="200"
                                    >
                                        <path
                                            d="M139.809136 882.957189c-110.688814-110.740801-110.688814-290.957283 0-401.646097l71.114249-71.11325a42.664751 42.664751 0 1 1 60.312098 60.357086l-71.11325 71.113249c-77.411589 77.462575-77.411589 203.460353 0 280.973915 74.953237 75.004223 205.865719 75.107196 281.025902 0l71.113249-71.113249a42.646755 42.646755 0 0 1 60.260111 60.362084l-71.118248 71.066262a283.790173 283.790173 0 0 1-401.594111 0z m213.343748-213.289761a42.642756 42.642756 0 0 1 0-60.362085L609.138387 353.311842a42.646755 42.646755 0 1 1 60.260111 60.362084L413.408996 669.663429a42.70474 42.70474 0 0 1-60.259112 0z m398.216001-56.885001a42.642756 42.642756 0 0 1 0-60.362085l71.062263-71.113249c77.461576-77.410589 77.461576-203.562326 0-280.972915-75.107196-75.210169-206.019678-75.158183-281.025901 0l-71.062263 71.113249a42.664751 42.664751 0 0 1-60.311098-60.362084l71.11325-71.114249a283.91414 283.91414 0 0 1 401.59411 0c110.689814 110.689814 110.689814 290.906296 0 401.694084l-71.113249 71.11325a42.70474 42.70474 0 0 1-60.259111 0z"
                                            p-id="5294"
                                            fill="#8a8a8a"
                                        ></path>
                                    </svg>
                                </template>
                            </el-button>
                        </template>
                        <template v-else>
                            <el-button @click="closeConnected(data.id)" title="断开" icon circle>
                                <template #icon>
                                    <svg
                                        t="1693201815086"
                                        class="icon"
                                        viewBox="0 0 1024 1024"
                                        version="1.1"
                                        xmlns="http://www.w3.org/2000/svg"
                                        p-id="2329"
                                        width="200"
                                        height="200"
                                    >
                                        <path
                                            d="M939.464783 84.620124a288.213008 288.213008 0 0 0-408.00314 0L402.286245 213.966163l68.000523 68.000524 129.175398-129.175398c71.669309-71.75463 192.82457-79.348164 272.002093 0 79.348164 79.348164 71.669309 200.162142 0 271.916772L742.11822 553.798138l68.171164 68.085844 129.175399-129.175398a288.724932 288.724932 0 0 0-0.170642-408.003139z m-514.739219 786.911952c-71.75463 71.669309-192.82457 79.348164-272.002093 0a191.459441 191.459441 0 0 1 0-272.002093l129.175398-129.175398-68.085843-68.085843L84.552307 531.44414a288.213008 288.213008 0 0 0 0 407.832498 288.383649 288.383649 0 0 0 407.832498 0l129.260719-129.090077-68.000523-68.000524-129.004757 129.346039z m-248.282964-762.851541a10.665076 10.665076 0 0 0-15.016427 0l-52.898776 52.813455a10.665076 10.665076 0 0 0 0 15.016427l739.132413 739.132413a10.665076 10.665076 0 0 0 15.101747 0l52.813456-52.898776a10.665076 10.665076 0 0 0 0-15.016427L176.4426 108.680535z"
                                            fill="#8a8a8a"
                                            p-id="2330"
                                            data-spm-anchor-id="a313x.search_index.0.i1.7a0e3a814HLE29"
                                            class="selected"
                                        ></path>
                                    </svg>
                                </template>
                            </el-button>
                            <el-button
                                @click="openDbTable(data.id)"
                                title="图"
                                :icon="Picture"
                                circle
                            />
                        </template>
                        <el-button :icon="Edit" circle title="编辑" @click="detail(data.id)" />
                        <el-button :icon="Delete" circle title="删除" @click="remove(data.id)" />
                    </el-col>
                </el-row>
            </el-card>
        </el-space>
    </el-row>
    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="700px">
        <el-form ref="formRef" :model="dbForm" label-width="80px" :rules="rules">
            <el-form-item label="名称" prop="name">
                <el-input v-model="dbForm.name" />
            </el-form-item>
            <el-form-item label="JdbcUrl" prop="jdbcUrl">
                <el-input
                    v-model="dbForm.jdbcUrl"
                    :autosize="{ minRows: 2, maxRows: 4 }"
                    type="textarea"
                />
            </el-form-item>
            <el-form-item label="账号" prop="username">
                <el-input v-model="dbForm.username" />
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="dbForm.password" type="password" show-password />
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
import {
    save,
    list,
    detail as getDetailById,
    remove as removeById,
    connect as connectByDbId,
    closeConnected as closeConnectedByDbId,
    type Db
} from '@/api/dbApi'
import { Delete, Edit, Picture } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'

const dbName = ref<string>('')
const dialogTitle = ref('')
const dialogVisible = ref(false)
const formRef = ref(null)
const datas: Array<Db.VO.Info> = reactive([])
const router = useRouter()
const dbForm = reactive<Db.CMD.Save>({
    id: 0,
    name: '',
    jdbcUrl: '',
    username: '',
    password: ''
})

onMounted(() => {
    query()
})

const query = async () => {
    console.info('点击查询')
    let res = await list()
    datas.length = 0
    datas.push(...(res.data || []))
    console.info(datas)
}
const add = () => {
    console.info('点击新增')
    dialogTitle.value = '添加数据库'
    dialogVisible.value = true
}
const remove = async (dbId: number) => {
    let res = await removeById({ ids: [dbId] })
    query()
}
const detail = async (dbId: number) => {
    console.info('点击详情')
    dialogTitle.value = '数据库详情'
    dialogVisible.value = true
    let res = await getDetailById(dbId)
    Object.assign(dbForm, res.data)
}

const rules = reactive<FormRules>({
    name: [
        {
            required: true,
            message: '请输入名称',
            trigger: 'blur'
        }
    ],
    jdbcUrl: [
        {
            required: true,
            message: '请输入JdbcUrl',
            trigger: 'blur'
        }
    ],
    username: [
        {
            required: true,
            message: '请输入账号',
            trigger: 'blur'
        }
    ],
    password: [
        {
            required: true,
            message: '请输入密码',
            trigger: 'blur'
        }
    ]
})

const submitForm = async () => {
    formRef.value.validate(async (valid, fields) => {
        if (!valid) {
            ElMessage({
                message: '表单校验未通过!',
                type: 'warning'
            })
            return
        }
        console.log('submit!')
        let res = await save(dbForm)
        console.info(res)
        dialogVisible.value = false
        query()
    })
}

const connect = async (dbId: number) => {
    console.log('connect -> ', dbId)
    await connectByDbId({ dbId: dbId })
    query()
}

const closeConnected = async (dbId: number) => {
    console.log('closeConnected -> ', dbId)
    await closeConnectedByDbId({ dbId: dbId })
    query()
}

const openDbTable = (dbId: number) => {
    router.push({
        path: '/db/graph/manage',
        query: {
            dbId: dbId
        }
    })
}
</script>

<style scoped>
.box-card {
    width: 300px;
}
</style>
