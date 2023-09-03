<script setup>
import { ref } from 'vue'
const encodeStr = ref('')

const decodeStr = ref('')
const decode = () => {
    console.log('encodeStr-> ', encodeStr.value, 'decodeStr-> ', decodeStr.value)
    decodeStr.value = decodeURI(encodeStr.value)
}
</script>

<template>
    <div>
        <el-row>
            <el-col :span="11">
                <el-input
                    v-moda="encodeStr"
                    :rows="24"
                    type="textarea"
                    placeholder="Please input encodeStr"
                />
            </el-col>
            <el-col :span="2">
                <a-button @click="decode">>>></a-button>
            </el-col>
            <el-col :span="11">
                <el-input
                    v-moda="decodeStr"
                    :rows="24"
                    type="textarea"
                    placeholder="Please input decodeStr"
                />
            </el-col>
        </el-row>
    </div>
</template>

<style scoped></style>
