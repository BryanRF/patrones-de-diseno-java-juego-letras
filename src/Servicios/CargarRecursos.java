package Servicios;

import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Servicio para cargar recursos de audio e imágenes
 * Responsabilidad: Cargar y gestionar recursos multimedia
 * Optimizado para usar ResourceLoader y ResourceCache
 * 
 * @deprecated Considerar usar AudioResourceManager para mejor rendimiento
 */
@Deprecated
public class CargarRecursos {
    private static final Logger logger = Logger.getLogger(CargarRecursos.class.getName());
    private final ResourceLoader resourceLoader;

    // Listas de clips de audio
    private  List<Clip> adiosH = new ArrayList<>();
    private  List<Clip> adiosD = new ArrayList<>();
    private  List<Clip> ayudaH = new ArrayList<>();
    private  List<Clip> ayudaD = new ArrayList<>();
    private  List<Clip> holaD = new ArrayList<>();      // Hola del duende
    private  List<Clip> holaH = new ArrayList<>();      // Hola del hada
    private  List<Clip> jugarD = new ArrayList<>();     // Jugar del duende
    private  List<Clip> jugarH = new ArrayList<>();     // Jugar del hada
    private  List<Clip> errorD = new ArrayList<>();     // Error del duende (inténtalo)
    private  List<Clip> errorH = new ArrayList<>();     // Error del hada (inténtalo)
    private  List<Clip> buena = new ArrayList<>();
    private  List<Clip> buenaH = new ArrayList<>();
    private  List<Clip> aceptarD = new ArrayList<>();  // Aceptar del duende
    private  List<Clip> aceptarH = new ArrayList<>();  // Aceptar del hada
    private  List<Clip> cancelarD = new ArrayList<>(); // Cancelar del duende
    private  List<Clip> cancelarH = new ArrayList<>(); // Cancelar del hada
    private  List<Clip> menuD = new ArrayList<>();     // Menu del duende
    private  List<Clip> menuH = new ArrayList<>();     // Menu del hada
    private  List<Clip> musicaOFFD = new ArrayList<>();  // Musica OFF del duende
    private  List<Clip> musicaOFFH = new ArrayList<>();  // Musica OFF del hada
    private  List<Clip> musicaOND = new ArrayList<>();   // Musica ON del duende
    private  List<Clip> musicaONH = new ArrayList<>();   // Musica ON del hada
    private  List<Clip> silenciarBotonesOND = new ArrayList<>();  // Silenciar botones ON del duende
    private  List<Clip> silenciarBotonesONH = new ArrayList<>();  // Silenciar botones ON del hada
    private  List<Clip> silenciarBotonesOFFD = new ArrayList<>(); // Silenciar botones OFF del duende
    private  List<Clip> silenciarBotonesOFFH = new ArrayList<>(); // Silenciar botones OFF del hada
    private  List<Clip> finD = new ArrayList<>();        // Fin del duende
    private  List<Clip> finH = new ArrayList<>();        // Fin del hada
    
    // Clips individuales
    private Clip bienvenida, bienvenida1, ambiente, palabraCompleta, blup, blop, ajustesH, ajustesD, tutorial;

    // Lista de fondos
    private  List<URL> fondo = new ArrayList<>();

    public CargarRecursos() {
        this.resourceLoader = new ResourceLoader();
        cargarAudios();
        cargarFondos();
    }

    private void cargarAudios() {
        // Clips individuales - Música
        ambiente = cargarClip("/recursos/audios/musica/M_J_bucle_ambiente.wav");
        // Clips individuales - Personajes
        bienvenida1 = cargarClip("/recursos/audios/personajes/hada/hada_bienvenido.wav");
        bienvenida = cargarClip("/recursos/audios/personajes/duende/duende_bienvenido.wav");
        ajustesH = cargarClip("/recursos/audios/personajes/hada/ajustes_hada.wav");
        ajustesD = cargarClip("/recursos/audios/personajes/duende/ajustes_duende.wav");
        // Clips individuales - Efectos
        palabraCompleta = cargarClip("/recursos/audios/efectos/palabra_completa.wav");
        blup = cargarClip("/recursos/audios/efectos/blup.wav");
        blop = cargarClip("/recursos/audios/efectos/blop.wav");
        tutorial = cargarClip("/recursos/audios/efectos/tutorial_genio.wav");

        // Cargar listas de clips - Personajes (separados por personaje)
        cargarListaClips(holaD, "/recursos/audios/personajes/duende/duende_holadenuevo.wav");
        cargarListaClips(holaH, "/recursos/audios/personajes/hada/hada_hola.wav");
        cargarListaClips(jugarD, "/recursos/audios/personajes/duende/duende_jugar.wav");
        cargarListaClips(jugarH, "/recursos/audios/personajes/hada/hada_jugar.wav");
        cargarListaClips(errorD, "/recursos/audios/personajes/duende/duende_intentalo.wav");
        // Nota: Si existe hada_intentalo.wav, agregarlo aquí
        // cargarListaClips(errorH, "/recursos/audios/personajes/hada/hada_intentalo.wav");
        // Nota: Archivos hasta_proxima_d.wav, adios_d.wav, hasta_proxima_h.wav, adios_h.wav no existen
        // Las listas adiosD y adiosH se mantienen vacías
        // Nota: Archivos ayuda_h.wav y ayuda1_h.wav no existen
        // La lista ayudaH se mantiene vacía
        cargarListaClips(ayudaD, "/recursos/audios/personajes/duende/duende_ayuda1.wav", 
                        "/recursos/audios/personajes/duende/ayuda_duende.wav");
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
        cargarListaClips(finD, "/recursos/audios/personajes/duende/duende_Fin.wav");
        cargarListaClips(finH, "/recursos/audios/personajes/hada/hada_fin.wav");
    }

