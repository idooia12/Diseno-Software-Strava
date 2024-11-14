package Strava.dto;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import Strava.entity.Deporte;

public class SesionEntrenamientoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UsuarioDTO usuario;
	private String titulo;
	private Deporte deporte;
	private int distanciaKm;
	private Date fechaInicio;
	private LocalTime horaInicio;
	private int duracion;
	
	public UsuarioDTO getUsuario() {
        return usuario;
    }
	
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
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
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalTime getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(LocalTime horaInicio) {
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
