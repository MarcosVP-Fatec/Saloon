package br.gov.sp.fatec.saloon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.sp.fatec.saloon.model.repository.regi.UserRepository;

@Component
public class NaoInjeta {

    @Autowired
    private UserRepository repo;

    public void run() {

        if (repo==null){
            System.out.println("\n>>> É NULL !!!!! :(\n");
        }

    }
    
}

        