/**
* Clase GestionUsuariosService.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package service;

import java.time.LocalDate;

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

		Boolean seniorActual = null;
		Boolean nuevoSenior = null;
		LocalDate nuevaFechaSenior = null;

		String nuevoApodo = null;
		String especialidadesActualesTexto = null;
		String nuevasEspecialidadesTexto = null;

		if (perfil == Perfiles.COORDINACION) {
			Coordinacion coord = coordinacionDAO.buscarPorIdPersona(idPersona);
			if (coord == null) {
				vistaGestion.mostrarSinCoordinacion();
				return;
			}

			seniorActual = coord.getSenior();
			LocalDate fechaActual = coord.getFechasenior();

			nuevoSenior = vistaGestion.pedirNuevoSenior(seniorActual);
			nuevaFechaSenior = fechaActual;

			if (nuevoSenior != null && nuevoSenior) {
				nuevaFechaSenior = vistaGestion
						.pedirNuevaFechaSenior(fechaActual);
			}
		} else if (perfil == Perfiles.ARTISTA) {

			Artista artista = artistaDAO.buscarPorIdPersona(idPersona);
			if (artista == null) {
				vistaGestion.mostrarSinArtista();
				return;
			}

			nuevoApodo = vistaGestion.pedirNuevoApodo(artista.getApodo());

			especialidadesActualesTexto = EspecialidadesAdapter
					.listaAString(artista.getEspecialidades());

			nuevasEspecialidadesTexto = vistaGestion
					.pedirNuevasEspecialidades(especialidadesActualesTexto);
		}

		boolean actualizarPersona = personaDAO.actualizarPersona(idPersona,
				nuevoNombre, nuevoEmail, nuevaNacionalidad);
		if (!actualizarPersona) {
			return;
		}

		if (perfil == Perfiles.COORDINACION) {
			coordinacionDAO.actualizarCoordinacion(idPersona, nuevoSenior,
					nuevaFechaSenior);
		}
		if (perfil == Perfiles.ARTISTA) {
			artistaDAO.actualizarArtista(idPersona, nuevoApodo,
					nuevasEspecialidadesTexto);
		}
		vistaGestion.mostrarResultadoActualizacionDatosPersonales(
				actualizarPersona);
	}

}
