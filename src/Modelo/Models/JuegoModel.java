package Modelo.Models;

import Modelo.Observer.Palabra;
import java.util.HashSet;
import java.util.Set;

/**
 * Modelo que representa el estado del juego
 * Encapsula los datos del juego siguiendo principios MVC
 */
public class JuegoModel {
    private Palabra palabraActual;
    private char[] palabraGuiones;
    private Set<Character> letrasUsadas;
    private int posicionPalabra;
    private int totalPalabras;
    private boolean palabraCompletada;
    private int categoriaActual;
    private int personajeActual;
    
    public JuegoModel() {
        this.letrasUsadas = new HashSet<>();
        this.palabraCompletada = false;
        this.posicionPalabra = 0;
    }
    
    public Palabra getPalabraActual() {
        return palabraActual;
    }
    
    public void setPalabraActual(Palabra palabraActual) {
        this.palabraActual = palabraActual;
        if (palabraActual != null) {
            resetearPalabraGuiones();
        }
    }
    
    public char[] getPalabraGuiones() {
        return palabraGuiones != null ? palabraGuiones.clone() : null;
    }
    
    public void setPalabraGuiones(char[] palabraGuiones) {
        this.palabraGuiones = palabraGuiones != null ? palabraGuiones.clone() : null;
    }
    
    private void resetearPalabraGuiones() {
        if (palabraActual != null && palabraActual.getNombre() != null) {
            this.palabraGuiones = new char[palabraActual.getNombre().length()];
            for (int i = 0; i < palabraGuiones.length; i++) {
                palabraGuiones[i] = '-';
            }
        }
    }
    
    public Set<Character> getLetrasUsadas() {
        return new HashSet<>(letrasUsadas);
    }
    
    public void agregarLetraUsada(char letra) {
        letrasUsadas.add(Character.toUpperCase(letra));
    }
    
    public boolean esLetraUsada(char letra) {
        return letrasUsadas.contains(Character.toUpperCase(letra));
    }
    
    public void resetearLetrasUsadas() {
        letrasUsadas.clear();
    }
    
    public int getPosicionPalabra() {
        return posicionPalabra;
    }
    
    public void setPosicionPalabra(int posicionPalabra) {
        this.posicionPalabra = posicionPalabra;
    }
    
    public void incrementarPosicionPalabra() {
        posicionPalabra++;
    }
    
    public int getTotalPalabras() {
        return totalPalabras;
    }
    
    public void setTotalPalabras(int totalPalabras) {
        this.totalPalabras = totalPalabras;
    }
    
    public boolean isPalabraCompletada() {
        return palabraCompletada;
    }
    
    public void setPalabraCompletada(boolean palabraCompletada) {
        this.palabraCompletada = palabraCompletada;
    }
    
    public int getCategoriaActual() {
        return categoriaActual;
    }
    
    public void setCategoriaActual(int categoriaActual) {
        this.categoriaActual = categoriaActual;
    }
    
    public int getPersonajeActual() {
        return personajeActual;
    }
    
    public void setPersonajeActual(int personajeActual) {
        this.personajeActual = personajeActual;
    }
    
    /**
     * Resetea el modelo para un nuevo juego
     */
    public void resetear() {
        palabraActual = null;
        palabraGuiones = null;
        letrasUsadas.clear();
        posicionPalabra = 0;
        palabraCompletada = false;
    }
}

