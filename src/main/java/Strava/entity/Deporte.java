package Strava.entity;

public enum Deporte {
	ciclismo("Running"), running("Ciclismo"), other("Otro");

	private String nombre;
	
	Deporte(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public static Deporte fromString(String deporte) {
		for (Deporte d : Deporte.values()) {
			if (d.nombre.equalsIgnoreCase(deporte)) {
				return d;
			}
		}
		return other; // Valor por defecto o manejar de otra manera si no se encuentra la coincidencia
	}
}
