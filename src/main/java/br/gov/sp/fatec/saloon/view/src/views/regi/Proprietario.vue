<template>
    <div class="proprietario">
        <div id="proprierario_cabec">
            <!-- <ul>
                <li v-for="(erro, index) of erros" :key="index">
                    Campo <b>{{ erro.field }}</b> - {{ erro.defaultMessage }}
                </li>
            </ul> -->
            <table v-if="this.erro" border="1px" width="500" style="color:white; background-color:red">
                <tbody>
                    <tr>
                        <td><strong>{{ this.erro }}</strong></td>
                    </tr>
                </tbody>
            </table>
            <table v-if="this.msg" border="1px" width="500" style="color:white; background-color:green">
                <tbody>
                    <tr>
                        <td><strong>{{ this.msg }}</strong></td>
                    </tr>
                </tbody>
            </table>

            <form @submit.prevent="cadastrar"> 
                <h2>Proprietário</h2>
                <p>
                    <label for="apelido" size=50>Usuário: </label>
                    <input  type="text"
                            id="apelido"
                            size=50
                            placeholder="Usuário"
                            required autofocus v-model="proprietario.apelido"/>
                </p><p>    
                    <label for="email"   size=50>Email: </label>
                    <input  type="email"
                            id="email" 
                            size=50
                            placeholder="E-mail"
                            required v-model="proprietario.email"/>
                </p><p>
                    <label for="senha" size=50>Senha: </label>
                    <input  type="password"
                            id="senha"
                            size=50
                            placeholder="Senha"
                            required v-model="proprietario.senha"/>
                </p><p>
                    <label for="nome" size=50>Nome completo: </label>
                    <input  type="text"
                            id="nome"
                            size=50
                            placeholder="Nome completo"
                            required v-model="proprietario.nome">
                </p><p>
                    <label for="dtnascimento" size=50>Data de nascimento: </label>
                    <input  type="date"
                            id="dtnascimento"
                            required v-model="proprietario.dtNascimento"/>
                </p><p>
                    <label for="cpf">CPF: </label>
                    <input  type="text"
                            id="cpf"
                            size=50
                            data-mask="000.000.000-00" data-mask-selectonfocus="true" 
                            placeholder="C.P.F."
                            required v-model="proprietario.cpf"/>
                </p><p>
                    <label for="id" size=50>Identificador: </label>
                    <input  type="text"   
                            id="id" 
                            size=50
                            readonly 
                            placeholder="Tela em modo de Inclusão"
                            v-model="proprietario.id"/>
                </p>

                <button type="submit" style="height:30px; width:120px; border-radius:25px;">Salvar</button>&nbsp;&nbsp;
                <button type="button" style="height:30px; width:120px; border-radius:25px;" @click="novo">Novo</button>
                
            </form>
        </div>

        <div class="proprietario-tabela">    
        <!-- <button type="button" style="height:30px; width:120px; border-radius:25px;" click="atualizarLista">Atualizar Lista</button> -->
        <table border="1px">
            <thead>
                <!-- <th width="050">Cód</th> -->
                <th width="150">Usuário</th>
                <th width="300">Nome Completo</th>
                <th width="300">CPF</th>
                <th>Edição</th>
            </thead>
            <tbody>
                <tr v-for="prop in proprietarios" :key="prop.id">
                    <!-- <td width="050">{{ prop.id              }}</td> -->
                    <td width="150">{{ prop.apelido         }}</td>
                    <td width="300">{{ prop.nome            }}</td>
                    <td width="300">{{ maskCPF(prop.cpf)    }}</td>
                    <td>
                        <button @click="editar(prop)"  class="btn_editar"> <i>editar</i> </button>
                        <button @click="excluir(prop)" class="btn_excluir"><i>excluir</i></button>
                    </td>
                </tr>
            </tbody>
        </table>
        </div>
    </div>
</template>

<script>
import axios        from 'axios';
import { mapState } from 'vuex';

