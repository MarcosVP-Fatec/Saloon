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

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.saloon.controller.View;
import br.gov.sp.fatec.saloon.exception.ValidacaoException;
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
@Entity
@Table(name = "usu_usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@AttributeOverride(name = "id", column=@Column(name="usu_id"))
public class Usuario extends GeneratorId{

    @JsonView({View.UsuarioProprietario.class
              ,View.ProprietarioApelidoUsuario.class
              ,View.UsuariosResumido.class
              ,View.UsuarioInclusao.class})
    @Column(name = "usu_apelido")           private String       apelido;
    @JsonView({View.UsuarioProprietario.class
              ,View.ProprietarioApelidoUsuario.class
              ,View.UsuariosResumido.class
              ,View.UsuarioInclusao.class})
    @Column(name = "usu_nome")              private String       nome;    
    @JsonView({View.UsuarioProprietario.class
              ,View.ProprietarioApelidoUsuario.class
              ,View.UsuarioInclusao.class})
    @Column(name = "usu_email")             private String       email;
    @Column(name = "usu_senha")             private String       senha;

    @JsonView({View.UsuarioInclusao.class,View.ProprietarioApelidoUsuario.class})
    @Column(name = "usu_dt_nascimento")     private Date         dtNascimento;

    @JsonView({View.UsuarioInclusao.class,View.ProprietarioApelidoUsuario.class})
    @Column(name = "usu_cpf_cnpj")          private String       cpf;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JsonView({View.UsuarioProprietario.class
              ,View.UsuariosResumido.class
              ,View.UsuarioInclusao.class} )    
    @JoinColumn(name = "usu_nivel")         private UsuarioNivel usuarioNivel;

    // CONSTRUTORES
    public Usuario(){}
    public Usuario( String       apelido
                  , String       email
                  , String       senha
                  , String       nome
                  , Date         dtNascimento
                  , String       cpf
                  , UsuarioNivel nivelUsuario){

      this.setApelido(apelido);
      this.setEmail(email);
      this.setSenha(senha);
      this.setNome(nome);
      this.setDtNascimento(dtNascimento);
      this.setCpf(cpf);
      this.setUsuarioNivel(nivelUsuario);

    }
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
    public void setCpf(String cpf)                      { 
    	if (Validador.cpf(cpf)) {
    		this.cpf = cpf;   
    	} else {
    		throw new ValidacaoException("CPF inválido: \"" + cpf + "\"");
    	}
  	}

    public UsuarioNivel getUsuarioNivel()               { return this.usuarioNivel;                 }
    public void setUsuarioNivel(UsuarioNivel usuarioNivel) {this.usuarioNivel = usuarioNivel;       }

}

