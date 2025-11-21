package views;

import java.util.Scanner;

public class MenuCredenciales {

	public String nombreUsuario() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduce nombre de usuario (solo letras, sin espacios, mínimo 3): ");
		String usuario = sc.nextLine().trim().toLowerCase();
		return usuario;

	}

}
