package Modelo.Models;

/**
 * Modelo que representa la configuración de la aplicación
 * Encapsula los datos de configuración siguiendo principios MVC
 */
public class ConfiguracionModel {
    private boolean modoVentanaSinDecoracion;
    private boolean sonidoBotonesHabilitado;
    private boolean musicaAmbienteHabilitada;
    private int resolucionAncho;
    private int resolucionAlto;
    private int fondoActual;
    
    public ConfiguracionModel() {
        this.modoVentanaSinDecoracion = true;
        this.sonidoBotonesHabilitado = true;
        this.musicaAmbienteHabilitada = true;
        this.fondoActual = 0;
    }
    
    public boolean isModoVentanaSinDecoracion() {
        return modoVentanaSinDecoracion;
    }
    
    public void setModoVentanaSinDecoracion(boolean modoVentanaSinDecoracion) {
        this.modoVentanaSinDecoracion = modoVentanaSinDecoracion;
    }
    
    public boolean isSonidoBotonesHabilitado() {
        return sonidoBotonesHabilitado;
    }
    
    public void setSonidoBotonesHabilitado(boolean sonidoBotonesHabilitado) {
        this.sonidoBotonesHabilitado = sonidoBotonesHabilitado;
    }
    
    public boolean isMusicaAmbienteHabilitada() {
        return musicaAmbienteHabilitada;
    }
    
    public void setMusicaAmbienteHabilitada(boolean musicaAmbienteHabilitada) {
        this.musicaAmbienteHabilitada = musicaAmbienteHabilitada;
    }
    
    public int getResolucionAncho() {
        return resolucionAncho;
    }
    
    public void setResolucionAncho(int resolucionAncho) {
        this.resolucionAncho = resolucionAncho;
    }
    
    public int getResolucionAlto() {
        return resolucionAlto;
    }
    
    public void setResolucionAlto(int resolucionAlto) {
        this.resolucionAlto = resolucionAlto;
    }
    
    public int getFondoActual() {
        return fondoActual;
    }
    
    public void setFondoActual(int fondoActual) {
        this.fondoActual = fondoActual;
    }
}

