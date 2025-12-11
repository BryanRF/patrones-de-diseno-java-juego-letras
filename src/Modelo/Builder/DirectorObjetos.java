package Modelo.Builder;

import Modelo.Entities.Objeto;

/**
 * Director del patrón Builder
 * Coordina la construcción de objetos complejos (Colores, Habitaciones, Paises)
 */
public class DirectorObjetos {
    public Tipo_Objeto T_Objeto;
    
    public void NuevoObjeto(){
        T_Objeto.NewObjeto();
        T_Objeto.buildObjeto();
        T_Objeto.buildListaPalabras();
        T_Objeto.buildListaDescripcion();
        T_Objeto.buildListaImagen();
        T_Objeto.buildListaAudio_duende();
        T_Objeto.buildListaAudio_hada();
    }
    
    public void setObjeto(Tipo_Objeto T_Objeto) {
        this.T_Objeto = T_Objeto;
    }
    
    public Objeto getObjeto() {
        return T_Objeto.getObjeto();
    }
}

