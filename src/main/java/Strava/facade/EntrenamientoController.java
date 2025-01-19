package Strava.facade;

import Strava.entity.*;
import Strava.dto.*;
import Strava.service.EntrenamientoService;
import Strava.service.AuthorizationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@RestController
@RequestMapping("/api/entrenamientos")
@Tag(name = "Entrenamientos Controller", description = "Gestión de sesiones de entrenamiento")
public class EntrenamientoController {

    private final EntrenamientoService entrenamientoService;
    private final AuthorizationService authorizationService;
    
	public EntrenamientoController(EntrenamientoService entrenamientoService,
			AuthorizationService authorizationService) {
		this.entrenamientoService = entrenamientoService;
		this.authorizationService = authorizationService;
	}

    // Método para validar el token utilizando AuthorizationService
    private boolean validarToken(String token) {
        return authorizationService.validateToken(token);
    }
    
     //Esta no va
    @Operation(
            summary = "Obtener entrenamientos",
            description = "Retorna la lista de todos los entrenamientos disponibles.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Lista de entrenamientos retornada con éxito"),
                @ApiResponse(responseCode = "401", description = "Token inválido"),
                @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
        )
    @GetMapping
    public ResponseEntity<List<SesionEntrenamientoEntity>> getAllEntrenamientos(
            @Parameter(description = "Token de autorización", required = true) 
            	@RequestParam("Token") String token) {
        if (!validarToken(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(entrenamientoService.getAllEntrenamientos(), HttpStatus.OK);
    }

    @Operation(
            summary = "Crear un entrenamiento",
            description = "Permite crear una nueva sesión de entrenamiento.",
            responses = {
                @ApiResponse(responseCode = "201", description = "Entrenamiento creado con éxito"),
                @ApiResponse(responseCode = "401", description = "Token inválido"),
                @ApiResponse(responseCode = "400", description = "Datos del entrenamiento inválidos"),
                @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
        )
    @PostMapping("/crear")
    public ResponseEntity<String> crearEntrenamiento(
    		@Parameter(description = "Token de autorización", required = true) @RequestParam("Token") String token,
            @Parameter(description = "Título del entrenamiento", required = true) @RequestParam("Titulo") String titulo,
            @Parameter(description = "Deporte (ciclismo,running)", required = true) @RequestParam("Deporte") String deporte,
            @Parameter(description = "Distancia(km)", required = true) @RequestParam("Distancia") int distancia,
            @Parameter(description = "Fecha de inicio del entrenamiento", required = true) @RequestParam("Fecha de Inicio") LocalDate fechaInicio,
            @Parameter(description = "Duración del entrenamiento (en minutos)", required = true) @RequestParam("Duracion") int duracion) {
        if (!validarToken(token)) {
            return new ResponseEntity<>("Token inválido", HttpStatus.UNAUTHORIZED);
        }

        try {
            // Placeholder para obtener el usuario actual
            UsuarioEntity usuario = authorizationService.getUsuarioFromToken(token);

            // Aquí utilizamos los parámetros recibidos para crear el entrenamiento
           if(entrenamientoService.crearEntrenamiento(
                    usuario,
                    titulo,
                    Deporte.fromString(deporte),
                    distancia,
                    fechaInicio,
                    LocalTime.now(),
                    duracion
            )) {
               return new ResponseEntity<>("Entrenamiento creado exitosamente", HttpStatus.CREATED);
           } else {
              return new ResponseEntity<>("El entrenamiento ya existe", HttpStatus.BAD_REQUEST);
           }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Datos de entrenamiento inválidos: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RemoteException e) {
            return new ResponseEntity<>("Error remoto al crear el entrenamiento", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Esta si
    @Operation(
            summary = "Listar entrenamientos",
            description = "Retorna una lista de entrenamientos en formato DTO.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Lista de entrenamientos retornada con éxito"),
                @ApiResponse(responseCode = "401", description = "Token inválido"),
                @ApiResponse(responseCode = "204", description = "No hay entrenamientos disponibles"),
                @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
        )
    @GetMapping("/listar")
    public ResponseEntity<List<SesionEntrenamientoDTO>> getEntrenamientos(
            @Parameter(description = "Token de autorización", required = true) 
            @RequestParam("Token") String token) {
        if (!validarToken(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            // Obtener la lista de entidades de entrenamientos desde el servicio
            UsuarioEntity usuario = authorizationService.getUsuarioFromToken(token);
        	List<SesionEntrenamientoEntity> entrenamientos = entrenamientoService.consultarUltimosEntrenamientos(usuario);

            // Convertir entidades a DTOs
            List<SesionEntrenamientoDTO> entrenamientosDTO = entrenamientos.stream()
                    .map(AssemblerMethods::toDTO) // Convierte cada entidad a un DTO
                    .toList();

            if (!entrenamientosDTO.isEmpty()) {
                return new ResponseEntity<>(entrenamientosDTO, HttpStatus.OK); // Devolver los DTOs
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No hay entrenamientos
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Error interno
        }
    }
}


