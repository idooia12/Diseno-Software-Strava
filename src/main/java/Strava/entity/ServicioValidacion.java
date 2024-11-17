package Strava.entity;

public enum ServicioValidacion {
	Google("Google"), Meta("Meta");
	private String nombre;

	ServicioValidacion(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public static ServicioValidacion fromString(String servicio) {
		for (ServicioValidacion s : ServicioValidacion.values()) {
			if (s.nombre.equalsIgnoreCase(servicio)) {
				return s;
			}
		}
		return Google; // Valor por defecto o manejar de otra manera si no se encuentra la coincidencia
	}
}
	


