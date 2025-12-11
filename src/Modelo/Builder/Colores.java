package Modelo.Builder;

/**
 * Builder concreto para la categoría Colores
 * Patrón Builder
 */
public class Colores extends TipoObjetoComun {

    public Colores() {
        cargarRecursosDesdeBaseDatos("Colores");
    }

    @Override
    public void buildObjeto() {
        Objeto.setObjeto("Colores");
    }
}

