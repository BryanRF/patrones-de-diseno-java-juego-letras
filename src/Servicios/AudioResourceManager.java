package Servicios;

import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Gestor optimizado de recursos de audio e imágenes
 * Integrado con ResourceCache para mejor rendimiento
 * Refactorizado siguiendo principios SOLID
 */
public class AudioResourceManager {
    private static final Logger logger = Logger.getLogger(AudioResourceManager.class.getName());
    private final ResourceLoader resourceLoader;
    
    // Listas de clips de audio (inmutables después de la carga)
    private final List<Clip> adiosH;
    private final List<Clip> adiosD;
    private final List<Clip> ayudaH;
    private final List<Clip> ayudaD;
    private final List<Clip> holaD;      // Hola del duende
    private final List<Clip> holaH;      // Hola del hada
    private final List<Clip> jugarD;     // Jugar del duende
    private final List<Clip> jugarH;     // Jugar del hada
    private final List<Clip> errorD;     // Error del duende (inténtalo)
    private final List<Clip> errorH;     // Error del hada (inténtalo)
    private final List<Clip> buena;
    private final List<Clip> buenaH;
    private final List<Clip> aceptarD;  // Aceptar del duende
    private final List<Clip> aceptarH;  // Aceptar del hada
    private final List<Clip> cancelarD;  // Cancelar del duende
    private final List<Clip> cancelarH; // Cancelar del hada
    private final List<Clip> menuD;      // Menu del duende
    private final List<Clip> menuH;      // Menu del hada
    private final List<Clip> musicaOFFD;  // Musica OFF del duende
    private final List<Clip> musicaOFFH;  // Musica OFF del hada
    private final List<Clip> musicaOND;   // Musica ON del duende
    private final List<Clip> musicaONH;   // Musica ON del hada
    private final List<Clip> silenciarBotonesOND;  // Silenciar botones ON del duende
    private final List<Clip> silenciarBotonesONH;  // Silenciar botones ON del hada
    private final List<Clip> silenciarBotonesOFFD; // Silenciar botones OFF del duende
    private final List<Clip> silenciarBotonesOFFH; // Silenciar botones OFF del hada
    private final List<Clip> finD;        // Fin del duende
    private final List<Clip> finH;        // Fin del hada
    
    // Clips individuales
    private final Clip bienvenida;
    private final Clip bienvenida1;
    private final Clip ambiente;
    private final Clip palabraCompleta;
    private final Clip blup;
    private final Clip blop;
    private final Clip ajustesH;
    private final Clip ajustesD;
    private final Clip tutorial;
    
    // Lista de fondos
    private final List<URL> fondo;
    
    public AudioResourceManager() {
        this.resourceLoader = new ResourceLoader();
        
        // Inicializar listas
        this.adiosH = new ArrayList<>();
        this.adiosD = new ArrayList<>();
        this.ayudaH = new ArrayList<>();
        this.ayudaD = new ArrayList<>();
        this.holaD = new ArrayList<>();
        this.holaH = new ArrayList<>();
        this.jugarD = new ArrayList<>();
        this.jugarH = new ArrayList<>();
        this.errorD = new ArrayList<>();
        this.errorH = new ArrayList<>();
        this.buena = new ArrayList<>();
        this.buenaH = new ArrayList<>();
        this.aceptarD = new ArrayList<>();
        this.aceptarH = new ArrayList<>();
        this.cancelarD = new ArrayList<>();
        this.cancelarH = new ArrayList<>();
        this.menuD = new ArrayList<>();
        this.menuH = new ArrayList<>();
        this.musicaOFFD = new ArrayList<>();
        this.musicaOFFH = new ArrayList<>();
        this.musicaOND = new ArrayList<>();
        this.musicaONH = new ArrayList<>();
        this.silenciarBotonesOND = new ArrayList<>();
        this.silenciarBotonesONH = new ArrayList<>();
        this.silenciarBotonesOFFD = new ArrayList<>();
        this.silenciarBotonesOFFH = new ArrayList<>();
        this.finD = new ArrayList<>();
        this.finH = new ArrayList<>();
        this.fondo = new ArrayList<>();
        
        // Inicializar clips individuales (campos final deben inicializarse en el constructor)
        // Música
        this.ambiente = cargarClip("/recursos/audios/musica/M_J_bucle_ambiente.wav");
        // Personajes
        this.bienvenida1 = cargarClip("/recursos/audios/personajes/hada/hada_bienvenido.wav");
        this.bienvenida = cargarClip("/recursos/audios/personajes/duende/duende_bienvenido.wav");
        this.ajustesH = cargarClip("/recursos/audios/personajes/hada/ajustes_hada.wav");
        this.ajustesD = cargarClip("/recursos/audios/personajes/duende/ajustes_duende.wav");
        // Efectos
        this.palabraCompleta = cargarClip("/recursos/audios/efectos/palabra_completa.wav");
        this.blup = cargarClip("/recursos/audios/efectos/blup.wav");
        this.blop = cargarClip("/recursos/audios/efectos/blop.wav");
        this.tutorial = cargarClip("/recursos/audios/efectos/tutorial_genio.wav");
        
        // Cargar recursos
        cargarAudios();
        cargarFondos();
    }
    
