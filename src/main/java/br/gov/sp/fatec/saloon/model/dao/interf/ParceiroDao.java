package br.gov.sp.fatec.saloon.model.dao.interf;

import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.regi.Parceiro;

public interface ParceiroDao {

    public Parceiro salvar(Parceiro parceiro);

    public Parceiro cadastrar(String apelido
                                             ,String email
                                             ,String senha
                                             ,String nome
                                             ,Date dtNascimento
                                             ,String cpf
                                             ,Date dtInicio);

    public Parceiro buscar(Long id);

    public Parceiro buscar(String apelido );

    public Parceiro buscarPorEmail(String email);

    public boolean remover(Parceiro parceiro);

    public boolean remover(Long id);
    
    public boolean existe(Long id);

    public boolean existe(String apelido );

    public boolean existeEmail(String email);
    
}



