<template> 
  <!--  Quando se usa um form no vue colocamos um @submit.prevent
        Um formulário quando é submetido envia os dados para o back end e recarrega a tela, mas neste caso 
        não queremos enviar os dados para o back-end nem que recarregue a tela. Este comando vai chamar
        um método de login. -->
  <div>
    <div v-if="this.isLogVencido">
        <form @submit.prevent="login">
            <h2>Login</h2>
            <p>
                <label for="username">Usuário: </label>
                <input type="text"     id="username"      required autofocus v-model="log_apelido"/>
            </p>
            <p>
                <label for="inputPassword">Senha: </label>
                <input type="password" id="inputPassword" required           v-model="log_senha"/>
            </p>
            <button type="submit">Ok</button>
        </form>
    </div>
    <div v-if="!this.isLogVencido">
        <h1>Você já está logado!</h1>
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
                log_apelido: ''
            ,   log_senha: ''
            }
        }
    ,   methods: {
            ...mapMutations([
                'setUsuario', 'setSenha', 'setMomento'
            ]),
            ...mapGetters([
                'isLogVencido'
            ])
        ,   login() {
                    axios.get('usuario',
                             { params:     { apelido: this.log_apelido},
                               headers:    { accept: 'application/json'}, //Quero receber um json
                               auth:       { username: this.log_apelido, password: this.log_senha } //Minha autenticação
                            }
                    ).then( res => { //Se deu tudo certo funcionou usuário e senha
                        console.log('############################ AXIOS SUCCESS');
                        console.log(res);
                        console.log('############################');
                        this.sucesso();

                    }).catch(error => {
                        
                        console.log('############################ AXIOS ERROR');

                        if (error.response){

                            // A solicitação foi feita e o servidor respondeu com um código de status
                            console.log(error.response.data);
                            console.log(error.response.status);
                            console.log(error.response.headers);

                            if (error.response.status === 401){ //Significa que usuário e senha estão errados
                                alert('Usuário ou senha inválidos');
                                this.$router.push('/logar');
                            } else { //Se for qualquer outro erro signfica que usuário e senha passou
                                this.sucesso();
                                alert('Logar.vue (else/sucesso) = '+this.$router.to.path);
                                this.$router.push(this.$router.to.path);
                            }

                        } else if (error.request) {

                            // A solicitação foi feita, mas nenhuma resposta foi recebida
                            alert('Erro na solicitação de autenticação! Tente novamente.');
                            this.$router.push('/logar');

                        } else {

                            // Algo aconteceu com a confituração da solicitação que disparou um erro.
                            alert('Erro na solicitação de autenticação! Tente novamente.');
                            this.$router.push('/logar');

                        }
                        
                        console.log(error.config);

                    });
                }
        ,   sucesso() {
                this.setUsuario(this.log_apelido);
                this.setSenha(this.log_senha);
                this.setMomento();
                this.$router.push('/home'); //Necessário criar uma rota proprietario
                alert("Logar.vue (sucesso) => " + this.$router.axios.to.name);
                
            }
        }
}
</script>
