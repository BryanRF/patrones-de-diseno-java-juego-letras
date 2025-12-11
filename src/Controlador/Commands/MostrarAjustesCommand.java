package Controlador.Commands;

import Controlador.VistaController;
import Controlador.AudioController;
import java.util.logging.Logger;

/**
 * Comando concreto para mostrar la ventana de ajustes
 * Patrón Command
 */
public class MostrarAjustesCommand implements Command {
    private static final Logger logger = Logger.getLogger(MostrarAjustesCommand.class.getName());
    private final VistaController vistaController;
    private final AudioController audioController;
    private final int indicePersonaje;
    
    public MostrarAjustesCommand(VistaController vistaController, AudioController audioController, int indicePersonaje) {
        this.vistaController = vistaController;
        this.audioController = audioController;
        this.indicePersonaje = indicePersonaje;
    }
    
    @Override
    public void execute() {
        if (audioController != null) {
            audioController.reproducirSonidoBoton();
        }
        if (vistaController != null) {
            vistaController.mostrarAjustes();
        }
        if (audioController != null) {
            audioController.reproducirAjustes(indicePersonaje);
        }
    }
}

