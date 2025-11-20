/**
* Clase Principal.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package principal;

import java.util.LinkedHashSet;

import service.MenuService;

public class Principal {

	public static void main(String[] args) {

		MenuService menuService = new MenuService();
		menuService.iniciarAplicacion();

	}
}
