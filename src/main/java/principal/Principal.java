/**
* Clase Principal.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package principal;

import java.util.LinkedHashSet;

import controller.InvitadoController;
import dao.EspectaculoDAO;
import entidades.Espectaculo;
import entidades.Perfiles;

public class Principal {

	public static void main(String[] args) {

	InvitadoController invitadoController = new InvitadoController();


		invitadoController.iniciar();
		
		
		
		/*Mostras espectaculo*/
	    EspectaculoDAO dao = new EspectaculoDAO();
		LinkedHashSet<Espectaculo> espectaculos =EspectaculoDAO.listaEspectaculos();

		for (Espectaculo e : espectaculos) {
		    System.out.println(e.getId() + " - " + e.getNombre() + 
		                       " (" + e.getFechaini() + " a " + e.getFechafin() + ")");
		}
	}


}
