package br.gov.sp.fatec.saloon.controller.entity.regi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

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

import br.gov.sp.fatec.saloon.controller.View;
import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;
import br.gov.sp.fatec.saloon.model.repository.regi.ProprietarioRepository;
import br.gov.sp.fatec.saloon.service.regi.ProprietarioService;

@RestController
@RequestMapping(value = "/proprietario")
@CrossOrigin
public class ProprietarioController {

    @Autowired
    private ProprietarioRepository proprietarioRepo;

    @Autowired
    private ProprietarioService    proprietarioService;

    @JsonView(View.ProprietarioApelidoUsuario.class)
    @GetMapping
    public List<Proprietario> buscarProprietarios(){
        return proprietarioRepo.buscarProprietarios();
    }
    
    @JsonView(View.ProprietarioApelidoUsuario.class)
    @GetMapping(value = "/{apelido}")
    public Proprietario buscarProprietariosPorCod(@PathVariable("apelido") String apelido){
        return proprietarioService.buscaPorApelido(apelido);
    }

    @PostMapping
    public ResponseEntity<Proprietario> incluirProprietario(@RequestBody Proprietario proprietario,
        UriComponentsBuilder uriComponentsBuilder){

        proprietario = proprietarioService.inc( proprietario);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(uriComponentsBuilder.path("/proprietario/"+proprietario.getId()).build().toUri());
        return new ResponseEntity<Proprietario>(proprietario, responseHeaders, HttpStatus.CREATED);

    }

}