    private void cargarFondos() {
        // Comenzar desde 1 porque fondo_pantalla_0.png no existe
        for (int i = 1; i <= 12; i++) {
            URL url = resourceLoader.loadURL(String.format("/recursos/imagenes/fondos/fondo_pantalla_%d.png", i));
            if (url != null) {
                fondo.add(url);
            } else {
                logger.warning("No se pudo cargar fondo: fondo_pantalla_" + i + ".png");
            }
        }
    }

    private Clip cargarClip(String path) {
        Clip clip = resourceLoader.loadAudio(path);
        if (clip == null) {
            logger.warning("No se pudo cargar audio: " + path);
        }
        return clip;
    }

    private void cargarListaClips(List<Clip> lista, String... paths) {
        for (String path : paths) {
            lista.add(cargarClip(path));
        }
    }

    public Clip getBienvenida() {
        return bienvenida;
    }

    public void setBienvenida(Clip bienvenida) {
        this.bienvenida = bienvenida;
    }

    public Clip getBienvenida1() {
        return bienvenida1;
    }

    public void setBienvenida1(Clip bienvenida1) {
        this.bienvenida1 = bienvenida1;
    }

    public Clip getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Clip ambiente) {
        this.ambiente = ambiente;
    }

    public Clip getPalabraCompleta() {
        return palabraCompleta;
    }

    public void setPalabraCompleta(Clip palabraCompleta) {
        this.palabraCompleta = palabraCompleta;
    }

    public Clip getBlup() {
        return blup;
    }

    public void setBlup(Clip blup) {
        this.blup = blup;
    }

    public Clip getBlop() {
        return blop;
    }

    public void setBlop(Clip blop) {
        this.blop = blop;
    }

    public Clip getAjustesH() {
        return ajustesH;
    }

    public void setAjustesH(Clip ajustesH) {
        this.ajustesH = ajustesH;
    }

    public Clip getAjustesD() {
        return ajustesD;
    }

    public void setAjustesD(Clip ajustesD) {
        this.ajustesD = ajustesD;
    }

    public Clip getTutorial() {
        return tutorial;
    }

    public void setTutorial(Clip tutorial) {
        this.tutorial = tutorial;
    }

    public List<Clip> getAdiosH() {
        return adiosH;
    }

    public void setAdiosH(List<Clip> adiosH) {
        this.adiosH = adiosH;
    }

    public List<Clip> getAdiosD() {
        return adiosD;
    }

    public void setAdiosD(List<Clip> adiosD) {
        this.adiosD = adiosD;
    }

    public List<Clip> getAyudaH() {
        return ayudaH;
    }

    public void setAyudaH(List<Clip> ayudaH) {
        this.ayudaH = ayudaH;
    }

    public List<Clip> getAyudaD() {
        return ayudaD;
    }

    public void setAyudaD(List<Clip> ayudaD) {
        this.ayudaD = ayudaD;
    }

    public List<Clip> getHolaD() {
        return holaD;
    }

    public List<Clip> getHolaH() {
        return holaH;
    }

    public List<Clip> getJugarD() {
        return jugarD;
    }

    public List<Clip> getJugarH() {
        return jugarH;
    }

    public List<Clip> getErrorD() {
        return errorD;
    }

    public List<Clip> getErrorH() {
        return errorH;
    }

    // Métodos legacy para compatibilidad (retornan lista combinada)
    @Deprecated
    public List<Clip> getHola() {
        List<Clip> combinado = new ArrayList<>(holaD);
        combinado.addAll(holaH);
        return combinado;
    }

    @Deprecated
    public void setHola(List<Clip> hola) {
        // Mantener para compatibilidad, pero no hacer nada
    }

    public List<Clip> getBuena() {
        return buena;
    }

    public void setBuena(List<Clip> buena) {
        this.buena = buena;
    }

    public List<Clip> getBuenaH() {
        return buenaH;
    }

    public void setBuenaH(List<Clip> buenaH) {
        this.buenaH = buenaH;
    }

    public List<Clip> getAceptarD() {
        return aceptarD;
    }

    public List<Clip> getAceptarH() {
        return aceptarH;
    }

    public List<Clip> getCancelarD() {
        return cancelarD;
    }

    public List<Clip> getCancelarH() {
        return cancelarH;
    }

    // Métodos legacy para compatibilidad (retornan lista combinada)
    @Deprecated
    public List<Clip> getAceptar() {
        List<Clip> combinado = new ArrayList<>(aceptarD);
        combinado.addAll(aceptarH);
        return combinado;
    }

    @Deprecated
    public void setAceptar(List<Clip> aceptar) {
        // Mantener para compatibilidad, pero no hacer nada
    }

    @Deprecated
    public List<Clip> getCancelar() {
        List<Clip> combinado = new ArrayList<>(cancelarD);
        combinado.addAll(cancelarH);
        return combinado;
    }

    @Deprecated
    public void setCancelar(List<Clip> cancelar) {
        // Mantener para compatibilidad, pero no hacer nada
    }

    public List<Clip> getMusicaOFFD() {
        return musicaOFFD;
    }

    public List<Clip> getMusicaOFFH() {
        return musicaOFFH;
    }

    public List<Clip> getMusicaOND() {
        return musicaOND;
    }

    public List<Clip> getMusicaONH() {
        return musicaONH;
    }

    public List<Clip> getSilenciarBotonesOND() {
        return silenciarBotonesOND;
    }

    public List<Clip> getSilenciarBotonesONH() {
        return silenciarBotonesONH;
    }

    public List<Clip> getSilenciarBotonesOFFD() {
        return silenciarBotonesOFFD;
    }

    public List<Clip> getSilenciarBotonesOFFH() {
        return silenciarBotonesOFFH;
    }

    public List<Clip> getFinD() {
        return finD;
    }

    public List<Clip> getFinH() {
        return finH;
    }

    // Métodos legacy para compatibilidad (retornan lista combinada)
    @Deprecated
    public List<Clip> getMusicaOFF() {
        List<Clip> combinado = new ArrayList<>(musicaOFFD);
        combinado.addAll(musicaOFFH);
        return combinado;
    }

    @Deprecated
    public void setMusicaOFF(List<Clip> musicaOFF) {
        // Mantener para compatibilidad, pero no hacer nada
    }

    @Deprecated
    public List<Clip> getMusicaON() {
        List<Clip> combinado = new ArrayList<>(musicaOND);
        combinado.addAll(musicaONH);
        return combinado;
    }

    @Deprecated
    public void setMusicaON(List<Clip> musicaON) {
        // Mantener para compatibilidad, pero no hacer nada
    }

    // Método legacy para compatibilidad (retorna lista combinada)
    @Deprecated
    public List<Clip> getJugar() {
        List<Clip> combinado = new ArrayList<>(jugarD);
        combinado.addAll(jugarH);
        return combinado;
    }

    @Deprecated
    public void setJugar(List<Clip> jugar) {
        // Mantener para compatibilidad, pero no hacer nada
    }

    public List<Clip> getMenuD() {
        return menuD;
    }

    public List<Clip> getMenuH() {
        return menuH;
    }

    // Método legacy para compatibilidad (retorna lista combinada)
    @Deprecated
    public List<Clip> getMenu() {
        List<Clip> combinado = new ArrayList<>(menuD);
        combinado.addAll(menuH);
        return combinado;
    }

    @Deprecated
    public void setMenu(List<Clip> menu) {
        // Mantener para compatibilidad, pero no hacer nada
    }

    // Métodos legacy para compatibilidad (retornan lista combinada)
    @Deprecated
    public List<Clip> getSilenciarBotonesON() {
        List<Clip> combinado = new ArrayList<>(silenciarBotonesOND);
        combinado.addAll(silenciarBotonesONH);
        return combinado;
    }

    @Deprecated
    public void setSilenciarBotonesON(List<Clip> silenciarBotonesON) {
        // Mantener para compatibilidad, pero no hacer nada
    }

    @Deprecated
    public List<Clip> getSilenciarBotonesOFF() {
        List<Clip> combinado = new ArrayList<>(silenciarBotonesOFFD);
        combinado.addAll(silenciarBotonesOFFH);
        return combinado;
    }

    @Deprecated
    public void setSilenciarBotonesOFF(List<Clip> silenciarBotonesOFF) {
        // Mantener para compatibilidad, pero no hacer nada
    }

    @Deprecated
    public List<Clip> getFin() {
        List<Clip> combinado = new ArrayList<>(finD);
        combinado.addAll(finH);
        return combinado;
    }

    @Deprecated
    public void setFin(List<Clip> fin) {
        // Mantener para compatibilidad, pero no hacer nada
    }

    public List<URL> getFondo() {
        return fondo;
    }

    public void setFondo(List<URL> fondo) {
        this.fondo = fondo;
    }

  
}

