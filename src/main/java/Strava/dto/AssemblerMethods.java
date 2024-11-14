package Strava.dto;

import Strava.entity.*;


public class AssemblerMethods {
	//Convierte RetoEntity en RetoDTO
	 public static RetoDTO retoToDTO(RetoEntity retoEntity) {
	        if (retoEntity == null) {
	            return null;
	        }      
	        RetoDTO retoDTO = new RetoDTO();
	        retoDTO.setNombre(retoEntity.getNombre());
	        retoDTO.setFechaInicio(retoEntity.getFechaInicio());
	        retoDTO.setFechaFin(retoEntity.getFechaFin());
	        retoDTO.setObjetivo(retoEntity.getObjetivo());
	        retoDTO.setDeporte(retoEntity.getDeporte());
	        retoDTO.setTipoReto(retoEntity.getTipoReto());
	        
	        return retoDTO;
	    }
	 // Convierte RetoDTO a RetoEntity
		public static RetoEntity toEntity(RetoDTO retoDTO) {
			if (retoDTO == null) {
				return null;
			}
			RetoEntity retoEntity = new RetoEntity();
			retoEntity.setNombre(retoDTO.getNombre());
			retoEntity.setFechaInicio(retoDTO.getFechaInicio());
			retoEntity.setFechaFin(retoDTO.getFechaFin());
			retoEntity.setObjetivo(retoDTO.getObjetivo());
			retoEntity.setDeporte(retoDTO.getDeporte());
			retoEntity.setTipoReto(retoDTO.getTipoReto());
			return retoEntity;
	    }

		// Convierte UsuarioEntity en UsuarioDTO
		public static UsuarioDTO usuarioToDTO(UsuarioEntity usuarioEntity) {
			if (usuarioEntity == null) {
				return null;
			}
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setEmail(usuarioEntity.getEmail());
			usuarioDTO.setNombre(usuarioEntity.getNombre());
			usuarioDTO.setFechaNacimiento(usuarioEntity.getFechaNacimiento());
			usuarioDTO.setPeso(usuarioEntity.getPeso());
			usuarioDTO.setAltura(usuarioEntity.getAltura());
			usuarioDTO.setFCmax_ppm(usuarioEntity.getFCmax_ppm());
			usuarioDTO.setFCrep_ppm(usuarioEntity.getFCrep_ppm());
			usuarioDTO.setRetosCreados(usuarioEntity.getRetosCreados());
			usuarioDTO.setRetosAceptados(usuarioEntity.getRetosAceptados());
			usuarioDTO.setRetosActivos(usuarioEntity.getRetosActivos());
			usuarioDTO.setEntrenamientos(usuarioEntity.getEntrenamientos());
			usuarioDTO.setContraseña(usuarioEntity.getContraseña());
			usuarioDTO.setServicio(usuarioEntity.getServicio());
			return usuarioDTO;
		}
		//Convierte UsuarioEntity a UsuarioDTO
		public static UsuarioDTO toDTO(UsuarioEntity usuarioEntity) {
			if (usuarioEntity == null) {
				return null;
			}
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setEmail(usuarioEntity.getEmail());
			usuarioDTO.setNombre(usuarioEntity.getNombre());
			usuarioDTO.setFechaNacimiento(usuarioEntity.getFechaNacimiento());
			usuarioDTO.setPeso(usuarioEntity.getPeso());
			usuarioDTO.setAltura(usuarioEntity.getAltura());
			usuarioDTO.setFCmax_ppm(usuarioEntity.getFCmax_ppm());
			usuarioDTO.setFCrep_ppm(usuarioEntity.getFCrep_ppm());
			usuarioDTO.setRetosCreados(usuarioEntity.getRetosCreados());
			usuarioDTO.setRetosAceptados(usuarioEntity.getRetosAceptados());
			usuarioDTO.setRetosActivos(usuarioEntity.getRetosActivos());
			usuarioDTO.setEntrenamientos(usuarioEntity.getEntrenamientos());
			usuarioDTO.setContraseña(usuarioEntity.getContraseña());
			usuarioDTO.setServicio(usuarioEntity.getServicio());
			return usuarioDTO;
		}
		
		// Convierte UsuarioDTO a UsuarioEntity
		public static UsuarioEntity toEntity(UsuarioDTO usuarioDTO) {
			if (usuarioDTO == null) {
				return null;
			}
			UsuarioEntity usuarioEntity = new UsuarioEntity();
			usuarioEntity.setEmail(usuarioDTO.getEmail());
			usuarioEntity.setNombre(usuarioDTO.getNombre());
			usuarioEntity.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
			usuarioEntity.setPeso(usuarioDTO.getPeso());
			usuarioEntity.setAltura(usuarioDTO.getAltura());
			usuarioEntity.setFCmax_ppm(usuarioDTO.getFCmax_ppm());
			usuarioEntity.setFCrep_ppm(usuarioDTO.getFCrep_ppm());
			usuarioEntity.setRetosCreados(usuarioDTO.getRetosCreados());
			usuarioEntity.setRetosAceptados(usuarioDTO.getRetosAceptados());
			usuarioEntity.setRetosActivos(usuarioDTO.getRetosActivos());
			usuarioEntity.setEntrenamientos(usuarioDTO.getEntrenamientos());
			return usuarioEntity;
		}
		
		// Convierte SesionEntrenamientoEntity a SesionEntrenamientoDTO
		public static SesionEntrenamientoDTO toDTO(SesionEntrenamientoEntity sesionEntrenamientoEntity) {
			if (sesionEntrenamientoEntity == null) {
				return null;
			}
			SesionEntrenamientoDTO sesionEntrenamientoDTO = new SesionEntrenamientoDTO();
			sesionEntrenamientoDTO.setTitulo(sesionEntrenamientoEntity.getTitulo());
			sesionEntrenamientoDTO.setDeporte(sesionEntrenamientoEntity.getDeporte());
			sesionEntrenamientoDTO.setDistanciaKm(sesionEntrenamientoEntity.getDistanciaKm());
			sesionEntrenamientoDTO.setFechaInicio(sesionEntrenamientoEntity.getFecha_inicio());
			sesionEntrenamientoDTO.setHoraInicio(sesionEntrenamientoEntity.getFecha_inicio());
			sesionEntrenamientoDTO.setDuracion(sesionEntrenamientoEntity.getDuracion());
			return sesionEntrenamientoDTO;
		}
		
		// Convierte SesionEntrenamientoDTO a SesionEntrenamientoEntity
		public static SesionEntrenamientoEntity toEntity(SesionEntrenamientoDTO sesionEntrenamientoDTO) {
			if (sesionEntrenamientoDTO == null) {
				return null;
			}
			SesionEntrenamientoEntity sesionEntrenamientoEntity = new SesionEntrenamientoEntity();
			sesionEntrenamientoEntity.setTitulo(sesionEntrenamientoDTO.getTitulo());
			sesionEntrenamientoEntity.setDeporte(sesionEntrenamientoDTO.getDeporte());
			sesionEntrenamientoEntity.setDistanciaKm(sesionEntrenamientoDTO.getDistanciaKm());
			sesionEntrenamientoEntity.setFecha_inicio(sesionEntrenamientoDTO.getFechaInicio());
			sesionEntrenamientoEntity.setHora_inicio(sesionEntrenamientoDTO.getFechaInicio());
			sesionEntrenamientoEntity.setDuracion(sesionEntrenamientoDTO.getDuracion());
			return sesionEntrenamientoEntity;
		}
		
	
	
}


