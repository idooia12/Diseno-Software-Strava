package Strava.facade;


import Strava.dto.AssemblerMethods;
import Strava.dto.RetoDTO;
import Strava.entity.Deporte;
import Strava.entity.RetoEntity;
import Strava.entity.UsuarioEntity;
import Strava.service.AuthorizationService;
import Strava.service.RetoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/retos")
@Tag(name = "Retos Controller", description = "Gestión de retos deportivos")
public class RetoController {

    private final RetoService retoService;
    private final AuthorizationService authorizationService;
    
	public RetoController(RetoService retoService, AuthorizationService authorizationService) {
		this.retoService = retoService;
		this.authorizationService = authorizationService;
	}

    // Método para validar el token utilizando AuthorizationService
    private boolean validarToken(String token) {
        return authorizationService.validateToken(token);
    }
    


    @Operation(
            summary = "Crear un reto",
            description = "Permite crear un nuevo reto deportivo.",
            responses = {
                @ApiResponse(responseCode = "201", description = "Reto creado con éxito"),
                @ApiResponse(responseCode = "401", description = "Token inválido"),
                @ApiResponse(responseCode = "409", description = "El reto ya existe"),
                @ApiResponse(responseCode = "400", description = "Datos del reto inválidos"),
                @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
        )
    @PostMapping("/crear")
    public ResponseEntity<String> crearReto(
    		@Parameter(description = "Token de autorización", required = true) @RequestParam("Token") String token,
            @Parameter(description = "Nombre del reto", required = true) @RequestParam("Nombre") String nombre,
            @Parameter(description = "Fecha de inicio del reto", required = true) @RequestParam("Fecha de Inicio") LocalDate fechaInicio,
            @Parameter(description = "Fecha de fin del reto", required = true) @RequestParam("Fecha de Fin") LocalDate fechaFin,
            @Parameter(description = "Objetivo del reto (km/minutos)", required = true) @RequestParam("Objetivo del Reto") int Objetivo,
            @Parameter(description = "Deporte del reto (ciclismo, running, others)", required = true) @RequestParam("Deporte") String deporte) {
        try {
            // Validar el token recibido y obtener el usuario
            UsuarioEntity usuario = authorizationService.getUsuarioFromToken(token);
            if (usuario == null) {
                // Si el token es inválido, devolver 401 Unauthorized
                return new ResponseEntity<>("Token inválido", HttpStatus.UNAUTHORIZED);
            }

            // Crear el reto usando los datos recibidos
            if(retoService.crearReto(
                    usuario,
                    nombre,
                    fechaInicio,   
                    fechaFin,
                    Objetivo,
                    Deporte.fromString(deporte)
            )) {
                return new ResponseEntity<>("Reto creado exitosamente", HttpStatus.CREATED);
            } else {
               return new ResponseEntity<>("El reto ya existe", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Datos del reto inválidos: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(
            summary = "Obtener retos activos",
            description = "Retorna la lista de retos activos asociados al usuario en la fecha actual.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Lista de retos activos retornada con éxito"),
                @ApiResponse(responseCode = "401", description = "Token inválido"),
                @ApiResponse(responseCode = "204", description = "No hay retos activos"),
                @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
        )
    @GetMapping("/activos")
    public ResponseEntity<List<RetoDTO>> obtenerRetosActivos(
            @Parameter(description = "Token de autorización", required = true) @RequestParam("Token") String token) {
    	 if (!validarToken(token)) {
             return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
    	 try {
    		UsuarioEntity usuario = authorizationService.getUsuarioFromToken(token);
            List<RetoEntity> retosActivos = retoService.getRetosActivos(usuario);
            if (retosActivos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            // Convertir los retos activos a DTOs
            List<RetoDTO> dtos = new ArrayList<>();
            for (RetoEntity reto : retosActivos) {
                dtos.add(convertToDTO(reto));
            }
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Aceptar un reto",
            description = "Permite que un usuario acepte un reto especificado por su nombre.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Reto aceptado exitosamente"),
                @ApiResponse(responseCode = "401", description = "Token inválido"),
                @ApiResponse(responseCode = "404", description = "Reto no encontrado"),
                @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
        )
    @PostMapping("/aceptar/{retoNombre}")
    public ResponseEntity<String> aceptarReto(
    		 @Parameter(description = "Nombre del reto a aceptar", required = true, example = "Reto Maraton")
             	@PathVariable String retoNombre,
             @Parameter(description = "Token de autorización del usuario", required = true)
             	@RequestParam("Token") String token) {
        try {
            // Validar el token recibido y obtener el usuario
            UsuarioEntity usuario = authorizationService.getUsuarioFromToken(token);
            if (usuario == null) {
                // Si el token es inválido, devolver 401 Unauthorized
                return new ResponseEntity<>("Token inválido", HttpStatus.UNAUTHORIZED);
            }

            // Buscar el reto por nombre
            RetoEntity reto = buscarRetoPorNombre(retoNombre);
            if (reto == null) {
                return new ResponseEntity<>("Reto no encontrado", HttpStatus.NOT_FOUND);
            }

            // Llamar al servicio para aceptar el reto
            retoService.aceptarReto(usuario, reto);
            return new ResponseEntity<>("Reto aceptado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(
            summary = "Consultar retos aceptados",
            description = "Obtiene la lista de retos que un usuario ha aceptado.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Lista de retos aceptados retornada con éxito"),
                @ApiResponse(responseCode = "401", description = "Token inválido"),
                @ApiResponse(responseCode = "204", description = "No hay retos aceptados"),
                @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
        )
    @GetMapping("/aceptados")
    public ResponseEntity<List<RetoDTO>> consultarRetosAceptados(
    		 @Parameter(description = "Token de autorización del usuario", required = true)
             @RequestParam("Token") String token){    
    	 
    	try {
    		if (!validarToken(token)) {
    			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    		}
           UsuarioEntity usuario = authorizationService.getUsuarioFromToken(token);
           List<RetoEntity> retosAceptados = retoService.getRetosAceptados(usuario);
           if (retosAceptados.isEmpty()) {
               return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           }
           List<RetoDTO> dtos = retosAceptados.stream()
                   .map(AssemblerMethods::retoToDTO)
                   .collect(Collectors.toList());
           System.out.println("Retos aceptados convertidos a DTO");
            return new ResponseEntity<>(dtos, HttpStatus.OK);
    	 } catch (EntityNotFoundException e) {
    	        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Métodos auxiliares

    private RetoDTO convertToDTO(RetoEntity reto) {
        RetoDTO dto = new RetoDTO();
        dto.setNombre(reto.getNombre());
        dto.setFechaInicio(reto.getFechaInicio());
        dto.setFechaFin(reto.getFechaFin());
        dto.setObjetivo(reto.getObjetivo());
        //HABRIA QUE MEJORARLO
        return dto;
    }

    private RetoEntity buscarRetoPorNombre(String nombre) {
        for (RetoEntity reto : retoService.getRetosActivos(new UsuarioEntity())) {
            if (reto.getNombre().equalsIgnoreCase(nombre)) {
                return reto;
            }
        }
        return null;
    }
    
    
    /*@GetMapping("/prueba")
    public ResponseEntity<String> testEndpoint() {
        return new ResponseEntity<>("Funcionando correctamente", HttpStatus.OK);
    }*/
}
