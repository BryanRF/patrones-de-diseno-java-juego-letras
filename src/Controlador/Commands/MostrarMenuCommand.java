package Controlador.Commands;

import Controlador.VistaController;
import Controlador.AudioController;
import java.util.logging.Logger;

/**
 * Comando concreto para mostrar el menú principal
 * Patrón Command
 */
public class MostrarMenuCommand implements Command {
    private static final Logger logger = Logger.getLogger(MostrarMenuCommand.class.getName());
    private final VistaController vistaController;
    private final AudioController audioController;
    
    public MostrarMenuCommand(VistaController vistaController, AudioController audioController) {
        this.vistaController = vistaController;
        this.audioController = audioController;
    }
    
    @Override
    public void execute() {
        if (audioController != null) {
            audioController.reproducirSonidoBoton();
        }
        if (vistaController != null) {
            vistaController.mostrarMenu();
        }
    }
}

