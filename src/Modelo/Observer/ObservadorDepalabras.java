package Modelo.Observer;

import Controlador.AudioController;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.function.Supplier;

/**
 * Observador concreto del patrón Observer
 * Reacciona a cambios en el estado de las palabras (éxito/fallo)
 * Mejorado para usar AudioController y reproducir audios del personaje
 */
public class ObservadorDepalabras implements Observador {
    private final Sujeto_Palabra nombrePalabra;
    private Clip buena;
    private Clip fallo;
    private AudioController audioController; // Para reproducir audios del personaje
    private Supplier<Integer> obtenerIndicePersonaje; // Función para obtener el índice del personaje actual

    public ObservadorDepalabras(Sujeto_Palabra nombrePalabra) {
        this.nombrePalabra = nombrePalabra;
        this.nombrePalabra.agregar(this);
        buena = cargarClip("/recursos/audios/efectos/bien.wav");
        fallo = cargarClip("/recursos/audios/efectos/mal.wav");
    }
    
    /**
     * Establece el AudioController para reproducir audios del personaje
     * @param audioController Controlador de audio
     */
    public void setAudioController(AudioController audioController) {
        this.audioController = audioController;
    }
    
    /**
     * Establece la función para obtener el índice del personaje actual
     * @param obtenerIndicePersonaje Función que retorna el índice del personaje (0=Duende, 1=Hada)
     */
    public void setObtenerIndicePersonaje(Supplier<Integer> obtenerIndicePersonaje) {
        this.obtenerIndicePersonaje = obtenerIndicePersonaje;
    }

    @Override
    public void actualizar() {
        if (this.nombrePalabra.toEstado() == false) { // FALLO LETRA
            // Intentar reproducir audio del personaje primero
            if (audioController != null && obtenerIndicePersonaje != null) {
                try {
                    int indicePersonaje = obtenerIndicePersonaje.get();
                    audioController.reproducirError(indicePersonaje);
                } catch (Exception e) {
                    // Si falla, usar efecto genérico
                    reproducirClip(fallo);
                }
            } else {
                // Si no hay AudioController, usar efecto genérico
                reproducirClip(fallo);
            }
        } else {
            reproducirClip(buena);
        }
    }

    // Método para cargar un archivo de audio como Clip
    private Clip cargarClip(String path) {
        try {
            URL url = getClass().getResource(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para reiniciar y reproducir un clip
    private void reproducirClip(Clip clip) {
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }
}

