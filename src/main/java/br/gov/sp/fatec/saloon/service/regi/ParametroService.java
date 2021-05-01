package br.gov.sp.fatec.saloon.service.regi;

import java.util.Date;

import br.gov.sp.fatec.saloon.model.entity.regi.Parametro;

public interface ParametroService {

    public Parametro inc(Parametro parametro);
    
    public Parametro inc(String cod, String descricao, double numero);

    public Parametro inc(String cod, String descricao, String texto);

    public Parametro inc(String cod, String descricao, Date data);

    public Parametro inc(String cod, String descricao, boolean logico);

    public Parametro alt(Parametro parametro);

    public Parametro alt(String cod, String descricao, double numero);

    public Parametro alt(String cod, String descricao, String texto);

    public Parametro alt(String cod, String descricao, Date data);

    public Parametro alt(String cod, String descricao, boolean logico);

    public boolean del(Long id);

    public boolean del(String cod);
    
    public Parametro buscarPorCod(String cod);

    public Parametro buscarPorTipoCod(char tipo, String cod);
    
}

    

