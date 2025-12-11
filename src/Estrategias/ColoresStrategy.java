package Estrategias;

import Modelo.Builder.Colores;
import Modelo.Builder.DirectorObjetos;
import Modelo.Entities.Objeto;
import Utilidades.GameConstants;

/**
 * Estrategia para la categoría Colores
 */
public class ColoresStrategy implements CategoriaStrategy {
    private final DirectorObjetos director;
    private final Colores colores;
    
    public ColoresStrategy() {
        this.director = new DirectorObjetos();
        this.colores = new Colores();
    }
    
    @Override
    public String getNombreCategoria() {
        return GameConstants.CATEGORIA_COLORES;
    }
    
    @Override
    public Objeto construirObjeto() {
        director.setObjeto(colores);
        director.NuevoObjeto();
        return director.getObjeto();
    }
    
    @Override
    public String getRutaImagen() {
        return GameConstants.RUTA_OBJETO_COLORES;
    }
}

