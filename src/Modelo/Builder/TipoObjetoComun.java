package Modelo.Builder;

import Modelo.Database.DatabaseManager;
import Modelo.Database.DatabaseSeeder;
import Modelo.Repository.PalabraRepository;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Builder concreto común para todos los tipos de objetos
 * Implementa la construcción de objetos desde la base de datos
 */
public abstract class TipoObjetoComun extends Tipo_Objeto {
    protected String[] listaDePalabras;
    protected String[] listaDeDescripcion;
    protected String[] listaUrlImagen;
    protected String[] listaUrlAudio_h;
    protected String[] listaUrlAudio_d;
    protected List<ImageIcon> listaImagenes = new ArrayList<>();
    protected List<Clip> listadoAudios_duende = new ArrayList<>();
    protected List<Clip> listadoAudios_hada = new ArrayList<>();

    private final PalabraRepository palabraRepository;
    private static final Logger logger = Logger.getLogger(TipoObjetoComun.class.getName());

    public TipoObjetoComun() {
        this.palabraRepository = new PalabraRepository();
        initializeDatabaseIfNeeded();
    }

    /**
     * Inicializa la base de datos y la pobla si es necesario
     */
    private void initializeDatabaseIfNeeded() {
        try {
            DatabaseManager dbManager = DatabaseManager.getInstance();
            if (dbManager.isDatabaseEmpty()) {
                DatabaseSeeder seeder = new DatabaseSeeder();
                seeder.seed();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al inicializar la base de datos", e);
        }
    }

    /**
     * Carga los recursos desde la base de datos SQLite
     * @param nombreCategoria El nombre de la categoría (Colores, Habitaciones, Paises)
     */
    public void cargarRecursosDesdeBaseDatos(String nombreCategoria) {
        try {
            List<PalabraRepository.PalabraData> palabrasData = palabraRepository.getPalabrasByCategoria(nombreCategoria);
            
            if (palabrasData.isEmpty()) {
                logger.warning("No se encontraron palabras para la categoría: " + nombreCategoria);
                return;
            }

            // Convertir a arrays para mantener compatibilidad con el código existente
            listaDePalabras = new String[palabrasData.size()];
            listaDeDescripcion = new String[palabrasData.size()];
            listaUrlImagen = new String[palabrasData.size()];
            listaUrlAudio_d = new String[palabrasData.size()];
            listaUrlAudio_h = new String[palabrasData.size()];

            // Cargar datos y recursos
            for (int i = 0; i < palabrasData.size(); i++) {
                PalabraRepository.PalabraData palabra = palabrasData.get(i);
                listaDePalabras[i] = palabra.getPalabra();
                listaDeDescripcion[i] = palabra.getDescripcion();
                listaUrlImagen[i] = palabra.getRutaImagen();
                listaUrlAudio_d[i] = palabra.getRutaAudioDuende();
                listaUrlAudio_h[i] = palabra.getRutaAudioHada();

                // Cargar imágenes y audios
                URL imagenUrl = getClass().getResource(listaUrlImagen[i]);
                if (imagenUrl != null) {
                    listaImagenes.add(new ImageIcon(imagenUrl));
                } else {
                    logger.warning("No se encontró la imagen: " + listaUrlImagen[i]);
                    listaImagenes.add(null);
                }

                URL audioDuendeUrl = getClass().getResource(listaUrlAudio_d[i]);
                if (audioDuendeUrl != null) {
                    listadoAudios_duende.add(cargarClip(audioDuendeUrl));
                } else {
                    logger.warning("No se encontró el audio del duende: " + listaUrlAudio_d[i]);
                    listadoAudios_duende.add(null);
                }

                URL audioHadaUrl = getClass().getResource(listaUrlAudio_h[i]);
                if (audioHadaUrl != null) {
                    listadoAudios_hada.add(cargarClip(audioHadaUrl));
                } else {
                    logger.warning("No se encontró el audio del hada: " + listaUrlAudio_h[i]);
                    listadoAudios_hada.add(null);
                }
            }

            logger.info("Recursos cargados desde la base de datos para la categoría: " + nombreCategoria);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al cargar recursos desde la base de datos", e);
            throw new RuntimeException("No se pudo cargar los recursos de la categoría: " + nombreCategoria, e);
        }
    }

    // Método para cargar archivos de audio como Clip
    protected Clip cargarClip(URL url) {
        Clip clip = null;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(TipoObjetoComun.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clip;
    }

    @Override
    public void buildListaPalabras() {
        Objeto.setListPalabras(listaDePalabras);
    }

    @Override
    public void buildListaDescripcion() {
        Objeto.setListDescripcion(listaDeDescripcion);
    }

    @Override
    public void buildListaImagen() {
        Objeto.setlistaDeImagen(listaImagenes);
    }

    @Override
    public void buildListaAudio_duende() {
        Objeto.setlistadoDeAudio_duende(listadoAudios_duende);
    }

    @Override
    public void buildListaAudio_hada() {
        Objeto.setlistadoDeAudio_hada(listadoAudios_hada);
    }
}

