package Strava.facade;

import Strava.service.AuthorizationService;
import Strava.gateway.ServiceGateway;
import Strava.dao.*;
import Strava.entity.UsuarioEntity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController // Anotación para indicar que es un controlador REST
@RequestMapping("/api/auth")
@Tag(name = "Authorization Controller", description = "Operaciones relacionadas con la autenticación de usuarios")
public class AuthorizationController {

    private final ServiceGateway serviceGateway;
    private final AuthorizationService authorizationService;
    private final UserRepository userRepository;

    // Constructor con inyección de dependencias
    public AuthorizationController(ServiceGateway serviceGateway, AuthorizationService authorizationService,UserRepository userRepository) {
        this.serviceGateway = serviceGateway;
        this.authorizationService = authorizationService;
        this.userRepository = userRepository;
    }

    @Operation(
        summary = "Login de usuario",
        description = "Permite iniciar sesión proporcionando email, contraseña y clave del servicio. Retorna un token si el login es exitoso.",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK: Login correcto"),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Credenciales inválidas"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )

    
    @PostMapping("/login")
    public ResponseEntity<String> login(
        @Parameter(description = "Correo electrónico del usuario", required = true) @RequestParam("Email") String email,
        @Parameter(description = "Contraseña del usuario", required = true) @RequestParam("Password") String password,
        @Parameter(description = "Clave del servicio (META/GOOGLE)", required = true) @RequestParam("ServiceKey") String key
        ) {

        try {
        	
        	//AQUI COMPRUEBO QUE EL USUARIO ESTE EN LA BBDD
            Optional<UsuarioEntity> usuarioOpt = userRepository.findByEmail(email);
            if (!usuarioOpt.isPresent()) {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            }
            else {
            	//AQUI VALIDO CON EL GATEWAY 
            	System.out.println("Usuario encontrado en la BBDD");
            	 boolean isAuthenticated = serviceGateway.login(email, password, key);
                 if (isAuthenticated) {
                     String token = authorizationService.generateToken(email);
                     authorizationService.addUsuarioActivo(token, serviceGateway.getUsuarioByEmail(email, key));
                     return new ResponseEntity<>(token, HttpStatus.OK);
                 } else {
                     return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
                 }
            }
           
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
        summary = "Validar token",
        description = "Verifica si el token proporcionado es válido.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Token válido"),
            @ApiResponse(responseCode = "401", description = "Token inválido"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )
    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(
        @Parameter(description = "Token del usuario", required = true) @RequestParam("Token") String token) {
        try {
            boolean isValid = authorizationService.validateToken(token);
            if (isValid) {
                return new ResponseEntity<>("Token válido", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Token inválido", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
        @Parameter(description = "Token del usuario", required = true) @RequestParam("Token") String token) {
        try {
            String email = authorizationService.getUsuarioFromToken(token).getEmail();
            if (email != null) {
                return new ResponseEntity<>(email, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Token inválido", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
        @Parameter(description = "Token del usuario", required = true) @RequestParam("Token") String token) {
        try {
            boolean success = authorizationService.logout(token);
            if (success) {
                return new ResponseEntity<>("Logout exitoso", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Token inválido", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



