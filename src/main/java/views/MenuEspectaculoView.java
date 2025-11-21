package views;

import java.util.LinkedHashSet;

import entidades.Espectaculo;

public class MenuEspectaculoView {

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
}
