<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
// import Api from '@/assets/api'
const selectedKeys = ref([])
const menus = ref([
    {
        id: '1',
        name: '数据库管理',
        path: '/db/manage',
        icon: ''
    },

    {
        id: '2',
        name: '差分数据',
        path: '/db/diff',
        icon: ''
    },
    {
        id: '3',
        name: '数据解码',
        path: '/decode',
        icon: ''
    },
    {
        id: '4',
        name: '数据表管理',
        path: '/db/table/manage',
        icon: ''
    },
    {
        id: '4',
        name: 'ER图管理',
        path: '/db/graph/manage',
        icon: ''
    }
])
const useRouter1 = useRouter()

onMounted(() => {
    console.log('api-url2:', import.meta.env.VITE_API_URL)
    // console.info(Api.get())
})

// const clickMenu = function ({ item, key, keyPath }) {
//   console.info(item, key, keyPath)
//   menus.value.forEach((element) => {
//     if (key == element.id) {
//       useRouter1.push(element.path)
//     }
//   })

// router.value.push({
//   name: 'search',
//   query: '/decode'
// })
// }
</script>

<template>
    <div>
        <el-menu :router="true">
            <div v-for="(menu, index) in menus" :key="index">
                <el-menu-item :key="menu.id" :index="menu.path">
                    <span>{{ menu.name }}</span>
                </el-menu-item>
            </div>
        </el-menu>
    </div>
</template>

<style lang="less" scoped></style>
