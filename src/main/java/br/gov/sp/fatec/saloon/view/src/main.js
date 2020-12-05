import Vue      from 'vue'
import App      from './App.vue'
import router   from './router'
import store    from './store'
import axios    from 'axios'

axios.defaults.baseURL = 'https://8080-b8eb5c1e-29c2-4af8-a777-16ee2a3d25db.ws-us03.gitpod.io/'

// axios.interceptors.request.use( config => {
//     config.headers.Authorization = 'um token';
//     return config;
//   .
// })


Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: function (h) { return h(App) }
}).$mount('#app')


