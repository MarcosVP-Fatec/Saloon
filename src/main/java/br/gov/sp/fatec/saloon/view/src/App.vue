<template>
  <div id="app">
    <div id="nav">
       <router-link to="/"             title="Página principal"               >Home</router-link>
       <span v-if="isAdministrador()" > | </span> 
            <router-link v-if="isAdministrador()" to="/proprietario" title="Proprietário de salões">
            Proprietários</router-link>

        |   <router-link to="/parceiro"       title="Cadastro de parceiros (buffets)">
            Parceiros</router-link>

        |   <router-link to="/usuario"        title="Cadastro de clientes">
            Clientes</router-link>

        |   <router-link to="/about"          title="Saiba mais sobre o Saloon">
            Sobre o Saloon</router-link>

        |   <router-link v-if="!isLogado()" to="/logar"  title="Faça o seu login"  data-cy="login">
            Entrar</router-link>
            <router-link v-if="isLogado()"  to="/logar"  title="Desconectar"       data-cy="logout">
            Logout</router-link> {{ this.usuarioAtual() }}

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

  import { mapGetters   } from 'vuex'; 
  
  export default {
        name: 'app'
      , data() {
          return {
              app_usuario: this.getUsuario()?'('+this.getUsuario()+')':''
          }
        }
      , methods: {
              ...mapGetters([
                  'getUsuario', 'getToken', 'getRole'
              ]),
              isAdministrador(){
                return this.getRole() === 'ROLE_ADMIN';
              },
              isLogado(){
                return this.getToken() !== null;
              },
              quemEstaLogado(){
                return this.getUsuario();
              },
              usuarioAtual(){
                return this.getUsuario()?'[ '+this.getUsuario()+' ]':''
              }
        }
  }
</script>