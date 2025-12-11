package Controlador.Commands;

import Controlador.AudioController;
import java.util.logging.Logger;

/**
 * Comando concreto para salir de la aplicación
 * Patrón Command
 */
public class SalirCommand implements Command {
    private static final Logger logger = Logger.getLogger(SalirCommand.class.getName());
    private final AudioController audioController;
    private final int indicePersonaje;
    
    public SalirCommand(AudioController audioController, int indicePersonaje) {
        this.audioController = audioController;
        this.indicePersonaje = indicePersonaje;
    }
    
    @Override
    public void execute() {
        if (audioController != null) {
            audioController.reproducirSonidoBoton();
            audioController.reproducirAdios(indicePersonaje);
        }
        
        // Usar Timer para dar tiempo a que se reproduzca el audio antes de salir
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 500);
    }
}

