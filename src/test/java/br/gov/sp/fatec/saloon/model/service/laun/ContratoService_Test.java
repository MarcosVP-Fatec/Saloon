package br.gov.sp.fatec.saloon.model.service.laun;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.laun.Contrato;
import br.gov.sp.fatec.saloon.model.entity.regi.Alugavel;
import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.service.laun.ContratoService;
import br.gov.sp.fatec.saloon.service.regi.AlugavelService;
import br.gov.sp.fatec.saloon.service.regi.ProprietarioService;

@SpringBootTest
@Transactional
@Rollback
public class ContratoService_Test {

    @Autowired
    private ProprietarioService     proprietarioService;

    @Autowired
    private ContratoService         contratoService;

    @Autowired
    private AlugavelService         alugavelService;

    @Test
    public void testeContratoCliente_Inserir() throws ParseException {

        /**
         * O proprietário e o alugável já devem existir para a realização deste cadastro.
         * Portanto esta parte do teste será realizada fora do controle transacional
         */

        Proprietario proprietario = proprietarioService.persist( "VICENTE"
                                                               , "vicente@saloon.br"
                                                               , "123"
                                                               , "Vicente dos Santos"
                                                               , Data.toDate("01/04/1990")
                                                               , "11111111111");

        Alugavel alugavel = alugavelService.persist( "Salão da Mama"
                                                   , proprietario.getId()
                                                   , 1L
                                                   , "Rua Nove de Julho, 789654"
                                                   , 120
                                                   , new BigDecimal(370));

        Contrato contrato = contratoService.novoClienteContrato( "22222222222"          //cliCpf
                                                               , "Adalberto Salesiano"  //cliNome
                                                               , "12"                   //cliTelDdd
                                                               , "39998888"             //cliTelNumero
                                                               , Data.today()           //cttData
                                                               , new BigDecimal(100) // cttReservaPaga
                                                               , "Júlia e Júlio"        //cttFestejoNomes
                                                               , 6                      //cttFestejoDia
                                                               , 2L                     //cttFestejoMes
                                                               , 1L                     //cttContratoMotivo
                                                               , proprietario           //Proprietario
                                                               , alugavel);

        assertNotNull(contrato.getId());

    }

}