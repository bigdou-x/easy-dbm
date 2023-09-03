<template>
    <el-form ref="ruleFormRef" :model="ruleForm" status-icon :rules="rules" label-width="90px">
        <el-collapse v-model="activeNames">
            <el-collapse-item title="基础配置" name="1">
                <el-form-item label="表名" prop="name">
                    <el-input v-model="ruleForm.name" />
                </el-form-item>
                <el-form-item label="备注" prop="comment">
                    <el-input v-model="ruleForm.comment" />
                </el-form-item>
                <!-- <el-form-item label="存储引擎" prop="storageEngine">
                    <el-input v-model.number="ruleForm.storageEngine" />
                </el-form-item>
                <el-form-item label="字符集" prop="charset">
                    <el-input v-model="ruleForm.charset" />
                </el-form-item>
                <el-form-item label="校验规则" prop="validateRule">
                    <el-input v-model="ruleForm.validateRule" />
                </el-form-item> -->
            </el-collapse-item>
            <!-- <el-collapse-item title="高级配置" name="2">
                <el-form-item
                    label="自增当前值"
                    prop="autoincrementCurrentValue"
                >
                    <el-input v-model="ruleForm.autoincrementCurrentValue" />
                </el-form-item>
                <el-form-item label="行格式" prop="rowFormat">
                    <el-input v-model="ruleForm.rowFormat" />
                </el-form-item>
                <el-form-item label="平均行长度" prop="averageRowLength">
                    <el-input v-model="ruleForm.averageRowLength" />
                </el-form-item>
                <el-form-item label="最小行" prop="minRow">
                    <el-input v-model="ruleForm.minRow" />
                </el-form-item>
                <el-form-item label="最大行" prop="maxRow">
                    <el-input v-model="ruleForm.maxRow" />
                </el-form-item>
            </el-collapse-item> -->
        </el-collapse>
    </el-form>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
const ruleFormRef = ref<FormInstance>()
const activeNames = ref(['1'])
const ruleForm = reactive({
    name: '',
    comment: '',
    storageEngine: '',
    charset: '',
    validateRule: '',
    autoincrementCurrentValue: '',
    rowFormat: '',
    averageRowLength: '',
    minRow: '',
    maxRow: ''
})

const rules = reactive<FormRules<typeof ruleForm>>({
    name: [{ required: true, message: '表名是必填项!' }]
})

const submitForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.validate((valid) => {
        if (valid) {
            console.log('submit!')
        } else {
            console.log('error submit!')
            return false
        }
    })
}

const resetForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.resetFields()
}
</script>

<style></style>
