package views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import service.PaisService;
import utils.Utilidades;

public class RegistroUsuarioView {

	public String pedirNombre() {
		return Utilidades.leerNombrePersona();
	}

	public int pedirOpcionPerfil() {
		return Utilidades.leerOpcionPerfil();
	}

	public boolean pedirEsSenior() {
		System.out.println("¿Es senior?");
		return Utilidades.leerBoolean();
	}

	public LocalDate pedirFechaSenior() {
		System.out
				.println("Introduce la fecha de senior (solo fechas pasadas):");
		return Utilidades.leerFechaSenior();
	}

	public String pedirApodo() {
		return Utilidades.leerApodo();
	}

	public String pedirNombreUsuario() {
		return Utilidades.leerNombreUsuarioValido();
	}

	public String pedirPassword() {
		return Utilidades.leerPasswordValida();
	}

	public String pedirEmail() {
		return Utilidades.leerEmailValido();
	}

	public void mostrarOpcionPerfilInvalida() {
		System.out.println("❌ Opción de perfil no válida.");
	}

	public void mostrarResultadoRegistro(boolean registrado) {
		if (registrado) {
			System.out.println(" Usuario registrado correctamente.");
		} else {
			System.out.println(" No se ha podido registrar al usuario.");
		}
	}

	public String pedirNacionalidad() {

		PaisService paisService = new PaisService();
		Map<String, String> mapaPaises = paisService.obtenerTodosLosPaises();

		List<String> listaPaises = new ArrayList<>(mapaPaises.values());

		Collections.sort(listaPaises);

		boolean correcto = false;
		int opcionElegida = -1;

		do {
			System.out.println("=== Elige tu país de la lista ===");

			for (int i = 0; i < listaPaises.size(); i++) {
				System.out.println((i + 1) + ". " + listaPaises.get(i));
			}

			System.out.print("Introduce el número de tu país: ");
			int opcion = Utilidades.leerInt();

			if (opcion >= 1 && opcion <= listaPaises.size()) {
				opcionElegida = opcion;
				correcto = true;
			} else {
				System.out.println(
						"❌ Opción no válida. Debes elegir un número entre 1 y "
								+ listaPaises.size() + ".");
			}

		} while (!correcto);

		return listaPaises.get(opcionElegida - 1);
	}

	public String pedirEspecialidades() {
		return Utilidades.pedirEspecialidades();
	}
}
