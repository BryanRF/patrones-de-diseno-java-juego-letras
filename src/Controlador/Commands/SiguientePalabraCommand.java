package Controlador.Commands;

import Controlador.JuegoController;
import Controlador.AudioController;
import java.util.logging.Logger;

/**
 * Comando concreto para pasar a la siguiente palabra
 * Patrón Command
 */
public class SiguientePalabraCommand implements Command {
    private static final Logger logger = Logger.getLogger(SiguientePalabraCommand.class.getName());
    private final JuegoController juegoController;
    private final AudioController audioController;
    
    public SiguientePalabraCommand(JuegoController juegoController, AudioController audioController) {
        this.juegoController = juegoController;
        this.audioController = audioController;
    }
    
    @Override
    public void execute() {
        if (audioController != null) {
            audioController.reproducirSonidoBoton();
        }
        if (juegoController != null) {
            juegoController.siguientePalabra();
        }
    }
}

