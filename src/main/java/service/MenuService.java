/**
* Clase MenuService.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package service;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Scanner;

import dao.ArtistaDAO;
import dao.CoordinacionDAO;
import dao.CredencialesDAO;
import dao.PersonaDAO;
import dto.FichaArtistaDto;
import dto.RegistroPersonaDTO;
import entidades.Sesion;
import views.MenuAdminView;
import views.MenuArtistaView;
import views.MenuCoordinacionView;
import views.MenuEspectaculoView;
import views.MenuInvitadoView;
import views.RegistroUsuarioView;
import entidades.Credenciales;
import entidades.Espectaculo;
import entidades.Perfiles;

public class MenuService {

	private Sesion sesion;

	public MenuService() {
		this.sesion = new Sesion(null, Perfiles.INVITADO);
	}

	/**
	 * Este metodo gestiona el menu invitado, nos muestras su menu con las
	 * funciones accesibles.
	 * 
	 * @return
	 */

	private boolean menuInvitado() {

		EspectaculoService espectaculoService = new EspectaculoService();
		MenuEspectaculoView menuEspectaculoView = new MenuEspectaculoView();
		LinkedHashSet<Espectaculo> espectaculos = espectaculoService
				.mostrarInformeBasico();
		MenuInvitadoView vistaMenuInvitado = new MenuInvitadoView();
		boolean seguirEnPrograma = true;
		boolean seguirEnMenu = true;
		int eleccion;

		while (seguirEnMenu) {
			SesionActiva();
			eleccion = vistaMenuInvitado
					.menuInvitado(sesion.getPerfil().name());

			switch (eleccion) {
			case 1:
				CredencialesService credencialesService = new CredencialesService();
				String usuario = vistaMenuInvitado.pedirUsuario();
				String password = vistaMenuInvitado.pedirPassword();
				Credenciales credencialesUsuario = credencialesService
						.login(usuario, password);

				if (credencialesUsuario != null) {

					vistaMenuInvitado.mostrarMensajeSesionIniciada(
							credencialesUsuario.getPerfil());

					sesion.iniciarSesion(credencialesUsuario.getNombre(),
							credencialesUsuario.getPerfil());

					seguirEnMenu = false;
					seguirEnPrograma = true;

				} else {
					vistaMenuInvitado.mostrarMensajeErrorUsuarioContrasenia();
				}

				break;

			case 2:
				menuEspectaculoView.mostrarEspectaculos(espectaculos);
				break;

			case 3:
				if (vistaMenuInvitado.confirmarSalida()) {
					vistaMenuInvitado.mostrarMensajeDespedida();
					seguirEnMenu = false;
					seguirEnPrograma = false;
				}
				break;

			case -1:
				break;

			default:
				vistaMenuInvitado.mostrarMensajeErrorOpcion();
				break;
			}
		}
		return seguirEnPrograma;
	}

	/**
	 * Este metodo gestiona el menuAdmin, dandonos acceso a las diferentes
	 * acciones o funcionalidades que puede acceder el admin. Tambien hay partes
	 * del menu en construccion a futuras mejoras.
	 * 
	 * @return
	 */
	private boolean menuAdmin() {
		SesionActiva();
		GestionUsuariosService gestionUsuariosService = new GestionUsuariosService();
		EspectaculoService espectaculoService = new EspectaculoService();
		RegistroUsuarioService registroService = new RegistroUsuarioService();
		MenuAdminView menuAdminVista = new MenuAdminView();
		MenuEspectaculoView menuEspectaculoView = new MenuEspectaculoView();
		LinkedHashSet<Espectaculo> espectaculos = espectaculoService
				.mostrarInformeBasico();
		boolean seguirEnPrograma = true;
		boolean seguirEnMenu = true;

		while (seguirEnMenu) {

			int eleccion = menuAdminVista.menuAdmin(sesion.getPerfil().name());

			switch (eleccion) {
			case 1:
				menuEspectaculoView.mostrarEspectaculos(espectaculos);
				break;

			case 2:
				gestionarRegistroUsuario();
				break;

			case 3:
				gestionUsuariosService.gestionarDatosPorUsuario();
				break;

			case 4:
				EspectaculoService.crearEspectaculo(sesion, sesion.getPerfil());
				break;

			case 5:
				EspectaculoService.modificarEspectaculo(sesion.getPerfil());
				break;

			case 6:
				menuAdminVista.mostrarMensajeEnConstruccion();
				break;

			case 7:
				menuAdminVista.mostrarMensajeEnConstruccion();
				break;

			case 8:
				if (menuAdminVista.confirmarLogout()) {
					sesion.cerrarSesion();
					seguirEnMenu = false;
					seguirEnPrograma = true;
				}
				break;

			case 9:
				if (menuAdminVista.confirmarSalirPrograma()) {
					seguirEnMenu = false;
					seguirEnPrograma = false;
				}
				break;

			case -1:
				break;

			default:
				menuAdminVista.mostrarMensajeOpcionInvalida();
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
		SesionActiva();

		EspectaculoService espectaculoService = new EspectaculoService();
		MenuEspectaculoView menuEspectaculoView = new MenuEspectaculoView();
		MenuCoordinacionView menuCoordinacionView = new MenuCoordinacionView();

		boolean seguirEnPrograma = true;
		boolean seguirEnMenu = true;

		while (seguirEnMenu) {

			int eleccion = menuCoordinacionView.mostrarMenuCoordinacion(
					sesion.getPerfil().name(), sesion.getNombre());

			switch (eleccion) {
			case 1:
				LinkedHashSet<Espectaculo> espectaculos = espectaculoService
						.mostrarInformeBasico();
				menuEspectaculoView.mostrarEspectaculos(espectaculos);
				break;
			case 2:
				EspectaculoService.crearEspectaculo(sesion, sesion.getPerfil());
				break;
			case 3:
				EspectaculoService.modificarEspectaculo(sesion.getPerfil());
				break;
			case 4:
				if (menuCoordinacionView.confirmarCerrarSesion()) {
					sesion.cerrarSesion();
					seguirEnMenu = false;
					seguirEnPrograma = true;
				}
				break;
			case 5:
				if (menuCoordinacionView.confirmarSalirPrograma()) {
					seguirEnMenu = false;
					seguirEnPrograma = false;
				}
				break;
			case -1:

				break;
			default:
				menuCoordinacionView.mostrarMensaje(
						"❌ Opción no válida. Intenta de nuevo.");
				break;
			}
		}

		return seguirEnPrograma;
	}

	/**
	 * Este metodo nos permite acceder al menu artista para gestionar sus
	 * diferentes funciones.
	 * 
	 * @return
	 */
	private boolean menuArtista() {
		SesionActiva();

		boolean seguirEnPrograma = true;
		boolean seguirEnMenu = true;

		EspectaculoService espectaculoService = new EspectaculoService();
		MenuEspectaculoView menuEspectaculoView = new MenuEspectaculoView();
		ArtistaService artistaService = new ArtistaService();
		MenuArtistaView menuArtistaView = new MenuArtistaView();

		while (seguirEnMenu) {

			int eleccion = menuArtistaView.mostrarMenuArtista(
					sesion.getPerfil().name(), sesion.getNombre());

			switch (eleccion) {
			case 1:
				LinkedHashSet<Espectaculo> espectaculos = espectaculoService
						.mostrarInformeBasico();
				menuEspectaculoView.mostrarEspectaculos(espectaculos);
				break;

			case 2:
				FichaArtistaDto ficha = artistaService
						.obtenerFichaArtistaPorNombreUsuario(
								sesion.getNombre());

				if (ficha == null) {
					menuArtistaView.mostrarMensaje(
							"❌ No se ha podido recuperar la ficha del artista.");
				} else {
					menuArtistaView.mostrarFicha(ficha);
				}
				break;

			case 3:
				if (menuArtistaView.confirmarCerrarSesion()) {
					sesion.cerrarSesion();
					seguirEnMenu = false;
					seguirEnPrograma = true;
				}
				break;

			case 4:
				if (menuArtistaView.confirmarSalirPrograma()) {
					seguirEnMenu = false;
					seguirEnPrograma = false;
				}
				break;

			case -1:

				break;

			default:
				menuArtistaView.mostrarMensaje(
						"❌ Opción no válida. Intenta de nuevo.");
				break;
			}
		}

		return seguirEnPrograma;
	}

	/**
	 * Iniciamos el programa, con la sesion correspondiente, de ella capturamos
	 * el Perfil y elegimos el menu concorde a ese Perfil. Nuestro perfil pasara
	 * un True, mientras nuestro menu tenga ese valor siempre estará activo.
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
			System.out.println(
					"⚠️ No hay sesión activa. Por favor, inicia sesión primero.");
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

	private void gestionarRegistroUsuario() {

		RegistroUsuarioView vistaRegistro = new RegistroUsuarioView();
		RegistroPersonaDTO dto = new RegistroPersonaDTO();
		RegistroUsuarioService registroService = new RegistroUsuarioService();
		dto.setNombrePersona(vistaRegistro.pedirNombre());
		dto.setEmail(vistaRegistro.pedirEmail());
		dto.setNacionalidad(vistaRegistro.pedirNacionalidad());

		int opcionPerfil = vistaRegistro.pedirOpcionPerfil();

		switch (opcionPerfil) {
		case 1:
			dto.setPerfil(Perfiles.COORDINACION);
			boolean esSenior = vistaRegistro.pedirEsSenior();
			dto.setEsSenior(esSenior);
			if (esSenior) {
				LocalDate fechaSenior = vistaRegistro.pedirFechaSenior();
				dto.setFechaSenior(fechaSenior);
			}
			break;

		case 2:
			dto.setPerfil(Perfiles.ARTISTA);
			dto.setApodo(vistaRegistro.pedirApodo());
			dto.setEspecialidad(vistaRegistro.pedirEspecialidades());
			break;

		default:
			vistaRegistro.mostrarOpcionPerfilInvalida();
			return;
		}

		dto.setNombreUsuario(vistaRegistro.pedirNombreUsuario());
		dto.setPassword(vistaRegistro.pedirPassword());

		boolean ok = registroService.registrarUsuario(dto);
		vistaRegistro.mostrarResultadoRegistro(ok);
	}

}
