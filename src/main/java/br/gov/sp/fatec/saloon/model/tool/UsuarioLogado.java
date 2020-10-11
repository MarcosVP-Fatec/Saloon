package br.gov.sp.fatec.saloon.model.tool;

public class UsuarioLogado {
    
    // Instanciamos a classe dentro dela mesma
    private static UsuarioLogado usuario;

    //Atributos que serão instanciados
    protected Long id;

    /**
     * O construtor é privado, portanto esta classe não pode ser instanciada
     */
    private UsuarioLogado () { }

    /**
     * O método getInstance() é a essência do singleton
     * Quando este método é executado verifica se a instância é nula. Se for nula ele é instanciado
     * e coloca na variável instance.
     * @return instance
     */
    public static UsuarioLogado getInstance( Long id ) {
        if (usuario == null) usuario = new UsuarioLogado();
        usuario.setId(id);
        return usuario;
    }

    public static UsuarioLogado getInstance() {
        if (usuario == null) usuario = new UsuarioLogado();
        return usuario;
    }

    public Long getId() {
        return this.id;
    }

    public void setId( Long id ) {
        if (this.getId() == null && id != 0){
            this.id = id;
        }
    }

}