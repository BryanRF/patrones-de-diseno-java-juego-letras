package Controlador;

import Servicios.AudioResourceManager;
import Servicios.CargarRecursos;
import Modelo.PersonajeManager;
import Utilidades.GameConstants;
import javax.sound.sampled.Clip;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Controlador para gestión de audio
 * Maneja la reproducción de sonidos y música
 * Optimizado para usar AudioResourceManager (recomendado) o CargarRecursos (legacy)
 */
public class AudioController {
    private static final Logger logger = Logger.getLogger(AudioController.class.getName());
    private final CargarRecursos recursos; // Legacy - mantener para compatibilidad
    private final AudioResourceManager resourceManager; // Nueva implementación optimizada
    private final Random random;
    private boolean sonidoBotonesHabilitado;
    private Clip ambienteActual;
    
    /**
     * Constructor usando AudioResourceManager optimizado (recomendado)
     */
    public AudioController(AudioResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        this.recursos = null;
        this.random = new Random(System.currentTimeMillis());
        this.sonidoBotonesHabilitado = true;
    }
    
    /**
     * Constructor legacy usando CargarRecursos (para compatibilidad)
     * @deprecated Usar constructor con AudioResourceManager
     */
    @Deprecated
    public AudioController(CargarRecursos recursos) {
        this.recursos = recursos;
        this.resourceManager = null;
        this.random = new Random(System.currentTimeMillis());
        this.sonidoBotonesHabilitado = true;
    }
    
    
    /**
     * Reproduce un sonido de botón si está habilitado
     */
    public void reproducirSonidoBoton() {
        Clip blup = getBlup();
        if (sonidoBotonesHabilitado && blup != null) {
            try {
                blup.setFramePosition(0);
                blup.start();
            } catch (Exception e) {
                logger.warning("Error al reproducir sonido de botón: " + e.getMessage());
            }
        }
    }
    
    private Clip getBlup() {
        if (resourceManager != null) {
            return resourceManager.getBlup();
        }
        return recursos != null ? recursos.getBlup() : null;
    }
    
    /**
     * Reproduce un clip de audio
     * @param clip Clip a reproducir
     */
    public void reproducirClip(Clip clip) {
        if (clip != null) {
            try {
                clip.setFramePosition(0);
                clip.start();
            } catch (Exception e) {
                logger.warning("Error al reproducir clip: " + e.getMessage());
            }
        }
    }
    
    /**
     * Reproduce un clip aleatorio de una lista
     * @param clips Lista de clips
     */
    public void reproducirClipAleatorio(java.util.List<Clip> clips) {
        if (clips != null && !clips.isEmpty()) {
            int indice = random.nextInt(clips.size());
            reproducirClip(clips.get(indice));
        }
    }
    
    /**
     * Reproduce el audio de bienvenida según el personaje
     * @param indicePersonaje Índice del personaje (0=Duende, 1=Hada)
     */
    public void reproducirBienvenida(int indicePersonaje) {
        // Usar constantes directamente para evitar problemas de inicialización
        if (indicePersonaje == GameConstants.INDICE_DUENDE) {
            Clip bienvenida = getBienvenida();
            if (bienvenida != null) {
                reproducirClip(bienvenida);
            } else {
                logger.warning("No se pudo cargar audio de bienvenida del Duende");
            }
        } else if (indicePersonaje == GameConstants.INDICE_HADA) {
            Clip bienvenida1 = getBienvenida1();
            if (bienvenida1 != null) {
                reproducirClip(bienvenida1);
            } else {
                logger.warning("No se pudo cargar audio de bienvenida del Hada");
            }
        } else {
            logger.warning("Índice de personaje inválido para bienvenida: " + indicePersonaje);
        }
    }
    
    private Clip getBienvenida() {
        return resourceManager != null ? resourceManager.getBienvenida() : 
               (recursos != null ? recursos.getBienvenida() : null);
    }
    
    private Clip getBienvenida1() {
        return resourceManager != null ? resourceManager.getBienvenida1() : 
               (recursos != null ? recursos.getBienvenida1() : null);
    }
    
