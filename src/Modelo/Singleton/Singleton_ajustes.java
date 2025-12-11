package Modelo.Singleton;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Singleton para gestión de fondos de pantalla
 * Patrón Singleton - Garantiza una única instancia thread-safe
 * 
 * Responsabilidad: Gestionar la rotación de fondos de pantalla disponibles
 */
public class Singleton_ajustes {
    private static final Logger logger = Logger.getLogger(Singleton_ajustes.class.getName());
    
    // Instancia única (thread-safe usando inicialización lazy con double-check locking)
    private static volatile Singleton_ajustes instancia;
    
    // Lista de fondos de pantalla (inmutable después de la inicialización)
    private final List<URL> fondos;
    private int posicionActual = 0;
    
    // Constructor privado
    private Singleton_ajustes() {
        this.fondos = inicializarFondos();
        logger.info("Singleton_ajustes inicializado con " + fondos.size() + " fondos");
    }
    
    /**
     * Obtiene la instancia única del Singleton (thread-safe)
     * Usa double-check locking para mejor rendimiento
     * 
     * @return Instancia única de Singleton_ajustes
     */
    public static Singleton_ajustes getInstance() {
        if (instancia == null) {
            synchronized (Singleton_ajustes.class) {
                if (instancia == null) {
                    instancia = new Singleton_ajustes();
                }
            }
        }
        return instancia;
    }
    
    /**
     * Método de compatibilidad con código existente
     * @deprecated Usar getInstance() en su lugar
     */
    @Deprecated
    public static Singleton_ajustes getpantalla() {
        return getInstance();
    }
    
    /**
     * Inicializa la lista de fondos de pantalla
     * @return Lista inmutable de URLs de fondos
     */
    private List<URL> inicializarFondos() {
        List<URL> listaFondos = new ArrayList<>();
        
        // Cargar fondos de forma más mantenible
        String[] rutasFondos = {
            "/recursos/imagenes/fondos/fondo_pantalla.png",
            "/recursos/imagenes/fondos/fondo_pantalla_1.png",
            "/recursos/imagenes/fondos/fondo_pantalla_2.png",
            "/recursos/imagenes/fondos/fondo_pantalla_3.png",
            "/recursos/imagenes/fondos/fondo_pantalla_4.png",
            "/recursos/imagenes/fondos/fondo_pantalla_5.png",
            "/recursos/imagenes/fondos/fondo_pantalla_6.png",
            "/recursos/imagenes/fondos/fondo_pantalla_7.png",
            "/recursos/imagenes/fondos/fondo_pantalla_8.png",
            "/recursos/imagenes/fondos/fondo_pantalla_9.png",
            "/recursos/imagenes/fondos/fondo_pantalla_10.png",
            "/recursos/imagenes/fondos/fondo_pantalla_11.png",
            "/recursos/imagenes/fondos/fondo_pantalla_12.png"
        };
        
        for (String ruta : rutasFondos) {
            URL url = Singleton_ajustes.class.getResource(ruta);
            if (url != null) {
                listaFondos.add(url);
            } else {
                logger.warning("No se pudo cargar el fondo: " + ruta);
            }
        }
        
        // Retornar lista inmutable para prevenir modificaciones externas
        return Collections.unmodifiableList(listaFondos);
    }
    
    /**
     * Obtiene el siguiente fondo de pantalla en la secuencia
     * Avanza automáticamente al siguiente fondo
     * 
     * @return URL del siguiente fondo de pantalla
     */
    public URL obtenerSiguienteFondo() {
        if (fondos.isEmpty()) {
            logger.warning("No hay fondos disponibles");
            return null;
        }
        
        URL fondo = fondos.get(posicionActual);
        
        // Avanzar a la siguiente posición (circular)
        posicionActual = (posicionActual + 1) % fondos.size();
        
        return fondo;
    }
    
    /**
     * Obtiene el fondo actual sin avanzar
     * 
     * @return URL del fondo actual
     */
    public URL obtenerFondoActual() {
        if (fondos.isEmpty()) {
            return null;
        }
        return fondos.get(posicionActual);
    }
    
    /**
     * Establece la posición del fondo
     * 
     * @param posicion Índice del fondo (0 a fondos.size()-1)
     * @throws IllegalArgumentException si la posición es inválida
     */
    public void establecerPosicion(int posicion) {
        if (posicion < 0 || posicion >= fondos.size()) {
            throw new IllegalArgumentException(
                "Posición inválida: " + posicion + ". Debe estar entre 0 y " + (fondos.size() - 1));
        }
        this.posicionActual = posicion;
    }
    
    /**
     * Obtiene el número total de fondos disponibles
     * 
     * @return Número de fondos
     */
    public int getTotalFondos() {
        return fondos.size();
    }
    
    /**
     * Obtiene la posición actual
     * 
     * @return Índice del fondo actual
     */
    public int getPosicionActual() {
        return posicionActual;
    }
    
    /**
     * Método de compatibilidad con código existente
     * @deprecated Usar obtenerSiguienteFondo() en su lugar
     */
    @Deprecated
    public URL Fondos() {
        return obtenerSiguienteFondo();
    }
}

