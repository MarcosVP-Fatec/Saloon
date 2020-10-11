package br.gov.sp.fatec.saloon.model.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Table(name = "usu_usuario")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AttributeOverride(name = "id", column=@Column(name="usu_id"))
public class Usuario extends GeneratorId{

    @Column(name = "usu_apelido")           private String  apelido;
    @Column(name = "usu_email")             private String  email;
    @Column(name = "usu_senha")             private String  senha;
    @Column(name = "usu_senha_validade")    private Date    senhaValidade;

    public Usuario(){}
    public Usuario( String apelido
                  , String email
                  , String senha){
        setApelido(apelido);
        setEmail(email);
        setSenha(senha);
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    
}