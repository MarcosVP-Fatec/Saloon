package br.gov.sp.fatec.saloon.model.service.stat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.stat.UsuarioNivel;
import br.gov.sp.fatec.saloon.model.repository.stat.UsuarioNivelRepository;

@Service("UsuarioNivelService")
public class UsuarioNivelServiceImpl implements UsuarioNivelService{
    
    @Autowired
    private UsuarioNivelRepository usuarioNivelRepo;

    @Override
    @Transactional
    public UsuarioNivel inc(Long id, String descr, boolean administrador, boolean proprietario,
            boolean parceiro, boolean cliente) {

        if (usuarioNivelRepo.existsById(id))
            return usuarioNivelRepo.buscarPorId(id);
        if (usuarioNivelRepo.existsByDescr(descr))
            return usuarioNivelRepo.findByDescr(descr);

        return this.incAlt(new UsuarioNivel(), id, descr, administrador, proprietario, parceiro, cliente);
    }

    @Override
    @Transactional
    public UsuarioNivel alt(Long id, String descr, boolean administrador, boolean proprietario,
            boolean parceiro, boolean cliente) {

        if (!usuarioNivelRepo.existsById(id))
            return null;

        return this.incAlt(usuarioNivelRepo.buscarPorId(id), id, descr, administrador, proprietario,
                parceiro, cliente);
    }

    private UsuarioNivel incAlt(UsuarioNivel usuarioNivel, Long id, String descr, boolean administrador,
            boolean proprietario, boolean parceiro, boolean cliente) {

        usuarioNivel.setId(id);
        usuarioNivel.setDescr(descr);
        usuarioNivel.setAdministrador(administrador);
        usuarioNivel.setProprietario(proprietario);
        usuarioNivel.setParceiro(parceiro);
        usuarioNivel.setCliente(cliente);

        return usuarioNivelRepo.save(usuarioNivel);

    }

    @Override
    @Transactional
    public boolean del(Long id) {
        if (!usuarioNivelRepo.existsById(id)){
            return true;
        }
        usuarioNivelRepo.deleteById(id);
        return !usuarioNivelRepo.existsById(id);
    }

    @Override
    public boolean del(String descr) {
        if (!usuarioNivelRepo.existsByDescr(descr)){
            return true;
        }
        return this.del(usuarioNivelRepo.findByDescr(descr).getId());
    }

}
