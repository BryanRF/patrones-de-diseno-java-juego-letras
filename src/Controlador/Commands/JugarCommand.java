package Controlador.Commands;

import Controlador.VistaController;
import Controlador.AudioController;
import java.util.logging.Logger;

/**
 * Comando concreto para iniciar el juego
 * Patrón Command
 */
public class JugarCommand implements Command {
    private static final Logger logger = Logger.getLogger(JugarCommand.class.getName());
    private final VistaController vistaController;
    private final AudioController audioController;
    private final int indicePersonaje;
    
    public JugarCommand(VistaController vistaController, AudioController audioController, int indicePersonaje) {
        this.vistaController = vistaController;
        this.audioController = audioController;
        this.indicePersonaje = indicePersonaje;
    }
    
    @Override
    public void execute() {
        if (vistaController != null) {
            vistaController.mostrarJuego();
        }
        if (audioController != null) {
            audioController.reproducirJugar(indicePersonaje);
        }
    }
}

