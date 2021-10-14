import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonaBBDD {
	
	private final String SQL_INSERT = "INSERT INTO Persona (Nombre,id,fechaNacimiento) VALUES(?,?,?)";
	private final String SQL_UPDATE = "UPDATE Persona SET Nombre=?, fechaNacimiento=? WHERE id=?";
	private final String SQL_DELETE = "DELETE FROM Persona WHERE id=?";
	private final String SQL_SELECT = "SELECT * FROM Persona ORDER BY id";
	
	
	public int insert(int id, String nombre, LocalDate fechaNacimiento) 
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0; //registros afectados
		
		
		Date date = Date.valueOf(fechaNacimiento);
	      
		
		try {
			conn = AccesoDatosBD.estableceConexion();
			stmt = conn.prepareStatement(SQL_INSERT);
			int index = 1;//contador de columnas
			stmt.setString(index++, nombre);//param 1 => ?
			stmt.setInt(index++, id);
			stmt.setDate(index++,date);
			System.out.println("Ejecutando query:" + SQL_INSERT);
			rows = stmt.executeUpdate();//no. registros afectados
			System.out.println("Registros afectados:" + rows);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccesoDatosBD.close(stmt);
			AccesoDatosBD.close(conn);
		}
		
		return rows;
	}
	
	public int update(int id, String nombre, LocalDate fechaNacimiento) 
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		
		Date date = Date.valueOf(fechaNacimiento);
		
		try {
			conn = AccesoDatosBD.estableceConexion();
			System.out.println("Ejecutando query:" + SQL_UPDATE);
			stmt = conn.prepareStatement(SQL_UPDATE);
			int index = 1;
			stmt.setString(index++, nombre);
			stmt.setDate(index++, date);
			stmt.setInt(index, id);
			rows = stmt.executeUpdate();
			System.out.println("Registros actualizados:" + rows);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccesoDatosBD.close(stmt);
			AccesoDatosBD.close(conn);
		}
		
		return rows;
	}
	
	public int delete(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		
		try {
			conn = AccesoDatosBD.estableceConexion();
			System.out.println("Ejecutando query:" + SQL_DELETE);
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, id);
			rows = stmt.executeUpdate();
			System.out.println("Registros eliminados:" + rows);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccesoDatosBD.close(stmt);
			AccesoDatosBD.close(conn);
		}
		
		return rows;
	}
	
	
	public List<Persona> select() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Persona persona = null;
		List<Persona> personas = new ArrayList<Persona>();
		
		
		try {
			conn = AccesoDatosBD.estableceConexion();
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(2);
				String nombre = rs.getString(1);
				Date date = rs.getDate(3);
				persona = new Persona();
				persona.setId(id);
				persona.setNombre(nombre);
				persona.setFechaNac(date.toLocalDate());
				personas.add(persona);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccesoDatosBD.close(rs);
			AccesoDatosBD.close(stmt);
			AccesoDatosBD.close(conn);
		}
			return personas;

}
}
