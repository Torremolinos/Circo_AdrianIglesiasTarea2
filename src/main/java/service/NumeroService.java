/**
* Clase NumeroService.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package service;

import java.util.LinkedHashSet;
import java.util.List;

import dao.NumeroDAO;
import dao.ParticipaDAO;
import dto.ArtistaPersonaDto;
import entidades.Numero;
import views.MenuNumeroView;

public class NumeroService {

	private final NumeroDAO numeroDAO = new NumeroDAO();
	private final ParticipaDAO participaDAO = new ParticipaDAO();
	private final MenuNumeroView view = new MenuNumeroView();

	/**
	 * Crea un número para un espectáculo
	 */
	public void crearNumeroParaEspectaculo(Long idEspectaculo) {

		view.mostrarTituloCrearNumero();

		String nombre = view.pedirNombreNumero();

		double duracion = leerDuracion();

		int orden = calcularSiguienteOrden(idEspectaculo);

		boolean creado = numeroDAO.crearNumero(nombre, duracion, orden,
				idEspectaculo);

		if (!creado) {
			view.mostrarMensaje("❌ Error creando el número.");
			return;
		}

		view.mostrarMensaje("✅ Número creado con orden = " + orden);

		asignarArtistas(idEspectaculo, orden);
	}

	/**
	 * Lee la duración validando que termine en .0 o .5
	 */
	private double leerDuracion() {
		while (true) {
			String entrada = view.pedirDuracionComoTexto();

			try {
				double dur = Double.parseDouble(entrada);
				double dec = dur * 10 % 10;

				if (dec == 0 || dec == 5) {
					return dur;
				}

			} catch (Exception ignored) {
				// Ignoramos el error de parseo y mostramos mensaje genérico
			}

			view.mostrarDuracionInvalida();
		}
	}

	/**
	 * Calcula automáticamente el siguiente orden del espectáculo
	 */
	private int calcularSiguienteOrden(Long idEspectaculo) {
		LinkedHashSet<Numero> numeros = numeroDAO
				.buscarPorEspectaculo(idEspectaculo);

		if (numeros.isEmpty())
			return 1;

		int max = 0;
		for (Numero n : numeros) {
			if (n.getOrder() > max)
				max = n.getOrder();
		}
		return max + 1;
	}

	/**
	 * Asigna uno o varios artistas a un número, en bucle hasta que el usuario
	 * pare.
	 */
	public void asignarArtistas(Long idEspectaculo, int ordenNumero) {

		// Buscar el número al que asignar artistas
		Numero numero = null;
		for (Numero n : numeroDAO.buscarPorEspectaculo(idEspectaculo)) {
			if (n.getOrder() == ordenNumero) {
				numero = n;
				break;
			}
		}

		if (numero == null) {
			view.mostrarMensaje("❌ No se encontró el número correspondiente.");
			return;
		}

		ArtistaService artistaService = new ArtistaService();

		List<ArtistaPersonaDto> lista = artistaService
				.listarArtistasConPersona();

		if (lista.isEmpty()) {
			view.mostrarMensaje("⚠ No hay artistas disponibles.");
			return;
		}

		boolean seguir = true;

		while (seguir) {
			view.mostrarArtistas(lista);

			int idxSeleccion = view.pedirIndiceArtista();

			if (idxSeleccion == -1) {
				view.mostrarMensaje("❌ Debes introducir un número válido.");
				continue;
			}

			// 0 → salir del bucle
			if (idxSeleccion == 0) {
				seguir = false;
				break;
			}

			if (idxSeleccion < 1 || idxSeleccion > lista.size()) {
				view.mostrarMensaje("❌ Opción fuera de rango.");
				continue;
			}

			Long idArtista = lista.get(idxSeleccion - 1).getIdArtista();
			participaDAO.asignarArtistaANumero(idArtista, numero.getId());
			view.mostrarMensaje("✅ Artista asignado al número.");

			if (!view.preguntarOtroArtista()) {
				seguir = false;
			}
		}

		view.mostrarMensaje("🎭 Asignación de artistas finalizada.");
	}

	/**
	 * Lista los números de un espectáculo
	 */
	public void listarNumeros(Long idEspectaculo) {
		LinkedHashSet<Numero> lista = numeroDAO
				.buscarPorEspectaculo(idEspectaculo);

		if (lista.isEmpty()) {
			view.mostrarMensaje("⚠ No hay números en este espectáculo.");
			return;
		}

		view.mostrarNumeros(lista);
	}

	/**
	 * Modifica los datos de un número y opcionalmente sus artistas
	 */
	public void modificarNumero(Long idEspectaculo) {

		LinkedHashSet<Numero> numeros = numeroDAO
				.buscarPorEspectaculo(idEspectaculo);

		if (numeros.isEmpty()) {
			view.mostrarMensaje(
					"⚠ No hay números registrados en este espectáculo.");
			return;
		}

		view.mostrarMensaje("=== Selecciona un número para modificar ===");

		int index = 1;
		for (Numero n : numeros) {
			view.mostrarMensaje(index + " → Orden " + n.getOrder() + " | "
					+ n.getNombre() + " | " + n.getDuracion() + " min");
			index++;
		}

		int eleccion = view.pedirSeleccionNumeroLista(numeros.size());
		if (eleccion == -1) {
			view.mostrarMensaje("❌ Selección inválida.");
			return;
		}

		Numero seleccionado = numeros.stream().skip(eleccion - 1).findFirst()
				.orElse(null);

		if (seleccionado == null) {
			view.mostrarMensaje("❌ Número no encontrado.");
			return;
		}

		view.mostrarDatosNumeroActual(seleccionado);

		String nuevoNombre = view.pedirNuevoNombre();
		if (!nuevoNombre.isEmpty()) {
			seleccionado.setNombre(nuevoNombre);
		}

		String nuevaDuracion = view.pedirNuevaDuracion();

		if (!nuevaDuracion.isEmpty()) {
			try {
				double dur = Double.parseDouble(nuevaDuracion);
				double dec = dur * 10 % 10;
				if (dec == 0 || dec == 5) {
					seleccionado.setDuracion(dur);
				} else {
					view.mostrarErrorDuracionNoValida();
				}
			} catch (Exception e) {
				view.mostrarErrorDuracionNoValida();
			}
		}

		boolean ok = numeroDAO.actualizarNumero(seleccionado);

		if (!ok) {
			view.mostrarMensaje("❌ Error al actualizar número.");
			return;
		}

		view.mostrarMensaje("✅ Número actualizado.");

		String resp = view
				.pedirRespuesta(
						"¿Quieres modificar los artistas asignados? (S/N): ")
				.toUpperCase();

		if (resp.equals("S")) {
			asignarArtistas(idEspectaculo, seleccionado.getOrder());
		}
	}
}
