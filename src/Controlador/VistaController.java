package Controlador;

import Vistas.Ventana_principal;
import java.util.logging.Logger;

/**
 * Controlador para gestión de cambios de vista
 * Coordina la navegación entre diferentes paneles
 */
public class VistaController {
    private static final Logger logger = Logger.getLogger(VistaController.class.getName());
    private final Ventana_principal ventanaPrincipal;
    
    public VistaController(Ventana_principal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }
    
    /**
     * Muestra la vista de juego
     */
    public void mostrarJuego() {
        cambiarVista(Ventana_principal.VENTANA_JUEGO_POS);
    }
    
    /**
     * Muestra la vista de menú
     */
    public void mostrarMenu() {
        cambiarVista(Ventana_principal.VENTANA_MENU_POS);
    }
    
    /**
     * Muestra la vista de ajustes
     */
    public void mostrarAjustes() {
        cambiarVista(Ventana_principal.VENTANA_AJUSTES_POS);
    }
    
    /**
     * Muestra la vista de inicio
     */
    public void mostrarInicio() {
        cambiarVista(Ventana_principal.VENTANA_INICIO_POS);
    }
    
    /**
     * Cambia a una vista específica
     * @param nombreVista Nombre de la vista (constante de Ventana_principal)
     */
    private void cambiarVista(String nombreVista) {
        try {
            if (ventanaPrincipal != null && ventanaPrincipal.CambiarPanel != null) {
                ventanaPrincipal.CambiarPanel.show(ventanaPrincipal.PanelCambiante, nombreVista);
                logger.fine("Vista cambiada a: " + nombreVista);
            }
        } catch (Exception e) {
            logger.log(java.util.logging.Level.WARNING, "Error al cambiar vista: " + nombreVista, e);
        }
    }
    
    /**
     * Obtiene la vista actual
     * @return Nombre de la vista actual o null si no se puede determinar
     */
    public String obtenerVistaActual() {
        // CardLayout no tiene un método directo para obtener la vista actual
        // Esta implementación puede mejorarse si es necesario
        return null;
    }
}

