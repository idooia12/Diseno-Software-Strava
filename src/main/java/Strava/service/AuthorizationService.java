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
	 private List<UsuarioEntity> usuarios = new ArrayList<>();
	 private Map<String, UsuarioEntity> usuariosActivos = new HashMap<>(); // Mapa de tokens activos y su usuario correspondiente
	
	
	// Getters y setters
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
	
	//Add
	public void addUsuario(UsuarioEntity usuario) {
		usuarios.add(usuario);
	}

	public void addUsuarioActivo(String token, UsuarioEntity usuario) {
		usuariosActivos.put(token, usuario);
	}
	
	 //Login
    public String login(String email, String password) {
    	UsuarioEntity usuario = getUsuarioFromEmail(email);
    	
    	for (UsuarioEntity user : usuarios) {
    		if (user.getEmail().equals(email) && password.equals(user.getContraseña())) { //De momento miramos nosotros si coinciden usuario y contraseña
    			String token = generateToken(email);
    			addUsuarioActivo(token, usuario);
    			return token;
    		}
    	}
    	return null;
    }
    
    
    
    //Logout
    public boolean logout(String token) {
        if (usuariosActivos.containsKey(token)) {
            usuariosActivos.remove(token);
            return true;
        }
        return false;
    }
    
    // Token valido
    public boolean validateToken(String token) {
        return usuariosActivos.containsKey(token);
    }
    
    
	// Metodos auxiliares
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
    
    
    public String generateToken(String email) {
        String prefijoMail = email.split("@")[0];
        String timeStamp = String.valueOf(System.currentTimeMillis());  	
    	return prefijoMail + "@" + timeStamp;
    }
    
    /** Obtener usuario desde token */
    public UsuarioEntity getUsuarioFromToken(String token) {
        return usuariosActivos.get(token);
    }
        
    // Realizado con ayuda de ChatGPT
}
