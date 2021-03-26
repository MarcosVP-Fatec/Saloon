package br.gov.sp.fatec.saloon.model.service.regi;

import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.regi.Parceiro;

public interface ParceiroService {

    /**
     * @apiNote persist(...)
     *          Inclui/Altera um parceiro passando os seus campos
     * @param id (Long)
     * @param apelido (String)
     * @param email (String)
     * @param senha (String)
     * @param nome (String)
     * @param dtNascimento (Date)
     * @param cpf (String)
     * @param usuarioNivel (Long)
     * @return Parceiro
     */
    public Parceiro persist( Long   id
                           , String apelido
                           , String email
                           , String senha
                           , String nome
                           , Date   dtNascimento
                           , String cpf);

    public Parceiro persist( String apelido
                           , String email
                           , String senha
                           , String nome
                           , Date   dtNascimento
                           , String cpf);
    /**
     * @apiNote delete(Long)
     *          Exclui um parceiro passando o id
     * @param id (Long)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean delete(Long id);

    /**
     * @apiNote delete(String)
     *          Exclui um usuário passando o apelido
     * @param apelido (String)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean delete(String apelido);
    
}
