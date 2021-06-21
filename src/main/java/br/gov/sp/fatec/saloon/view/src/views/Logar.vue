<template> 
  <!--  Quando se usa um form no vue colocamos um @submit.prevent
        Um formulário quando é submetido envia os dados para o back end e recarrega a tela, mas neste caso 
        não queremos enviar os dados para o back-end nem que recarregue a tela. Este comando vai chamar
        um método de login. -->
  <div class="login">
        <!-- LOGIN -->
        <form @submit.prevent="login" v-if="getToken()===null">
            <h2>Entre com seu usuário e senha</h2>
            <p>
                <input type="text"     id="username"      placeholder="Nome de usuário" size=50 required autofocus v-model="log_apelido"/>
            </p>
            <p>
                <input type="password" id="inputPassword" placeholder="Senha"           size=50 required           v-model="log_senha"/>
            </p>
            <button type="submit" style="height:40px; width:300px; border-radius:25px;">Entrar</button>
            <p><router-link to="usuario" replace>Quero me cadastrar</router-link></p>
            <p>{{ log_situacao }}</p>
        </form>
        <!-- LOGOUT -->
        <form @submit.prevent="sair" v-else>
            <h2>Você já está conectado neste momento</h2>
            <!-- <input type="button" value="Home" style="height:40px; width:150px; border-radius:25px;"> -->
            <button type="submit" style="height:40px; width:150px; border-radius:25px;" title="Clique aqui para desconectar">Sair</button>
            <p><router-link to="/" replace>Voltar</router-link></p>
        </form>
  </div>
</template>

<script>

    import axios            from 'axios';
    import { mapMutations } from 'vuex'; //Na store colocar usuário e senha para controlar o login.
    import { mapGetters   } from 'vuex'; 

    export default {
            name: 'Logar'
        ,   data() {
                return {
                    log_apelido: ''
                ,   log_senha: ''
                ,   log_situacao: ''
                }
            }
        ,   methods: {
                ...mapMutations([
                    'setUsuario', 'setSenha', 'setToken', 'setRole'
                ]),
                ...mapGetters([
                    'getUsuario', 'getToken', 'getRole'
                ]),
                login() {

                    this.log_situacao = "Autenticando...";
                    axios.post('login'
                                , { usuario: this.log_apelido , senha: this.log_senha }
                    ).then( response => { //Se deu tudo certo funcionou usuário e senha

                        // console.log(response);
                        // console.log(response.status);
                        // console.log(response.statusText);
                        // console.log(response.headers);
                        // console.log(response.config);
                        // console.log(localStorage.getItem('token'));

                        this.setSenha(null);

                        //Limpa os campos da tela
                        this.log_senha = '';

                        //Animação de redirecionamento
                        this.log_situacao = "Usuário logado! Redirecionando..";
                        let t = 1000;
                        for (let i=0; i < 10;i++){
                            setTimeout(() => {
                                this.log_situacao = this.log_situacao + ".";
                            }, t+(i*500));
                        }
                
                        //Redirecionamento e registro de usuário e token
                        setTimeout(() => {
                            this.log_apelido = '';
                            this.setUsuario(response.data.usuario);
                            this.setToken(response.data.token);
                            this.setRole(response.data.autorizacao);
                            this.$router.push('/');
                        }, 3000)

                    }).catch( () => {
                        
                        //console.log(error);
                        this.log_senha = '';
                        this.log_situacao = "Usuário ou senha inválidos !";
                        setTimeout(() => {
                            this.limparEstado();
                        }, 3000);

                    });
                },
                exibir(){
                    this.log_apelido = this.getUsuario;
                },
                limparEstado(){
                    this.log_senha="";
                    this.log_situacao="";
                    this.setUsuario(null);
                    this.setSenha(null);
                    this.setToken(null);
                    this.setRole(null);
                },
                sair(){
                    this.limparEstado();
                    this.log_apelido="";
                    //this.$router.push('logar'); //Ficou redundante 
                }
            }
    }

</script>

