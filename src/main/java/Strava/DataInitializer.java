package Strava;

import java.time.LocalTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Strava.entity.*;
import Strava.service.*;


@Configuration
public class DataInitializer {
  
	private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
	@Bean
	 CommandLineRunner initData(RetoService retoService, EntrenamientoService entrenamientoService, AuthorizationService autorizacionService) {
        return args -> {	
        	
        // Crear usuarios	
       UsuarioEntity usuario1 = new UsuarioEntity("Manu@deusto.es", "Manu", new Date(12/12/1999),65, 180, 190, 120, ServicioValidacion.Google);
       UsuarioEntity usuario2 = new UsuarioEntity("Ana@deusto.es", "Ana", new Date(01/03/2002), 55, 165, 175, 110, ServicioValidacion.Meta);
       UsuarioEntity usuario3 = new UsuarioEntity("Carlos@deusto.es", "Carlos", new Date(30/02/1990), 80, 175, 185, 125, ServicioValidacion.Google);
       logger.info("Usuarios guardados!");
       
       // Crear retos
       RetoEntity reto1 = new RetoEntity("Reto Ciclismo de Resistencia", usuario1, new Date(13/04/2024), new Date(20/03/2024), 200, Deporte.ciclismo, TipoDeReto.DISTANCIA);
       RetoEntity reto2 = new RetoEntity("Reto Running Semanal", usuario2, new Date(02/05/2025), new Date(28/04/2025), 50, Deporte.running, TipoDeReto.TIEMPO);
       RetoEntity reto3 = new RetoEntity("Maratón Virtual", usuario1, new Date(13/11/2024), new Date(13/12/2024), 42, Deporte.running, TipoDeReto.DISTANCIA);
       RetoEntity reto4 = new RetoEntity("Reto MTB", usuario2, new Date(13/10/2024), new Date(27/04/2024), 150, Deporte.ciclismo, TipoDeReto.TIEMPO);
       logger.info("Retos guardados!");

       //Crear sesiones de entrenamiento
       entrenamientoService.crearEntrenamiento(usuario1, "Entrenamiento Matutino", Deporte.running, 10, new Date(13/11/2024), LocalTime.now(), 60);
       entrenamientoService.crearEntrenamiento(usuario2, "Ruta de Montaña", Deporte.ciclismo, 25, new Date(14/11/2024), LocalTime.now(), 120);
       entrenamientoService.crearEntrenamiento(usuario3,"Entrenamiento Nocturno", Deporte.running, 5, new Date(15/11/2024), LocalTime.now(), 45);
       entrenamientoService.crearEntrenamiento(usuario1,"Ruta de Resistencia", Deporte.ciclismo, 40, new Date(16/11/2024), LocalTime.now(), 180);
       entrenamientoService.crearEntrenamiento(usuario2,"Entrenamiento de Velocidad", Deporte.running, 8, new Date(17/11/2024), LocalTime.now(), 50);
       logger.info("Entrenamientos guardados!");
       
        };
        
       
        
        
}
}
