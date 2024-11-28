package Strava.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios") // "user" is a reserved keyword in H2 database

public class UsuarioEntity {
	@Id
	private String email;
	@Column
	private String nombre;
	@Column
	private String contraseña;
	@Column
	private LocalDate fechaNacimiento;
	@Column
	private int peso;
	@Column
	private int altura;
	@Column
	private int FCmax_ppm;
	@Column
	private int FCrep_ppm;
	@Column
	private ServicioValidacion servicio;
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SesionEntrenamientoEntity> entrenamientos = new ArrayList<>();
	@OneToMany(mappedBy = "usuario creador", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RetoEntity> retosCreados = new ArrayList<>();
	@ManyToMany(mappedBy = "usuarios en reto", cascade = CascadeType.ALL)
	private List<RetoEntity> retosAceptados = new ArrayList<>();
	@ManyToMany(mappedBy = "usuarios en reto activo", cascade = CascadeType.ALL)
	private List<RetoEntity> retosActivos = new ArrayList<>();
	
	public UsuarioEntity(String email, String nombre, String contrasena, LocalDate fechaNacimiento, int peso, int altura, int FCmax_ppm,
			int FCrep_ppm, ServicioValidacion servicio) {
		super();
		this.email = email;
		this.nombre = nombre;
		this.contraseña = contrasena;
		this.fechaNacimiento = fechaNacimiento;
		this.peso = peso;
		this.altura = altura;
		this.FCmax_ppm = FCmax_ppm;
		this.FCrep_ppm = FCrep_ppm;
		this.servicio = servicio;
	}
	
	public UsuarioEntity() {
		super();
		this.email = "";
		this.nombre = "";
		this.contraseña = "";
		this.fechaNacimiento = LocalDate.of(1980,1,1);
		this.peso = 0;
		this.altura = 0;
		this.FCmax_ppm = 0;
		this.FCrep_ppm = 0;
		this.servicio = ServicioValidacion.Google;
	}
	
	
	//Getters y setters
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
        this.contraseña = contraseña;   
    }
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public int getFCmax_ppm() {
		return FCmax_ppm;
	}
	public void setFCmax_ppm(int fCmax_ppm) {
		FCmax_ppm = fCmax_ppm;
	}
	public int getFCrep_ppm() {
		return FCrep_ppm;
	}
	public void setFCrep_ppm(int fCrep_ppm) {
		FCrep_ppm = fCrep_ppm;
	}
	public ServicioValidacion getServicio() {
		return servicio;
	}
	public void setServicio(ServicioValidacion servicio) {
		this.servicio = servicio;
	}	
	
	public List<RetoEntity> getRetosCreados() {
		return retosCreados;
	}

	public void setRetosCreados(List<RetoEntity> retosCreados) {
		this.retosCreados = retosCreados;
	}
	

	public List<RetoEntity> getRetosAceptados() {
		return retosAceptados;
	}

	public void setRetosAceptados(List<RetoEntity> retosAceptados) {
		this.retosAceptados = retosAceptados;
	}

	public List<SesionEntrenamientoEntity> getEntrenamientos() {
		return entrenamientos;
	}

	public void setEntrenamientos(List<SesionEntrenamientoEntity> entrenamientos) {
		this.entrenamientos = entrenamientos;
	}
	

	public List<RetoEntity> getRetosActivos() {
		return retosActivos;
	}

	public void setRetosActivos(List<RetoEntity> retosActivos) {
		this.retosActivos = retosActivos;
	}
	
	
	//ANADIR A LISTAS
	public void addRetoAceptado(RetoEntity reto) {
		this.retosAceptados.add(reto);
	}
	
	public void addRetoCreado(RetoEntity reto) {
		this.retosCreados.add(reto);
	}
	
	public void addRetoActivo(RetoEntity reto) {
		this.retosActivos.add(reto);
	}
	
	public void addEntrenamiento(SesionEntrenamientoEntity entrenamiento) {
		this.entrenamientos.add(entrenamiento);
	}
	
	@Override
	public String toString() {
		return "Usuario [email=" + email + ", nombre=" + nombre + ", contraseña=" + contraseña + ",  fechaNacimiento=" + fechaNacimiento + ", peso="
				+ peso + ", altura=" + altura + ", FCmax_ppm=" + FCmax_ppm + ", FCrep_ppm=" + FCrep_ppm + ", servicio=" + servicio + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, fechaNacimiento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioEntity other = (UsuarioEntity) obj;
		return Objects.equals(email, other.email) && Objects.equals(fechaNacimiento, other.fechaNacimiento);
	}
	
	
	
	
	
}
