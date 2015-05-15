package com.zubiri.Multiteca;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class Anadir
 */
public class Anadir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Anadir() {
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
			out.print("a");
			int cb = Integer.parseInt(request.getParameter("codigoBarras"));
			out.print("Código de barras: " + cb + "<br>");
			//int codigoBarras = Integer.parseInt(request.getParameter("codigoBarras"));
			//out.print("Código de barras: "+codigoBarras+"<br>");
			//out.print("Código de barras: "+Integer.parseInt(request.getParameter("codigoBarras"))+"<br>");
			//int codigoBarras = Integer.parseInt(request.getParameter("codigoBarras"));
			out.print("Título: "+request.getParameter("titulo")+"<br>");
			String titulo = request.getParameter("titulo");
			out.print("Autor: "+request.getParameter("autor")+"<br>");
			String autor = request.getParameter("autor");
			out.print("Año de edición: "+request.getParameter("añoEdicion")+"<br>");
			//int añoEdicion = Integer.parseInt(request.getParameter("añoEdicion"));

			
			String tipo = request.getParameter("tipo");
			String libro = "libro";
			String peli = "pelicula";
			String disco = "disco";
			if (tipo.equals(disco)) {
				out.println("Tipo: Disco<br>");
				out.print("Discografica: "+request.getParameter("discografica")+"<br>");
				String discografica = request.getParameter("discografica");
				out.print("Numero de canciones: "+request.getParameter("numCanciones")+"<br>");
				int numCanciones = Integer.parseInt(request.getParameter("numCanciones"));


				Statement stmtd = cone.createStatement();
				stmtd.executeQuery("INSERT INTO obras(cod_barras, titulo, autor, añoEdicion, tipo) VALUES ( '"+request.getParameter("codigoBarras")+"','"+titulo+"','"+autor+"','"+request.getParameter("añoEdicion")+"','"+tipo+"'");
				stmtd.executeQuery("INSERT INTO discos(cod_barras, discografica, nCanciones) VALUES ('"+request.getParameter("codigoBarras")+"','"+discografica+"','"+numCanciones+"'");

			} else if (tipo.equals(peli)){
				out.print("Tipo: Película<br>");
				out.print("Productora: "+request.getParameter("productora")+"<br>");
				String productora = request.getParameter("productora");
				
				Statement stmtp = cone.createStatement();
				stmtp.executeQuery("INSERT INTO obras(cod_barras, titulo, autor, añoEdicion, tipo) VALUES ( '"+request.getParameter("codigoBarras")+"','"+titulo+"','"+autor+"','"+request.getParameter("añoEdicion")+"','"+tipo+"'");
				stmtp.executeQuery("INSERT INTO peliculas(cod_barras, productora) VALUES ('"+request.getParameter("codigoBarras")+"','"+productora+"'");
				
			} else if (tipo.equals(libro)){
				out.print("Tipo: Libro<br>");
				out.print("Editorial: "+request.getParameter("editorial")+"<br>");
				String editorial = request.getParameter("editorial");
				out.print("Numero de paginas: "+request.getParameter("numPaginas")+"<br>");
				int numPaginas = Integer.parseInt(request.getParameter("numPaginas"));
				
				Statement stmtl = cone.createStatement();
				stmtl.executeQuery("INSERT INTO obras(cod_barras, titulo, autor, añoEdicion, tipo) VALUES ( "+request.getParameter("codigoBarras")+",'"+titulo+"','"+autor+"',"+request.getParameter("añoEdicion")+",'"+tipo+"'");
				stmtl.executeQuery("INSERT INTO libros(cod_barras, editorial, nPaginas) VALUES ("+request.getParameter("codigoBarras")+",'"+editorial+"',"+numPaginas);
			}
			
		out.println("Media añadida correctamente.");
		out.println("<br/><a href='index.html'><input type='button' value='Volver'></a>");
		out.println("</body></html>");
		cone.close();
		}catch(Exception ex){
			//Tratar el error
		}
	}

}
