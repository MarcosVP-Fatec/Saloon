package br.gov.sp.fatec.saloon.model.dao;

import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.Parceiro;

public interface ParceiroDao {

    public Parceiro salvarParceiro(Parceiro parceiro);

    public Parceiro cadastrarParceiro(String apelido
                                             ,String email
                                             ,String senha
                                             ,String nome
                                             ,Date dtNascimento
                                             ,String cpf
                                             ,Date dtInicio);

    public Parceiro buscarParceiro(Long id);

    public Parceiro buscarParceiro(String apelido );

    public Parceiro buscarParceiroPorEmail(String email);

    public boolean removerParceiro(Long id);

    public boolean removerParceiro(Parceiro parceiro);
    
    
}



