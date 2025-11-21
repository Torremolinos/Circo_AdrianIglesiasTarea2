/**
* Clase EspecialidadesAdapter.java
*
* @author ADRIAN IGLESIAS RIÑO
* @version 1.0
*/

package utils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import entidades.Especialidades;

public class EspecialidadesAdapter {

	/**
	 * Funcion para transformar una lista de arrays en cadena de strings.
	 * 
	 */
	public static String listaAString(List<Especialidades> lista) {

		if (lista == null || lista.isEmpty()) {
			return null;
		}

		Set<String> nombres = new LinkedHashSet<>();

		for (Especialidades e : lista) {
			if (e != null) {
				nombres.add(e.name());
			}
		}

		if (nombres.isEmpty()) {
			return null;
		}

		return String.join(",", nombres);
	}

	/***
	 * Funcion para cambiar la string a lista
	 * 
	 * @param valor
	 * @return
	 */
	public static List<Especialidades> stringALista(String valor) {

		List<Especialidades> lista = new ArrayList<>();

		if (valor == null || valor.isBlank()) {
			return lista;
		}

		String[] partes = valor.split(",");

		for (String parte : partes) {
			String nombre = parte.trim();
			if (!nombre.isEmpty()) {
				try {
					Especialidades e = Especialidades.valueOf(nombre);
					lista.add(e);
				} catch (IllegalArgumentException ex) {

				}
			}
		}

		return lista;
	}
}
