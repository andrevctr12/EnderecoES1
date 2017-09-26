package nada;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import unioeste.geral.endereco.manager.*;

/**
 * Servlet implementation class AlteraEndereco
 */
@WebServlet("/AlteraEnderecoServletCEPBusca")
public class AlteraEnderecoServletCEPBusca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlteraEnderecoServletCEPBusca() {
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
                
                
                try (PrintWriter out = response.getWriter()) {
                    EnderecoControle controle = new EnderecoControle();
                    ArrayList<String> valores= controle.obterListaEnderecoString();
                    out.printf(name);
                        out.print("[");
                        for(int i=0;i<valores.size();i+=7){
                                out.print("{\"idEndereco\":\"".concat(valores.get(i)).concat("\"}"));
                                out.print(",{\"cep\":\"".concat(valores.get(i+1)).concat("\"}"));
                                out.print(",{\"idLogradouro\":\"".concat(valores.get(i+2)).concat("\"}"));
                                out.print(",{\"idBairro\":\"".concat(valores.get(i+3)).concat("\"}"));
                                out.print(",{\"idCidade\":\"".concat(valores.get(i+4)).concat("\"}"));
                                out.print(",{\"idUF\":\"".concat(valores.get(i+5)).concat("\"}"));
                                out.print(",{\"idPais\":\"".concat(valores.get(i+6)).concat("\"}"));
                                if((i != valores.size()-7)&&(valores.size()!=7)){
                                        out.print(",");
                                }
                        }
                        out.print("]");
                } catch (Exception ex) {
                Logger.getLogger(AlteraEnderecoServletCEP.class.getName()).log(Level.SEVERE, null, ex);
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

