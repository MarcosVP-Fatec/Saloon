package br.gov.sp.fatec.saloon.model.entity.regi;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.entity.comm.GeneratorId;
import br.gov.sp.fatec.saloon.model.entity.laun.Contrato;
import br.gov.sp.fatec.saloon.model.tool.Texto;
import br.gov.sp.fatec.saloon.model.tool.Validador;

@Entity
@Table(name = "cli_cliente")
@AttributeOverride(name = "id", column = @Column(name = "cli_id"))
//@PrimaryKeyJoinColumn(name = "cli_id")  //Faz ligação com o Contrato
public class Cliente extends GeneratorId {

    @Column(name = "cli_cpf_cnpj"  , length = 14, nullable = false, unique = true ) private String cpf_cnpj;
    @Column(name = "cli_nome"      , length = 80, nullable = false, unique = true ) private String nome;
    @Column(name = "cli_tel_ddd"   , length =  2, nullable = true , unique = false) private String telDdd;
    @Column(name = "cli_tel_numero", length = 10, nullable = true , unique = false) private String telNumero;

    // @Column(name = "cli_cpf_cnpj"  ) private String cpf_cnpj;
    // @Column(name = "cli_nome"      ) private String nome;
    // @Column(name = "cli_tel_ddd"   ) private String telDdd;
    // @Column(name = "cli_tel_numero") private String telNumero;

    //A chave estrangeira não se mapeia diretamente
    //x to y => x é da entidade atual.
    //No JoinColumn definir o nome da coluna fk desta entidade.
    @OneToOne(fetch = FetchType.LAZY)    
    @JoinColumn(name = "cli_pco_id")        private Parceiro parceiro;

    //Cliente que é atendido por um parceiro
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "clientes") 
    private Set<Parceiro> parceiros;

    //Usar o Set poque não pode repetir o Contrato
    //O Hibernate não trabalha bem com List
    //LAZY porque não quero carregar todos os Contratos do Cliente
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    private Set<Contrato> contratosDoCliente;

    // CONSTRUTORES
    public Cliente(){}
    public Cliente( Long    id
                  , String  cpf_cnpj
                  , String  nome
                  , String  telDdd
                  , String  telNumero){
        this.setId(id);                      
        this.setCpf_cnpj(cpf_cnpj);
        this.setNome(nome);
        this.setTelDdd(telDdd);
        this.setTelNumero(telNumero);
    }

    // GETTERS AND SETTERS
    public String getCpf_cnpj()                 { return cpf_cnpj;                              }
    public void setCpf_cnpj(String cpf_cnpj)    { Validador.isCpfOrCnpj(cpf_cnpj);
                                                  this.cpf_cnpj = cpf_cnpj;                     }
    public String getNome()                     { return nome;                                  }
    public void setNome(String nome)            { this.nome = Texto.left(nome,80);              }
    public String getTelDdd()                   { return telDdd;                                }
    public void setTelDdd(String telDdd)        { this.telDdd = Texto.left(telDdd,2);           }
    public String getTelNumero()                { return telNumero;                             }
    public void setTelNumero(String telNumero)  { this.telNumero = Texto.left(telNumero,10);    }
    public Parceiro getParceiro()               { return parceiro;                              }
    public void setParceiro(Parceiro parceiro)  { this.parceiro = parceiro;                     }
    public Set<Parceiro> getParceiros()         { return parceiros;                             }

    public Set<Contrato> getContratosDoCliente() {
        return contratosDoCliente;
    }

}