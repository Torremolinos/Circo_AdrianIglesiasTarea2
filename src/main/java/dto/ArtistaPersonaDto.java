/**
* Clase ArtistaPersonaDTO.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package dto;

public class ArtistaPersonaDto {

	private Long idArtista;
	private String nombrePersona;
	private String nacionalidadPersona;
	private String apodo;

	public ArtistaPersonaDto(Long idArtista, String nombrePersona,
			String nacionalidadPersona, String apodo) {
		this.idArtista = idArtista;
		this.nombrePersona = nombrePersona;
		this.nacionalidadPersona = nacionalidadPersona;
		this.apodo = apodo;
	}

	public Long getIdArtista() {
		return idArtista;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public String getNacionalidadPersona() {
		return nacionalidadPersona;
	}

	public String getApodo() {
		return apodo;
	}
}