package unioeste.geral.web.servlet;

import unioeste.geral.bo.endereco.EnderecoEspecifico;
import unioeste.geral.controller.ControllerEndereco;
import unioeste.geral.exception.EnderecoException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CadastroEnderecoServlet")
public class CadastroEnderecoServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        request.setCharacterEncoding("UTF-8"); // pega caracteres UTF-8 do JSP
        request.setAttribute("pagina", "CadastroEndereco.jsp");

        String nomeTipoLogradouro = request.getParameter("nomeTipoLogradouro");
        String nomeLogradouro = request.getParameter("nomeLogradouro");
        String nomeBairro = request.getParameter("nomeBairro");
        String nomeCidade = request.getParameter("nomeCidade");
        String nomeUF = request.getParameter("nomeUF");
        String siglaUF = request.getParameter("siglaUF");
        String nomePais = request.getParameter("nomePais");
        String siglaPais = request.getParameter("siglaPais");
        String cep = request.getParameter("cep");
        int numero = Integer.parseInt(request.getParameter(""));
        String complemento = request.getParameter("");

        EnderecoEspecifico endereco = new ControllerEndereco().popularEndereco(
                nomeTipoLogradouro, nomeLogradouro, nomeBairro, nomeCidade, nomeUF, siglaUF,
                nomePais, siglaPais, cep, complemento, numero);

        try {
            new ControllerEndereco().cadastrarEndereco(endereco);
            requestDispatcher = request.getRequestDispatcher("Sucesso.jsp");
        } catch (EnderecoException e) {
            request.setAttribute("e", e);
            requestDispatcher = request.getRequestDispatcher("Falha.jsp");
        }
        requestDispatcher.forward(request, response);
    }


}
