package Strava.service;

import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

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
