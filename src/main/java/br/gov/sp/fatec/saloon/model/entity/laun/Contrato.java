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

import br.gov.sp.fatec.saloon.model.entity.comm.GeneratorId;
import br.gov.sp.fatec.saloon.model.entity.regi.Alugavel;
import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;
import br.gov.sp.fatec.saloon.model.entity.stat.ContratoMotivo;
import br.gov.sp.fatec.saloon.model.entity.stat.MesAno;
import br.gov.sp.fatec.saloon.model.tool.Texto;

@Table(name = "ctt_contrato")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "ctt_id"))
public class Contrato extends GeneratorId {

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