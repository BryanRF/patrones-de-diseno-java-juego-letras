package Servicios;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.List;

/**
 * Adaptador de compatibilidad para mantener la interfaz de CargarRecursos
 * mientras se usa internamente AudioResourceManager
 * 
 * @deprecated Usar AudioResourceManager directamente en código nuevo
 */
@Deprecated
public class CargarRecursosAdapter {
    private final AudioResourceManager resourceManager;
    
    public CargarRecursosAdapter() {
        this.resourceManager = new AudioResourceManager();
    }
    
    public Clip getBienvenida() {
        return resourceManager.getBienvenida();
    }
    
    public Clip getBienvenida1() {
        return resourceManager.getBienvenida1();
    }
    
    public Clip getAmbiente() {
        return resourceManager.getAmbiente();
    }
    
    public Clip getPalabraCompleta() {
        return resourceManager.getPalabraCompleta();
    }
    
    public Clip getBlup() {
        return resourceManager.getBlup();
    }
    
    public Clip getBlop() {
        return resourceManager.getBlop();
    }
    
    public Clip getAjustesH() {
        return resourceManager.getAjustesH();
    }
    
    public Clip getAjustesD() {
        return resourceManager.getAjustesD();
    }
    
    public Clip getTutorial() {
        return resourceManager.getTutorial();
    }
    
    public List<Clip> getAdiosH() {
        return resourceManager.getAdiosH();
    }
    
    public List<Clip> getAdiosD() {
        return resourceManager.getAdiosD();
    }
    
    public List<Clip> getAyudaH() {
        return resourceManager.getAyudaH();
    }
    
    public List<Clip> getAyudaD() {
        return resourceManager.getAyudaD();
    }
    
    public List<Clip> getHola() {
        return resourceManager.getHola();
    }
    
    public List<Clip> getBuena() {
        return resourceManager.getBuena();
    }
    
    public List<Clip> getBuenaH() {
        return resourceManager.getBuenaH();
    }
    
    public List<Clip> getAceptar() {
        return resourceManager.getAceptar();
    }
    
    public List<Clip> getCancelar() {
        return resourceManager.getCancelar();
    }
    
    public List<Clip> getMusicaOFF() {
        return resourceManager.getMusicaOFF();
    }
    
    public List<Clip> getMusicaON() {
        return resourceManager.getMusicaON();
    }
    
    public List<Clip> getJugar() {
        return resourceManager.getJugar();
    }
    
    public List<Clip> getMenu() {
        return resourceManager.getMenu();
    }
    
    public List<Clip> getSilenciarBotonesON() {
        return resourceManager.getSilenciarBotonesON();
    }
    
    public List<Clip> getSilenciarBotonesOFF() {
        return resourceManager.getSilenciarBotonesOFF();
    }
    
    public List<Clip> getFin() {
        return resourceManager.getFin();
    }
    
    public List<URL> getFondo() {
        return resourceManager.getFondo();
    }
}

