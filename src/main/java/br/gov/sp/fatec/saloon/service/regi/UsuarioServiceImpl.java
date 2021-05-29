package br.gov.sp.fatec.saloon.service.regi;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.exception.RegistroJaExisteException;
import br.gov.sp.fatec.saloon.exception.RegistroNaoEncontradoException;
import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;
import br.gov.sp.fatec.saloon.model.repository.regi.UsuarioRepository;
import br.gov.sp.fatec.saloon.model.repository.stat.UsuarioNivelRepository;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository       usuarioRepo;

    @Autowired
    private UsuarioNivelRepository  nivelRepo;

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

        Usuario usuario;

        //Se for inclusão de usuário
        if (id == null){
            if (usuarioRepo.existsByApelido(apelido.toUpperCase())){
                throw new RegistroJaExisteException("Usuário já cadastrado: \"" + apelido + "\'");    
            } 
            usuario = new Usuario();
        } else {
            if (!usuarioRepo.existsByApelido(apelido.toUpperCase())){
                throw new RegistroNaoEncontradoException("Usuário não encontrado: \"" + apelido + "\'");    
            } 
            usuario = usuarioRepo.findByApelido(apelido);
        }

        //Verifica se o nível de usuário existe
        if (!nivelRepo.existsById(usuarioNivel)){
            throw new RegistroNaoEncontradoException("Nível de usuário não existe: \"" + usuarioNivel + "\"");
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

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepo.buscarPorId(id);
        if (usuario == null){
            throw new RegistroNaoEncontradoException("Código de usuário não existe: \"" + id + "\"");
        }
        return usuario;
    }

    @Override
    //@PreAuthorize("isAuthenticated()")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> buscarTodosOsUsuarios() {
        return usuarioRepo.findAll();
    }
    
}