    private void cargarAudios() {
        // Cargar listas de clips - Personajes (separados por personaje)
        cargarListaClips(holaD, "/recursos/audios/personajes/duende/duende_holadenuevo.wav");
        cargarListaClips(holaH, "/recursos/audios/personajes/hada/hada_hola.wav");
        cargarListaClips(jugarD, "/recursos/audios/personajes/duende/duende_jugar.wav");
        cargarListaClips(jugarH, "/recursos/audios/personajes/hada/hada_jugar.wav");
        cargarListaClips(errorD, "/recursos/audios/personajes/duende/duende_intentalo.wav");
        // Nota: Si existe hada_intentalo.wav, agregarlo aquí
        // cargarListaClips(errorH, "/recursos/audios/personajes/hada/hada_intentalo.wav");
        cargarListaClips(adiosD, "/recursos/audios/personajes/duende/hasta_proxima_d.wav", 
                        "/recursos/audios/personajes/duende/adios_d.wav");
        cargarListaClips(ayudaH, "/recursos/audios/personajes/hada/ayuda_h.wav", 
                        "/recursos/audios/personajes/hada/ayuda1_h.wav");
        cargarListaClips(adiosH, "/recursos/audios/personajes/hada/hasta_proxima_h.wav", 
                        "/recursos/audios/personajes/hada/adios_h.wav");
        cargarListaClips(ayudaD, "/recursos/audios/personajes/duende/duende_ayuda1.wav", 
                        "/recursos/audios/personajes/duende/ayuda_duende.wav");
        cargarListaClips(finD, "/recursos/audios/personajes/duende/duende_Fin.wav");
        cargarListaClips(finH, "/recursos/audios/personajes/hada/hada_fin.wav");
        cargarListaClips(buena, "/recursos/audios/personajes/duende/duende_bienHecho.wav", 
                        "/recursos/audios/personajes/duende/duende_buenTrabajo.wav",
                        "/recursos/audios/personajes/duende/duende_correcto.wav", 
                        "/recursos/audios/personajes/duende/duende_estupendo.wav");
        cargarListaClips(buenaH, "/recursos/audios/personajes/hada/hada_excelente.wav", 
                        "/recursos/audios/personajes/hada/hada_correcto.wav", 
                        "/recursos/audios/personajes/hada/hada_buenTrabajo.wav");
        
        // Cargar otros sonidos - Personajes (separados por personaje)
        cargarListaClips(aceptarD, "/recursos/audios/personajes/duende/duende_aceptar.wav");
        cargarListaClips(aceptarH, "/recursos/audios/personajes/hada/hada_aceptar.wav");
        cargarListaClips(cancelarD, "/recursos/audios/personajes/duende/duende_cancelado.wav");
        cargarListaClips(cancelarH, "/recursos/audios/personajes/hada/hada_cancelar.wav");
        cargarListaClips(menuD, "/recursos/audios/personajes/duende/duende_menu.wav");
        cargarListaClips(menuH, "/recursos/audios/personajes/hada/hada_menu.wav");
        cargarListaClips(musicaOND, "/recursos/audios/personajes/duende/duende_musicaON.wav");
        cargarListaClips(musicaONH, "/recursos/audios/personajes/hada/hada_musicaON.wav");
        cargarListaClips(musicaOFFD, "/recursos/audios/personajes/duende/duende_musicaOFF.wav");
        cargarListaClips(musicaOFFH, "/recursos/audios/personajes/hada/hada_musicaOFF.wav");
        cargarListaClips(silenciarBotonesOFFD, "/recursos/audios/personajes/duende/duende_silenciarOFF.wav");
        cargarListaClips(silenciarBotonesOFFH, "/recursos/audios/personajes/hada/hada_silenciarOFF.wav");
        cargarListaClips(silenciarBotonesOND, "/recursos/audios/personajes/duende/duende_silenciarON.wav");
        cargarListaClips(silenciarBotonesONH, "/recursos/audios/personajes/hada/hada_silenciarON.wav");
    }
    
