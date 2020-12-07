<template>
    <div class="proprietario">
        <div id="proprierario_cabec">
            <form @submit.prevent="cadastrar"> 
                <h2>Proprietário</h2>
                <p>
                    <label for="apelido" size=50>Apelido: </label>
                    <input  type="text"
                            id="apelido"
                            size=50
                            placeholder="Apelido"
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

                <button type="submit" style="height:30px; width:120px; border-radius:25px;">Salvar</button>
                <button type="button" style="height:30px; width:120px; border-radius:25px;" @click="novo">Novo</button>
                <!-- <button type="submit" style="height:30px; width:120px; border-radius:25px;">Excluir</button> -->
                
            </form>
        </div>

        <div class="proprietario-tabela">    
        <!-- <button type="button" style="height:30px; width:120px; border-radius:25px;" click="atualizarLista">Atualizar Lista</button> -->
        <table border="1px">
            <thead>
                <th width="050">Cód</th>
                <th width="150">Login</th>
                <th width="300">Nome Completo</th>
                <th>Edição</th>
            </thead>
            <tbody>
                <tr v-for="prop in proprietarios" :key="prop.id">
                    <td width="050">{{ prop.id              }}</td>
                    <td width="150">{{ prop.apelido         }}</td>
                    <td width="300">{{ prop.nome            }}</td>
                    <td>
                        <button @click="editar(prop)" class="btn_editar"> <i>editar</i> </button>
                        <button class="btn_excluir"><i>excluir</i></button>
                    </td>
                </tr>
            </tbody>
        </table>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
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
                // 3º parãmetro = propriedades= autenticação.
                axios.get('proprietario',
                    { auth: { username: this.login_usuario , password: this.login_senha } }
                ).then( res => {
                    console.log(res);
                    this.proprietarios = res.data;
                }).catch( error => console.log(error))
        },
        novo(){
            this.proprietario = {};
            this.focoNoPrimeiroCampo();
        },
        editar(proprietario){
            this.proprietario = proprietario;
            if (proprietario.dtNascimento){
                this.proprietario.dtNascimento = new Date(proprietario.dtNascimento).toJSON().substring(0,10);
            } else {
                proprietario.dtNascimento = null;
            }
            this.focoNoPrimeiroCampo();
        },
        focoNoPrimeiroCampo(){
            document.getElementById("apelido").focus(); 
        },
        cadastrar() {
            // 1º parâmetro = rota
            // 2º parâmetro = json
            // 3º parãmetro = propriedades= autenticação.
            if (this.proprietario.id){ //PUT
                axios.put('proprietario',
                    {       id:             this.proprietario.id
                        ,   apelido:        this.proprietario.apelido
                        ,   email:          this.proprietario.email
                        ,   senha:          this.proprietario.senha
                        ,   nome:           this.proprietario.nome
                        ,   dtNascimento:   this.proprietario.dtNascimento
                        ,   cpf:            this.proprietario.cpf
                    },
                    {
                        auth: {
                            username: this.login_usuario,
                            password: this.login_senha
                        }
                    }
                ).then( res => {
                    console.log(res);
                    this.proprietario.id = res.data.id;
                    this.novo;
                }).catch( error => console.log(error));
            } else {   //POST
                axios.post('proprietario',
                    {
                            apelido:        this.apelido
                        ,   email:          this.email
                        ,   senha:          this.senha
                        ,   nome:           this.nome
                        ,   dtNascimento:   this.dtNascimento
                        ,   cpf:            this.cpf
                    },
                    {
                        auth: {
                            username: this.login_usuario,
                            password: this.login_senha
                        }
                    }
                ).then( res => {
                    console.log(res);
                    this.proprietario.id = res.data.id;
                    this.proprietarios.push(res.data);
                    this.novo;
                }).catch( error => console.log(error));
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
