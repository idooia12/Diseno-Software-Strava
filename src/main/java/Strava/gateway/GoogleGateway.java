package Strava.gateway;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GoogleGateway implements ServiceGatewayInterface {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String GOOGLE_BASE_URL = "http://localhost:8084/api/google"; // Cambia el puerto según el GoogleGateway

    //TODO No se usa key, ya se sabe que es Google

    @Override
    public boolean validar(String email) {
        String url = GOOGLE_BASE_URL + "/validar?email=" + email;
        return restTemplate.getForObject(url, Boolean.class);
    }

    @Override
    public boolean login(String email, String password) {
        String url = GOOGLE_BASE_URL + "/login?email=" + email + "&password=" + password;
        return restTemplate.getForObject(url, Boolean.class);
    }
}