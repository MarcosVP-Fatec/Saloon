package br.gov.sp.fatec.saloon.controller.entity.regi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.sp.fatec.saloon.model.dao.ProprietarioDaoJpa;
import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;

/**
 * Controler da entidade Proprietário
 * Este proprietário também adiciona um usuário.
 */
public class ProprietarioController  extends HttpServlet {

    /**
     * doGet
     * 1) Recupera o parâmetro id do request
     * 2) Recupera a entidade deste id
     * 3) Usa o Jackson para transformar o objeto em um Json (String)
     * 4) Formata a resposta
     * 5) Retorna
     */
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recupera o parâmetro id (de proprietario?id=<valor>)
        Long id = Long.valueOf(req.getParameter("id"));

        // Busca o proprietário com o id
        Proprietario proprietario = new ProprietarioDaoJpa().buscarProprietario(id);

        // Usamos o Jackson para transformar o objeto em um JSON (String)
        ObjectMapper mapper = new ObjectMapper();
        String proprietarioJson = mapper.writeValueAsString(proprietario);

        // Formatação da Resposta
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(200);

        PrintWriter out = resp.getWriter();
        out.print(proprietarioJson);
        out.flush();
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recuperamos o corpo da requisição e transformamos o JSON em objeto
        ObjectMapper mapper = new ObjectMapper();
        Trabalho trabalho = mapper.readValue(req.getReader(), Trabalho.class);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(req.getReader());
        // Salvamos no Banco de Dados
        TrabalhoDao trabalhoDao = new TrabalhoDaoJpa();
        trabalhoDao.salvarTrabalho(trabalho);
        // Retornamos o registro gerado
        String trabalhoJson = mapper.writeValueAsString(trabalho);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        // O código 201 requer que retornemos um header de Location
        resp.setStatus(201);
        String location = req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/trabalho?id="
                + trabalho.getId();
        resp.setHeader("Location", location);
        PrintWriter out = resp.getWriter();
        out.print(trabalhoJson);
        out.flush();
    }
 */   
}