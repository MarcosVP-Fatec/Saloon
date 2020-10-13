package br.gov.sp.fatec.saloon.model.dao;

import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.Proprietario;

public interface ProprietarioDao {

    public Proprietario salvarProprietario(Proprietario proprietario);

    public Proprietario cadastrarProprietario(String apelido
                                             ,String email
                                             ,String senha
                                             ,String nome
                                             ,Date dtNascimento
                                             ,String cpf
                                             ,Date dtInicio);

    public Proprietario buscarProprietario(Long id);

    public Proprietario buscarProprietario(String apelido );

    public Proprietario buscarProprietarioPorEmail(String email);

    public boolean removerProprietario(Long id);

    public boolean removerProprietario(Proprietario proprietario);
    
}