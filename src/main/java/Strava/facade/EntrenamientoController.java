package Strava.facade;

import Strava.entity.Deporte;
import Strava.entity.SesionEntrenamientoEntity;
import Strava.entity.UsuarioEntity;
import Strava.service.EntrenamientoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/entrenamientos")
public class EntrenamientoController {

    private final  EntrenamientoService entrenamientoService; //= EntrenamientoService.getInstance();
    
    public EntrenamientoController (EntrenamientoService entrenamientoService) {
    	        this.entrenamientoService = entrenamientoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearEntrenamiento(
            @RequestParam("titulo") String titulo,
            @RequestParam("deporte") String deporte,
            @RequestParam("fechaInicio") Date fechaInicio,
            @RequestParam("duracion") int duracion) {
        try {
            // Placeholder para obtener el usuario actual
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setEmail("usuario@ejemplo.com");

            // Aquí utilizamos los parámetros recibidos para crear el entrenamiento
            entrenamientoService.crearEntrenamiento(
                usuario,
                titulo,
                deporte,
                fechaInicio,
                duracion
            );

            return new ResponseEntity<>("Sesión de entrenamiento creada exitosamente", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Datos de entrenamiento inválidos: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RemoteException e) {
            return new ResponseEntity<>("Error remoto al crear el entrenamiento", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    // Método GET para obtener todos los entrenamientos
    @GetMapping("/listar")
    public ResponseEntity<List<SesionEntrenamientoEntity>> getEntrenamientos() {
        try {
            // Obtener la lista de entrenamientos del servicio
            List<SesionEntrenamientoEntity> entrenamientos = entrenamientoService.obtenerEntrenamientos();

            if (entrenamientos != null && !entrenamientos.isEmpty()) {
                return new ResponseEntity<>(entrenamientos, HttpStatus.OK);
            } else {
                return null; //new ResponseEntity<>("No hay entrenamientos disponibles", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


