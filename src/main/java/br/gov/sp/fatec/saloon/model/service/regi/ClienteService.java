package br.gov.sp.fatec.saloon.model.service.regi;

import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;

public interface ClienteService {

    public Cliente persist( Long     id
                          , String   cpf_cnpj
                          , String   nome
                          , String   telDdd
                          , String   telNumero
                          , Long     idParceiro);

    public Cliente persist( String   cpf_cnpj
                          , String   nome
                          , String   telDdd
                          , String   telNumero
                          , Long     idParceiro);

    public Cliente persist( String   cpf_cnpj
                          , String   nome
                          , String   telDdd
                          , String   telNumero);

    public Cliente persist( String   cpf_cnpj
                          , String   nome);

    public boolean delete(Long id);
    public boolean delete(String nome);
    
}



