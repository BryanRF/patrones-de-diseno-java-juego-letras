package Utilidades;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * Clase para cargar y gestionar fuentes personalizadas del juego
 */
public class Font_letras {
    private static final Logger logger = Logger.getLogger(Font_letras.class.getName());
    
    // Constantes para tipos de fuente
    public static final int BUNN = 0; // Fuente principal del juego
    
    private Font fuenteBunn;
    
    public Font_letras() {
        cargarFuentes();
    }
    
    /**
     * Carga las fuentes personalizadas desde los recursos
     */
    private void cargarFuentes() {
        try {
            // Intentar cargar la fuente principal (04B_30__.TTF o ltromatic.ttf)
            InputStream fontStream = Font_letras.class.getResourceAsStream("/recursos/fuentes/04B_30__.TTF");
            if (fontStream == null) {
                fontStream = Font_letras.class.getResourceAsStream("/recursos/fuentes/ltromatic.ttf");
            }
            
            if (fontStream != null) {
                fuenteBunn = Font.createFont(Font.TRUETYPE_FONT, fontStream);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(fuenteBunn);
                fontStream.close();
                logger.info("Fuente cargada correctamente");
            } else {
                logger.warning("No se pudo cargar la fuente personalizada, usando fuente por defecto");
                // Usar fuente por defecto si no se puede cargar
                fuenteBunn = new Font("Snap ITC", Font.PLAIN, 12);
            }
        } catch (Exception e) {
            logger.warning("Error al cargar fuente: " + e.getMessage());
            // Usar fuente por defecto en caso de error
            fuenteBunn = new Font("Snap ITC", Font.PLAIN, 12);
        }
    }
    
    /**
     * Obtiene una fuente con el tipo, estilo y tamaño especificados
     * @param tipo Tipo de fuente (BUNN)
     * @param estilo Estilo de la fuente (Font.PLAIN, Font.BOLD, Font.ITALIC)
     * @param tamaño Tamaño de la fuente
     * @return Font configurada
     */
    public Font fuente(int tipo, int estilo, int tamaño) {
        try {
            if (tipo == BUNN && fuenteBunn != null) {
                return fuenteBunn.deriveFont(estilo, tamaño);
            }
        } catch (Exception e) {
            logger.warning("Error al crear fuente: " + e.getMessage());
        }
        
        // Fallback a fuente por defecto
        return new Font("Snap ITC", estilo, tamaño);
    }
}

