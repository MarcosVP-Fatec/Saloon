package br.gov.sp.fatec.saloon.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtAuthenticationFilter extends GenericFilterBean {

    //O chain serve para passar a requisição adiante.
    @Override
    public void doFilter( ServletRequest  request
                        , ServletResponse response
                        , FilterChain     chain) throws IOException, ServletException {

        try {
            
            //Faz um cast do request
            HttpServletRequest servletRequest = (HttpServletRequest) request;

            //Com o tipo HttpServletRequest conseguimos pegar os seus dados
            String authorization = servletRequest.getHeader(HttpHeaders.AUTHORIZATION);

            if (authorization != null) {
                //No lugar de Basic xxx o JWT traz Bearer, então tiramos isso da autorização antes de usar 
                //(Fica só o token). O parse valida o token e retira dele o objeto authentication
                Authentication credentials = JwtUtils.parseToken(authorization.replaceAll("Bearer ", ""));
                //Singleton que não pode ser instanciado novamente.
                //Isso pega a instância local que o sistema está usando.
                //Passa o objeto do tipo Authentication que tem o usuário e suas autorizaçães
                //Este método abaixo pega a instância local que o sistema está usando e usa o 
                //método setAuthentication(e passa o objeto que terá o usuário e autorizações)
                //Se ocorrer algum erro aqui teremos uma exception 401-Unauthorized
                SecurityContextHolder.getContext().setAuthentication(credentials);
            }
            chain.doFilter(request, response);
        } catch (Throwable t) {
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            servletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, t.getMessage());
        }
    }

}