    private void cargarFondos() {
        for (int i = 0; i <= 12; i++) {
            URL url = resourceLoader.loadURL(String.format("/recursos/imagenes/fondos/fondo_pantalla_%d.png", i));
            if (url != null) {
                fondo.add(url);
            }
        }
    }
    
    private Clip cargarClip(String path) {
        return resourceLoader.loadAudio(path);
    }
    
    private void cargarListaClips(List<Clip> lista, String... paths) {
        for (String path : paths) {
            Clip clip = cargarClip(path);
            if (clip != null) {
                lista.add(clip);
            } else {
                logger.warning("No se pudo cargar el audio: " + path);
            }
        }
    }
    
    // Getters (sin setters para mantener inmutabilidad)
    public Clip getBienvenida() {
        return bienvenida;
    }
    
    public Clip getBienvenida1() {
        return bienvenida1;
    }
    
    public Clip getAmbiente() {
        return ambiente;
    }
    
    public Clip getPalabraCompleta() {
        return palabraCompleta;
    }
    
    public Clip getBlup() {
        return blup;
    }
    
    public Clip getBlop() {
        return blop;
    }
    
    public Clip getAjustesH() {
        return ajustesH;
    }
    
    public Clip getAjustesD() {
        return ajustesD;
    }
    
    public Clip getTutorial() {
        return tutorial;
    }
    
    public List<Clip> getAdiosH() {
        return Collections.unmodifiableList(adiosH);
    }
    
    public List<Clip> getAdiosD() {
        return Collections.unmodifiableList(adiosD);
    }
    
    public List<Clip> getAyudaH() {
        return Collections.unmodifiableList(ayudaH);
    }
    
    public List<Clip> getAyudaD() {
        return Collections.unmodifiableList(ayudaD);
    }
    
    public List<Clip> getHolaD() {
        return Collections.unmodifiableList(holaD);
    }
    
    public List<Clip> getHolaH() {
        return Collections.unmodifiableList(holaH);
    }
    
    public List<Clip> getJugarD() {
        return Collections.unmodifiableList(jugarD);
    }
    
    public List<Clip> getJugarH() {
        return Collections.unmodifiableList(jugarH);
    }
    
    public List<Clip> getErrorD() {
        return Collections.unmodifiableList(errorD);
    }
    
    public List<Clip> getErrorH() {
        return Collections.unmodifiableList(errorH);
    }
    
    // Métodos legacy para compatibilidad (retornan lista combinada)
    @Deprecated
    public List<Clip> getHola() {
        List<Clip> combinado = new ArrayList<>(holaD);
        combinado.addAll(holaH);
        return Collections.unmodifiableList(combinado);
    }
    
    @Deprecated
    public List<Clip> getJugar() {
        List<Clip> combinado = new ArrayList<>(jugarD);
        combinado.addAll(jugarH);
        return Collections.unmodifiableList(combinado);
    }
    
    public List<Clip> getBuena() {
        return Collections.unmodifiableList(buena);
    }
    
    public List<Clip> getBuenaH() {
        return Collections.unmodifiableList(buenaH);
    }
    
