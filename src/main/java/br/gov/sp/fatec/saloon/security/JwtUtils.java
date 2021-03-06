package br.gov.sp.fatec.saloon.security;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {

  /**
   * Chave para validar o token
   * O certo é pegar uma chave gerada e estar fora do código
   * Usar o Autowired e pegar uma variável de ambiente no lugar de colocar fixo como no exemplo
   */
  private static final String KEY = "spring.jwt.sec";

  public static Login generateToken(Authentication usuario) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    Login usuarioSemSenha = new Login();
    usuarioSemSenha.setUsuario(usuario.getName());

    //Neste projeto uma única autorização já resolve as necessidades
    usuarioSemSenha.setAutorizacao(usuario.getAuthorities().iterator().next().getAuthority());
    //if (usuario.getAuthorities().isEmpty()) {}

    String usuarioJson = mapper.writeValueAsString(usuarioSemSenha); //Gera um Json do objeto
    Date agora = new Date();
    Long hora = 1000L * 60L * 60L; // Uma hora em milissegundos
    //Criar o token
    usuarioSemSenha.setToken(Jwts.builder().claim("userDetails", usuarioJson) 
                              .setIssuer("br.gov.sp.fatec")                       //Quem está gerando
                              .setSubject(usuario.getName())                      //Para que se destina (Para este usuário)
                              .setExpiration(new Date(agora.getTime() + hora))    //Daqui até uma hora vai expirar.
                              .signWith(SignatureAlgorithm.HS512, KEY)            //Assino com HS512 + a chave de criptografia local
                              .compact()                                          //Compacta que garar uma string (Token)
    );
    return usuarioSemSenha;
  }

  /**
   * Método que abre um token para ser usado no filtro.
   */
  public static Authentication parseToken(String token) throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    String credentialsJson = Jwts.parser()  //Já faz todas as validações
        .setSigningKey(KEY)                 //Chave de criptografia
        .parseClaimsJws(token)              //Abre o token
        .getBody()                          //Pega o corpo do token
        .get("userDetails", String.class);  //E do corpo pega o userdetails

    //Transformar de volta na classe Login
    //Abrir o meu userDetail que é do tipo Login e vou transformar de volta na classe Login
    Login usuario = mapper.readValue(credentialsJson, Login.class);

    //Cria os detalhes (Regras) para poder pegar o getAuthorities abaixo.
    UserDetails userDetails = User.builder()
                    .username(usuario.getUsuario())
                    .password("qualquercoisa") //Como não tenho senha coloco qualquer coisa
                    .authorities(usuario.getAutorizacao())
                    .build();

    //Gera o token -> Retorna Authentication
    return new UsernamePasswordAuthenticationToken( usuario.getUsuario()
                                                  , usuario.getSenha()
                                                  , userDetails.getAuthorities());
  }

}
