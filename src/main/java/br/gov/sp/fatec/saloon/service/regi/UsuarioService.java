package br.gov.sp.fatec.saloon.service.regi;

import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;

public interface UsuarioService {

    /**
     * @apiNote persist(...) --> Inclui/Altera um usuário passando os seus campos
     * @param id (Long)
     * @param apelido (String)
     * @param email (String)
     * @param senha (String)
     * @param nome (String)
     * @param dtNascimento (Date)
     * @param cpf (String)
     * @param usuarioNivel Long
     * @return Usuario
     */
    public Usuario persist( Long   id
                          , String apelido
                          , String email
                          , String senha
                          , String nome
                          , Date   dtNascimento
                          , String cpf
                          , Long   usuarioNivel);

    public Usuario persist( String apelido
                          , String email
                          , String senha
                          , String nome
                          , Date   dtNascimento
                          , String cpf
                          , Long   usuarioNivel);
    
    /**
     * @apiNote delete(Long) --> Exclui um usuário passando o id
     * @param id (Long)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean delete(Long id);

    /**
     * @apiNote delete(String) --> Exclui um usuário passando o apelido
     * @param apelido (String)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean delete(String apelido);

    public Usuario buscarUsuarioPorId(Long id);
    
}
