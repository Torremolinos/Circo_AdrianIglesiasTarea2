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

public class EspectaculoDAO {

	private Connection connection;

	public LinkedHashSet<Espectaculo> listaEspectaculos() {

		String sql = "SELECT * FROM espectaculo";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Espectaculo e = new Espectaculo();
				e.setId(rs.getLong("id_espectaculo"));
				e.setNombre(rs.getString("nombre"));
				e.setFechaini(rs.getDate("fechaini").toLocalDate());
				e.setFechafin(rs.getDate("fechafin").toLocalDate());
				e.setIdCoordinacion(rs.getLong("id_coordinacion"));
				listaEspectaculos().add(e);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaEspectaculos();
	}

}