    public List<Clip> getAceptarD() {
        return Collections.unmodifiableList(aceptarD);
    }
    
    public List<Clip> getAceptarH() {
        return Collections.unmodifiableList(aceptarH);
    }
    
    public List<Clip> getCancelarD() {
        return Collections.unmodifiableList(cancelarD);
    }
    
    public List<Clip> getCancelarH() {
        return Collections.unmodifiableList(cancelarH);
    }
    
    // Métodos legacy para compatibilidad (retornan lista combinada)
    @Deprecated
    public List<Clip> getAceptar() {
        List<Clip> combinado = new ArrayList<>(aceptarD);
        combinado.addAll(aceptarH);
        return Collections.unmodifiableList(combinado);
    }
    
    @Deprecated
    public List<Clip> getCancelar() {
        List<Clip> combinado = new ArrayList<>(cancelarD);
        combinado.addAll(cancelarH);
        return Collections.unmodifiableList(combinado);
    }
    
    public List<Clip> getMusicaOFFD() {
        return Collections.unmodifiableList(musicaOFFD);
    }
    
    public List<Clip> getMusicaOFFH() {
        return Collections.unmodifiableList(musicaOFFH);
    }
    
    public List<Clip> getMusicaOND() {
        return Collections.unmodifiableList(musicaOND);
    }
    
    public List<Clip> getMusicaONH() {
        return Collections.unmodifiableList(musicaONH);
    }
    
    public List<Clip> getSilenciarBotonesOND() {
        return Collections.unmodifiableList(silenciarBotonesOND);
    }
    
    public List<Clip> getSilenciarBotonesONH() {
        return Collections.unmodifiableList(silenciarBotonesONH);
    }
    
    public List<Clip> getSilenciarBotonesOFFD() {
        return Collections.unmodifiableList(silenciarBotonesOFFD);
    }
    
    public List<Clip> getSilenciarBotonesOFFH() {
        return Collections.unmodifiableList(silenciarBotonesOFFH);
    }
    
    public List<Clip> getFinD() {
        return Collections.unmodifiableList(finD);
    }
    
    public List<Clip> getFinH() {
        return Collections.unmodifiableList(finH);
    }
    
    // Métodos legacy para compatibilidad (retornan lista combinada)
    @Deprecated
    public List<Clip> getMusicaOFF() {
        List<Clip> combinado = new ArrayList<>(musicaOFFD);
        combinado.addAll(musicaOFFH);
        return Collections.unmodifiableList(combinado);
    }
    
    @Deprecated
    public List<Clip> getMusicaON() {
        List<Clip> combinado = new ArrayList<>(musicaOND);
        combinado.addAll(musicaONH);
        return Collections.unmodifiableList(combinado);
    }
    
    @Deprecated
    public List<Clip> getSilenciarBotonesON() {
        List<Clip> combinado = new ArrayList<>(silenciarBotonesOND);
        combinado.addAll(silenciarBotonesONH);
        return Collections.unmodifiableList(combinado);
    }
    
    @Deprecated
    public List<Clip> getSilenciarBotonesOFF() {
        List<Clip> combinado = new ArrayList<>(silenciarBotonesOFFD);
        combinado.addAll(silenciarBotonesOFFH);
        return Collections.unmodifiableList(combinado);
    }
    
    @Deprecated
    public List<Clip> getFin() {
        List<Clip> combinado = new ArrayList<>(finD);
        combinado.addAll(finH);
        return Collections.unmodifiableList(combinado);
    }
    
    public List<Clip> getMenuD() {
        return Collections.unmodifiableList(menuD);
    }
    
    public List<Clip> getMenuH() {
        return Collections.unmodifiableList(menuH);
    }
    
    // Método legacy para compatibilidad (retorna lista combinada)
    @Deprecated
    public List<Clip> getMenu() {
        List<Clip> combinado = new ArrayList<>(menuD);
        combinado.addAll(menuH);
        return Collections.unmodifiableList(combinado);
    }
    
    public List<URL> getFondo() {
        return Collections.unmodifiableList(fondo);
    }
}

