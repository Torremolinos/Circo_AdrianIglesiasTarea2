/**
* Clase GestionUsuarioView.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package views;

import java.util.Map;
import java.util.Scanner;

import entidades.Perfiles;
import utils.Utilidades;

public class GestionUsuarioView {
	private final Scanner sc = new Scanner(System.in);

	public String pedirNombreUsuarioParaGestion() {
		System.out.println(
				"=== GESTIÓN DE DATOS DE PERSONA / ARTISTA / COORDINACIÓN ===");
		System.out.print("Introduce el nombre de usuario a gestionar: ");
		return sc.nextLine().trim();
	}

	public void mostrarUsuarioNoEncontrado(String usuario) {
		System.out
				.println("❌ No se han encontrado credenciales para el usuario: "
						+ usuario);
	}

	public void mostrarPersonaNoEncontrada(Long idPersona) {
		System.out.println(
				"❌ No se ha encontrado la persona con id: " + idPersona);
	}

	public void mostrarResultadoActualizacionDatosPersonales(boolean ok) {
		if (ok) {
			System.out
					.println("✅ Datos personales actualizados correctamente.");
		} else {
			System.out.println(
					"❌ No se han podido actualizar los datos personales.");
		}
	}

	public void mostrarSinCoordinacion() {
		System.out.println(
				" Esta persona no tiene datos de Coordinación asociados.");
	}

	public void mostrarSinArtista() {
		System.out
				.println(" Esta persona no tiene datos de Artista asociados.");
	}

	public String pedirNuevoNombre(String nombreActual) {
		return Utilidades.actualizarNombrePersona(nombreActual);
	}

	public String pedirNuevoEmail(String emailActual) {
		return Utilidades.actualizarEmail(emailActual);
	}

	public String pedirNuevaNacionalidad(String nacionalidadActual,
			Map<String, String> paises) {
		return Utilidades.actualizarNacionalidad(nacionalidadActual, paises);
	}

	public Boolean pedirNuevoSenior(Boolean seniorActual) {
		return Utilidades.actualizarEsSenior(seniorActual);
	}

	public java.time.LocalDate pedirNuevaFechaSenior(
			java.time.LocalDate fechaActual) {
		return Utilidades.actualizarFechaSenior(fechaActual);
	}

	public String pedirNuevoApodo(String apodoActual) {
		return Utilidades.actualizarApodo(apodoActual);
	}

	public String pedirNuevasEspecialidades(
			String especialidadesActualesTexto) {
		return Utilidades.actualizarEspecialidades(especialidadesActualesTexto);
	}

}
