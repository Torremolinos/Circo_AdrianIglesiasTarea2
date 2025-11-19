/**
* Clase Credenciales.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package entidades;

public class Credenciales {

	private Long id;
	private String nombre;
	private String password;
	private Long id_persona;
	private Perfiles perfil;

	public Credenciales() {
		super();
	}

	public Credenciales(String nombre, String password, Perfiles perfil) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.perfil = perfil;
	}

	public Credenciales(Long id, String nombre, String password, Long id_persona, Perfiles perfil) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.id_persona = id_persona;
		this.perfil = perfil;
	}

	public Long getId_persona() {
		return id_persona;
	}

	public void setId_persona(Long id_persona) {
		this.id_persona = id_persona;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Perfiles getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfiles perfil) {
		this.perfil = perfil;
	}

}
