package Strava.dto;

import java.io.Serializable;
import java.util.Objects;

public class RetoDTO implements Serializable {

		private static final long serialVersionUID = 1L;
		private String nombre;
		private String fechaInicio;
		private String fechaFin;
		private int objetivo;
		private String deporte;
		
		public String getNombre() {
			return nombre;
		}
		
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		public String getFechaInicio() {
			return fechaInicio;
		}
		
		public void setFechaInicio(String fechaInicio) {
			this.fechaInicio = fechaInicio;
		}
		
		public String getFechaFin() {
			return fechaFin;
		}
		
		public void setFechaFin(String fechaFin) {
			this.fechaFin = fechaFin;
		}
		
		public int getObjetivo() {
			return objetivo;
		}
		
		public void setObjetivo(int objetivo) {
			this.objetivo = objetivo;
		}
		
		public String getDeporte() {
			return deporte;
		}
		
		public void setDeporte(String deporte) {
			this.deporte = deporte;
		}
		
		@Override
		public String toString() {
			return "RetoDTO [nombre=" + nombre + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
					+ ", objetivo=" + objetivo + ", deporte=" + deporte + "]";
		}
		
		@Override
		public int hashCode() {
            return Objects.hash(deporte, fechaFin, fechaInicio, nombre, objetivo);
        }
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            RetoDTO other = (RetoDTO) obj;
            return Objects.equals(deporte, other.deporte) && Objects.equals(fechaFin, other.fechaFin)
                    && Objects.equals(fechaInicio, other.fechaInicio) && Objects.equals(nombre, other.nombre)
                    && objetivo == other.objetivo;
		}
		
}
