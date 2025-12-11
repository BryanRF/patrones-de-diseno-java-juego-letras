package Modelo.Builder;

/**
 * Builder concreto para la categoría Habitaciones
 * Patrón Builder
 */
public class Habitaciones extends TipoObjetoComun {

    public Habitaciones() {
        cargarRecursosDesdeBaseDatos("Habitaciones");
    }

    @Override
    public void buildObjeto() {
        Objeto.setObjeto("Habitaciones");
    }
}

