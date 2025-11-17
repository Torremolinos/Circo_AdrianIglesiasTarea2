/**
* Clase Controlador.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package controller;

import entidades.Sesion;
import service.CredencialesService;
import service.EspectaculoService;
import service.MenuService;
import service.PaisService;

public class Controlador {

	private Sesion sesion;

	private MenuService menuService;

	private CredencialesService credencialesService;

	private EspectaculoService espectaculoService;

	private PaisService paisService;

	public Controlador() {

		this.sesion = new Sesion();
		this.menuService = new MenuService(this.sesion);
		this.credencialesService = new CredencialesService();
		this.espectaculoService = new EspectaculoService();
		this.paisService = new PaisService();
	}

	public void iniciar() {
		menuService.iniciarPrograma(this.sesion);
	}
}
