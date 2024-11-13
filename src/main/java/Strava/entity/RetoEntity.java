package Strava.entity;

import java.sql.Date;

public class RetoEntity {
	
	private String nombre;
	private Date fechaInicio;
	private Date fechaFin;
	private int objetivo;
	private Deporte deporte;
	
	public RetoEntity(String nombre, Date fechaInicio, Date fechaFin, int objetivo, Deporte deporte) {
		super();
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.objetivo = objetivo;
		this.deporte = deporte;
	}
	
	public RetoEntity() {
		super();
		this.nombre = "";
		this.fechaInicio = new Date(0);
		this.fechaFin = new Date(0);
		this.objetivo = 0;
		this.deporte = Deporte.running;
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
	
	@Override
	public String toString() {
		return "Reto [nombre=" + nombre + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", objetivo="
				+ objetivo + ", deporte=" + deporte + "]";
	}
	
}
