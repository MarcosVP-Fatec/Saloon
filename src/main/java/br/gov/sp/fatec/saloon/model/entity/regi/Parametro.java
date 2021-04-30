package br.gov.sp.fatec.saloon.model.entity.regi;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.gov.sp.fatec.saloon.model.entity.comm.GeneratorId;

@Entity
@Table(name = "par_parametro")
@AttributeOverride(name = "id", column = @Column(name="par_id"))
public class Parametro extends GeneratorId{

    @Column(name="par_cod")         private String  cod;         //100
    @Column(name="par_descr")       private String  descricao;   //20
    @Column(name="par_tipo")        private char    tipo;        //1 - Int, String, Date, Numero
    @Column(name="par_val_num")     private double  numero;
    @Column(name="par_val_str")     private String  str;
    @Column(name="par_val_dat")     private Date    data;
    @Column(name="par_val_bol")     private boolean logico;

    // CONSTRUTORES
    public Parametro(){}
    
    //CONSTRUTOR base dos tipos
    private Parametro( String cod
            	      , String descricao
                      , char   tipo){

    	setCod(cod); 
    	setDescricao(descricao);            
    	setTipo(tipo);
    }

    //CONSTRUTOR NÚMERO
    public Parametro( String cod
                     , String descricao
                     , Double numero){
           
           this(cod, descricao, 'N');
           setNumero(numero);
    }

    //CONSTRUTOR DATA
    public Parametro( String cod
                    , String descricao
                    , Date   data){
           
           this(cod, descricao, 'D');
           setData(data);
    }

    //CONSTRUTOR STRING
    public Parametro( String cod
                    , String descricao
                    , String texto){
           
           this(cod, descricao, 'C');
           setStr(texto);
    }

    //CONSTRUTOR LÓGICO
    public Parametro( String cod
                    , String descricao
                    , boolean logico){
           
           this(cod, descricao, 'L');
           setLogico(logico);
    }

    public String getCod()                      { return cod;                   }
    public void setCod(String cod)              { this.cod = cod;               }
    public String getDescricao()                { return descricao;             }
    public void setDescricao(String descricao)  { this.descricao = descricao;   }
    public char getTipo()                       { return tipo;                  }
    public void setTipo(char tipo)              { this.tipo = tipo;             }
    public Double getNumero()                   { return numero;                }
    public void setNumero(Double numero)        { this.numero = numero;         }
    public String getStr()                      { return str;                   }
    public void setStr(String str)              { this.str = str;               }
    public Date getData()                       { return data;                  }
    public void setData(Date data)              { this.data = data;             }
    public boolean isLogico()                   { return logico;                }
    public void setLogico(boolean logico)       { this.logico = logico;         }
    
  
    /*
    public static double lernumero(String cod){
        Parametro parametro = new ParametroDaoJpa().buscar(cod);
        return parametro.getNumero();
    }

    public static Date lerData(String cod){
        Parametro parametro = new ParametroDaoJpa().buscar(cod);
        if (parametro == null) return null;
        return parametro.getData();
    }

    public static String lerString(String cod){
        Parametro parametro = new ParametroDaoJpa().buscar(cod);
        if (parametro == null) return null;
        return parametro.getStr();
    }

    public static boolean lerLogico(String cod){
        Parametro parametro = new ParametroDaoJpa().buscar(cod);
        if (parametro == null) return false;
        return parametro.isLogico();
    }
    */
}