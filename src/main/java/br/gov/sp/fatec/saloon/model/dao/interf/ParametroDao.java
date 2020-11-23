package br.gov.sp.fatec.saloon.model.dao.interf;

import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.regi.Parametro;

public interface ParametroDao {

    public Parametro    salvar(Parametro parametro);

    public Parametro    buscar(Long id);

    public Parametro    buscar(String cod);
    
    public boolean      remover(Parametro parametro);

    public boolean      remover(Long id);

    public boolean      remover(String cod);

    public boolean      existe(Long id);

    public boolean      existe(String cod);

    public Parametro    cadastrar( String cod
                                 , String descricao
                                 , Double numero);

    public Parametro    cadastrar( String cod
                                 , String descricao
                                 , Date   data);
    
    public Parametro    cadastrar( String cod
                                 , String descricao
                                 , String texto);
                                 
    public Parametro    cadastrar( String  cod
                                 , String  descricao
                                 , boolean logico);
}