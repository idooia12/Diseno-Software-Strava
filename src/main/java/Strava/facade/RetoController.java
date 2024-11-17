package Strava.facade;


import Strava.dto.RetoDTO;
import Strava.entity.Deporte;
import Strava.entity.RetoEntity;
import Strava.entity.UsuarioEntity;
import Strava.service.AuthorizationService;
import Strava.service.RetoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/retos")
public class RetoController {

    private final RetoService retoService = RetoService.getInstance();
    private final AuthorizationService authorizationService = AuthorizationService.getInstance();

    // Método para validar el token utilizando AuthorizationService
    private boolean validarToken(String token) {
        return authorizationService.validateToken(token);
    }
    


    @PostMapping("/crear")
    public ResponseEntity<String> crearReto(
    		@RequestParam("Authorization") String token,
    		@RequestParam("nombre") String nombre,
    		@RequestParam("fecha Inicio") LocalDate fechaInicio,
    		@RequestParam("fecha Fin") LocalDate fechaFin,
    		@RequestParam ("Objetivo")int Objetivo,
    		@RequestParam("deporte") Deporte deporte) {
        try {
            // Validar el token recibido y obtener el usuario
            UsuarioEntity usuario = authorizationService.getUsuarioFromToken(token);
            if (usuario == null) {
                // Si el token es inválido, devolver 401 Unauthorized
                return new ResponseEntity<>("Token inválido", HttpStatus.UNAUTHORIZED);
            }

            // Crear el reto usando los datos recibidos
            retoService.crearReto(
                    usuario,
                    nombre,
                    fechaInicio,   //HAY QUE MIRAR LO DE LOS DEPORTES AQUI
                    fechaFin,
                    Objetivo,
                    deporte
                     // Aquí iría el deporte si se requiere
            );

            return new ResponseEntity<>("Reto creado exitosamente", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Datos del reto inválidos: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/activos")
    public ResponseEntity<List<RetoDTO>> obtenerRetosActivos(
    		@RequestParam("Authorization") String token) {
        try {
            // Validar el token recibido y obtener el usuario
            UsuarioEntity usuario = authorizationService.getUsuarioFromToken(token);
            if (usuario == null) {
                // Si el token es inválido, devolver 401 Unauthorized
               // return new ResponseEntity<>("Token inválido", HttpStatus.UNAUTHORIZED);
            }
            // Obtener los retos activos del usuario
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


    @PostMapping("/aceptar/{retoNombre}")
    public ResponseEntity<String> aceptarReto(
            @PathVariable String retoNombre,
            @RequestParam("Authorization") String token) {
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



    @GetMapping("/aceptados")
    public ResponseEntity<List<RetoDTO>> consultarRetosAceptados(
    		@RequestParam("Authorization") String token) {
        try {
            // Validar el token recibido
            UsuarioEntity usuario = authorizationService.getUsuarioFromToken(token);
            if (!validarToken(token)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            // Obtener los retos aceptados para el usuario autenticado
            List<RetoEntity> retosAceptados = retoService.consultarRetosAceptados(usuario);
            if (retosAceptados.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            // Convertir los retos a DTOs
            List<RetoDTO> dtos = new ArrayList<>();
            for (RetoEntity reto : retosAceptados) {
                dtos.add(convertToDTO(reto));
            }

            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


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
    
    
    @GetMapping("/prueba")
    public ResponseEntity<String> testEndpoint() {
        return new ResponseEntity<>("Funcionando correctamente", HttpStatus.OK);
    }
}
