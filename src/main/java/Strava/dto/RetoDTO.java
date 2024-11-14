package Strava.dto;

import java.io.Serializable;
import java.util.Date;


import Strava.entity.Deporte;
import Strava.entity.TipoDeReto;

public class RetoDTO implements Serializable {

		private static final long serialVersionUID = 1L;
		private String nombre;
		private Date fechaInicio;
		private Date fechaFin;
		private TipoDeReto tipoReto;
		private int objetivo;
		private Deporte deporte;
		
		public String getNombre() {
			return nombre;
		}
		
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		public Date getFechaInicio() {
			return fechaInicio;
		}
		
		public void setFechaInicio(Date fechaInicio) {
			this.fechaInicio = fechaInicio;
		}
		
		public Date getFechaFin() {
			return fechaFin;
		}
		
		public void setFechaFin(Date fechaFin) {
			this.fechaFin = fechaFin;
		}
		
		public int getObjetivo() {
			return objetivo;
		}
		
		public void setObjetivo(int objetivo) {
			this.objetivo = objetivo;
		}
		
		public Deporte getDeporte() {
			return deporte;
		}
		
		public void setDeporte(Deporte deporte) {
			this.deporte = deporte;
		}
		
		public TipoDeReto getTipoReto() {
			return tipoReto;
		}
		
		public void setTipoReto(TipoDeReto tipoReto) {
			this.tipoReto = tipoReto;
		}
}
