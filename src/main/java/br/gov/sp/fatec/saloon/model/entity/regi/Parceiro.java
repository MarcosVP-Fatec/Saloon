package br.gov.sp.fatec.saloon.model.entity.regi;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.entity.stat.UsuarioNivel;
import br.gov.sp.fatec.saloon.model.tool.Data;

@Table(name = "par_parceiro")
@Entity
@PrimaryKeyJoinColumn(name = "par_usu_id")
public class Parceiro extends Usuario {

	@Column(name = "par_dt_inicio")             private Date    dtInicio;
    @Column(name = "par_inativo")               private boolean inativo;
    
    //Um PARCEIRO pode atender vários CLIENTES e um CLIENTE pode ser atendido por vários PARCEIROS
    //Ao inves de informar qual coluna vai me ajudar a buscar os alunos eu tenho
    //que falar qual a tabela
    //joinColuns define quais as colunas desta classe Parceiro são referenciadas na tabela de ligação
    //inverseJoinColumn define quais colunas da outra tabela (Cliente) são referenciadas na tabela de ligação
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pxc_parceiro_x_cliente",
               joinColumns = {@JoinColumn(name = "pxc_par_usu_id") },
               inverseJoinColumns = {@JoinColumn(name = "pxc_cli_id")})
    private Set<Cliente> clientes;
    
    // CONSTRUTORES
    public Parceiro() {}
    public Parceiro( Long id
    		        , String apelido
    		        , String email
    		        , String senha
    		        , String nome
    		        , Date   dtNascimento
    		        , String cpf
    		        , UsuarioNivel usuarioNivel) {
    	
    	this.setApelido(apelido);
    	this.setEmail(email);
    	this.setSenha(senha);
    	this.setNome(nome);
    	this.setDtNascimento(dtNascimento);
    	this.setCpf(cpf);
    	
    	this.setUsuarioNivel(usuarioNivel);
    	
    	setDtInicio();
    	setInativo(false);
    	
    }

    // GETTERS AND SETTERS
    public Date getDtInicio()              { return this.dtInicio;           }
    public void setDtInicio()              { this.dtInicio = Data.today();   }
    public void setDtInicio(Date dtInicio) { this.dtInicio = dtInicio;       }
    public boolean isInativo()             { return inativo;                 }
    public void setInativo(boolean inativo){ this.inativo = inativo;         }
    public Set<Cliente> getClientes()      { return clientes;                }
    
}