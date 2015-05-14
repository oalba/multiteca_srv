package com.zubiri.Multiteca;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class Modificar
 */
public class Modificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modificar() {
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
		   
		    String cb = request.getParameter("codigoBarras");
		    //String cb = "1";
		    ResultSet rs = stmt.executeQuery("SELECT * FROM obras WHERE cod_barras = '"+cb+"'");
		    
		    
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><title></title></head>");
		out.println("<body>");
		//String auto = null;
		while (rs.next()) {
		out.print("<fieldset><legend><h2>Modificar obra</h2></legend>");
		out.print("<form method='POST' action='modificarok.jr'>");
			//out.print("Código de barras: <input type='text' name='codigoBarras' id='cb' value='"+cb+"' maxlength='7' disabled><br>");
			out.print("Código de barras: <input type='text' name='codigoBarras' id='cb' value='"+cb+"' maxlength='7'><br>");
			out.print("Título: <input type='text' name='titulo' value='"+rs.getString("titulo")+"'><br>");
			out.print("Autor: <input type='text' name='autor' value='"+rs.getString("autor")+"'><br>");
			out.print("Año de edición: <input type='number' name='anoEdicion' value='"+rs.getInt("añoEdicion")+"'><br>");
			
			//out.println(rs.getString("tipo"));
			//out.print("Tipo: <select name='tipo' disabled>");
			out.print("Tipo: <select name='tipo'>");
			String tipo = rs.getString("tipo");
			String libro = "libro";
			String peli = "pelicula";
			String disco = "disco";
			//String tipo = "libro";
			
			//if (tipo != "pelicula" && tipo != "disco") {
			if (tipo.equals(libro)) {
				
				out.print("<option value='libro' selected>Libro</option><option value='pelicula'>Pelicula</option><option value='disco'>Disco</option></select><br>");
				Statement stmtl = cone.createStatement();
				ResultSet rsl = stmtl.executeQuery("SELECT * FROM libros WHERE cod_barras = '"+cb+"'");
				while (rsl.next()){
					out.println("Editorial: <input type='text' name='editorial' value='"+rsl.getString("editorial")+"'><br>");
					out.println("Número de páginas: <input type='number' name='nPaginas' value='"+rsl.getInt("nPaginas")+"'><hr>");
				}
				
			//} else if (tipo != "libro" && tipo != "disco"){
			} else if (tipo.equals(peli)){
				out.print("<option value='libro'>Libro</option><option value='pelicula' selected>Pelicula</option><option value='disco'>Disco</option></select><br>");
				Statement stmtp = cone.createStatement();
				ResultSet rsp = stmtp.executeQuery("SELECT * FROM peliculas WHERE cod_barras = '"+cb+"'");
				while (rsp.next()){
					out.println("Productora: <input type='text' name='productora' value='"+rsp.getString("productora")+"'><hr>");
				}
			//} else if (tipo != "pelicula" && tipo != "libro"){
			} else if (tipo.equals(disco)){
				out.print("<option value='libro'>Libro</option><option value='pelicula'>Pelicula</option><option value='disco' selected>Disco</option></select><br>");
				Statement stmtd = cone.createStatement();
				ResultSet rsd = stmtd.executeQuery("SELECT * FROM discos WHERE cod_barras = '"+cb+"'");
				while (rsd.next()){
					out.println("Discográfica: <input type='text' name='discografica' value='"+rsd.getString("discografica")+"'><br>");
					out.println("Número de canciones: <input type='number' name='nCanciones' value='"+rsd.getInt("nCanciones")+"'><hr>");
				}
			}
						
			out.print("<input type='submit' value='Modificar'>");
		out.print("</form>");
		out.print("</fieldset><a href='index.html'><input type='button' value='Volver'></a>");
		}
		
		out.println("</body></html>");
		
		cone.close();
		}catch(Exception ex){
			//Tratar el error
		}
	}

}
