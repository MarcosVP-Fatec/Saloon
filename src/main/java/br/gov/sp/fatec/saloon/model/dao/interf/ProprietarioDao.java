package br.gov.sp.fatec.saloon.model.dao.interf;

import java.util.Date;
import java.util.List;

import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;

public interface ProprietarioDao {

    public Proprietario salvar(Proprietario proprietario);

    public Proprietario cadastrar(String apelido
                                 ,String email
                                 ,String senha
                                 ,String nome
                                 ,Date dtNascimento
                                 ,String cpf
                                 ,Date dtInicio);

    public Proprietario cadastrar(String apelido
                                 ,String email
                                 ,String senha
                                 ,String nome
                                 ,Date dtNascimento
                                 ,String cpf);

    public Proprietario buscar(Long id);

    public Proprietario buscar(String apelido );

    public Proprietario buscarPorEmail(String email);

    public List<Proprietario> buscar();

    public boolean remover(Proprietario proprietario);

    public boolean remover(Long id);

    public boolean remover(String apelido);
    
    public boolean existe(Long id);

    public boolean existe(String apelido );

    public boolean existeEmail(String email);

}