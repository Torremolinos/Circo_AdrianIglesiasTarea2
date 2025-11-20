/**
* Clase CoordinacionDAO.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import entidades.Coordinacion;
import utils.DatabaseConnection;

public class CoordinacionDAO {

	private Connection connection;
	private static final Logger LOGGER = Logger
			.getLogger(CoordinacionDAO.class.getName());

	public CoordinacionDAO() {
		this.connection = DatabaseConnection.getInstance().getConnection();
	}

	public boolean registrarCoordinador(Boolean senior, LocalDate fechasenior,
			Long idPersona) {

		String consulta = "INSERT INTO coordinacion (senior, fechasenior, id_persona) "
				+ "VALUES (?, ?, ?)";

		try (PreparedStatement ps = connection.prepareStatement(consulta)) {

			ps.setBoolean(1, senior);
			java.sql.Date fechaSql = java.sql.Date.valueOf(fechasenior);
			ps.setDate(2, fechaSql);
			ps.setLong(3, idPersona);

			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error creando coordinador", e);
		}

		return false;
	}

	public Coordinacion buscarPorIdPersona(Long idPersona) {

		String sql = "SELECT * FROM coordinacion WHERE id_persona = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setLong(1, idPersona);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Coordinacion coordinacion = new Coordinacion();

					coordinacion.setId(rs.getLong("id"));
					coordinacion.setSenior(rs.getBoolean("senior"));

					Date fecha = rs.getDate("fechasenior");
					if (fecha != null) {
						coordinacion.setFechasenior(fecha.toLocalDate());
					}

					coordinacion.setId_persona(rs.getLong("id_persona"));

					return coordinacion;
				}
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE,
					"Error al buscar coordinación por id_persona", e);
		}

		return null;
	}

	
	/***
	 * Metodo para buscar coordinacion por id del mismo
	 * @param idCoordinacion
	 * @return Coordinacion
	 */
	public Coordinacion buscarPorIdCoordinacion(Long idCoordinacion) {

		String sql = "SELECT * FROM coordinacion WHERE id = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setLong(1, idCoordinacion);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Coordinacion coordinacion = new Coordinacion();

					coordinacion.setId(rs.getLong("id"));
					coordinacion.setSenior(rs.getBoolean("senior"));

					Date fecha = rs.getDate("fechasenior");
					if (fecha != null) {
						coordinacion.setFechasenior(fecha.toLocalDate());
					}

					coordinacion.setId_persona(rs.getLong("id_persona"));

					return coordinacion;
				}
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE,
					"Error al buscar coordinación por id_persona", e);
		}

		return null;
	}

	public boolean actualizarCoordinacion(Long idPersona, Boolean senior,
			LocalDate fechaSenior) {

		String sql = "UPDATE coordinacion " + "SET senior = ?, fechasenior = ? "
				+ "WHERE id_persona = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setBoolean(1, senior != null ? senior : false);
			ps.setDate(2, Date.valueOf(fechaSenior));
			ps.setLong(3, idPersona);

			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al actualizar coordinación", e);
		}

		return false;
	}

}
