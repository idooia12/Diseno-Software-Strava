package Strava.dao;


import java.util.Optional;import org.springframework.data.jpa.repository.JpaRepository;
import Strava.entity.UsuarioEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UsuarioEntity, String> {
    Optional<UsuarioEntity> findByEmail(String email);
}
