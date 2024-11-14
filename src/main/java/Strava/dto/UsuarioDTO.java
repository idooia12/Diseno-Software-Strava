package Strava.dto;

import java.util.Date;
import java.util.List;

import Strava.entity.RetoEntity;
import Strava.entity.SesionEntrenamientoEntity;

public class UsuarioDTO {
	private String email;
	private String nombre;
	private Date fechaNacimiento;
	private int peso;
	private int altura;
	private int FCmax_ppm;
	private int FCrep_ppm;
	
	private List<RetoEntity> retosCreados;
	private List<RetoEntity> retosAceptados;
	private List<RetoEntity> retosActivos;
	private List<SesionEntrenamientoEntity> entrenamientos;
	
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
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
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
	public List<RetoEntity> getRetosActivos() {
		return retosActivos;
	}
	public void setRetosActivos(List<RetoEntity> retosActivos) {
		this.retosActivos = retosActivos;
	}
	public List<SesionEntrenamientoEntity> getEntrenamientos() {
		return entrenamientos;
	}
	public void setEntrenamientos(List<SesionEntrenamientoEntity> entrenamientos) {
		this.entrenamientos = entrenamientos;
	}
}
