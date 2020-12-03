import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/home'
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
    , {
        path: '/',
        name: 'Logar',
        component: () => import('../views/Logar.vue')
    }
    , {
        path: '/naologado',
        name: 'NaoLogado',
        component: () => import('../views/NaoLogado.vue')
    }

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router

