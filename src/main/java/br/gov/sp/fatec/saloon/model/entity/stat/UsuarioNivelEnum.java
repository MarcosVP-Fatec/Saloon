package br.gov.sp.fatec.saloon.model.entity.stat;

public enum UsuarioNivelEnum {
    
    ADMINISTRADOR(1L)
  , PROPRIETARIO(2L)
  , PARCEIRO(3L)
  , CLIENTE(4L)
  , PARCEIRO_CLIENTE(5L);

  public Long codigo;

  UsuarioNivelEnum(Long codigo) {
     this.codigo = codigo;
  }

}