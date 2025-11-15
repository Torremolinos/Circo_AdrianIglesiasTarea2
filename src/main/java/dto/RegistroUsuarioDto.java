package dto;

import java.time.LocalDate;

import entidades.Especialidades;
import entidades.Perfiles;

public class RegistroUsuarioDto {
	private String nombreUsuario;
	private String password;
	private Perfiles perfil;
	private String email;
	private String nombreCompleto;
	private String nacionalidad;
	private boolean esSenior;
	private LocalDate fechaSenior;
	private Especialidades especialidad;
	private String apodo;
}
