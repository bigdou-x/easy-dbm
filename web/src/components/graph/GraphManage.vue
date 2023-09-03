<template>
    <component
        :is="isGraphListFlag ? GraphListView : GraphView"
        :dbId="dbId"
        :graphLayoutId="graphLayoutId"
        @changeView="changeView"
    ></component>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import GraphView from './GraphView.vue'
import GraphListView from './GraphListView.vue'

const dbId = ref<number>(0)
const graphLayoutId = ref<number>(0)
const isGraphListFlag = ref<boolean>(true)
const route = useRoute()

onMounted(() => {
    if (route.query.dbId) {
        dbId.value = Number(route.query.dbId)
    }
})

const changeView = (
    graphListViewFlag: boolean,
    changeDbId: number,
    changeGraphLayoutId: number
) => {
    console.log(`changeView -> ${graphListViewFlag}`)
    isGraphListFlag.value = graphListViewFlag
    dbId.value = changeDbId
    graphLayoutId.value = changeGraphLayoutId
}
</script>

<style></style>
