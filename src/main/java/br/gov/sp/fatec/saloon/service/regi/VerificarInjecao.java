package br.gov.sp.fatec.saloon.service.regi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.saloon.model.repository.regi.UserRepository;

@Service("verificarInjecao")
public class VerificarInjecao {

    @Autowired
    private UserRepository repo;

    public void run() {

        if (repo==null){
            System.out.println("\nÉ NULL !!!!! :(\n");
        }

    }
    

}
