package br.gov.sp.fatec.saloon.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.stat.UsuarioNivel;
import br.gov.sp.fatec.saloon.model.repository.UsuarioNivelRepository;

@Service("DesenvolvimentoService")
public class DesenvolvimentoServiceImpl implements DesenvolvimentoService {

    @Autowired
    private UsuarioNivelRepository usuarioNivelRepo;

    @Override
    @Transactional
    public UsuarioNivel incUsuarioNivel( Long    id
                                       , String  descr
                                       , boolean administrador
                                       , boolean proprietario
                                       , boolean parceiro
                                       , boolean cliente) {

        if (usuarioNivelRepo.existsById(id)      ) return usuarioNivelRepo.buscarPorId(id);
        if (usuarioNivelRepo.existsByDescr(descr)) return usuarioNivelRepo.findByDescr(descr);

        return this.incAltUsuarioNivel( new UsuarioNivel() 
                                      , id
                                      , descr
                                      , administrador
                                      , proprietario
                                      , parceiro
                                      , cliente);
    }

    @Override
    public UsuarioNivel altUsuarioNivel( Long id
                                       , String descr
                                       , boolean administrador
                                       , boolean proprietario
                                       , boolean parceiro
                                       , boolean cliente) {
        
        if ( ! usuarioNivelRepo.existsById(id) ) return null;

        return this.incAltUsuarioNivel( usuarioNivelRepo.buscarPorId(id)
                                      , id
                                      , descr
                                      , administrador
                                      , proprietario
                                      , parceiro
                                      , cliente);
    }

    private UsuarioNivel incAltUsuarioNivel( UsuarioNivel usuarioNivel
                                           , Long    id
                                           , String  descr
                                           , boolean administrador
                                           , boolean proprietario
                                           , boolean parceiro
                                           , boolean cliente) {

        usuarioNivel.setId(id);
        usuarioNivel.setDescr(descr);
        usuarioNivel.setAdministrador(administrador);
        usuarioNivel.setProprietario(proprietario);
        usuarioNivel.setParceiro(parceiro);
        usuarioNivel.setCliente(cliente);
        usuarioNivelRepo.save(usuarioNivel);

        return usuarioNivel;


    }
    
}
