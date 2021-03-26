package br.gov.sp.fatec.saloon.model.service.stat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.stat.ContratoMotivo;
import br.gov.sp.fatec.saloon.model.repository.stat.ContratoMotivoRepository;

@Service("ContratoMotivoService")
public class ContratoMotivoServiceImpl implements ContratoMotivoService {

    @Autowired
    private ContratoMotivoRepository contratoMotivoRepo;

    @Override
    @Transactional
    public ContratoMotivo persist(Long id, String descr) {

        ContratoMotivo contratoMotivo;

        if (id != null) {
            if (!contratoMotivoRepo.existsById(id) ){
                return null;
            }
            contratoMotivo = contratoMotivoRepo.buscarPorId(id);
        } else {
            contratoMotivo = new ContratoMotivo();
        }

        contratoMotivo.setDescr(descr);
        return contratoMotivoRepo.save(contratoMotivo);

    }
    @Override
    public ContratoMotivo persist(         String descr){
        return this.persist(null,descr);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!contratoMotivoRepo.existsById(id)){
            return true;
        }
        contratoMotivoRepo.deleteById(id);
        return !contratoMotivoRepo.existsById(id);
    }

    @Override
    public boolean delete(String descr) {
        if (!contratoMotivoRepo.existsByDescr(descr))
            return true;
        return this.delete(contratoMotivoRepo.findByDescr(descr).getId());
    }
}



