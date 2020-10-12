package br.gov.sp.fatec.saloon.model.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.tool.Texto;
import br.gov.sp.fatec.saloon.model.tool.Validador;

@Table(name = "usu_usuario")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AttributeOverride(name = "id", column=@Column(name="usu_id"))
public abstract class UsuarioGeral extends GeneratorId {
    
    @Column(name = "usu_apelido")           private String  apelido;
    @Column(name = "usu_email")             private String  email;
    @Column(name = "usu_senha")             private String  senha;

    @Column(name = "usu_nome")              private String  nome;    
    @Column(name = "usu_dt_nascimento")     private Date    dtNascimento;
    @Column(name = "usu_cpf_cnpj")          private String  cpf;

    // Construtores
    public UsuarioGeral(){}
    public UsuarioGeral( String apelido
                       , String email
                       , String senha
                       , String nome
                       , Date dtNascimento
                       , String cpf) {

        setApelido(apelido);
        setEmail(email);
        setSenha(senha);
        setNome(nome);
        setDtNascimento(dtNascimento);
        setCpf(cpf);

    }

    // Getters and Setters
    public String getApelido()              { return apelido;           }
    public void setApelido(String apelido)  { this.apelido = apelido;   }
    public String getEmail()                { return email;             }
    public void setEmail(String email)      { this.email = email;       }
    public String getSenha()                { return senha;             }
    public void setSenha(String senha)      { this.senha = senha;       }

    public String getNome()                         { return nome;                              }
    public void setNome(String nome)                { this.nome = Texto.left(nome,80);          }
    public Date getDtNascimento()                   { return dtNascimento;                      }
    public void setDtNascimento(Date dtNascimento)  { this.dtNascimento = dtNascimento;         }
    public String getCpf()                          { return this.cpf;                          }
    public void setCpf(String cpf)                  { if (Validador.cpf(cpf)) this.cpf = cpf;   }

}