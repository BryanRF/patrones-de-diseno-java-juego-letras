package Controlador.Commands;

/**
 * Interfaz Command del patrón Command
 * Encapsula una acción como un objeto, permitiendo parametrizar clientes
 * con diferentes acciones, encolar operaciones, y soportar operaciones deshacer
 */
public interface Command {
    /**
     * Ejecuta el comando
     */
    void execute();
    
    /**
     * Deshace el comando (opcional)
     * @return true si se puede deshacer, false en caso contrario
     */
    default boolean canUndo() {
        return false;
    }
    
    /**
     * Deshace el comando
     */
    default void undo() {
        throw new UnsupportedOperationException("Este comando no soporta deshacer");
    }
}

