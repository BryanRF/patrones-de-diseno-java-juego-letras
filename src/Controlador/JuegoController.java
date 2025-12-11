package Controlador;

import Vistas.Ventana_juego;
import Modelo.Observer.Palabra;
import Modelo.Builder.DirectorObjetos;
import Modelo.Builder.Tipo_Objeto;
import Modelo.Entities.Objeto;
import Servicios.JuegoService;
import Servicios.DialogService;
import Utilidades.ButtonManager;
import Utilidades.GameConstants;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.util.logging.Logger;

/**
 * Controlador específico para la lógica del juego
 * Maneja las interacciones del usuario durante el juego
 */
public class JuegoController {
    private static final Logger logger = Logger.getLogger(JuegoController.class.getName());
    private final Ventana_juego ventanaJuego;
    private final JuegoService juegoService;
    private final ButtonManager buttonManager;
    private final DirectorObjetos director;
    private char[] palabraGuiones;
    private Palabra palabraActual;
    private boolean palabraCompletada;
    private int posicionPalabra;
    
    public JuegoController(Ventana_juego ventanaJuego) {
        this.ventanaJuego = ventanaJuego;
        this.juegoService = new JuegoService();
        this.buttonManager = crearButtonManager();
        this.director = new DirectorObjetos();
        this.palabraCompletada = false;
        this.posicionPalabra = 0;
    }
    
    /**
     * Crea el ButtonManager con todos los botones del teclado
     */
    private ButtonManager crearButtonManager() {
        JButton[] botones = {
            ventanaJuego.BUTTON_Q, ventanaJuego.BUTTON_W, ventanaJuego.BUTTON_E,
            ventanaJuego.BUTTON_R, ventanaJuego.BUTTON_T, ventanaJuego.BUTTON_Y,
            ventanaJuego.BUTTON_U, ventanaJuego.BUTTON_I, ventanaJuego.BUTTON_O,
            ventanaJuego.BUTTON_P, ventanaJuego.BUTTON_A, ventanaJuego.BUTTON_S,
            ventanaJuego.BUTTON_D, ventanaJuego.BUTTON_F, ventanaJuego.BUTTON_G,
            ventanaJuego.BUTTON_H, ventanaJuego.BUTTON_J, ventanaJuego.BUTTON_K,
            ventanaJuego.BUTTON_L, ventanaJuego.BUTTON_NI, ventanaJuego.BUTTON_Z,
            ventanaJuego.BUTTON_X, ventanaJuego.BUTTON_C, ventanaJuego.BUTTON_V,
            ventanaJuego.BUTTON_B, ventanaJuego.BUTTON_N, ventanaJuego.BUTTON_M
        };
        return new ButtonManager(botones);
    }
    
    /**
     * Inicializa una nueva palabra para adivinar
     * @param palabra Instancia de Palabra con los datos
     */
    public void inicializarPalabra(Palabra palabra) {
        this.palabraActual = palabra;
        if (palabra != null && palabra.getNombre() != null) {
            this.palabraGuiones = palabra.PalabraOculta();
        } else {
            this.palabraGuiones = new char[0];
        }
        this.palabraCompletada = false;
        actualizarDisplay();
        buttonManager.reset();
    }
    
    /**
     * Actualiza la palabra actual y sus guiones
     * @param palabra Nueva palabra
     */
    public void actualizarPalabra(Palabra palabra) {
        this.palabraActual = palabra;
        if (palabra != null && palabra.getNombre() != null) {
            this.palabraGuiones = palabra.PalabraOculta();
            actualizarDisplay();
            actualizarDescripcionEImagen();
        }
    }
    
