/**
* Clase NumeroDAO.java
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

import entidades.Numero;
import utils.DatabaseConnection;

public class NumeroDAO {

	private final Connection connection;
	private static final Logger LOGGER = Logger
			.getLogger(NumeroDAO.class.getName());

	public NumeroDAO() {
		this.connection = DatabaseConnection.getInstance().getConnection();
	}

	/***
	 * Metodo para crear un numero nuevo pasandole de parametro un objeto Numero
	 * 
	 * @param numero
	 * @return
	 */

	public boolean crearNumero(Numero numero) {

		String sql = "INSERT INTO numero (`order`, nombre, duracion, id_espectaculo) "
				+ "VALUES (?, ?, ?, ?)";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setInt(1, numero.getOrder());
			ps.setString(2, numero.getNombre());
			ps.setDouble(3, numero.getDuracion());
			ps.setLong(4, numero.getId_espectaculo());

			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al crear número", e);
		}

		return false;
	}

	/***
	 * Metodo para listar espectaculos por orden de order
	 * 
	 * @param idEspectaculo
	 * @return
	 */

	public List<Numero> listarPorEspectaculo(Long idEspectaculo) {

		String sql = "SELECT * FROM numero WHERE id_espectaculo = ? "
				+ "ORDER BY `order`";

		List<Numero> lista = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setLong(1, idEspectaculo);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Numero n = new Numero();
					n.setId(rs.getLong("id"));
					n.setOrder(rs.getInt("order"));
					n.setNombre(rs.getString("nombre"));
					n.setDuracion(rs.getDouble("duracion"));
					n.setId(rs.getLong("id_espectaculo"));
					lista.add(n);
				}
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al listar números por espectáculo",
					e);
		}

		return lista;
	}

	/***
	 * Metodo para actualizar numeros pasandole de parametro el numero
	 * 
	 * @param numero
	 * @return
	 */

	public boolean actualizarNumero(Numero numero) {

		String sql = "UPDATE numero "
				+ "SET `order` = ?, nombre = ?, duracion = ? " + "WHERE id = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setInt(1, numero.getOrder());
			ps.setString(2, numero.getNombre());
			ps.setDouble(3, numero.getDuracion());
			ps.setLong(4, numero.getId());

			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al actualizar número", e);
		}

		return false;
	}

	/***
	 * Metodo para borrar numeros pasandole el id del numero a borrar
	 * 
	 * @param idNumero
	 * @return
	 */
	public boolean borrarNumero(Long idNumero) {

		String sql = "DELETE FROM numero WHERE id = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setLong(1, idNumero);
			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al borrar número", e);
		}

		return false;
	}
}
