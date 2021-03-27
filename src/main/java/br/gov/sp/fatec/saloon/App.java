package br.gov.sp.fatec.saloon;

import java.text.ParseException;

/**
 * @App Saloon
 *
 *      Usar a expressão abaixo para executar pelo maven 
 *      mvn exec:java -Dexec.mainClass="br.gov.sp.fatec.saloon.App"
 * 
 */

public class App {

    public static void main(String[] args) throws ParseException
    {
/*
        //MvpTesteDesenvolvimento.run();
        Contrato ctr = new Contrato().novoContrato("11111111111"
                                                  ,"Joana D'arck"
                                                  ,"12"
                                                  ,"987654321"
                                                  ,"PRO_RICO"
                                                  ,"prorico@saloon.br"
                                                  ,"123"
                                                  ,Data.toDate("31/12/1960")
                                                  ,"22222222222"
                                                  ,"SALÃO FESTA ANIMADA"
                                                  ,"RUA DO SOBE E DESCE, Nº QUE NUNCA APARECE"
                                                  ,250
                                                  ,new BigDecimal(750.00)
                                                  ,Data.today()
                                                  ,new BigDecimal(100.00));

        System.out.println("---------------------------------");
        System.out.println("Exemplo controle transacional");
        System.out.println("---------------------------------");
        System.out.println(ctr.getId());
*/
    }

}

/*
    // Demonstração controle transacional
    @Transactional
    public Contrato novoContrato(String     cli_cpf
                                ,String     cli_nome
                                ,String     cli_ddd
                                ,String     cli_tel
                                ,String     pro_apelido
                                ,String     pro_email
                                ,String     pro_senha
                                ,Date       pro_nascimento
                                ,String     pro_cpf
                                ,String     alu_descr
                                ,String     alu_endereco
                                ,int        alu_capacidade
                                ,BigDecimal alu_valor
                                ,Date       ctt_data
                                ,BigDecimal ctt_reservaPaga ){

        Cliente cliente = new Cliente();
        cliente.setCpf_cnpj(cli_cpf);
        cliente.setNome(cli_nome);
        cliente.setTelDdd(cli_ddd);
        cliente.setTelNumero(cli_tel);

        Proprietario proprietario = new Proprietario();
        proprietario.setApelido(pro_apelido);
        proprietario.setEmail(pro_email);
        proprietario.setSenha(pro_senha);
        proprietario.setNome(pro_apelido);
        proprietario.setDtNascimento(pro_nascimento);
        proprietario.setCpf(pro_cpf);

        // usuarioNivelRepo.buscarPorId(2L).setId(2L);
        // usuarioNivelRepo.buscarPorId(2L).setDescr("Proprietário");
        Long nivel = 2L;
        usuarioNivelRepo.buscarPorId(nivel);
        proprietario.setUsuarioNivel(usuarioNivelRepo.buscarPorId(2L));

        AlugavelTipo alugavelTipo = new AlugavelTipo();
        alugavelTipo.setId(1L);
        alugavelTipo.setDescr("Salão de Festas");
        
        Alugavel alugavel = new Alugavel();
        alugavel.setDescr(alu_descr);
        alugavel.setAlugavelTipo(alugavelTipo);
        alugavel.setEndereco(alu_endereco);
        alugavel.setCapacidade(alu_capacidade);
        alugavel.setValor(alu_valor);
        alugavel.setProprietario(proprietario);

        ContratoMotivo contratoMotivo = new ContratoMotivo();
        contratoMotivo.setId(1L);
        contratoMotivo.setDescr("Aniversário");

        Contrato contrato = new Contrato();
        contrato.setCliente(cliente);
        contrato.setAlugavel(alugavel);
        contrato.setData(ctt_data);
        contrato.setReservaPaga(ctt_reservaPaga);
        contrato.setContratoMotivo(contratoMotivo);

        //return contratoServiceRepo.persist(contrato, cliente, alugavel);
        if (cliente.getId() != null && clienteRepo.existsByNome(cliente.getNome())) {
            cliente = clienteRepo.findByNome(cliente.getNome());
        } else {
            cliente.setId(null);
            clienteRepo.save(cliente);
        }
        
        if (proprietarioRepo.existsByApelido(alugavel.getProprietario().getApelido())){
            alugavel.setProprietario( proprietarioRepo.findByApelido(alugavel.getProprietario().getApelido()));
        } else {
            alugavel.getProprietario().setId(null);
            proprietarioRepo.save(alugavel.getProprietario());
        }

        if (alugavelRepo.existsByDescr(alugavel.getDescr())){
            alugavel = alugavelRepo.findByDescr(alugavel.getDescr());
        } else {
            alugavel.setId(null);
            alugavelRepo.save(alugavel);
        }

        contrato.setCliente(cliente);
        contrato.setAlugavel(alugavel);
        return contratoRepo.save(contrato);

    }

*/
