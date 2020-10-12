package br.gov.sp.fatec.saloon.model.dao;

import br.gov.sp.fatec.saloon.model.entity.UsuarioDadosPessoais;

public interface UsuarioDadosPessoaisDao {

    public UsuarioDadosPessoais cadastrarUsuarioDadosPessoais();

    public UsuarioDadosPessoais salvarUsuarioDadosPessoaisSemCommit(UsuarioDadosPessoais UsuarioDadosPessoais);

    public UsuarioDadosPessoais salvarUsuarioDadosPessoais(UsuarioDadosPessoais UsuarioDadosPessoais);

    public UsuarioDadosPessoais buscarUsuarioDadosPessoaisPorRa(Long ra);

    public void removerUsuarioDadosPessoaisPorRa(Long ra);


}


