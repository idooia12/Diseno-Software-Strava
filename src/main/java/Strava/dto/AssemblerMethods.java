package Strava.dto;

import Strava.entity.RetoEntity;

//Convierte RetoEntity en RetoDTO
public class AssemblerMethods {
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
}


