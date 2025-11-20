package controller;

import dao.CredencialesDAO;
import entidades.Credenciales;
import entidades.Perfiles;
import entidades.Sesion;
import service.CredencialesService;
import utils.Config;
import views.MenuInvitado;

public class InvitadoController {

/*	static Config config = new Config();
	public static String ruta = config.getProperty("credenciales");
	MenuInvitado menuInvitado = new MenuInvitado();
	Sesion sesion = new Sesion(null, Perfiles.INVITADO);
	CredencialesDAO credencialesDAO = new CredencialesDAO();
	CredencialesService credencialesService = new CredencialesService();
	Credenciales credencialesUsuario;

	public void iniciar() {
		boolean salir = false;
		
		int opcion = menuInvitado.menuInvitado(sesion.getPerfil().name());

		while (!salir && sesion.getPerfil() == Perfiles.INVITADO) {

			switch (opcion) {
			case 1:
				String usuario = menuInvitado.pedirUsuario();
				String password = menuInvitado.pedirPassword();
         
				credencialesUsuario = credencialesService.login(usuario, password);

				
				if (null == credencialesUsuario) {
					menuInvitado.mostrarMensajeErrorUsuarioContrasenia();
				} else {
					menuInvitado.mostrarMensajeSesionIniciada(credencialesUsuario.getPerfil());
					// tengo que poner un metodo para cambiar de menu segun perfil
				}
				break;

			case 2:
				//espectaculoService.mostrarEspectaculosBasicos();
				break;

			case 3:
				if (menuInvitado.confirmarSalida()) {
					salir = true;
				}
				break;

			default:
				menuInvitado.mostrarMensajeErrorOpcion();
			}
		}
		
	}*/

}
