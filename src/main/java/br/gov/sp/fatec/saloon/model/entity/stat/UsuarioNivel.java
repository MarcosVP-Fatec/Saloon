package br.gov.sp.fatec.saloon.model.entity.stat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.entity.comm.GeneratorAudit;
import br.gov.sp.fatec.saloon.model.tool.Texto;

/**
 * @apiNote Entidade UsuarioNivel
 *          Esta tabela é estática e não será cadastrada por usuário comum
 *          Os dados aqui contidos acompanham a programação
 * @version 1.1 - Spring-boot
 * 
 */
@Entity
@Table(name = "niv_usuario")
public class UsuarioNivel extends GeneratorAudit {

    @Id
    @Column(name = "niv_id")
    private Long id;

    @Column(name = "niv_descr")
    private String descr;

/*
    @Column(name = "niv_adm")
    private boolean administrador;

    @Column(name = "niv_prop")
    private boolean proprietario;

    @Column(name = "niv_parc")
    private boolean parceiro;

    @Column(name = "niv_cli")
    private boolean cliente;
*/
    // GETTERS AND SETTERS
    
    public Long getId()                                 { return this.id;                       }
    public void setId(Long id)                          { this.id = id;                         }
    public String getDescr()                            { return this.descr;                    }
    public void setDescr(String descr)                  { this.descr = Texto.left(descr, 30);   }
/*
    public boolean getAdministrador()                   { return this.administrador;            }
    public void setAdministrador(boolean administrador) { this.administrador = administrador;   }

    public boolean getProprietario()                    { return this.proprietario;             }
    public void setProprietario(boolean proprietario)   { this.proprietario = proprietario;     }

    public boolean getParceiro()                        { return this.parceiro;                 }
    public void setParceiro(boolean parceiro)           { this.parceiro = parceiro;             }

    public boolean getCliente()                         { return this.cliente;                  }
    public void setCliente(boolean cliente)             { this.cliente = cliente;               }
*/
}