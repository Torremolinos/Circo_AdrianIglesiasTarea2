/**
* Clase Artista.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package entidades;

import java.util.LinkedHashSet;
import java.util.List;

public class Artista {

	private Long idArt;
	private String apodo = null;
	private List<Especialidades> especialidades;
	private LinkedHashSet<Numero> numeros;
	
	public Artista() {
		super();
	}

	public Artista(Long idArt, String apodo,
			List<Especialidades> especialidades,
			LinkedHashSet<Numero> numeros) {
		super();
		this.idArt = idArt;
		this.apodo = apodo;
		this.especialidades = especialidades;
		this.numeros = numeros;
	}

	public Long getIdArt() {
		return idArt;
	}

	public void setIdArt(Long idArt) {
		this.idArt = idArt;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public List<Especialidades> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidades> especialidades) {
		this.especialidades = especialidades;
	}

	public LinkedHashSet<Numero> getNumeros() {
		return numeros;
	}

	public void setNumeros(LinkedHashSet<Numero> numeros) {
		this.numeros = numeros;
	}
	
}
