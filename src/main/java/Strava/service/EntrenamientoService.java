package Strava.service;

import java.rmi.RemoteException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import Strava.entity.Deporte;
import Strava.entity.SesionEntrenamientoEntity;
import Strava.entity.UsuarioEntity;

@Service
public class EntrenamientoService {
	
    private List<SesionEntrenamientoEntity> entrenamientos = new ArrayList<>();

	
	
	public SesionEntrenamientoEntity crearEntrenamiento(UsuarioEntity usuario, String titulo, Deporte deporte, int distanciaKm, LocalDate fecha_inicio, LocalTime hora_inicio, int duracion) throws RemoteException {
		SesionEntrenamientoEntity sesion = new SesionEntrenamientoEntity(usuario, titulo, deporte, distanciaKm, fecha_inicio, hora_inicio, duracion);
	    entrenamientos.add(sesion);
	    usuario.addEntrenamiento(sesion);
	    return sesion;
	}
	
	//Por defecto muestra los últimos 5 entrenamientos
	public List<SesionEntrenamientoEntity> consultarUltimosEntrenamientos(UsuarioEntity usuario) {
		List<SesionEntrenamientoEntity> ultimosEntrenamientos = new ArrayList<>();
	    for (int i = entrenamientos.size() - 1; i >= 0; i--) {
	        if (entrenamientos.get(i).getUsuario().equals(usuario)) {
	            ultimosEntrenamientos.add(entrenamientos.get(i));
	            if (ultimosEntrenamientos.size() == 5) {
	                break;
	            }
	        }
	    }
		return ultimosEntrenamientos;
	}


	public List<SesionEntrenamientoEntity> getAllEntrenamientos() {
		return entrenamientos;
	}
	
	public void addEntrenamiento(SesionEntrenamientoEntity sesion) {
        entrenamientos.add(sesion);
    }


}

