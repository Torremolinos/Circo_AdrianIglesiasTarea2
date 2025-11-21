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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entidades.Artista;
import utils.DatabaseConnection;
import utils.EspecialidadesAdapter;

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
	 * @param especialidad
	 * @param id_persona
	 * @return
	 */
	public boolean registrarArtista(String apodo, String especialidad,
			Long id_persona) {

		String sql = "INSERT INTO artista (apodo, especialidad, id_persona) VALUES (?, ?, ?)";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, apodo);
			ps.setString(2, especialidad);
			ps.setLong(3, id_persona);

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
					Artista a = new Artista();
					a.setIdArt(rs.getLong("id"));
					a.setApodo(rs.getString("apodo"));

					String especialidadesTexto = rs.getString("especialidad");

					a.setEspecialidades(EspecialidadesAdapter
							.stringALista(especialidadesTexto));

					a.setId_persona(idPersona);

					return a;
				}
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error buscando artista", e);
		}

		return null;
	}

	/***
	 * Metodo para actualizar artista pasando los parametros idPersona, apodo y
	 * especialidades (texto "ACROBACIA,MAGIA")
	 *
	 * @param idPersona
	 * @param apodo
	 * @param especialidades
	 * @return
	 */
	public boolean actualizarArtista(Long idPersona, String apodo,
			String especialidades) {

		String sql = "UPDATE artista SET apodo = ?, especialidad = ? WHERE id_persona = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, apodo);
			ps.setString(2, especialidades.toLowerCase());
			ps.setLong(3, idPersona);

			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error actualizando artista", e);
			return false;
		}
	}

	/**
	 * Lista todos los artistas registrados en el sistema
	 */
	public List<Artista> listarArtistas() {

		List<Artista> artistas = new ArrayList<>();

		String sql = "SELECT id, apodo, id_persona FROM artista";

		try (PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				Artista a = new Artista();
				a.setIdArt(rs.getLong("id"));
				a.setApodo(rs.getString("apodo"));
				a.setId_persona(rs.getLong("id_persona"));

				artistas.add(a);
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al listar artistas", e);
		}

		return artistas;
	}
}
