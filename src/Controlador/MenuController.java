package Controlador;

import Vistas.Ventana_menu;
import Vistas.Ventana_juego;
import Utilidades.GameConstants;
import Modelo.PersonajeManager;
import javax.swing.ImageIcon;
import java.util.logging.Logger;

/**
 * Controlador específico para el menú principal
 * Maneja las acciones del menú
 */
public class MenuController {
    private static final Logger logger = Logger.getLogger(MenuController.class.getName());
    private final Ventana_menu ventanaMenu;
    private Ventana_juego ventanaJuego;
    private int posicionTipoObjeto;
    private int posicionPersonaje;
    private boolean baseCambio; // true = cambio de personaje, false = cambio de objeto
    private Controlador.AudioController audioController; // Para reproducir audio al cambiar personaje
    
    public MenuController(Ventana_menu ventanaMenu) {
        this.ventanaMenu = ventanaMenu;
        this.posicionTipoObjeto = 0;
        this.posicionPersonaje = 0;
        this.baseCambio = false;
        // Inicializar el texto del botón a "TEMA" (modo objeto por defecto)
        if (ventanaMenu != null && ventanaMenu.BUTTON_MENU_CAMBIO != null) {
            ventanaMenu.BUTTON_MENU_CAMBIO.setText("PERSONAJE");
        }
    }
    
    /**
     * Establece la ventana de juego para actualizar el personaje
     * @param ventanaJuego Ventana de juego
     */
    public void setVentanaJuego(Ventana_juego ventanaJuego) {
        this.ventanaJuego = ventanaJuego;
    }
    
    /**
     * Establece el AudioController para reproducir sonidos al cambiar personaje
     * @param audioController Controlador de audio
     */
    public void setAudioController(Controlador.AudioController audioController) {
        this.audioController = audioController;
    }
    
    /**
     * Avanza a la siguiente opción (objeto o personaje según baseCambio)
     */
    public void siguiente() {
        if (baseCambio) {
            // Cambiar personaje
            if (posicionPersonaje >= GameConstants.TOTAL_PERSONAJES - 1) {
                posicionPersonaje = 0;
            } else {
                posicionPersonaje++;
            }
        } else {
            // Cambiar objeto
            if (posicionTipoObjeto >= GameConstants.TOTAL_CATEGORIAS - 1) {
                posicionTipoObjeto = 0;
            } else {
                posicionTipoObjeto++;
            }
        }
    }
    
    /**
     * Retrocede a la opción anterior (objeto o personaje según baseCambio)
     */
    public void anterior() {
        if (baseCambio) {
            // Cambiar personaje
            if (posicionPersonaje <= 0) {
                posicionPersonaje = GameConstants.TOTAL_PERSONAJES - 1;
            } else {
                posicionPersonaje--;
            }
        } else {
            // Cambiar objeto
            if (posicionTipoObjeto <= 0) {
                posicionTipoObjeto = GameConstants.TOTAL_CATEGORIAS - 1;
            } else {
                posicionTipoObjeto--;
            }
        }
    }
    
    /**
     * Cambia entre modo objeto (TEMA) y modo personaje (PERSONAJE)
     * Actualiza el texto del botón según el modo
     */
    public void cambiarModo() {
        baseCambio = !baseCambio;
        
        // Actualizar el texto del botón según el modo
        if (ventanaMenu != null && ventanaMenu.BUTTON_MENU_CAMBIO != null) {
            if (baseCambio) {
                // Modo cambio de personaje - mostrar "PERSONAJE"
                ventanaMenu.BUTTON_MENU_CAMBIO.setText("TEMA");
            } else {
                // Modo cambio de objeto/tema - mostrar "TEMA"
                ventanaMenu.BUTTON_MENU_CAMBIO.setText("PERSONAJE");
            }
        }
        
        // Actualizar visualmente según el modo ANTES de cambiar visibilidad
        if (baseCambio) {
            // Modo cambio de personaje - actualizar y mostrar personaje actual
            cambiarPersonaje();
            if (ventanaMenu != null) {
                ventanaMenu.ETIQUETA_MENU_TIPO_CARICATURA1.setVisible(true);
                ventanaMenu.ETIQUETA_MENU_TIPO_OBJETO.setVisible(false);
            }
        } else {
            // Modo cambio de objeto - actualizar y mostrar objeto actual
            cambiarObjetoImagen();
            if (ventanaMenu != null) {
                ventanaMenu.ETIQUETA_MENU_TIPO_CARICATURA1.setVisible(false);
                ventanaMenu.ETIQUETA_MENU_TIPO_OBJETO.setVisible(true);
            }
        }
    }
    
