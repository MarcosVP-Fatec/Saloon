package br.gov.sp.fatec.saloon.controller.entity.regi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.sp.fatec.saloon.model.dao.UsuarioDaoJpa;
import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;

/**
 * Controler da entidade Usuario (Apenas para login)
 */
public class UsuarioController extends HttpServlet{

    private static final long serialVersionUID = 7401956926661998026L;

    /**
     * doGet 1) Recupera o parâmetro id do request 2) Recupera a entidade deste id
     * 3) Usa o Jackson para transformar o objeto em um Json (String) 4) Formata a
     * resposta 5) Retorna
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recupera o parâmetro id (de usuario?id=<valor>)
        
        String apelido = req.getParameter("apelido");
        Usuario usuario = new UsuarioDaoJpa().buscar(apelido);
        
        // Usamos o Jackson para transformar o objeto em um JSON (String)
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");

        if (usuario.getId() != null) {

            String usuarioJson = mapper.writeValueAsString(usuario);

            // Formatação da Resposta
System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ACHEI ACHEI ACHEI => ID DE USUÁRIO = " + usuario.getId());
            resp.setContentType("application/json");
            resp.setStatus(200);

            out.print(usuarioJson);

        } else {

            resp.setContentType("text/xml");
            resp.setStatus(401);

            resp.addHeader("Erro", ">>>>>>>>>>>>>>>>>>>>>> Usuário não encontrado => " + apelido);
            out.print(">>>>>>>>>>>>>>>>>>>>>> Apelido de Usuário não encontrado => " + apelido);
        }

        out.flush();

    }

}

