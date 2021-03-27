package br.gov.sp.fatec.saloon.model.entity.laun;       

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.comm.GeneratorId;
import br.gov.sp.fatec.saloon.model.entity.regi.Alugavel;
import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;
import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;
import br.gov.sp.fatec.saloon.model.entity.stat.AlugavelTipo;
import br.gov.sp.fatec.saloon.model.entity.stat.ContratoMotivo;
import br.gov.sp.fatec.saloon.model.entity.stat.MesAno;
import br.gov.sp.fatec.saloon.model.entity.stat.UsuarioNivel;
import br.gov.sp.fatec.saloon.model.repository.laun.ContratoRepository;
import br.gov.sp.fatec.saloon.model.repository.regi.AlugavelRepository;
import br.gov.sp.fatec.saloon.model.repository.regi.ClienteRepository;
import br.gov.sp.fatec.saloon.model.repository.regi.ProprietarioRepository;
import br.gov.sp.fatec.saloon.model.repository.stat.UsuarioNivelRepository;
import br.gov.sp.fatec.saloon.model.tool.Texto;

@Table(name = "ctt_contrato")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "ctt_id"))
public class Contrato extends GeneratorId {

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private ProprietarioRepository proprietarioRepo;

    @Autowired
    private AlugavelRepository alugavelRepo;

    @Autowired
    private ContratoRepository contratoRepo;

    @Autowired
    private UsuarioNivelRepository usuarioNivelRepo;

    @Column(name = "ctt_data")                  private Date        data;
    @Column(name = "ctt_reserva_paga")          private BigDecimal  reservaPaga;    //10,2
    
    @Column(name = "ctt_festejo_nomes")         private String      festejoNomes;   //4000
    @Column(name = "ctt_festejo_dia")           private int         festejoDia;     //2

    //A chave estrangeira não se mapeia diretamente
    //x to y => x é da entidade atual.
    //No JoinColumn definir o nome da coluna fk desta entidade.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ctt_cli_id")            private Cliente     cliente;      

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ctt_alu_id")            private Alugavel    alugavel;      

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ctt_festejo_mes_id")    private MesAno      festejoMes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ctt_ctm_id")            private ContratoMotivo contratoMotivo;

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

    // GETTERS AND SETTERS
    public Date getData()                               { return data;                      }
    public void setData(Date data)                      { this.data = data;                 }
    public BigDecimal getReservaPaga()                  { return reservaPaga;               }
    public void setReservaPaga(BigDecimal reservaPaga)  { this.reservaPaga = reservaPaga;   }
    public String getFestejoNomes()                     { return festejoNomes;              }
    public void setFestejoNomes(String festejoNomes)    { this.festejoNomes = Texto.left(festejoNomes, 4000); }
    public int getFesjetoDia()                          { return festejoDia;                }
    public void setfestejoDia(int festejoDia)           { this.festejoDia = festejoDia;     }
    public MesAno getFestejoMes()                       { return festejoMes;                }
    public void setFestejoMes(MesAno festejoMes)        { this.festejoMes = festejoMes;     }
    public ContratoMotivo getContratoMotivo()           { return contratoMotivo;            }
    public void setContratoMotivo(ContratoMotivo contratoMotivo) { this.contratoMotivo = contratoMotivo;    }
    public Cliente getCliente()                         { return cliente;                   }
    public void setCliente(Cliente cliente)             { this.cliente = cliente;           }
    public Alugavel getAlugavel()                       { return alugavel;                  }
    public void setAlugavel(Alugavel alugavel)          { this.alugavel = alugavel;         }
    
}