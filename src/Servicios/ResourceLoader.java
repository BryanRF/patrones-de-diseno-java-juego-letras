package Servicios;

import javax.swing.ImageIcon;
import javax.swing.SwingWorker;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cargador de recursos asíncrono
 * Usa SwingWorker para cargar recursos en background sin bloquear la UI
 */
public class ResourceLoader {
    private static final Logger logger = Logger.getLogger(ResourceLoader.class.getName());
    private final ResourceCache cache;
    
    public ResourceLoader() {
        this.cache = ResourceCache.getInstance();
    }
    
    /**
     * Carga una imagen de forma asíncrona
     * @param path Ruta del recurso
     * @param callback Callback que se ejecuta cuando la imagen está cargada
     */
    public void loadImageAsync(String path, Consumer<ImageIcon> callback) {
        SwingWorker<ImageIcon, Void> worker = new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                return loadImage(path);
            }
            
            @Override
            protected void done() {
                try {
                    ImageIcon image = get();
                    if (callback != null) {
                        callback.accept(image);
                    }
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error al cargar imagen asíncronamente: " + path, e);
                    if (callback != null) {
                        callback.accept(null);
                    }
                }
            }
        };
        worker.execute();
    }
    
    /**
     * Carga múltiples imágenes de forma asíncrona
     * @param paths Lista de rutas
     * @param callback Callback que se ejecuta cuando todas las imágenes están cargadas
     */
    public void loadImagesAsync(List<String> paths, Consumer<List<ImageIcon>> callback) {
        SwingWorker<List<ImageIcon>, Void> worker = new SwingWorker<List<ImageIcon>, Void>() {
            @Override
            protected List<ImageIcon> doInBackground() throws Exception {
                List<ImageIcon> images = new ArrayList<>();
                for (String path : paths) {
                    ImageIcon image = loadImage(path);
                    if (image != null) {
                        images.add(image);
                    }
                }
                return images;
            }
            
            @Override
            protected void done() {
                try {
                    List<ImageIcon> images = get();
                    if (callback != null) {
                        callback.accept(images);
                    }
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error al cargar imágenes asíncronamente", e);
                    if (callback != null) {
                        callback.accept(new ArrayList<>());
                    }
                }
            }
        };
        worker.execute();
    }
    
    /**
     * Carga un clip de audio de forma asíncrona
     * @param path Ruta del recurso
     * @param callback Callback que se ejecuta cuando el clip está cargado
     */
    public void loadAudioAsync(String path, Consumer<Clip> callback) {
        SwingWorker<Clip, Void> worker = new SwingWorker<Clip, Void>() {
            @Override
            protected Clip doInBackground() throws Exception {
                return loadAudio(path);
            }
            
            @Override
            protected void done() {
                try {
                    Clip clip = get();
                    if (callback != null) {
                        callback.accept(clip);
                    }
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error al cargar audio asíncronamente: " + path, e);
                    if (callback != null) {
                        callback.accept(null);
                    }
                }
            }
        };
        worker.execute();
    }
    
    /**
     * Carga una imagen de forma síncrona (usa caché)
     * @param path Ruta del recurso
     * @return ImageIcon cargado o null si falla
     */
    public ImageIcon loadImage(String path) {
        return cache.getImage(path, () -> {
            try {
                URL url = ResourceLoader.class.getResource(path);
                if (url != null) {
                    return new ImageIcon(url);
                }
            } catch (Exception e) {
                logger.log(Level.WARNING, "Error al cargar imagen: " + path, e);
            }
            return null;
        });
    }
    
    /**
     * Carga una imagen y la escala
     * @param path Ruta del recurso
     * @param width Ancho deseado
     * @param height Alto deseado
     * @return ImageIcon escalado o null si falla
     */
    public ImageIcon loadImageScaled(String path, int width, int height) {
        String cacheKey = path + "_" + width + "x" + height;
        return cache.getImage(cacheKey, () -> {
            ImageIcon original = loadImage(path);
            if (original != null) {
                return new ImageIcon(original.getImage().getScaledInstance(
                    width, height, java.awt.Image.SCALE_DEFAULT));
            }
            return null;
        });
    }
    
    /**
     * Carga un clip de audio de forma síncrona (usa caché)
     * @param path Ruta del recurso
     * @return Clip cargado o null si falla
     */
    public Clip loadAudio(String path) {
        return cache.getAudio(path, () -> {
            try {
                URL url = ResourceLoader.class.getResource(path);
                if (url != null) {
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    return clip;
                }
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                logger.log(Level.WARNING, "Error al cargar audio: " + path, e);
            }
            return null;
        });
    }
    
    /**
     * Carga una URL de forma síncrona (usa caché)
     * @param path Ruta del recurso
     * @return URL cargada o null si falla
     */
    public URL loadURL(String path) {
        return cache.getURL(path, () -> {
            return ResourceLoader.class.getResource(path);
        });
    }
}

