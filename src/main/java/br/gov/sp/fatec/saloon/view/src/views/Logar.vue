<template> 
  <!--  Quando se usa um form no vue colocamos um @submit.prevent
        Um formulário quando é submetido envia os dados para o back end e recarrega a tela, mas neste caso 
        não queremos enviar os dados para o back-end nem que recarregue a tela. Este comando vai chamar
        um método de login. -->
  <div>
        <form @submit.prevent="login">
            <h2>Entre com seu usuário e senha</h2>
            <p>
                <input type="text"     id="username"      placeholder="Nome de usuário" size=50 required autofocus v-model="log_apelido"/>
            </p>
            <p>
                <input type="password" id="inputPassword" placeholder="Senha"           size=50 required           v-model="log_senha"/>
            </p>
            <p> {{ log_situacao }}</p>
            <button type="submit" style="height:50px; width:300px; border-radius:25px;">Ok</button>
        </form>
  </div>
</template>

<script>
import axios from 'axios';
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
                'setUsuario', 'setSenha', 'setToken'
            ]),
            ...mapGetters([
                'getUsuario', 'getToken'
            ])
        ,   login() {

                    this.log_situacao = "Autenticando...";
                    axios.post('login'
                              , { usuario: this.log_apelido , senha: this.log_senha }
                    ).then( response => { //Se deu tudo certo funcionou usuário e senha

                        //alert("Entreio no RES"); //PAREI AQUI
                        console.log(response);
                        // console.log(response.status);
                        // console.log(response.statusText);
                        // console.log(response.headers);
                        // console.log(response.config);
                        // console.log("#### TOKEN ### ");
                        // console.log(localStorage.getItem('token'));
                        this.sucesso();

                    }).catch( error => {
                        
                        console.log("###### CATCH ERROR #######");
                        //console.log(error);
                        //console.log("#############");


                        if (error.response.status === 401){ //Significa que usuário e senha estão errados
                            this.log_situacao = "Usuário ou senha inválidos !";
                            setTimeout(() => {
                                this.log_situacao = "";
                            }, 3000);
                        } else { //Se for qualquer outro erro signfica que usuário e senha passou
                            this.sucesso();
                        }

                    });
                }
        ,   sucesso() {

                //Limpa os campos da tela
                this.log_apelido = '';
                this.log_senha = '';
                this.log_situacao = "L O G A D O (Redirecionando...)";

                setTimeout(() => {
                    this.$router.push('/');
                }, 3000)
                
            }
        }
}

</script>

