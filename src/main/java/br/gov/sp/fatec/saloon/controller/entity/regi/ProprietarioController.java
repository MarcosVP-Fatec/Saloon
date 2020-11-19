package br.gov.sp.fatec.saloon.controller.entity.regi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.sp.fatec.saloon.model.dao.ProprietarioDaoJpa;
import br.gov.sp.fatec.saloon.model.dao.interf.ProprietarioDao;
import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;
import br.gov.sp.fatec.saloon.model.tool.UsuarioLogado;

/**
 * Controler da entidade Proprietário Este proprietário também adiciona um
 * usuário.
 */
public class ProprietarioController extends HttpServlet {

    private static final long serialVersionUID = 3511119533886767378L;
    private int numeroStatus = 200;

    /**
     * doGet 1) Recupera o parâmetro id do request 2) Recupera a entidade deste id
     * 3) Usa o Jackson para transformar o objeto em um Json (String) 4) Formata a
     * resposta 5) Retorna
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        resp.setStatus(numeroStatus); // 200 para o GET e 204 para o PUT

        PrintWriter out = resp.getWriter();
        out.print(proprietarioJson);
        out.flush();

    }

    /**
     * doPost
     * 
     * 1) Recupera o corpo da requisição e transforma o JSON em objeto 2) Salva no
     * banco de dados 3) Retorna o registro gerado formatando a resposta 4) O código
     * 201 requer que retorneos um header de Location
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Recuperamos o corpo da requisição e transformamos o JSON em objeto
        ObjectMapper mapper = new ObjectMapper();
        Proprietario proprietario = mapper.readValue(req.getReader(), Proprietario.class);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> req.getReader()");
        System.out.println(req.getReader());

        // Salvamos no Banco de Dados
        new ProprietarioDaoJpa().salvarProprietario(proprietario);

        // Retornamos o registro gerado formatando a resposta
        String proprietarioJson = mapper.writeValueAsString(proprietario);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        // O código 201 requer que retornemos um header de Location
        resp.setStatus(201);
        String location = req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/proprietario?id="
                + proprietario.getId();
        System.out.println(">>>>>> LOCATION: " + location);

        resp.setHeader("Location", location);
        PrintWriter out = resp.getWriter();
        out.print(proprietarioJson);
        out.flush();
    }

    /**
     * doPut
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //#####################################################################
        // Se o usuário não for admim não pode alterar
        //#####################################################################
        if (UsuarioLogado.getUsuarioLogado().getUsuarioNivel() != 1){
           resp.setStatus(401); //UNAUTHORIZED
           PrintWriter out = resp.getWriter();
           out.print("O usuario nao tem privilegios de administrador para executar esta alteracao!");
           out.flush();
           return;     
        }
        
        this.numeroStatus = 204;
        doGet(req, resp);
        this.numeroStatus = 200;
    }

    /**
     * doDelete
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //#####################################################################
        // Se o usuário não for admim não pode excluir
        //#####################################################################
        if (UsuarioLogado.getUsuarioLogado().getUsuarioNivel() != 1){
           resp.setStatus(401); //UNAUTHORIZED
           PrintWriter out = resp.getWriter();
           out.print("O usuario nao tem privilegios de administrador para executar esta exclusao!");
           out.flush();
           return;     
        }

        // Recupera o parâmetro id (de proprietario?id=<valor>)
        Long id = Long.valueOf(req.getParameter("id"));

        // Busca o proprietário com o id
        ProprietarioDao proprietarioDao = new ProprietarioDaoJpa();
        Proprietario proprietario = proprietarioDao.buscarProprietario(id);

        if (proprietario != null) {

            try {

                proprietarioDao.removerProprietario(proprietario);
                resp.setStatus(204); // Formatação da Resposta 

            } catch (Exception e) {
                resp.setStatus(404); // Formatação da Resposta quando ocorreu uma falha
            }

        }

        PrintWriter out = resp.getWriter();
        out.flush();
    }

}