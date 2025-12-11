package Modelo;

import Modelo.Database.DatabaseManager;
import Modelo.Database.DatabaseSeeder;
import Modelo.Entities.Personaje;
import Modelo.Repository.PersonajeRepository;
import Utilidades.GameConstants;
import Vistas.Ventana_menu;
import Vistas.Ventana_juego;
import javax.swing.ImageIcon;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gestor centralizado de personajes
 * Maneja imágenes, audios y todos los aspectos relacionados con personajes
 * Carga personajes desde la base de datos SQLite
 */
public class PersonajeManager {
    private static final Logger logger = Logger.getLogger(PersonajeManager.class.getName());
    private static final PersonajeRepository personajeRepository = new PersonajeRepository();
    
    // Cache de personajes cargados desde la base de datos
    private static Map<Integer, Personaje> personajesCache = null;
    private static boolean cacheInicializado = false;
    
    /**
     * Inicializa el cache de personajes desde la base de datos
     */
    private static void inicializarCache() {
        if (cacheInicializado) {
            return;
        }
        
        personajesCache = new HashMap<>();
        
        try {
            // Asegurar que la base de datos esté inicializada
            DatabaseManager dbManager = DatabaseManager.getInstance();
            if (dbManager.isDatabaseEmpty()) {
                DatabaseSeeder seeder = new DatabaseSeeder();
                seeder.seed();
            }
            
            List<Personaje> personajes = personajeRepository.getAllPersonajes();
            
            if (personajes == null || personajes.isEmpty()) {
                logger.warning("No se encontraron personajes en la base de datos, usando fallback");
                inicializarCacheFallback();
                return;
            }
            
            for (Personaje personaje : personajes) {
                personajesCache.put(personaje.getIndice(), personaje);
                logger.info("Personaje cargado desde BD: " + personaje.getNombre() + " (índice " + personaje.getIndice() + ")");
            }
            
            // Si no se cargaron personajes, usar fallback
            if (personajesCache.isEmpty()) {
                logger.warning("Cache vacío después de cargar desde BD, usando fallback");
                inicializarCacheFallback();
                return;
            }
            
            cacheInicializado = true;
            logger.info("Cache de personajes inicializado con " + personajesCache.size() + " personajes");
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al cargar personajes desde la base de datos", e);
            // Fallback a valores por defecto si falla la BD
            inicializarCacheFallback();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error inesperado al inicializar cache de personajes", e);
            // Fallback a valores por defecto
            inicializarCacheFallback();
        }
    }
    
    /**
     * Inicializa el cache con valores por defecto si falla la base de datos
     */
    private static void inicializarCacheFallback() {
        logger.warning("Usando valores por defecto para personajes (fallback)");
        personajesCache = new HashMap<>();
        
        // Duende por defecto
        Personaje duende = new Personaje(1, "Duende", 
            GameConstants.RUTA_DUENDE,
            GameConstants.RUTA_AUDIO_BIENVENIDA_DUENDE,
            GameConstants.INDICE_DUENDE);
        personajesCache.put(GameConstants.INDICE_DUENDE, duende);
        
        // Hada por defecto
        Personaje hada = new Personaje(2, "Hada",
            GameConstants.RUTA_HADA,
            GameConstants.RUTA_AUDIO_BIENVENIDA_HADA,
            GameConstants.INDICE_HADA);
        personajesCache.put(GameConstants.INDICE_HADA, hada);
        
        cacheInicializado = true;
    }
    
    /**
     * Obtiene un personaje desde la base de datos según su índice
     * @param indicePersonaje Índice del personaje
     * @return Personaje o null si no existe
     */
    public static Personaje getPersonaje(int indicePersonaje) {
        inicializarCache();
        return personajesCache.get(indicePersonaje);
    }
    
