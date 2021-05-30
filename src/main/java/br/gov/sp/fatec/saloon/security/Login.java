package br.gov.sp.fatec.saloon.security;

public class Login {

    private String usuario;
    private String senha;
    private String autorizacao;
    private String token;

    public String getUsuario()                      { return this.usuario;           }
    public void setUsuario(String usuario)          { this.usuario = usuario;        }
    public String getSenha()                        { return this.senha;             }
    public void setSenha(String senha)              { this.senha = senha;            }
    public String getToken()                        { return this.token;             }
    public void setToken(String token)              { this.token = token;            }
    public String getAutorizacao()                  { return this.autorizacao;       }
    public void setAutorizacao(String autorizacao)  { this.autorizacao = autorizacao;}

}
