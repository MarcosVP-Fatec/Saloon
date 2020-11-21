package br.gov.sp.fatec.saloon.model.dao.interf;

import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;

public interface UsuarioDao {

    public Usuario buscar(Long id);

    public Usuario buscar(String apelido);

    public Usuario buscarPorEmail(String email);

    public boolean existe(Long id);

    public boolean existe(String apelido);

    public boolean existeEmail(String email);
    
}