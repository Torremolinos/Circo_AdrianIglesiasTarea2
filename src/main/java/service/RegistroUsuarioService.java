/**
* Clase RegistroUsuarioService.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package service;

import dao.ArtistaDAO;
import dao.CoordinacionDAO;
import dao.CredencialesDAO;
import dao.PersonaDAO;
import dto.RegistroPersonaDTO;
import entidades.Perfiles;

public class RegistroUsuarioService {

	private final PersonaDAO personaDAO = new PersonaDAO();
	private final CoordinacionDAO coordinacionDAO = new CoordinacionDAO();
	private final ArtistaDAO artistaDAO = new ArtistaDAO();
	private final CredencialesDAO credencialesDAO = new CredencialesDAO();

	public boolean registrarUsuario(RegistroPersonaDTO dto) {

		boolean personaCreada = personaDAO.registrarPersona(dto.getEmail(),
				dto.getNombrePersona(), dto.getNacionalidad());

		if (!personaCreada) {
			return false;
		}

		Long idPersona = personaDAO.obtenerUltimoIdPersona();
		if (idPersona == null) {
			return false;
		}

		if (dto.getPerfil() == Perfiles.COORDINACION) {

			boolean coordinacionCreada = coordinacionDAO.registrarCoordinador(
					dto.getEsSenior(), dto.getFechaSenior(), idPersona);

			if (!coordinacionCreada) {
				return false;
			}

		} else if (dto.getPerfil() == Perfiles.ARTISTA) {

			boolean artistaCreado = artistaDAO.registrarArtista(dto.getApodo(),
					dto.getEspecialidad(), idPersona);

			if (!artistaCreado) {
				return false;
			}
		}

		boolean credencialesCreadas = credencialesDAO.registrarNuevaCredencial(
				dto.getNombreUsuario(), dto.getPassword(), idPersona,
				dto.getPerfil());

		if (!credencialesCreadas) {
			return false;
		}

		return true;
	}

}