    /**
     * Reproduce el audio de saludo según el personaje
     * @param indicePersonaje Índice del personaje (0=Duende, 1=Hada)
     */
    public void reproducirHola(int indicePersonaje) {
        List<Clip> hola = null;
        if (indicePersonaje == GameConstants.INDICE_DUENDE) {
            hola = resourceManager != null ? resourceManager.getHolaD() : 
                   (recursos != null ? recursos.getHolaD() : null);
        } else if (indicePersonaje == GameConstants.INDICE_HADA) {
            hola = resourceManager != null ? resourceManager.getHolaH() : 
                   (recursos != null ? recursos.getHolaH() : null);
        } else {
            logger.warning("Índice de personaje inválido para hola: " + indicePersonaje);
            return;
        }
        
        if (hola != null && !hola.isEmpty()) {
            reproducirClipAleatorio(hola);
        } else {
            logger.warning("No hay audios de hola disponibles para personaje índice: " + indicePersonaje);
        }
    }
    
    /**
     * Reproduce el audio de jugar según el personaje
     * @param indicePersonaje Índice del personaje (0=Duende, 1=Hada)
     */
    public void reproducirJugar(int indicePersonaje) {
        List<Clip> jugar = null;
        if (indicePersonaje == GameConstants.INDICE_DUENDE) {
            jugar = resourceManager != null ? resourceManager.getJugarD() : 
                    (recursos != null ? recursos.getJugarD() : null);
        } else if (indicePersonaje == GameConstants.INDICE_HADA) {
            jugar = resourceManager != null ? resourceManager.getJugarH() : 
                    (recursos != null ? recursos.getJugarH() : null);
        } else {
            logger.warning("Índice de personaje inválido para jugar: " + indicePersonaje);
            return;
        }
        
        if (jugar != null && !jugar.isEmpty()) {
            reproducirClipAleatorio(jugar);
        } else {
            logger.warning("No hay audios de jugar disponibles para personaje índice: " + indicePersonaje);
        }
    }
    
    /**
     * Reproduce el audio de error (inténtalo de nuevo) según el personaje
     * @param indicePersonaje Índice del personaje (0=Duende, 1=Hada)
     */
    public void reproducirError(int indicePersonaje) {
        List<Clip> error = null;
        if (indicePersonaje == GameConstants.INDICE_DUENDE) {
            error = resourceManager != null ? resourceManager.getErrorD() : 
                    (recursos != null ? recursos.getErrorD() : null);
        } else if (indicePersonaje == GameConstants.INDICE_HADA) {
            error = resourceManager != null ? resourceManager.getErrorH() : 
                    (recursos != null ? recursos.getErrorH() : null);
        } else {
            logger.warning("Índice de personaje inválido para error: " + indicePersonaje);
            return;
        }
        
        if (error != null && !error.isEmpty()) {
            reproducirClipAleatorio(error);
        } else {
            // Si no hay audio del personaje, reproducir efecto genérico
            logger.fine("No hay audio de error del personaje, usando efecto genérico");
        }
    }
    
    /**
     * Reproduce el audio de adiós según el personaje
     * @param indicePersonaje Índice del personaje (0=Duende, 1=Hada)
     */
    public void reproducirAdios(int indicePersonaje) {
        List<Clip> adios = null;
        if (indicePersonaje == GameConstants.INDICE_DUENDE) {
            adios = resourceManager != null ? resourceManager.getAdiosD() : 
                   (recursos != null ? recursos.getAdiosD() : null);
        } else if (indicePersonaje == GameConstants.INDICE_HADA) {
            adios = resourceManager != null ? resourceManager.getAdiosH() : 
                   (recursos != null ? recursos.getAdiosH() : null);
        } else {
            logger.warning("Índice de personaje inválido para adiós: " + indicePersonaje);
            return;
        }
        
        if (adios != null && !adios.isEmpty()) {
            reproducirClipAleatorio(adios);
        } else {
            logger.warning("No hay audios de adiós disponibles para personaje índice: " + indicePersonaje);
        }
    }
    
    /**
     * Reproduce el audio de ayuda según el personaje
     * @param indicePersonaje Índice del personaje (0=Duende, 1=Hada)
     */
    public void reproducirAyuda(int indicePersonaje) {
        List<Clip> ayuda = null;
        if (indicePersonaje == GameConstants.INDICE_DUENDE) {
            ayuda = resourceManager != null ? resourceManager.getAyudaD() : 
                   (recursos != null ? recursos.getAyudaD() : null);
        } else if (indicePersonaje == GameConstants.INDICE_HADA) {
            ayuda = resourceManager != null ? resourceManager.getAyudaH() : 
                   (recursos != null ? recursos.getAyudaH() : null);
        } else {
            logger.warning("Índice de personaje inválido para ayuda: " + indicePersonaje);
            return;
        }
        
        if (ayuda != null && !ayuda.isEmpty()) {
            reproducirClipAleatorio(ayuda);
        } else {
            logger.warning("No hay audios de ayuda disponibles para personaje índice: " + indicePersonaje);
        }
    }
    
