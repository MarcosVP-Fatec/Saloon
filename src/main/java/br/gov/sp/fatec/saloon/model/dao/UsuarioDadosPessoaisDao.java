package br.gov.sp.fatec.saloon.model.dao;

import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.UsuarioDadosPessoais;

public interface UsuarioDadosPessoaisDao {

    public void salvarUsuarioDadosPessoaisSemCommit(UsuarioDadosPessoais usuarioDadosPessoais);

    public UsuarioDadosPessoais salvarUsuarioDadosPessoais(UsuarioDadosPessoais usuarioDadosPessoais);

    public UsuarioDadosPessoais cadastrarUsuarioDadosPessoais(String apelido
                                                             ,String email
                                                             ,String senha
                                                             ,String nome
                                                             ,Date dtNascimento
                                                             ,String cpf);

    public UsuarioDadosPessoais buscarUsuarioDadosPessoais(Long id);

    public UsuarioDadosPessoais buscarUsuarioDadosPessoais(String apelido);

    public UsuarioDadosPessoais buscarUsuarioDadosPessoaisPorEmail(String email);

    public void removerUsuarioDadosPessoais(Long id);

    public void removerUsuarioDadosPessoais(UsuarioDadosPessoais usuarioDadosPessoais);
}


