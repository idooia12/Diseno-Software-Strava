package Strava.dto;

import Strava.entity.RetoEntity;

//Convierte RetoEntity en RetoDTO
public class RetoAssembler {
	 public static RetoDTO entityToDTO(RetoEntity retoEntity) {
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
}
