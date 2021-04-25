package br.gov.sp.fatec.saloon.model.entity.stat;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.entity.comm.GeneratorId;
import br.gov.sp.fatec.saloon.model.tool.Texto;

/**
 * @apiNote Entidade MesAno
 *          Esta tabela é estática e não será cadastrada por usuário comum
 *          Os dados aqui contidos acompanham a programação por isso não há geração automática do id.
 * @version 1.1 - Spring-boot
 * 
 */
@Table(name = "mes_ano")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "mes_id"))
//@PrimaryKeyJoinColumn(name = "mes_id")
public class MesAno extends GeneratorId {

    @Column(name = "mes_numero")    private String numero;
    @Column(name = "mes_descr")     private String descr;

    // GETTERS AND SETTERS
    public String getNumero()           { return numero;                    }
    public void setNumero(String mes)   { this.numero = mes;                }
    public String getDescr()            { return descr;                     }
    public void setDescr(String descr)  { this.descr = Texto.left(descr,9); }
    
}