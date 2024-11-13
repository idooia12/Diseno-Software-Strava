package Strava.entity;

import java.util.Date;

public class UsuarioEntity {

	private String email;
	private String nombre;
	private Date fechaNacimiento;
	private int peso;
	private int altura;
	private int FCmax_ppm;
	private int FCrep_ppm;
	
	public UsuarioEntity(String email, String nombre, Date fechaNacimiento, int peso, int altura, int FCmax_ppm,
			int FCrep_ppm) {
		super();
		this.email = email;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.peso = peso;
		this.altura = altura;
		this.FCmax_ppm = FCmax_ppm;
		this.FCrep_ppm = FCrep_ppm;
	}
	
	public UsuarioEntity() {
		super();
		this.email = "";
		this.nombre = "";
		this.fechaNacimiento = new Date();
		this.peso = 0;
		this.altura = 0;
		this.FCmax_ppm = 0;
		this.FCrep_ppm = 0;
	}
	
	@Override
	public String toString() {
		return "Usuario [email=" + email + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", peso="
				+ peso + ", altura=" + altura + ", FCmax_ppm=" + FCmax_ppm + ", FCrep_ppm=" + FCrep_ppm + "]";
	}
	
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
	
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.email.equals(((UsuarioEntity)obj).email);
		}
		
		return false;
	}

	public static Object getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
