package Modelo.Entities;

/**
 * Entidad que representa un personaje del juego
 * Contiene toda la información del personaje: nombre, imagen, audios, etc.
 */
public class Personaje {
    private int id;
    private String nombre;
    private String rutaImagen;
    private String rutaAudioBienvenida;
    private int indice; // Índice del personaje (0=Duende, 1=Hada, etc.)
    
    /**
     * Constructor por defecto
     */
    public Personaje() {
    }
    
    /**
     * Constructor completo
     * @param id ID del personaje en la base de datos
     * @param nombre Nombre del personaje
     * @param rutaImagen Ruta de la imagen del personaje
     * @param rutaAudioBienvenida Ruta del audio de bienvenida
     * @param indice Índice del personaje (0=Duende, 1=Hada, etc.)
     */
    public Personaje(int id, String nombre, String rutaImagen, 
                     String rutaAudioBienvenida, int indice) {
        this.id = id;
        this.nombre = nombre;
        this.rutaImagen = rutaImagen;
        this.rutaAudioBienvenida = rutaAudioBienvenida;
        this.indice = indice;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getRutaImagen() {
        return rutaImagen;
    }
    
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
    
    public String getRutaAudioBienvenida() {
        return rutaAudioBienvenida;
    }
    
    public void setRutaAudioBienvenida(String rutaAudioBienvenida) {
        this.rutaAudioBienvenida = rutaAudioBienvenida;
    }
    
    public int getIndice() {
        return indice;
    }
    
    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    @Override
    public String toString() {
        return "Personaje{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", rutaImagen='" + rutaImagen + '\'' +
                ", indice=" + indice +
                '}';
    }
}
