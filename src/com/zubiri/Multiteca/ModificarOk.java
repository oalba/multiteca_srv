package com.zubiri.Multiteca;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class ModificarOk
 */
public class ModificarOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarOk() {
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
		out.println("NUEVOS DATOS:<hr>");
		out.println("Código de barras: " + Integer.parseInt(request.getParameter("codigoBarras")) + "<br>");
		out.println("Título: " + request.getParameter("titulo") + "<br>");
		out.println("Autor: " + request.getParameter("autor") + "<br>");
		out.println("Año de edición: " + Integer.parseInt(request.getParameter("anoEdicion")) + "<br>");
		stmt.execute("UPDATE obras SET titulo = '"+request.getParameter("titulo")
				+"', autor = '"+request.getParameter("autor")
				+"', añoEdicion = "+Integer.parseInt(request.getParameter("anoEdicion"))
				+", tipo = '"+request.getParameter("tipo")
				+"' WHERE cod_barras = "+ Integer.parseInt(request.getParameter("codigoBarras")));
		String tipo = request.getParameter("tipo");
		String libro = "libro";
		String peli = "pelicula";
		String disco = "disco";
		
		if (tipo.equals(libro)) {
			out.println("Tipo: Libro<br>");
			out.println("Número de páginas: " + Integer.parseInt(request.getParameter("nPaginas")) + "<br>");
			out.println("Editorial: " + request.getParameter("editorial") + "<br>");
			Statement stmtl = cone.createStatement();
			stmtl.execute("UPDATE libros SET editorial = '"+request.getParameter("editorial")
					+"', nPaginas = "+Integer.parseInt(request.getParameter("nPaginas"))
					+" WHERE cod_barras = "+ Integer.parseInt(request.getParameter("codigoBarras")));
		} else if (tipo.equals(peli)){
			out.println("Tipo: Película<br>");
			out.println("Productora: " + request.getParameter("productora") + "<br>");
			Statement stmtp = cone.createStatement();
			stmtp.execute("UPDATE peliculas SET productora = '"+request.getParameter("productora")
					+"' WHERE cod_barras = "+ Integer.parseInt(request.getParameter("codigoBarras")));
		} else if (tipo.equals(disco)){
			out.println("Tipo: Disco<br>");
			out.println("Número de canciones: " + Integer.parseInt(request.getParameter("nCanciones")) + "<br>");
			out.println("Discográfica: " + request.getParameter("discografica") + "<br>");
			Statement stmtd = cone.createStatement();
			stmtd.execute("UPDATE discos SET discografica = '"+request.getParameter("discografica")
					+"', nCanciones = "+Integer.parseInt(request.getParameter("nCanciones"))
					+" WHERE cod_barras = "+ Integer.parseInt(request.getParameter("codigoBarras")));
		}
		
		out.println("<a href='index.html'><input type='button' value='Volver'></a>");
		out.println("</body></html>");
		cone.close();
		}catch(Exception ex){
			//Tratar el error
		}
	}

}
