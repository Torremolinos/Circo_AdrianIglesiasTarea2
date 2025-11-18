/**
* Clase CredencialesDAO.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import entidades.Credenciales;
import entidades.Perfiles;

public class CredencialesDAO {

	private Connection connection;
	private static final Logger LOGGER = Logger
					.getLogger(CoordinacionDAO.class.getName());

	public Credenciales buscarUsuario(String nombre, String password) {
		Credenciales credenciales = null;
		String consulta = "SELECT * FROM credenciales WHERE nombre = ? AND password = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(consulta);
			ps.setString(1, nombre);
			ps.setString(2, password);
			rs = ps.executeQuery();

			if (rs.next()) {
				credenciales = new Credenciales(rs.getLong("id"),
								rs.getString("nombre"),
								rs.getString("password"),
								rs.getLong("id_persona"),
								Perfiles.valueOf(rs.getString("perfiles")
												.toUpperCase()));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al consultar usuario y contraseña",
							e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "Error cerrando recursos", e);
			}
		}
		return credenciales;
	}

	public void crearNuevaCredencial() {

	}
}
