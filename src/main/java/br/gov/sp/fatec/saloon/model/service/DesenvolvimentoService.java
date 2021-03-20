package br.gov.sp.fatec.saloon.model.service;

import br.gov.sp.fatec.saloon.model.entity.stat.UsuarioNivel;

public interface DesenvolvimentoService {

    public UsuarioNivel incUsuarioNivel(Long id, String descr, boolean administrador, boolean proprietario, boolean parceiro, boolean cliente);
    public UsuarioNivel altUsuarioNivel(Long id, String descr, boolean administrador, boolean proprietario, boolean parceiro, boolean cliente);
    
}
