package br.gov.sp.fatec.saloon.model.tool;

public enum Saloon {

      ADMINISTRADOR(1L)
    , PROPRIETARIO(2L)
    , PARCEIRO(3L)
    , CLIENTER(4L);

    private final Long valor;
    Saloon(Long valorOpcao) { valor = valorOpcao; }
    public Long getValor()  { return valor;       }    
    
}