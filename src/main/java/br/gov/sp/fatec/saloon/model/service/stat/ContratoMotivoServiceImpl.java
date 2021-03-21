package br.gov.sp.fatec.saloon.model.service.stat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.stat.ContratoMotivo;
import br.gov.sp.fatec.saloon.model.repository.stat.ContratoMotivoRepository;

public class ContratoMotivoServiceImpl implements ContratoMotivoService {

    @Autowired
    private ContratoMotivoRepository contratoMotivoRepo;

    @Override
    @Transactional
    public ContratoMotivo inc(String descr) {
        if (contratoMotivoRepo.existsByDescr(descr)) return contratoMotivoRepo.findByDescr(descr);
        ContratoMotivo ctr = new ContratoMotivo();
        ctr.setDescr(descr);
        return contratoMotivoRepo.save(ctr);
    }

    @Override
    @Transactional
    public ContratoMotivo alt(Long id, String descr) {
        if (!contratoMotivoRepo.existsById(id))
            return null;
        
        ContratoMotivo ctr = contratoMotivoRepo.buscaPorId(id);
        ctr.setDescr(descr);
        return contratoMotivoRepo.save(ctr);
    }


    @Override
    @Transactional
    public boolean del(Long id) {
        if (!contratoMotivoRepo.existsById(id))
            return true;
        contratoMotivoRepo.deleteById(id);
        return !contratoMotivoRepo.existsById(id);
    }

    @Override
    public boolean del(String descr) {
        if (!contratoMotivoRepo.existsByDescr(descr))
            return true;
        return this.del(contratoMotivoRepo.findByDescr(descr).getId());
    }
    
}

