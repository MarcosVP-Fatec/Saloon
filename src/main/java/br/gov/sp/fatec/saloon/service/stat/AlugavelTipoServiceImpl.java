package br.gov.sp.fatec.saloon.service.stat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;
import br.gov.sp.fatec.saloon.model.repository.stat.AlugavelTipoRepository;

@Service("alugavelTipoService")
public class AlugavelTipoServiceImpl implements AlugavelTipoService {

    @Autowired
    private AlugavelTipoRepository alugavelTipoRepo;

    @Override
    @Transactional
    public AlugavelTipo persist(Long id, String descr) {
        
        AlugavelTipo alugavelTipo;

        if (id != null) {
            if (!alugavelTipoRepo.existsById(id) ){
                return null;
            }
            alugavelTipo = alugavelTipoRepo.buscarPorId(id);
        } else {
            alugavelTipo = new AlugavelTipo();
        }

        alugavelTipo.setDescr(descr);
        return alugavelTipoRepo.save(alugavelTipo);

    }

    @Override
    public AlugavelTipo persist(         String descr){
        return this.persist(null,descr);
    }
    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!alugavelTipoRepo.existsById(id))
            return true;
        alugavelTipoRepo.deleteById(id);
        return !alugavelTipoRepo.existsById(id);
    }

    @Override
    public boolean delete(String descr) {
        if (!alugavelTipoRepo.existsByDescr(descr))
            return true;
        return this.delete(alugavelTipoRepo.findByDescr(descr).getId());
    }
    
}