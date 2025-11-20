/**
* Clase ArtistaDAO.java
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

import entidades.Artista;
import utils.DatabaseConnection;

public class ArtistaDAO {

	private final Connection connection;
	private static final Logger LOGGER = Logger
			.getLogger(ArtistaDAO.class.getName());

	public ArtistaDAO() {
		this.connection = DatabaseConnection.getInstance().getConnection();
	}

	/***
	 * Metodo para registrar el artista agregando el apodo y asignado al
	 * id_persona correspondiente
	 * 
	 * @param apodo
	 * @param id_persona
	 * @return
	 */
	public boolean registrarArtista(String apodo, Long id_persona) {

		String sql = "INSERT INTO artista (apodo, id_persona) VALUES (?, ?)";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, apodo);
			ps.setLong(2, id_persona);

			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al registrar artista", e);
		}

		return false;
	}

	/***
	 * Metodo para buscar el id de la persona en artista y traer asi los datos
	 * del artista al completo.
	 * 
	 * @param idPersona
	 * @return
	 */

	public Artista buscarPorIdPersona(Long idPersona) {

		String sql = "SELECT * FROM artista WHERE id_persona = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setLong(1, idPersona);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Artista artista = new Artista();
					artista.setIdArt(rs.getLong("idart"));
					artista.setApodo(rs.getString("apodo"));
					artista.setId_persona(rs.getLong("id_persona"));
					return artista;
				}
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al buscar artista por id_persona",
					e);
		}

		return null;
	}

	/***
	 * Metodo para actualizar artista pasando los parametros idPersona y el
	 * apodo nuevo si quisiera actualizarlo.
	 * 
	 * @param idPersona
	 * @param nuevoApodo
	 * @return
	 */
	public boolean actualizarArtista(Long idPersona, String nuevoApodo) {

		String sql = "UPDATE artista SET apodo = ? WHERE id_persona = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, nuevoApodo);
			ps.setLong(2, idPersona);

			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al actualizar artista", e);
		}

		return false;
	}
}
