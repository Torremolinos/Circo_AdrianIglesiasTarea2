package controller;

import dao.CredencialesDAO;
import entidades.Credenciales;
import entidades.Perfiles;
import entidades.Sesion;
import service.CredencialesService;
import utils.Config;
import views.MenuInvitado;

public class InvitadoController {

	static Config config = new Config();
	public static String ruta = config.getProperty("credenciales");
	MenuInvitado menuInvitado = new MenuInvitado();
	Sesion sesion = new Sesion(null, Perfiles.INVITADO);
	CredencialesDAO credencialesDAO = new CredencialesDAO();


	public void iniciar() {
		boolean salir = false;
		
		int opcion = menuInvitado.menuInvitado(sesion.getPerfil().name());

		while (!salir && sesion.getPerfil() == Perfiles.INVITADO) {

			switch (opcion) {
			case 1:
				String usuario = menuInvitado.pedirUsuario();
				String password = menuInvitado.pedirPassword();
                //TODO
				/*boolean login = CredencialesService.login(usuario, password);

				
				if (!login) {
					menuInvitado.mostrarMensajeErrorUsuarioContrasenia();
				} else {
					menuInvitado.mostrarMensajeSesionIniciada(sesion.getPerfil);
					// tengo que poner un metodo para cmaibar de menu segun perfil
				}
				break;
*/
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
		
	}
	
	public void logout() {}
	
	
	
	/*public static Credenciales login(String usuarioBuscado,
			String passwordBuscada) {
		
		String adminUser = config.getProperty("useradmin");
		String adminPass = config.getProperty("passadmin");

		if (usuarioBuscado.equals(adminUser)
				&& passwordBuscada.equals(adminPass)) {
			/*Aqui tenemos que crear un metodo que busque con credencialesDao
			 * y contraste con un admin*/
		//	return new Credenciales(0L, adminUser, adminPass, Perfiles.ADMIN);
		//}

		/*Aqui otro metodo, el loing que busque en sql el usuaro y la password y nos devuelva al ususario enconcreto
		 * creando un nuevo Credneciales (lo tenog en CredencialesService hay que mirar como meter el Dao y avanzar.*/
	//	return buscarUsuarioYPassword(usuarioBuscado, passwordBuscada);
//	}
	
}
