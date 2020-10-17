package br.gov.sp.fatec.saloon.model.entity.regi;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.entity.regi.UsuarioGeral;
import br.gov.sp.fatec.saloon.model.tool.Data;

@Table(name = "par_parceiro")
@Entity
@PrimaryKeyJoinColumn(name = "par_usu_id")
public class Parceiro extends UsuarioGeral {

    @Column(name = "par_dt_inicio")             private Date    dtInicio;
    @Column(name = "par_inativo")               private boolean inativo;
    
    // CONSTRUTORES
    public Parceiro(){ setDtInicio(); }
    public Parceiro(String apelido
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
              ,cpf);

        setDtInicio();
    }

    // GETTERS AND SETTERS
    public Date getDtInicio()              { return this.dtInicio;          }
    public void setDtInicio()              { this.dtInicio = Data.today();  }
    public void setDtInicio(Date dtInicio) { this.dtInicio = dtInicio;      }
    public boolean isInativo()             { return inativo;                }
    public void setInativo(boolean inativo){ this.inativo = inativo;        }
    
}