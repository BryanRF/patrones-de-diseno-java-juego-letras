package Modelo.Builder;

import Modelo.Entities.Objeto;

/**
 * Clase abstracta Builder del patrón Builder
 * Define los pasos para construir un Objeto
 */
public abstract class Tipo_Objeto {
    protected Objeto Objeto;
    
    public void NewObjeto(){
        Objeto = new Objeto();
    }
    
    public Objeto getObjeto() {
        return Objeto;
    }
    
    public abstract void buildObjeto();
    public abstract void buildListaPalabras(); 
    public abstract void buildListaDescripcion();  
    public abstract void buildListaImagen(); 
    public abstract void buildListaAudio_duende(); 
    public abstract void buildListaAudio_hada(); 
}

