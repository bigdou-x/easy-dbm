<template>
    <el-row>
        <el-button type="primary">新增</el-button>
        <el-button>删除</el-button>
        <el-button>插入</el-button>
        <el-button>上移</el-button>
        <el-button>下移</el-button>
    </el-row>
    <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="name" label="列名" width="180" />
        <el-table-column prop="type" label="类型" width="180" />
        <el-table-column prop="length" label="长度" />
        <el-table-column prop="comment" label="备注" />
        <el-table-column prop="nullable" label="可空" />
        <el-table-column prop="primaryKey" label="主键" />
        <el-table-column prop="index" label="索引" />
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
