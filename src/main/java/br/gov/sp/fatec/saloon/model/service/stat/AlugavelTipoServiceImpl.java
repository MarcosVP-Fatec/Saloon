package br.gov.sp.fatec.saloon.model.service.stat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;
import br.gov.sp.fatec.saloon.model.repository.stat.AlugavelTipoRepository;

@Service("AlugavelTipoService")
public class AlugavelTipoServiceImpl implements AlugavelTipoService {

    @Autowired
    private AlugavelTipoRepository alugavelTipoRepo;

    @Override
    @Transactional
    public AlugavelTipo inc(String descr) {
        if (alugavelTipoRepo.existsByDescr(descr)) return alugavelTipoRepo.findByDescr(descr);
        AlugavelTipo tipo = new AlugavelTipo();
        tipo.setDescr(descr);
        return alugavelTipoRepo.save(tipo);
    }

    @Override
    @Transactional
    public AlugavelTipo alt(Long id, String descr) {
        if (!alugavelTipoRepo.existsById(id)){
            return null;
        }
        AlugavelTipo tipo = alugavelTipoRepo.buscarPorId(id);
        tipo.setDescr(descr);
        return alugavelTipoRepo.save(tipo);
    }

    @Override
    @Transactional
    public boolean del(Long id) {
        if (!alugavelTipoRepo.existsById(id))
            return true;
        alugavelTipoRepo.deleteById(id);
        return !alugavelTipoRepo.existsById(id);
    }


    @Override
    public boolean del(String descr) {
        if (!alugavelTipoRepo.existsByDescr(descr))
            return true;
        return this.del(alugavelTipoRepo.findByDescr(descr).getId());
    }
    
}
