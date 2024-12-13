package Strava.gateway;

import Strava.entity.UsuarioEntity;
import Strava.service.AuthorizationService;
import org.springframework.stereotype.Component;

@Component
public class ServiceGateway implements ServiceGatewayInterface {

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

    @Override
    public boolean validar(String email, String key) {
        // Lógica de validación
        switch (key) {
        //    case "GOOGLE":
      //          return googleServiceGateway.validar(email, key);  // Usamos googleServiceGateway
            case "META":
                return metaServiceGateway.validar(email, key);  // Usamos metaServiceGateway
            default:
                throw new IllegalArgumentException("Unexpected service key: " + key);
        }
    }

    @Override
    public boolean login(String email, String password, String key) {
        // Lógica de login
        boolean isAuthenticated = false;
        switch (key) {
          //  case "GOOGLE":
        //        isAuthenticated = googleServiceGateway.login(email, password, key);  // Usamos googleServiceGateway
          //      break;
            case "META":
                isAuthenticated = metaServiceGateway.login(email, password, key);  // Usamos metaServiceGateway
                break;
            default:
                throw new IllegalArgumentException("Unexpected service key: " + key);
        }

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
        //    case "GOOGLE":
        //        googleServiceGateway.setInstance(servIP, servPort);  // Configuramos Google
        //        break;
            case "META":
                metaServiceGateway.setInstance(servIP, servPort, key);  // Configuramos Meta
                break;
            default:
                throw new IllegalArgumentException("Unexpected service key: " + key);
        }
    }

    
    public UsuarioEntity getUsuarioByEmail(String email, String key) {
        // Lógica para obtener el usuario por email
        switch (key) {
        //    case "GOOGLE":
       //         return googleServiceGateway.getUsuarioByEmail(email, key);
            case "META":
                return metaServiceGateway.getUsuarioByEmail(email, key);
            default:
                throw new IllegalArgumentException("Unexpected service key: " + key);
        }
    }
}
