package br.gov.sp.fatec.saloon.model.entity.stat;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;

import br.gov.sp.fatec.saloon.model.entity.comm.GeneratorId;
import br.gov.sp.fatec.saloon.model.tool.Texto;

@AttributeOverride(name = "id", column=@Column(name="cnt_id"))
public class ContratoMotivo extends GeneratorId {

    @Column(name="cnt_descr")           private String descr;

    // CONSTRUTOR
    public ContratoMotivo(){}
    public ContratoMotivo(String descr){ setDescr(descr); }

    // GETTERS AND SETTERS
    public String getDescr()          { return descr;                    }
    public void setDescr(String desc) { this.descr = Texto.left(descr,30);}

}