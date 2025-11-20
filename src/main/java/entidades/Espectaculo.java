/**
* Clase Espectaculo.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package entidades;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public class Espectaculo {

	private Long id;
	private String nombre;
	private LocalDate fechaini;
	private LocalDate fechafin;
	private LinkedHashSet<Numero> numeros;
	private Long id_coordinacion;

	public Espectaculo() {
		super();
	}

	public Espectaculo(Long id, String nombre, LocalDate fechaini,
			LocalDate fechafin, LinkedHashSet<Numero> numeros,
			Long id_coordinacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaini = fechaini;
		this.fechafin = fechafin;
		this.numeros = numeros;
		this.id_coordinacion = id_coordinacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaini() {
		return fechaini;
	}

	public void setFechaini(LocalDate fechaini) {
		this.fechaini = fechaini;
	}

	public LocalDate getFechafin() {
		return fechafin;
	}

	public void setFechafin(LocalDate fechafin) {
		this.fechafin = fechafin;
	}

	public LinkedHashSet<Numero> getNumeros() {
		return numeros;
	}

	public void setNumeros(LinkedHashSet<Numero> numeros) {
		this.numeros = numeros;
	}

	public Long getId_coordinacion() {
		return id_coordinacion;
	}

	public void setId_coordinacion(Long id_coordinacion) {
		this.id_coordinacion = id_coordinacion;
	}

}