    /**
     * Reproduce el audio de ajustes según el personaje
     * @param indicePersonaje Índice del personaje (0=Duende, 1=Hada)
     */
    public void reproducirAjustes(int indicePersonaje) {
        Clip ajustes = null;
        if (indicePersonaje == GameConstants.INDICE_DUENDE) {
            ajustes = resourceManager != null ? resourceManager.getAjustesD() : 
                     (recursos != null ? recursos.getAjustesD() : null);
        } else if (indicePersonaje == GameConstants.INDICE_HADA) {
            ajustes = resourceManager != null ? resourceManager.getAjustesH() : 
                     (recursos != null ? recursos.getAjustesH() : null);
        } else {
            logger.warning("Índice de personaje inválido para ajustes: " + indicePersonaje);
            return;
        }
        
        if (ajustes != null) {
            reproducirClip(ajustes);
        } else {
            logger.warning("No se pudo cargar audio de ajustes para personaje índice: " + indicePersonaje);
        }
    }
    
    /**
     * Reproduce el audio de palabra correcta según el personaje
     * @param indicePersonaje Índice del personaje (0=Duende, 1=Hada)
     */
    public void reproducirPalabraCorrecta(int indicePersonaje) {
        List<Clip> buena = null;
        if (indicePersonaje == GameConstants.INDICE_DUENDE) {
            buena = resourceManager != null ? resourceManager.getBuena() : 
                   (recursos != null ? recursos.getBuena() : null);
        } else if (indicePersonaje == GameConstants.INDICE_HADA) {
            buena = resourceManager != null ? resourceManager.getBuenaH() : 
                   (recursos != null ? recursos.getBuenaH() : null);
        } else {
            logger.warning("Índice de personaje inválido para palabra correcta: " + indicePersonaje);
            return;
        }
        
        if (buena != null && !buena.isEmpty()) {
            reproducirClipAleatorio(buena);
        } else {
            logger.warning("No hay audios de palabra correcta disponibles para personaje índice: " + indicePersonaje);
        }
    }
    
    /**
     * Reproduce el audio de fin de juego según el personaje
     * @param indicePersonaje Índice del personaje (0=Duende, 1=Hada)
     */
    public void reproducirFin(int indicePersonaje) {
        List<Clip> fin = null;
        if (indicePersonaje == GameConstants.INDICE_DUENDE) {
            fin = resourceManager != null ? resourceManager.getFinD() : 
                  (recursos != null ? recursos.getFinD() : null);
        } else if (indicePersonaje == GameConstants.INDICE_HADA) {
            fin = resourceManager != null ? resourceManager.getFinH() : 
                  (recursos != null ? recursos.getFinH() : null);
        } else {
            logger.warning("Índice de personaje inválido para fin: " + indicePersonaje);
            return;
        }
        
        if (fin != null && !fin.isEmpty()) {
            reproducirClipAleatorio(fin);
        } else {
            logger.warning("No hay audios de fin disponibles para personaje índice: " + indicePersonaje);
        }
    }
    
    /**
     * Reproduce el audio de música ON según el personaje
     * @param indicePersonaje Índice del personaje (0=Duende, 1=Hada)
     */
    public void reproducirMusicaON(int indicePersonaje) {
        List<Clip> musicaON = null;
        if (indicePersonaje == GameConstants.INDICE_DUENDE) {
            musicaON = resourceManager != null ? resourceManager.getMusicaOND() : 
                       (recursos != null ? recursos.getMusicaOND() : null);
        } else if (indicePersonaje == GameConstants.INDICE_HADA) {
            musicaON = resourceManager != null ? resourceManager.getMusicaONH() : 
                       (recursos != null ? recursos.getMusicaONH() : null);
        } else {
            logger.warning("Índice de personaje inválido para música ON: " + indicePersonaje);
            return;
        }
        
        if (musicaON != null && !musicaON.isEmpty()) {
            reproducirClipAleatorio(musicaON);
        } else {
            logger.warning("No hay audios de música ON disponibles para personaje índice: " + indicePersonaje);
        }
    }
    
