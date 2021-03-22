package br.gov.sp.fatec.saloon.model.service.regi;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.regi.Parceiro;
import br.gov.sp.fatec.saloon.model.repository.regi.ParceiroRepository;
import br.gov.sp.fatec.saloon.model.repository.stat.UsuarioNivelRepository;

@Service("ParceiroService")
public class ParceiroServiceImpl implements ParceiroService {
    
    @Autowired
    private ParceiroRepository      parceiroRepo;

    @Autowired
    private UsuarioNivelRepository  nivelRepo;

    @Override
    @Transactional
    public Parceiro inc(String apelido, String email, String senha, String nome, Date dtNascimento, String cpf) {
        if (parceiroRepo.existsByApelido(apelido)){
            return parceiroRepo.findByApelido(apelido);
        } 
        
        Parceiro parceiro = new Parceiro();

        parceiro.setApelido(apelido);
        parceiro.setEmail(email);
        parceiro.setSenha(senha);
        parceiro.setNome(nome);
        parceiro.setDtNascimento(dtNascimento);
        parceiro.setCpf(cpf);
        parceiro.setUsuarioNivel(nivelRepo.buscarPorId(3L));

        return parceiroRepo.save(parceiro);
    }

    @Override
    @Transactional
    public Parceiro alt(Long id, String apelido, String email, String senha, String nome, Date dtNascimento,
            String cpf) {
    
        if (!parceiroRepo.existsById(id)){
            return null;
        }

        Parceiro parceiro = parceiroRepo.buscarPorId(id);

        parceiro.setApelido(apelido);
        parceiro.setEmail(email);
        parceiro.setSenha(senha);
        parceiro.setNome(nome);
        parceiro.setDtNascimento(dtNascimento);
        parceiro.setCpf(cpf);

        return parceiroRepo.save(parceiro);
    }

    @Override
    @Transactional
    public boolean del(Long id) {
        if (!parceiroRepo.existsById(id)){
            return true;
        }
            
        parceiroRepo.deleteById(id);
        return !parceiroRepo.existsById(id);
    }

    @Override
    public boolean del(String apelido) {
        if (!parceiroRepo.existsByApelido(apelido))
            return true;
        return this.del(parceiroRepo.findByApelido(apelido).getId());
    }
}