package br.gov.sp.fatec.saloon.controller.entity.regi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.saloon.controller.View;
import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;
import br.gov.sp.fatec.saloon.model.repository.regi.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioRepository   usuarioRepo;

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioRepo.findAll();
    }

    @GetMapping(value = "/proprietario")
    @JsonView(View.UsuarioProprietario.class)
    public List<Usuario> listarUsuariosProprietarios(){
        return usuarioRepo.buscarUsuariosProprietarios();
    }
    
}
