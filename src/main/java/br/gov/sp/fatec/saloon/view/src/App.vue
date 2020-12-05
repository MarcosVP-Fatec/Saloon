<template>
  <div id="app">
    <div id="nav">
            <router-link to="/">Home</router-link> 
        |   <router-link to="/proprietario">Proprietários</router-link>
        |   <router-link to="/usuario">Usuários</router-link>
        |   <router-link to="/about">Sobre o Saloon</router-link>
        |   <router-link to="/logar">Entrar</router-link>
    </div>
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>

<script>
export default {
      name: 'app'
    , data() {
        return {
            nome: 'ver depois'
        }
    }
}
</script>