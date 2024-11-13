package Strava.service;

import java.rmi.RemoteException;
import java.util.*;
import java.sql.Date;

import Strava.entity.Deporte;
import Strava.entity.SesionEntrenamientoEntity;
import Strava.entity.UsuarioEntity;

public class EntrenamientoService {
	
	private static EntrenamientoService instance;
	
	private EntrenamientoService() {}
	
	public void crearEntrenamiento(UsuarioEntity user, String titulo, String deporte, Date inicio, int duracion) throws RemoteException {
		SesionEntrenamientoEntity sesion = new SesionEntrenamientoEntity();
		sesion.setTitulo(titulo);
		sesion.setFecha_inicio(inicio);
		sesion.setDeporte(Deporte.fromString(deporte));
		sesion.setDuracion(duracion);

	//	UsuarioEntity user = UsuarioEntity.getInstance().find(user.getNombre());

	//  user.agregarSesion(sesion);
	//	System.out.println("hola");

	//	SesionEntrenamientoEntity.getInstance().store(sesion);
	//	UsuarioEntity.getInstance().store(usr);
	//	UsuarioEntity.agregarSesion(sesion);
		
	}
	
	public static EntrenamientoService getInstance() {
		if (instance == null) {
			instance = new EntrenamientoService();
		}

		return instance;
	}
}

