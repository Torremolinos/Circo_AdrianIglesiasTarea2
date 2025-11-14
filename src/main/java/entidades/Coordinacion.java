/**
* Clase Coordinacion.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package entidades;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public class Coordinacion extends Persona {

	private Long idCoord;
	private Boolean senior = false;
	private LocalDate fechasenior;
	private LinkedHashSet<Espectaculo> espectaculos;

	public Coordinacion() {
		super();
	}

	public Coordinacion(Long idCoord, Boolean senior, LocalDate fechasenior,
			LinkedHashSet<Espectaculo> espectaculos) {
		super();
		this.idCoord = idCoord;
		this.senior = senior;
		this.fechasenior = fechasenior;
		this.espectaculos = espectaculos;
	}

	public Long getIdCoord() {
		return idCoord;
	}

	public void setIdCoord(Long idCoord) {
		this.idCoord = idCoord;
	}

	public Boolean getSenior() {
		return senior;
	}

	public void setSenior(Boolean senior) {
		this.senior = senior;
	}

	public LocalDate getFechasenior() {
		return fechasenior;
	}

	public void setFechasenior(LocalDate fechasenior) {
		this.fechasenior = fechasenior;
	}

	public LinkedHashSet<Espectaculo> getEspectaculos() {
		return espectaculos;
	}

	public void setEspectaculos(LinkedHashSet<Espectaculo> espectaculos) {
		this.espectaculos = espectaculos;
	}

}
