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
@WebServlet("/AlteraEnderecoServletCidade")
public class AlteraEnderecoServletCidade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlteraEnderecoServletCidade() {
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
                    CidadeControle controle = new CidadeControle();
                    ArrayList<String> valores= controle.CidadeDoUF(Integer.parseInt(uf));
                    out.printf(name);
                        out.print("[");
                        for(int i=0;i<valores.size();i+=3){
                                out.print("{\"atributo\":\"".concat(valores.get(i)).concat("\"}"));
                                out.print(",{\"id\":\"".concat(valores.get(i+1)).concat("\"}"));
                                out.print(",{\"idPai\":\"".concat(valores.get(i+2)).concat("\"}"));
                                if((i != valores.size()-3)&&(valores.size()!=3)){
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
