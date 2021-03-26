package br.gov.sp.fatec.saloon.model.service.regi;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;
import br.gov.sp.fatec.saloon.model.repository.regi.UsuarioRepository;
import br.gov.sp.fatec.saloon.model.repository.stat.UsuarioNivelRepository;

@Service("UsuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private UsuarioNivelRepository nivelRepo;

    @Override
    @Transactional
    public Usuario inc(String apelido, String email, String senha, String nome, Date dtNascimento, String cpf,
            Long usuarioNivel) {
        if (usuarioRepo.existsByApelido(apelido)) return usuarioRepo.findByApelido(apelido);
        Usuario usuario = new Usuario();

        usuario.setApelido(apelido);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setNome(nome);
        usuario.setDtNascimento(dtNascimento);
        usuario.setCpf(cpf);
        usuario.setUsuarioNivel(nivelRepo.buscarPorId(usuarioNivel));
        return usuarioRepo.save(usuario);
    }

    @Override
    @Transactional
    public Usuario alt(Long id, String apelido, String email, String senha, String nome, Date dtNascimento, String cpf,
            Long usuarioNivel) {
        if (!usuarioRepo.existsById(id)){
            return null;
        }
        Usuario usuario = usuarioRepo.buscarPorId(id);
        usuario.setApelido(apelido);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setNome(nome);
        usuario.setDtNascimento(dtNascimento);
        usuario.setCpf(cpf);
        usuario.setUsuarioNivel(nivelRepo.buscarPorId(usuarioNivel));
        return usuarioRepo.save(usuario);
    }

    @Override
    @Transactional
    public boolean del(Long id) {
        if (!usuarioRepo.existsById(id)){
            return true;
        }
            
        usuarioRepo.deleteById(id);
        return !usuarioRepo.existsById(id);
    }

    @Override
    public boolean del(String apelido) {
        if (!usuarioRepo.existsByApelido(apelido))
            return true;
        return this.del(usuarioRepo.findByApelido(apelido).getId());
    }
    
}
