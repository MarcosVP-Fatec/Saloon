<template>
    <div class="proprietario">
        <div id="proprierario_cabec">
            <form @submit.prevent="cadastrar"> 
                <h2>Proprietário</h2>
                <p>
                    <label for="id" size=50>Identificador: </label>
                    <input  type="text"   
                            id="id" 
                            size=50
                            disable=""
                            placeholder="Tela em modo de Inclusão"
                            v-model="id"/>
                </p><p>    
                <p>
                    <label for="        apelido" size=50>Apelido: </label>
                    <input  type="text"
                            id="apelido"
                            size=50
                            placeholder="Apelido"
                            required autofocus v-model="apelido"/>
                </p><p>    
                    <label for="email"   size=50>Email: </label>
                    <input  type="email"
                            id="email" 
                            size=50
                            placeholder="E-mail"
                            required v-model="email"/>
                </p><p>
                    <label for="senha" size=50>Senha: </label>
                    <input  type="password"
                            id="senha"
                            size=50
                            placeholder="Senha"
                            required v-model="senha"/>
                </p><p>
                    <label for="nome" size=50>Nome completo: </label>
                    <input  type="text"
                            id="nome"
                            size=50
                            placeholder="Nome completo"
                            required v-model="nome">
                </p><p>
                    <label for="dtnascimento" size=50>Data de nascimento: </label>
                    <input  type="date"
                            id="dtnascimento"
                            required v-model="dtnascimento"/>
                </p><p>
                    <label for="cpf">CPF: </label>
                    <input  type="text"
                            id="cpf"
                            size=50
                            data-mask="000.000.000-00" data-mask-selectonfocus="true" 
                            placeholder="C.P.F."
                            required v-model="cpf"/>
                </p>
                <button type="submit" style="height:20px; width:100px; border-radius:25px;">Salvar</button>
                <button type="submit" style="height:20px; width:100px; border-radius:25px;">Excluir</button>
                
            </form>
        </div>

        <div class="proprietario-tabela">    
        <table border="1px">
            <thead>
                <th width="050">Cód</th>
                <th width="100">Login</th>
                <th width="300">Nome Completo</th>
            </thead>
            <tbody>
                <tr v-for="prop in proprietarios" :key="prop.id">
                    <td width="050">{{ prop.id              }}</td>
                    <td width="100">{{ prop.apelido         }}</td>
                    <td width="300">{{ prop.nome            }}</td>
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
    data() {
        return {
                 id: ''
               , apelido:      ''
               , email:        ''
               , senha:        ''
               , nome:         ''
               , dtnascimento: ''
               , cpf:          ''
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
        cadastrar() {
            // 1º parâmetro = rota
            // 2º parâmetro = json
            // 3º parãmetro = propriedades= autenticação.
            if (this.id > 0){ //PUT
                axios.put('proprietario',
                    {       id:             this.id
                        ,   apelido:        this.apelido
                        ,   email:          this.email
                        ,   senha:          this.senha
                        ,   nome:           this.nome
                        ,   dtNascimento:   this.dtnascimento
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
                    this.apelido='';
                    this.email='';
                    this.senha='';
                    this.nome='';
                    this.cpf='';
                    this.proprietarios.push(res.data);
                }).catch( error => console.log(error));
            } else {   //POST
                axios.post('proprietario',
                    {
                            apelido:        this.apelido
                        ,   email:          this.email
                        ,   senha:          this.senha
                        ,   nome:           this.nome
                        ,   dtNascimento:   this.dtnascimento
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
                    this.apelido='';
                    this.email='';
                    this.senha='';
                    this.nome='';
                    this.cpf='';
                    this.proprietarios.push(res.data);
                }).catch( error => console.log(error));
            }
        },
        getProprietarios(){
            // 1º parâmetro = rota
            // 2º parâmetro = json
            // 3º parãmetro = propriedades= autenticação.
            axios.post('proprietario',
                {
                        id: 4
                },
                {
                    auth: {
                        username: this.login_usuario,
                        password: this.login_senha
                    }
                }
            ).then( res => {
                console.log(res);
                alert("VER RES.DATA");
                alert(res.data);
                this.proprietarios.push(res.data);
            }).catch( error => console.log(error))
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
</style>
