package Strava.entity;

import java.time.LocalDate;

public class RetoEntity {
	
	private String nombre;
	private UsuarioEntity usuarioCreador;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private int objetivo;
	private TipoDeReto tipoReto; 
	private Deporte deporte;
	
	public RetoEntity(String nombre, UsuarioEntity usuarioCreador, LocalDate fechaInicio, LocalDate fechaFin, 
			int objetivo, Deporte deporte, TipoDeReto tipoReto) {
		super();
		this.nombre = nombre;
		this.usuarioCreador = usuarioCreador;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.objetivo = objetivo;
		this.deporte = deporte;
		this.tipoReto = tipoReto;
	}
	
	public RetoEntity() {
		super();
		this.nombre = "";
		this.fechaInicio = LocalDate.now();
		this.fechaFin = LocalDate.now().plusDays(30);
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
	
	public UsuarioEntity getUsuarioCreador() {
		return usuarioCreador;
	}
	
	public void setUsuarioCreador(UsuarioEntity usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
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
	@Override
	public String toString() {
		return "Reto [nombre=" + nombre + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", objetivo="
				+ objetivo + ", deporte=" + deporte + "]";
	}
	
}
