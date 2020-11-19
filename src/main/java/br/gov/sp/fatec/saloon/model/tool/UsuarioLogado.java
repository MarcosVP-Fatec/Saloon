package br.gov.sp.fatec.saloon.model.tool;

import br.gov.sp.fatec.saloon.model.entity.regi.UsuarioDadosPessoais;

public class UsuarioLogado {
    
    // Instanciamos a classe dentro dela mesma
    private static UsuarioLogado instance;

    //Atributos que serão instanciados
    //protected Long id;
    protected UsuarioDadosPessoais usuario;

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
    public static UsuarioLogado getInstance() {
        if (instance == null) instance = new UsuarioLogado();
        return instance;
    }

    public static void logar( UsuarioDadosPessoais usuario ) {
        if (getInstance().usuario == null ) getInstance().usuario = usuario; 
    }

    public static boolean isUsuarioLogado(){
        return getInstance().usuario != null; 
    }
    public static void logOff(){
        getInstance().usuario = null;
    }

    public static Long getUsuarioLogadoId(){
        if (getInstance().usuario == null) return null;
        return getInstance().usuario.getId();
    }

    public static UsuarioDadosPessoais getUsuarioLogado(){
        if (getInstance().usuario == null) return null;
        return getInstance().usuario;
    }
}