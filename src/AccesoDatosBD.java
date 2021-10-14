import java.io.*;
import java.sql.*;
import java.util.*;

public class AccesoDatosBD {
	
	public static Connection estableceConexion(){
		Connection conn;
		
		// ESTO ES CON EL OBJETO PROPERTIES, PARA PODER MODIFICAR LA CONEXION DESDE UN FICHERO
	    Properties properties= new Properties();
	    try {
	      properties.load(new FileInputStream(new File("configuracion.conexion")));
	      
//	      System.out.println(properties.get("DRIVER"));
//	      System.out.println(properties.get("URL"));
//	      System.out.println(properties.get("USUARIO"));
//	      System.out.println(properties.get("CLAVE"));

//	      properties.get("DRIVER");
//	      properties.get("URL");
//	      properties.get("USUARIO");
//	      properties.get("CLAVE");
	      
	      //String Driver = properties.getProperty("DRIVER");
	      String Url = properties.getProperty("URL");
	      
	      String Usuario = properties.getProperty("USUARIO");
	      String Clave = properties.getProperty("CLAVE");
	      
	      //String encadenaConexion= Driver + Url;
	      
	      System.out.println("Connected");
	      
	      conn = DriverManager.getConnection(Url, Usuario, Clave);
	      return conn;
	      
	    } catch (FileNotFoundException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }catch (SQLException e) {
	    	
	    	e.printStackTrace();
	    } catch (Exception e) {
	    	
	    	e.printStackTrace();
	    } 
		return null;
	 }
	
	public static void desconexion(Connection conn) {
		
		try {
			
			if(conn!= null) {
				conn.close();
				System.out.println("\nTe has desconectado de la base de datos existosamente");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println(e);
		}
	
	}
	
	public static void close(ResultSet rs) 
	{
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement stmt) 
	{
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	//Cierre de la conexion
	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	

}	
