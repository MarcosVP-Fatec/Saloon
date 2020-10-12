package br.gov.sp.fatec.saloon.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.tool.Data;

@Table(name = "pro_proprietario")
@Entity
@PrimaryKeyJoinColumn(name = "pro_usu_id")
public class Proprietario extends UsuarioGeral {
    
    @Column(name = "pro_dt_inicio")             private Date dtInicio;
    @Column(name = "pro_dt_limite")             private Date dtLimite;

    // CONSTRUTORES
    public Proprietario(){ setDtInicio(); }
    public Proprietario(String apelido
                       ,String email
                       ,String senha
                       ,String nome
                       ,Date dtNascimento
                       ,String cpf){
        super( apelido
              ,email
              ,senha
              ,nome
              ,dtNascimento
              ,cpf);

        setDtInicio();
    }
    public Proprietario(String apelido
                       ,String email
                       ,String senha
                       ,String nome
                       ,Date dtNascimento
                       ,String cpf
                       ,Date dtInicio){

        super( apelido
              ,email
              ,senha
              ,nome
              ,dtNascimento
              ,cpf);

        setDtInicio(dtInicio);

    }

    // Getters and Setters
    public Date getDtInicio()              { return this.dtInicio;          }
    public void setDtInicio()              { this.dtInicio = Data.today();  }
    public void setDtInicio(Date dtInicio) { this.dtInicio = dtInicio;      }
    public Date getDtLimite()              { return this.dtLimite;          }
    public void setDtLimite(Date dtLimite) { this.dtLimite = dtLimite;      }
    public void setDtLimite(int dias)      { this.dtLimite = Data.dataSomaDias(Data.today(), dias); }

}