package br.gov.sp.fatec.saloon.model.dao.interf;

import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.regi.UsuarioDadosPessoais;

public interface UsuarioDadosPessoaisDao {

    public UsuarioDadosPessoais salvar(UsuarioDadosPessoais usuarioDadosPessoais);

    public UsuarioDadosPessoais cadastrar(String apelido
                                         , String email
                                         , String senha
                                         , String nome
                                         , Date   dtNascimento
                                         , String cpf
                                         , Long   nivelUsuario);

    public UsuarioDadosPessoais buscar(Long id);

    public UsuarioDadosPessoais buscar(String apelido);

    public UsuarioDadosPessoais buscarPorEmail(String email);

    public boolean remover(Long id);

    public boolean remover(UsuarioDadosPessoais usuarioDadosPessoais);

    public boolean existe(Long id);

    public boolean existe(String apelido);

    public boolean existePorEmail(String email);

}
