package Controlador.Commands;

import Controlador.JuegoController;
import java.util.logging.Logger;

/**
 * Comando concreto para seleccionar una letra
 * Patrón Command
 */
public class SeleccionarLetraCommand implements Command {
    private static final Logger logger = Logger.getLogger(SeleccionarLetraCommand.class.getName());
    private final JuegoController juegoController;
    private final char letra;
    
    public SeleccionarLetraCommand(JuegoController juegoController, char letra) {
        this.juegoController = juegoController;
        this.letra = Character.toUpperCase(letra);
    }
    
    @Override
    public void execute() {
        if (juegoController != null) {
            juegoController.procesarLetra(letra);
        } else {
            logger.warning("JuegoController es null, no se puede procesar la letra: " + letra);
        }
    }
    
    public char getLetra() {
        return letra;
    }
}

