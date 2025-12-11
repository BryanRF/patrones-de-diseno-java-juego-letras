package Modelo.Entities;

import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * Entidad que representa un objeto del juego (categoría)
 * Contiene las palabras, descripciones, imágenes y audios
 */
public class Objeto {
    private String Objeto; // colores, habitaciones, paises
    private String[] listaDePalabras;
    private String[] listaDeDescripcion;
    private List<ImageIcon> listaDeImagen = new ArrayList<ImageIcon>();
    private List<Clip> listadoAudio_duende = new ArrayList<Clip>();
    private List<Clip> listadoAudio_hada = new ArrayList<Clip>();
    
    public void setObjeto(String Objeto) {
        this.Objeto = Objeto;
    }
    
    public String getObjeto() {
        return Objeto;
    }
    
    public String[] getListPalabras() {
        return listaDePalabras;
    }
    
    public void setListPalabras(String[] listaDePalabras) {
        this.listaDePalabras = listaDePalabras;
    }
    
    public String[] getListDescripcion() {
        return listaDeDescripcion;
    }
    
    public void setListDescripcion(String[] listaDeDescripcion) {
        this.listaDeDescripcion = listaDeDescripcion;
    }
    
    public List<ImageIcon> getlistaDeImagen() {
        return listaDeImagen;
    }
    
    public void setlistaDeImagen(List<ImageIcon> listaDeImagen) {
        this.listaDeImagen = listaDeImagen;
    }
    
    public List<Clip> getlistadoDeAudio_hada() {
        return listadoAudio_hada;
    }
    
    public void setlistadoDeAudio_hada(List<Clip> listadoAudio) {
        this.listadoAudio_hada = listadoAudio;
    }
    
    public List<Clip> getlistadoDeAudio_duende() {
        return listadoAudio_duende;
    }
    
    public void setlistadoDeAudio_duende(List<Clip> listadoAudio) {
        this.listadoAudio_duende = listadoAudio;
    }
    
    public String getPalabra(int x) {
        return listaDePalabras[x];
    }
    
    public String getDescripcion(int x) {
        return listaDeDescripcion[x];
    }
    
    public ImageIcon getImagen(int x) {
        return listaDeImagen.get(x);
    }
    
    public Clip getAudio_hada(int x) {
        return listadoAudio_hada.get(x);
    }
    
    public Clip getAudio_duende(int x) {
        return listadoAudio_duende.get(x);
    }
}

