/**
* Clase PersonaDAO.java
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

import entidades.Persona;
import utils.DatabaseConnection;

public class PersonaDAO {

	private Connection connection;
	private static final Logger LOGGER = Logger
			.getLogger(PersonaDAO.class.getName());

	public PersonaDAO() {
		this.connection = DatabaseConnection.getInstance().getConnection();
	}

	/**
	 * Busca una persona por nombre y email.
	 * 
	 * @param nombre - nombre de la persona
	 * @param email  - email de la persona
	 * @return Persona encontrada o null si no existe
	 */

	public Persona buscarPorNombreYEmail(String nombre, String email) {
		String consulta = "SELECT * FROM persona WHERE nombre = ? AND email = ?";

		/*
		 * Los try with resources cierran la conexion PrepareStatement y
		 * Resulset al acabar, es más comodo.
		 */
		try (PreparedStatement ps = connection.prepareStatement(consulta)) {

			ps.setString(1, nombre);
			ps.setString(2, email);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Persona p = new Persona();
					p.setId(rs.getLong("id"));
					p.setEmail(rs.getString("email"));
					p.setNombre(rs.getString("nombre"));
					p.setNacionalidad(rs.getString("nacionalidad"));
					return p;
				}
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al buscar la persona", e);
		}

		return null;
	}

	/**
	 * Inserta una persona en la BD.
	 * 
	 * @return true si se insertó al menos una fila, false si falló.
	 */
	public boolean registrarPersona(String email, String nombre,
			String nacionalidad) {

		String consulta = "INSERT INTO persona (email, nombre, nacionalidad) VALUES (?, ?, ?)";

		try (PreparedStatement ps = connection.prepareStatement(consulta)) {

			ps.setString(1, email);
			ps.setString(2, nombre);
			ps.setString(3, nacionalidad);

			int filas = ps.executeUpdate();

			return filas > 0;

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al registrar a la persona", e);

		}
		return false;
	}

	/**
	 * Devuelve el último id_persona insertado según MAX(id_persona).
	 * 
	 * @return Long o null si no encuentra
	 */
	public Long obtenerUltimoIdPersona() {
		String consulta = "SELECT MAX(id) AS id FROM persona";

		try (PreparedStatement ps = connection.prepareStatement(consulta);
				ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				long id = rs.getLong("id");
				if (!rs.wasNull()) {
					return id;
				}
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al obtener el id", e);
		}

		return null;
	}

	/***
	 * Metodo para buscar una persona pasando de parametro el id de la persona
	 * 
	 * @param idPersona
	 * @return
	 */

	public Persona buscarPorId(Long idPersona) {
		String sql = "SELECT * FROM persona WHERE id = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setLong(1, idPersona);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Persona p = new Persona();
					p.setId(rs.getLong("id"));
					p.setEmail(rs.getString("email"));
					p.setNombre(rs.getString("nombre"));
					p.setNacionalidad(rs.getString("nacionalidad"));
					return p;
				}
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al buscar persona por id", e);
		}

		return null;
	}

	/***
	 * Metodo para actualizar a la persona, pasando su id y los parametros que
	 * quiera actualizar.
	 * 
	 * @param idPersona
	 * @param nombre
	 * @param email
	 * @param nacionalidad
	 * @return
	 */

	public boolean actualizarPersona(Long idPersona, String nombre,
			String email, String nacionalidad) {

		String sql = "UPDATE persona "
				+ "SET nombre = ?, email = ?, nacionalidad = ? "
				+ "WHERE id = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, nombre);
			ps.setString(2, email);
			ps.setString(3, nacionalidad);
			ps.setLong(4, idPersona);

			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al actualizar persona", e);
		}

		return false;
	}
	

	public boolean existeEmail(String email) {
	    String sql = "SELECT 1 FROM persona WHERE email = ?";

	    try (PreparedStatement ps = connection.prepareStatement(sql)) {

	        ps.setString(1, email);

	        try (ResultSet rs = ps.executeQuery()) {
	            return rs.next();
	        }

	    } catch (SQLException e) {
	        LOGGER.log(Level.SEVERE, "Error al comprobar email existente", e);
	    }

	    return false;
	}

}
