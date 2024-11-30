package Strava.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Strava.dao.RetoRepository;
import Strava.dao.UserRepository;
import Strava.entity.*;

@Service
public class RetoService {

	//private List<RetoEntity> retos = new ArrayList<>();
	@Autowired
	private RetoRepository retoRepository;
	
	@Autowired
	private UserRepository userRepository;

	private RetoService() {
	}

	//Crear reto
	public boolean crearReto(UsuarioEntity usuario, String nombre, LocalDate fechaInicio, LocalDate fechaFin,
			int objetivo, Deporte deporteReto) {
		RetoEntity reto = new RetoEntity();
		reto.setNombre(nombre);
		reto.setFechaInicio(fechaInicio);
		reto.setFechaFin(fechaFin);
		reto.setObjetivo(objetivo);
		reto.setDeporte(deporteReto);
		usuario.aceptarReto(reto);
		return addReto(reto); //False si ya existe el reto
	}
	
	//Obtener retos activos
	public List<RetoEntity> getRetosActivos(UsuarioEntity usuario) {
	    return retoRepository.findRetosActivos(LocalDate.now());
	}
	
	//Obtener Retos Aceptados
	public List<RetoEntity> getRetosAceptados(UsuarioEntity usuario) { 
		Optional<UsuarioEntity> OptUser = userRepository.findByEmail(usuario.getEmail());
		UsuarioEntity user = OptUser.get();
        Hibernate.initialize(user.getRetosAceptados());
        //Se queda aqui
        return user.getRetosAceptados();
	}
	
	//Aceptar reto
	public void aceptarReto(UsuarioEntity usuario, RetoEntity reto) {
		if (retoRepository.findAll().contains(reto)) {
			for (RetoEntity retoBD : retoRepository.findAll()) {
				if (retoBD.equals(reto)) {
					UsuarioEntity usuarioBD = userRepository.findById(usuario.getEmail()).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
					usuarioBD.aceptarReto(retoBD);
					retoRepository.save(retoBD);
					userRepository.save(usuarioBD);
				}
			}
		}		
	}
	
	//Añadir Reto a Repositorio
	public boolean addReto(RetoEntity reto) {
	    for (RetoEntity r : retoRepository.findAll()) {
	        if (r.retoRepetido(reto)) {
	            return false; // El reto ya existe, no se guarda
	        }
	    }
	    retoRepository.save(reto); // Guardamos el reto si no hay duplicados
	    return true;
	}
}
