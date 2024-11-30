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
	
	@ManyToMany(mappedBy = "retosAceptados", fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Indica que UsuarioEntity es el propietario
    private List<UsuarioEntity> usuariosEnReto = new ArrayList<>();
	
	//Constructor
	public RetoEntity(String nombre, LocalDate fechaInicio, LocalDate fechaFin, 
			int objetivo, Deporte deporte, TipoDeReto tipoReto) {
		super();
		this.nombre = nombre;
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
	
	
	public List<UsuarioEntity> getUsuariosEnReto() {
		return usuariosEnReto;
	}
	
	public Long getId() {
		return id;
	}
	
	public void addUsuarioEnReto(UsuarioEntity usuario) {
		this.usuariosEnReto.add(usuario);
	}



	@Override
	public String toString() {
		return "Reto [nombre=" + nombre + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", objetivo="
				+ objetivo + ", deporte=" + deporte + "]";
	}

	


	@Override
	public int hashCode() {
		return Objects.hash(deporte, fechaFin, fechaInicio, nombre, objetivo, tipoReto);
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
		return deporte == other.deporte && Objects.equals(fechaFin, other.fechaFin)
				&& Objects.equals(fechaInicio, other.fechaInicio) && Objects.equals(nombre, other.nombre)
				&& objetivo == other.objetivo && tipoReto == other.tipoReto;
	}

	//El reto existe por nombre, fechaInicio y fechaFin
	public boolean retoRepetido(RetoEntity reto) {
		return this.nombre.equals(reto.getNombre()) && this.fechaInicio.equals(reto.getFechaInicio())
				&& this.fechaFin.equals(reto.getFechaFin());
	}	

}
