/**
* Clase EspectaculoService.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package service;

import java.time.LocalDate;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

import dao.CoordinacionDAO;
import dao.EspectaculoDAO;
import dao.PersonaDAO;
import entidades.Coordinacion;
import entidades.Espectaculo;
import entidades.Perfiles;
import entidades.Persona;
import entidades.Sesion;
import utils.Utilidades;
import views.MenuEspectaculoView;
import views.MenuNumeroView;

public class EspectaculoService {

	/**
	 * Esta función muestra un informe básico para los invitados muestra el id,
	 * nombre, fechainicio y fechafin.
	 */
	public LinkedHashSet<Espectaculo> mostrarInformeBasico() {
		EspectaculoDAO dao = new EspectaculoDAO();
		LinkedHashSet<Espectaculo> espectaculos = dao.listaEspectaculos();
		return espectaculos;
	}

	/**
	 * Creamos un espectaculo, adecuado al perfil que pasa por parametros luego
	 * usamos el metodo guardar, este lo guardara correspondientemente.
	 * 
	 * @param perfilUsuario
	 */
	public static void crearEspectaculo(Sesion sesion, Perfiles perfilUsuario) {

		EspectaculoDAO espectaculoDAO = new EspectaculoDAO();
		CoordinacionDAO coordinacionDAO = new CoordinacionDAO();
		Coordinacion usuarioCoord = null;

		MenuEspectaculoView vista = new MenuEspectaculoView();
		MenuNumeroView menuNumeroView = new MenuNumeroView();

		LinkedHashSet<Espectaculo> existentes = espectaculoDAO
				.listaEspectaculos();

		vista.mostrarMensaje("=== Creación de nuevo espectáculo ===");

		String nombreValido = null;
		Boolean entradaCorrecta = true;
		while (entradaCorrecta) {
			String nombre = vista.pedirCadena(
					"Introduce el nombre del espectáculo (máx 25 caracteres): ");
			if (nombre.isEmpty() || nombre.length() > 25) {
				vista.mostrarMensaje(
						"❌ El nombre no puede estar vacío ni superar 25 caracteres.");
				continue;
			}
			boolean repetido = existentes.stream()
					.anyMatch(e -> e.getNombre().equalsIgnoreCase(nombre));
			if (repetido) {
				vista.mostrarMensaje(
						"❌ Ya existe un espectáculo con ese nombre.");
				continue;
			}
			nombreValido = nombre;
			break;
		}

		vista.mostrarMensaje("Introduce la fecha de inicio:");
		LocalDate inicio = Utilidades.leerFechasLocalDate();

		vista.mostrarMensaje("Introduce la fecha de fin:");
		LocalDate fin = Utilidades.leerFechasLocalDate();

		if (fin.isBefore(inicio)) {
			vista.mostrarMensaje(
					"❌ La fecha de fin debe ser posterior a la de inicio.");
			return;
		}
		if (inicio.plusYears(1).isBefore(fin)) {
			vista.mostrarMensaje("❌ El periodo no puede superar 1 año.");
			return;
		}

		if (perfilUsuario == Perfiles.COORDINACION) {
			String nombreCoordinadorSesion = sesion.getNombre();

			LinkedHashSet<Coordinacion> coordinadores = coordinacionDAO
					.listarCoordinacionMap();
			usuarioCoord = coordinadores.stream()
					.filter(c -> c.getNombre()
							.equalsIgnoreCase(nombreCoordinadorSesion))
					.findFirst().orElse(null);

			if (usuarioCoord == null) {
				vista.mostrarMensaje(
						"❌ No se encontró el coordinador de la sesión en la base de datos.");
				return;
			}
		} else if (perfilUsuario == Perfiles.ADMIN) {
			usuarioCoord = seleccionarCoordinador();
			if (usuarioCoord == null) {
				vista.mostrarMensaje(
						"❌ No se ha seleccionado ningún coordinador.");
				return;
			}
		} else {
			vista.mostrarMensaje(
					"❌ Perfil no autorizado para crear espectáculos.");
			return;
		}

		Espectaculo nuevo = new Espectaculo();
		nuevo.setNombre(nombreValido);
		nuevo.setFechaini(inicio);
		nuevo.setFechafin(fin);
		nuevo.setId_coordinacion(usuarioCoord.getId());
		boolean creado = espectaculoDAO.crearEspectaculo(nuevo);
		if (creado) {
			vista.mostrarMensaje("✅ Espectáculo creado correctamente.");
		} else {
			vista.mostrarMensaje("❌ No se ha podido crear el espectáculo.");
		}

		if (creado) {
			final String nombreBuscado = nombreValido;

			LinkedHashSet<Espectaculo> todos = espectaculoDAO
					.listaEspectaculos();

			Long idEspectaculo = todos.stream()
					.filter(e -> e.getNombre().equalsIgnoreCase(nombreBuscado))
					.map(Espectaculo::getId).findFirst().orElse(null);

			if (idEspectaculo == null) {
				vista.mostrarMensaje(
						"⚠ No se ha podido recuperar el ID del espectáculo recién creado.");
				return;
			}

			NumeroService numeroService = new NumeroService();

			boolean seguir = true;

			while (seguir) {
				int opcion = menuNumeroView.pedirOpcionMenu();

				switch (opcion) {
				case 1:
					numeroService.crearNumeroParaEspectaculo(idEspectaculo);
					break;

				case 2:
					numeroService.modificarNumero(idEspectaculo);
					break;

				case 3:
					numeroService.listarNumeros(idEspectaculo);
					break;

				case 4:
					seguir = false;
					break;

				default:
					vista.mostrarMensaje("❌ Opción inválida.");
					break;
				}
			}
		}
	}

	/**
	 * Metodo para seleccionar un coordinador, leemos el fichero, y comparamos
	 * los datos correspondientes
	 * 
	 * @return
	 */

	public static Coordinacion seleccionarCoordinador() {
		MenuEspectaculoView vista = new MenuEspectaculoView();
		CoordinacionDAO coordinacionDAO = new CoordinacionDAO();
		PersonaDAO personaDAO = new PersonaDAO();

		// Coge todos los coordinadores desde el DAO
		// Usa el método que tengas implementado: listarCoordinacion() o
		// listarCoordinacionMap()
		// Si el que tienes devuelve List<Coordinacion>, cambia esto en
		// consecuencia.
		List<Coordinacion> coordinadores = coordinacionDAO.listarCoordinacion();
		// Si tu método devuelve LinkedHashSet<Coordinacion>, usa:
		// LinkedHashSet<Coordinacion> setCoordinadores =
		// coordinacionDAO.listarCoordinacionMap();
		// List<Coordinacion> coordinadores = new ArrayList<>(setCoordinadores);

		if (coordinadores == null || coordinadores.isEmpty()) {
			vista.mostrarMensaje("⚠ No hay coordinadores registrados.");
			return null;
		}

		vista.mostrarMensaje("=== Selecciona un coordinador ===");
		for (int i = 0; i < coordinadores.size(); i++) {
			Coordinacion c = coordinadores.get(i);

			Persona p = personaDAO.buscarPorId(c.getId_persona());

			String nombre = (p != null && p.getNombre() != null) ? p.getNombre()
					: "Sin nombre";

			String nacionalidad = (p != null && p.getNacionalidad() != null)
					? p.getNacionalidad()
					: "Sin nacionalidad";

			vista.mostrarMensaje(String.format("%d → %s (%s)", (i + 1), nombre,
					nacionalidad));
		}

		int eleccion = -1;

		while (true) {
			String entrada = vista
					.pedirCadena("Introduce el número del coordinador: ");
			try {
				eleccion = Integer.parseInt(entrada.trim()) - 1;
				if (eleccion >= 0 && eleccion < coordinadores.size()) {
					break;
				} else {
					vista.mostrarMensaje(
							"Opcion no valida, intentalo de nuevo.");
				}
			} catch (NumberFormatException e) {
				vista.mostrarMensaje("❌ Debes introducir un número válido.");
			}
		}

		Coordinacion elegido = coordinadores.get(eleccion);
		vista.mostrarMensaje("✅ Has seleccionado a: "
				+ personaDAO.buscarPorId(elegido.getId_persona()).getNombre());
		return elegido;
	}

	/**
	 * Modificamos el espectaculo, indicandolo por id, pasaremos el perfil del
	 * usuario puesto si es perfil admin, podra recolocar un coordinador.
	 * 
	 * @param perfilUsuario
	 */

	public static void modificarEspectaculo(Perfiles perfilUsuario) {

		EspectaculoDAO espectaculoDAO = new EspectaculoDAO();
		MenuEspectaculoView vista = new MenuEspectaculoView();
		MenuNumeroView menuNumeroView = new MenuNumeroView();

		LinkedHashSet<Espectaculo> espectaculos = espectaculoDAO
				.listaEspectaculos();

		if (espectaculos.isEmpty()) {
			vista.mostrarMensaje(
					"⚠ No hay espectáculos registrados para modificar.");
			return;
		}

		vista.mostrarMensaje("=== LISTA DE ESPECTÁCULOS ===");
		for (Espectaculo e : espectaculos) {
			vista.mostrarMensaje("ID: " + e.getId() + " → " + e.getNombre()
					+ " (" + e.getFechaini() + " → " + e.getFechafin() + ")");
		}

		String idTexto = vista.pedirCadena(
				"Introduce el ID del espectáculo que deseas modificar: ");
		long idBuscado;
		try {
			idBuscado = Long.parseLong(idTexto.trim());
		} catch (NumberFormatException e) {
			vista.mostrarMensaje("❌ Debes introducir un número válido.");
			return;
		}

		Espectaculo seleccionado = null;
		for (Espectaculo e : espectaculos) {
			if (e.getId() == idBuscado) {
				seleccionado = e;
				break;
			}
		}

		if (seleccionado == null) {
			vista.mostrarMensaje("❌ No existe ningún espectáculo con ese ID.");
			return;
		}

		Long id_coordinacion = seleccionado.getId_coordinacion();
		CoordinacionDAO coordinacionDAO = new CoordinacionDAO();
		Coordinacion coordinadorSeleccionado = coordinacionDAO
				.buscarPorIdCoordinacion(id_coordinacion);

		vista.mostrarMensaje("\n=== Modificando espectáculo ===");
		vista.mostrarMensaje("Nombre actual: " + seleccionado.getNombre());
		vista.mostrarMensaje("Inicio actual: " + seleccionado.getFechaini());
		vista.mostrarMensaje("Fin actual: " + seleccionado.getFechafin());
		vista.mostrarMensaje("Coordinador actual: "
				+ (coordinadorSeleccionado.getId_persona() != null
						? coordinadorSeleccionado.getNombre()
						: "Sin asignar"));

		String nuevoNombre = "";
		boolean nombreEsCorrecto = false;

		while (!nombreEsCorrecto) {
			nuevoNombre = vista.pedirCadena(
					"Introduce el nuevo nombre (Enter para mantener): ");

			if (nuevoNombre.isEmpty()) {
				nombreEsCorrecto = true;
			} else if (nuevoNombre.length() > 25) {
				vista.mostrarMensaje(
						"❌ El nombre no puede superar 25 caracteres. Inténtalo de nuevo.");
			} else {
				seleccionado.setNombre(nuevoNombre);
				nombreEsCorrecto = true;
			}
		}

		vista.mostrarMensaje("Introduce la nueva fecha de inicio:");
		LocalDate nuevaInicio = Utilidades.leerFechasLocalDate();

		vista.mostrarMensaje("Introduce la nueva fecha de fin:");
		LocalDate nuevaFin = Utilidades.leerFechasLocalDate();

		if (nuevaFin.isBefore(nuevaInicio)) {
			vista.mostrarMensaje(
					"❌ La fecha de fin debe ser posterior a la de inicio.");
			return;
		}

		seleccionado.setFechaini(nuevaInicio);
		seleccionado.setFechafin(nuevaFin);

		String resp = "";
		if (perfilUsuario == Perfiles.ADMIN) {

			boolean respuestaValida = false;

			while (!respuestaValida) {
				resp = vista
						.pedirCadena("¿Deseas cambiar el coordinador? (S/N): ")
						.toUpperCase();

				if (resp.equals("S") || resp.equals("N")) {
					respuestaValida = true;
				} else {
					vista.mostrarMensaje(
							"❌ Entrada no válida. Por favor, escribe 'S' o 'N'.");
				}
			}

			if (resp.equals("S")) {
				Coordinacion nuevoCoord = seleccionarCoordinador();
				if (nuevoCoord != null) {
					seleccionado.setId_coordinacion(nuevoCoord.getId());
				} else {
					vista.mostrarMensaje("⚠ No se cambió el coordinador.");
				}
			} else {
				vista.mostrarMensaje("ℹ No se modificó el coordinador.");
			}
		}

		boolean actualizado = espectaculoDAO
				.actualizarEspectaculo(seleccionado);

		if (actualizado) {
			vista.mostrarMensaje("✅ Espectáculo modificado correctamente.");
		} else {
			vista.mostrarMensaje(
					"❌ No se ha podido actualizar el espectáculo.");
		}

		if (actualizado) {

			Long idEspectaculo = seleccionado.getId();
			NumeroService numeroService = new NumeroService();

			boolean seguir = true;

			while (seguir) {
				int opcion = menuNumeroView.pedirOpcionMenu();

				switch (opcion) {
				case 1:
					numeroService.crearNumeroParaEspectaculo(idEspectaculo);
					break;

				case 2:
					numeroService.modificarNumero(idEspectaculo);
					break;

				case 3:
					numeroService.listarNumeros(idEspectaculo);
					break;

				case 4:
					seguir = false;
					break;

				default:
					vista.mostrarMensaje("❌ Opción inválida.");
					break;
				}
			}
		}
	}

}