/**
* Clase CoordinacionDAO.java
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


public class CoordinacionDAO {

	private Connection connection;
	private static final Logger LOGGER = Logger
					.getLogger(CoordinacionDAO.class.getName());



	public void insertarCredenciales (String email, String nombre,
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
