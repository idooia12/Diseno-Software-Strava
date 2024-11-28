package Strava.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "retos")
public class RetoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
	private String nombre;
   
    @ManyToOne
    @JoinColumn(name = "usuario_creador_email", nullable = false)
	private UsuarioEntity usuarioCreador;
    
    @Column
	private LocalDate fechaInicio;
    
    @Column
	private LocalDate fechaFin;
    
    @Column
	private int objetivo;
    
    @Enumerated(EnumType.STRING)
    @Column
	private TipoDeReto tipoReto; 
    
	@Enumerated(EnumType.STRING)
	@Column
	private Deporte deporte;
	
	@ManyToMany
    @JoinTable(
        name = "usuariosAceptados",
        joinColumns = @JoinColumn(name = "reto_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_email")
    )
    private List<UsuarioEntity> usuariosAceptados = new ArrayList<>();
	
	@ManyToMany
    @JoinTable(
        name = "usuariosEnRetoActivo",
        joinColumns = @JoinColumn(name = "reto_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_email")
    )
    private List<UsuarioEntity> usuariosEnRetoActivo = new ArrayList<>(); //Tiene que llamarse igual que el mappedby de usuarioEntity
	
	//Constructor
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
		usuarioCreador.addRetoCreado(this);
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

	@Override
	public int hashCode() {
		return Objects.hash(fechaInicio, id, nombre, usuarioCreador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RetoEntity other = (RetoEntity) obj;
		return Objects.equals(fechaInicio, other.fechaInicio) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(usuarioCreador, other.usuarioCreador);
	}
	
	
	
}