    /**
     * Reproduce el audio de música OFF según el personaje
     * @param indicePersonaje Índice del personaje (0=Duende, 1=Hada)
     */
    public void reproducirMusicaOFF(int indicePersonaje) {
        List<Clip> musicaOFF = null;
        if (indicePersonaje == GameConstants.INDICE_DUENDE) {
            musicaOFF = resourceManager != null ? resourceManager.getMusicaOFFD() : 
                        (recursos != null ? recursos.getMusicaOFFD() : null);
        } else if (indicePersonaje == GameConstants.INDICE_HADA) {
            musicaOFF = resourceManager != null ? resourceManager.getMusicaOFFH() : 
                        (recursos != null ? recursos.getMusicaOFFH() : null);
        } else {
            logger.warning("Índice de personaje inválido para música OFF: " + indicePersonaje);
            return;
        }
        
        if (musicaOFF != null && !musicaOFF.isEmpty()) {
            reproducirClipAleatorio(musicaOFF);
        } else {
            logger.warning("No hay audios de música OFF disponibles para personaje índice: " + indicePersonaje);
        }
    }
    
    /**
     * Reproduce el audio de silenciar botones ON según el personaje
     * @param indicePersonaje Índice del personaje (0=Duende, 1=Hada)
     */
    public void reproducirSilenciarBotonesON(int indicePersonaje) {
        List<Clip> silenciar = null;
        if (indicePersonaje == GameConstants.INDICE_DUENDE) {
            silenciar = resourceManager != null ? resourceManager.getSilenciarBotonesOND() : 
                        (recursos != null ? recursos.getSilenciarBotonesOND() : null);
        } else if (indicePersonaje == GameConstants.INDICE_HADA) {
            silenciar = resourceManager != null ? resourceManager.getSilenciarBotonesONH() : 
                        (recursos != null ? recursos.getSilenciarBotonesONH() : null);
        } else {
            logger.warning("Índice de personaje inválido para silenciar botones ON: " + indicePersonaje);
            return;
        }
        
        if (silenciar != null && !silenciar.isEmpty()) {
            reproducirClipAleatorio(silenciar);
        } else {
            logger.warning("No hay audios de silenciar botones ON disponibles para personaje índice: " + indicePersonaje);
        }
    }
    
    /**
     * Reproduce el audio de silenciar botones OFF según el personaje
     * @param indicePersonaje Índice del personaje (0=Duende, 1=Hada)
     */
    public void reproducirSilenciarBotonesOFF(int indicePersonaje) {
        List<Clip> silenciar = null;
        if (indicePersonaje == GameConstants.INDICE_DUENDE) {
            silenciar = resourceManager != null ? resourceManager.getSilenciarBotonesOFFD() : 
                        (recursos != null ? recursos.getSilenciarBotonesOFFD() : null);
        } else if (indicePersonaje == GameConstants.INDICE_HADA) {
            silenciar = resourceManager != null ? resourceManager.getSilenciarBotonesOFFH() : 
                        (recursos != null ? recursos.getSilenciarBotonesOFFH() : null);
        } else {
            logger.warning("Índice de personaje inválido para silenciar botones OFF: " + indicePersonaje);
            return;
        }
        
        if (silenciar != null && !silenciar.isEmpty()) {
            reproducirClipAleatorio(silenciar);
        } else {
            logger.warning("No hay audios de silenciar botones OFF disponibles para personaje índice: " + indicePersonaje);
        }
    }
    
    /**
     * Reproduce el audio de menú según el personaje
     * @param indicePersonaje Índice del personaje (0=Duende, 1=Hada)
     */
    public void reproducirMenu(int indicePersonaje) {
        List<Clip> menu = null;
        if (indicePersonaje == GameConstants.INDICE_DUENDE) {
            menu = resourceManager != null ? resourceManager.getMenuD() : 
                   (recursos != null ? recursos.getMenuD() : null);
        } else if (indicePersonaje == GameConstants.INDICE_HADA) {
            menu = resourceManager != null ? resourceManager.getMenuH() : 
                   (recursos != null ? recursos.getMenuH() : null);
        } else {
            logger.warning("Índice de personaje inválido para menú: " + indicePersonaje);
            return;
        }
        
        if (menu != null && !menu.isEmpty()) {
            reproducirClipAleatorio(menu);
        } else {
            logger.warning("No hay audios de menú disponibles para personaje índice: " + indicePersonaje);
        }
    }
    
