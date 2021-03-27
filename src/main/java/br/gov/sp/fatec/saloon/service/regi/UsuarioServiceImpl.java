package br.gov.sp.fatec.saloon.service.regi;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;
import br.gov.sp.fatec.saloon.model.repository.regi.UsuarioRepository;
import br.gov.sp.fatec.saloon.model.repository.stat.UsuarioNivelRepository;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private UsuarioNivelRepository nivelRepo;

    @Override
    @Transactional
    public Usuario persist( Long   id
                          , String apelido
                          , String email
                          , String senha
                          , String nome
                          , Date   dtNascimento
                          , String cpf
                          , Long   usuarioNivel){

        if (usuarioRepo.existsByApelido(apelido)) return usuarioRepo.findByApelido(apelido);

        Usuario usuario;

        if (id != null) {
            if (!usuarioRepo.existsById(id) ){
                return null;
            }
            usuario = usuarioRepo.buscarPorId(id);
        } else {
            usuario = new Usuario();
        }

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
    public Usuario persist( String apelido
                          , String email
                          , String senha
                          , String nome
                          , Date   dtNascimento
                          , String cpf
                          , Long   usuarioNivel){

        return this.persist( null 
                           , apelido
                           , email
                           , senha
                           , nome
                           , dtNascimento
                           , cpf
                           , usuarioNivel);

    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!usuarioRepo.existsById(id)){
            return true;
        }
            
        usuarioRepo.deleteById(id);
        return !usuarioRepo.existsById(id);
    }

    @Override
    public boolean delete(String apelido) {
        if (!usuarioRepo.existsByApelido(apelido))
            return true;
        return this.delete(usuarioRepo.findByApelido(apelido).getId());
    }
    
}