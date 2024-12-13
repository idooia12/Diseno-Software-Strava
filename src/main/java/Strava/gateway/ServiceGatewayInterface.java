package Strava.gateway;

import Strava.entity.UsuarioEntity;

public interface ServiceGatewayInterface {

    boolean validar(String email, String key);
    boolean login(String email, String password, String key);
    
}
