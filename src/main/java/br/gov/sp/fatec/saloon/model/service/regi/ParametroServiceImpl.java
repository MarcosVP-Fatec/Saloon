package br.gov.sp.fatec.saloon.model.service.regi;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.regi.Parametro;
import br.gov.sp.fatec.saloon.model.repository.regi.ParametroRepository;

@Service("ParametroService")
public class ParametroServiceImpl implements ParametroService {

    @Autowired
    private ParametroRepository     parametroRepo;

    @Override
    @Transactional
    public Parametro inc(String cod, String descricao, double numero) {
        if (parametroRepo.existsByCod(cod)){
            return parametroRepo.findByCod(cod);
        } 
        
        Parametro parametro = new Parametro();
        parametro.setCod(cod);
        parametro.setDescricao(descricao);
        parametro.setNumero(numero);
        return parametroRepo.save(parametro);
    }

    @Override
    @Transactional
    public Parametro inc(String cod, String descricao, String texto) {
        if (parametroRepo.existsByCod(cod)){
            return parametroRepo.findByCod(cod);
        } 
        
        Parametro parametro = new Parametro();
        parametro.setCod(cod);
        parametro.setDescricao(descricao);
        parametro.setStr(texto);
        return parametroRepo.save(parametro);
    }

    @Override
    @Transactional
    public Parametro inc(String cod, String descricao, Date data) {
        if (parametroRepo.existsByCod(cod)){
            return parametroRepo.findByCod(cod);
        } 
        
        Parametro parametro = new Parametro();
        parametro.setCod(cod);
        parametro.setDescricao(descricao);
        parametro.setData(data);
        return parametroRepo.save(parametro);
    }

    @Override
    @Transactional
    public Parametro inc(String cod, String descricao, boolean logico) {
        if (parametroRepo.existsByCod(cod)){
            return parametroRepo.findByCod(cod);
        } 
        
        Parametro parametro = new Parametro();
        parametro.setCod(cod);
        parametro.setDescricao(descricao);
        parametro.setLogico(logico);
        return parametroRepo.save(parametro);
    }

    @Override
    @Transactional
    public Parametro alt(Long id, String descricao, double numero) {
        if (!parametroRepo.existsById(id)){
            return null;
        }

        Parametro parametro = parametroRepo.buscarPorId(id);
        parametro.setDescricao(descricao);
        parametro.setNumero(numero);
        return parametroRepo.save(parametro);
    }

    @Override
    @Transactional
    public Parametro alt(Long id, String descricao, String texto) {
        if (!parametroRepo.existsById(id)){
            return null;
        }

        Parametro parametro = parametroRepo.buscarPorId(id);
        parametro.setDescricao(descricao);
        parametro.setStr(texto);
        return parametroRepo.save(parametro);
    }

    @Override
    @Transactional
    public Parametro alt(Long id, String descricao, Date data) {
        if (!parametroRepo.existsById(id)){
            return null;
        }

        Parametro parametro = parametroRepo.buscarPorId(id);
        parametro.setDescricao(descricao);
        parametro.setData(data);
        return parametroRepo.save(parametro);
    }

    @Override
    @Transactional
    public Parametro alt(Long id, String descricao, boolean logico) {
        if (!parametroRepo.existsById(id)){
            return null;
        }

        Parametro parametro = parametroRepo.buscarPorId(id);
        parametro.setDescricao(descricao);
        parametro.setLogico(logico);
        return parametroRepo.save(parametro);
    }

    @Override
    @Transactional
    public boolean del(Long id) {
        if (!parametroRepo.existsById(id)){
            return true;
        }
            
        parametroRepo.deleteById(id);
        return !parametroRepo.existsById(id);
    }

    @Override
    public boolean del(String cod) {
        if (!parametroRepo.existsByCod(cod))
            return true;
        return this.del(parametroRepo.findByCod(cod).getId());
    }
    
}
