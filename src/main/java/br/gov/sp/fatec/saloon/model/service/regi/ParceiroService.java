package br.gov.sp.fatec.saloon.model.service.regi;

import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.regi.Parceiro;

public interface ParceiroService {

    /**
     * @apiNote inc() --> Inclui um parceiro passando os seus campos
     * @param apelido
     * @param email
     * @param senha
     * @param nome
     * @param dtNascimento
     * @param cpf
     * @param usuarioNivel
     * @return Parceiro
     */
    public Parceiro inc( String apelido
                       , String email
                       , String senha
                       , String nome
                       , Date   dtNascimento
                       , String cpf);

    /**
     * @apiNote alt() --> Altera um parceiro passando os seus campos
     * @param id
     * @param apelido
     * @param email
     * @param senha
     * @param nome
     * @param dtNascimento
     * @param cpf
     * @param usuarioNivel
     * @return Parceiro
     */
    public Parceiro alt( Long   id
                       , String apelido
                       , String email
                       , String senha
                       , String nome
                       , Date   dtNascimento
                       , String cpf);

    /**
     * @apiNote del() --> Exclui um parceiro passando o id
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
