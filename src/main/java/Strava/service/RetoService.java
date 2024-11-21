package Strava.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Strava.entity.*;

@Service
public class RetoService {

	private List<RetoEntity> retos = new ArrayList<>();


	private RetoService() {
	}

	
	public void crearReto(UsuarioEntity usuario, String nombre, LocalDate fechaInicio, LocalDate fechaFin,
			int objetivo, Deporte deporteReto) {
		RetoEntity reto = new RetoEntity();
		reto.setUsuarioCreador(usuario);
		reto.setNombre(nombre);
		reto.setFechaInicio(fechaInicio);
		reto.setFechaFin(fechaFin);
		reto.setObjetivo(objetivo);
		reto.setDeporte(deporteReto);
		usuario.addRetoAceptado(reto);
		retos.add(reto);
	}
	
	public List<RetoEntity> getRetosActivos(UsuarioEntity usuario) {
		LocalDate fechaActual = LocalDate.now();
		List<RetoEntity> retosActivos = new ArrayList<>();
		for (RetoEntity reto : this.retos) {
			if (reto.getFechaInicio().isBefore(fechaActual) && reto.getFechaFin().isAfter(fechaActual)) {
				if (usuario.getRetosAceptados().contains(reto)){
					retosActivos.add(reto);
				}
			}
		}
		return retosActivos;
	}
	
	public List<RetoEntity> getRetosAceptados(UsuarioEntity usuario) {
		return usuario.getRetosAceptados();
	}
	
	public void aceptarReto(UsuarioEntity usuario, RetoEntity reto) {
		if (retos.contains(reto)){
			usuario.addRetoAceptado(reto);
		}
	}
	
	
	public List<RetoEntity> consultarRetosAceptados(UsuarioEntity usuario) {
       return usuario.getRetosAceptados();
    }
	
	public void addReto(RetoEntity reto) {
		retos.add(reto);
	}
}
