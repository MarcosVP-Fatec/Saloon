package br.gov.sp.fatec.saloon.model.entity.regi;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.saloon.controller.View;
import br.gov.sp.fatec.saloon.model.tool.Data;

@Table(name = "pro_proprietario")
@Entity
@PrimaryKeyJoinColumn(name = "pro_usu_id") //Este parâmetro indica que não teremos mapeamento do campo na classe por ser pk aqui e fk de outra entidade.
public class Proprietario extends Usuario {

    @JsonView({View.ProprietarioApelidoUsuario.class
              ,View.UsuarioInclusao.class})
    @Column(name = "pro_dt_inicio")             private Date dtInicio;
    @JsonView({View.ProprietarioApelidoUsuario.class
              ,View.UsuarioInclusao.class})
    @Column(name = "pro_dt_limite")             private Date dtLimite;

    //Usar o Set poque não pode repetir o trabalho
    //O Hibernate não trabalha bem com List
    //LAZY porque não quero carregar todos os Alugaveis do proprietário

    @JsonView(View.ProprietarioApelidoUsuario.class)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "proprietario")
    private Set<Alugavel> alugaveis;

    //CONSTRUTORES
    public Proprietario(){
        this.setDtInicio(Data.today());
    }

    // Getters and Setters
    public Date getDtInicio()                        { return this.dtInicio;          }
    public void setDtInicio()                        { this.dtInicio = Data.today();  }
    public void setDtInicio(Date dtInicio)           { this.dtInicio = dtInicio;      }
    public Date getDtLimite()                        { return this.dtLimite;          }
    public void setDtLimite(Date dtLimite)           { this.dtLimite = dtLimite;      }
    public void setDtLimite(int dias)                { this.dtLimite = Data.addD(Data.today(), dias); }
    public Set<Alugavel> getAlugaveis()               { return this.alugaveis;        }

}