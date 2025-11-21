/**
* Clase MenuAdmin.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package views;

import java.util.Scanner;

import entidades.Credenciales;
import entidades.Sesion;
import service.CredencialesService;
import service.EspectaculoService;

public class MenuAdminView {

	Sesion sesion;
	private final Scanner sc = new Scanner(System.in);

	/**
	 * Este metodo gestiona el menuAdmin, dandonos acceso a las diferentes
	 * acciones o funcionalidades que puede acceder el admin. Tambien hay partes
	 * del menu en construccion a futuras mejoras.
	 * 
	 * @return
	 */
	public int menuAdmin(String perfiles) {
		System.out.println();
		System.out.println("\n===(͠≖ ͜ʖ͠≖) MENÚ " + perfiles + " (͠≖ ͜ʖ͠≖)===");
		System.out.println("🎪 Bienvenido " + perfiles);
		System.out.println(
				"Tienes que elegir una de las opciones para continuar : ");
		System.out.println("1. Ver espectáculos");
		System.out.println("2. Registrar usuarios");
		System.out.println("3. Modificar perfil y credenciales");
		System.out.println("4. Crear espectáculos");
		System.out.println("5. Modificar espectáculos");
		System.out.println("6. Crear número");
		System.out.println("7. Modificar número");
		System.out.println("8. Ver datos de espectáculo completo");
		System.out.println("9. Ver ficha");
		System.out.println("10. Log out");
		System.out.println("11. Salir");
		System.out.println();
		String entrada = sc.nextLine().trim();

		try {
			return Integer.parseInt(entrada);
		} catch (NumberFormatException e) {
			System.out.println(" ༼ ಠ 益 ಠ༽ Debes introducir un número");
			System.out.println();
			return -1;
		}
	}

	/**
	 * Pregunta si el usuario quiere cerrar sesión (log out).
	 *
	 * @return true si confirma, false si cancela.
	 */

	public boolean confirmarLogout() {
		while (true) {
			System.out.println("¿Seguro que quieres cerrar sesión?");
			System.out.println("Pulsa S para salir, N para cancelar");

			String eleccionSalida = sc.nextLine().toLowerCase().trim();

			switch (eleccionSalida) {
			case "s":
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
	 * Pregunta si el usuario quiere salir del programa.
	 *
	 * @return true si quiere salir del programa, false si cancela.
	 */

	public boolean confirmarSalirPrograma() {
		while (true) {
			System.out.println("¿Seguro que quieres salir del programa?");
			System.out.println("Pulsa S para salir, N para cancelar");

			String eleccionSalida = sc.nextLine().toLowerCase().trim();

			switch (eleccionSalida) {
			case "s":
				return true;
			case "n":
				System.out.println("Operación cancelada. Volviendo al menú...");
				return false;
			default:
				System.out.println("❌ Opción no válida. Escribe 'S' o 'N'.");
			}
		}
	}
}