package Strava.service;

import java.rmi.RemoteException;
import java.util.*;
import java.sql.Date;

import Strava.entity.Deporte;
import Strava.entity.SesionEntrenamientoEntity;
import Strava.entity.UsuarioEntity;

public class EntrenamientoService {
	
	private static EntrenamientoService instance;
    private List<SesionEntrenamientoEntity> entrenamientos;

	
	 private EntrenamientoService() {
	        entrenamientos = new ArrayList<>(); // Inicializamos la lista
	 }
	
	public void crearEntrenamiento(UsuarioEntity user, String titulo, String deporte, Date inicio, int duracion) throws RemoteException {
		SesionEntrenamientoEntity sesion = new SesionEntrenamientoEntity();
		sesion.setTitulo(titulo);
		sesion.setFecha_inicio(inicio);
		sesion.setDeporte(Deporte.fromString(deporte));
		sesion.setDuracion(duracion);
		

	}
	
	public static EntrenamientoService getInstance() {
		if (instance == null) {
			instance = new EntrenamientoService();
		}

		return instance;
	}

	public List<SesionEntrenamientoEntity> obtenerEntrenamientos() {
		return entrenamientos;
	}


}

