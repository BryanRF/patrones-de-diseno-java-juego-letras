package Estrategias;

/**
 * Interfaz Strategy para diferentes personajes
 * Permite extender fácilmente con nuevos personajes
 */
public interface PersonajeStrategy {
    /**
     * Obtiene el nombre del personaje
     * @return Nombre del personaje
     */
    String getNombrePersonaje();
    
    /**
     * Obtiene la ruta de la imagen del personaje
     * @return Ruta de la imagen
     */
    String getRutaImagen();
    
    /**
     * Obtiene el índice del personaje
     * @return Índice (0=Duende, 1=Hada, etc.)
     */
    int getIndice();
}

