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
    public Parceiro persist( Long   id
                           , String apelido
                           , String email
                           , String senha
                           , String nome
                           , Date dtNascimento
                           , String cpf) {

        Parceiro parceiro;

        if (id != null) {
            if (!parceiroRepo.existsById(id) ){
                return null;
            }
            parceiro = parceiroRepo.buscarPorId(id);
        } else {
            parceiro = new Parceiro();
        }

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
    public Parceiro persist( String apelido
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
        if (!parceiroRepo.existsById(id)){
            return true;
        }
        parceiroRepo.deleteById(id);
        return !parceiroRepo.existsById(id);
    }

    @Override
    public boolean delete(String apelido) {
        if (!parceiroRepo.existsByApelido(apelido))
            return true;
        return this.delete(parceiroRepo.findByApelido(apelido).getId());
    }
}
