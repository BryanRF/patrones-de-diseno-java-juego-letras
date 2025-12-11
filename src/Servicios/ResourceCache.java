package Servicios;

import javax.swing.ImageIcon;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Caché de recursos para evitar cargar imágenes y audios repetidamente
 * Implementa el patrón Singleton para un único punto de acceso
 */
public class ResourceCache {
    private static ResourceCache instance;
    private static final Logger logger = Logger.getLogger(ResourceCache.class.getName());
    
    // Cachés para diferentes tipos de recursos
    private final Map<String, ImageIcon> imageCache;
    private final Map<String, Clip> audioCache;
    private final Map<String, URL> urlCache;
    
    private ResourceCache() {
        this.imageCache = new HashMap<>();
        this.audioCache = new HashMap<>();
        this.urlCache = new HashMap<>();
    }
    
    /**
     * Obtiene la instancia única del ResourceCache (Singleton)
     */
    public static synchronized ResourceCache getInstance() {
        if (instance == null) {
            instance = new ResourceCache();
        }
        return instance;
    }
    
    /**
     * Obtiene una imagen del caché o la carga si no existe
     * @param path Ruta del recurso
     * @param loader Función para cargar la imagen si no está en caché
     * @return ImageIcon cargado o null si no se pudo cargar
     */
    public ImageIcon getImage(String path, java.util.function.Supplier<ImageIcon> loader) {
        if (path == null || path.isEmpty()) {
            return null;
        }
        
        // Verificar caché
        ImageIcon cached = imageCache.get(path);
        if (cached != null) {
            return cached;
        }
        
        // Cargar y cachear
        ImageIcon image = loader.get();
        if (image != null) {
            imageCache.put(path, image);
            logger.fine("Imagen cacheada: " + path);
        } else {
            logger.warning("No se pudo cargar la imagen: " + path);
        }
        
        return image;
    }
    
    /**
     * Obtiene un clip de audio del caché o lo carga si no existe
     * @param path Ruta del recurso
     * @param loader Función para cargar el clip si no está en caché
     * @return Clip cargado o null si no se pudo cargar
     */
    public Clip getAudio(String path, java.util.function.Supplier<Clip> loader) {
        if (path == null || path.isEmpty()) {
            return null;
        }
        
        // Verificar caché
        Clip cached = audioCache.get(path);
        if (cached != null) {
            return cached;
        }
        
        // Cargar y cachear
        Clip clip = loader.get();
        if (clip != null) {
            audioCache.put(path, clip);
            logger.fine("Audio cacheado: " + path);
        } else {
            logger.warning("No se pudo cargar el audio: " + path);
        }
        
        return clip;
    }
    
    /**
     * Obtiene una URL del caché o la carga si no existe
     * @param path Ruta del recurso
     * @param loader Función para cargar la URL si no está en caché
     * @return URL cargada o null si no se pudo cargar
     */
    public URL getURL(String path, java.util.function.Supplier<URL> loader) {
        if (path == null || path.isEmpty()) {
            return null;
        }
        
        // Verificar caché
        URL cached = urlCache.get(path);
        if (cached != null) {
            return cached;
        }
        
        // Cargar y cachear
        URL url = loader.get();
        if (url != null) {
            urlCache.put(path, url);
            logger.fine("URL cacheada: " + path);
        } else {
            logger.warning("No se pudo cargar la URL: " + path);
        }
        
        return url;
    }
    
    /**
     * Limpia el caché de imágenes
     */
    public void clearImageCache() {
        imageCache.clear();
        logger.info("Caché de imágenes limpiado");
    }
    
    /**
     * Limpia el caché de audio
     */
    public void clearAudioCache() {
        audioCache.clear();
        logger.info("Caché de audio limpiado");
    }
    
    /**
     * Limpia todos los cachés
     */
    public void clearAll() {
        clearImageCache();
        clearAudioCache();
        urlCache.clear();
        logger.info("Todos los cachés limpiados");
    }
    
    /**
     * Obtiene estadísticas del caché
     * @return String con información del tamaño de los cachés
     */
    public String getCacheStats() {
        return String.format("Caché - Imágenes: %d, Audio: %d, URLs: %d",
            imageCache.size(), audioCache.size(), urlCache.size());
    }
    
    /**
     * Pre-carga recursos comunes para mejorar rendimiento
     */
    public void preloadCommonResources() {
        // Este método puede ser extendido para pre-cargar recursos frecuentemente usados
        logger.info("Pre-carga de recursos comunes iniciada");
    }
}

