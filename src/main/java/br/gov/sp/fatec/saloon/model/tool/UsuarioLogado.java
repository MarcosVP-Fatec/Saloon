package br.gov.sp.fatec.saloon.model.tool;

import br.gov.sp.fatec.saloon.model.dao.UsuarioDaoJpa;
import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;

public class UsuarioLogado {
    
    // Instanciamos a classe dentro dela mesma
    private static UsuarioLogado instance;

    //Atributos que serão instanciados
    //protected Long id;
    protected Usuario usuario;

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

    public static void logar( Usuario usuario ) {
        getInstance().usuario = usuario; 
    }

    public static boolean isUsuarioLogado( String userName, String userPW){
        
        if ( getInstance().usuario == null ) {
            return false; 
        } else if (   getUsuarioLogado() != null 
                   && getUsuarioLogado().getApelido().equals(userName)
                   && getUsuarioLogado().getSenha().equals(userPW) ){
            return true;
        }

        Usuario usuario = new UsuarioDaoJpa().buscar(userName);
        if (usuario == null || !usuario.getApelido().equals(userName) || !usuario.getSenha().equals(userPW)) return logOff();

        logar(usuario);
        return true;
        
    }

    public static boolean logOff(){
        getInstance().usuario = null;
        return false;
    }

    public static Long getUsuarioLogadoId(){
        if (getInstance().usuario == null) return null;
        return getInstance().usuario.getId();
    }

    public static Usuario getUsuarioLogado(){
        return getInstance().usuario;
    }
}