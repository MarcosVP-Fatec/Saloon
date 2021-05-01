package br.gov.sp.fatec.saloon.controller.entity.regi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.saloon.model.entity.regi.Parametro;
import br.gov.sp.fatec.saloon.model.repository.regi.ParametroRepository;
import br.gov.sp.fatec.saloon.service.regi.ParametroService;

@RestController
@RequestMapping(value = "/parametro")
@CrossOrigin
public class ParametroController {
	
	@Autowired
	ParametroRepository parametroRepo;
	
	@Autowired
	ParametroService parametroService;

    @GetMapping  //(value = "/todos")
    public List<Parametro> buscarTodos(){
        return parametroRepo.findAll();
    }
	
    @GetMapping(value = "/{cod}")
    public Parametro buscarPorCod(@PathVariable("cod") String cod){
        return parametroService.buscarPorCod(cod);
    }
	
    @PostMapping
    ResponseEntity<Parametro> cadastrarNovoParametro(
    		@RequestBody Parametro parametro,
    		UriComponentsBuilder uriComponentsBuilder){
    	
        System.out.println(">>>>> " + parametro.getDescricao());
        parametro = parametroService.inc( parametro.getCod()
        		                        , parametro.getDescricao()
        		                        , parametro.isLogico()) ;
        
        //Trata o retorno 
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(uriComponentsBuilder.path("/parametro/"+parametro.getCod()).build().toUri());
        return new ResponseEntity<Parametro>( parametro       
        		                             , responseHeaders
        		                             , HttpStatus.CREATED );
    }
    
}
