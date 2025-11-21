/**
 * Clase ArtistaService.java
 *
 * @author ADRIAN
 * @version 1.0
 */

package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import dao.ArtistaDAO;
import dao.PersonaDAO;
import dao.ParticipaDAO;
import dao.NumeroDAO;
import dao.EspectaculoDAO;
import dao.CredencialesDAO;
import dto.ArtistaPersonaDto;
import dto.FichaArtistaDto;
import dto.ParticipacionDto;
import entidades.Artista;
import entidades.Persona;
import entidades.Numero;
import entidades.Espectaculo;
import entidades.Credenciales;

public class ArtistaService {

	private final ArtistaDAO artistaDAO = new ArtistaDAO();
	private final PersonaDAO personaDAO = new PersonaDAO();
	private final ParticipaDAO participaDAO = new ParticipaDAO();
	private final NumeroDAO numeroDAO = new NumeroDAO();
	private final EspectaculoDAO espectaculoDAO = new EspectaculoDAO();
	private final CredencialesDAO credencialesDAO = new CredencialesDAO();

	/**
	 * Metodo para listar artistas con datos de persona.
	 */
	public List<ArtistaPersonaDto> listarArtistasConPersona() {

		List<Artista> artistas = artistaDAO.listarArtistas();
		List<ArtistaPersonaDto> lista = new ArrayList<>();

		for (Artista art : artistas) {

			Persona p = personaDAO.buscarPorId(art.getId_persona());

			if (p != null) {
				lista.add(new ArtistaPersonaDto(art.getIdArt(), p.getNombre(),
						p.getNacionalidad(), art.getApodo()));
			}
		}

		return lista;
	}

	/**
	 * Metodo que construye un FichaArtistaDto con: - datos personales (Persona)
	 * - datos profesionales (Artista) - trayectoria (números + espectáculos
	 * donde participa)
	 *
	 * @param idPersona id de la persona logueada (artista)
	 * @return FichaArtistaDto o null si algo falla
	 */
	public FichaArtistaDto obtenerFichaArtista(Long idPersona) {

		Persona persona = personaDAO.buscarPorId(idPersona);
		if (persona == null) {
			return null;
		}

		Artista artista = artistaDAO.buscarPorIdPersona(idPersona);
		if (artista == null) {
			return null;
		}

		List<Long> idNumeros = participaDAO
				.obtenerNumerosDeArtista(artista.getIdArt());

		List<ParticipacionDto> participaciones = new ArrayList<>();

		for (Long idNumero : idNumeros) {

			Numero numero = numeroDAO.buscarPorId(idNumero);
			if (numero == null) {
				continue;
			}

			Espectaculo espectaculo = espectaculoDAO
					.buscarPorId(numero.getId_espectaculo());
			if (espectaculo == null) {
				continue;
			}

			ParticipacionDto dto = new ParticipacionDto(espectaculo.getId(),
					espectaculo.getNombre(), numero.getId(), numero.getNombre(),
					numero.getOrder());

			participaciones.add(dto);
		}

		participaciones.sort(
				Comparator.comparing(ParticipacionDto::getNombreEspectaculo)
						.thenComparing(ParticipacionDto::getOrdenNumero));

		return new FichaArtistaDto(persona.getNombre(), persona.getEmail(),
				persona.getNacionalidad(), artista.getApodo(),
				artista.getEspecialidades(), participaciones);
	}

	/**
	 * 
	 * 
	 */
	public FichaArtistaDto obtenerFichaArtistaPorNombreUsuario(
			String nombreUsuario) {

		Credenciales cred = credencialesDAO
				.buscarPorNombreUsuario(nombreUsuario);

		if (cred == null) {
			return null;
		}

		Long idPersona = cred.getId_persona();
		return obtenerFichaArtista(idPersona);
	}
}
