package br.gov.sp.fatec.saloon.model.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.tool.Texto;

@Entity
@Table(name = "alt_alugavel_tipo")
@AttributeOverride(name = "id", column=@Column(name="alt_id"))
public class AlugavelTipo extends GeneratorId {

    @Column(name = "alt_descr")     private String descr;

    // CONSTRUTORES
    public AlugavelTipo(){}
    public AlugavelTipo( String descr ){

    }

    // GETTERS AND SETTERS
    public String getDescr()            {  return descr;                    }
    public void setDescr(String descr)  { this.descr = Texto.left(descr,20);}

}