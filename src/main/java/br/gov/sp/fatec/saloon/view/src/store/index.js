import Vue          from 'vue'
import Vuex         from 'vuex'
import VuexPersist  from 'vuex-persist'

Vue.use(Vuex)

// Instanciar o vuexPersist
const vuexPersist = new VuexPersist({
     key: '202011021841' //Colocar um número único
   , storage: localStorage
})

// Similar a classes do java
// Todas as variáveis criadas aqui são compartilhadas por todos os componentes da aplicação
// Possível criar getters para não acessar a variável diretamente
// Mutations são métodos (setters) para acessar os valores do estado indiretamente. A vantagem
//   é que se vc tiver instalado em seu navegador a extensão "devtools" você conseguirá mapear
//   quando a variável foi alterada, porque quando o motation for chamado ele irá logar.
//   A limitação é que ele só set. Não consegue fazer nada assíncrono. Ele fica esperando.
//   Quando se chama uma mutation é fazer uma COMMIT. O valor vai ser aplicado a um STATE por uma mutation
// Actions servem para fazer operações assincronas com chamadas ao back-end e o resultado vai alterar o estado
//   Quando se chama uma Action se chama dispatch

export default new Vuex.Store({
  
  plugins: [
    vuexPersist.plugin
  ],  
  state: {
      login_usuario: null
    , login_senha: null
    , login_token: null
    , login_role: null
    , last_from: null
  },
  getters: {
      getUsuario:   state => { return state.login_usuario   }
    , getSenha:     state => { return state.login_senha     }
    , getToken:     state => { return state.login_token     }
    , getRole:      state => { return state.login_role      }
    , getFromn:     state => { return state.last_from       }
  },
  mutations: {
      setUsuario(state, valor)      { state.login_usuario = valor; }
    , setSenha(state, valor)        { state.login_senha   = valor; }
    , setToken(state, valor)        { state.login_token   = valor; }
    , setRole(state, valor)         { state.login_role    = valor; }
    , setLastFrom(state, valor)     { state.last_from     = valor; }
  },
  actions: {
  },
  modules: {
  }
})
