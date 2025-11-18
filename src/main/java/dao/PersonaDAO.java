/**
* Clase PersonaDAO.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonaDAO {

	private Connection connection;

	public void buscarEmail(String email) {

		String consulta = "SELECT * FROM persona WHERE email =?";

		try {
			PreparedStatement ps = connection.prepareStatement(consulta);
			ps.setString(1, email);
			ps.close();
		} catch (SQLException e) {
			/* usar un LOGGER para auditar */
			e.printStackTrace();
		}

	}

	public void registrarPersona(String email, String nombre,
					String nacionalidad) {
		String consulta = "INSERT INTO persona (email,nombre,nacionalidad) VALUES('?','?','?')";
		try {
			PreparedStatement ps = connection.prepareStatement(consulta);
			ps.setString(1, email);
			ps.setString(2, nombre);
			ps.setString(3, nacionalidad);
			ps.executeUpdate();

			connection.commit();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
}
