package Estrategias;

import Utilidades.GameConstants;

/**
 * Estrategia para el personaje Hada
 */
public class HadaStrategy implements PersonajeStrategy {
    
    @Override
    public String getNombrePersonaje() {
        return "Hada";
    }
    
    @Override
    public String getRutaImagen() {
        return GameConstants.RUTA_HADA;
    }
    
    @Override
    public int getIndice() {
        return GameConstants.INDICE_HADA;
    }
}

