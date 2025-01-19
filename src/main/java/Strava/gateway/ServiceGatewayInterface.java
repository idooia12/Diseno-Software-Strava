package Strava.gateway;


public interface ServiceGatewayInterface {

    boolean validar(String email);
    
    boolean login(String email, String password);
    
}
