/**
* Clase MenuInvitado.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package views;

import java.util.Scanner;

import entidades.Sesion;

public class MenuInvitado {

	private final Scanner sc = new Scanner(System.in);

	public int pedirOpcion(Sesion sesion) {
		System.out.println("Bienvenido " + sesion.getPerfil());
		System.out.println("🎪 Te damos la bienvenida a nuestro Circo 🎪 ");
		System.out.println("1.Iniciar sesión");
		System.out.println("2.Ver espectáculos");
		System.out.println("3.Salir");

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
		while (true) {
			System.out.println("¿Seguro que quieres salir? (S/N)");
			String respuesta = sc.nextLine().trim().toLowerCase();
			if (respuesta.equals("s")) {
				return true;
			}

			if (respuesta.equals("n")) {
				return false;
			}
			System.out.println("❌ Opción no válida. Escribe 'S' o 'N'.");
		}
	}

	public void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}
}
