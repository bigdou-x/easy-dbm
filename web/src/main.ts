import { createApp } from 'vue'
import { createRouter, createWebHashHistory } from 'vue-router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import DecodeData from './components/DecodeData.vue'
import DbManage from './components/db/DbManage.vue'
import GraphManage from './components/graph/GraphManage.vue'
import GraphListView from './components/graph/GraphListView.vue'
import GraphView from './components/graph/GraphView.vue'

const routes = [
    {
        path: '/db/manage',
        component: DbManage
    },
    {
        path: '/db/graph/manage',
        component: GraphManage
    },
    {
        path: '/db/graph/graph',
        component: GraphView,
        props: true
    },
    { path: '/decode', component: DecodeData }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

const app = createApp(App)

app.use(ElementPlus)
app.use(router)
app.mount('#app')
