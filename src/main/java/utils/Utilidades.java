/*
* Clase Principal.java
*
* @author Luis de Blas
* @version 1.0
*/

package utils;

import java.sql.Date;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Utilidades {

	/**
	 * Función que pide al usuario que introduzca 's' o 'S' para Sí o 'n' o 'N'
	 * para No y devuelve true o false en cada caso. Si el usuario no introduce
	 * ni 's' ni 'S' ni 'n' ni 'N' entonces avisa al usuario y le vuelve a pedir
	 * a que lo introduzca de nuevo.
	 *
	 * @return true si el usuario introduce 's' o 'S'; false si el usuario
	 *         introduce 'n' o 'N' Ahora si, antes si se
	 */
	public static boolean leerBoolean() {
		Scanner in = new Scanner(System.in, "ISO-8859-1");
		char resp;
		do {
			System.out.println("Pulse S para Sí o N para No:");
			String entrada = in.nextLine().trim();

			if (entrada.isEmpty()) {
				System.out.println("Valor vacío. Intente de nuevo.");
				continue;
			}

			resp = entrada.charAt(0);

			if (resp == 's' || resp == 'S') {
				return true;
			} else if (resp == 'n' || resp == 'N') {
				return false;
			} else {
				System.out.println(
						"Valor introducido incorrecto. Introduzca S o N.");
			}
		} while (true);

	}

	/**
	 * Función que pide al usuario que introduce un valor para una fecha a
	 * partir de 3 enteros para el día, mes y año respectivamente. Si los
	 * valores introducidos no producen una fecha correcta, avisa al usuario y
	 * le pide que los introduzca de nuevo. Si no lo consigue, devolverá null
	 *
	 * @return una fecha de la clase java.sql.Date o null si hay error
	 */
	public static java.sql.Date leerFecha() {
		Date ret = null;
		int dia, mes, anio;
		boolean correcto = false;
		Scanner in;
		do {
			System.out.println("Introduzca un valor para el día (1...31)");
			in = new Scanner(System.in, "ISO-8859-1");
			dia = in.nextInt();
			System.out.println("Introduzca un valor para el mes (1...12)");
			in = new Scanner(System.in, "ISO-8859-1");
			mes = in.nextInt();
			System.out.println("Introduzca un valor para el año");
			in = new Scanner(System.in, "ISO-8859-1");
			anio = in.nextInt();

			try {
				ret = Date.valueOf(LocalDate.of(anio, mes, dia));
				correcto = true;
			} catch (Exception e) {
				System.out.println("Fecha introducida incorrecta.");
				correcto = false;
			}
		} while (!correcto);
		return ret;
	}

	/**
	 * Función que pide al usuario que introduzca un valor entero por la entrada
	 * estándar. Si el formato introducido no es correcto, avisa al usuario y le
	 * vuelve a pedir que lo introduzca de nuevo.
	 *
	 * @return el valor entero introducido por el usuario
	 */

	public static int leerInt() {
		int ret = 0;
		boolean correcto = false;
		Scanner in;
		do {
			System.out.println("Introduzca un número entero:");
			in = new Scanner(System.in, "ISO-8859-1");
			try {
				ret = in.nextInt();
				correcto = true;
			} catch (InputMismatchException ime) {
				System.out.println(
						"Formato introducido incorrecto. Intente de nuevo.");
			}
		} while (!correcto);
		return ret;
	}

	public static String removeDiacriticalMarks(String string) {
		// Form.NFC acepta ñ y distingue las tildes en español
		return Normalizer.normalize(string, Form.NFC)
				.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

	/**
	 * Lee una fecha en formato europeo (dd/MM/yyyy), valida que exista y que
	 * esté dentro del rango de hoy a un año máximo.
	 *
	 * @return LocalDate con la fecha válida
	 */
	public static LocalDate leerFechasLocalDate() {
		Scanner sc = new Scanner(System.in);
		LocalDate fecha = null;
		boolean correcta = false;

		do {
			try {
				System.out.print("Introduce el día (1-31): ");
				int dia = sc.nextInt();

				System.out.print("Introduce el mes (1-12): ");
				int mes = sc.nextInt();

				System.out.print("Introduce el año (YYYY): ");
				int anio = sc.nextInt();

				fecha = LocalDate.of(anio, mes, dia);

				LocalDate hoy = LocalDate.now();
				LocalDate max = hoy.plusYears(1);

				if (fecha.isBefore(hoy)) {
					System.out
							.println("❌ La fecha no puede ser anterior a hoy.");
				} else if (fecha.isAfter(max)) {
					System.out.println(
							"❌ La fecha no puede ser posterior a un año desde hoy.");
				} else {
					correcta = true;
				}

			} catch (InputMismatchException e) {
				System.out.println("❌ Debes introducir números válidos.");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println(
						"❌ Fecha introducida incorrecta (día, mes o año no válidos).");
				sc.nextLine();
			}

		} while (!correcta);

		sc.nextLine();
		return fecha;
	}

	/**
	 * Metodo para introducir el nombre completo de la persona con sus
	 * comprobaciones
	 * 
	 * @return
	 */
	public static String leerNombrePersona() {
		Scanner in = new Scanner(System.in, "ISO-8859-1");
		String nombrePersona = "";
		boolean correcto = false;

		do {
			System.out.print(
					"Introduce el nombre de la persona (máx. 25 caracteres): ");
			nombrePersona = in.nextLine().trim();

			if (nombrePersona.length() <= 2) {
				System.out.println("El nombre debe tener más de 2 caracteres.");
			} else if (nombrePersona.length() > 25) {
				System.out.println(
						"El nombre no puede tener más de 25 caracteres.");
			} else if (nombrePersona.contains(" ")) {
				System.out.println(
						"El nombre no puede contener espacios en blanco.");
			} else {

				String sinTildes = removeDiacriticalMarks(nombrePersona);

				if (!sinTildes.matches("[a-zA-Z]+")) {
					System.out.println(
							"El nombre solo puede contener letras (sin tildes).");
				} else {
					nombrePersona = sinTildes.toLowerCase();
					correcto = true;
				}
			}

		} while (!correcto);

		return nombrePersona;
	}

	/***
	 * Metodo para introducir el apodo con sus comprobaciones
	 * 
	 * @return
	 */

	public static String leerApodo() {
		Scanner in = new Scanner(System.in, "ISO-8859-1");
		String apodo = "";
		boolean correcto = false;

		do {
			System.out.print("Introduce el apodo (máx. 25 caracteres): ");
			apodo = in.nextLine().trim();

			if (apodo.isEmpty()) {
				System.out.println("El apodo no puede estar vacío.");
			} else if (apodo.length() > 25) {
				System.out.println(
						"El apodo no puede tener más de 25 caracteres.");
			} else {
				correcto = true;
			}
		} while (!correcto);

		return apodo;
	}

	/**
	 * Metodo para introducir el email con sus comprobaciones
	 * 
	 * @return
	 */

	public static String leerEmailValido() {
		Scanner in = new Scanner(System.in, "ISO-8859-1");
		String email = "";
		boolean correcto = false;

		do {
			System.out.print("Introduce el email (máx. 25 caracteres): ");
			email = in.nextLine().trim();

			if (email.isEmpty()) {
				System.out.println("El email no puede estar vacío.");
			} else if (email.length() > 25) {
				System.out.println(
						"El email no puede tener más de 25 caracteres.");
			} else if (!email.contains("@") || !email.contains(".")) {
				System.out.println("El email debe contener '@' y '.'.");
			} else {
				correcto = true;
			}

		} while (!correcto);

		return email;
	}

	/***
	 * Metodo para introducr el nombre de usuario con sus comprobaciones
	 * 
	 * @return
	 */

	public static String leerNombreUsuarioValido() {
		Scanner in = new Scanner(System.in, "ISO-8859-1");
		String nombreUsuario = "";
		boolean correcto = false;

		do {
			System.out.print(
					"Introduce el nombre de usuario (máx. 25 caracteres): ");
			nombreUsuario = in.nextLine().trim();

			if (nombreUsuario.length() <= 2) {
				System.out.println(
						"El nombre de usuario debe tener más de 2 caracteres.");
			} else if (nombreUsuario.length() > 25) {
				System.out.println(
						"El nombre de usuario no puede tener más de 25 caracteres.");
			} else if (nombreUsuario.contains(" ")) {
				System.out.println(
						"El nombre de usuario no puede contener espacios en blanco.");
			} else {

				String sinTildes = removeDiacriticalMarks(nombreUsuario);

				if (!sinTildes.matches("[a-zA-Z]+")) {
					System.out.println(
							"El nombre de usuario solo puede contener letras (sin tildes).");
				} else {
					nombreUsuario = sinTildes.toLowerCase();
					correcto = true;
				}
			}

		} while (!correcto);

		return nombreUsuario;
	}

	public static String leerPasswordValida() {
		Scanner in = new Scanner(System.in, "ISO-8859-1");
		String password = "";
		boolean correcto = false;

		do {
			System.out.print("Introduce la contraseña (máx. 40 caracteres): ");
			password = in.nextLine().trim();

			if (password.length() <= 2) {
				System.out.println(
						"La contraseña debe tener más de 2 caracteres.");
			} else if (password.length() > 40) {
				System.out.println(
						"La contraseña no puede tener más de 40 caracteres.");
			} else if (password.contains(" ")) {
				System.out.println(
						"La contraseña no puede contener espacios en blanco.");
			} else {
				correcto = true;
			}

		} while (!correcto);

		return password;
	}

	/**
	 * Metodo para selecicon el perfil
	 * 
	 * 
	 * @return
	 */

	public static int leerOpcionPerfil() {
		Scanner in = new Scanner(System.in, "ISO-8859-1");
		boolean correcto = false;
		int opcion = -1;

		do {
			System.out.println("Elige el perfil:");
			System.out.println("1. Coordinación");
			System.out.println("2. Artista");
			System.out.print("Opción: ");

			String entrada = in.nextLine().trim();

			try {
				opcion = Integer.parseInt(entrada);
				if (opcion == 1 || opcion == 2) {
					correcto = true;
				} else {
					System.out.println("Opción no válida. Debes elegir 1 o 2.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Debes introducir un número (1 o 2).");
			}

		} while (!correcto);

		return opcion;
	}

	/***
	 * Metodo para comprobar la fecha senior se meten los parametros de manera
	 * individual y se transofrman a localDate
	 * 
	 */

	public static LocalDate leerFechaSenior() {
		Scanner sc = new Scanner(System.in, "ISO-8859-1");
		LocalDate fecha = null;
		boolean correcto = false;

		do {
			try {
				System.out.print("Introduce el día (1-31): ");
				int dia = sc.nextInt();

				System.out.print("Introduce el mes (1-12): ");
				int mes = sc.nextInt();

				System.out.print("Introduce el año (YYYY): ");
				int anio = sc.nextInt();

				fecha = LocalDate.of(anio, mes, dia);
				LocalDate hoy = LocalDate.now();

				if (!fecha.isBefore(hoy)) {
					System.out.println(
							"La fecha de senior debe ser ANTERIOR a hoy.");
				} else {
					correcto = true;
				}

			} catch (InputMismatchException e) {
				System.out.println("Debes introducir números válidos.");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println(
						"Fecha introducida incorrecta (día, mes o año no válidos).");
				sc.nextLine();
			}

		} while (!correcto);

		sc.nextLine();
		return fecha;
	}

	/***
	 * Metodo para pedir especialidades, con un switch para comprobar con opcion
	 * boolean valida para que no se repita la eleccion
	 * 
	 * @return
	 */
	public static String pedirEspecialidades() {

		java.util.Set<String> especialidadesSeleccionadas = new java.util.LinkedHashSet<>();
		boolean seguirEligiendo;

		do {
			boolean opcionValida = false;
			String especialidad = null;

			do {
				System.out.println("Elige la especialidad del artista:");
				System.out.println("1. ACROBACIA");
				System.out.println("2. HUMOR");
				System.out.println("3. MAGIA");
				System.out.println("4. EQUILIBRISMO");
				System.out.println("5. MALABARISMO");

				int opcion = utils.Utilidades.leerInt();

				switch (opcion) {
				case 1:
					especialidad = "ACROBACIA";
					opcionValida = true;
					break;
				case 2:
					especialidad = "HUMOR";
					opcionValida = true;
					break;
				case 3:
					especialidad = "MAGIA";
					opcionValida = true;
					break;
				case 4:
					especialidad = "EQUILIBRISMO";
					opcionValida = true;
					break;
				case 5:
					especialidad = "MALABARISMO";
					opcionValida = true;
					break;
				default:
					System.out.println(
							"Opción no válida. Debes elegir un número del 1 al 5.");
				}

			} while (!opcionValida);

			if (especialidadesSeleccionadas.contains(especialidad)) {
				System.out.println(
						"Esa especialidad ya está seleccionada. No se añade de nuevo.");
			} else {
				especialidadesSeleccionadas.add(especialidad);
			}

			System.out.println("¿Quieres añadir otra especialidad? (S/N)");
			seguirEligiendo = utils.Utilidades.leerBoolean();

		} while (seguirEligiendo);

		if (especialidadesSeleccionadas.isEmpty()) {
			System.out.println(
					"No se ha seleccionado ninguna especialidad. Se asigna ACROBACIA por defecto.");
			especialidadesSeleccionadas.add("ACROBACIA");
		}

		return String.join(",", especialidadesSeleccionadas);
	}

	/***
	 * Metodo para actualizar el nombre completo
	 * 
	 * @param nombreActual
	 * @return
	 */
	public static String actualizarNombrePersona(String nombreActual) {
		String nuevoNombre = nombreActual;
		boolean correcto = false;
		Scanner in;

		do {
			System.out.println("Nombre actual: " + nombreActual);
			System.out.print("Nuevo nombre (Enter para mantener el actual): ");
			in = new Scanner(System.in, "ISO-8859-1");
			String entrada = in.nextLine().trim();

			if (entrada.isEmpty()) {
				nuevoNombre = nombreActual;
				correcto = true;
			} else if (entrada.length() <= 2) {
				System.out.println("El nombre debe tener más de 2 caracteres.");
			} else if (entrada.length() > 25) {
				System.out.println(
						"El nombre no puede tener más de 25 caracteres.");

			} else {
				String sinTildes = removeDiacriticalMarks(entrada);
				if (!sinTildes.matches("[a-zA-Z]+")) {
					System.out.println(
							"El nombre solo puede contener letras (sin tildes).");
				} else {
					nuevoNombre = sinTildes.toLowerCase();
					correcto = true;
				}
			}

		} while (!correcto);

		return nuevoNombre;
	}

	/**
	 * Metodo para actualizar el nombre usuario
	 * 
	 * @param usuarioActual
	 */
	public static String actualizarNombreUsuario(String usuarioActual) {
		Scanner in;
		String nuevoUsuario = usuarioActual;
		boolean correcto = false;

		do {
			System.out.println("Nombre de usuario actual: " + usuarioActual);
			System.out.print(
					"Nuevo nombre de usuario (Enter para mantener el actual): ");
			in = new Scanner(System.in, "ISO-8859-1");
			String entrada = in.nextLine().trim();

			if (entrada.isEmpty()) {
				nuevoUsuario = usuarioActual;
				correcto = true;
			} else if (entrada.length() <= 2) {
				System.out.println(
						"El nombre de usuario debe tener más de 2 caracteres.");
			} else if (entrada.length() > 25) {
				System.out.println(
						"El nombre de usuario no puede tener más de 25 caracteres.");
			} else if (entrada.contains(" ")) {
				System.out.println(
						"El nombre de usuario no puede contener espacios en blanco.");
			} else {
				String sinTildes = removeDiacriticalMarks(entrada);
				if (!sinTildes.matches("[a-zA-Z]+")) {
					System.out.println(
							"El nombre de usuario solo puede contener letras (sin tildes).");
				} else {
					nuevoUsuario = sinTildes.toLowerCase();
					correcto = true;
				}
			}

		} while (!correcto);

		return nuevoUsuario;
	}

	/**
	 * Funcion para actualizar el email, con las comprobaciones
	 * correspondientes.
	 * 
	 * @param emailActual
	 * @return
	 */
	public static String actualizarEmail(String emailActual) {
		String nuevoEmail = emailActual;
		boolean correcto = false;
		Scanner in;

		do {
			System.out.println("Email actual: " + emailActual);
			System.out.print("Nuevo email (Enter para mantener el actual): ");
			in = new Scanner(System.in, "ISO-8859-1");
			String entrada = in.nextLine().trim();

			if (entrada.isEmpty()) {
				nuevoEmail = emailActual;
				correcto = true;
			} else if (entrada.length() > 50) {
				System.out.println(
						"El email no puede tener más de 50 caracteres.");
			} else if (!entrada.contains("@") || !entrada.contains(".")) {
				System.out.println("❌ El email debe contener '@' y '.'.");
			} else if (entrada.contains(" ")) {
				System.out.println(
						"El email no puede contener espacios en blanco.");
			} else {
				nuevoEmail = entrada;
				correcto = true;
			}

		} while (!correcto);

		return nuevoEmail;
	}

	public static Boolean actualizarEsSenior(Boolean seniorActual) {
		Boolean nuevoValor = seniorActual;
		boolean correcto = false;

		do {
			System.out.println("Valor actual de 'senior': "
					+ ((seniorActual != null && seniorActual) ? "Sí" : "No"));
			System.out.println("¿Quieres cambiar el valor de 'senior'? (S/N)");

			boolean cambiar = leerBoolean();

			if (!cambiar) {

				nuevoValor = seniorActual;
				correcto = true;
			} else {
				System.out.println("¿Debe ser senior ahora? (S/N)");
				boolean esSenior = leerBoolean();
				nuevoValor = esSenior;
				correcto = true;
			}

		} while (!correcto);

		return nuevoValor;
	}

	public static java.time.LocalDate actualizarFechaSenior(
			java.time.LocalDate fechaActual) {
		java.time.LocalDate nuevaFecha = fechaActual;
		boolean correcto = false;

		do {
			if (fechaActual == null) {
				System.out.println(
						"No hay fecha de senior registrada. Debes introducir una.");
				System.out.println(
						"Introduce una fecha de senior (debe ser anterior a hoy):");
				nuevaFecha = leerFechaSenior();
				correcto = true;
			} else {
				System.out.println("Fecha de senior actual: " + fechaActual);
				System.out
						.println("¿Quieres cambiar la fecha de senior? (S/N)");
				boolean cambiar = leerBoolean();

				if (!cambiar) {
					nuevaFecha = fechaActual;
					correcto = true;
				} else {
					System.out.println(
							"Introduce la nueva fecha de senior (debe ser anterior a hoy):");
					nuevaFecha = leerFechaSenior(); // <<--- ESTE
					correcto = true;
				}
			}

		} while (!correcto);

		return nuevaFecha;
	}

	public static String actualizarApodo(String apodoActual) {
		String nuevoApodo = apodoActual;
		boolean correcto = false;
		Scanner in;

		do {
			System.out.println("Apodo actual: " + apodoActual);
			System.out.print("Nuevo apodo (Enter para mantener el actual): ");
			in = new Scanner(System.in, "ISO-8859-1");
			String entrada = in.nextLine().trim();

			if (entrada.isEmpty()) {
				nuevoApodo = apodoActual;
				correcto = true;
			} else if (entrada.length() > 25) {
				System.out.println(
						"❌ El apodo no puede tener más de 25 caracteres.");
			} else {
				nuevoApodo = entrada;
				correcto = true;
			}

		} while (!correcto);

		return nuevoApodo;
	}

	public static String actualizarEspecialidades(
			String especialidadesActuales) {

		String actuales = (especialidadesActuales == null
				|| especialidadesActuales.isBlank()) ? "(ninguna)"
						: especialidadesActuales;

		System.out.println("Especialidades actuales: " + actuales);
		System.out.println("¿Quieres cambiar las especialidades? (S/N)");

		boolean cambiar = leerBoolean();

		if (!cambiar) {
			return especialidadesActuales;
		}

		return pedirEspecialidades();
	}

	public static String actualizarNacionalidad(String nacionalidadActual,
			Map<String, String> mapaPaises) {

		Scanner in = new Scanner(System.in, "ISO-8859-1");
		String nuevaNacionalidad = nacionalidadActual;
		boolean correcto = false;

		do {
			System.out.println("Nacionalidad actual: " + nacionalidadActual);
			System.out.println(
					"¿Quieres cambiar la nacionalidad? (S/N) (Enter = mantener actual)");

			String resp = in.nextLine().trim();

			if (resp.isEmpty()) {
				return nacionalidadActual;
			}

			if (resp.equalsIgnoreCase("n")) {
				return nacionalidadActual;
			}

			if (!resp.equalsIgnoreCase("s")) {
				System.out.println("Opción no válida. Escribe S o N.");
				continue;
			}

			System.out.println("Lista de países disponibles:");
			for (Map.Entry<String, String> entry : mapaPaises.entrySet()) {
				System.out.println(entry.getKey() + " - " + entry.getValue());
			}

			System.out.print(
					"Introduce el código de país (o Enter para mantener el actual): ");
			String codigo = in.nextLine().trim();

			if (codigo.isEmpty()) {
				nuevaNacionalidad = nacionalidadActual;
				correcto = true;
			} else if (!mapaPaises.containsKey(codigo)) {
				System.out.println(
						"❌ Código de país no válido. Inténtalo de nuevo.");
			} else {
				nuevaNacionalidad = mapaPaises.get(codigo);
				correcto = true;
			}

		} while (!correcto);

		return nuevaNacionalidad;
	}
}