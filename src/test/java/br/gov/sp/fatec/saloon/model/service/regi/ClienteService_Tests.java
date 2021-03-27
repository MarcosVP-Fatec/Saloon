package br.gov.sp.fatec.saloon.model.service.regi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;
import br.gov.sp.fatec.saloon.model.repository.regi.ClienteRepository;
import br.gov.sp.fatec.saloon.service.regi.ClienteService;

@SpringBootTest
@Transactional
@Rollback
public class ClienteService_Tests {
    
    @Autowired
    private ClienteRepository       clienteRepo;

    @Autowired
    private ClienteService          clienteServiceRepo;

    final String    CPF_1       = "44444444444";
    final String    NOME_1      = "#TESTE_NOME_CLIENTE_1";
    final String    NOME_2      = "#TESTE_NOME_CLIENTE_2";
    final String    TEL_DDD_1   = "12";
    final String    TEL_1       = "999998888";
    final String    TEL_2       = "977776666";

    @Test
    void testeClienteServiceIncluir()  {
        assertFalse(clienteRepo.existsByNome(NOME_1));
        this.criaClienteTeste();
        assertTrue(clienteRepo.existsByNome(NOME_1));
    }

  	@Test
	void testeClienteServiceAlterar() {
        Cliente cliente = this.criaClienteTeste();
        assertTrue(clienteRepo.existsByNome(NOME_1));
        cliente.setNome(NOME_2);
        clienteRepo.save(cliente);
        clienteRepo.flush();
        assertFalse(clienteRepo.existsByNome(NOME_1));
        assertTrue(clienteRepo.existsByNome(NOME_2));
    }    

    @Test
    void testeClienteServiceExcluir() {
        clienteServiceRepo.delete(this.criaClienteTeste().getId());
        assertFalse(clienteRepo.existsByNome(NOME_1));
    }

    /*
     * Função padrão de criação de usuário
     */
    private Cliente criaClienteTeste(){

        Cliente cliente = new Cliente();

        cliente.setCpf_cnpj(CPF_1);
        cliente.setNome(NOME_1);
        cliente.setTelDdd(TEL_DDD_1);
        cliente.setTelNumero(TEL_1);

        return clienteRepo.save(cliente);
    }
}


