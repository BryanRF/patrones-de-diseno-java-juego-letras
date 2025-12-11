package Estrategias;

import Modelo.Builder.Habitaciones;
import Modelo.Builder.DirectorObjetos;
import Modelo.Entities.Objeto;
import Utilidades.GameConstants;

/**
 * Estrategia para la categoría Habitaciones
 */
public class HabitacionesStrategy implements CategoriaStrategy {
    private final DirectorObjetos director;
    private final Habitaciones habitaciones;
    
    public HabitacionesStrategy() {
        this.director = new DirectorObjetos();
        this.habitaciones = new Habitaciones();
    }
    
    @Override
    public String getNombreCategoria() {
        return GameConstants.CATEGORIA_HABITACIONES;
    }
    
    @Override
    public Objeto construirObjeto() {
        director.setObjeto(habitaciones);
        director.NuevoObjeto();
        return director.getObjeto();
    }
    
    @Override
    public String getRutaImagen() {
        return GameConstants.RUTA_CASA_OBJ;
    }
}

