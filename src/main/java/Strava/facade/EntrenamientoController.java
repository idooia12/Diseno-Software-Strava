package Strava.facade;

import Strava.service.EntrenamientoService;
import Strava.entity.Deporte;
import Strava.entity.SesionEntrenamientoEntity;
import Strava.entity.UsuarioEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EntrenamientoController {

    private EntrenamientoService entrenamientoService;
    
    // Constructor para inicializar el servicio
    public EntrenamientoController() {
        this.entrenamientoService = EntrenamientoService.getInstance();
    }

    // Método para crear un nuevo entrenamiento
    public String crearEntrenamiento(UsuarioEntity usuario, String titulo, String deporte, Date inicio, int duracion) {
        try {
            entrenamientoService.crearEntrenamiento(usuario, titulo, deporte, inicio, duracion);
            return "Entrenamiento creado exitosamente!";
        } catch (Exception e) {
            return "Error al crear el entrenamiento: " + e.getMessage();
        }
    }

    // Método para obtener todos los entrenamientos de un usuario (en este ejemplo, supongamos que el usuario tiene sesiones)
    public List<SesionEntrenamientoEntity> obtenerEntrenamientos(UsuarioEntity usuario) {
        // Aquí tendríamos que obtener las sesiones de entrenamiento, por simplicidad, supongamos que ya están cargadas
        // en la entidad del usuario. Este es un ejemplo simplificado sin conexión a bases de datos.
        return new ArrayList<SesionEntrenamientoEntity>();  // Retorna una lista vacía por el momento
    }

    // Método de ejemplo para obtener un entrenamiento específico
    public SesionEntrenamientoEntity obtenerEntrenamiento(long id) {
        // Suponiendo que estamos obteniendo el entrenamiento de una base de datos o lista.
        return new SesionEntrenamientoEntity(); // Devuelve un objeto vacío por simplicidad
    }

    // Método principal para pruebas
    public static void main(String[] args) {
        // Crear instancias de los objetos necesarios
        UsuarioEntity usuario = new UsuarioEntity();
        Date inicio = Date.valueOf("2024-11-14");
        int duracion = 60; // Duración en minutos
        String titulo = "Entrenamiento de carrera";
        String deporte = "Correr";
        
        // Crear controlador de entrenamiento
        EntrenamientoController controller = new EntrenamientoController();
        
        // Crear un nuevo entrenamiento
        String respuesta = controller.crearEntrenamiento(usuario, titulo, deporte, inicio, duracion);
        System.out.println(respuesta);
        
        // Obtener los entrenamientos
        List<SesionEntrenamientoEntity> entrenamientos = controller.obtenerEntrenamientos(usuario);
        System.out.println("Número de entrenamientos: " + entrenamientos.size());
        
        // Obtener un entrenamiento específico por id (en este caso no estamos usando ids reales)
        SesionEntrenamientoEntity entrenamiento = controller.obtenerEntrenamiento(1);
        System.out.println("Entrenamiento obtenido: " + entrenamiento);
    }
}
