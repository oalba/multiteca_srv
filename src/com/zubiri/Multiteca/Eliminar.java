package com.zubiri.Multiteca;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class Eliminar
 */
public class Eliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Eliminar() {
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
		String cb = request.getParameter("codigoBarras");
	    ResultSet rs = stmt.executeQuery("SELECT * FROM obras WHERE cod_barras = '"+cb+"'");
	
		out.println("<html>");
		out.println("<head><title></title></head>");
		out.println("<body>");
		while (rs.next()) {
			out.print("Código de barras: "+cb+"<br>");
			out.print("Título: "+rs.getString("titulo")+"<br>");
			out.print("Autor: "+rs.getString("autor")+"<br>");
			out.print("Año de edición: "+rs.getInt("añoEdicion")+"<br>");
			
			String tipo = rs.getString("tipo");
			String libro = "libro";
			String peli = "pelicula";
			String disco = "disco";
			if (tipo.equals(libro)) {
				out.print("Tipo: Libro<br>");
				Statement stmtl = cone.createStatement();
				ResultSet rsl = stmtl.executeQuery("SELECT * FROM libros WHERE cod_barras = '"+cb+"'");
				while (rsl.next()){
					out.println("Editorial: "+rsl.getString("editorial")+"<br>");
					out.println("Número de páginas: "+rsl.getInt("nPaginas")+"<hr>");
				}
			} else if (tipo.equals(peli)){
				out.print("Tipo: Película<br>");
				Statement stmtp = cone.createStatement();
				ResultSet rsp = stmtp.executeQuery("SELECT * FROM peliculas WHERE cod_barras = '"+cb+"'");
				while (rsp.next()){
					out.println("Productora: "+rsp.getString("productora")+"<hr>");
				}
			} else if (tipo.equals(disco)){
				out.print("Tipo: Disco<br>");
				Statement stmtd = cone.createStatement();
				ResultSet rsd = stmtd.executeQuery("SELECT * FROM discos WHERE cod_barras = '"+cb+"'");
				while (rsd.next()){
					out.println("Discográfica: "+rsd.getString("discografica")+"<br>");
					out.println("Número de canciones: "+rsd.getInt("nCanciones")+"<hr>");
				}
			}
	    }
		stmt.executeUpdate("DELETE FROM obras WHERE cod_barras = '"+cb+"'");
		out.println("Vehículo eliminado correctamente.");
		out.println("<br/><a href='index.html'><input type='button' value='Volver'></a>");
		out.println("</body></html>");
		cone.close();
		}catch(Exception ex){
			//Tratar el error
		}
	}

}
