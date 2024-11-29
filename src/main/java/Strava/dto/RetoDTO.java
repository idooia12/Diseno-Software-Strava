package Strava.dto;

import java.io.Serializable;
import java.time.LocalDate;


import Strava.entity.Deporte;
import Strava.entity.TipoDeReto;

public class RetoDTO implements Serializable {

		private static final long serialVersionUID = 1L;
		private String nombre;
		private LocalDate fechaInicio;
		private LocalDate fechaFin;
		private TipoDeReto tipoReto;
		private int objetivo;
		private Deporte deporte;
		private UsuarioDTO usuario;
		
		public RetoDTO() {
			super();
		}
		public String getNombre() {
			return nombre;
		}
		
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		public LocalDate getFechaInicio() {
			return fechaInicio;
		}
		
		public void setFechaInicio(LocalDate fechaInicio) {
			this.fechaInicio = fechaInicio;
		}
		
		public LocalDate getFechaFin() {
			return fechaFin;
		}
		
		public void setFechaFin(LocalDate fechaFin) {
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
		public void setDeporte(String string) {
			this.deporte = Deporte.fromString(string);
		}
		public UsuarioDTO getUsuario() {
			return usuario;
		}
		public void setUsuario(UsuarioDTO usuario) {
			this.usuario = usuario;
		}
		
}
