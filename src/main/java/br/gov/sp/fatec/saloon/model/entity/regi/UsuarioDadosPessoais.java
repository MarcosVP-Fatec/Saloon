package br.gov.sp.fatec.saloon.model.entity.regi;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.tool.Validador;

@Table(name = "usu_usuario")
@Entity
@DiscriminatorValue("F")
@Inheritance(strategy = InheritanceType.JOINED)
public class UsuarioDadosPessoais extends Usuario {
    
    @Column(name = "usu_dt_nascimento")     private Date    dtNascimento;
    @Column(name = "usu_cpf_cnpj")          private String  cpf;

    // Construtores
    public UsuarioDadosPessoais(){}
    public UsuarioDadosPessoais( String apelido
                               , String email
                               , String senha
                               , String nome
                               , Date   dtNascimento
                               , String cpf
                               , Long   nivelUsuario) {

        super(apelido, nome, email, senha, nivelUsuario);
        setDtNascimento(dtNascimento);
        setCpf(cpf);
        setUsuarioNivel(nivelUsuario);

    }

    // Getters and Setters
    public Date getDtNascimento()                   { return dtNascimento;                   }
    public void setDtNascimento(Date dtNascimento)  { this.dtNascimento = dtNascimento;      }
    public String getCpf()                          { return this.cpf;                       }
    public void setCpf(String cpf)                  { if (Validador.cpf(cpf)) this.cpf = cpf;}

}