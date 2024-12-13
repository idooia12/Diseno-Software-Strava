package Strava.gateway;


import org.springframework.stereotype.Component;

import Strava.entity.UsuarioEntity;
import Strava.service.AuthorizationService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

@Component
public class MetaGateway { 

  

	private String serverIP = "127.0.0.1"; // Cambia por la IP real del servidor de Meta
    private int serverPort = 9000;       // Cambia por el puerto real del servidor de Meta
    private final String DELIMITER = "#";

    public void setInstance(String servIP, int servPort) {
        this.serverIP = servIP;
        this.serverPort = servPort;
        System.out.println("MetaGateway reconfigurado con IP: " + serverIP + " y puerto: " + serverPort);
    }

    /**
     * Valida si un usuario está registrado en el sistema Meta.
     * 
     * @param email El correo del usuario.
     * @return true si está registrado, false de lo contrario.
     */
    public boolean validar(String email) {
        String mensaje = "registrado" + DELIMITER + email;
        boolean resultado = false;

        try (Socket socket = new Socket(serverIP, serverPort);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            // Enviar mensaje al servidor
            out.writeUTF(mensaje);
            System.out.println(" - Sending data to Meta server: " + mensaje);

            // Recibir respuesta del servidor
            String respuesta = in.readUTF();
            System.out.println(" - Response from Meta server: " + respuesta);

            resultado = "true".equalsIgnoreCase(respuesta);
        } catch (Exception e) {
            System.err.println("# MetaGateway error in validar: " + e.getMessage());
        }

        return resultado;
    }

    /**
     * Intenta autenticar un usuario en el sistema Meta.
     * 
     * @param email    El correo del usuario.
     * @param password La contraseña del usuario.
     * @return true si las credenciales son válidas, false de lo contrario.
     */
    public boolean login(String email, String password) {
        String mensaje = "validar" + DELIMITER + email + DELIMITER + password;
        boolean resultado = false;

        try (Socket socket = new Socket(serverIP, serverPort);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            // Enviar mensaje al servidor
            out.writeUTF(mensaje);
            System.out.println(" - Sending data to Meta server: " + mensaje);

            // Recibir respuesta del servidor
            String respuesta = in.readUTF();
            System.out.println(" - Response from Meta server: " + respuesta);
            System.out.println(respuesta);

            // Procesar la respuesta del servidor para construir un UsuarioEntity
            if (respuesta.equals("true")) {
            	resultado = true;
            	System.out.println("No entra aqui?");
			} 
        } catch (Exception e) {
            System.err.println("# MetaGateway error in login: " + e.getMessage());
        }
        return resultado;
        

        //return usuarioEntity; // Devuelve el usuario o null si falló
    }


    
    /**
     * Obtiene un usuario por su email desde el servidor Meta.
     * 
     * @param email El correo electrónico del usuario.
     * @param key   La clave de autenticación.
     * @return Un objeto UsuarioEntity con la información del usuario.
     */
    public UsuarioEntity getUsuarioByEmail(String email, String key) {
        String mensaje = "obtenerUsuario" + DELIMITER + email + DELIMITER + key;
        UsuarioEntity usuarioEntity = null;

        try (Socket socket = new Socket(serverIP, serverPort);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            // Enviar mensaje al servidor Meta
            out.writeUTF(mensaje);
            System.out.println("Enviando datos al servidor Meta: " + mensaje);

            // Recibir respuesta del servidor Meta
            String respuesta = in.readUTF();
            System.out.println("Respuesta del servidor Meta: " + respuesta);

            // Validar y procesar la respuesta
            if (respuesta != null && !respuesta.equals("null")) {
                String[] partes = respuesta.split(DELIMITER);
                if (partes.length >= 2) { // Asegúrate de que hay al menos dos partes
                    usuarioEntity = new UsuarioEntity();
                    usuarioEntity.setEmail(partes[0]);  // Primer elemento es el email
                    usuarioEntity.setContraseña(partes[1]);  // Segundo elemento es la contraseña
                    // Si hay más campos, procesarlos aquí
                } else {
                    System.err.println("Respuesta inválida del servidor Meta: No contiene suficientes partes");
                }
            } else {
                System.err.println("Respuesta inválida o nula recibida del servidor Meta");
            }

        } catch (Exception e) {
            System.err.println("Error al obtener usuario desde Meta: " + e.getMessage());
        }

        return usuarioEntity;
    }




}


