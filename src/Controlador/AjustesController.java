package Controlador;

import Vistas.Ventana_ajustes;
import Vistas.Ventana_juego;
import Utilidades.GameConstants;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.util.logging.Logger;

/**
 * Controlador específico para los ajustes
 * Maneja la configuración de la aplicación
 */
public class AjustesController {
    private static final Logger logger = Logger.getLogger(AjustesController.class.getName());
    private final Ventana_ajustes ventanaAjustes;
    private Ventana_juego ventanaJuego;
    private int posicionFondo;
    
    public AjustesController(Ventana_ajustes ventanaAjustes) {
        this.ventanaAjustes = ventanaAjustes;
        this.posicionFondo = 0;
    }
    
    /**
     * Establece la ventana de juego para actualizar iconos
     * @param ventanaJuego Ventana de juego
     */
    public void setVentanaJuego(Ventana_juego ventanaJuego) {
        this.ventanaJuego = ventanaJuego;
    }
    
    /**
     * Cambia el tamaño de la ventana según la opción seleccionada
     * @param opcion Opción seleccionada
     * @param ventanaPrincipal Ventana principal a redimensionar
     */
    public void cambiarTamañoVentana(String opcion, JFrame ventanaPrincipal) {
        if (ventanaPrincipal == null) {
            return;
        }
        
        // Verificar si es un tamaño personalizado (formato: "WIDTHxHEIGHT")
        if (opcion.matches("\\d+x\\d+")) {
            String[] partes = opcion.split("x");
            int ancho = Integer.parseInt(partes[0]);
            int alto = Integer.parseInt(partes[1]);
            ventanaPrincipal.setSize(ancho, alto);
            ventanaPrincipal.setLocationRelativeTo(null);
            
            // Ajustar para pantallas pequeñas si es necesario
            if (alto < GameConstants.ALTURA_MINIMA_PANTALLA) {
                ajustarParaPantallaPequena(ventanaPrincipal);
            }
            return;
        }
        
        switch (opcion) {
            case "Acomodar a mi pantalla":
                ventanaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
                ajustarParaPantallaPequena(ventanaPrincipal);
                break;
            case "1920x1080":
                ventanaPrincipal.setSize(
                    GameConstants.RESOLUCION_1920x1080_ANCHO,
                    GameConstants.RESOLUCION_1920x1080_ALTO);
                ventanaPrincipal.setLocationRelativeTo(null);
                break;
            case "1400x870":
                ventanaPrincipal.setSize(
                    GameConstants.RESOLUCION_1400x870_ANCHO,
                    GameConstants.RESOLUCION_1400x870_ALTO);
                ventanaPrincipal.setLocationRelativeTo(null);
                break;
            case "1366x768":
                ventanaPrincipal.setSize(
                    GameConstants.RESOLUCION_1366x768_ANCHO,
                    GameConstants.RESOLUCION_1366x768_ALTO);
                ajustarParaPantallaPequena(ventanaPrincipal);
                ventanaPrincipal.setLocationRelativeTo(null);
                break;
            default:
                logger.warning("Opción de tamaño desconocida: " + opcion);
        }
    }
    
    /**
     * Ajusta elementos para pantallas pequeñas
     * @param ventanaPrincipal Ventana principal
     */
    private void ajustarParaPantallaPequena(JFrame ventanaPrincipal) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double height = screenSize.getHeight();
        
        if (height < GameConstants.ALTURA_MINIMA_PANTALLA) {
            // Ajustes para pantallas pequeñas se pueden agregar aquí
            logger.fine("Ajustando para pantalla pequeña");
        }
    }
    
    /**
     * Avanza al siguiente fondo disponible
     * @param totalFondos Total de fondos disponibles
     * @return Nueva posición del fondo
     */
    public int siguienteFondo(int totalFondos) {
        if (posicionFondo >= totalFondos - 1) {
            posicionFondo = 0;
        } else {
            posicionFondo++;
        }
        return posicionFondo;
    }
    
    /**
     * Obtiene la posición actual del fondo
     * @return Posición del fondo
     */
    public int getPosicionFondo() {
        return posicionFondo;
    }
    
    /**
     * Establece la posición del fondo
     * @param posicion Nueva posición
     */
    public void setPosicionFondo(int posicion) {
        this.posicionFondo = posicion;
    }
    
    /**
     * Actualiza los iconos de música en la ventana de juego y ajustes
     * @param activa true si la música está activa, false si está desactivada
     */
    public void actualizarIconoMusica(boolean activa) {
        String rutaIcono = activa ? GameConstants.RUTA_MUTE : GameConstants.RUTA_MUTEADO;
        String rutaRadio = activa ? GameConstants.RUTA_BOTON_VERDE_RADIO : GameConstants.RUTA_BOTON_ROJO_RADIO;
        
        if (ventanaJuego != null) {
            ImageIcon icono = new ImageIcon(AjustesController.class.getResource(rutaIcono));
            ImageIcon iconoEscalado = new ImageIcon(icono.getImage().getScaledInstance(
                GameConstants.TAMANO_BOTON_ICONO, GameConstants.TAMANO_BOTON_ICONO, java.awt.Image.SCALE_DEFAULT));
            ventanaJuego.BUTTON_SILENCIAR.setIcon(iconoEscalado);
        }
        
        if (ventanaAjustes != null) {
            ImageIcon iconoRadio = new ImageIcon(AjustesController.class.getResource(rutaRadio));
            ImageIcon iconoRadioEscalado = new ImageIcon(iconoRadio.getImage().getScaledInstance(80, 50, java.awt.Image.SCALE_DEFAULT));
            ventanaAjustes.BUTTON_MUSICA.setIcon(iconoRadioEscalado);
        }
    }
}

