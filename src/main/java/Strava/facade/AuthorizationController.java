package Strava.facade;


import Strava.service.AuthorizationService;

import java.util.Optional;

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
@Tag(name = "Authorization Controller", description = "Operaciones relacionadas con la autenticación de usuarios")

public class AuthorizationController {
    private final AuthorizationService authorizationService;
    
 // Inyección de dependencias mediante constructor
    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }
   
    @Operation(
			summary = "Login de usuario", description = "Permite iniciar sesión proporcionando email y contraseña. Retorna un token si el login es exitoso.",
			responses = {
					@ApiResponse(responseCode = "200", description = "OK: Login correcto"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized: Credenciales invalidas"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
			}
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(
    		 @Parameter(description = "Correo electrónico del usuario", required = true) @RequestParam("Email") String email,
             @Parameter(description = "Contraseña del usuario", required = true) @RequestParam("Password") String password) {
        try {
            Optional<String> token = authorizationService.login(email, password);
            if (token != null) {
                return new ResponseEntity<>(token.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Validar token",
            description = "Verifica si el token proporcionado es válido, es decir, si pertenece a un usuario que ha hecho login con éxito.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Token válido"),
                @ApiResponse(responseCode = "401", description = "Token inválido"),
                @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
        )
    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(
    		@Parameter(description = "Token del usuario", required = true) 
    			@RequestParam("Token") String token) {  
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


    @Operation(
            summary = "Obtener email del usuario logeado",
            description = "Retorna el email del usuario asociado al token proporcionado.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Email del usuario retornado con éxito"),
                @ApiResponse(responseCode = "401", description = "Token inválido"),
                @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
        )
    @GetMapping("/user-email")
    public ResponseEntity<String> getUserEmail(
    		@Parameter(description = "Token del usuario", required = true) 
    			@RequestParam("Token") String token) {
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
    
    
    @Operation(
            summary = "Cerrar sesión",
            description = "Permite cerrar la sesión del usuario invalidando su token.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Logout exitoso"),
                @ApiResponse(responseCode = "401", description = "Token inválido"),
                @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
        )
    @PostMapping("/logout")
	public ResponseEntity<String> logout(
			@Parameter(description = "Token del usuario", required = true) 
			@RequestParam("Token") String token) {
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
    
    /*@GetMapping("/prueba")
    public ResponseEntity<String> testEndpoint() {
        return new ResponseEntity<>("Funcionando correctamente", HttpStatus.OK);
    }*/
    
    
}


