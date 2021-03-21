package br.gov.sp.fatec.saloon.model.service.stat;

import br.gov.sp.fatec.saloon.model.entity.stat.UsuarioNivel;

public interface UsuarioNivelService {
    
    /**
     * @apiNote inc() --> Inclui um nível de usuário passando os seus campos
     * @param id
     * @param descr
     * @param administrador
     * @param proprietario
     * @param parceiro
     * @param cliente
     * @return UsuarioNivel
     */
    public UsuarioNivel inc(Long id, String descr, boolean administrador, boolean proprietario, boolean parceiro, boolean cliente);

    /**
     * @apiNote alt() --> Altera um nível de usuário passando os seus campos
     * @param id
     * @param descr
     * @param administrador
     * @param proprietario
     * @param parceiro
     * @param cliente
     * @return UsuarioNivel
     */
    public UsuarioNivel alt(Long id, String descr, boolean administrador, boolean proprietario, boolean parceiro, boolean cliente);

    /**
     * @apiNote del() --> Exclui um nível de usuário passando o id
     * @param id (Long)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean del(Long id);

    /**
     * @apiNote del() --> Exclui um nível de usuário passando a descrição
     * @param descr (String)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean del(String descr);

}
