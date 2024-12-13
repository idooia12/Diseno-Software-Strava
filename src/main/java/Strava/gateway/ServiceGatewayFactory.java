package Strava.gateway;

import jakarta.inject.Singleton;
import Strava.entity.ServicioValidacion;

@Singleton
public class ServiceGatewayFactory {

    // Instancia única (Singleton)
    private static ServiceGatewayFactory instance;

    // Constructor privado para evitar la creación directa de instancias
    private ServiceGatewayFactory() {
    }

    
    public static synchronized ServiceGatewayFactory getInstance() {
        if (instance == null) {
            instance = new ServiceGatewayFactory();
        }
        return instance;
    }

    public ServiceGatewayInterface crearGateway(ServicioValidacion tipo) {
        switch (tipo) {
            case Google:
                return new GoogleGateway(); // Asegúrate de que esta clase implemente ServiceGatewayInterface
            case Meta:
                return new MetaGateway(); // Asegúrate de que esta clase implemente ServiceGatewayInterface
            default:
                throw new IllegalArgumentException("Tipo de servicio no soportado: " + tipo);
        }
    }
}


//User repsoitory si no lo encuentra devuelve un no encontrado, cerre el getway segun el tipo de login,
//Controller tien que haber regitrar.