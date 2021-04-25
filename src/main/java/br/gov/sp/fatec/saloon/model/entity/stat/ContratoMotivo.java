package br.gov.sp.fatec.saloon.model.entity.stat;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.entity.comm.GeneratorId;
import br.gov.sp.fatec.saloon.model.tool.Texto;

/**
 * @apiNote Entidade ContratoMotivo
 *          Esta tabela é para indicar o motivo do contrato
 *          Já vem com dados, mas pode receber novos.
 * @version 1.1 - Spring-boot
  */
@Table(name = "ctm_contrato_motivo")
@Entity
@AttributeOverride(name = "id", column=@Column(name="ctm_id"))
//@PrimaryKeyJoinColumn(name = "cmt_id")
public class ContratoMotivo extends GeneratorId {

    @Column(name="ctm_descr")           private String descr;

    // GETTERS AND SETTERS
    public String getDescr()          { return descr;                    }
    public void setDescr(String descr){ this.descr = Texto.left(descr,30);}

}