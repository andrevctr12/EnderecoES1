package nada;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import unioeste.geral.endereco.exception.EnderecoException;
import unioeste.geral.endereco.manager.*;

/**
 * Servlet implementation class AlteraEndereco
 */
@WebServlet("/AlteraEnderecoServletAlterar")
public class AlteraEnderecoServletAlterar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlteraEnderecoServletAlterar() {
        super();
        // TODO Auto-generated constructor stub
    }    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher requestDispatcher;
                request.setCharacterEncoding("UTF-8"); // pega caracteres UTF-8 do JSP
                request.setAttribute("pagina", "AlteraEndereco.jsp");

                
                String nomeTipoLogradouro = request.getParameter("TipoLogradouroNome");
                String nomeLogradouro = request.getParameter("LogradouroNome");
                String nomeBairro = request.getParameter("BairroNome");
                String nomeCidade = request.getParameter("CidadeNome");
                String nomeUF = request.getParameter("UFNome");                
                String nomePais = request.getParameter("PaisNome");                
                String cep = request.getParameter("cep");
                
                String IdTipoLogradouro = request.getParameter("TipoLogradouroId");
                String IdLogradouro = request.getParameter("LogradouroId");
                String IdBairro = request.getParameter("BairroId");
                String IdCidade = request.getParameter("CidadeId");
                String IdUF = request.getParameter("UFId");                
                String IdPais = request.getParameter("PaisId");                
                //String IdCep = request.getParameter("cepId");
                
                try {         
           
                    EnderecoControle controle = new EnderecoControle();
                    controle.alteraEndereco(cep, nomeTipoLogradouro , nomeLogradouro,  
                                nomeBairro,  nomeCidade, nomeUF, nomePais,
                                IdTipoLogradouro, IdLogradouro, IdBairro, IdCidade, IdUF, IdPais);
                    requestDispatcher = request.getRequestDispatcher("Sucesso.jsp");
             } catch (Exception e) {
            request.setAttribute("e", e);
            requestDispatcher = request.getRequestDispatcher("Falha.jsp");
            }
            requestDispatcher.forward(request, response);
        

	}

}

