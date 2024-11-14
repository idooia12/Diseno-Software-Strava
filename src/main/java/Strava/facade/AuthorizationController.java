package Strava.facade;


import Strava.service.AuthorizationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        try {
            boolean authenticated = authorizationService.authenticate(email, password);

            if (authenticated) {
                String token = authorizationService.generateToken(email);
                return new ResponseEntity<>(token, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para validar el token

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(
            @RequestParam("token") String token) {
        try {
            boolean isValid = authorizationService.validateToken(token);

            if (isValid) {
                return new ResponseEntity<>("Token válido", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Token inválido", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/user-email")
    public ResponseEntity<String> getUserEmail(
            @RequestParam("token") String token) {
        try {
            String email = authorizationService.getUserEmailFromToken(token);

            if (email != null) {
                return new ResponseEntity<>(email, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Token inválido", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


