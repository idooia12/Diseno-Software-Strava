package Strava.gateway;

import org.springframework.stereotype.Component;

@Component
public class ServiceGatewayFactory {

    private final MetaGateway metaServiceGateway;
    private final GoogleGateway googleServiceGateway;

    // Constructor que inyecta las dependencias de MetaGateway y GoogleGateway
    public ServiceGatewayFactory(MetaGateway metaServiceGateway, GoogleGateway googleServiceGateway) {
        this.metaServiceGateway = metaServiceGateway;
        this.googleServiceGateway = googleServiceGateway;
    }

    /**
     * Devuelve el servicio adecuado (Meta o Google) según el string que se pase.
     * 
     * @param serviceKey El string que indica el tipo de servicio ("META" o "GOOGLE").
     * @return El servicio seleccionado (MetaGateway o GoogleGateway).
     */
    public Object getServiceGateway(String serviceKey) {
        switch (serviceKey.toUpperCase()) {
            case "GOOGLE":
                return googleServiceGateway;  // Retorna la instancia de GoogleGateway
            case "META":
                return metaServiceGateway;    // Retorna la instancia de MetaGateway
            default:
                throw new IllegalArgumentException("Servicio no soportado: " + serviceKey);
        }
    }
}


