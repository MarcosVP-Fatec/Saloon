package br.gov.sp.fatec.saloon.model.entity.regi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.repository.regi.ClienteRepository;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.service.regi.ParceiroService;

@SpringBootTest
@Transactional
@Rollback
public class Cliente_Tests {

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private ParceiroService parceiroServiceRepo;

    final String CPF_1 = "44444444444";
    final String NOME_1 = "#TESTE_NOME_CLIENTE_1";
    final String NOME_2 = "#TESTE_NOME_CLIENTE_2";
    final String TEL_DDD_1 = "12";
    final String TEL_1 = "999998888";
    final String TEL_2 = "977776666";

    @Test
    void testeClienteIncluir() throws ParseException {
        assertFalse(clienteRepo.existsByNome(NOME_1));
        Cliente cliente = clienteRepo.save(this.criaClienteTeste());
        clienteRepo.flush();
        assertTrue(clienteRepo.existsByNome(NOME_1));
        assertNotNull(cliente.getId());
        assertEquals(CPF_1, cliente.getCpf_cnpj());
        assertEquals(NOME_1, cliente.getNome());
        assertEquals(TEL_DDD_1, cliente.getTelDdd());
        assertEquals(TEL_1, cliente.getTelNumero());
        assertNotNull(cliente.getParceiro());
        assertNull(cliente.getParceiros());
        assertNull(cliente.getContratosDoCliente());
    }

    @Test
    void testeClienteAlterar() throws ParseException {

        Cliente cliente = clienteRepo.save(this.criaClienteTeste());
        assertTrue(clienteRepo.existsByNome(NOME_1));
        cliente.setNome(NOME_2);
        clienteRepo.save(cliente);
        clienteRepo.flush();
        assertFalse(clienteRepo.existsByNome(NOME_1));
        assertTrue(clienteRepo.existsByNome(NOME_2));
    }

    @Test
    void testeClienteDelete() throws ParseException {
        clienteRepo.delete(clienteRepo.save(this.criaClienteTeste()));
        assertFalse(clienteRepo.existsByNome(NOME_1));
    }

    /*
     * Função padrão de criação de cliente
     */
    private Cliente criaClienteTeste() throws ParseException {

        Parceiro parceiro = parceiroServiceRepo.persist( null
                                                       , "#PARC_TESTE_CLI"
                                                       , "#parctestecli@saloon.br"
                                                       , "123"
                                                       , "#NOME_PARCEIRO_CLI"
                                                       , Data.toDate("12/04/1969")
                                                       , "22222222222");
        
        Cliente cliente = new Cliente(null, CPF_1, NOME_1, TEL_DDD_1, TEL_1);

        cliente.setParceiro(parceiro);

        return cliente;
    }
    
}

