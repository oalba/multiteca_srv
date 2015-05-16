package com.zubiri.Multiteca;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AnadirA
 */
public class AnadirA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnadirA() {
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
			out.print("Nombre: "+request.getParameter("nombre")+"<br>");
			//String nombre = request.getParameter("codigoBarras");
			out.print("Año de nacimiento: "+Integer.parseInt(request.getParameter("anoNacimiento"))+"<br>");
			//int anoEdicion = Integer.parseInt(request.getParameter("anoEdicion"));
			Statement stmto = cone.createStatement();
			stmto.executeUpdate("INSERT INTO artistas (nombre, añoNacimiento) VALUES ('"+request.getParameter("nombre")+"',"+Integer.parseInt(request.getParameter("anoNacimiento"))+")");

			
			
		out.println("Artista añadido correctamente.");
		out.println("<br/><a href='index.html'><input type='button' value='Volver'></a>");
		out.println("</body></html>");
		cone.close();
		}catch(Exception ex){
			//Tratar el error
		}
		
		
	}

}
