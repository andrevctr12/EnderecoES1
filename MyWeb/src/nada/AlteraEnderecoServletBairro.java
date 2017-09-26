package nada;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import unioeste.geral.endereco.manager.*;

/**
 * Servlet implementation class AlteraEndereco
 */
@WebServlet("/AlteraEnderecoServletBairro")
public class AlteraEnderecoServletBairro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlteraEnderecoServletBairro() {
        super();
        // TODO Auto-generated constructor stub
    }    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
                response.setContentType("text/html;charset=UTF-8");
                String name = new String();
                String uf=request.getParameter("UF");
                try (PrintWriter out = response.getWriter()) {
                    BairroControle controle = new BairroControle();
                    ArrayList<String> valores= controle.bairroNomeId();
                    out.printf(name);
                        out.print("[");
                        for(int i=0;i<valores.size();i+=2){
                                out.print("{\"atributo\":\"".concat(valores.get(i)).concat("\"}"));
                                out.print(",{\"id\":\"".concat(valores.get(i+1)).concat("\"}"));
                                if((i != valores.size()-2)&&(valores.size()!=2)){
                                        out.print(",");
                                }
                        }
                        out.print("]");
                }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}