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
        name: 'About'
    ,   data() {
            return {
                log_apelido: ''
            ,   log_senha: ''
            ,   log_situacao: 'Não logado!'
            }
        }
    ,   methods: {
            ...mapMutations([
                'setUsuario', 'setSenha', 'setMomento'
            ]),
            ...mapGetters([
                'isLogVencido',
                'getUsuario'
            ])
        ,   login() {
                    axios.get('usuario',
                             { params:     { apelido: this.log_apelido},
                               headers:    { accept: 'application/json'}, //Quero receber um json
                               auth:       { username: this.log_apelido, password: this.log_senha } //Minha autenticação
                            }
                    ).then( res => { //Se deu tudo certo funcionou usuário e senha

                        //alert("Entreio no RES"); //PAREI AQUI
                        console.log(res);
                        this.sucesso();

                    }).catch(error => {
                        
                        console.log(error);
                        //alert("Entrei no ERROR"); //parei aqui
                        //alert("error.response.status => " + error.response.status) //parei aqui

                        if (error.response){

                            // A solicitação foi feita e o servidor respondeu com um código de status
                            // console.log(error.response.data);
                            // console.log(error.response.status);
                            // console.log(error.response.headers);

                            if (error.response.status === 401){ //Significa que usuário e senha estão errados
                                alert('Usuário ou senha inválidos !');
                            } else { //Se for qualquer outro erro signfica que usuário e senha passou
                                this.sucesso();
                            }

                        } else if (error.request) {

                            // A solicitação foi feita, mas nenhuma resposta foi recebida
                            alert('Erro na solicitação de autenticação! Tente novamente.' + error.request.response);

                        } else {

                            // Algo aconteceu com a confituração da solicitação que disparou um erro.
                            alert('Erro na solicitação de autenticação! Tente novamente.');

                        }
                        
                        console.log(error.config);

                    });
                }
        ,   sucesso() {
                this.setUsuario(this.log_apelido);
                this.setSenha(this.log_senha);
                this.setMomento();
                this.log_apelido = '';
                this.log_senha = '';
                this.log_situacao = "L O G A D O";
                
            }
        }
}

</script>

