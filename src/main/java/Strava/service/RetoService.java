package Strava.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import Strava.entity.*;

@Service
public class RetoService {

	private static RetoService instance;
	private List<RetoEntity> retos = new ArrayList<>();


	private RetoService() {
	}

	public static RetoService getInstance() {
		if (instance == null) {
			instance = new RetoService();
		}

		return instance;
	}
	
	public void crearReto(UsuarioEntity usuario, String nombre, Date fechaInicio, Date fechaFin,
			int objetivo, Deporte deporteReto) {
		RetoEntity reto = new RetoEntity();
		reto.setNombre(nombre);
		reto.setFechaInicio(fechaInicio);
		reto.setFechaFin(fechaFin);
		reto.setObjetivo(objetivo);
		reto.setDeporte(deporteReto);
		retos.add(reto);
		usuario.addRetoAceptado(reto);
	}
	
	public List<RetoEntity> getRetosActivos(UsuarioEntity usuario) {
		Date fechaActual = new Date();
		List<RetoEntity> retosActivos = new ArrayList<>();
		for (RetoEntity reto : this.retos) {
			if (reto.getFechaInicio().before(fechaActual) && reto.getFechaFin().after(fechaActual)) {
				if (usuario.getRetosAceptados().contains(reto)){
					retosActivos.add(reto);
				}
			}
		}
		return retosActivos;
	}
	
	public void aceptarReto(UsuarioEntity usuario, RetoEntity reto) {
		if (retos.contains(reto)){
			usuario.addRetoAceptado(reto);
		}
	}
	
	public List<RetoEntity> consultarRetosAceptados(UsuarioEntity usuario) {
       return usuario.getRetosAceptados();
    }
}
