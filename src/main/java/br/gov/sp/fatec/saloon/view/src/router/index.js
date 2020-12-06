import Vue          from 'vue'
import VueRouter    from 'vue-router'
import Home         from '../views/Home.vue'
import store        from '../store'

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
    , {
        path: '/logar',
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

// to = Para onde quero ir
// from = De onde eu venho
// next = Função que uso para permitir ou não a navegação
router.beforeEach( (to, from, next) => {
    //Aqui faremos a verificação do usuário logado se ele tem premissão ou não de entrar
    //if (from.name === 'Logar' || from.name === 'Home' || store.state.isLogValido()) {
    window.alert("ROUTER | INDEX.JS to.name = " + to.name + "   |||   from.name = " + from.name + "   |||   isLogVencido = " + (store.getters.isLogVencido?"SIM":"não"));    
    if ( to.name === 'Home' || to.name === null) {    
        next()
    } else if (store.getters.isLogVencido) {
        window.alert("ROUTER | INDEX.JS else if (isLogVencido)= "+store.getters.isLogVencido);
        if (to.name === 'Logar') {
            next()
        } else {
           next(false) ;
        }
    } else {
        alert("ROUTER | INDEX.JS  ÚLTIMO ELSE")
        next()
    }        
} )

export default router
