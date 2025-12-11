package Controlador.Commands;

import Controlador.JuegoController;
import Controlador.AudioController;
import java.util.logging.Logger;

/**
 * Comando concreto para mostrar ayuda (letra aleatoria)
 * Patrón Command
 */
public class AyudaCommand implements Command {
    private static final Logger logger = Logger.getLogger(AyudaCommand.class.getName());
    private final JuegoController juegoController;
    private final AudioController audioController;
    private final int indicePersonaje;
    
    public AyudaCommand(JuegoController juegoController, AudioController audioController, int indicePersonaje) {
        this.juegoController = juegoController;
        this.audioController = audioController;
        this.indicePersonaje = indicePersonaje;
    }
    
    @Override
    public void execute() {
        if (audioController != null) {
            audioController.reproducirSonidoBoton();
            audioController.reproducirAyuda(indicePersonaje);
        }
        if (juegoController != null) {
            juegoController.mostrarAyuda();
        }
    }
}

