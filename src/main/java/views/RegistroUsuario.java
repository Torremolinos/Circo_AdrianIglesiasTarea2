package views;

import java.util.Scanner;

import entidades.Perfiles;

public class RegistroUsuario {

	 private final Scanner sc = new Scanner(System.in);

	    public String pedirNombrePersona() {
	        System.out.println("Introduce el nombre de la persona:");
	        return sc.nextLine().trim();
	    }

	    public String pedirApellidosPersona() {
	        System.out.println("Introduce los apellidos de la persona:");
	        return sc.nextLine().trim();
	    }

	    public String pedirDniPersona() {
	        System.out.println("Introduce el DNI de la persona:");
	        return sc.nextLine().trim();
	    }

	    public String pedirTelefonoPersona() {
	        System.out.println("Introduce el teléfono de la persona:");
	        return sc.nextLine().trim();
	    }

	    public Perfiles pedirTipoPerfil() {
	        while (true) {
	            System.out.println("Selecciona el tipo de perfil para esta persona:");
	            System.out.println("1. ARTISTA");
	            System.out.println("2. COORDINADOR");

	            String opcion = sc.nextLine().trim();

	            switch (opcion) {
	                case "1":
	                    return Perfiles.ARTISTA;
	                case "2":
	                    return Perfiles.COORDINACION;
	                default:
	                    System.out.println("❌ Opción no válida. Inténtalo de nuevo.");
	            }
	        }
	    }

	    public String pedirEspecialidadArtista() {
	        System.out.println("Introduce la especialidad del artista (ej: trapecista, malabarista...):");
	        return sc.nextLine().trim();
	    }

	    public String pedirDepartamentoCoordinacion() {
	        System.out.println("Introduce el departamento de coordinación (ej: técnica, artística...):");
	        return sc.nextLine().trim();
	    }

	    public String pedirNombreUsuario() {
	        System.out.println("Introduce el nombre de usuario para el login:");
	        return sc.nextLine().trim();
	    }

	    public String pedirPassword() {
	        System.out.println("Introduce la contraseña para el login:");
	        return sc.nextLine().trim();
	    }

	    public boolean confirmarRegistro(
	            String nombrePersona,
	            String apellidosPersona,
	            String dni,
	            String telefono,
	            Perfiles perfilPersona,
	            String datoExtra,
	            String nombreUsuario
	    ) {
	        System.out.println("\n=== RESUMEN DEL USUARIO A REGISTRAR ===");
	        System.out.println("Nombre: " + nombrePersona);
	        System.out.println("Apellidos: " + apellidosPersona);
	        System.out.println("DNI: " + dni);
	        System.out.println("Teléfono: " + telefono);
	        System.out.println("Perfil: " + perfilPersona);

	        if (perfilPersona == Perfiles.ARTISTA) {
	            System.out.println("Especialidad artista: " + datoExtra);
	        } else if (perfilPersona == Perfiles.COORDINACION) {
	            System.out.println("Departamento coordinación: " + datoExtra);
	        }

	        System.out.println("Nombre de usuario (login): " + nombreUsuario);

	        System.out.println("\n¿Confirmas que los datos son correctos?");
	        System.out.println("Pulsa S para confirmar, N para cancelar:");

	        String opcion = sc.nextLine().trim().toLowerCase();
	        return opcion.equals("s");
	    }
}
