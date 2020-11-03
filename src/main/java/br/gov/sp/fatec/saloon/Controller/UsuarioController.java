package br.gov.sp.fatec.saloon.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import br.gov.sp.fatec.saloon.Interface.UsuarioRepositorio;
import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;

  
@Controller   
@RequestMapping(path="/usuario")
public class UsuarioController {
  @Autowired 
  private UsuarioRepositorio usuarioRepositorio;

  //curl https://8080-d38bc4f4-fde6-4f7b-baa5-208e0e3636ac.ws-us02.gitpod.io/usuario/add 
  //-d nome=Teste API -d email=someemail@someemailprovider.com -d senha=testeteste

//   @PostMapping(path="/add") // Map ONLY POST Requests
//   public @ResponseBody String addNewUser (@RequestParam final String nome, @RequestParam final String email,
//           @RequestParam final String senha) {
//       final Usuario usuario = new Usuario() ;
//       usuario.setApelido(nome);
//       usuario.setEmail(email);
//       usuario.setSenha(senha);
//       usuarioRepositorio.save(usuario);
//       return "Saved";
//   }


  @GetMapping(path = "/all")
  public @ResponseBody Iterable<Usuario> getAllUsers() {
      return usuarioRepositorio.findAll();
  }

}