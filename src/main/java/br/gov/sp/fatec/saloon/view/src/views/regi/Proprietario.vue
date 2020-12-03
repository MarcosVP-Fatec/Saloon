<template>
    <div class="proprietario">
        <div id="proprierario_cabec">
            <form @submit.prevent="cadastrar"> 
                <h2>Proprietário</h2>
                <p>
                    <label for="apelido">Apelido: </label>
                    <input type="text" id = "apelido" required autofocus v-model="apelido">
                </p><p>    
                    <label for="email">Email: </label>
                    <input type="email" id = "email" required v-model="email">
                </p><p>
                    <label for="senha">Senha: </label>
                    <input type="password" id = "senha" required v-model="senha">
                </p><p>
                    <label for="nome">Nome completo: </label>
                    <input type="text" id = "nome" required v-model="nome">
                </p><p>
                    <label for="dtnascimento">Data de nascimento: </label>
                    <input type="date" id = "dtnascimento" required v-model="dtnascimento">
                </p><p>
                    <label for="cpf">CPF: </label>
                    <input type="text" id = "cpf" required v-model="cpf">
                </p>
                <button type="submit">Salvar</button>
            </form>
        </div>

        <div class="proprietario-tabela">    
        <table>
            <thead>
                <th>Id</th>
                <th>Nome</th>
            </thead>
            <tbody>
                <tr v-for="prop in proprietarios" :key="prop.id">
                    <td>{{ prop.id              }}</td>
                    <td>{{ prop.nome            }}</td>
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
              apelido:      ''
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
