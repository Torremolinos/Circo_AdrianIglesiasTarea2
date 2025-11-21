/**
* Clase GestionUsuariosService.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package service;

import dao.ArtistaDAO;
import dao.CoordinacionDAO;
import dao.CredencialesDAO;
import dao.PersonaDAO;
import entidades.Artista;
import entidades.Coordinacion;
import entidades.Credenciales;
import entidades.Perfiles;
import entidades.Persona;
import utils.EspecialidadesAdapter;
import views.GestionUsuarioView;

public class GestionUsuariosService {

	private final PersonaDAO personaDAO = new PersonaDAO();
	private final CredencialesDAO credencialesDAO = new CredencialesDAO();
	private final CoordinacionDAO coordinacionDAO = new CoordinacionDAO();
	private final ArtistaDAO artistaDAO = new ArtistaDAO();
	private final GestionUsuarioView vistaGestion = new GestionUsuarioView();
	private final PaisService paisService = new PaisService();

	public void gestionarDatosPorUsuario() {

		String nombreUsuario = vistaGestion.pedirNombreUsuarioParaGestion();

		Credenciales cred = credencialesDAO
				.buscarPorNombreUsuario(nombreUsuario);
		if (cred == null) {
			vistaGestion.mostrarUsuarioNoEncontrado(nombreUsuario);
			return;
		}

		Long idPersona = cred.getId_persona();
		Perfiles perfil = cred.getPerfil();

		Persona persona = personaDAO.buscarPorId(idPersona);
		if (persona == null) {
			vistaGestion.mostrarPersonaNoEncontrada(idPersona);
			return;
		}

		String nuevoNombre = vistaGestion.pedirNuevoNombre(persona.getNombre());
		String nuevoEmail = vistaGestion.pedirNuevoEmail(persona.getEmail());
		String nuevaNacionalidad = vistaGestion.pedirNuevaNacionalidad(
				persona.getNacionalidad(), paisService.obtenerTodosLosPaises());

		boolean actualizarPersona = personaDAO.actualizarPersona(idPersona,
				nuevoNombre, nuevoEmail, nuevaNacionalidad);
		vistaGestion.mostrarResultadoActualizacionDatosPersonales(
				actualizarPersona);

		if (!actualizarPersona) {
			return;
		}

		if (perfil == Perfiles.COORDINACION) {
			gestionarDatosCoordinacion(idPersona);
		} else if (perfil == Perfiles.ARTISTA) {
			gestionarDatosArtista(idPersona);
		} else {
			vistaGestion.mostrarSinDatosProfesionales(perfil);
		}
	}

	private void gestionarDatosCoordinacion(Long idPersona) {

		Coordinacion coord = coordinacionDAO.buscarPorIdPersona(idPersona);
		if (coord == null) {
			vistaGestion.mostrarSinCoordinacion();
			return;
		}

		Boolean seniorActual = coord.getSenior();
		java.time.LocalDate fechaActual = coord.getFechasenior();

		Boolean nuevoSenior = vistaGestion.pedirNuevoSenior(seniorActual);
		java.time.LocalDate nuevaFechaSenior = fechaActual;

		if (nuevoSenior != null && nuevoSenior) {
			nuevaFechaSenior = vistaGestion.pedirNuevaFechaSenior(fechaActual);
		}

		boolean ok = coordinacionDAO.actualizarCoordinacion(idPersona,
				nuevoSenior, nuevaFechaSenior);
		vistaGestion.mostrarResultadoActualizacionCoordinacion(ok);
	}

	private void gestionarDatosArtista(Long idPersona) {

		Artista artista = artistaDAO.buscarPorIdPersona(idPersona);
		if (artista == null) {
			vistaGestion.mostrarSinArtista();
			return;
		}

		String nuevoApodo = vistaGestion.pedirNuevoApodo(artista.getApodo());

		String especialidadesActualesTexto = EspecialidadesAdapter
				.listaAString(artista.getEspecialidades());

		String nuevasEspecialidadesTexto = vistaGestion
				.pedirNuevasEspecialidades(especialidadesActualesTexto);

		boolean ok = artistaDAO.actualizarArtista(idPersona, nuevoApodo,
				nuevasEspecialidadesTexto);
		vistaGestion.mostrarResultadoActualizacionArtista(ok);
	}
}
