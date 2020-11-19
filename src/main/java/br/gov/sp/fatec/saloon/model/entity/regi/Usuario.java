package br.gov.sp.fatec.saloon.model.entity.regi;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.entity.comm.GeneratorId;

@Table(name = "usu_usuario")
@Entity
@AttributeOverride(name = "id", column=@Column(name="usu_id"))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "usu_pj_ou_pf")
public abstract class Usuario extends GeneratorId{

    @Column(name = "usu_apelido")           private String  apelido;
    @Column(name = "usu_email")             private String  email;
    @Column(name = "usu_senha")             private String  senha;
    @Column(name = "usu_nivel")             private Long    usuarioNivel;

    // CONSTRUTORES
    public Usuario(){}
    public Usuario( String apelido
                  , String email
                  , String senha
                  , Long   usuarioNivel){

        setApelido(apelido);
        setEmail(email);
        setSenha(senha);
        setUsuarioNivel(usuarioNivel);
    }

    // GETTERS AND SETTERS
    public String getApelido()                          { return apelido;                    }
    public void setApelido(String apelido)              { this.apelido = apelido;            }
    public String getEmail()                            { return email;                      }
    public void setEmail(String email)                  { this.email = email;                }
    public String getSenha()                            { return senha;                      }
    public void setSenha(String senha)                  { this.senha = senha;                }
    public Long getUsuarioNivel(   )                    { return usuarioNivel;               }
    public void setUsuarioNivel(Long usuarioNivel)      { this.usuarioNivel = usuarioNivel;  }

}