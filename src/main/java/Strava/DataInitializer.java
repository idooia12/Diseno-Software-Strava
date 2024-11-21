package Strava;

import java.time.LocalDate;
import java.time.LocalTime;
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
       UsuarioEntity usuario1 = new UsuarioEntity("Manu@deusto.es", "Manu", "pass", LocalDate.of(1990, 11, 17),65, 180, 190, 120, ServicioValidacion.Google);
       UsuarioEntity usuario2 = new UsuarioEntity("Ana@deusto.es", "Ana", "pass",LocalDate.of(2002, 1, 20), 55, 165, 175, 110, ServicioValidacion.Meta);
       UsuarioEntity usuario3 = new UsuarioEntity("Carlos@deusto.es", "Carlos", "pass", LocalDate.of(2024, 3, 15), 80, 175, 185, 125, ServicioValidacion.Google);
       autorizacionService.addUsuario(usuario1);
       autorizacionService.addUsuario(usuario2);
       autorizacionService.addUsuario(usuario3);
       logger.info("Usuarios guardados!");
       
       // Crear retos
       RetoEntity reto1 = new RetoEntity("Reto Ciclismo de Resistencia", usuario1 ,LocalDate.now(),LocalDate.now().plusDays(5), 200, Deporte.ciclismo, TipoDeReto.DISTANCIA);
       RetoEntity reto2 = new RetoEntity("Reto Running Semanal", usuario2,LocalDate.now().minusDays(5),LocalDate.now().minusDays(1), 50, Deporte.running, TipoDeReto.TIEMPO);
       RetoEntity reto3 = new RetoEntity("Maratón Virtual", usuario1,LocalDate.now().minusDays(3),LocalDate.now().plusDays(7), 42, Deporte.running, TipoDeReto.DISTANCIA);
       RetoEntity reto4 = new RetoEntity("Reto MTB", usuario2,LocalDate.now().plusDays(5),LocalDate.now().plusDays(40), 150, Deporte.ciclismo, TipoDeReto.TIEMPO);
       
       logger.info("Retos guardados!");

       //Crear sesiones de entrenamiento
       entrenamientoService.crearEntrenamiento(usuario1, "Entrenamiento Matutino", Deporte.running, 10,LocalDate.now().minusDays(5), LocalTime.now(), 60);
       entrenamientoService.crearEntrenamiento(usuario2, "Ruta de Montaña", Deporte.ciclismo, 25, LocalDate.now(), LocalTime.now(), 120);
       entrenamientoService.crearEntrenamiento(usuario3,"Entrenamiento Nocturno", Deporte.running, 5, LocalDate.now().minusDays(1), LocalTime.now(), 45);
       entrenamientoService.crearEntrenamiento(usuario1,"Ruta de Resistencia", Deporte.ciclismo, 40, LocalDate.now().minusDays(2), LocalTime.now(), 180);
       entrenamientoService.crearEntrenamiento(usuario2,"Entrenamiento de Velocidad", Deporte.running, 8, LocalDate.now().minusDays(3), LocalTime.now(), 50);
       logger.info("Entrenamientos guardados!");
       
        };
        
       
        
        
}
}
