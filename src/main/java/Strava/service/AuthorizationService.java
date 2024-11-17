package Strava.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import Strava.entity.UsuarioEntity;

@Service
public class AuthorizationService {
	// Atributos
	private static AuthorizationService instance;
	 private List<UsuarioEntity> usuarios = new ArrayList<>();
	 private Map<String, UsuarioEntity> usuariosActivos = new HashMap<>(); // Mapa de tokens activos y su usuario correspondiente
	
	//GETTERS y SETTERS
	public static AuthorizationService getInstance() {
		if (instance == null) {
			instance = new AuthorizationService();
		}
		return instance;
	}
	public List<UsuarioEntity> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioEntity> usuarios) {
		this.usuarios = usuarios;
	}

	public Map<String, UsuarioEntity> getUsuariosActivos() {
		return usuariosActivos;
	}

	public void setUsuariosActivos(Map<String, UsuarioEntity> usuariosActivos) {
		this.usuariosActivos = usuariosActivos;
	}
	
	// Otener el UsuarioEntity desde el email
    public UsuarioEntity getUsuarioFromEmail(String email) {
		for (UsuarioEntity usuario : usuarios) {
			if (usuario.getEmail().equals(email)) {
				return usuario;
			}
			else {
				return null;
			}
		}
		return null;
    }

    // SIMULACIONES DE AUTENTICACIÓN Y TOKENS
    public boolean authenticate(String email, String password) {
        return email.equals("usuario@example.com") && password.equals("password123");
    }

    public String generateToken(String email) {
        return "token_simulado_" + email.hashCode();
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("token_simulado_");
    }

    public String getUserEmailFromToken(String token) {
        if (validateToken(token)) {
            return token.replace("token_simulado_", "");
        }
        return null;
    }
    // Realizado con ayuda de ChatGPT
}
