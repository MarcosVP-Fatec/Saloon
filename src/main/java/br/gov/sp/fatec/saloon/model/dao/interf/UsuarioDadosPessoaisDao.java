package br.gov.sp.fatec.saloon.model.dao.interf;

import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.regi.UsuarioDadosPessoais;

public interface UsuarioDadosPessoaisDao {

    public UsuarioDadosPessoais salvarUsuarioDadosPessoais(UsuarioDadosPessoais usuarioDadosPessoais);

    public UsuarioDadosPessoais cadastrarUsuarioDadosPessoais(String apelido
                                                             ,String email
                                                             ,String senha
                                                             ,String nome
                                                             ,Date dtNascimento
                                                             ,String cpf);

    public UsuarioDadosPessoais buscarUsuarioDadosPessoais(Long id);

    public UsuarioDadosPessoais buscarUsuarioDadosPessoais(String apelido );

    public UsuarioDadosPessoais buscarUsuarioDadosPessoaisPorEmail(String email);

    public boolean removerUsuarioDadosPessoais(Long id);

    public boolean removerUsuarioDadosPessoais(UsuarioDadosPessoais usuarioDadosPessoais);
}

