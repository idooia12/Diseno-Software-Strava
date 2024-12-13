package Strava.gateway;

import Strava.entity.UsuarioEntity;
import Strava.service.AuthorizationService;
import org.springframework.stereotype.Component;

@Component
public class ServiceGateway implements ServiceGatewayInterface{
//Canbui
    private final MetaGateway metaServiceGateway;
    private final GoogleGateway googleServiceGateway;
    private final AuthorizationService authorizationService;

    public ServiceGateway(MetaGateway metaServiceGateway,
    					  GoogleGateway googleServiceGateway,
                          AuthorizationService authorizationService) {
        this.metaServiceGateway = metaServiceGateway;
        this.googleServiceGateway = googleServiceGateway;
        this.authorizationService = authorizationService;
    }
    

    public boolean validar(String email, String key) {
        return switch (key) {
        	case "GOOGLE" -> googleServiceGateway.validar(email);
            case "META" -> metaServiceGateway.validar(email);
            default -> throw new IllegalArgumentException("Unexpected service key: " + key);
        };
    }

    public boolean login(String email, String password, String key) {
        boolean isAuthenticated = switch (key) {
           	case "GOOGLE" -> googleServiceGateway.login(email, password);
            case "META" -> metaServiceGateway.login(email, password);
            default -> throw new IllegalArgumentException("Unexpected service key: " + key);
        };

        if (isAuthenticated) {
            String token = authorizationService.generateToken(email);
            System.out.println("Token generado: " + token);
            authorizationService.addUsuarioActivo(token, authorizationService.getUsuarioFromEmail(email));
            return true;
        }
        return false;
    }

    public void setInstance(String servIP, int servPort, String key) {
        switch (key) {
        	case "GOOGLE" -> googleServiceGateway.setInstance(servIP, servPort);
            case "META" -> metaServiceGateway.setInstance(servIP, servPort);
            default -> throw new IllegalArgumentException("Unexpected service key: " + key);
        }
    }
    
    public UsuarioEntity getUsuarioByEmail(String email, String key) {
        return switch (key) {
        	case "GOOGLE" -> googleServiceGateway.getUsuarioByEmail(email, key);
        	case "META" -> metaServiceGateway.getUsuarioByEmail(email, key);
            default -> throw new IllegalArgumentException("Unexpected service key: " + key);
        };
    }
}