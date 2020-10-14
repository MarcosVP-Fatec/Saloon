package br.gov.sp.fatec.saloon.model.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.tool.Texto;

@Entity
@Table(name = "alt_alugavel_tipo")
@AttributeOverride(name = "id", column=@Column(name="alt_id"))
@PrimaryKeyJoinColumn(name = "alt_id")
public class AlugavelTipo extends GeneratorId {

    @Column(name = "alt_descr")     private String descr;

    //Usar o Set poque não pode repetir o trabalho
    //O Hibernate não trabalha bem com List
    //LAZY porque não quero carregar todos os Alugaveis do tipo
    //
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "alugavelTipo")
    private Set<Alugavel> alugaveisDoProprietario;

    // CONSTRUTORES
    public AlugavelTipo(){}
    public AlugavelTipo( String descr ){ setDescr(descr); }

    // GETTERS AND SETTERS
    public String getDescr()                         {  return descr;                    }
    public void setDescr(String descr)               { this.descr = Texto.left(descr,20);}
    public Set<Alugavel> getAlugaveisDoProprietario(){ return alugaveisDoProprietario;   }
    public void setAlugaveisDoProprietario(Set<Alugavel> alugaveisDoProprietario)
                                                     { this.alugaveisDoProprietario = alugaveisDoProprietario;}

}