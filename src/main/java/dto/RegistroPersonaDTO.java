/**
* Clase RegistroPersonaDTO.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package dto;

import java.time.LocalDate;

import entidades.Perfiles;

public class RegistroPersonaDTO {

	private String nombrePersona;
	private String email;
	private String nacionalidad;

	private Perfiles perfil;

	private Boolean esSenior;
	private LocalDate fechaSenior;
	private String especialidad;
	private String apodo;
	private String nombreUsuario;
	private String password;

	public RegistroPersonaDTO() {
		super();
	}

	public RegistroPersonaDTO(String nombrePersona, String email,
			String nacionalidad, Perfiles perfil, Boolean esSenior,
			LocalDate fechaSenior, String apodo, String nombreUsuario,
			String password) {
		super();
		this.nombrePersona = nombrePersona;
		this.email = email;
		this.nacionalidad = nacionalidad;
		this.perfil = perfil;
		this.esSenior = esSenior;
		this.fechaSenior = fechaSenior;
		this.apodo = apodo;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Perfiles getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfiles perfil) {
		this.perfil = perfil;
	}

	public Boolean getEsSenior() {
		return esSenior;
	}

	public void setEsSenior(Boolean esSenior) {
		this.esSenior = esSenior;
	}

	public LocalDate getFechaSenior() {
		return fechaSenior;
	}

	public void setFechaSenior(LocalDate fechaSenior) {
		this.fechaSenior = fechaSenior;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
}