    /**
     * Actualiza la descripción e imagen en la ventana
     */
    private void actualizarDescripcionEImagen() {
        if (ventanaJuego != null && palabraActual != null) {
            ventanaJuego.ETIQUETA_DESCRIPCION.setText(palabraActual.getDescripcion());
            if (palabraActual.getImagen() != null) {
                ImageIcon etica = new ImageIcon(palabraActual.getImagen().getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_DEFAULT));
                ventanaJuego.ETIQUETA_IMAGEN.setIcon(etica);
            }
        }
    }
    
    /**
     * Procesa la selección de una letra usando el Observer Pattern
     * @param letra Letra seleccionada
     * @return true si la palabra está completa después de esta acción
     */
    public boolean procesarLetra(char letra) {
        if (palabraActual == null || palabraGuiones == null) {
            return false;
        }
        
        // Desactivar el botón
        buttonManager.desactivar(letra);
        
        // Usar el método del Observer Pattern para completar la letra
        palabraGuiones = palabraActual.PALABRA_COMPLETA(palabraGuiones, letra);
        
        // Actualizar display
        actualizarDisplay();
        
        // Verificar si la palabra está completa
        if (juegoService.esPalabraCompleta(palabraActual.getNombre(), palabraGuiones)) {
            palabraCompletada = true;
            buttonManager.desactivarTodos();
            return true;
        }
        
        return false;
    }
    
    /**
     * Obtiene la palabra con guiones actual
     * @return Array de caracteres con la palabra oculta
     */
    public char[] getPalabraGuiones() {
        return palabraGuiones != null ? palabraGuiones.clone() : null;
    }
    
    /**
     * Establece la palabra con guiones
     * @param palabraGuiones Array de caracteres
     */
    public void setPalabraGuiones(char[] palabraGuiones) {
        this.palabraGuiones = palabraGuiones != null ? palabraGuiones.clone() : null;
    }
    
    /**
     * Actualiza la visualización de la palabra
     */
    private void actualizarDisplay() {
        if (ventanaJuego != null && palabraGuiones != null) {
            ventanaJuego.ETIQUETA_PALABRA_SECRETA.setText(
                Arrays.toString(palabraGuiones).replaceAll("[\\[\\], ]", ""));
        }
    }
    
    /**
     * Prepara para la siguiente palabra
     */
    public void prepararSiguientePalabra() {
        palabraCompletada = false;
        ventanaJuego.ETIQUETA_IMAGEN.setVisible(false);
        ventanaJuego.ETIQUETA_INCOGNITA.setVisible(true);
        ventanaJuego.BUTTON_NEXT.setEnabled(false);
        ventanaJuego.BUTTON_AUDIO_TEXT.setEnabled(false);
        ventanaJuego.BUTTON_AYUDA.setEnabled(true);
        buttonManager.reset();
    }
    
    /**
     * Muestra la palabra completada
     */
    public void mostrarPalabraCompleta() {
        ventanaJuego.ETIQUETA_INCOGNITA.setVisible(false);
        ventanaJuego.ETIQUETA_IMAGEN.setVisible(true);
        ventanaJuego.BUTTON_NEXT.setEnabled(true);
        ventanaJuego.BUTTON_AUDIO_TEXT.setEnabled(true);
        ventanaJuego.BUTTON_AYUDA.setEnabled(false);
    }
    
    /**
     * Obtiene la letra de ayuda
     * @return Caracter de ayuda
     */
    public char obtenerLetraAyuda() {
        if (palabraActual != null) {
            return juegoService.seleccionarLetraAyuda(palabraActual.getNombre());
        }
        return '?';
    }
    
    /**
     * Verifica si la palabra está completada
     * @return true si está completada
     */
    public boolean isPalabraCompletada() {
        return palabraCompletada;
    }
    
    /**
     * Obtiene el ButtonManager
     * @return ButtonManager
     */
    public ButtonManager getButtonManager() {
        return buttonManager;
    }
    
    /**
     * Obtiene la palabra actual
     * @return Palabra actual
     */
    public Palabra getPalabraActual() {
        return palabraActual;
    }
    
    /**
     * Pasa a la siguiente palabra
     */
    public void siguientePalabra() {
        prepararSiguientePalabra();
    }
    
    /**
     * Muestra la ayuda (letra aleatoria) en una ventana modal
     */
    public void mostrarAyuda() {
        if (palabraActual != null && palabraActual.letraAyuda != 0) {
            DialogService.mostrarAyuda(palabraActual.letraAyuda, GameConstants.DELAY_VENTANA_AYUDA_MS);
        }
    }
    
    /**
     * Muestra el tutorial en una ventana modal
     */
    public void mostrarTutorial() {
        DialogService.mostrarTutorial(GameConstants.DELAY_VENTANA_TUTORIAL_MS);
    }
    
    /**
     * Inicializa una palabra desde un objeto usando Builder pattern
     * @param tipoObjeto Tipo de objeto (Colores, Habitaciones, Paises)
     * @param palabra Instancia de Palabra a inicializar
     */
    public void inicializarPalabraDesdeObjeto(Tipo_Objeto tipoObjeto, Palabra palabra) {
        if (tipoObjeto == null || palabra == null) {
            return;
        }
        
        director.setObjeto(tipoObjeto);
        director.NuevoObjeto();
        Objeto objeto = director.getObjeto();
        
        palabra.palabraConteo(objeto);
        palabra.TotalPalabras(objeto);
        palabra.LetraAyuda(objeto);
        
        actualizarPalabra(palabra);
    }
    
    /**
     * Pasa a la siguiente palabra usando Builder y Observer patterns
     * @param tipoObjeto Tipo de objeto (Colores, Habitaciones, Paises)
     * @param palabra Instancia de Palabra
     */
    public void siguientePalabraDesdeObjeto(Tipo_Objeto tipoObjeto, Palabra palabra) {
        if (tipoObjeto == null || palabra == null) {
            return;
        }
        
        director.setObjeto(tipoObjeto);
        director.NuevoObjeto();
        Objeto objeto = director.getObjeto();
        
        palabra.siguiente_palabra(objeto);
        palabra.TotalPalabras(objeto);
        palabra.LetraAyuda(objeto);
        
        actualizarPalabra(palabra);
    }
    
    /**
     * Obtiene el objeto según la posición usando Builder pattern
     * @param tipoObjetos Array de tipos de objetos [Colores, Habitaciones, Paises]
     * @param posicion Posición del objeto (0, 1, 2)
     * @return Tipo_Objeto correspondiente o null
     */
    public Tipo_Objeto obtenerTipoObjetoPorPosicion(Tipo_Objeto[] tipoObjetos, int posicion) {
        if (tipoObjetos == null || posicion < 0 || posicion >= tipoObjetos.length) {
            return null;
        }
        return tipoObjetos[posicion];
    }
    
    /**
     * Obtiene la letra correspondiente al botón presionado
     * @param source Fuente del evento
     * @return Letra correspondiente o 0 si no es un botón de letra
     */
    public char obtenerLetraDelBoton(Object source) {
        if (source == ventanaJuego.BUTTON_A) return 'A';
        if (source == ventanaJuego.BUTTON_B) return 'B';
        if (source == ventanaJuego.BUTTON_C) return 'C';
        if (source == ventanaJuego.BUTTON_D) return 'D';
        if (source == ventanaJuego.BUTTON_E) return 'E';
        if (source == ventanaJuego.BUTTON_F) return 'F';
        if (source == ventanaJuego.BUTTON_G) return 'G';
        if (source == ventanaJuego.BUTTON_H) return 'H';
        if (source == ventanaJuego.BUTTON_I) return 'I';
        if (source == ventanaJuego.BUTTON_J) return 'J';
        if (source == ventanaJuego.BUTTON_K) return 'K';
        if (source == ventanaJuego.BUTTON_L) return 'L';
        if (source == ventanaJuego.BUTTON_M) return 'M';
        if (source == ventanaJuego.BUTTON_N) return 'N';
        if (source == ventanaJuego.BUTTON_NI) return 'Ñ';
        if (source == ventanaJuego.BUTTON_O) return 'O';
        if (source == ventanaJuego.BUTTON_P) return 'P';
        if (source == ventanaJuego.BUTTON_Q) return 'Q';
        if (source == ventanaJuego.BUTTON_R) return 'R';
        if (source == ventanaJuego.BUTTON_S) return 'S';
        if (source == ventanaJuego.BUTTON_T) return 'T';
        if (source == ventanaJuego.BUTTON_U) return 'U';
        if (source == ventanaJuego.BUTTON_V) return 'V';
        if (source == ventanaJuego.BUTTON_W) return 'W';
        if (source == ventanaJuego.BUTTON_X) return 'X';
        if (source == ventanaJuego.BUTTON_Y) return 'Y';
        if (source == ventanaJuego.BUTTON_Z) return 'Z';
        return 0;
    }
    
    /**
     * Finaliza una categoría completa y muestra ventana de finalización
     * @param callback Callback para ejecutar después de mostrar la ventana
     */
    public void finalizarCategoria(Runnable callback) {
        posicionPalabra = 0;
        palabraCompletada = false;
        ventanaJuego.BUTTON_NEXT.setEnabled(false);
        ventanaJuego.BUTTON_AUDIO_TEXT.setEnabled(false);
        buttonManager.desactivarTodos();
        
        DialogService.mostrarFinalizacionCategoria(GameConstants.DELAY_VENTANA_FIN_MS);
        
        if (callback != null) {
            java.util.Timer timer = new java.util.Timer();
            timer.schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                    callback.run();
                }
            }, GameConstants.DELAY_VENTANA_FIN_MS);
        }
    }
    
    /**
     * Obtiene la posición actual de la palabra
     * @return Posición de la palabra
     */
    public int getPosicionPalabra() {
        return posicionPalabra;
    }
    
    /**
     * Establece la posición de la palabra
     * @param posicion Nueva posición
     */
    public void setPosicionPalabra(int posicion) {
        this.posicionPalabra = posicion;
    }
    
    /**
     * Incrementa la posición de la palabra
     */
    public void incrementarPosicionPalabra() {
        posicionPalabra++;
    }
    
    /**
     * Resetea la posición de la palabra
     */
    public void resetearPosicionPalabra() {
        posicionPalabra = 0;
    }
}

