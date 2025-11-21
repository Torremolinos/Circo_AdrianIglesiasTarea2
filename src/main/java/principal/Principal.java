/**
* Clase Principal.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package principal;

import java.util.LinkedHashSet;

import dao.CredencialesDAO;
import dao.PersonaDAO;
import entidades.Credenciales;
import entidades.Perfiles;
import service.MenuService;

public class Principal {

	public static void main(String[] args) {

		MenuService menuService = new MenuService();
		menuService.iniciarAplicacion();
		
		
		
		
		
		/*CredencialesDAO credenecialesdao = new CredencialesDAO();
		
		String nombre = "adri";
		String password = "1234";
		
		Credenciales credencialesUsuario =credenecialesdao.buscarUsuario(nombre, password);
		
		System.out.println("Las credenciales son : " + credencialesUsuario.getNombre() + credencialesUsuario.getPassword());
		
		
		String nombrePrueba = "PEPE".toLowerCase();
		String pass = "1234";
		Perfiles perfil = Perfiles.ARTISTA;
		Long idpersonaasingada = 12l;
		credenecialesdao.registrarNuevaCredencial(nombrePrueba,pass,idpersonaasingada,perfil);*/
		
		
		/*  PersonaDAO personaDAO = new PersonaDAO();

	        String email = "adri" + "@correo.com";
	        String nombre = "Adrian";
	        String apellido = "Igleisas";
	        String nombrecompleto = nombre + " " + apellido;
	        String nacionalidad = "España";

	        boolean insertOk = personaDAO.registrarPersona(email, nombrecompleto, nacionalidad);
	        System.out.println("¿Se ha insertado la persona? " + insertOk);

	        Long ultimoId = personaDAO.obtenerUltimoIdPersona();
	        System.out.println("Último id_persona en la tabla: " + ultimoId);*/
	    

	}
}
