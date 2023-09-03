<script lang="ts" setup>
import { ref } from 'vue'
import Menu from '@/components/base/Menu.vue'
</script>

<template>
    <el-container>
        <el-aside style="width: 200px">
            <div style="font-size: 20px">数据库运维工具</div>
            <Menu></Menu>
        </el-aside>
        <el-container>
            <el-header style="background: #fff; padding: 0"> xxx </el-header>
            <el-main>
                <router-view></router-view>
            </el-main>
            <el-footer style="text-align: center"> Ant Design ©2018 Created by Ant UED </el-footer>
        </el-container>
    </el-container>
</template>

<style>
.el-row {
    padding-bottom: 8px;
    padding-top: 8px;
}
</style>
