package Strava.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Strava.entity.*;

@Repository
public interface EntrenamientoRepository extends JpaRepository<SesionEntrenamientoEntity, Long>{
    List<SesionEntrenamientoEntity> findByUsuario(UsuarioEntity usuario);
}
