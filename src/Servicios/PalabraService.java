package Servicios;

import Modelo.Entities.Objeto;
import Modelo.Observer.Palabra;
import javax.swing.ImageIcon;

/**
 * Servicio para gestión de palabras
 * Coordina la lógica de palabras con el modelo
 */
public class PalabraService {
    private final JuegoService juegoService;
    
    public PalabraService() {
        this.juegoService = new JuegoService();
    }
    
    /**
     * Inicializa una palabra desde un objeto
     * @param palabra Instancia de Palabra a inicializar
     * @param objeto Objeto que contiene los datos
     * @param contador Posición de la palabra a usar
     */
    public void inicializarPalabra(Palabra palabra, Objeto objeto, int contador) {
        if (palabra == null || objeto == null) {
            return;
        }
        
        if (contador < 0 || contador >= objeto.getListPalabras().length) {
            return;
        }
        
        palabra.setNombre(objeto.getPalabra(contador));
        palabra.setDescripcion(objeto.getDescripcion(contador));
        
        ImageIcon imagenOriginal = objeto.getImagen(contador);
        if (imagenOriginal != null) {
            ImageIcon imagenEscalada = new ImageIcon(
                imagenOriginal.getImage().getScaledInstance(
                    200, 200, java.awt.Image.SCALE_DEFAULT));
            palabra.setImagen(imagenEscalada);
        }
        
        palabra.setAudio_duende(objeto.getAudio_duende(contador));
        palabra.setAudio_hada(objeto.getAudio_hada(contador));
    }
    
    /**
     * Obtiene el total de palabras de un objeto
     * @param objeto Objeto que contiene las palabras
     * @return Total de palabras
     */
    public int obtenerTotalPalabras(Objeto objeto) {
        if (objeto == null || objeto.getListPalabras() == null) {
            return 0;
        }
        return objeto.getListPalabras().length;
    }
    
    /**
     * Selecciona una letra de ayuda para una palabra
     * @param palabra Instancia de Palabra
     * @return Caracter de ayuda
     */
    public char seleccionarLetraAyuda(Palabra palabra) {
        if (palabra == null || palabra.getNombre() == null) {
            return '?';
        }
        return juegoService.seleccionarLetraAyuda(palabra.getNombre());
    }
}

