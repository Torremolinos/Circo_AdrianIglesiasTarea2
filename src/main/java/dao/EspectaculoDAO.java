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
import java.util.logging.Level;
import java.util.logging.Logger;

import entidades.Coordinacion;
import entidades.Espectaculo;
import entidades.Perfiles;
import entidades.Sesion;
import utils.DatabaseConnection;

public class EspectaculoDAO {
	private final Connection connection;
	private static final Logger LOGGER = Logger
			.getLogger(EspectaculoDAO.class.getName());

	public EspectaculoDAO() {
		this.connection = DatabaseConnection.getInstance().getConnection();
	}

	/***
	 * Metodo para listar espectaculos. Devuelve una lista de espectaculos
	 * 
	 * @return
	 */
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
				e.setId_coordinacion(rs.getLong("id_coordinacion"));
				lista.add(e);
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al listar los espectaculos", e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	public Espectaculo buscarPorId(Long idEspectaculo) {

		String sql = "SELECT * FROM espectaculo WHERE id = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setLong(1, idEspectaculo);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Espectaculo e = new Espectaculo();
					e.setId(rs.getLong("id"));
					e.setNombre(rs.getString("nombre"));
					e.setFechaini(rs.getDate("fechaini").toLocalDate());
					e.setFechafin(rs.getDate("fechafin").toLocalDate());
					e.setId_coordinacion(rs.getLong("id_coordinacion"));
					return e;
				}
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al buscar espectáculo por id", e);
		}

		return null;
	}

	public boolean crearEspectaculo(Espectaculo espectaculo) {

		String sql = "INSERT INTO espectaculo "
				+ "(nombre, fechaini, fechafin, id_coordinacion) "
				+ "VALUES (?, ?, ?, ?)";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, espectaculo.getNombre());
			ps.setDate(2, java.sql.Date.valueOf(espectaculo.getFechaini()));
			ps.setDate(3, java.sql.Date.valueOf(espectaculo.getFechafin()));
			ps.setLong(4, espectaculo.getId_coordinacion());

			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al crear espectáculo", e);
		}

		return false;
	}

	public boolean actualizarEspectaculo(Espectaculo espectaculo) {

		String sql = "UPDATE espectaculo "
				+ "SET nombre = ?, fechaini = ?, fechafin = ?, id_coordinacion = ? "
				+ "WHERE id = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, espectaculo.getNombre());
			ps.setDate(2, java.sql.Date.valueOf(espectaculo.getFechaini()));
			ps.setDate(3, java.sql.Date.valueOf(espectaculo.getFechafin()));
			ps.setLong(4, espectaculo.getId_coordinacion());
			ps.setLong(5, espectaculo.getId());

			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al actualizar espectáculo", e);
		}

		return false;
	}
}
