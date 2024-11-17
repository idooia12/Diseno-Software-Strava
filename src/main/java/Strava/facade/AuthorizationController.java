package Strava.facade;


import Strava.service.AuthorizationService;
import Strava.dto.*;
import Strava.entity.UsuarioEntity;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
// Comentarios de Swagger
@Tag(name = "Authorization Controller", description = "Operations related to user authorization")

public class AuthorizationController {
    private final AuthorizationService authorizationService = AuthorizationService.getInstance();
   
    @Operation(
			summary = "Login de usuario", description = "Iniciar sesion", responses = {
					@ApiResponse(responseCode = "200", description = "OK: Login correcto"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized: Credenciales invalidas"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
			}
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        try {
            String token = authorizationService.login(email, password);
            if (token != null) {
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
            String email = authorizationService.getUsuarioFromToken(token).getEmail();
            if (email != null) {
                return new ResponseEntity<>(email, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Token inválido", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Token invalido", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/logout")
	public ResponseEntity<String> logout(
			@RequestParam("token") String token) {
		try {
			boolean success = authorizationService.logout(token);

			if (success) {
				return new ResponseEntity<>("Logout exitoso", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Token inválido", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    @GetMapping("/prueba")
    public ResponseEntity<String> testEndpoint() {
        return new ResponseEntity<>("Funcionando correctamente", HttpStatus.OK);
    }
    
    
}


