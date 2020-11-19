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

import br.gov.sp.fatec.saloon.model.dao.UsuarioDadosPessoaisDaoJpa;
import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;
import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.model.tool.UsuarioLogado;

public class FilterAuth implements Filter {

    private ServletContext context;
    private Usuario usuario;
    private String username = "admin";
    private String password = "password_dificil";
    private String realm = "PROTECTED";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        this.context.log("[AUTH] Filtro de autenticação acessado (1) às " + Data.time());
        HttpServletRequest request = (HttpServletRequest) req;
        this.context.log("[AUTH] Filtro de autenticação acessado (2) às " + Data.time());
        HttpServletResponse response = (HttpServletResponse) res;
        this.context.log("[AUTH] Filtro de autenticação acessado (3) às " + Data.time());

        this.context.log(request.toString());

        // ---------------------------------------------------------------
        // Verifica se tem o header Authorization
        // ---------------------------------------------------------------
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            this.context.log("[AUTH] Filtro de autenticação acessado (Falta Authorization) às " + Data.time());
            unauthorized(response);
            return;
        }
        // ---------------------------------------------------------------
        // Divide o conteúdo do header por espaços
        // Verifica se possui conteúdo
        // ---------------------------------------------------------------
        StringTokenizer st = new StringTokenizer(authHeader);
        if (!st.hasMoreTokens()) {
            this.context.log("[AUTH] Filtro de autenticação acessado (Token não informado) às " + Data.time());
            unauthorized(response);
            return;
        }

        // ---------------------------------------------------------------
        // Verifica se possui o prefixo Basic
        // ---------------------------------------------------------------
        String basic = st.nextToken(); // Basic sopadeletrinhas
        if (!basic.equalsIgnoreCase("Basic")) {
            this.context.log("[AUTH] Filtro de autenticação acessado (Token sem Basic) às " + Data.time());
            unauthorized(response);
            return;
        }

        this.context.log("[AUTH] Extrair as credenciais às " + Data.time());
        // ---------------------------------------------------------------
        // Extrai as credenciais (Base64)
        // ---------------------------------------------------------------
        try {

            String credentials = new String(Base64.getDecoder().decode(st.nextToken()));
            this.context.log("[AUTH] Credenciais: " + credentials);

            // ---------------------------------------------------------------
            // Separa as credenciais em usuário e senha
            // ---------------------------------------------------------------
            Integer posicaoDoDoisPontos = credentials.indexOf(":"); // Localiza o : na string

            if (posicaoDoDoisPontos != -1) {

                this.context.log("[AUTH] Entrou no -1");

                String _username = credentials.substring(0, posicaoDoDoisPontos).trim();
                String _password = credentials.substring(posicaoDoDoisPontos + 1).trim(); // Depois dos dois pontos.

                this.context.log("[AUTH] Usuário e senha informados >>>>>>>>>>>> " + _username + ":" + _password + " x "
                        + username + ":" + password);

                if (!UsuarioLogado.isUsuarioLogado()) {

                    // ###########################################################################
                    // Busca o usuário informado no BANCO DE DADOS
                    // ###########################################################################
                    usuario = new UsuarioDadosPessoaisDaoJpa().buscarUsuarioDadosPessoais(_username);
                    if (usuario == null) {
                        this.context.log("[AUTH] Usuário ou senha não cadastrados = " + credentials);
                        unauthorized(response); // Este comando exige nova digitação do login.
                        return;
                    }

                    username = _username;
                    password = usuario.getSenha();

                    // ---------------------------------------------------------------
                    // Se nao bate com configuração retorna erro
                    // ---------------------------------------------------------------
                    if (!username.equals(_username) || !password.equals(_password)) {
                        unauthorized(response, "Usuário ou senha não cadastrados!");
                        this.context.log("[AUTH] Usuário ou senha não cadastrados = " + credentials);
                        return;
                    }

                    UsuarioLogado.logar(usuario);
                    this.context.log("[AUTH] Usuário e senha autorizados");
                }

                // ---------------------------------------------------------------
                // Prossegue com a requisicao
                // ---------------------------------------------------------------
                chain.doFilter(req, res);
                this.context.log("[AUTH] doFilter enviado às " + Data.time());

            } else {

                unauthorized(response, "Token de autenticação inválido!");

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
        this.context.log("Filtro inicializado!");
        this.username = config.getInitParameter("username");
        this.password = config.getInitParameter("password");
        String paramRealm = config.getInitParameter("realm");
        if (paramRealm != null && paramRealm.length() > 0) {
            this.realm = paramRealm;
        }
    }

    private void unauthorized(HttpServletResponse response, String message) throws IOException {
        response.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
        response.sendError(401, message);
    }

    private void unauthorized(HttpServletResponse response) throws IOException {
        unauthorized(response, "Unauthorized");
    }

}