package Strava.facade;


import Strava.dto.RetoDTO;
import Strava.entity.Deporte;
import Strava.entity.RetoEntity;
import Strava.entity.UsuarioEntity;
import Strava.service.RetoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/retos")
public class RetoController {

    private final RetoService retoService = RetoService.getInstance();


    @PostMapping("/crear")
    public ResponseEntity<String> crearReto(@RequestBody RetoDTO retoDTO) {
        try {
            // Placeholder para obtener el usuario actual
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setEmail("usuario@ejemplo.com");

            retoService.crearReto(
                usuario,
                retoDTO.getNombre(),
                retoDTO.getFechaInicio(),
                retoDTO.getFechaFin(),
                retoDTO.getObjetivo(),
                null //Habria que poner deporte
            );

            return new ResponseEntity<>("Reto creado exitosamente", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Datos del reto inválidos: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/activos")
    public ResponseEntity<List<RetoDTO>> obtenerRetosActivos() {
        try {
            // Placeholder para obtener el usuario actual
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setEmail("usuario@ejemplo.com");

            List<RetoEntity> retosActivos = retoService.getRetosActivos(usuario);
            if (retosActivos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

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
    public ResponseEntity<String> aceptarReto(@PathVariable String retoNombre) {
        try {
            // Placeholder para obtener el usuario actual
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setEmail("usuario@ejemplo.com");

            RetoEntity reto = buscarRetoPorNombre(retoNombre);
            if (reto == null) {
                return new ResponseEntity<>("Reto no encontrado", HttpStatus.NOT_FOUND);
            }

            retoService.aceptarReto(usuario, reto);
            return new ResponseEntity<>("Reto aceptado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/aceptados")
    public ResponseEntity<List<RetoDTO>> consultarRetosAceptados() {
        try {
            // Placeholder para obtener el usuario actual
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setEmail("usuario@ejemplo.com");

            List<RetoEntity> retosAceptados = retoService.consultarRetosAceptados(usuario);
            if (retosAceptados.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

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
}
