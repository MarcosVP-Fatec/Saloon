package br.gov.sp.fatec.saloon.model.service.regi;

import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;

public interface UsuarioService {

    /**
     * @apiNote inc() --> Inclui um usuário passando os seus campos
     * @param apelido
     * @param email
     * @param senha
     * @param nome
     * @param dtNascimento
     * @param cpf
     * @param usuarioNivel
     * @return Usuario
     */
    public Usuario inc( String apelido
                      , String email
                      , String senha
                      , String nome
                      , Date   dtNascimento
                      , String cpf
                      , Long   usuarioNivel);


    /**
     * @apiNote alt() --> Altera um usuário passando os seus campos
     * @param id
     * @param apelido
     * @param email
     * @param senha
     * @param nome
     * @param dtNascimento
     * @param cpf
     * @param usuarioNivel
     * @return Usuario
     */
    public Usuario alt( Long   id
                      , String apelido
                      , String email
                      , String senha
                      , String nome
                      , Date   dtNascimento
                      , String cpf
                      , Long   usuarioNivel);

    /**
     * @apiNote del() --> Exclui um usuário passando o id
     * @param id (Long)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean del(Long id);

    /**
     * @apiNote del() --> Exclui um usuário passando o apelido
     * @param apelido (String)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean del(String apelido);
    
}
