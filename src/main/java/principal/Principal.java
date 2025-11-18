/**
* Clase Principal.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package principal;

import controller.InvitadoController;

public class Principal {

	public static void main(String[] args) {

		// 4. Crear el controller de invitado inyectando dependencias
		InvitadoController invitadoController = new InvitadoController();

		// 5. Arrancar el flujo para invitado
		invitadoController.iniciar();
	}

}
