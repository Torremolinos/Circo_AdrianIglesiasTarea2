/**
 * Clase EspectaculoService.java
 *
 * @author ADRIAN
 * @version 1.0
 */

package service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import dao.CoordinacionDAO;
import dao.EspectaculoDAO;
import entidades.Coordinacion;
import entidades.Espectaculo;
import entidades.Perfiles;
import entidades.Sesion;

public class EspectaculoService {

	private final EspectaculoDAO espectaculoDAO = new EspectaculoDAO();

	public LinkedHashSet<Espectaculo> mostrarInformeBasico() {
		return espectaculoDAO.listaEspectaculos();
	}

	public static void crearEspectaculo(Sesion sesion, Perfiles perfilUsuario) {

		Scanner sc = new Scanner(System.in);
		EspectaculoDAO espectaculoDAO = new EspectaculoDAO();
		CoordinacionDAO coordinacionDAO = new CoordinacionDAO();

		System.out.println("=== Creación de nuevo espectáculo ===");

		String nombreValido = null;
		boolean entradaCorrecta = false;

		while (!entradaCorrecta) {
			System.out.print(
					"Introduce el nombre del espectáculo (máx 25 caracteres): ");
			String nombre = sc.nextLine().trim();

			if (nombre.isEmpty() || nombre.length() > 25) {
				System.out.println(
						"❌ El nombre no puede estar vacío ni superar 25 caracteres.");
				continue;
			}

			if (espectaculoDAO.existeNombre(nombre)) {
				System.out.println(
						"❌ Ya existe un espectáculo con ese nombre. Prueba con otro.");
				continue;
			}

			nombreValido = nombre;
			entradaCorrecta = true;
		}

		List<Coordinacion> coordinadores = coordinacionDAO.listarCoordinacion();

		if (coordinadores == null || coordinadores.isEmpty()) {
			System.out.println(
					"⚠️ No hay personas de coordinación registradas. No se puede crear el espectáculo.");
			return;
		}

		System.out.println("\n=== Selección de Coordinador/a ===");
		for (Coordinacion c : coordinadores) {
			System.out.printf(" - %s (%s)%n", c.getNombre(), c.getEmail());
		}

		Coordinacion usuarioCoord = null;
		boolean coordValido = false;

		while (!coordValido) {
			System.out.print(
					"Escribe el NOMBRE de la persona de coordinación que dirigirá el espectáculo: ");
			String nombreCoord = sc.nextLine().trim();

			final String nombreCoordFinal = nombreCoord;

			usuarioCoord = coordinadores.stream()
					.filter(c -> c.getNombre() != null
							&& c.getNombre().equalsIgnoreCase(nombreCoordFinal))
					.findFirst().orElse(null);

			if (usuarioCoord == null) {
				System.out.println(
						"❌ No se ha encontrado un coordinador con ese nombre. Intenta de nuevo.");
			} else {
				coordValido = true;
			}
		}

		LocalDate fechaIni = null;
		LocalDate fechaFin = null;
		boolean fechasOk = false;

		while (!fechasOk) {
			try {
				System.out.print(
						"Introduce la fecha de INICIO (formato AAAA-MM-DD): ");
				String f1 = sc.nextLine().trim();
				fechaIni = LocalDate.parse(f1);

				System.out.print(
						"Introduce la fecha de FIN (formato AAAA-MM-DD): ");
				String f2 = sc.nextLine().trim();
				fechaFin = LocalDate.parse(f2);

				if (fechaFin.isBefore(fechaIni)) {
					System.out.println(
							"❌ La fecha de fin no puede ser anterior a la de inicio.");
					continue;
				}

				fechasOk = true;

			} catch (DateTimeParseException e) {
				System.out.println(
						"❌ Formato de fecha incorrecto. Ejemplo válido: 2025-03-15");
			}
		}

		Espectaculo nuevo = new Espectaculo();
		nuevo.setNombre(nombreValido);
		nuevo.setFechaini(fechaIni);
		nuevo.setFechafin(fechaFin);
		nuevo.setId_coordinacion(usuarioCoord.getId());

		boolean ok = espectaculoDAO.crearEspectaculo(nuevo);

		if (ok) {
			System.out.println("✅ Espectáculo creado correctamente.");
		} else {
			System.out.println("❌ Error al crear el espectáculo.");
		}
	}

	public static void modificarEspectaculo(Perfiles perfilUsuario) {

		Scanner sc = new Scanner(System.in);
		EspectaculoDAO espectaculoDAO = new EspectaculoDAO();

		LinkedHashSet<Espectaculo> espectaculos = espectaculoDAO
				.listaEspectaculos();

		if (espectaculos == null || espectaculos.isEmpty()) {
			System.out.println(
					"⚠️ No hay espectáculos registrados para modificar.");
			return;
		}

		System.out.println("=== Lista de espectáculos ===");
		for (Espectaculo e : espectaculos) {
			System.out.printf("%d - %s (%s a %s)%n", e.getId(), e.getNombre(),
					e.getFechaini(), e.getFechafin());
		}

		System.out.print("Introduce el ID del espectáculo a modificar: ");
		String entrada = sc.nextLine().trim();
		Long idSeleccionado = null;

		try {
			idSeleccionado = Long.parseLong(entrada);
		} catch (NumberFormatException e) {
			System.out.println("❌ Debes introducir un número de ID válido.");
			return;
		}

		Espectaculo existente = espectaculoDAO.buscarPorId(idSeleccionado);
		if (existente == null) {
			System.out.println("❌ No existe un espectáculo con ese ID.");
			return;
		}

		System.out.println("Has seleccionado: " + existente.getNombre());

		System.out.print("Nuevo nombre (dejar vacío para mantener '"
				+ existente.getNombre() + "'): ");
		String nuevoNombre = sc.nextLine().trim();
		if (!nuevoNombre.isEmpty()) {
			existente.setNombre(nuevoNombre);
		}

		System.out
				.print("Nueva fecha de inicio (AAAA-MM-DD, vacío para mantener "
						+ existente.getFechaini() + "): ");
		String fIni = sc.nextLine().trim();
		if (!fIni.isEmpty()) {
			try {
				existente.setFechaini(LocalDate.parse(fIni));
			} catch (DateTimeParseException e) {
				System.out.println(
						"⚠️ Formato incorrecto, se mantiene la fecha anterior.");
			}
		}

		System.out.print("Nueva fecha de fin (AAAA-MM-DD, vacío para mantener "
				+ existente.getFechafin() + "): ");
		String fFin = sc.nextLine().trim();
		if (!fFin.isEmpty()) {
			try {
				existente.setFechafin(LocalDate.parse(fFin));
			} catch (DateTimeParseException e) {
				System.out.println(
						"⚠️ Formato incorrecto, se mantiene la fecha anterior.");
			}
		}

		boolean ok = espectaculoDAO.actualizarEspectaculo(existente);

		if (ok) {
			System.out.println("✅ Espectáculo modificado correctamente.");
		} else {
			System.out.println("❌ Error al modificar el espectáculo.");
		}
	}
}