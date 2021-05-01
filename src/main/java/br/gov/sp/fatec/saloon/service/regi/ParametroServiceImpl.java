package br.gov.sp.fatec.saloon.service.regi;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.exception.RegistroJaExisteException;
import br.gov.sp.fatec.saloon.exception.RegistroNaoEncontradoException;
import br.gov.sp.fatec.saloon.exception.RegistroNaoExcluidoException;
import br.gov.sp.fatec.saloon.model.entity.regi.Parametro;
import br.gov.sp.fatec.saloon.model.repository.regi.ParametroRepository;

@Service("parametroService")
public class ParametroServiceImpl implements ParametroService {

    @Autowired
    private ParametroRepository     parametroRepo;

    @Override
    public Parametro inc(Parametro parametro) {
        String cod = parametro.getCod();
        String descr = parametro.getDescricao();
        switch (parametro.getTipo()){

            case 'C':   parametro = this.inc( cod, descr, parametro.getStr() );
                        break;                                                    
            case 'N':   parametro = this.inc( cod, descr, parametro.getNumero() );
                        break;
            case 'D':   parametro = this.inc( cod, descr, parametro.getData() ) ;
                        break;                                                    
            case 'L':   parametro = this.inc( cod, descr, parametro.isLogico() ) ;
                        break;                                                    
        }
        return parametro;
    }

    @Override
    public Parametro alt(Parametro parametro) {
        String cod = parametro.getCod();
        String descr = parametro.getDescricao();
        switch (parametro.getTipo()){

            case 'C':   parametro = this.alt( cod, descr, parametro.getStr() );
                        break;                                                    
            case 'N':   parametro = this.alt( cod, descr, parametro.getNumero() );
                        break;
            case 'D':   parametro = this.alt( cod, descr, parametro.getData() ) ;
                        break;                                                    
            case 'L':   parametro = this.alt( cod, descr, parametro.isLogico() ) ;
                        break;                                                    
        }
        return parametro;
    }

    /**
     * Parâmetro Número
     */
    @Override
    @Transactional
    public Parametro inc(String cod, String descricao, double numero) {
        if (parametroRepo.existsByCod(cod)){
            throw new RegistroJaExisteException("Tentativa de inclusão de parâmetro que já existe -> " + cod);
        } 
        return parametroRepo.saveAndFlush(new Parametro(cod, descricao, numero));
    }

    @Override
    @Transactional
    public Parametro alt(String cod, String descricao, double numero) {
        if (!parametroRepo.existsByCod(cod)){
            throw new RegistroNaoEncontradoException("Tentativa de alteração de parâmetro que não existe -> " + cod);
        } 
        Parametro parametro = parametroRepo.findByCod(cod);
        parametro.setNull();
        parametro.setDescricao(descricao);
        parametro.setNumero(numero);
        return parametroRepo.saveAndFlush(parametro);
    }

    /**
     * Parâmetro Texto
     */
    @Override
    @Transactional
    public Parametro inc(String cod, String descricao, String texto) {
        if (parametroRepo.existsByCod(cod)){
            throw new RegistroJaExisteException("Tentativa de inclusão de parâmetro que já existe -> " + cod);
        } 
        return parametroRepo.saveAndFlush(new Parametro(cod, descricao, texto));
    }

    @Override
    @Transactional
    public Parametro alt(String cod, String descricao, String texto) {
        if (!parametroRepo.existsByCod(cod)){
            throw new RegistroNaoEncontradoException("Tentativa de alteração de parâmetro que não existe -> " + cod);
        } 
        Parametro parametro = parametroRepo.findByCod(cod);
        parametro.setNull();
        parametro.setDescricao(descricao);
        parametro.setStr(texto);
        return parametroRepo.saveAndFlush(parametro);
    }

    /**
     * Parâmetro Data
     */
    @Override
    @Transactional
    public Parametro inc(String cod, String descricao, Date data) {
        if (parametroRepo.existsByCod(cod)){
            throw new RegistroJaExisteException("Tentativa de inclusão de parâmetro que já existe -> " + cod);
        } 
        return parametroRepo.saveAndFlush(new Parametro(cod, descricao, data));
    }

    @Override
    @Transactional
    public Parametro alt( String cod, String descricao, Date data) {
        if (!parametroRepo.existsByCod(cod)){
            throw new RegistroNaoEncontradoException("Tentativa de alteração de parâmetro que não existe -> " + cod);
        } 
        Parametro parametro = parametroRepo.findByCod(cod);
        parametro.setNull();
        parametro.setDescricao(descricao);
        parametro.setData(data);
        return parametroRepo.saveAndFlush(parametro);
    }

    /**
     * Parâmetro Lógico
     */
    @Override
    @Transactional
    public Parametro inc(String cod, String descricao, boolean logico) {
        if (parametroRepo.existsByCod(cod)){
            throw new RegistroJaExisteException("Tentativa de inclusão de parâmetro que já existe -> " + cod);
        } 
        return parametroRepo.saveAndFlush(new Parametro(cod, descricao, logico));
    }

    @Override
    @Transactional
    public Parametro alt(String cod, String descricao, boolean logico) {
        if (!parametroRepo.existsByCod(cod)){
            throw new RegistroNaoEncontradoException("Tentativa de alteração de parâmetro que não existe -> " + cod);
        } 
        Parametro parametro = parametroRepo.findByCod(cod);
        parametro.setNull();
        parametro.setDescricao(descricao);
        parametro.setLogico(logico);
        return parametroRepo.saveAndFlush(parametro);
    }

    @Override
    @Transactional
    public boolean del(Long id) {
        if (!parametroRepo.existsById(id)){
            return true;
        }
        try {
        	parametroRepo.deleteById(id);
		} catch (RegistroNaoExcluidoException e) {
			throw new RegistroNaoExcluidoException("Tentativa de exclusão de parâmetro mal suscedida -> " + parametroRepo.findById(id).get().getCod());
		}
        return !parametroRepo.existsById(id);
    }

    @Override
    public boolean del(String cod) {
        if (!parametroRepo.existsByCod(cod)) return true;
        return this.del(parametroRepo.findByCod(cod).getId());
    }

	@Override
	public Parametro buscarPorCod(String cod) {
    	Parametro parametro = parametroRepo.findByCod(cod);
    	if (parametro == null) {
    		throw new RegistroNaoEncontradoException("Código de Parâmetro não encontrado: " + cod);
    	}
        return parametro;
	}

    @Override
    public Parametro buscarPorTipoCod(char tipo, String cod) {
        // TODO Auto-generated method stub
        return null;
    }
}