export default {
    name: 'app-proprietario',
    mounted() {
        this.atualizarLista()
    },
    data() {
        return { 
            proprietario: {
                              id:           ''
                            , apelido:      ''
                            , email:        ''
                            , senha:        ''
                            , nome:         ''
                            , dtNascimento: ''
                            , cpf:          ''
                            }
            , proprietarios: []
            , erro: null
            , msg: null
        }
    },
    computed: {
        ...mapState([
            'login_usuario',
            'login_senha'
        ])
    },
    methods: {
        atualizarLista(){
                // 1º parâmetro = rota
                // 2º parâmetro = json
                this.mostraErro(null);
                axios.get('proprietario'
                ).then( res => {
                    console.log(res);
                    this.proprietarios = res.data;
                }).catch( error => { this.trataErro(error); });
        },
        maskCPF(cpf){
            return cpf[0]+cpf[1]+cpf[2]+"."+cpf[3]+cpf[4]+cpf[5]+"."+cpf[6]+cpf[7]+cpf[8]+"-"+cpf[9]+cpf[10];
        },
        novo(){
            this.proprietario = {};
            this.mostraErro(null);
            this.focoNoPrimeiroCampo();
            this.mostraMsg("Inclua novo proprietário")
        },
        editar(proprietario){
            this.mostraErro(null);
            this.proprietario = proprietario;
            proprietario.senha = "";
            if (proprietario.dtNascimento){
                this.proprietario.dtNascimento = new Date(proprietario.dtNascimento).toJSON().substring(0,10);
            }
            this.focoNoPrimeiroCampo();
            this.mostraMsg("Alteração de cadastro de proprietário",3)
        },
        excluir(proprietario){
            this.mostraErro(null);
            if (proprietario.id){
                if (confirm("Deseja mesmo excluir o proprietário " + proprietario.nome + " ?")){
                    axios.delete('proprietario/'+proprietario.id, proprietario
                    ).then( res => {
                        console.log(res);
                        this.atualizarLista();
                        this.mostraMsg("Proprietário excluído!",1);
                    })
                }
                this.novo()
            } else {
                this.mostraErro("Nenhum registro foi excluído!",1);
            }
        },
        focoNoPrimeiroCampo(){
            document.getElementById("apelido").focus(); 
        },
        cadastrar() {
            // 1º parâmetro = rota
            // 2º parâmetro = json
            // 3º parãmetro = propriedades= autenticação.
            this.mostraErro(null);
            if (this.proprietario.id){ //PUT
                axios.put('proprietario' , this.proprietario 
                ).then( res => {
                    console.log(res);
                    this.proprietario.id = res.data.id;
                    this.novo;
                }).catch( error => { this.trataErro(error); });

            } else {   //POST
                axios.post('proprietario' , this.proprietario
                ).then( res => {
                    console.log(res);
                    this.proprietario.id = res.data.id;
                    this.proprietarios.push(res.data);
                    this.novo;
                }).catch( error => { this.trataErro(error); });
            }
        },
        trataErro( error ){
            if (error){
                if (error.response) { // Request made and server responded
                    console.error("### ERROR RESPONSE");
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                    this.mostraErro(error.response.data.message,60);
                } else if (error.request) { // The request was made but no response was received
                    console.error("### ERROR REQUEST");
                    console.log(error.request);
                    console.log(error.request.message);
                } else { // Something happened in setting up the request that triggered an Error
                    console.error("### ERROR MESSAGE");
                    console.log('Error', error.message);
                }
            }
        },
        mostraErro( texto , segundos ){
            this.erro = texto;
            if (texto){
                if (!segundos) {segundos = 1}
                setTimeout(() => {
                    this.erro = null;
                }, segundos*1000);
            }
        },
        mostraMsg( texto , segundos ){
            this.msg = texto;
            if (texto){
                if (!segundos) {segundos = 1}
                setTimeout(() => {
                    this.msg = null;
                }, segundos*1000);
            }
        }

    }
}

</script>

<style>
    .proprietario {
        display: flex;
        align-items: center;
        flex-direction: column;
    }
    .proprietario-tabela {
        display: flex;
        margin-top: 50px;
    }

    .btn_editar {
        font-weight: bold;
        float: left;
        color: rgb(255, 255, 255);
		background-color: rgb(21, 20, 94);
    }

    .btn_editar:hover {
        color: #330000;
        background-color: #FFFFFF;
        /*CSS 3*/
        border-radius: 10px 0px 10px 0px;
    }

    .btn_excluir {
        font-weight: bold;
        float: left;
        color: rgb(255, 255, 255);
		background-color: rgb(116, 28, 21);
    }

    .btn_excluir:hover {
        color: #330000;
        background-color: #FFFFFF;
        /*CSS 3*/
        border-radius: 10px 0px 10px 0px;
    }

</style>
