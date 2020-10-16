package br.gov.sp.fatec.saloon.model.dao;

import java.util.List;

import br.gov.sp.fatec.saloon.model.entity.Cliente;
import br.gov.sp.fatec.saloon.model.entity.Parceiro;

public interface ClienteDao {

    public Cliente salvarCliente(Cliente cliente);

    public Cliente cadastrarCliente( String   cpf_cnpj
                                   , String   nome
                                   , String   tel_ddd
                                   , String   tel_numero
                                   , Parceiro parceiro);

    public Cliente cadastrarCliente( String   cpf_cnpj
                                   , String   nome
                                   , String   tel_ddd
                                   , String   tel_numero);

    public Cliente cadastrarCliente( String   cpf_cnpj
                                   , String   nome);

    public Cliente buscarCliente(Long id);

    public Cliente buscarCliente(String cpf );

    public List<Cliente> buscarClientePorNome(String nome );

    public Cliente buscarClienteParceiro(Long idParceiro);

    public boolean removerCliente(Long id);

    public boolean removerCliente(Cliente cliente);
    
}