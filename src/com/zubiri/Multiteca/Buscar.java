package com.zubiri.Multiteca;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Buscar
 */
public class Buscar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buscar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{ 
			Class.forName("com.mysql.jdbc.Driver");
			Connection cone = DriverManager.getConnection("jdbc:mysql://localhost/multiteca","root","zubiri");
			
			Statement stmt = cone.createStatement();
			
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><title></title></head>");
		out.println("<body>");
		String st = request.getParameter("selecTipo");
		String cb = request.getParameter("codigoBarras");
		if (st == "disco") {
			ResultSet rsl = stmt.executeQuery("SELECT * FROM libro WHERE cod_barras = "+cb);
			while (rsl.next()) {
				out.println("Código de barras: " + rsl.getInt("cod_barras") + "<br>");
				out.println("Título: " + rsl.getString("titulo") + "<br>");
				out.println("Autor: " + rsl.getString("autor") + "<br>");
				out.println("Año de edición: " + rsl.getInt("añoEdicion") + "<br>");
				out.println("Editorial: " + rsl.getString("editorial") + "<br>");
				out.println("Número de páginas: " + rsl.getInt("numPaginas") + "<hr>");
		    }
		} else if (st == "pelicula") {
			ResultSet rsp = stmt.executeQuery("SELECT * FROM pelicula WHERE cod_barras = "+cb);
			while (rsp.next()) {
				out.println("Código de barras: " + rsp.getInt("cod_barras") + "<br>");
				out.println("Título: " + rsp.getString("titulo") + "<br>");
				out.println("Autor: " + rsp.getString("autor") + "<br>");
				out.println("Año de edición: " + rsp.getInt("añoEdicion") + "<br>");
				out.println("Productora: " + rsp.getString("productora") + "<hr>");
		    }
		} else {
			ResultSet rsd = stmt.executeQuery("SELECT * FROM disco WHERE cod_barras = "+cb);
			while (rsd.next()) {
				out.println("Código de barras: " + rsd.getInt("cod_barras") + "<br>");
				out.println("Título: " + rsd.getString("titulo") + "<br>");
				out.println("Autor: " + rsd.getString("autor") + "<br>");
				out.println("Año de edición: " + rsd.getInt("añoEdicion") + "<br>");
				out.println("Discográfica: " + rsd.getString("discografica") + "<br>");
				out.println("Número de canciones: " + rsd.getInt("nCanciones") + "<hr>");
		    }
		}
		out.println("<br/><a href='index.html'><input type='button' value='Volver'></a>");
		out.println("</body></html>");
		cone.close();
		}catch(Exception ex){
			//Tratar el error
		}
	}

}
