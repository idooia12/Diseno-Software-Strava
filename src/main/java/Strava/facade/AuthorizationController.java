package Strava.facade;

public class AuthorizationController {

	public class AuthorizationService {

	    // SIMULACIONES DE AUTENTICACIÓN Y TOKENS
	    public boolean authenticate(String email, String password) {
	        // Simula un proceso de autenticación verificando correo y contraseña
	        return email.equals("usuario@example.com") && password.equals("password123");
	    }

	    public String generateToken(String email) {
	        // Genera un token simulado basado en el hash del correo electrónico
	        return "token_simulado_" + email.hashCode();
	    }

	    public boolean validateToken(String token) {
	        // Valida que el token comience con la cadena esperada
	        return token != null && token.startsWith("token_simulado_");
	    }

	    public String getUserEmailFromToken(String token) {
	        // Extrae el correo del token si es válido
	        if (validateToken(token)) {
	            return token.replace("token_simulado_", "");
	        }
	        return null;
	    }
	}

}
