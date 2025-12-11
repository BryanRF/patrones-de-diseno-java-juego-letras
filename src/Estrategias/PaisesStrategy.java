package Estrategias;

import Modelo.Builder.Paises;
import Modelo.Builder.DirectorObjetos;
import Modelo.Entities.Objeto;
import Utilidades.GameConstants;

/**
 * Estrategia para la categoría Paises
 */
public class PaisesStrategy implements CategoriaStrategy {
    private final DirectorObjetos director;
    private final Paises paises;
    
    public PaisesStrategy() {
        this.director = new DirectorObjetos();
        this.paises = new Paises();
    }
    
    @Override
    public String getNombreCategoria() {
        return GameConstants.CATEGORIA_PAISES;
    }
    
    @Override
    public Objeto construirObjeto() {
        director.setObjeto(paises);
        director.NuevoObjeto();
        return director.getObjeto();
    }
    
    @Override
    public String getRutaImagen() {
        return GameConstants.RUTA_PAISES;
    }
}

