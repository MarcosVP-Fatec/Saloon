package br.gov.sp.fatec.saloon.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.tool.Texto;

@Table(name = "usu_usuario")
@Entity
@DiscriminatorValue("F")
public class UsuarioDadosPessoais extends Usuario {
    
    @Column(name = "usu_nome")              private String  nome;    
    @Column(name = "usu_dt_nascimento")     private Date    dtNascimento;

    // Construtores
    public UsuarioDadosPessoais(){}
    public UsuarioDadosPessoais( String apelido
                               , String email
                               , String senha
                               , String nome
                               , Date date) {

        setApelido(apelido);
        setEmail(email);
        setSenha(senha);
        setNome(nome);
        setDtNascimento(date);
        //setCpf(cpf);

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


/*
    @Column(name = "usu_cpf")               private String  cpf;
    @Column(name = "usu_mudar_senha")       private char    mudarSenha;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public char getMudarSenha() {
        return mudarSenha;
    }

    public void setMudarSenha(char mudarSenha) {
        this.mudarSenha = mudarSenha;
    }
  */ 

}