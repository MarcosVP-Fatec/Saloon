<template>
  <div class="home">
    <h1>S A L O O N</h1>

    <h2>Página está em construção</h2>

    <p><img align="center" alt="S A L O O N" src="../../assets/saloon.gif"></P>

    <button @click="voltar">Voltar para o início</button>
  </div>
</template>

<script>
export default {
    methods: {
        voltar(){
            this.$router.push('/');
        }
    }
}
</script>

