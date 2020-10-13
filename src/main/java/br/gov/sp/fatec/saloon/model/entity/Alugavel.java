package br.gov.sp.fatec.saloon.model.entity;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.tool.Texto;

@Entity
@Table(name = "alu_alugavel")
@AttributeOverride(name = "id", column=@Column(name="alu_id"))
public class Alugavel extends GeneratorId {

    @Column(name = "alu_descr")                             private String      descr;       //50
    @Column(name = "alu_endereco")                          private String      endereco;    //500
    @Column(name = "alu_capacidade")                        private int         capacidade; 
    @Column(name = "alu_valor", precision = 10, scale = 2)  private BigDecimal  valor;       //10,2

    //A chave estrangeira não se mapeia diretamente
    //x to y => x é da entidade atual.
    //No JoinColumn definir o nome da coluna fk desta entidade.
    @ManyToOne(fetch = FetchType.LAZY)    //LAZY porque o proprietário tem muitas colunas
    @JoinColumn(name = "alu_pro_id")  private Proprietario proprietario;

    @ManyToOne(fetch = FetchType.EAGER)    //EAGER porque a tabela de tipo contém apenas a descrição
    @JoinColumn(name = "alu_alt_id")  private AlugavelTipo alugavelTipo;

    // CONSTRUTORES
    public Alugavel(){}
    public Alugavel( String         descr
                   , Proprietario   proprietario
                   , AlugavelTipo   alugavelTipo
                   , String         endereco
                   , int            capacidade
                   , BigDecimal     valor){

    }

    // GETTERS AND SETTERS
    public String getDescr()                                { return descr;                             }
    public void setDescr(String descr)                      { this.descr = Texto.left(descr,50);        }
    public String getEndereco()                             { return endereco;                          }
    public void setEndereco(String endereco)                { this.endereco = Texto.left(endereco,500); }
    public int getCapacidade()                              { return capacidade;                        }
    public void setCapacidade(int capacidade)               { this.capacidade = capacidade;             }
    public BigDecimal getValor()                            { return valor;                             }
    public void setValor(BigDecimal valor)                  { this.valor = valor;                       }
    public AlugavelTipo getAlugavelTipo()                   { return alugavelTipo;                      }
    public void setAlugavelTipo(AlugavelTipo alugavelTipo)  { this.alugavelTipo = alugavelTipo;         }
    public Proprietario getProprietario()                   { return proprietario;                      }
    public void setProprietario(Proprietario proprietario)  { this.proprietario = proprietario;         }
    
}