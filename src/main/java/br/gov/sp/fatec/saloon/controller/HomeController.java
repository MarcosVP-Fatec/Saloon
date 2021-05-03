package br.gov.sp.fatec.saloon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.saloon.model.tool.Data;

@RestController
@CrossOrigin
public class HomeController {

    @GetMapping
    ResponseEntity<String> bemVindo(){

        String body = "<h1>Seja bem vindo ao Saloon !!!</h1><br><br>" +
                      "<h2>Agora são " + Data.time() + "</h2>";
                      
                      //<img src=@{/images/saloon_porta_269.png}>";
                      // alt='Saloon' width='500' height='600'>";

        return new ResponseEntity<String>(body, HttpStatus.OK);

    }
  
}