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
import utils.DatabaseConnection;

public class CredencialesDAO {

	private Connection connection;
	private static final Logger LOGGER = Logger
			.getLogger(CoordinacionDAO.class.getName());

	public CredencialesDAO() {
		this.connection = DatabaseConnection.getInstance().getConnection();
	}

	/***
	 * Metodo para buscar un usuario y traer sus credenciales, pasando el nombre
	 * y la contrasenia
	 * 
	 * @param nombre
	 * @param password
	 * @return
	 */
	public Credenciales buscarUsuario(String nombre, String password) {
		Credenciales credenciales = null;
		String consulta = "SELECT * FROM credenciales WHERE nombre = ? AND password = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(consulta);
			ps.setString(1, nombre.toLowerCase());
			ps.setString(2, password);
			rs = ps.executeQuery();

			if (rs.next()) {
				credenciales = new Credenciales(rs.getLong("id"),
						rs.getString("nombre"), rs.getString("password"),
						rs.getLong("id_persona"), Perfiles.valueOf(
								rs.getString("perfiles").toUpperCase()));
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

	public Credenciales buscarPorNombreUsuario(String nombre) {
		Credenciales credenciales = null;
		String consulta = "SELECT * FROM credenciales WHERE nombre = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(consulta);
			ps.setString(1, nombre.toLowerCase());
			rs = ps.executeQuery();
			if (rs.next()) {
				credenciales = new Credenciales(rs.getLong("id"),
						rs.getString("nombre"), rs.getString("password"),
						rs.getLong("id_persona"), Perfiles.valueOf(
								rs.getString("perfiles").toUpperCase()));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al consultar usuario",
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

	/***
	 * Metodo para registrar una nueva credencial pasandole de parametros
	 * nombre, password, id_persona y perfil
	 * 
	 * @param nombre
	 * @param password
	 * @param idPersona
	 * @param perfil
	 * @return
	 */
	public boolean registrarNuevaCredencial(String nombre, String password,
			Long idPersona, Perfiles perfil) {

		String consulta = "INSERT INTO credenciales (nombre, password, id_persona, perfiles) "
				+ "VALUES (?, ?, ?, ?)";

		try (PreparedStatement ps = connection.prepareStatement(consulta)) {

			ps.setString(1, nombre.toLowerCase());
			ps.setString(2, password);
			ps.setLong(3, idPersona);
			ps.setString(4, perfil.name());

			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE,
					" Error creando credenciales: " + e.getMessage());
		}

		return false;
	}

	/**
	 * Comprueba si ya existe un nombre de usuario.
	 */
	public boolean existeNombreUsuario(String nombreUsuario) {

		String sql = "SELECT 1 FROM credenciales WHERE nombre = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, nombreUsuario.toLowerCase());

			try (ResultSet rs = ps.executeQuery()) {
				return rs.next(); // hay al menos un registro
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE,
					"Error al comprobar nombre de usuario existente", e);
		}

		return false;
	}

	/**
	 * Recupera las credenciales asociadas a una persona.
	 */
	public Credenciales buscarPorIdPersona(Long idPersona) {

		String sql = "SELECT * FROM credenciales WHERE id_persona = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setLong(1, idPersona);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Credenciales c = new Credenciales();
					c.setId(rs.getLong("id"));
					c.setNombre(rs.getString("nombre"));
					c.setPassword(rs.getString("password"));
					c.setId_persona(rs.getLong("id_persona"));
					c.setPerfil(Perfiles.valueOf(rs.getString("perfiles")));
					return c;
				}
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE,
					"Error al buscar credenciales por id_persona", e);
		}

		return null;
	}

	/**
	 * Actualiza usuario, password y perfil de una persona.
	 * 
	 */
	public boolean actualizarCredenciales(Long idPersona, String nuevoNombre,
			String nuevaPassword, Perfiles nuevoPerfil) {

		String sql = "UPDATE credenciales "
				+ "SET nombre = ?, password = ?, perfiles = ? "
				+ "WHERE id_persona = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, nuevoNombre.toLowerCase());
			ps.setString(2, nuevaPassword);
			ps.setString(3, nuevoPerfil.name());
			ps.setLong(4, idPersona);

			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al actualizar credenciales", e);
		}

		return false;
	}
}
