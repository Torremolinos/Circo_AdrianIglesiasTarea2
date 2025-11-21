/**
* Clase MenuService.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package service;

import java.util.LinkedHashSet;
import java.util.Scanner;

import entidades.Sesion;
import views.MenuAdmin;
import views.MenuEspectaculo;
import views.MenuInvitado;
import entidades.Credenciales;
import entidades.Espectaculo;
import entidades.Perfiles;

public class MenuService {

	private Sesion sesion;

	public MenuService() {
		this.sesion = new Sesion(null, Perfiles.INVITADO);
	}

	/**
	 * Este metodo gestiona el menu invitado, nos muestras su menu con las funciones
	 * accesibles.
	 * 
	 * @return
	 */

	private boolean menuInvitado() {

		EspectaculoService espectaculoService = new EspectaculoService();
		MenuInvitado vistaMenuInvitado = new MenuInvitado();
		boolean seguirEnPrograma = true;
		boolean seguirEnMenu = true;
		int eleccion;

		while (seguirEnMenu) {
			SesionActiva();
			eleccion = vistaMenuInvitado.menuInvitado(sesion.getPerfil().name());

			switch (eleccion) {
			case 1:
				CredencialesService credencialesService = new CredencialesService();
				String usuario = vistaMenuInvitado.pedirUsuario();
				String password = vistaMenuInvitado.pedirPassword();
				Credenciales credencialesUsuario = credencialesService.login(usuario, password);

				if (credencialesUsuario != null) {
					System.out.println(" Inicio correcto ");

					sesion.iniciarSesion(credencialesUsuario.getNombre(), credencialesUsuario.getPerfil());

					seguirEnMenu = false;
					seguirEnPrograma = true;

				} else {
					System.out.println("Usuario o contraseña incorrectos. Intentalo de nuevo.");
				}

				break;

			case 2:
				espectaculoService.mostrarInformeBasico();
				break;

			case 3:
				System.out.println("Gracias por tu visita, ¡cuidate!");
				seguirEnMenu = false;
				seguirEnPrograma = false;
				break;

			default:
				System.out.println("❌ La opción marcada es incorrecta, por favor inténtalo de nuevo.");
				break;
			}
		}
        return seguirEnPrograma;
	}

	/**
	 * Este metodo gestiona el menuAdmin, dandonos acceso a las diferentes acciones
	 * o funcionalidades que puede acceder el admin. Tambien hay partes del menu en
	 * construccion a futuras mejoras.
	 * 
	 * @return
	 */
	private boolean menuAdmin() {
	    SesionActiva();

	    EspectaculoService espectaculoService = new EspectaculoService();
	    RegistroUsuarioService registroService = new RegistroUsuarioService();
	    MenuAdmin menuAdminVista = new MenuAdmin();
	    MenuEspectaculo menuEspectaculo = new MenuEspectaculo();
	    LinkedHashSet<Espectaculo> espectaculos = espectaculoService.mostrarInformeBasico();
	    boolean seguirEnPrograma = true;
	    boolean seguirEnMenu = true;

	    while (seguirEnMenu) {

	        int eleccion = menuAdminVista.menuAdmin(sesion.getPerfil().name());

	        switch (eleccion) {
	        case 1:
	            menuEspectaculo.mostrarEspectaculos(espectaculos);
	            break;

	        case 2:
	          //  registroService.registrarUsuario();
	            break;

	        case 3:
	            EspectaculoService.crearEspectaculo(sesion, sesion.getPerfil());
	            break;

	        case 4:
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
	            // LOG OUT
	            if (menuAdminVista.confirmarLogout()) {
	                System.out.println("Saliendo al menú principal...");
	                sesion.cerrarSesion(); 
	                seguirEnMenu = false;
	                seguirEnPrograma = true; 
	            }
	            break;

	        case 10:
	            // SALIR DEL PROGRAMA
	            if (menuAdminVista.confirmarSalirPrograma()) {
	                System.out.println("👋 Saliendo del programa...");
	                seguirEnMenu = false;
	                seguirEnPrograma = false; 
	            }
	            break;

	        case -1:
	            break;

	        default:
	            System.out.println(
	                    "❌ La opción marcada es incorrecta, por favor intentalo de nuevo.");
	            break;
	        }
	    }

	    return seguirEnPrograma;
	}


	/**
	 * Este metodo nos permite acceder a las funcionalidades del menu y poder
	 * elegirlas correspondientemente.
	 * 
	 * @return
	 */

	private boolean menuCoordinacion() {
		boolean confirmarSalida = false;
		SesionActiva();
		EspectaculoService espectaculo = new EspectaculoService();
		boolean comprobador = true;
		Scanner sc = new Scanner(System.in);
		String entrada;
		String eleccionSalida;
		int eleccion = -1;

		do {
			System.out.println("\n=== 🎪 MENÚ " + sesion.getPerfil() + " 🎪 ===");
			System.out.println("Bienvenido/a, " + sesion.getNombre());
			System.out.println("Elige una opción:");
			System.out.println("1. Ver espectáculos");
			System.out.println("2. Crear espectáculo");
			System.out.println("3. Modificar espectáculo");
			System.out.println("4. Cerrar sesión");
			System.out.println("5. Salir del programa");

			entrada = sc.nextLine().trim();
			try {
				eleccion = Integer.parseInt(entrada);
			} catch (NumberFormatException e) {
				System.out.println("⚠️ Debes introducir un número.");
				continue;
			}

			switch (eleccion) {
			case 1:
			/*	espectaculo.mostrarInformeBasico(sesion.getPerfil());*/
				break;
			case 2:
				EspectaculoService.crearEspectaculo(sesion, sesion.getPerfil());
				break;
			case 3:
				EspectaculoService.modificarEspectaculo(sesion.getPerfil());
				break;
			case 4:
				confirmarSalida = false;
				while (!confirmarSalida) {
					System.out.println("¿Seguro que quieres cerrar sesión?");
					System.out.println("Pulsa S para cerrar sesión o N para cancelar:");

					eleccionSalida = sc.nextLine().trim().toLowerCase();

					switch (eleccionSalida) {
					case "s":
						System.out.println("Cerrando sesión...");
				/*		sesion.cerrarSesion();
						this.iniciarPrograma(sesion);*/
						comprobador = false;
						return true;
					case "n":
						System.out.println("Operación cancelada. Sigues en la sesión actual.");
						confirmarSalida = true;
						break;
					default:
						System.out.println("❌ Opción no válida. Escribe 'S' o 'N'.");
						break;
					}
				}
				break;
			case 5:
				confirmarSalida = false;
				while (!confirmarSalida) {
					System.out.println("¿Seguro que quieres salir del programa?");
					System.out.println("Pulsa S para salir o N para cancelar:");

					eleccionSalida = sc.nextLine().trim().toLowerCase();

					switch (eleccionSalida) {
					case "s":
						System.out.println("👋 Saliendo del programa...");
						comprobador = false;
						return false;
					case "n":
						System.out.println("Operación cancelada. Volviendo al menú...");
						confirmarSalida = true;
						break;
					default:
						System.out.println("❌ Opción no válida. Escribe 'S' o 'N'.");
						break;
					}
				}
				break;
			default:
				System.out.println("❌ Opción no válida. Intenta de nuevo.");
			}
		} while (comprobador);
		return true;
	}

	/**
	 * Este metodo nos permite acceder al menu artista para gestionar sus diferentes
	 * funciones.
	 * 
	 * @return
	 */
	private boolean menuArtista() {
		SesionActiva();
		String eleccionSalida;
		boolean confirmarSalida;
		boolean comprobador = true;
		Scanner sc = new Scanner(System.in);
		String entrada;
		int eleccion = -1;

		do {
			System.out.println("\n=== 🎨 MENÚ " + sesion.getPerfil() + " 🎨 ===");
			System.out.println("Bienvenido/a, " + sesion.getNombre());
			System.out.println("Selecciona una opción:");
			System.out.println("1. Ver espectáculos disponibles");
			System.out.println("2. Ver mi ficha personal");
			System.out.println("3. Cerrar sesión");
			System.out.println("4. Salir del programa");

			entrada = sc.nextLine().trim();
			try {
				eleccion = Integer.parseInt(entrada);
			} catch (NumberFormatException e) {
				System.out.println("⚠️ Debes introducir un número válido.");
				continue;
			}

			switch (eleccion) {
			case 1:
				EspectaculoService espectaculo = new EspectaculoService();
			/*	espectaculo.mostrarInformeBasico(sesion.getPerfil());*/
				break;
			case 2:
				System.out.println("=== Ver mi ficha personal ===");
				System.out.println("(En construcción)");
				break;
			case 3:
				confirmarSalida = false;
				while (!confirmarSalida) {
					System.out.println("¿Seguro que deseas cerrar sesión?");
					System.out.println("Pulsa S para cerrar sesión o N para cancelar:");
					eleccionSalida = sc.nextLine().toLowerCase().trim();

					switch (eleccionSalida) {
					case "s":
						System.out.println("Cerrando sesión...");
				/*		sesion.cerrarSesion();
						this.iniciarPrograma(sesion);*/
						comprobador = false;
						confirmarSalida = true;
						break;

					case "n":
						System.out.println("Operación cancelada. Volviendo al menú...");
						confirmarSalida = true;
						break;

					default:
						System.out.println("❌ Opción no válida. Escribe 'S' o 'N'.");
						break;
					}
				}
				break;

			case 4:
				confirmarSalida = false;
				while (!confirmarSalida) {
					System.out.println("¿Seguro que deseas salir del programa?");
					System.out.println("Pulsa S para salir o N para cancelar:");
					eleccionSalida = sc.nextLine().toLowerCase().trim();

					switch (eleccionSalida) {
					case "s":
						System.out.println("👋 Saliendo del programa...");
						comprobador = false;
						return false;

					case "n":
						System.out.println("Operación cancelada. Volviendo al menú...");
						confirmarSalida = true;
						break;

					default:
						System.out.println("❌ Opción no válida. Escribe 'S' o 'N'.");
						break;
					}
				}
				break;
			default:
				System.out.println("❌ Opción no válida. Intenta de nuevo.");
				break;
			}
		} while (comprobador);
		return true;
	}

	/**
	 * Iniciamos el programa, con la sesion correspondiente, de ella capturamos el
	 * Perfil y elegimos el menu concorde a ese Perfil. Nuestro perfil pasara un
	 * True, mientras nuestro menu tenga ese valor siempre estará activo.
	 * 
	 * @param perfil
	 * @return
	 */
	public boolean iniciarPrograma() {

		if (null == sesion.getPerfil()) {
			return false;
		}

		switch (sesion.getPerfil()) {
		case INVITADO:
			return menuInvitado();
		case ADMIN:
			return menuAdmin();
		case ARTISTA:
			return menuArtista();
		case COORDINACION:
			return menuCoordinacion();
		default:
			System.out.println("Perfil no existente.");
			return false;
		}
	}

	/**
	 * Nos muestra el perfil de la sesion que tenemos actualmente activa
	 * 
	 * @return
	 * @boolean
	 */
	public boolean SesionActiva() {
		System.out.println("Sesión activa: " + sesion.getPerfil());
		/* para Utils? */
		if (this.sesion == null || this.sesion.getPerfil() == null) {
			System.out.println("⚠️ No hay sesión activa. Por favor, inicia sesión primero.");
			return false;
		}
		return true;
	}

	public void iniciarAplicacion() {
		boolean aplicacionIniciada = true;
		while (aplicacionIniciada) {
			aplicacionIniciada = iniciarPrograma();
		}
		System.out.println("Programa finalizado. 👋");
	}
}
