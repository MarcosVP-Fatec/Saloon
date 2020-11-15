package br.gov.sp.fatec.saloon.model.dao.interf;

import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.regi.Parceiro;

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
    
    public boolean existe(Long id);

    public boolean existe(String apelido );

    public boolean existeEmail(String email);
    
}



