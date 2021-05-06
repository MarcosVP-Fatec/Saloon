package br.gov.sp.fatec.saloon.service.regi;

import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;

public interface ProprietarioService {

    public Proprietario inc(Proprietario proprietario);

    public Proprietario inc( String apelido
				            , String email
				            , String senha
				            , String nome
				            , Date   dtNascimento
				            , String cpf);

    public Proprietario alt( String apelido
				            , String email
				            , String senha
				            , String nome
				            , Date   dtNascimento
				            , String cpf);

    /**
     * @apiNote persist(...)
     *          Inclui/Altera um proprietário passando os seus campos
     * @param id (Long)
     * @param apelido (String)
     * @param email (String)
     * @param senha (String)
     * @param nome  (String)
     * @param dtNascimento (Date)
     * @param cpf (String)
     * @param usuarioNivel (Long)
     * @return Proprietario
     */
    public Proprietario persist( Long   id
                               , String apelido
                               , String email
                               , String senha
                               , String nome
                               , Date   dtNascimento
                               , String cpf);

    public Proprietario persist( String apelido
                               , String email
                               , String senha
                               , String nome
                               , Date   dtNascimento
                               , String cpf);

    /**
     * @apiNote delete(Long)
     *          Exclui um proprietário passando o id
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

    public Proprietario buscaPorApelido(String apelidoUsuario);
}
