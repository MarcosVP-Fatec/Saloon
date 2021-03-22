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
    public Proprietario inc(String apelido, String email, String senha, String nome, Date dtNascimento, String cpf) {
        if (proprietarioRepo.existsByApelido(apelido)) return proprietarioRepo.findByApelido(apelido);
        Proprietario proprietario = new Proprietario();
        proprietario.setApelido(apelido);
        proprietario.setEmail(email);
        proprietario.setSenha(senha);
        proprietario.setNome(nome);
        proprietario.setDtNascimento(dtNascimento);
        proprietario.setCpf(cpf);
        //proprietario.setUsuarioNivel(nivelRepo.buscarPorId(2L));

        return proprietarioRepo.save(proprietario);
    }

    @Override
    @Transactional
    public Proprietario alt(Long id, String apelido, String email, String senha, String nome, Date dtNascimento,
            String cpf) {
    
        if (!proprietarioRepo.existsById(id)){
            return null;
        }

        Proprietario proprietario = proprietarioRepo.buscarPorId(id);

        proprietario.setApelido(apelido);
        proprietario.setEmail(email);
        proprietario.setSenha(senha);
        proprietario.setNome(nome);
        proprietario.setDtNascimento(dtNascimento);
        proprietario.setCpf(cpf);

        return proprietarioRepo.save(proprietario);
    }

    @Override
    @Transactional
    public boolean del(Long id) {
        if (!proprietarioRepo.existsById(id)){
            return true;
        }
            
        proprietarioRepo.deleteById(id);
        return !proprietarioRepo.existsById(id);
    }

    @Override
    public boolean del(String apelido) {
        if (!proprietarioRepo.existsByApelido(apelido))
            return true;
        return this.del(proprietarioRepo.findByApelido(apelido).getId());
    }

}
