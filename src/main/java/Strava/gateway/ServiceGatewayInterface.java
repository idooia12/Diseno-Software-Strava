package Strava.gateway;


public interface ServiceGatewayInterface {

	/*Método para validar que el mail esta registrado
	 * @param email: mail del usuario
     * @param key: Google o meta 
     */
    boolean validar(String email);
    
    /*Método para loguear al usuario
     * @param email: mail del usuario
     * @param password: contraseña del usuario
     * @param key: Google o meta
     */
    boolean login(String email, String password);
    
}
