package br.gov.sp.fatec.saloon.model.dao.interf;

import java.util.List;

import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;
import br.gov.sp.fatec.saloon.model.entity.regi.Parceiro;

public interface ClienteDao {

    public Cliente salvar(Cliente cliente);

    public Cliente cadastrar( String   cpf_cnpj
                            , String   nome
                            , String   tel_ddd
                            , String   tel_numero
                            , Parceiro parceiro);

    public Cliente cadastrar( String   cpf_cnpj
                            , String   nome
                            , String   tel_ddd
                            , String   tel_numero);

    public Cliente cadastrar( String   cpf_cnpj
                            , String   nome);

    public Cliente buscar(Long id);

    public Cliente buscar(String cpf );

    public List<Cliente> buscarPorNome(String nome );

    public Cliente buscarParceiro(Long idParceiro);

    public boolean remover(Long id);

    public boolean remover(Cliente cliente);
    
    public boolean existe(Long id);

    public boolean existe(String cpf );

}