package br.gov.sp.fatec.saloon.model.dao;

import br.gov.sp.fatec.saloon.model.entity.UsuarioDadosPessoais;

public interface UsuarioDadosPessoaisDao {

    public void salvarUsuarioDadosPessoaisSemCommit(UsuarioDadosPessoais usuarioDadosPessoais);

    public UsuarioDadosPessoais salvarUsuarioDadosPessoais(UsuarioDadosPessoais UsuarioDadosPessoais);

    public UsuarioDadosPessoais cadastrarUsuarioDadosPessoais();

    public UsuarioDadosPessoais buscarUsuarioDadosPessoaisPorId(String id);

    public UsuarioDadosPessoais buscarUsuarioDadosPessoaisPorApelido(String apelido);

    public UsuarioDadosPessoais buscarUsuarioDadosPessoaisPorNome(String nome);

    public void removerUsuarioDadosPessoaisPorId(Long id);

}


