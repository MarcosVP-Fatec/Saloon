package br.gov.sp.fatec.saloon.model.entity.laun;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.regi.Alugavel;
import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;
import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;
import br.gov.sp.fatec.saloon.model.repository.laun.ContratoRepository;
import br.gov.sp.fatec.saloon.model.repository.regi.ClienteRepository;
import br.gov.sp.fatec.saloon.model.repository.stat.ContratoMotivoRepository;
import br.gov.sp.fatec.saloon.model.repository.stat.MesAnoRepository;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.service.regi.AlugavelService;
import br.gov.sp.fatec.saloon.service.regi.ProprietarioService;

@SpringBootTest
@Transactional
@Rollback
public class Contrato_Tests {

    @Autowired
    private ClienteRepository       clienteRepo;

    @Autowired
    private ProprietarioService      proprietarioServiceRepo;

    @Autowired
    private AlugavelService          alugavelServiceRepo;

    @Autowired
    private ContratoMotivoRepository contratoMotivoRepo;

    @Autowired
    private MesAnoRepository         mesAnoRepo;

    @Autowired
    private ContratoRepository       contratoRepo;

    @Test
    void testeContratoIncluir() throws ParseException {
        Contrato contrato = contratoRepo.save(this.criaContratoTeste());
        contratoRepo.save(contrato);
        contratoRepo.flush();
        assertNotNull(contrato.getId());
    }

    @Test
    void testeContratoAlterar() throws ParseException {

        int qtd = contratoRepo.findByFestejoNomesContainsIgnoreCase("## João, José, Maria e Joana ##").size();
        Contrato contrato = contratoRepo.save(this.criaContratoTeste());
        contrato.setFestejoNomes("## João, José, Maria e Joana ##");
        contratoRepo.save(contrato);
        contratoRepo.flush();
        assertEquals(qtd+1, contratoRepo.findByFestejoNomesContainsIgnoreCase("## João, José, Maria e Joana ##").size());
    }

    @Test
    void testeContratoDeletar() throws ParseException {
        Contrato contrato = contratoRepo.save(this.criaContratoTeste());
        Long idContrato = contrato.getId();
        contratoRepo.delete(contrato);
        contratoRepo.flush();
        assertFalse(contratoRepo.existsById(idContrato));
    }

    @Test
    void listaPorProprietarioEClienteTeste() throws ParseException {
        Contrato contrato = contratoRepo.save(this.criaContratoTeste());
        int qtd = contratoRepo.listaPorAlugavelCliente(contrato.getAlugavel().getId(), contrato.getCliente().getId()).size();
        contratoRepo.delete(contrato);
        contratoRepo.flush();
        assertEquals(qtd-1, contratoRepo.listaPorAlugavelCliente(contrato.getAlugavel().getId(), contrato.getCliente().getId()).size());
    }

    /*
     * Função padrão de criação de contrato
     */
    private Contrato criaContratoTeste() throws ParseException {

        Cliente cliente = new Cliente();
        cliente.setCpf_cnpj("22222222222");
        cliente.setNome("#NOME_CLI_CONTRATO");
        clienteRepo.save(cliente);
        clienteRepo.flush();

        Proprietario proprietario = proprietarioServiceRepo.persist("PROP_APELIDO_CTR", "propctr@saloon.br", "123", "PROP_NOME_CTR", Data.toDate("12/04/1969"), "33333333333");

        Alugavel alugavel = alugavelServiceRepo.persist("SALÃO GRANDE FESTA"
                                                       , proprietario.getId()
                                                       , 1L
                                                       , "RUA DO SOBE QUE DESCE"
                                                       , 100
                                                       , new BigDecimal(590.00) );
        
        Contrato contrato = new Contrato();

        contrato.setCliente(cliente);
        contrato.setAlugavel(alugavel);
        contrato.setData(Data.toDate("12/04/2055"));
        contrato.setReservaPaga(new BigDecimal(100));
        contrato.setContratoMotivo(contratoMotivoRepo.buscarPorId(1L));
        contrato.setFestejoNomes("João, José e Maria");
        contrato.setfestejoDia(12);
        contrato.setFestejoMes(mesAnoRepo.buscarPorId(4L));

        return contrato;
    }

}

