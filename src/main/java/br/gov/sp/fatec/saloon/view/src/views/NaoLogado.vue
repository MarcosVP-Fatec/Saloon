<template> 
    <form>
        <h2>Login</h2>
        <a href="voltar">Você não está logado! Tentar Novamente</a>
    </form>
</template>

<script>

export default {
        name: 'NaoLogado'
    ,   methods: {
            voltar() { this.$router.push('/'); }
        }
}
</script>
