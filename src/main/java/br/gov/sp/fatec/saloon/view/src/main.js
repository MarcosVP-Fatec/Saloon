import Vue      from 'vue'
import App      from './App.vue'
import router   from './router'
import store    from './store'
import axios    from 'axios'

axios.defaults.baseURL = 'https://8080-f2ed4dd6-7d9b-4232-90a7-1b4f1c605ae1.ws-us03.gitpod.io/'

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


