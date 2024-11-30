package Strava.service;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import Strava.dao.EntrenamientoRepository;
import Strava.entity.Deporte;
import Strava.entity.SesionEntrenamientoEntity;
import Strava.entity.UsuarioEntity;

@Service
public class EntrenamientoService {
	
    @Autowired
    private EntrenamientoRepository entrenamientoRepository;
	
	//Crear entrenamiento y guardarlo en la base de datos
	public boolean crearEntrenamiento(UsuarioEntity usuario, String titulo, Deporte deporte, int distanciaKm, LocalDate fecha_inicio, LocalTime hora_inicio, int duracion) throws RemoteException {
		SesionEntrenamientoEntity sesion = new SesionEntrenamientoEntity(usuario, titulo, deporte, distanciaKm, fecha_inicio, hora_inicio, duracion);
		return addEntrenamiento(sesion); //False si ya existe el entrenamiento
	}
	
	
	//Consultar los últimos 5 entrenamientos
    public List<SesionEntrenamientoEntity> consultarUltimosEntrenamientos(UsuarioEntity usuario) {
        List<SesionEntrenamientoEntity> entrenamientosUsuario = entrenamientoRepository.findByUsuario(usuario);
        // Ordenar por fecha y devolver los últimos 5
        return entrenamientosUsuario.stream()
                .sorted((e1, e2) -> e2.getFechaInicio().compareTo(e1.getFechaInicio())) // Orden descendente por fecha
                .limit(5)
                .toList();
    }

    //Obtener todos los entrenamientos
	public List<SesionEntrenamientoEntity> getAllEntrenamientos() {
		List<SesionEntrenamientoEntity> entrenamientos = entrenamientoRepository.findAll();
		return entrenamientos;
	}
	
	public boolean addEntrenamiento(SesionEntrenamientoEntity sesion) {
		//Añadir entrenamiento a la base de datos si no existe ya (equals)
		if (!entrenamientoRepository.findAll().contains(sesion)) {
			entrenamientoRepository.save(sesion);
			return true;
		}
		return false;		
    }


}