    /**
     * Reproduce el audio de aceptar según el personaje
     * @param indicePersonaje Índice del personaje (0=Duende, 1=Hada)
     */
    public void reproducirAceptar(int indicePersonaje) {
        List<Clip> aceptar = null;
        if (indicePersonaje == GameConstants.INDICE_DUENDE) {
            aceptar = resourceManager != null ? resourceManager.getAceptarD() : 
                     (recursos != null ? recursos.getAceptarD() : null);
        } else if (indicePersonaje == GameConstants.INDICE_HADA) {
            aceptar = resourceManager != null ? resourceManager.getAceptarH() : 
                     (recursos != null ? recursos.getAceptarH() : null);
        } else {
            logger.warning("Índice de personaje inválido para aceptar: " + indicePersonaje);
            return;
        }
        
        if (aceptar != null && !aceptar.isEmpty()) {
            reproducirClipAleatorio(aceptar);
        } else {
            logger.warning("No hay audios de aceptar disponibles para personaje índice: " + indicePersonaje);
        }
    }
    
    /**
     * Reproduce el audio de cancelar según el personaje
     * @param indicePersonaje Índice del personaje (0=Duende, 1=Hada)
     */
    public void reproducirCancelar(int indicePersonaje) {
        List<Clip> cancelar = null;
        if (indicePersonaje == GameConstants.INDICE_DUENDE) {
            cancelar = resourceManager != null ? resourceManager.getCancelarD() : 
                      (recursos != null ? recursos.getCancelarD() : null);
        } else if (indicePersonaje == GameConstants.INDICE_HADA) {
            cancelar = resourceManager != null ? resourceManager.getCancelarH() : 
                      (recursos != null ? recursos.getCancelarH() : null);
        } else {
            logger.warning("Índice de personaje inválido para cancelar: " + indicePersonaje);
            return;
        }
        
        if (cancelar != null && !cancelar.isEmpty()) {
            reproducirClipAleatorio(cancelar);
        } else {
            logger.warning("No hay audios de cancelar disponibles para personaje índice: " + indicePersonaje);
        }
    }
    
    /**
     * Inicia la música de ambiente en loop
     */
    public void iniciarMusicaAmbiente() {
        detenerMusicaAmbiente();
        ambienteActual = resourceManager != null ? resourceManager.getAmbiente() : 
                        (recursos != null ? recursos.getAmbiente() : null);
        if (ambienteActual != null) {
            try {
                ambienteActual.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception e) {
                logger.warning("Error al iniciar música ambiente: " + e.getMessage());
            }
        }
    }
    
    /**
     * Detiene la música de ambiente
     */
    public void detenerMusicaAmbiente() {
        if (ambienteActual != null) {
            try {
                ambienteActual.stop();
            } catch (Exception e) {
                logger.warning("Error al detener música ambiente: " + e.getMessage());
            }
        }
    }
    
    /**
     * Reproduce el tutorial
     */
    public void reproducirTutorial() {
        Clip tutorial = resourceManager != null ? resourceManager.getTutorial() : 
                       (recursos != null ? recursos.getTutorial() : null);
        reproducirClip(tutorial);
    }
    
    /**
     * Detiene el tutorial
     */
    public void detenerTutorial() {
        Clip tutorial = resourceManager != null ? resourceManager.getTutorial() : 
                       (recursos != null ? recursos.getTutorial() : null);
        if (tutorial != null) {
            tutorial.stop();
        }
    }
    
    /**
     * Habilita o deshabilita los sonidos de botones
     * @param habilitado true para habilitar, false para deshabilitar
     */
    public void setSonidoBotonesHabilitado(boolean habilitado) {
        this.sonidoBotonesHabilitado = habilitado;
    }
    
    /**
     * Verifica si los sonidos de botones están habilitados
     * @return true si están habilitados
     */
    public boolean isSonidoBotonesHabilitado() {
        return sonidoBotonesHabilitado;
    }
    
    /**
     * Detiene todos los audios de palabra correcta (duende y hada)
     */
    public void detenerAudiosPalabraCorrecta() {
        List<Clip> buena = resourceManager != null ? resourceManager.getBuena() : 
                           (recursos != null ? recursos.getBuena() : null);
        List<Clip> buenaH = resourceManager != null ? resourceManager.getBuenaH() : 
                            (recursos != null ? recursos.getBuenaH() : null);
        
        if (buena != null) {
            for (Clip clip : buena) {
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                }
            }
        }
        
        if (buenaH != null) {
            for (Clip clip : buenaH) {
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                }
            }
        }
    }
}

