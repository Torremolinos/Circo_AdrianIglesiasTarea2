package views;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import entidades.Espectaculo;
import entidades.Numero;
import entidades.Persona;

public class MenuEspectaculoView {

	private final Scanner sc = new Scanner(System.in);

	/**
	 * Mostrar un mensaje genérico por consola.
	 */
	public void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}

	/**
	 * Pedir una cadena al usuario mostrando un mensaje previo. Devuelve el
	 * texto introducido, ya con trim().
	 */
	public String pedirCadena(String mensaje) {
		System.out.print(mensaje);
		return sc.nextLine().trim();
	}

	public void mostrarEspectaculos(LinkedHashSet<Espectaculo> espectaculos) {
		System.out.println("📜 Lista de espectáculos disponibles:");
		if (espectaculos == null || espectaculos.isEmpty()) {
			System.out.println("No hay espectáculos disponibles.");
			return;
		}

		for (Espectaculo e : espectaculos) {
			System.out.println(e.getId() + " - " + e.getNombre() + " ("
					+ e.getFechaini() + " a " + e.getFechafin() + ")");
		}
	}

	public Long pedirIdEspectaculo() {
		System.out.print(
				"Introduce el id del espectáculo que quieres ver en detalle: ");
		String entrada = sc.nextLine().trim();
		try {
			return Long.parseLong(entrada);
		} catch (NumberFormatException e) {
			System.out.println("❌ Debes introducir un número válido.");
			return null;
		}
	}

	public void mostrarEspectaculoNoEncontrado(Long id) {
		System.out.println("❌ No se ha encontrado el espectáculo con id " + id);
	}

	public void mostrarPermisoInvitado() {
		System.out.println(
				"❌ Debes iniciar sesión para ver el detalle completo de un espectáculo.");
	}

	public void mostrarInformeCompleto(Espectaculo espectaculo,
			Persona coordinador, boolean esSenior,
			LinkedHashSet<Numero> numeros,
			Map<Long, List<Persona>> artistasPorNumero) {

		System.out.println("\n===== INFORME COMPLETO DEL ESPECTÁCULO =====");
		System.out.println("ID: " + espectaculo.getId());
		System.out.println("Nombre: " + espectaculo.getNombre());
		System.out.println("Periodo: " + espectaculo.getFechaini() + "  -  "
				+ espectaculo.getFechafin());
		System.out.println("------------------------------------------");
		System.out.println("COORDINACIÓN:");
		if (coordinador != null) {
			System.out.println("  Nombre: " + coordinador.getNombre());
			System.out.println("  Email : " + coordinador.getEmail());
			System.out.println("  Senior: " + (esSenior ? "Sí" : "No"));
		} else {
			System.out
					.println("  (No se han encontrado datos de coordinación)");
		}

		System.out.println("------------------------------------------");
		System.out.println("NÚMEROS Y ARTISTAS:");
		if (numeros == null || numeros.isEmpty()) {
			System.out.println(
					"  (Este espectáculo no tiene números registrados)");
		} else {
			for (Numero n : numeros) {
				System.out.println("  [" + n.getOrder() + "] " + n.getNombre()
						+ "  -  duración: " + n.getDuracion() + " min");

				List<Persona> artistas = artistasPorNumero.get(n.getId());
				if (artistas == null || artistas.isEmpty()) {
					System.out.println("     · Sin artistas asignados.");
				} else {
					for (Persona p : artistas) {
						System.out.println("     · " + p.getNombre() + " ("
								+ p.getNacionalidad() + ")");
					}
				}
			}
		}

		System.out.println("==========================================\n");
	}

}
