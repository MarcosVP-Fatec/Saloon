package br.gov.sp.fatec.saloon.model.entity.stat;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.entity.comm.GeneratorId;
import br.gov.sp.fatec.saloon.model.tool.Texto;

@Entity
@Table(name = "niv_usuario")
@AttributeOverride(name = "id", column = @Column(name = "niv_id"))
@PrimaryKeyJoinColumn(name = "niv_id")
public class UsuarioNivel extends GeneratorId {

    @Column(name = "niv_descr")
    private String descr;

    // CONSTRUTOR
    public UsuarioNivel() {}
    public UsuarioNivel(String descr) { setDescr(descr); }

    // GETTERS AND SETTERS
    public String getDescr()           { return descr;    }
    public void setDescr(String descr) { this.descr = Texto.left(descr, 30); }

}