package nada;

import unioeste.geral.bo.endereco.Endereco;
import unioeste.geral.endereco.exception.EnderecoException;
import unioeste.geral.endereco.manager.EnderecoControle;
import unioeste.geral.endereco.manager.UCEnderecoGeralServicos;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@WebServlet("/BuscaEnderecoServlet")
public class BuscaEnderecoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // recebe caracteres UTF-8 do JSP
        String cep = request.getParameter("cep");
        String id = request.getParameter("id");
        RequestDispatcher requestDispatcher;
        Endereco endereco = null;
        String uuid = UUID.randomUUID().toString();

        try {
            // Busca por CEP
            if (!cep.equals("") && id.equals("")) {
                endereco = new UCEnderecoGeralServicos().obterEnderecoPorCEP(cep);

            // Busca por ID
            } else if (!id.equals("") && cep.equals("")) {
                Endereco enderecoEntrada = new Endereco();
                enderecoEntrada.setIdEndereco(Integer.parseInt(id));
                endereco = new UCEnderecoGeralServicos().obterEnderecoPorID(enderecoEntrada);
            }
            requestDispatcher = request.getRequestDispatcher("ExibeEndereco.jsp");
            if (endereco != null) {
                ArrayList<String> atributos = new EnderecoControle().obterListaAtributos(endereco);
                request.setAttribute("atributos", atributos);
                request.getSession().setAttribute(uuid, endereco);
                request.setAttribute("uuid", uuid);
            }
        } catch (EnderecoException e) {
            requestDispatcher = request.getRequestDispatcher("Falha.jsp");
            request.setAttribute("e", e);
        }
        request.setAttribute("pagina", "BuscaEndereco.jsp");
        requestDispatcher.forward(request, response);
    }

}

