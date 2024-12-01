package Strava.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import Strava.entity.UsuarioEntity;
import Strava.dao.UserRepository;

@Service
public class AuthorizationService {
    // Atributos
    private final UserRepository userRepository;
    private final Map<String, UsuarioEntity> usuariosActivos = new HashMap<>(); // Mapa de tokens activos y su usuario correspondiente

    // Constructor
    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Getters y setters
    public Map<String, UsuarioEntity> getUsuariosActivos() {
        return usuariosActivos;
    }

    // Add Usuario Activo (ha hecho login)
    public void addUsuarioActivo(String token, UsuarioEntity usuario) {
        usuariosActivos.put(token, usuario);
    }

    // Add usuario a BD
    public Optional<UsuarioEntity> addUsuario(UsuarioEntity usuario) {
        if (userRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return Optional.empty(); // Si ya existe un usuario con ese email, no se puede añadir
        }
        userRepository.save(usuario); // Guardar en la base de datos
        return Optional.of(usuario);
    }

    // Login
    public Optional<String> login(String email, String password) {
        Optional<UsuarioEntity> user = userRepository.findByEmail(email);
        if (user.isPresent() && password.equals(user.get().getContraseña())) { // Validar usuario y contraseña
            String token = generateToken(email);
            addUsuarioActivo(token, user.get());
            return Optional.of(token);
        } else {
            return Optional.empty();
        }
    }

    // Logout
    public boolean logout(String token) {
        if (usuariosActivos.containsKey(token)) {
            usuariosActivos.remove(token);
            return true;
        } else {
            return false;
        }
    }

    // Validar Token
    public boolean validateToken(String token) {
        return usuariosActivos.containsKey(token);
    }

    // Métodos auxiliares
    public UsuarioEntity getUsuarioFromEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public String generateToken(String email) {
        // Generar un token único usando UUID
        return UUID.randomUUID().toString();
    }

    /** Obtener usuario desde token */
    public UsuarioEntity getUsuarioFromToken(String token) {
        return usuariosActivos.get(token);
    }
}
