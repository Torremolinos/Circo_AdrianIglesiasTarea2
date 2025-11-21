/**
* Clase ParticipaDAO.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.DatabaseConnection;

public class ParticipaDAO {
	private final Connection connection;
	private static final Logger LOGGER = Logger
			.getLogger(ParticipaDAO.class.getName());

	public ParticipaDAO() {
		this.connection = DatabaseConnection.getInstance().getConnection();
	}

	/**
	 * Inserta una relación artista - número.
	 */
	public boolean insertarParticipacion(Long idArtista, Long idNumero) {

		String sql = "INSERT INTO participa (idArt, idNumero) "
				+ "VALUES (?, ?)";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setLong(1, idArtista);
			ps.setLong(2, idNumero);

			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE,
					"Error al insertar participación artista-número", e);
		}

		return false;
	}

	/**
	 * Borra todas las participaciones de un artista. Útil cuando cambias sus
	 * "especialidades"/números.
	 */
	public boolean borrarParticipacionesDeArtista(Long idArtista) {

		String sql = "DELETE FROM participa WHERE idArt = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setLong(1, idArtista);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE,
					"Error al borrar participaciones de artista", e);
		}

		return false;
	}

	/**
	 * Devuelve los idNumero en los que participa un artista.
	 */
	public List<Long> obtenerNumerosDeArtista(Long idArtista) {

		String sql = "SELECT idNumero FROM participa WHERE idArt = ?";
		List<Long> numeros = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setLong(1, idArtista);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					numeros.add(rs.getLong("idNumero"));
				}
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al obtener números de un artista",
					e);
		}

		return numeros;
	}

	/**
	 * Devuelve los idArt de artistas que participan en un número.
	 */
	public List<Long> obtenerArtistasDeNumero(Long idNumero) {

		String sql = "SELECT idArt FROM participa WHERE idNumero = ?";
		List<Long> artistas = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setLong(1, idNumero);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					artistas.add(rs.getLong("idArt"));
				}
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al obtener artistas de un número",
					e);
		}

		return artistas;
	}
}
