package br.gov.sp.fatec.saloon.controller.entity.regi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.sp.fatec.saloon.model.dao.UsuarioDadosPessoaisDaoJpa;
import br.gov.sp.fatec.saloon.model.entity.regi.UsuarioDadosPessoais;

/**
 * Controler da entidade UsuarioGeral (Todos os dados da tabela de usuário)
 */
public class UsuarioDadosPessoaisController extends HttpServlet {

    private static final long serialVersionUID = 3526619276860718040L;

    /**
     * doGet 1) Recupera o parâmetro id do request 2) Recupera a entidade deste id
     * 3) Usa o Jackson para transformar o objeto em um Json (String) 4) Formata a
     * resposta 5) Retorna
     */
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recupera o parâmetro id (de proprietario?id=<valor>)
        Long id = Long.valueOf(req.getParameter("id"));

        // Busca o usuario com o id
        UsuarioDadosPessoais usuario = new UsuarioDadosPessoaisDaoJpa().buscarUsuarioDadosPessoais(id);

        // Usamos o Jackson para transformar o objeto em um JSON (String)
        ObjectMapper mapper = new ObjectMapper();
        String usuarioDadosPessoaisJson = mapper.writeValueAsString(usuario);

        // Formatação da Resposta
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(200);

        PrintWriter out = resp.getWriter();
        out.print(usuarioDadosPessoaisJson);
        out.flush();

    }

    /**
     * doPost
     * 
     * 1) Recupera o corpo da requisição e transforma o JSON em objeto
     * 2) Salva no banco de dados
     * 3) Retorna o registro gerado formatando a resposta
     * 4) O código 201 requer que retorneos um header de Location
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Recuperamos o corpo da requisição e transformamos o JSON em objeto
        ObjectMapper mapper = new ObjectMapper();
        UsuarioDadosPessoais usuario = mapper.readValue(req.getReader(), UsuarioDadosPessoais.class);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> UsuarioDadosPessoais -> req.getReader()");
        System.out.println(req.getReader());

        // Salvamos no Banco de Dados
        new UsuarioDadosPessoaisDaoJpa().salvarUsuarioDadosPessoais(usuario);

        // Retornamos o registro gerado formatando a resposta
        String usuarioDadosPessoaisJson = mapper.writeValueAsString(usuario);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        // O código 201 requer que retornemos um header de Location
        resp.setStatus(201); //CREATED
        String location = req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/usuariodadospessoais?id=" 
            + usuario.getId();
        System.out.println(">>>>>> LOCATION: " + location);    

        resp. setHeader("Location", location);
        PrintWriter out = resp.getWriter();
        out.print(usuarioDadosPessoaisJson);
        out.flush();
    }
    
}