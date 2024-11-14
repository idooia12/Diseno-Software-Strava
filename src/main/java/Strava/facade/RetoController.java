package Strava.facade;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Strava.entity.Deporte;
import Strava.entity.RetoEntity;
import Strava.entity.UsuarioEntity;
import Strava.service.RetoService;
import Strava.dto.RetoDTO;

public class RetoController {

    private final RetoService retoService;

    public RetoController(RetoService retoService) {
        this.retoService = retoService;
    }

    // Método para crear un reto
    public String crearReto(UsuarioEntity usuario, String nombre, Date fechaInicio, Date fechaFin,
                             int objetivo, Deporte deporteReto) {
        try {
            retoService.crearReto(usuario, nombre, fechaInicio, fechaFin, objetivo, deporteReto);
            return "Reto creado exitosamente.";
        } catch (Exception e) {
            return "Error al crear el reto: " + e.getMessage();
        }
    }

    // Método para obtener todos los retos activos de un usuario
    public List<RetoDTO> getRetosActivos(UsuarioEntity usuario) {
        List<RetoDTO> retosActivosDTO = new ArrayList<>();
        try {
            List<RetoEntity> retosActivos = retoService.getRetosActivos(usuario);
            for (RetoEntity reto : retosActivos) {
                retosActivosDTO.add(retoToDTO(reto));
            }
            return retosActivosDTO;
        } catch (Exception e) {
            System.out.println("Error al obtener los retos activos: " + e.getMessage());
            return retosActivosDTO;
        }
    }

    // Método para aceptar un reto
    public String aceptarReto(UsuarioEntity usuario, RetoEntity reto) {
        try {
            retoService.aceptarReto(usuario, reto);
            return "Reto aceptado exitosamente.";
        } catch (Exception e) {
            return "Error al aceptar el reto: " + e.getMessage();
        }
    }

    // Método para consultar retos aceptados de un usuario
    public List<RetoDTO> consultarRetosAceptados(UsuarioEntity usuario) {
        List<RetoDTO> retosAceptadosDTO = new ArrayList<>();
        try {
            List<RetoEntity> retosAceptados = retoService.consultarRetosAceptados(usuario);
            for (RetoEntity reto : retosAceptados) {
                retosAceptadosDTO.add(retoToDTO(reto));
            }
            return retosAceptadosDTO;
        } catch (Exception e) {
            System.out.println("Error al consultar los retos aceptados: " + e.getMessage());
            return retosAceptadosDTO;
        }
    }

    // Método auxiliar para convertir un RetoEntity a RetoDTO
    private RetoDTO retoToDTO(RetoEntity reto) {
        return new RetoDTO(null, null, null, null, 0, null, null );
    }
}