    /**
     * Actualiza visualmente el personaje en el menú y juego
     * @param indicePersonaje Índice del personaje
     * @param ventanaMenu Ventana del menú (puede ser null)
     * @param ventanaJuego Ventana del juego (puede ser null)
     */
    public static void actualizarPersonajeVisual(int indicePersonaje, 
                                                  Ventana_menu ventanaMenu, 
                                                  Ventana_juego ventanaJuego) {
        Personaje personaje = getPersonaje(indicePersonaje);
        if (personaje == null) {
            logger.warning("No se encontró personaje con índice: " + indicePersonaje);
            return;
        }
        
        String rutaImagen = personaje.getRutaImagen();
        String nombrePersonaje = personaje.getNombre();
        
        logger.info("Actualizando personaje visual: " + nombrePersonaje + " (índice " + indicePersonaje + ")");
        
        // Cargar imagen
        java.net.URL urlPersonaje = PersonajeManager.class.getResource(rutaImagen);
        if (urlPersonaje == null) {
            logger.warning("No se encontró el recurso de imagen: " + rutaImagen);
            return;
        }
        
        ImageIcon iconoOriginal = new ImageIcon(urlPersonaje);
        if (iconoOriginal.getImage() == null) {
            logger.warning("No se pudo cargar la imagen del personaje: " + rutaImagen);
            return;
        }
        
        // Actualizar menú
        if (ventanaMenu != null) {
            ImageIcon iconoMenuEscalado = new ImageIcon(iconoOriginal.getImage().getScaledInstance(470, 400, java.awt.Image.SCALE_DEFAULT));
            ventanaMenu.ETIQUETA_MENU_TIPO_CARICATURA1.setIcon(iconoMenuEscalado);
            ventanaMenu.revalidate();
            ventanaMenu.repaint();
        }
        
        // Actualizar juego
        if (ventanaJuego != null) {
            ImageIcon iconoJuegoEscalado = new ImageIcon(iconoOriginal.getImage().getScaledInstance(220, 220, java.awt.Image.SCALE_DEFAULT));
            ventanaJuego.ETIQUETA_CARICATURA.setIcon(iconoJuegoEscalado);
            ventanaJuego.revalidate();
            ventanaJuego.repaint();
        }
    }
    
    /**
     * Obtiene el nombre del personaje según su índice
     * @param indicePersonaje Índice del personaje
     * @return Nombre del personaje o "Desconocido" si no existe
     */
    public static String getNombrePersonaje(int indicePersonaje) {
        Personaje personaje = getPersonaje(indicePersonaje);
        return personaje != null ? personaje.getNombre() : "Desconocido";
    }
    
    /**
     * Obtiene la ruta de la imagen del personaje según su índice
     * @param indicePersonaje Índice del personaje
     * @return Ruta de la imagen o null si no existe
     */
    public static String getRutaImagenPersonaje(int indicePersonaje) {
        Personaje personaje = getPersonaje(indicePersonaje);
        return personaje != null ? personaje.getRutaImagen() : null;
    }
    
    /**
     * Obtiene la ruta del audio de bienvenida del personaje según su índice
     * @param indicePersonaje Índice del personaje
     * @return Ruta del audio o null si no existe
     */
    public static String getRutaAudioBienvenida(int indicePersonaje) {
        Personaje personaje = getPersonaje(indicePersonaje);
        return personaje != null ? personaje.getRutaAudioBienvenida() : null;
    }
    
    /**
     * Verifica si un índice de personaje es válido
     * @param indicePersonaje Índice a verificar
     * @return true si es válido, false en caso contrario
     */
    public static boolean esIndicePersonajeValido(int indicePersonaje) {
        inicializarCache();
        
        // Si el cache está vacío o no inicializado, usar valores por defecto
        if (personajesCache == null || personajesCache.isEmpty()) {
            logger.warning("Cache de personajes vacío, inicializando fallback");
            inicializarCacheFallback();
        }
        
        // Validar contra constantes conocidas como fallback
        boolean existeEnCache = personajesCache != null && personajesCache.containsKey(indicePersonaje);
        boolean esIndiceConocido = (indicePersonaje == GameConstants.INDICE_DUENDE || 
                                    indicePersonaje == GameConstants.INDICE_HADA);
        
        return existeEnCache || esIndiceConocido;
    }
    
    /**
     * Obtiene todos los personajes disponibles
     * @return Lista de personajes
     */
    public static List<Personaje> getAllPersonajes() {
        try {
            return personajeRepository.getAllPersonajes();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener todos los personajes", e);
            return new java.util.ArrayList<>();
        }
    }
}
