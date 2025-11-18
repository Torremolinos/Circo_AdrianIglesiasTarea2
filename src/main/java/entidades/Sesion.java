/*
* Clase Sesion.java
*
* @author Adrian Iglesias Riño
* @version 1.0
*/

package entidades;

public class Sesion {

	private String nombre;
	private Perfiles perfil;

	public Sesion() {
		super();
	}

	public Sesion(String nombre, Perfiles perfil) {
		super();
		this.nombre = nombre;
		this.perfil = perfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Perfiles getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfiles perfil) {
		this.perfil = perfil;
	}

	public void iniciarSesion(String usuario, Perfiles perfil) {
		this.nombre = usuario;
		this.perfil = perfil;
	}
	
	public void sesionInvitado() {
		this.nombre = null;
		this.perfil = Perfiles.INVITADO;
	}

	public void cerrarSesion() {
		this.nombre = null;
		this.perfil = Perfiles.INVITADO;
	}
	
	public boolean isAutenticado() {
		return perfil != Perfiles.INVITADO;
	}

	
}
