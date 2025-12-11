package Estrategias;

import Utilidades.GameConstants;

/**
 * Estrategia para el personaje Duende
 */
public class DuendeStrategy implements PersonajeStrategy {
    
    @Override
    public String getNombrePersonaje() {
        return "Duende";
    }
    
    @Override
    public String getRutaImagen() {
        return GameConstants.RUTA_DUENDE;
    }
    
    @Override
    public int getIndice() {
        return GameConstants.INDICE_DUENDE;
    }
}

