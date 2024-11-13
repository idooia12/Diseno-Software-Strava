package Strava.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import Strava.entity.Deporte;

public class RetoDTO implements Serializable {

		private static final long serialVersionUID = 1L;
		private String nombre;
		private String fechaInicio;
		private String fechaFin;
		private int objetivo;
		private Set<Integer> deportes = new HashSet<>();
		
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
		
		public Set<Integer> getDeporte() {
			return deportes;
		}
		
		public void setDeporte(String deporte) {
			this.deportes = deportes;
		}
		
		@Override
		public String toString() {
			return "RetoDTO [nombre=" + nombre + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
					+ ", objetivo=" + objetivo + ", deporte=" + deportes + "]";
		}
		
		@Override
		public int hashCode() {
            return Objects.hash(deportes, fechaFin, fechaInicio, nombre, objetivo);
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
            return Objects.equals(deportes, other.deportes) && Objects.equals(fechaFin, other.fechaFin)
                    && Objects.equals(fechaInicio, other.fechaInicio) && Objects.equals(nombre, other.nombre)
                    && objetivo == other.objetivo;
		}
		
}
