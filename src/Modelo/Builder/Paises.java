package Modelo.Builder;

/**
 * Builder concreto para la categoría Paises
 * Patrón Builder
 */
public class Paises extends TipoObjetoComun {

    public Paises() {
        cargarRecursosDesdeBaseDatos("Paises");
    }

    @Override
    public void buildObjeto() {
        Objeto.setObjeto("Paises");
    }
}

