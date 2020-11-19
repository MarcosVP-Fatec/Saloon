package br.gov.sp.fatec.saloon.model.dao.interf.stat;

import br.gov.sp.fatec.saloon.model.entity.stat.UsuarioNivel;

public interface UsuarioNivelDao {

    public UsuarioNivel salvar(UsuarioNivel usuarioNivel);

    public UsuarioNivel cadastrar( String descr );

    public UsuarioNivel buscar(Long id);

    public boolean remover(Long id);

    public boolean remover(UsuarioNivel usuarioNivel);
    
}