package Servicios;

import Modelo.Entities.Objeto;
import Modelo.Observer.Palabra;
import java.util.Random;

/**
 * Servicio de lógica de negocio del juego
 * Maneja la lógica relacionada con el juego de palabras
 */
public class JuegoService {
    private final Random random;
    
    public JuegoService() {
        this.random = new Random(System.currentTimeMillis());
    }
    
    /**
     * Selecciona una palabra aleatoria de un objeto
     * @param objeto Objeto que contiene las palabras
     * @param totalPalabrasUsadas Total de palabras ya usadas
     * @return Índice de la palabra seleccionada
     */
    public int seleccionarPalabraAleatoria(Objeto objeto, int totalPalabrasUsadas) {
        if (objeto == null || objeto.getListPalabras() == null) {
            return 0;
        }
        
        int totalPalabras = objeto.getListPalabras().length;
        if (totalPalabras == 0) {
            return 0;
        }
        
        // Si ya se usaron todas las palabras, reiniciar
        if (totalPalabrasUsadas >= totalPalabras) {
            return random.nextInt(totalPalabras);
        }
        
        // Seleccionar palabra aleatoria que no haya sido usada
        int posicionAleatoria;
        do {
            posicionAleatoria = random.nextInt(totalPalabras);
        } while (posicionAleatoria == totalPalabrasUsadas && totalPalabras > 1);
        
        return posicionAleatoria;
    }
    
    /**
     * Obtiene la siguiente posición de palabra
     * @param posicionActual Posición actual
     * @param totalPalabras Total de palabras disponibles
     * @return Siguiente posición (circular)
     */
    public int siguientePosicion(int posicionActual, int totalPalabras) {
        if (totalPalabras == 0) {
            return 0;
        }
        
        if (posicionActual >= totalPalabras - 1) {
            return 0; // Volver al inicio
        }
        
        return posicionActual + 1;
    }
    
    /**
     * Obtiene la posición anterior de palabra
     * @param posicionActual Posición actual
     * @param totalPalabras Total de palabras disponibles
     * @return Posición anterior (circular)
     */
    public int anteriorPosicion(int posicionActual, int totalPalabras) {
        if (totalPalabras == 0) {
            return 0;
        }
        
        if (posicionActual <= 0) {
            return totalPalabras - 1; // Ir al final
        }
        
        return posicionActual - 1;
    }
    
    /**
     * Selecciona una letra aleatoria de ayuda de una palabra
     * @param palabra Palabra de la cual obtener la letra
     * @return Caracter de ayuda seleccionado
     */
    public char seleccionarLetraAyuda(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return '?';
        }
        
        int posicionAleatoria = random.nextInt(palabra.length());
        return palabra.charAt(posicionAleatoria);
    }
    
    /**
     * Verifica si una palabra está completa
     * @param palabraOriginal Palabra original
     * @param palabraGuiones Palabra con guiones
     * @return true si la palabra está completa
     */
    public boolean esPalabraCompleta(String palabraOriginal, char[] palabraGuiones) {
        if (palabraOriginal == null || palabraGuiones == null) {
            return false;
        }
        
        if (palabraOriginal.length() != palabraGuiones.length) {
            return false;
        }
        
        for (int i = 0; i < palabraOriginal.length(); i++) {
            if (palabraGuiones[i] != palabraOriginal.charAt(i)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Completa una palabra con una letra
     * @param palabraOriginal Palabra original
     * @param palabraGuiones Palabra con guiones
     * @param letra Letra a buscar y completar
     * @return true si se encontró y completó al menos una letra
     */
    public boolean completarLetra(String palabraOriginal, char[] palabraGuiones, char letra) {
        if (palabraOriginal == null || palabraGuiones == null) {
            return false;
        }
        
        boolean encontrada = false;
        char letraUpper = Character.toUpperCase(letra);
        
        for (int i = 0; i < palabraOriginal.length(); i++) {
            if (Character.toUpperCase(palabraOriginal.charAt(i)) == letraUpper) {
                if (palabraGuiones[i] != letraUpper) {
                    palabraGuiones[i] = letraUpper;
                    encontrada = true;
                }
            }
        }
        
        return encontrada;
    }
    
    /**
     * Crea un array de guiones para una palabra
     * @param palabra Palabra original
     * @return Array de guiones del mismo tamaño
     */
    public char[] crearPalabraOculta(String palabra) {
        if (palabra == null) {
            return new char[0];
        }
        
        char[] guiones = new char[palabra.length()];
        for (int i = 0; i < palabra.length(); i++) {
            guiones[i] = '-';
        }
        return guiones;
    }
}

