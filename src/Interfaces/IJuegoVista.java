package Interfaces;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * Interfaz para la vista del juego
 * Permite desacoplar el controlador de la implementación concreta de la vista
 */
public interface IJuegoVista {
    // Métodos para actualizar la UI sin exponer componentes directamente
    void actualizarPalabra(String palabra);
    void actualizarDescripcion(String descripcion);
    void mostrarImagen(ImageIcon imagen);
    void ocultarImagen();
    void mostrarIncognita();
    void ocultarIncognita();
    void habilitarBotonAyuda(boolean habilitado);
    void habilitarBotonSiguiente(boolean habilitado);
    void habilitarBotonAudio(boolean habilitado);
    void habilitarBotonLetra(char letra, boolean habilitado);
    void habilitarTodosLosBotones(boolean habilitado);
    
    // Getters necesarios (temporal, para compatibilidad durante la transición)
    JButton getBotonLetra(char letra);
    JLabel getEtiquetaPalabra();
    JLabel getEtiquetaDescripcion();
    JLabel getEtiquetaImagen();
    JLabel getEtiquetaIncognita();
}

