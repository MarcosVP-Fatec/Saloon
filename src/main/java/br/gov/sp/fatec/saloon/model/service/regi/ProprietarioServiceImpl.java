package br.gov.sp.fatec.saloon.model.service.regi;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;
import br.gov.sp.fatec.saloon.model.repository.regi.ProprietarioRepository;
import br.gov.sp.fatec.saloon.model.repository.stat.UsuarioNivelRepository;

@Service("ProprietarioService")
public class ProprietarioServiceImpl implements ProprietarioService {

    @Autowired
    private ProprietarioRepository proprietarioRepo;

    @Autowired
    private UsuarioNivelRepository nivelRepo;

    @Override
    @Transactional
    public Proprietario persist( Long   id
                               , String apelido
                               , String email
                               , String senha
                               , String nome
                               , Date   dtNascimento
                               , String cpf) {
    
        Proprietario proprietario;

        if (id != null) {
            if (!proprietarioRepo.existsById(id) ){
                return null;
            }
            proprietario = proprietarioRepo.buscarPorId(id);
        } else {
            proprietario = new Proprietario();
        }

        proprietario.setApelido(apelido);
        proprietario.setEmail(email);
        proprietario.setSenha(senha);
        proprietario.setNome(nome);
        proprietario.setDtNascimento(dtNascimento);
        proprietario.setCpf(cpf);
        proprietario.setUsuarioNivel(nivelRepo.buscarPorId(2L));

        return proprietarioRepo.save(proprietario);
    }

    @Override
    public Proprietario persist( String apelido
                               , String email
                               , String senha
                               , String nome
                               , Date   dtNascimento
                               , String cpf){

        return this.persist( null
                           , apelido
                           , email
                           , senha
                           , nome
                           , dtNascimento
                           , cpf);
    
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!proprietarioRepo.existsById(id)){
            return true;
        }
            
        proprietarioRepo.deleteById(id);
        return !proprietarioRepo.existsById(id);
    }

    @Override
    public boolean delete(String apelido) {
        if (!proprietarioRepo.existsByApelido(apelido))
            return true;
        return this.delete(proprietarioRepo.findByApelido(apelido).getId());
    }
}
