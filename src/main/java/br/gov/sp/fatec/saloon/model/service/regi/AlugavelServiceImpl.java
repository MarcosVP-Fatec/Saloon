package br.gov.sp.fatec.saloon.model.service.regi;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.regi.Alugavel;
import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;
import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;
import br.gov.sp.fatec.saloon.model.repository.regi.AlugavelRepository;
import br.gov.sp.fatec.saloon.model.repository.regi.ProprietarioRepository;
import br.gov.sp.fatec.saloon.model.repository.stat.AlugavelTipoRepository;

@Service("AlugavelService")
public class AlugavelServiceImpl implements AlugavelService {

    @Autowired
    private AlugavelRepository      alugavelRepo;

    @Autowired
    private ProprietarioRepository  proprietarioRepo;

    @Autowired
    private AlugavelTipoRepository  alugavelTipoRepo;

    @Override
    @Transactional
    public Alugavel persist( Long   id
                           , String descr
                           , Long   idProprietario
                           , Long   idAlugavelTipo
                           , String endereco
                           , int capacidade
                           , BigDecimal valor) {

        Alugavel alugavel;

        if (id != null) {
            if (!alugavelRepo.existsById(id) ){
                return null;
            }
            alugavel = alugavelRepo.buscarPorId(id);
        } else {
            alugavel = new Alugavel();
        }

        // Transforma o id do proprietário em um objeto
        Proprietario proprietario = proprietarioRepo.buscarPorId(idProprietario);
        if (proprietario == null){
            return null; //throw new Exception("Não há proprietário para o id informato ("+String.valueOf(idProprietario)+").");
        }

        // Transforma o id do tipo de alugável em um objeto
        AlugavelTipo alugavelTipo = alugavelTipoRepo.buscarPorId(idAlugavelTipo);
        if (alugavel == null){
            return null; //throw new Exception("Não há alugável para o id informato ("+String.valueOf(idAlugavelTipo)+").");
        }
        
        alugavel.setDescr(descr);
        alugavel.setProprietario(proprietario);
        alugavel.setAlugavelTipo(alugavelTipo);
        alugavel.setEndereco(endereco);
        alugavel.setCapacidade(capacidade);
        alugavel.setCapacidade(capacidade);

        return alugavelRepo.save(alugavel);
        
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!alugavelRepo.existsById(id)){
            return true;
        }
        alugavelRepo.deleteById(id);
        return !alugavelRepo.existsById(id);
    }
    
}

