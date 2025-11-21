/**
 * Clase FichaArtistaDTO.java
 *
 * Contiene toda la información necesaria para mostrar
 * la ficha completa de un artista (CU6).
 *
 * @author ADRIAN
 * @version 1.0
 */
package dto;

import java.util.List;

import entidades.Especialidades;

public class FichaArtistaDto {

	private String nombre;
	private String email;
	private String nacionalidad;

	private String apodo;
	private List<Especialidades> especialidades;

	private List<ParticipacionDto> participaciones;

	public FichaArtistaDto(String nombre, String email, String nacionalidad,
			String apodo, List<Especialidades> especialidades,
			List<ParticipacionDto> participaciones) {
		this.nombre = nombre;
		this.email = email;
		this.nacionalidad = nacionalidad;
		this.apodo = apodo;
		this.especialidades = especialidades;
		this.participaciones = participaciones;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public String getApodo() {
		return apodo;
	}

	public List<Especialidades> getEspecialidades() {
		return especialidades;
	}

	public List<ParticipacionDto> getParticipaciones() {
		return participaciones;
	}
}
