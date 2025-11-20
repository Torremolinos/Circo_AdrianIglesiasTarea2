/**
* Clase EspectaculosDAO.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import entidades.Coordinacion;
import entidades.Espectaculo;
import entidades.Perfiles;
import entidades.Sesion;
import utils.DatabaseConnection;

public class EspectaculoDAO {
	private final Connection connection;

	public EspectaculoDAO() {
		this.connection = DatabaseConnection.getInstance().getConnection();
	}

	public LinkedHashSet<Espectaculo> listaEspectaculos() {
		LinkedHashSet<Espectaculo> lista = new LinkedHashSet<>();
		String sql = "SELECT * FROM espectaculo";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			 ps = connection.prepareStatement(sql);
			 rs = ps.executeQuery();
			while (rs.next()) {
				Espectaculo e = new Espectaculo();
				e.setId(rs.getLong("id"));
				e.setNombre(rs.getString("nombre"));
				e.setFechaini(rs.getDate("fechaini").toLocalDate());
				e.setFechafin(rs.getDate("fechafin").toLocalDate());
				e.setIdCoordinacion(rs.getLong("id_coordinacion"));
				lista.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			 try {
			        if (rs != null) rs.close();
			        if (ps != null) ps.close();
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
		}
		return lista;
	}

	public static void guardarEspectaculos(LinkedHashSet<Espectaculo> espectaculos) {

	}

	public void mostrarInforme(Perfiles perfil) {
	}

	public static void crearEspectaculo(Sesion sesion, Perfiles perfilUsuario) {
	}

	public static Coordinacion seleccionarCoordinador() {
		return null;
	}

	public static void modificarEspectaculo(Perfiles perfilUsuario) {
	}
}
