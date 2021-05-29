package br.gov.sp.fatec.saloon.controller.entity.regi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.saloon.controller.View;
import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;
import br.gov.sp.fatec.saloon.model.repository.regi.UsuarioRepository;
import br.gov.sp.fatec.saloon.service.regi.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioRepository   usuarioRepo;

    @Autowired
    private UsuarioService      usuarioService;

    @GetMapping
    @JsonView(View.UsuariosResumido.class)
    public List<Usuario> listarUsuarios(){
        //return usuarioRepo.findAll();
        return usuarioService.buscarTodosOsUsuarios();
    }

    @GetMapping(value = "/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable("id") Long id){
        return usuarioService.buscarUsuarioPorId(id);
    }

    @GetMapping(value = "/proprietario")
    @JsonView(View.UsuarioProprietario.class)
    public List<Usuario> listarUsuariosProprietarios(){
        return usuarioRepo.buscarUsuariosProprietarios();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> cadastrarNovoUsuario(@RequestBody Usuario usuario
                                                       ,UriComponentsBuilder uriComponentsBuilder){

        usuario = usuarioService.persist( usuario.getApelido()
                                        , usuario.getEmail()
                                        , usuario.getSenha()
                                        , usuario.getNome()
                                        , usuario.getDtNascimento()
                                        , usuario.getCpf()
                                        , usuario.getUsuarioNivel().getId());

        HttpHeaders responseHeaders = new HttpHeaders();
        
        responseHeaders.setLocation(uriComponentsBuilder.path("/usuario/"+usuario.getId()).build().toUri());
        return new ResponseEntity<Usuario>(usuario, responseHeaders, HttpStatus.CREATED);

    }
    
}