    /**
     * Obtiene la posición del tipo de objeto
     * @return Índice del objeto (0=Colores, 1=Habitaciones, 2=Paises)
     */
    public int getPosicionTipoObjeto() {
        return posicionTipoObjeto;
    }
    
    /**
     * Establece la posición del tipo de objeto
     * @param posicion Nueva posición
     */
    public void setPosicionTipoObjeto(int posicion) {
        if (posicion >= 0 && posicion < GameConstants.TOTAL_CATEGORIAS) {
            this.posicionTipoObjeto = posicion;
        }
    }
    
    /**
     * Obtiene la posición del personaje
     * @return Índice del personaje (0=Duende, 1=Hada)
     */
    public int getPosicionPersonaje() {
        return posicionPersonaje;
    }
    
    /**
     * Establece la posición del personaje
     * @param posicion Nueva posición
     */
    public void setPosicionPersonaje(int posicion) {
        if (posicion >= 0 && posicion < GameConstants.TOTAL_PERSONAJES) {
            this.posicionPersonaje = posicion;
        }
    }
    
    /**
     * Verifica si está en modo cambio de personaje
     * @return true si está cambiando personaje, false si está cambiando objeto
     */
    public boolean isBaseCambio() {
        return baseCambio;
    }
    
    /**
     * Cambia el personaje mostrado en el menú y en el juego
     * Usa PersonajeManager para manejar todos los aspectos del personaje
     * Actualiza imagen, audio y todos los recursos relacionados
     */
    public void cambiarPersonaje() {
        // Validar índice
        if (!PersonajeManager.esIndicePersonajeValido(posicionPersonaje)) {
            logger.warning("Índice de personaje inválido: " + posicionPersonaje);
            return;
        }
        
        String nombrePersonaje = PersonajeManager.getNombrePersonaje(posicionPersonaje);
        logger.info("Cambiando a personaje: " + nombrePersonaje + " (índice " + posicionPersonaje + ")");
        
        // Actualizar visualmente usando PersonajeManager
        PersonajeManager.actualizarPersonajeVisual(posicionPersonaje, ventanaMenu, ventanaJuego);
        
        // Reproducir audio de confirmación del cambio de personaje
        if (audioController != null) {
            // Reproducir bienvenida del nuevo personaje para confirmar el cambio
            audioController.reproducirBienvenida(posicionPersonaje);
        }
        
        // Asegurar visibilidad correcta en el menú
        if (ventanaMenu != null && baseCambio) {
            ventanaMenu.ETIQUETA_MENU_TIPO_CARICATURA1.setVisible(true);
            ventanaMenu.ETIQUETA_MENU_TIPO_OBJETO.setVisible(false);
        }
    }
    
    /**
     * Cambia la imagen del objeto en el menú
     */
    public void cambiarObjetoImagen() {
        String rutaImagen;
        
        switch (posicionTipoObjeto) {
            case 0: // Colores
                rutaImagen = GameConstants.RUTA_OBJETO_COLORES;
                break;
            case 1: // Habitaciones
                rutaImagen = GameConstants.RUTA_CASA_OBJ;
                break;
            case 2: // Paises
                rutaImagen = GameConstants.RUTA_PAISES;
                break;
            default:
                return;
        }
        
        if (ventanaMenu != null) {
            ImageIcon icono = new ImageIcon(MenuController.class.getResource(rutaImagen));
            ImageIcon iconoEscalado = new ImageIcon(icono.getImage().getScaledInstance(470, 400, java.awt.Image.SCALE_DEFAULT));
            ventanaMenu.ETIQUETA_MENU_TIPO_OBJETO.setIcon(iconoEscalado);
        }
    }
}

