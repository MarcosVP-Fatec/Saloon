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
    //private ServletContext context;


    /**
     * doGet 1) Recupera o parâmetro id do request 2) Recupera a entidade deste id
     * 3) Usa o Jackson para transformar o objeto em um Json (String) 4) Formata a
     * resposta 5) Retorna
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recupera o parâmetro id (de usuario?id=<valor>)
        Long id = Long.valueOf(req.getParameter("id"));;
        String apelido = "";
        Usuario usuario;
        if (id > 0){
            usuario = new UsuarioDaoJpa().buscar(id);
        } else {
            apelido = req.getParameter("apelido");
            usuario = new UsuarioDaoJpa().buscar(apelido);
        }
        
        // Usamos o Jackson para transformar o objeto em um JSON (String)
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");

        if (usuario != null) {

            String usuarioJson = mapper.writeValueAsString(usuario);

            // Formatação da Resposta
            resp.setContentType("application/json");
            resp.setStatus(200);

            out.print(usuarioJson);

        } else {

            resp.setContentType("text/xml");
            resp.setStatus(404);
            if (id > 0){
                resp.addHeader("Erro", ">>>>>>>>>>>>>>>>>>>>>> Id de Usuário não encontrado => " + id);
                out.print(">>>>>>>>>>>>>>>>>>>>>> Id de Usuário não encontrado => " + id);
            } else {
                resp.addHeader("Erro", ">>>>>>>>>>>>>>>>>>>>>> Apelido de Usuário não encontrado => " + apelido);
                out.print(">>>>>>>>>>>>>>>>>>>>>> Apelido de Usuário não encontrado => " + apelido);
            }

        }

        out.flush();

    }

}