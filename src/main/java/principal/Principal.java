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
	
		InvitadoController invitadoController = new InvitadoController();
		invitadoController.iniciar();
	}

}
