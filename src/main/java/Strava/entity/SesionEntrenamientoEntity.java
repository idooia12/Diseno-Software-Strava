package Strava.entity;

import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SesionEntrenamientoEntity {
	private UsuarioEntity usuario;
	private String titulo;
	private Deporte deporte;
	private int distanciaKm;
	private Date fechaInicio;
	private LocalTime horaInicio;
	private int duracion;
	
	public SesionEntrenamientoEntity(UsuarioEntity usuario, String titulo, Deporte deporte, int distanciaKm, Date fecha_inicio,
			LocalTime hora_inicio, int duracion) {
		super();
		this.usuario = usuario;
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
		this.fechaInicio = new Date();
		this.horaInicio = LocalTime.now();
		this.duracion = 0;
	}
	
	//GETTERS y SETTERS
	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
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
	
	public void setFechaInicio(Date date) {
		fechaInicio = date;
	}	
	
	public LocalTime getHoraInicio() {
		return horaInicio;
	}
	
	public void setHoraInicio(LocalTime hora_inicio) {
		horaInicio = hora_inicio;
	}
	
	public int getDuracion() {
		return duracion;
	}
	
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	
	//OTROS METODOS
	@Override
	public String toString() {
		return "SesionEntrenamientoEntity [titulo=" + titulo + ", deporte=" + deporte + ", distanciaKm=" + distanciaKm
				+ ", Fecha_inicio=" + fechaInicio + ", Hora_inicio=" + horaInicio + ", duracion=" + duracion + "]";
	}
	
}
