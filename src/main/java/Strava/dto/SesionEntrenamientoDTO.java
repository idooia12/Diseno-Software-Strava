package Strava.dto;

import java.io.Serializable;
import java.util.Date;

import Strava.entity.Deporte;

public class SesionEntrenamientoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String titulo;
	private Deporte deporte;
	private int distanciaKm;
	private Date fechaInicio;
	private Date horaInicio;
	private int duracion;
	
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
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
