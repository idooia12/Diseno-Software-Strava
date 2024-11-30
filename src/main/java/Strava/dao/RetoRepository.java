package Strava.dao;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Strava.entity.*;

@Repository
public interface RetoRepository extends JpaRepository<RetoEntity, Long> {

	@Query("SELECT r FROM RetoEntity r JOIN r.usuariosEnReto u WHERE r.fechaInicio <= :fechaActual AND r.fechaFin >= :fechaActual")
	List<RetoEntity> findRetosActivos(@Param("fechaActual") LocalDate fechaActual);
	
}
