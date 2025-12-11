package Servicios;

import javax.sound.sampled.Clip;

/**
 * Implementación del servicio de audio usando AudioResourceManager
 */
public class AudioResourceService implements AudioService {
    private final AudioResourceManager resourceManager;
    
    public AudioResourceService(AudioResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }
    
    @Override
    public void playButtonClickSound() {
        playClip(resourceManager.getBlup());
    }
    
    @Override
    public void playButtonHoverSound() {
        playClip(resourceManager.getBlop());
    }
    
    private void playClip(Clip clip) {
        if (clip != null) {
            try {
                clip.setFramePosition(0);
                clip.start();
            } catch (Exception e) {
                // Silenciar errores de audio
            }
        }
    }
}
