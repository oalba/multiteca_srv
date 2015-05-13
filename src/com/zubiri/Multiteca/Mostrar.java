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
 * Servlet implementation class Mostrar
 */
public class Mostrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mostrar() {
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
			
			Statement stmtl = cone.createStatement();
			Statement stmtp = cone.createStatement();
			Statement stmtd = cone.createStatement();
			
		    ResultSet rsl = stmtl.executeQuery("SELECT * FROM libro");
		    ResultSet rsp = stmtp.executeQuery("SELECT * FROM pelicula");
		    ResultSet rsd = stmtd.executeQuery("SELECT * FROM disco");
		    
		    
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><title></title></head>");
		out.println("<body>");
		out.println("<h3>Libros</h3>");
		while (rsl.next()) {
			//Statement stmtla = cone.createStatement();
			//String au = rsl.getString("autor");
			//ResultSet rsa = stmtla.executeQuery("SELECT añoNacimiento FROM artista WHERE nombre='"+au+"'");
			out.println("Código de barras: " + rsl.getInt("cod_barras") + "<br>");
			out.println("Título: " + rsl.getString("titulo") + "<br>");
			//out.println("Autor: " + rsa.getInt("añoNacimiento") + "<br>");
			out.println("Autor: " + rsl.getString("autor") + "<br>");
			out.println("Año de edición: " + rsl.getInt("añoEdicion") + "<br>");
			out.println("Editorial: " + rsl.getString("editorial") + "<br>");
			out.println("Número de páginas: " + rsl.getInt("numPaginas") + "<hr>");
	    }
		out.println("<h3>Películas</h3>");
		while (rsp.next()) {
			out.println("Código de barras: " + rsp.getInt("cod_barras") + "<br>");
			out.println("Título: " + rsl.getString("titulo") + "<br>");
			out.println("Autor: " + rsl.getString("autor") + "<br>");
			out.println("Año de edición: " + rsl.getInt("añoEdicion") + "<br>");
			out.println("Productora: " + rsl.getString("productora") + "<hr>");
	    }
		out.println("<h3>Discos</h3>");
		while (rsd.next()) {
			out.println("Código de barras: " + rsd.getInt("cod_barras") + "<br>");
			out.println("Título: " + rsl.getString("titulo") + "<br>");
			out.println("Autor: " + rsl.getString("autor") + "<br>");
			out.println("Año de edición: " + rsl.getInt("añoEdicion") + "<br>");
			out.println("Discográfica: " + rsl.getString("discografica") + "<br>");
			out.println("Número de canciones: " + rsl.getInt("nCanciones") + "<hr>");
	    }
		
		out.println("<br/><a href='index.html'><input type='button' value='Volver'></a>");
		out.println("</body></html>");
		cone.close();
		}catch(Exception ex){
			//Tratar el error
		}
	}

}
