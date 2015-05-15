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

import com.zubiri.multiteca.*;

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
			
			
			
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><title></title></head>");
		out.println("<body>");
		//String st = request.getParameter("selecTipo");
		String cb = request.getParameter("codigoBarras");
		//if (st == "disco") {
		Statement stmtl = cone.createStatement();
		ResultSet rsl = stmtl.executeQuery("SELECT * FROM obras,libros WHERE obras.cod_barras = "+cb+" and obras.cod_barras=libros.cod_barras");
			while (rsl.next()) {
				out.println("Código de barras: " + rsl.getInt("obras.cod_barras") + "<br>");
				out.println("Título: " + rsl.getString("obras.titulo") + "<br>");
				out.println("Año de edición: " + rsl.getInt("obras.añoEdicion") + "<br>");
				out.println("Editorial: " + rsl.getString("libros.editorial") + "<br>");
				out.println("Número de páginas: " + rsl.getInt("libros.nPaginas") + "<br>");
				out.println("AUTOR<br>");
				out.println("Autor: " + rsl.getString("obras.autor") + "<br>");
				Statement stmta = cone.createStatement();
				ResultSet rsa = stmta.executeQuery("SELECT * FROM artistas WHERE nombre = '"+rsl.getString("obras.autor")+"'");
				while (rsa.next()) {
					out.println("Año de nacimiento: "+rsa.getInt("añoNacimiento")+"<hr>");
					Artista autor = new Artista(rsl.getString("obras.autor"),rsa.getInt("añoNacimiento"));
					//Artista autor = new Artista(rsl.getString("obras.autor"),0);
					Libro libro = new Libro(rsl.getString("obras.titulo"),autor,rsl.getInt("obras.añoEdicion"),rsl.getString("libros.editorial"),rsl.getInt("libros.nPaginas"));
					//libro.getTitulo();
					}
			}
		//} else if (st == "pelicula") {
			Statement stmtp = cone.createStatement();
			ResultSet rsp = stmtp.executeQuery("SELECT * FROM obras,peliculas WHERE obras.cod_barras = "+cb+" and obras.cod_barras=peliculas.cod_barras");
			while (rsp.next()) {
				out.println("Código de barras: " + rsp.getInt("obras.cod_barras") + "<br>");
				out.println("Título: " + rsp.getString("obras.titulo") + "<br>");
				out.println("Año de edición: " + rsp.getInt("obras.añoEdicion") + "<br>");
				out.println("Productora: " + rsp.getString("peliculas.productora") + "<br>");
				out.println("AUTOR");
				out.println("Autor: " + rsp.getString("obras.autor") + "<br>");
				Statement stmta = cone.createStatement();
				ResultSet rsa = stmta.executeQuery("SELECT * FROM artistas WHERE nombre = '"+rsp.getString("obras.autor")+"'");
				while (rsa.next()) {
					out.println("Año de nacimiento: "+rsa.getInt("añoNacimiento")+"<hr>");
					Artista autor = new Artista(rsp.getString("obras.autor"),rsa.getInt("añoNacimiento"));
					Pelicula pelicula = new Pelicula(rsp.getString("obras.titulo"),autor,rsp.getInt("obras.añoEdicion"),rsp.getString("peliculas.productora"));
				}				
		    }
		//} else {
			Statement stmtd = cone.createStatement();
			ResultSet rsd = stmtd.executeQuery("SELECT * FROM obras,discos WHERE obras.cod_barras = "+cb+" and obras.cod_barras=discos.cod_barras");
			while (rsd.next()) {
				out.println("Código de barras: " + rsd.getInt("obras.cod_barras") + "<br>");
				out.println("Título: " + rsd.getString("obras.titulo") + "<br>");
				out.println("Año de edición: " + rsd.getInt("obras.añoEdicion") + "<br>");
				out.println("Discográfica: " + rsd.getString("discos.discografica") + "<br>");
				out.println("Número de canciones: " + rsd.getInt("discos.nCanciones") + "<br>");
				out.println("AUTOR");
				out.println("Autor: " + rsd.getString("obras.autor") + "<br>");
				Statement stmta = cone.createStatement();
				ResultSet rsa = stmta.executeQuery("SELECT * FROM artistas WHERE nombre = '"+rsd.getString("obras.autor")+"'");
				while (rsa.next()) {
					out.println("Año de nacimiento: "+rsa.getInt("añoNacimiento")+"<hr>");
					Artista autor = new Artista(rsd.getString("obras.autor"),rsa.getInt("añoNacimiento"));
					Disco disco = new Disco(rsd.getString("obras.titulo"),autor,rsd.getInt("obras.añoEdicion"),rsd.getString("discos.discografica"),rsd.getInt("discos.nCanciones"));
				}				
		    }
		//}
		out.println("<br/><a href='index.html'><input type='button' value='Volver'></a>");
		out.println("</body></html>");
		cone.close();
		}catch(Exception ex){
			//Tratar el error
		}
	}

}
