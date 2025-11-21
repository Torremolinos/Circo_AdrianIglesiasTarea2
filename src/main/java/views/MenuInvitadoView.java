/**
* Clase MenuInvitado.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package views;

import java.util.Scanner;

import entidades.Perfiles;

public class MenuInvitadoView {

	private final Scanner sc = new Scanner(System.in);

	public int menuInvitado(String perfiles) {
		System.out.println("Bienvenido " + perfiles);
		System.out.println("🎪 Te damos la bienvenida a nuestro Circo 🎪 ");
		System.out.println("1. Iniciar sesión");
		System.out.println("2. Ver espectáculos");
		System.out.println("3. Salir");

		String opcion = sc.nextLine().trim();
		try {
			return Integer.parseInt(opcion);
		} catch (Exception e) {
			System.out.println("⚠️ Debes introducir un número, entre 1 a 3");
			return -1;
		}
	}

	public String pedirUsuario() {
		System.out.println("Introduce tu nombre de usuario, por favor: ");
		return sc.nextLine().trim();
	}

	public String pedirPassword() {
		System.out.println("Introduce tu contraseña por favor: ");
		return sc.nextLine().trim();
	}

	public boolean confirmarSalida() {
		String respuestaUsuario;
		boolean esValido;

		do {
			System.out.println("¿Seguro que quieres salir? (S/N)");
			respuestaUsuario = sc.nextLine().trim().toLowerCase();
			esValido = respuestaUsuario.equals("s")
					|| respuestaUsuario.equals("n");

			if (!esValido) {
				System.out.println("❌ Opción no válida. Escribe 'S' o 'N'.");
			}

		} while (!esValido);

		return respuestaUsuario.equals("s");
	}

	public void mostrarMensajeDespedida() {
		System.out.println("Gracias por tu visita, ¡cuídate!");
	}

	public void mostrarMensajeErrorUsuarioContrasenia() {
		System.out.println(
				"❌ Usuario o contraseña incorrectos. Inténtalo de nuevo.");
	}

	public void mostrarMensajeSesionIniciada(Perfiles perfiles) {
		System.out.println("✅ Sesión iniciada como " + perfiles);
	}

	public void mostrarMensajeErrorOpcion() {
		System.out.println("⚠️ Opción no válida. Elige de 1 a 3.");
	}
}
