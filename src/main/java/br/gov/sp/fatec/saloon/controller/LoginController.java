package br.gov.sp.fatec.saloon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.saloon.security.JwtUtils;
import br.gov.sp.fatec.saloon.security.Login;

@RestController
@RequestMapping(value = "/login")
@CrossOrigin
public class LoginController {

    /**
     * O AuthenticationManager do spring já utiliza toda a configuração do login básico
     */
    @Autowired
    private AuthenticationManager authManager; 

    /**
     * O objeto aut contém apenas usuário e senha e, depois de autenticar, recebe mais dados
     * @throws JsonProcessingException
     */
    @PostMapping
    public Login autenticar(@RequestBody Login login) throws JsonProcessingException{

        /**
         * O Authentication é uma interface, por isso foi usado o UsernamePasswordAuthenticationToken para instanciar
         */
        Authentication auth = new UsernamePasswordAuthenticationToken(login.getUsuario()
                                                                     ,login.getSenha());
        /**
         * O método abaixo faz as validações. Senão validar gera uma exception
         * Depois de autenticar já possui as autorizações que buscou no BD.
         */                                                                     
        auth = authManager.authenticate(auth); 
        
        // Não precisamos mais da senha, pois já foi autenticado
        login.setSenha(null); 
        
        login.setToken(JwtUtils.generateToken(auth));
        
        return login;
    }
    
}
