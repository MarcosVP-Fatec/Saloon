<template> 
  <!--  Quando se usa um form no vue colocamos um @submit.prevent
        Um formulário quando é submetido envia os dados para o back end e recarrega a tela, mas neste caso 
        não queremos enviar os dados para o back-end nem que recarregue a tela. Este comando vai chamar
        um método de login. -->
  <div>
    <div v-if="this.isLogado">
        <form @submit.prevent="login">
            <h2>Login</h2>
            <p>
                <label for="username">Usuário: </label>
                <input type="text" id="username" required autofocus v-model="login_nome"/>
            </p>
            <p>
                <label for="inputPassword">Senha: </label>
                <input type="password" id="inputPassword" required v-model="login_senha"/>
            </p>
            <button type="submit">Ok</button>
        </form>
    </div>
    <div v-if="!this.isLogado">
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { mapMutations } from 'vuex'; //Na store colocar usuário e senha para controlar o login.
import { mapGetters   } from 'vuex'; 

export default {
        name: 'About'
    ,   data() {
            return {
                login_nome: ''
            ,   login_senha: ''
            }
        }
    ,   methods: {
            ...mapMutations([
                'setUsuario', 'setSenha', 'setLogado'
            ]),
            ...mapGetters([
                'isLogado'
            ])
        ,   login() {
                    this.setLogado(false);
                    axios.get('usuario',
                             { params:     { id: 1},
                               headers:    { accept: 'application/json'}, //Quero receber um json
                               auth:       { username: this.login_nome, password: this.login_senha } //Minha autenticação
                            }
                    ).then( res => { //Se deu tudo certo funncionou usuário e senha
                        console.log(res);
                        this.sucesso();
                    }).catch(error => {
                        console.log(error);
                        if (error.response.status === 401){ //Significa que usuári oe senha estão errados
                            console.log('Usuário ou senha inváidos!');
                            this.$router.push('/naologado');
                        } else { //Se for qualquer outro erro signfica que usuário e senha passou
                            this.sucesso();
                        }
                    });
                }
        ,   sucesso() {
                this.setUsuario(this.login_nome);
                this.setSenha(this.login_senha);
                this.setLogado(true);
                this.$router.push('/home'); //Necessário criar uma rota proprietario
            }
        }
}
</script>
