package Controlador.Commands;

import Controlador.JuegoController;
import Controlador.VistaController;
import Controlador.AudioController;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Factory para crear comandos
 * Centraliza la creación de comandos y facilita su reutilización
 * Patrón Factory
 */
public class CommandFactory {
    private static final Logger logger = Logger.getLogger(CommandFactory.class.getName());
    private final JuegoController juegoController;
    private final VistaController vistaController;
    private final AudioController audioController;
    private final Map<Character, SeleccionarLetraCommand> letraCommands;
    
    public CommandFactory(JuegoController juegoController, 
                         VistaController vistaController,
                         AudioController audioController) {
        this.juegoController = juegoController;
        this.vistaController = vistaController;
        this.audioController = audioController;
        this.letraCommands = new HashMap<>();
    }
    
    /**
     * Crea o obtiene un comando para seleccionar una letra
     */
    public SeleccionarLetraCommand crearSeleccionarLetraCommand(char letra) {
        char letraUpper = Character.toUpperCase(letra);
        return letraCommands.computeIfAbsent(letraUpper, 
            l -> new SeleccionarLetraCommand(juegoController, l));
    }
    
    /**
     * Crea un comando para iniciar el juego
     * @param indicePersonaje Índice del personaje (0=duende, 1=hada)
     */
    public JugarCommand crearJugarCommand(int indicePersonaje) {
        return new JugarCommand(vistaController, audioController, indicePersonaje);
    }
    
    /**
     * Crea un comando para pasar a la siguiente palabra
     */
    public SiguientePalabraCommand crearSiguientePalabraCommand() {
        return new SiguientePalabraCommand(juegoController, audioController);
    }
    
    /**
     * Crea un comando para mostrar ayuda
     * @param indicePersonaje Índice del personaje (0=duende, 1=hada)
     */
    public AyudaCommand crearAyudaCommand(int indicePersonaje) {
        return new AyudaCommand(juegoController, audioController, indicePersonaje);
    }
    
    /**
     * Crea un comando para mostrar el menú
     */
    public MostrarMenuCommand crearMostrarMenuCommand() {
        return new MostrarMenuCommand(vistaController, audioController);
    }
    
    /**
     * Crea un comando para mostrar ajustes
     * @param indicePersonaje Índice del personaje (0=duende, 1=hada)
     */
    public MostrarAjustesCommand crearMostrarAjustesCommand(int indicePersonaje) {
        return new MostrarAjustesCommand(vistaController, audioController, indicePersonaje);
    }
    
    /**
     * Crea un comando para salir
     * @param indicePersonaje Índice del personaje (0=duende, 1=hada)
     */
    public SalirCommand crearSalirCommand(int indicePersonaje) {
        return new SalirCommand(audioController, indicePersonaje);
    }
}

