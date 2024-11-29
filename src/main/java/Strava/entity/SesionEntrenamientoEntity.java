package Strava.entity;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.Objects;

import jakarta.persistence.*;
@Entity
@Table(name = "sesiones_entrenamiento")
public class SesionEntrenamientoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "usuario_mail", nullable = false)
	private UsuarioEntity usuario;
	
	@Column
	private String titulo;
    
	@Enumerated(EnumType.STRING)
	@Column
	private Deporte deporte;
	@Column
	private LocalDate fechaInicio;
	@Column
	private LocalTime horaInicio;
	@Column
	private int distanciaKm;
    @Column
	private int duracion;

	
	public SesionEntrenamientoEntity(UsuarioEntity usuario, String titulo, Deporte deporte, int distanciaKm, LocalDate fecha_inicio,
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
		this.fechaInicio = LocalDate.now();
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
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(LocalDate date) {
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
	
	@Override
	public String toString() {
		return "SesionEntrenamientoEntity [titulo=" + titulo + ", deporte=" + deporte + ", distanciaKm=" + distanciaKm
				+ ", Fecha_inicio=" + fechaInicio + ", Hora_inicio=" + horaInicio + ", duracion=" + duracion + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(deporte, distanciaKm, duracion, fechaInicio, horaInicio, id, titulo, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SesionEntrenamientoEntity other = (SesionEntrenamientoEntity) obj;
		return deporte == other.deporte && distanciaKm == other.distanciaKm && duracion == other.duracion
				&& Objects.equals(fechaInicio, other.fechaInicio) && Objects.equals(horaInicio, other.horaInicio)
				&& Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo)
				&& Objects.equals(usuario, other.usuario);
	}
	
	
	
}
