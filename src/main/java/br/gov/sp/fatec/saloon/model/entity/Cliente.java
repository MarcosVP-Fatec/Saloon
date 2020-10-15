package br.gov.sp.fatec.saloon.model.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.tool.Texto;
import br.gov.sp.fatec.saloon.model.tool.Validador;

@Entity
@Table(name = "cli_cliente")
@AttributeOverride(name = "id", column = @Column(name = "cli_id"))
public class Cliente extends GeneratorId {

    @Column(name = "cli_cpf_cnpj")          private String cpf_cnpj;        //14
    @Column(name = "cli_nome")              private String nome;            //80
    @Column(name = "cli_tel_ddd")           private String tel_ddd;         //2
    @Column(name = "cli_tel_numero")        private String tel_numero;      //10

    //A chave estrangeira não se mapeia diretamente
    //x to y => x é da entidade atual.
    //No JoinColumn definir o nome da coluna fk desta entidade.
    @OneToOne(fetch = FetchType.LAZY)    
    @JoinColumn(name = "cli_pco_id")        private Parceiro parceiro;

    // CONSTRUTORES
    public Cliente(){}
    public Cliente( String   cpf_cnpj
                  , String   nome
                  , String   tel_ddd
                  , String   tel_numero
                  , Parceiro parceiro){
        this(cpf_cnpj,nome,tel_ddd,tel_numero);
        setParceiro(parceiro);
    }
    public Cliente( String   cpf_cnpj
                  , String   nome
                  , String   tel_ddd
                  , String   tel_numero){
        setCpf_cnpj(cpf_cnpj);
        setNome(nome);
        setTel_ddd(tel_ddd);
        setTel_numero(tel_numero);
    }
    public Cliente( String   cpf_cnpj
                  , String   nome){
        setCpf_cnpj(cpf_cnpj);
        setNome(nome);
    }
    
    // GETTERS AND SETTERS
    public String getCpf_cnpj()                 { return cpf_cnpj;                              }
    public void setCpf_cnpj(String cpf_cnpj)    { Validador.isCpfOrCnpj(cpf_cnpj);
                                                  this.cpf_cnpj = cpf_cnpj;                     }
    public String getNome()                     { return nome;                                  }
    public void setNome(String nome)            { this.nome = Texto.left(nome,80);              }
    public String getTel_ddd()                  { return tel_ddd;                               }
    public void setTel_ddd(String tel_ddd)      { this.tel_ddd = Texto.left(tel_ddd,2);         }
    public String getTel_numero()               { return tel_numero;                            }
    public void setTel_numero(String tel_numero){ this.tel_numero = Texto.left(tel_numero,10);  }
    public Parceiro getParceiro()               { return parceiro;                              }
    public void setParceiro(Parceiro parceiro)  { this.parceiro = parceiro;                     }

}