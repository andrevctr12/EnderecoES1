package nada;

import unioeste.geral.bo.endereco.Endereco;
import unioeste.geral.endereco.exception.EnderecoException;
import unioeste.geral.endereco.manager.UCEnderecoGeralServicos;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ExcluiEnderecoServlet")
public class ExcluiEnderecoServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        Endereco endereco = (Endereco)request.getSession().getAttribute(uuid);
        RequestDispatcher requestDispatcher;
        request.setAttribute("pagina", "ExcluiEndereco.jsp");
        try {
            new UCEnderecoGeralServicos().excluirEndereco(endereco);
            requestDispatcher = request.getRequestDispatcher("Sucesso.jsp");
        } catch(EnderecoException e) {
            requestDispatcher = request.getRequestDispatcher("Falha.jsp");
        }
        requestDispatcher.forward(request, response);
    }
}
