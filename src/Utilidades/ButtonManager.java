package Utilidades;

import javax.swing.JButton;
import java.util.HashMap;
import java.util.Map;

/**
 * Gestor de botones del teclado virtual
 * Centraliza la gestión de estados de los botones para evitar código duplicado
 */
public class ButtonManager {
    private final Map<Character, JButton> buttonMap;
    private final JButton[] allButtons;
    
    /**
     * Constructor que inicializa el mapa de botones
     * @param buttons Array de todos los botones del teclado
     */
    public ButtonManager(JButton[] buttons) {
        this.allButtons = buttons;
        this.buttonMap = new HashMap<>();
        initializeButtonMap();
    }
    
    /**
     * Inicializa el mapa de botones con sus caracteres correspondientes
     */
    private void initializeButtonMap() {
        // Mapeo de caracteres a botones (orden: Q,W,E,R,T,Y,U,I,O,P,A,S,D,F,G,H,J,K,L,Ñ,Z,X,C,V,B,N,M)
        char[] letras = {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P',
                        'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Ñ',
                        'Z', 'X', 'C', 'V', 'B', 'N', 'M'};
        
        for (int i = 0; i < letras.length && i < allButtons.length; i++) {
            if (allButtons[i] != null) {
                buttonMap.put(letras[i], allButtons[i]);
            }
        }
    }
    
    /**
     * Activa todos los botones
     */
    public void activarTodos() {
        for (JButton button : allButtons) {
            if (button != null) {
                button.setEnabled(true);
            }
        }
    }
    
    /**
     * Desactiva todos los botones
     */
    public void desactivarTodos() {
        for (JButton button : allButtons) {
            if (button != null) {
                button.setEnabled(false);
            }
        }
    }
    
    /**
     * Activa un botón específico por su letra
     * @param letra La letra del botón a activar
     */
    public void activar(char letra) {
        JButton button = buttonMap.get(Character.toUpperCase(letra));
        if (button != null) {
            button.setEnabled(true);
        }
    }
    
    /**
     * Desactiva un botón específico por su letra
     * @param letra La letra del botón a desactivar
     */
    public void desactivar(char letra) {
        JButton button = buttonMap.get(Character.toUpperCase(letra));
        if (button != null) {
            button.setEnabled(false);
        }
    }
    
    /**
     * Obtiene un botón por su letra
     * @param letra La letra del botón
     * @return El botón correspondiente o null si no existe
     */
    public JButton getButton(char letra) {
        return buttonMap.get(Character.toUpperCase(letra));
    }
    
    /**
     * Verifica si un botón está habilitado
     * @param letra La letra del botón
     * @return true si está habilitado, false en caso contrario
     */
    public boolean estaHabilitado(char letra) {
        JButton button = buttonMap.get(Character.toUpperCase(letra));
        return button != null && button.isEnabled();
    }
    
    /**
     * Resetea todos los botones (los activa)
     */
    public void reset() {
        activarTodos();
    }
}

