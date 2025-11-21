/**
 * Clase MenuArtistaView.java
 *
 * Vista para el perfil Artista.
 *
 * @author
 * @version 1.0
 */

package views;

import java.util.List;
import java.util.Scanner;

import dto.FichaArtistaDto;
import dto.ParticipacionDto;
import entidades.Especialidades;

public class MenuArtistaView {

	private final Scanner sc = new Scanner(System.in);

	/**
	 * Muestra el menú del artista y devuelve la opción elegida.
	 */
	public int mostrarMenuArtista(String perfil, String nombreUsuario) {
		System.out.println("\n=== 🎨 MENÚ " + perfil + " 🎨 ===");
		System.out.println("Bienvenido/a, " + nombreUsuario);
		System.out.println("Selecciona una opción:");
		System.out.println("1. Ver espectáculos disponibles");
		System.out.println("2. Ver mi ficha personal");
		System.out.println("3. Cerrar sesión");
		System.out.println("4. Salir del programa");
		System.out.print("Elige una opción: ");

		String entrada = sc.nextLine().trim();
		try {
			return Integer.parseInt(entrada);
		} catch (NumberFormatException e) {
			System.out.println("⚠️ Debes introducir un número válido.");
			return -1;
		}
	}

	/**
	 * Pregunta al usuario si quiere cerrar sesión.
	 *
	 * @return true si confirma que sí, false si cancela.
	 */
	public boolean confirmarCerrarSesion() {
		while (true) {
			System.out.println("¿Seguro que deseas cerrar sesión?");
			System.out.println("Pulsa S para cerrar sesión o N para cancelar:");
			String eleccion = sc.nextLine().toLowerCase().trim();

			switch (eleccion) {
			case "s":
				System.out.println("Cerrando sesión...");
				return true;
			case "n":
				System.out.println("Operación cancelada. Volviendo al menú...");
				return false;
			default:
				System.out.println("❌ Opción no válida. Escribe 'S' o 'N'.");
			}
		}
	}

	/**
	 * Pregunta al usuario si quiere salir del programa.
	 *
	 * @return true si confirma que sí, false si cancela.
	 */
	public boolean confirmarSalirPrograma() {
		while (true) {
			System.out.println("¿Seguro que deseas salir del programa?");
			System.out.println("Pulsa S para salir o N para cancelar:");
			String eleccion = sc.nextLine().toLowerCase().trim();

			switch (eleccion) {
			case "s":
				System.out.println("👋 Saliendo del programa...");
				return true;
			case "n":
				System.out.println("Operación cancelada. Volviendo al menú...");
				return false;
			default:
				System.out.println("❌ Opción no válida. Escribe 'S' o 'N'.");
			}
		}
	}

	/**
	 * Muestra la ficha completa del artista (CU6).
	 */
	public void mostrarFicha(FichaArtistaDto ficha) {

		System.out.println("\n===== FICHA DEL ARTISTA =====\n");

		System.out.println("Datos personales:");
		System.out.println(" - Nombre:       " + ficha.getNombre());
		System.out.println(" - Email:        " + ficha.getEmail());
		System.out.println(" - Nacionalidad: " + ficha.getNacionalidad());

		System.out.println("\nDatos profesionales:");
		String apodo = ficha.getApodo();
		System.out.println(" - Apodo:        "
				+ (apodo == null || apodo.isBlank() ? "—" : apodo));

		List<Especialidades> especialidades = ficha.getEspecialidades();
		if (especialidades == null || especialidades.isEmpty()) {
			System.out.println(" - Especialidades: —");
		} else {
			System.out.println(" - Especialidades:");
			for (Especialidades esp : especialidades) {
				System.out.println("   · " + esp.name());
			}
		}

		System.out.println("\nTrayectoria en el circo:");
		List<ParticipacionDto> participaciones = ficha.getParticipaciones();

		if (participaciones == null || participaciones.isEmpty()) {
			System.out.println("   No tiene participaciones registradas.");
		} else {
			Long espectaculoActual = null;

			for (ParticipacionDto p : participaciones) {

				if (espectaculoActual == null
						|| !espectaculoActual.equals(p.getIdEspectaculo())) {

					espectaculoActual = p.getIdEspectaculo();

					System.out.println("\nEspectáculo " + p.getIdEspectaculo()
							+ " - " + p.getNombreEspectaculo());
					System.out.println("-----------------------------");
				}

				System.out.printf("  (%d) Número %d - %s%n", p.getOrdenNumero(),
						p.getIdNumero(), p.getNombreNumero());
			}
		}

		System.out.println("\n==============================\n");
	}

	public void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}
}
