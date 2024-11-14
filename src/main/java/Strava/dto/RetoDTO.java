package Strava.dto;

import java.io.Serializable;
import java.util.Date;


import Strava.entity.Deporte;
import Strava.entity.TipoDeReto;
import Strava.entity.UsuarioEntity;

public class RetoDTO implements Serializable {

		private static final long serialVersionUID = 1L;
		private String nombre;
		private Date fechaInicio;
		private Date fechaFin;
		private TipoDeReto tipoReto;
		private int objetivo;
		private Deporte deporte;
		private UsuarioDTO usuario;
		
		public RetoDTO(String nombre, UsuarioDTO usuarioCreador, Date fechaInicio, Date fechaFin, 
				int objetivo, Deporte deporte, TipoDeReto tipoReto) {
			super();
			this.nombre = nombre;
			this.usuario = usuarioCreador;
			this.fechaInicio = fechaInicio;
			this.fechaFin = fechaFin;
			this.objetivo = objetivo;
			this.deporte = deporte;
			this.tipoReto = tipoReto;
		}
		
		public RetoDTO(String string, Date date, Date date2, TipoDeReto tipoDeReto, int i, Deporte deporte2) {
			super();
			this.nombre = "";
			this.fechaInicio = new Date(0);
			this.fechaFin = new Date(0);
			this.objetivo = 0;
			this.deporte = Deporte.running;
			this.tipoReto = TipoDeReto.DISTANCIA;
		}
		
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
