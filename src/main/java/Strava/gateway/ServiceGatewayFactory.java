package Strava.gateway;

import org.springframework.stereotype.Component;

@Component
public class ServiceGatewayFactory {

    private final MetaGateway metaServiceGateway;
    private final GoogleGateway googleServiceGateway;

    // Constructor que inyecta las dependencias
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
    public ServiceGatewayInterface getServiceGateway(String serviceKey) {
        switch (serviceKey.toUpperCase()) {
            case "GOOGLE":
                return googleServiceGateway;  // Retorna la instancia de GoogleGateway
            case "META":
                return metaServiceGateway;    // Retorna la instancia de MetaGateway
            default:
                throw new IllegalArgumentException("Servicio no soportado: " + serviceKey);
        }
    }

    /**
     * Configura la instancia de los gateways (Meta o Google) con la IP y el puerto proporcionados.
     * 
     * @param servIP Dirección IP del servidor.
     * @param servPort Puerto del servidor.
     * @param serviceKey Clave que indica el servicio ("META" o "GOOGLE").
     */
    public void setInstance(String servIP, int servPort, String serviceKey) {
        switch (serviceKey.toUpperCase()) {
      //      case "GOOGLE":
      //          googleServiceGateway.setInstance(servIP, servPort);  // Configura Google
      //         break;
            case "META":
                metaServiceGateway.setInstance(servIP, servPort, serviceKey);  // Configura Meta
                break;
            default:
                throw new IllegalArgumentException("Servicio no soportado: " + serviceKey);
        }
    }
}


