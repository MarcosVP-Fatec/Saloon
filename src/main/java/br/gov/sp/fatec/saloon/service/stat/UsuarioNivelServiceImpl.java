package br.gov.sp.fatec.saloon.service.stat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.stat.UsuarioNivel;
import br.gov.sp.fatec.saloon.model.repository.stat.UsuarioNivelRepository;

@Service("usuarioNivelService")
public class UsuarioNivelServiceImpl implements UsuarioNivelService{
    
    @Autowired
    private UsuarioNivelRepository usuarioNivelRepo;

    @Override
    @Transactional
    public UsuarioNivel persist( Long id
                               , String descr
                               , boolean administrador
                               , boolean proprietario
                               , boolean parceiro
                               , boolean cliente) {

        UsuarioNivel usuarioNivel;

        // Esta entidade não tem o id automático
        if (id == null) {
            return null;
        }    

        if (usuarioNivelRepo.existsById(id) ){
            usuarioNivel = usuarioNivelRepo.buscarPorId(id);
        } else {
            usuarioNivel = new UsuarioNivel();
            usuarioNivel.setId(id);
        }

        usuarioNivel.setDescr(descr);
        usuarioNivel.setAdministrador(administrador);
        usuarioNivel.setProprietario(proprietario);
        usuarioNivel.setParceiro(parceiro);
        usuarioNivel.setCliente(cliente);

        return usuarioNivelRepo.save(usuarioNivel);
    }

    @Override
    public UsuarioNivel persist(         String descr, boolean administrador, boolean proprietario, boolean parceiro, boolean cliente){
        return this.persist( null, descr, administrador, proprietario, parceiro, cliente);
    }                               

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!usuarioNivelRepo.existsById(id)){
            return true;
        }
        usuarioNivelRepo.deleteById(id);
        return !usuarioNivelRepo.existsById(id);
    }

    @Override
    public boolean delete(String descr) {
        if (!usuarioNivelRepo.existsByDescr(descr)){
            return true;
        }
        return this.delete(usuarioNivelRepo.findByDescr(descr).getId());
    }

}






