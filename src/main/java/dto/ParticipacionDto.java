/**
 * Clase ParticipacionDTO.java
 *
 * Representa una participación de un artista en un número
 * concreto de un espectáculo.
 *
 * @author ADRIAN IGLESIAS RIÑO
 * @version 1.0
 */
package dto;

public class ParticipacionDto {

	private Long idEspectaculo;
	private String nombreEspectaculo;
	private Long idNumero;
	private String nombreNumero;
	private int ordenNumero;

	public ParticipacionDto(Long idEspectaculo, String nombreEspectaculo,
			Long idNumero, String nombreNumero, int ordenNumero) {
		this.idEspectaculo = idEspectaculo;
		this.nombreEspectaculo = nombreEspectaculo;
		this.idNumero = idNumero;
		this.nombreNumero = nombreNumero;
		this.ordenNumero = ordenNumero;
	}

	public Long getIdEspectaculo() {
		return idEspectaculo;
	}

	public String getNombreEspectaculo() {
		return nombreEspectaculo;
	}

	public Long getIdNumero() {
		return idNumero;
	}

	public String getNombreNumero() {
		return nombreNumero;
	}

	public int getOrdenNumero() {
		return ordenNumero;
	}
}
