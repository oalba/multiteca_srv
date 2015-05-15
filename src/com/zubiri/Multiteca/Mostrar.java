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

import com.zubiri.multiteca.Artista;
import com.zubiri.multiteca.Libro;

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
			
			Statement stmto = cone.createStatement();
			/*Statement stmtl = cone.createStatement();
			Statement stmtp = cone.createStatement();
			Statement stmtd = cone.createStatement();*/
			
			ResultSet rso = stmto.executeQuery("SELECT * FROM obras");
			/*ResultSet rsl = stmtl.executeQuery("SELECT * FROM libros");
		    ResultSet rsp = stmtp.executeQuery("SELECT * FROM peliculas");
		    ResultSet rsd = stmtd.executeQuery("SELECT * FROM discos");*/
		    
		    
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><title></title></head>");
		out.println("<body>");
		
		while (rso.next()) {
			Integer cb = rso.getInt("cod_barras");
			//String tipo = rso.getString("tipo");
			//if (tipo=="libro") {
			
			//out.println("<h3>Libros</h3>");
			Statement stmtl = cone.createStatement();
			ResultSet rsl = stmtl.executeQuery("SELECT * FROM libros WHERE cod_barras="+cb);
			while (rsl.next()) {
			//Statement stmtla = cone.createStatement();
			//String au = rsl.getString("autor");
			out.println("<hr>");
			//ResultSet rsa = stmtla.executeQuery("SELECT añoNacimiento FROM artista WHERE nombre='"+au+"'");
			out.println("TIPO: " + rso.getString("tipo") + "<br>");
			out.println("Código de barras: " + cb + "<br>");
			out.println("Título: " + rso.getString("titulo") + "<br>");
				//out.println("Autor: " + rsa.getInt("añoNacimiento") + "<br>");
			//out.println("Autor: " + rso.getString("autor") + "<br>");
			out.println("Año de edición: " + rso.getInt("añoEdicion") + "<br>");
			out.println("Editorial: " + rsl.getString("editorial") + "<br>");
			out.println("Número de páginas: " + rsl.getInt("nPaginas") + "<br>");
			out.println("AUTOR<br>");
			out.println("Autor: " + rso.getString("obras.autor") + "<br>");
			Statement stmta = cone.createStatement();
			ResultSet rsa = stmta.executeQuery("SELECT * FROM artistas WHERE nombre = '"+rso.getString("obras.autor")+"'");
			while (rsa.next()) {
				out.println("Año de nacimiento: "+rsa.getInt("añoNacimiento")+"<hr>");
				//Artista autor = new Artista(rso.getString("obras.autor"),rsa.getInt("añoNacimiento"));
				//Artista autor = new Artista(rsl.getString("obras.autor"),0);
				//Libro libro = new Libro(rsl.getString("obras.titulo"),autor,rsl.getInt("obras.añoEdicion"),rsl.getString("libros.editorial"),rsl.getInt("libros.nPaginas"));
			}
			}
			
			//out.println("<h3>Películas</h3>");
			Statement stmtp = cone.createStatement();
			ResultSet rsp = stmtp.executeQuery("SELECT * FROM peliculas WHERE cod_barras="+cb);
			while (rsp.next()) {
			//Statement stmtla = cone.createStatement();
			//String au = rsl.getString("autor");
			out.println("<hr>");
			//ResultSet rsa = stmtla.executeQuery("SELECT añoNacimiento FROM artista WHERE nombre='"+au+"'");
			out.println("TIPO: " + rso.getString("tipo") + "<br>");
			out.println("Código de barras: " + cb + "<br>");
			out.println("Título: " + rso.getString("titulo") + "<br>");
				//out.println("Autor: " + rsa.getInt("añoNacimiento") + "<br>");
			//out.println("Autor: " + rso.getString("autor") + "<br>");
			out.println("Año de edición: " + rso.getInt("añoEdicion") + "<br>");
			out.println("Productora: " + rsp.getString("productora") + "<br>");
			out.println("AUTOR<br>");
			out.println("Autor: " + rso.getString("obras.autor") + "<br>");
			Statement stmta = cone.createStatement();
			ResultSet rsa = stmta.executeQuery("SELECT * FROM artistas WHERE nombre = '"+rso.getString("obras.autor")+"'");
			while (rsa.next()) {
				out.println("Año de nacimiento: "+rsa.getInt("añoNacimiento")+"<hr>");
				//Artista autor = new Artista(rso.getString("obras.autor"),rsa.getInt("añoNacimiento"));
				//Artista autor = new Artista(rsl.getString("obras.autor"),0);
				//Libro libro = new Libro(rsl.getString("obras.titulo"),autor,rsl.getInt("obras.añoEdicion"),rsl.getString("libros.editorial"),rsl.getInt("libros.nPaginas"));
			}
			}
			
			//out.println("<h3>Discos</h3>");
			Statement stmtd = cone.createStatement();
			ResultSet rsd = stmtd.executeQuery("SELECT * FROM discos WHERE cod_barras="+cb);
			while (rsd.next()) {
			//Statement stmtla = cone.createStatement();
			//String au = rsl.getString("autor");
			out.println("<hr>");
			//ResultSet rsa = stmtla.executeQuery("SELECT añoNacimiento FROM artista WHERE nombre='"+au+"'");
			out.println("TIPO: " + rso.getString("tipo") + "<br>");
			out.println("Código de barras: " + cb + "<br>");
			out.println("Título: " + rso.getString("titulo") + "<br>");
				//out.println("Autor: " + rsa.getInt("añoNacimiento") + "<br>");
			//out.println("Autor: " + rso.getString("autor") + "<br>");
			out.println("Discográfica: " + rsd.getString("discografica") + "<br>");
			out.println("Número de canciones: " + rsd.getInt("nCanciones") + "<br>");
			out.println("AUTOR<br>");
			out.println("Autor: " + rso.getString("obras.autor") + "<br>");
			Statement stmta = cone.createStatement();
			ResultSet rsa = stmta.executeQuery("SELECT * FROM artistas WHERE nombre = '"+rso.getString("obras.autor")+"'");
			while (rsa.next()) {
				out.println("Año de nacimiento: "+rsa.getInt("añoNacimiento")+"<hr>");
				//Artista autor = new Artista(rso.getString("obras.autor"),rsa.getInt("añoNacimiento"));
				//Artista autor = new Artista(rsl.getString("obras.autor"),0);
				//Libro libro = new Libro(rsl.getString("obras.titulo"),autor,rsl.getInt("obras.añoEdicion"),rsl.getString("libros.editorial"),rsl.getInt("libros.nPaginas"));
			}
			}
			//}
	    }
		
		/*while (rso.next()) {
			//if (rso.getString("tipo")=="pelicula") {
			Integer cb = rso.getInt("cod_barras");
			Statement stmtp = cone.createStatement();
			ResultSet rsp = stmtp.executeQuery("SELECT * FROM peliculas WHERE cod_barras="+cb);
			while (rsp.next()) {
			//Statement stmtla = cone.createStatement();
			//String au = rsl.getString("autor");
			out.println("<hr>");
			//ResultSet rsa = stmtla.executeQuery("SELECT añoNacimiento FROM artista WHERE nombre='"+au+"'");
			out.println("Código de barras: " + cb + "<br>");
			out.println("Título: " + rso.getString("titulo") + "<br>");
				//out.println("Autor: " + rsa.getInt("añoNacimiento") + "<br>");
			out.println("Autor: " + rso.getString("autor") + "<br>");
			out.println("Año de edición: " + rso.getInt("añoEdicion") + "<br>");
			out.println("Productora: " + rsp.getString("productora") + "<hr>");
			}
			//}
	    }*/
		/*while (rsp.next()) {
			out.println("Código de barras: " + rsp.getInt("cod_barras") + "<br>");
			out.println("Título: " + rsl.getString("titulo") + "<br>");
			out.println("Autor: " + rsl.getString("autor") + "<br>");
			out.println("Año de edición: " + rsl.getInt("añoEdicion") + "<br>");
			out.println("Productora: " + rsl.getString("productora") + "<hr>");
	    }*/
		
		/*while (rso.next()) {
			//if (rso.getString("tipo")=="disco") {
			Integer cb = rso.getInt("cod_barras");
			Statement stmtd = cone.createStatement();
			ResultSet rsd = stmtd.executeQuery("SELECT * FROM discos WHERE cod_barras="+cb);
			while (rsd.next()) {
			//Statement stmtla = cone.createStatement();
			//String au = rsl.getString("autor");
			out.println("<hr>");
			//ResultSet rsa = stmtla.executeQuery("SELECT añoNacimiento FROM artista WHERE nombre='"+au+"'");
			out.println("Código de barras: " + cb + "<br>");
			out.println("Título: " + rso.getString("titulo") + "<br>");
				//out.println("Autor: " + rsa.getInt("añoNacimiento") + "<br>");
			out.println("Autor: " + rso.getString("autor") + "<br>");
			out.println("Discográfica: " + rsd.getString("discografica") + "<br>");
			out.println("Número de canciones: " + rsd.getInt("nCanciones") + "<hr>");
			}
			//}
	    }*/
		/*while (rsd.next()) {
			out.println("Código de barras: " + rsd.getInt("cod_barras") + "<br>");
			out.println("Título: " + rsl.getString("titulo") + "<br>");
			out.println("Autor: " + rsl.getString("autor") + "<br>");
			out.println("Año de edición: " + rsl.getInt("añoEdicion") + "<br>");
			out.println("Discográfica: " + rsl.getString("discografica") + "<br>");
			out.println("Número de canciones: " + rsl.getInt("nCanciones") + "<hr>");
	    }
		*/
		out.println("<br/><a href='index.html'><input type='button' value='Volver'></a>");
		out.println("</body></html>");
		cone.close();
		}catch(Exception ex){
			//Tratar el error
		}
	}

}
