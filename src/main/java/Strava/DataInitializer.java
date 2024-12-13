package Strava;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Strava.entity.*;
import Strava.service.*;
import Strava.dao.*;

@Configuration
public class DataInitializer {
  
	private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
	@Bean
	 CommandLineRunner initData(RetoService retoService, RetoRepository retoRepository, EntrenamientoService entrenamientoService, AuthorizationService autorizacionService) {
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
       RetoEntity reto1 = new RetoEntity("Reto Ciclismo de Resistencia",LocalDate.of(2024,12,10),LocalDate.of(2025,02,20), 200, Deporte.ciclismo, TipoDeReto.DISTANCIA);
       RetoEntity reto2 = new RetoEntity("Reto Running Semanal",LocalDate.of(2024,11,10),LocalDate.of(2024,11,17), 50, Deporte.running, TipoDeReto.TIEMPO);
       RetoEntity reto3 = new RetoEntity("Maratón Virtual",LocalDate.of(2024,12,10),LocalDate.of(2025,04,25), 42, Deporte.running, TipoDeReto.DISTANCIA);
       RetoEntity reto4 = new RetoEntity("Reto Inicio Ano",LocalDate.of(2025,1,1),LocalDate.of(2025,1,31), 150, Deporte.ciclismo, TipoDeReto.TIEMPO);
       RetoEntity reto5 = new RetoEntity("Desafío Trail Running", LocalDate.of(2024,12,15), LocalDate.of(2025,01,15), 60, Deporte.running, TipoDeReto.DISTANCIA);
       RetoEntity reto6 = new RetoEntity("Reto Cicloturismo", LocalDate.of(2025,03,01), LocalDate.of(2025,03,31), 300, Deporte.ciclismo, TipoDeReto.DISTANCIA);
       RetoEntity reto7 = new RetoEntity("Sprint de Invierno", LocalDate.of(2025,01,10), LocalDate.of(2025,01,20), 20, Deporte.running, TipoDeReto.TIEMPO);
       RetoEntity reto8 = new RetoEntity("Subida a la Montaña", LocalDate.of(2025,02,01), LocalDate.of(2025,02,28), 100, Deporte.ciclismo, TipoDeReto.TIEMPO);
       RetoEntity reto9 = new RetoEntity("Carrera 10K Virtual", LocalDate.of(2024,12,20), LocalDate.of(2024,12,25), 10, Deporte.running, TipoDeReto.DISTANCIA);

       retoService.addAllRetos(List.of(reto1, reto2, reto3, reto4, reto5, reto6, reto7, reto8, reto9));
       logger.info("Retos guardados!");

       //Crear sesiones de entrenamiento
       entrenamientoService.crearEntrenamiento(usuario1, "Entrenamiento Matutino", Deporte.running, 10, LocalDate.of(2024,12,25), LocalTime.of(12,14), 60);
       entrenamientoService.crearEntrenamiento(usuario2, "Ruta de Montaña", Deporte.ciclismo, 25, LocalDate.of(2024,12,13), LocalTime.of(10,10), 120);
       entrenamientoService.crearEntrenamiento(usuario3,"Entrenamiento Nocturno", Deporte.running, 5, LocalDate.of(2025,1,13), LocalTime.of(17,40), 45);
       entrenamientoService.crearEntrenamiento(usuario1,"Ruta de Resistencia", Deporte.ciclismo, 40, LocalDate.of(2025,1,1), LocalTime.of(12,05), 180);
       entrenamientoService.crearEntrenamiento(usuario2,"Entrenamiento de Velocidad", Deporte.running, 8, LocalDate.of(2025,1,5), LocalTime.of(7,30), 50);
       entrenamientoService.crearEntrenamiento(usuario1, "Entrenamiento Express", Deporte.running, 6, LocalDate.of(2024,12,15), LocalTime.of(8,30), 30);
       entrenamientoService.crearEntrenamiento(usuario3, "Circuito de Fondo", Deporte.ciclismo, 50, LocalDate.of(2025,1,10), LocalTime.of(9,45), 150);
       entrenamientoService.crearEntrenamiento(usuario2, "Sesión de Resistencia", Deporte.running, 12, LocalDate.of(2025,1,18), LocalTime.of(6,15), 90);

       logger.info("Entrenamientos guardados!");
       
        };  
}
}
