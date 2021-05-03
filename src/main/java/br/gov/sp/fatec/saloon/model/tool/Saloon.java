package br.gov.sp.fatec.saloon.model.tool;

/**
 * 
 * @author Marcos Vinicio Pereira
 *
 */

public enum Saloon {

      ADMINISTRADOR(1L)
    , PROPRIETARIO(2L)
    , PARCEIRO(3L)
    , CLIENTE(4L);

    private final Long valor;
    Saloon(Long valorOpcao) { valor = valorOpcao; }
    public Long getValor()  { return valor;       }    
    
}