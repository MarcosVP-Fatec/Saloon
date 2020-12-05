package br.gov.sp.fatec.saloon.controller.entity.tools;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import br.gov.sp.fatec.saloon.model.tool.Data;

public class FilterServlet implements Filter {
    
    private ServletContext context; //Console

    @Override
    public void doFilter(ServletRequest param_req, ServletResponse param_res, FilterChain param_chain)
            throws IOException, ServletException {

        this.context.log(">>>>>>>>>>>>>>>>>>>>>> Filtro acessado !"); //Console
        this.context.log(param_req.toString());                       //Console
        
        // Todo código aqui é executado antes da requisição chegar ao destino
        // Passa a requisição adiante

        // O código comentado abaixo serve para gerer logs no console.
        // * Necessário fazer um cast porque com ServletResponce eu não consigo ter acesso às características
        // * da minha resposta nem da minha requisição
        HttpServletResponse resp = (HttpServletResponse) param_res;                         //Console
        resp.addHeader("In", ">>>>>>>>>>>>>>>>>>>>>> Eu passei pelo filtro na entrada!");   //Console
        
        param_chain.doFilter(param_req, param_res);

        // Todo código aqui é executado no retorno
        //resp.addHeader("Out", ">>>>>>>>>>>>>>>>>>>>>> Eu passei pelo filtro na saída!");    //Console
        this.context.log("[FILTER SERVLET] @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"); //Console
        this.context.log("[FILTER SERVLET] >>>>>>> Resposta enviada às " + Data.time());                                                 //Console
        this.context.log("[FILTER SERVLET] @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"); //Console
    }

    @Override
    public void destroy() {
        // Aqui pode-se desalocar qualquer recurso
    }

    /**
     * @apiNote init
     * Construtor do Filtro
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        this.context = config.getServletContext();      //Console
    }
    
}