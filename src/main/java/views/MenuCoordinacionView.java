/**
 * Clase MenuCoordinacionView.java
 *
 * Vista para el perfil Coordinación.
 *
 * @author
 * @version 1.0
 */

package views;

import java.util.Scanner;

public class MenuCoordinacionView {

	private final Scanner sc = new Scanner(System.in);

	/**
	 * Muestra el menú de coordinación y devuelve la opción elegida.
	 */
	public int mostrarMenuCoordinacion(String perfil, String nombreUsuario) {
		System.out.println("\n=== 🎪 MENÚ " + perfil + " 🎪 ===");
		System.out.println("Bienvenido/a, " + nombreUsuario);
		System.out.println("Elige una opción:");
		System.out.println("1. Ver espectáculos");
		System.out.println("2. Crear espectáculo");
		System.out.println("3. Modificar espectáculo");
		System.out.println("4. Cerrar sesión");
		System.out.println("5. Salir del programa");
		System.out.print("Elige una opción: ");

		String entrada = sc.nextLine().trim();
		try {
			return Integer.parseInt(entrada);
		} catch (NumberFormatException e) {
			System.out.println("⚠️ Debes introducir un número.");
			return -1;
		}
	}

	public boolean confirmarCerrarSesion() {
		while (true) {
			System.out.println("¿Seguro que quieres cerrar sesión?");
			System.out.println("Pulsa S para cerrar sesión o N para cancelar:");

			String eleccion = sc.nextLine().trim().toLowerCase();

			switch (eleccion) {
			case "s":
				System.out.println("Cerrando sesión...");
				return true;
			case "n":
				System.out.println(
						"Operación cancelada. Sigues en la sesión actual.");
				return false;
			default:
				System.out.println("❌ Opción no válida. Escribe 'S' o 'N'.");
			}
		}
	}

	public boolean confirmarSalirPrograma() {
		while (true) {
			System.out.println("¿Seguro que quieres salir del programa?");
			System.out.println("Pulsa S para salir o N para cancelar:");

			String eleccion = sc.nextLine().trim().toLowerCase();

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

	public void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}
}
