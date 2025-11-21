package views;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

import dto.ArtistaPersonaDto;
import entidades.Numero;

public class MenuNumeroView {

	private final Scanner sc = new Scanner(System.in);

	public void mostrarMensaje(String msg) {
		System.out.println(msg);
	}

	public String pedirCadena(String msg) {
		System.out.print(msg);
		return sc.nextLine().trim();
	}

	public int pedirOpcionMenu() {
		System.out.println("\n=== Gestión de números ===");
		System.out.println("1. Crear número");
		System.out.println("2. Modificar número");
		System.out.println("3. Listar números");
		System.out.println("4. Volver");

		String entrada = pedirCadena("Opción: ");
		try {
			return Integer.parseInt(entrada);
		} catch (Exception e) {
			mostrarMensaje("❌ Debes introducir un número válido.");
			return -1;
		}
	}

	public void mostrarTituloCrearNumero() {
		mostrarMensaje("=== Crear número ===");
	}

	public String pedirNombreNumero() {
		return pedirCadena("Nombre del número: ");
	}

	public String pedirDuracionComoTexto() {
		return pedirCadena(
				"Duración (solo termina en .0 o .5 — Ej: 3.0, 4.5, 10.0): ");
	}

	public void mostrarDuracionInvalida() {
		mostrarMensaje(
				"❌ Duración inválida. Debe terminar en .0 o .5 (ejemplos: 3.0, 7.5, 10.0)");
	}

	public void mostrarNumeros(LinkedHashSet<Numero> numeros) {
		mostrarMensaje("=== Números del espectáculo ===");
		for (Numero n : numeros) {
			System.out.printf("Orden %d → %s (duración: %.1f minutos)%n",
					n.getOrder(), n.getNombre(), n.getDuracion());
		}
	}

	public int pedirSeleccionNumeroLista(int max) {
		String entrada = pedirCadena("Introduce el número de la lista: ");
		try {
			int num = Integer.parseInt(entrada);
			if (num < 1 || num > max)
				return -1;
			return num;
		} catch (Exception e) {
			return -1;
		}
	}

	public void mostrarDatosNumeroActual(Numero numero) {
		mostrarMensaje("=== Modificando número ===");
		mostrarMensaje("Nombre actual: " + numero.getNombre());
		mostrarMensaje("Duración actual: " + numero.getDuracion() + " minutos");
	}

	public String pedirNuevoNombre() {
		return pedirCadena("Nuevo nombre (Enter para mantener): ");
	}

	public String pedirNuevaDuracion() {
		return pedirCadena("Nueva duración (.0 o .5 — Enter para mantener): ");
	}

	public void mostrarErrorDuracionNoValida() {
		mostrarMensaje("❌ Duración inválida. Debe acabar en .0 o .5");
	}

	public void mostrarArtistas(List<ArtistaPersonaDto> artistas) {
		mostrarMensaje("=== Selecciona artistas ===");
		for (int i = 0; i < artistas.size(); i++) {
			ArtistaPersonaDto a = artistas.get(i);
			System.out.printf("%d → %s (%s) — Apodo: %s%n", i + 1,
					a.getNombrePersona(), a.getNacionalidadPersona(),
					a.getApodo());
		}
	}

	public int pedirIndiceArtista() {
		String entrada = pedirCadena("Número del artista (0 para terminar): ");
		try {
			return Integer.parseInt(entrada);
		} catch (Exception e) {
			return -1;
		}
	}

	public boolean preguntarOtroArtista() {
		String resp = pedirCadena("¿Quieres añadir otro artista? (S/N): ");
		return resp.equalsIgnoreCase("S");
	}

	public String pedirRespuesta(String msg) {
		return pedirCadena(msg).trim();
	}
}
