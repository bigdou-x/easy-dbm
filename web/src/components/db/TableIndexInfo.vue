<template>
    <el-row>
        <el-button type="primary">新增</el-button>
        <el-button>删除</el-button>
    </el-row>
    <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="name" label="索引名" width="180" />
        <el-table-column prop="column" label="包含列" width="180" />
        <el-table-column prop="length" label="索引类型" />
        <el-table-column prop="comment" label="索引结构" />
    </el-table>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
const tableData = [
    {
        date: '2016-05-03',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles'
    },
    {
        date: '2016-05-02',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles'
    },
    {
        date: '2016-05-04',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles'
    },
    {
        date: '2016-05-01',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles'
    }
]
</script>

<style></style>
