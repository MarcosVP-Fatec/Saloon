import Vue      from 'vue'
import App      from './App.vue'
import router   from './router'
import store    from './store'
import axios    from 'axios'

//axios.defaults.baseURL = 'localhost://8080-eab15bbd-6b5d-48c7-834b-9f5f0fe0bb02.ws-us03.gitpod.io/'
//axios.defaults.baseURL = 'http://localhost:8080/saloon/' //Back-end
axios.defaults.baseURL = 'https://8081-turquoise-wildfowl-zo1jv6en.ws-us09.gitpod.io/saloon/'

// axios.interceptors.request.use( config => {
//     config.headers.Authorization = 'um token';
//     return config;
//   .
// })

axios.interceptors.request.use( config => {
    const token = localStorage.getItem('login_token')
    if (token) {
      config.headers.Authorization = `Bearer $(token)`
    }
    return config;
}, (err) => {
  return Promise.reject(err)
})

axios.interceptors.response.use( response => {
  return response;
}, (error) => {
  if (error.response.status == 401) {
    window.location = '#/home'
  }
  return Promise.reject(error)
})

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: function (h) { return h(App) }
}).$mount('#app')


