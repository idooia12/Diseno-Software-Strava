package Strava.entity;

import java.sql.Date;

public class SesionEntrenamientoEntity {

	private String titulo;
	private Deporte deporte;
	private int distanciaKm;
	private Date fechaInicio;
	private Date horaInicio;
	private int duracion;
	
	public SesionEntrenamientoEntity(String titulo, Deporte deporte, int distanciaKm, Date fecha_inicio,
			Date hora_inicio, int duracion) {
		super();
		this.titulo = titulo;
		this.deporte = deporte;
		this.distanciaKm = distanciaKm;
		this.fechaInicio = fecha_inicio;
		this.horaInicio = hora_inicio;
		this.duracion = duracion;
	}
	
	public SesionEntrenamientoEntity() {
		super();
		this.titulo = "";
		this.deporte = Deporte.running;
		this.distanciaKm = 0;
		this.fechaInicio = new Date(0);
		this.horaInicio = new Date(0);
		this.duracion = 0;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Deporte getDeporte() {
		return deporte;
	}
	
	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}
	
	public int getDistanciaKm() {
		return distanciaKm;
	}
	
	public void setDistanciaKm(int distanciaKm) {
		this.distanciaKm = distanciaKm;
	}
	
	public Date getFecha_inicio() {
		return fechaInicio;
	}
	
	public void setFecha_inicio(Date fecha_inicio) {
		fechaInicio = fecha_inicio;
	}	
	
	public Date getHora_inicio() {
		return horaInicio;
	}
	
	public void setHora_inicio(Date hora_inicio) {
		horaInicio = hora_inicio;
	}
	
	public int getDuracion() {
		return duracion;
	}
	
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	@Override
	public String toString() {
		return "SesionEntrenamientoEntity [titulo=" + titulo + ", deporte=" + deporte + ", distanciaKm=" + distanciaKm
				+ ", Fecha_inicio=" + fechaInicio + ", Hora_inicio=" + horaInicio + ", duracion=" + duracion + "]";
	}
}
