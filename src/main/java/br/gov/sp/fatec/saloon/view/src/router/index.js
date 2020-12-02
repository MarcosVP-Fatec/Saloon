import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/'
        , name: 'Home'
        , component: Home
    }
    , {
        path: '/about',
        name: 'About',
        component: function () {
            return import('../views/About.vue')
        }
    }
    , {
        path: '/proprietario',
        name: 'Proprietario',
        component: () => import('../views/regi/Proprietario.vue')
    }
    , {
        path: '/usuario',
        name: 'Usuario',
        component: () => import('../views/regi/Usuario.vue')
    }

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
