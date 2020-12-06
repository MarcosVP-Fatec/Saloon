package br.gov.sp.fatec.saloon.controller.entity.tools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.gov.sp.fatec.saloon.model.dao.UsuarioDaoJpa;
import br.gov.sp.fatec.saloon.model.tool.Data;

public class FilterAuth implements Filter {

    private ServletContext          context;
    private String                  realm = "PROTECTED";
    /*
    private String                  username = "admin";
    private String                  password = "password_dificil";
    */

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        this.context.log("[AUTH] Filtro de autenticação acessado às " + Data.time());
        HttpServletRequest request = (HttpServletRequest) req;
        //HttpServletResponse response = (HttpServletResponse) res;

        this.context.log(request.toString());

        // ---------------------------------------------------------------
        // Verifica se tem o header Authorization
        // ---------------------------------------------------------------
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            unAuthorized(res,"Falta Authorization");
            return;
        }
        // ---------------------------------------------------------------
        // Divide o conteúdo do header por espaços
        // Verifica se possui conteúdo
        // ---------------------------------------------------------------
        StringTokenizer st = new StringTokenizer(authHeader);
        if (!st.hasMoreTokens()) {
            unAuthorized(res,"Token não informado");
            return;
        }

        // ---------------------------------------------------------------
        // Verifica se possui o prefixo Basic
        // ---------------------------------------------------------------
        String basic = st.nextToken(); // Basic sopadeletrinhas
        if (!basic.equalsIgnoreCase("Basic")) {
            unAuthorized(res,"Token sem Basic");
            return;
        }

        this.context.log("[AUTH] Extrair as credenciais às " + Data.time());
        // ---------------------------------------------------------------
        // Extrai as credenciais (Base64)
        // ---------------------------------------------------------------
        try {

            //Decodifica a credencial
            String credentials = new String(Base64.getDecoder().decode(st.nextToken()));
            this.context.log("[AUTH] Credenciais: " + credentials);

            // ---------------------------------------------------------------
            // Separa as credenciais em usuário e senha
            // ---------------------------------------------------------------
            Integer posicaoDoDoisPontos = credentials.indexOf(":"); // Localiza o : na string

            if (posicaoDoDoisPontos != -1) {

                this.context.log("[AUTH] Credencial com dois Token (Ok)");

                String _username = credentials.substring(0, posicaoDoDoisPontos).trim();
                String _password = credentials.substring(posicaoDoDoisPontos + 1).trim(); // Depois dos dois pontos.

                this.context.log("[AUTH] Usuário e senha informados >>>>>>>>>>>> " + _username + ":" + _password );

                if ( !UsuarioDaoJpa.autenticar( _username , _password ) ) {
                    unAuthorized(res, "Usuário ou senha inválidos => " + credentials);
                    return;
                }

                this.context.log("[AUTH] Usuário e senha autorizados");

                // ---------------------------------------------------------------
                // Prossegue com a requisicao
                // ---------------------------------------------------------------
                chain.doFilter(req, res);
                this.context.log("[AUTH] doFilter enviado às " + Data.time());

            } else {

                unAuthorized(res, "Token de autenticação inválido!");

            }

        } catch (UnsupportedEncodingException e) {
            throw new Error("Não foi possível recuperar a autenticação!", e);
        }

    }

    @Override
    public void destroy() {
        // Aqui pode-se desalocar qualquer recurso
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.context = config.getServletContext();
        
        /* Parâmetros do web.xml
        this.context.log("Filtro inicializado!");
        this.username = config.getInitParameter("username");
        this.password = config.getInitParameter("password");
        String paramRealm = config.getInitParameter("realm");
        if (paramRealm != null && paramRealm.length() > 0) {
            this.realm = paramRealm;
        }
        */
    }

    //private void unAuthorized( ServletResponse res                  ) throws IOException { unAuthorized(res, ""); }
    private void unAuthorized( ServletResponse res, String mensagem ) throws IOException {
        HttpServletResponse resposta = (HttpServletResponse) res;
        mensagem = "# Falha às " + Data.time() + " # " + mensagem;
        resposta.setCharacterEncoding("UTF-8");
        resposta.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
        this.context.log( "[AUTH] " + mensagem );
        resposta.sendError(401, mensagem);

    }

}