/**
* Clase MenuAdmin.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/


package views;

import java.util.Scanner;

import controller.Controlador;
import entidades.Credenciales;
import entidades.Sesion;
import service.CredencialesService;
import service.EspectaculoService;

public class MenuAdmin {

	Sesion sesion;
	
	Controlador controlador;
	/**
	 * Este metodo gestiona el menuAdmin, dandonos acceso a las diferentes
	 * acciones o funcionalidades que puede acceder el admin. Tambien hay partes
	 * del menu en construccion a futuras mejoras.
	 * 
	 * @return
	 */
	private boolean menuAdmin() {
		SesionActiva();
		boolean confirmarSalida;
		String eleccionSalida;
		EspectaculoService espectaculo = new EspectaculoService();
		Credenciales credenciales = new Credenciales();
		boolean comprobador = true;
		Scanner usuario = new Scanner(System.in);
		System.out.println("Bienvenido " + sesion.getPerfil());
		String entrada;
		int eleccion = -1;
		do {
			System.out.println("\n===(͠≖ ͜ʖ͠≖) MENÚ " + sesion.getPerfil()
					+ " (͠≖ ͜ʖ͠≖)===");
			System.out.println("🎪Bienvenido " + sesion.getNombre());
			System.out.println(
					"Tienes que elegir una de las opciones para continuar : ");
			System.out.println("1.Ver espectáculo");
			System.out.println("2.Registrar Usuarios");
			System.out.println("3.Crear espectáculos");
			System.out.println("4.Modificar espectáculos");
			System.out.println("5.Asignar perfil y credenciales");
			System.out
					.println("6.Gestionar datos de Artistas y de Coordinacion");
			System.out.println("7.Ver datos de espectáculo completo");
			System.out.println("8.Ver ficha");
			System.out.println("9.Log out");
			System.out.println("10.Salir");

			entrada = usuario.nextLine().trim();
			try {
				eleccion = Integer.parseInt(entrada);
			} catch (NumberFormatException e) {
				System.out.println(" ༼ ಠ 益 ಠ༽ Debes introducir un número");
				System.out.println();

				continue;
			}

			switch (eleccion) {
			case 1:
				espectaculo.mostrarInformeBasico(sesion.getPerfil());
				break;

			case 2:
				credenciales = CredencialesService.crearNuevaCredencial();
				break;

			case 3:
				System.out.println("Crear espectáculos");
				EspectaculoService.crearEspectaculo(sesion, sesion.getPerfil());
				break;

			case 4:
				System.out.println("Modificar espectáculos");
				EspectaculoService.modificarEspectaculo(sesion.getPerfil());
				break;

			case 5:
				System.out.println("En construcción aún no disponible");
				break;

			case 6:
				System.out.println("En construcción aún no disponible");
				break;

			case 7:
				System.out.println("En construcción aún no disponible");
				break;

			case 8:
				System.out.println("En construcción aún no disponible");
				break;

			case 9:
				confirmarSalida = false;

				while (!confirmarSalida) {
					System.out.println("¿Seguro que quieres salir?");
					System.out.println("Pulsa S para salir, N para cancelar");

					eleccionSalida = usuario.nextLine().toLowerCase().trim();

					switch (eleccionSalida) {
					case "s":
						System.out.println("Saliendo al menú principal...");
						sesion.cerrarSesion();
						this.iniciarPrograma(sesion);
						comprobador = false;
						return true;

					case "n":
						System.out.println(
								"Operación cancelada. Volviendo al menú...");
						confirmarSalida = true;
						break;

					default:
						System.out.println(
								"❌ Opción no válida. Escribe 'S' o 'N'.");
						break;
					}
				}
				break;

			case 10:
				confirmarSalida = false;

				while (!confirmarSalida) {
					System.out.println("¿Seguro que quieres salir?");
					System.out.println("Pulsa S para salir, N para cancelar");

					eleccionSalida = usuario.nextLine().toLowerCase().trim();

					switch (eleccionSalida) {
					case "s":
						System.out.println("Saliendo al menú principal...");
						sesion.cerrarSesion();
						this.iniciarPrograma(sesion);
						comprobador = false;
						return true;

					case "n":
						System.out.println(
								"Operación cancelada. Volviendo al menú...");
						confirmarSalida = true;
						break;

					default:
						System.out.println(
								"❌ Opción no válida. Escribe 'S' o 'N'.");
						break;
					}
				}
				break;

			default:
				System.out.println(
						"❌ La opcion marcada es incorrecta, por favor intentalo de nuevo.");
				break;
			}
		} while (comprobador);
		return true;
	}
}
