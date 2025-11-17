/**
* Clase CredencialesDAO.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.LoginDto;

public class CredencialesDAO {

	private Connection connection;

	public void buscarUsuarioyPassword(String usuario, String password) {

		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM credenciales WHERE usuario = ? AND password = ?");
			ps.setString(1, usuario);
			ps.setString(2, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void buscarEmail() {

	}
}
