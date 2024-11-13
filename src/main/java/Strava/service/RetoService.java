package Strava.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Strava.entity.*;

public class RetoService {

	private static RetoService instance;
	private List<RetoEntity> retos = new ArrayList<>();
	private List<RetoEntity> retosActivos = new ArrayList<>();
	private List<RetoEntity> retosAceptados = new ArrayList<>();


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
	}
	
	public List<RetoEntity> getRetosActivos(Date fecha) {
		Date fechaActual = new Date();
		for (RetoEntity reto : this.retos) {
			if (reto.getFechaInicio().before(fechaActual) && reto.getFechaFin().after(fechaActual)) {
				retosActivos.add(reto);
			}
		}
		return retosActivos;
	}
	
	public void aceptarReto(UsuarioEntity usuario, RetoEntity reto) {
		if (retos.contains(reto)){
			retosAceptados.add(reto);
		}
	}
	
	public List<RetoEntity> consultarRetosAceptados(UsuarioEntity usuario) {
       return retosAceptados;
    }
}
