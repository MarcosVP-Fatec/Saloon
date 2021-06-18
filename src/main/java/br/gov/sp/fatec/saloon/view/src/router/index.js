import Vue          from 'vue'
import VueRouter    from 'vue-router'
import Home         from '../views/Home.vue'
//import store        from '../store'

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
    //Aqui faremos a verificação do usuário logado se ele tem permissão ou não de entrar
    if ( to.name === 'Home' || to.name === 'About' ||  to.name === 'Logar' || to.name === null) {    
        next();
    } else if (!localStorage.getItem('login_token')) {
        window.alert(localStorage.getItem('login_token'));
        next('/logar');
    } else {
        next()
    }        
})

//Esta opção é para quando o usuário está tentando sair da sala.
//Pode-se usar para verificar se há algo pendende que possa ser perdido
// router.afterEach( (to, from) => ){
    
// }

export default router
