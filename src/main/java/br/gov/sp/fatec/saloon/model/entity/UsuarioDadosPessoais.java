package br.gov.sp.fatec.saloon.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.tool.Texto;
import br.gov.sp.fatec.saloon.model.tool.Validador;

@Table(name = "usu_usuario")
@Entity
@DiscriminatorValue("F")
public class UsuarioDadosPessoais extends Usuario {
    
    @Column(name = "usu_nome")              private String  nome;    
    @Column(name = "usu_dt_nascimento")     private Date    dtNascimento;
    @Column(name = "usu_cpf_cnpj")          private String  cpf;

    // Construtores
    public UsuarioDadosPessoais(){}
    public UsuarioDadosPessoais( String apelido
                               , String email
                               , String senha
                               , String nome
                               , Date date
                               , String cpf) {

        setApelido(apelido);
        setEmail(email);
        setSenha(senha);
        setNome(nome);
        setDtNascimento(date);
        setCpf(cpf);

    }


    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = Texto.left(nome,80);
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        if (Validador.cpf(cpf)) this.cpf = cpf;
    }

/*
    @Column(name = "usu_mudar_senha")       private char    mudarSenha;

  */ 

}