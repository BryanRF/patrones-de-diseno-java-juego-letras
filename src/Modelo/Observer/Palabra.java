package Modelo.Observer;

import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import Modelo.Entities.Objeto;

/**
 * Clase Palabra que implementa el patrón Observer como Sujeto
 * Notifica a los observadores cuando cambia el estado de la palabra
 */
public class Palabra implements Sujeto_Palabra {
    private static List<Observador> Observer;
    private String nombre;
    private boolean estado;
    private String descripcion;
    private ImageIcon imagen;
    private Clip audio_duende;
    private Clip audio_hada;
    private int contador;
    private int total;
    public int totalPalabras;
    public char letraAyuda;

    public Palabra() {
        this.Observer = new ArrayList<>();
        this.nombre = "";
        this.descripcion = "";
        this.imagen = null;
        this.audio_duende = null;
        this.audio_hada = null;
        this.estado = false;
    }
    
    public List<Observador> getObserver() {
        return Observer;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nuevo) {
        this.nombre = nuevo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String nuevo) {
        this.descripcion = nuevo;
    }
    
    public ImageIcon getImagen() {
        return imagen;
    }
    
    public void setImagen(ImageIcon nuevo) {
        this.imagen = nuevo;
    }
    
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean nuevo) {
        this.estado = nuevo;
    }
    
    public Clip getAudio_duende() {
        return audio_duende;
    }
    
    public void setAudio_duende(Clip nuevo) {
        this.audio_duende = nuevo;
    }
    
    public Clip getAudio_hada() {
        return audio_hada;
    }
    
    public void setAudio_hada(Clip nuevo) {
        this.audio_hada = nuevo;
    }
    
    public char getLetra(int x) {
        return nombre.charAt(x);
    }
    
    private int PosicionPalabra(Objeto i) {
        int posicionAleatoria;
        do {
            posicionAleatoria = (int) Math.floor(Math.random() * i.getListPalabras().length);
        } while (!(posicionAleatoria != total));
        return posicionAleatoria;
    }
    
    public void palabraConteo(Objeto i) {
        contador = PosicionPalabra(i);
        this.nombre = i.getPalabra(contador);
        System.out.println("Con contador:" + contador);
        this.descripcion = i.getDescripcion(contador);
        this.imagen = new ImageIcon(i.getImagen(contador).getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_DEFAULT));
        this.audio_duende = i.getAudio_duende(contador);
        this.audio_hada = i.getAudio_hada(contador);
    }
    
    public void siguiente_palabra(Objeto i) {
        total = i.getListPalabras().length;
        if (contador == (total - 1)) {
            contador = -1;
        }
        contador = contador + 1;
        this.nombre = i.getPalabra(contador);
        this.descripcion = i.getDescripcion(contador);
        this.imagen = new ImageIcon(i.getImagen(contador).getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_DEFAULT));
        this.audio_duende = i.getAudio_duende(contador);
        this.audio_hada = i.getAudio_hada(contador);
    }
    
    public int TotalPalabras(Objeto i) {
        totalPalabras = i.getListPalabras().length;
        return total;
    }
    
    public char LetraAyuda(Objeto i) {
        int posicionAleatoria;
        posicionAleatoria = (int) Math.floor(Math.random() * this.nombre.length());
        letraAyuda = this.nombre.charAt(posicionAleatoria);
        return letraAyuda;
    }
    
    public char[] PalabraOculta() {
        char[] PALABRA_GUIONADOS = new char[this.nombre.length()];
        for (int i = 0; i < this.nombre.length(); i++) {
            PALABRA_GUIONADOS[i] = '-';
        }
        return PALABRA_GUIONADOS;
    }

    public char[] PALABRA_COMPLETA(char[] PALABRA_GUIONADOS, char letra) {
        this.estado = false;
        for (int i = 0; i < this.nombre.length(); i++) {
            if (this.nombre.charAt(i) == letra) {
                if (PALABRA_GUIONADOS[i] != letra) {
                    PALABRA_GUIONADOS[i] = letra;
                    this.estado = true;
                }
            }
        }
        this.notificar();
        return PALABRA_GUIONADOS;
    }
    
    @Override
    public boolean toEstado() {
        return estado;
    }
    
    @Override
    public void agregar(Observador O) {
        Observer.add(O);
    }

    @Override
    public void eliminar(Observador O) {
        Observer.remove(O);
    }

    @Override
    public void notificar() {
        for (int i = 0; i < Observer.size(); i++) {
            Observer.get(i).actualizar();
        }
    }
}

