package Strava.gateway;

import jakarta.inject.Singleton;
import Strava.entity.ServicioValidacionEnum;


@Singleton
public class ServiceGatewayFactory {

    private static ServiceGatewayFactory instance;

    private ServiceGatewayFactory() {
        // Constructor privado para asegurar Singleton
    }

    public static synchronized ServiceGatewayFactory getInstance() {
        if (instance == null) {
            instance = new ServiceGatewayFactory();
        }
        return instance;
    }

    public ServiceGatewayInterface crearGateway(ServicioValidacionEnum tipo) {
        switch (tipo) {
            case Google:
                return new GoogleGateway();
            case Meta:
                return new MetaGateway();
            default:
                throw new IllegalArgumentException("Tipo de servicio no soportado: " + tipo);
        }
    }
}

