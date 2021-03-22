package br.gov.sp.fatec.saloon.model.entity.regi;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.entity.comm.GeneratorId;
import br.gov.sp.fatec.saloon.model.entity.stat.UsuarioNivel;
import br.gov.sp.fatec.saloon.model.tool.Texto;
import br.gov.sp.fatec.saloon.model.tool.Validador;

/**
 * @apiNote Entidade Usuario
 *
 * @version 1.1 - Spring-boot
 * 
 */
@Table(name = "usu_usuario")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AttributeOverride(name = "id", column=@Column(name="usu_id"))
public class Usuario extends GeneratorId{

    @Column(name = "usu_apelido")           private String       apelido;
    @Column(name = "usu_nome")              private String       nome;    
    @Column(name = "usu_email")             private String       email;
    @Column(name = "usu_senha")             private String       senha;

    @Column(name = "usu_dt_nascimento")     private Date         dtNascimento;
    @Column(name = "usu_cpf_cnpj")          private String       cpf;

    @ManyToOne(fetch = FetchType.LAZY)      
    @JoinColumn(name = "usu_nivel")         private UsuarioNivel usuarioNivel;

    // GETTERS AND SETTERS
    public String getApelido()                          { return this.apelido;                      }
    public void setApelido(String apelido)              { this.apelido = apelido.toUpperCase();     }

    public String getNome()                             { return this.nome;                         }
    public void setNome(String nome)                    { this.nome = Texto.left(nome,80);          }

    public String getEmail()                            { return this.email;                        }
    public void setEmail(String email)                  { this.email = email;                       }

    public String getSenha()                            { return this.senha;                        }
    public void setSenha(String senha)                  { this.senha = senha;                       }

    public Date getDtNascimento()                       { return dtNascimento;                      }
    public void setDtNascimento(Date dtNascimento)      { this.dtNascimento = dtNascimento;         }

    public String getCpf()                              { return this.cpf;                          }
    public void setCpf(String cpf)                      { if (Validador.cpf(cpf)) this.cpf = cpf;   }

    public UsuarioNivel getUsuarioNivel()               { return this.usuarioNivel;                 }
    public void setUsuarioNivel(UsuarioNivel usuarioNivel) {this.usuarioNivel = usuarioNivel;       }

    

}

