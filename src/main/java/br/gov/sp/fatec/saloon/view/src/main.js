import Vue      from 'vue'
import App      from './App.vue'
import router   from './router'
import store    from './store'
import axios    from 'axios'

axios.defaults.baseURL = 'https://8080-eab15bbd-6b5d-48c7-834b-9f5f0fe0bb02.ws-us03.gitpod.io/'

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


