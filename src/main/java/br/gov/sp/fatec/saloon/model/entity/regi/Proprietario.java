package br.gov.sp.fatec.saloon.model.entity.regi;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gov.sp.fatec.saloon.model.tool.Data;

@Table(name = "pro_proprietario")
@Entity
@PrimaryKeyJoinColumn(name = "pro_usu_id")
public class Proprietario extends UsuarioGeral {
    
    @Column(name = "pro_dt_inicio")             private Date dtInicio;
    @Column(name = "pro_dt_limite")             private Date dtLimite;

    //Usar o Set poque não pode repetir o trabalho
    //O Hibernate não trabalha bem com List
    //LAZY porque não quero carregar todos os Alugaveis do proprietário
    //
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "proprietario")
    private Set<Alugavel> alugaveis;

    // CONSTRUTORES
    public Proprietario(){ 
        setDtInicio();
        setUsuarioNivel(2L);
    }
    public Proprietario(String apelido
                       ,String email
                       ,String senha
                       ,String nome
                       ,Date   dtNascimento
                       ,String cpf){
        super( apelido
              ,email
              ,senha
              ,nome
              ,dtNascimento
              ,cpf
              ,2L);

        setDtInicio();
    }
    public Proprietario(String apelido
                       ,String email
                       ,String senha
                       ,String nome
                       ,Date   dtNascimento
                       ,String cpf
                       ,Date   dtInicio){

        super( apelido
              ,email
              ,senha
              ,nome
              ,dtNascimento
              ,cpf
              ,2L);

        setDtInicio(dtInicio);

    }

    // Getters and Setters
    public Date getDtInicio()                        { return this.dtInicio;          }
    public void setDtInicio()                        { this.dtInicio = Data.today();  }
    public void setDtInicio(Date dtInicio)           { this.dtInicio = dtInicio;      }
    public Date getDtLimite()                        { return this.dtLimite;          }
    public void setDtLimite(Date dtLimite)           { this.dtLimite = dtLimite;      }
    public void setDtLimite(int dias)                { this.dtLimite = Data.dataSomaDias(Data.today(), dias); }
    public Set<Alugavel> getAlugaveis()              { return alugaveis;              }
    public void setAlugaveis(Set<Alugavel> alugaveis){ this.alugaveis = alugaveis;    }

}