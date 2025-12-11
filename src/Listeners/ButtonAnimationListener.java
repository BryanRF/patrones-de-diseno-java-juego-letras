package Listeners;

import Servicios.ResourceLoader;
import Servicios.AudioService;
import Vistas.Ventana_ajustes;
import Vistas.Ventana_juego;
import Vistas.Ventana_menu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Listener optimizado para manejar animaciones y sonidos de botones
 * Refactorizado para eliminar código duplicado y seguir principios SOLID
 */
public class ButtonAnimationListener implements MouseListener {
    private static final Logger logger = Logger.getLogger(ButtonAnimationListener.class.getName());
    
    private final Ventana_juego ventanaJuego;
    private final Ventana_menu ventanaMenu;
    private final Ventana_ajustes ventanaAjustes;
    private final ResourceLoader resourceLoader;
    private AudioService audioService;
    
    // Dimensiones de botones
    private int anchoButton = 90;
    private int altoButton = 80;
    private int nav100 = altoButton + 20;
    private int nav100Y = altoButton - 5;
    private int nav130 = altoButton + 50;
    private int nav320 = altoButton + 240;
    
    private int pos = 0;
    private boolean sonidoBotones = true;
    
    // Mapa para mapear botones a sus rutas de imágenes
    private final Map<JButton, ButtonImageConfig> buttonImageMap;
    
    /**
     * Configuración de imágenes para un botón
     */
    private static class ButtonImageConfig {
        final String normal;
        final String entered;
        final String clicked;
        final int width;
        final int height;
        
        ButtonImageConfig(String normal, String entered, String clicked, int width, int height) {
            this.normal = normal;
            this.entered = entered;
            this.clicked = clicked;
            this.width = width;
            this.height = height;
        }
    }
    
    public ButtonAnimationListener(Ventana_juego ventanaJuego,
                                   Ventana_menu ventanaMenu,
                                   Ventana_ajustes ventanaAjustes,
                                   ResourceLoader resourceLoader,
                                   AudioService audioService,
                                   boolean sonidoBotones,
                                   int pos) {
        this.ventanaJuego = ventanaJuego;
        this.ventanaMenu = ventanaMenu;
        this.ventanaAjustes = ventanaAjustes;
        this.resourceLoader = resourceLoader;
        this.audioService = audioService;
        this.sonidoBotones = sonidoBotones;
        this.pos = pos;
        this.buttonImageMap = new HashMap<>();
        initializeButtonImageMap();
    }
    
    /**
     * Constructor alternativo que crea el AudioService internamente
     */
    public ButtonAnimationListener(Ventana_juego ventanaJuego,
                                   Ventana_menu ventanaMenu,
                                   Ventana_ajustes ventanaAjustes,
                                   Servicios.AudioResourceManager resourceManager,
                                   boolean sonidoBotones,
                                   int pos) {
        this(ventanaJuego, ventanaMenu, ventanaAjustes, 
             new Servicios.ResourceLoader(), 
             new Servicios.AudioResourceService(resourceManager),
             sonidoBotones, pos);
    }
    
    /**
     * Inicializa el mapa de imágenes para todos los botones
     */
    private void initializeButtonImageMap() {
        // Botones del teclado (A-Z, Ñ)
        String[] letras = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", 
                          "m", "n", "ni", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        
        for (String letra : letras) {
            String pathBase = "/recursos/imagenes/botones/teclado/" + letra;
            ButtonImageConfig config = new ButtonImageConfig(
                pathBase + ".png",
                pathBase + "_entered.png",
                pathBase + "_click.png",
                anchoButton,
                altoButton
            );
            // Mapear cada botón de letra
            mapButtonByLetter(letra, config);
        }
        
        // Botones de navegación del menú
        if (ventanaMenu != null) {
            mapButton(ventanaMenu.BUTTON_MENU_SIGUIENTE, 
                "/recursos/imagenes/derecha_menu.png",
                "/recursos/imagenes/derecha_menu_entered.png",
                "/recursos/imagenes/derecha_menu_click.png", 300, 300);
            
            mapButton(ventanaMenu.BUTTON_MENU_ANTERIOR,
                "/recursos/imagenes/izquierda_menu.png",
                "/recursos/imagenes/izquierda_menu_entered.png",
                "/recursos/imagenes/izquierda_menu_click.png", 300, 300);
            
            mapButton(ventanaMenu.BUTTON_MENU_JUGAR,
                "/recursos/imagenes/boton.png",
                "/recursos/imagenes/boton_entered.png",
                null, 610, 80);
            
            mapButton(ventanaMenu.BUTTON_MENU_CAMBIO,
                "/recursos/imagenes/boton.png",
                "/recursos/imagenes/boton_entered.png",
                null, 180, 40);
            
            mapButton(ventanaMenu.BUTTON_MENU_AJUSTES,
                "/recursos/imagenes/configuraciones.png",
                "/recursos/imagenes/configuraciones_entered.png",
                null, nav100, nav100Y);
            
            mapButton(ventanaMenu.BUTTON_MENU_SALIR,
                "/recursos/imagenes/salir.png",
                "/recursos/imagenes/salir_entered.png",
                null, nav100, nav100Y);
        }
        
        // Botones de la ventana de juego
        if (ventanaJuego != null) {
            mapButton(ventanaJuego.BUTTON_JUEGO_MENU,
                "/recursos/imagenes/casa.png",
                "/recursos/imagenes/casa_entered.png",
                null, nav100, nav100Y);
            
            mapButton(ventanaJuego.BUTTON_SILENCIAR,
                pos == 0 ? "/recursos/imagenes/mute.png" : "/recursos/imagenes/muteado.png",
                "/recursos/imagenes/mute_entered.png",
                null, nav100, nav100Y);
            
            mapButton(ventanaJuego.BUTTON_AYUDA,
                "/recursos/imagenes/ayuda.png",
                "/recursos/imagenes/ayuda_entered.png",
                "/recursos/imagenes/ayuda_click.png", nav100, nav100Y);
            
            mapButton(ventanaJuego.BUTTON_AUDIO_TEXT,
                "/recursos/imagenes/boton.png",
                "/recursos/imagenes/boton_entered.png",
                null, nav130, nav100);
            
            mapButton(ventanaJuego.BUTTON_TUTORIAL,
                "/recursos/imagenes/boton.png",
                "/recursos/imagenes/boton_entered.png",
                null, nav320, nav100);
            
            mapButton(ventanaJuego.BUTTON_NEXT,
                "/recursos/imagenes/derecha_menu.png",
                "/recursos/imagenes/derecha_menu_entered.png",
                null, nav100, nav100Y);
        }
        
        // Botones de ajustes
        if (ventanaAjustes != null) {
            mapButton(ventanaAjustes.BUTTON_AJUSTES_SIGUIENTE,
                "/recursos/imagenes/derecha_menu.png",
                "/recursos/imagenes/derecha_menu_entered.png",
                null, 190, 190);
        }
    }
    
    /**
     * Mapea un botón a sus configuraciones de imagen
     */
    private void mapButton(JButton button, String normal, String entered, String clicked, int width, int height) {
        if (button != null) {
            buttonImageMap.put(button, new ButtonImageConfig(normal, entered, clicked, width, height));
        }
    }
    
    /**
     * Mapea botones de letras del teclado
     */
    private void mapButtonByLetter(String letra, ButtonImageConfig config) {
        if (ventanaJuego == null) return;
        
        JButton button = getButtonByLetter(letra);
        if (button != null) {
            buttonImageMap.put(button, config);
        }
    }
    
    /**
     * Obtiene un botón por su letra
     */
    private JButton getButtonByLetter(String letra) {
        if (ventanaJuego == null) return null;
        
        switch (letra.toUpperCase()) {
            case "A": return ventanaJuego.BUTTON_A;
            case "B": return ventanaJuego.BUTTON_B;
            case "C": return ventanaJuego.BUTTON_C;
            case "D": return ventanaJuego.BUTTON_D;
            case "E": return ventanaJuego.BUTTON_E;
            case "F": return ventanaJuego.BUTTON_F;
            case "G": return ventanaJuego.BUTTON_G;
            case "H": return ventanaJuego.BUTTON_H;
            case "I": return ventanaJuego.BUTTON_I;
            case "J": return ventanaJuego.BUTTON_J;
            case "K": return ventanaJuego.BUTTON_K;
            case "L": return ventanaJuego.BUTTON_L;
            case "M": return ventanaJuego.BUTTON_M;
            case "N": return ventanaJuego.BUTTON_N;
            case "NI": return ventanaJuego.BUTTON_NI;
            case "O": return ventanaJuego.BUTTON_O;
            case "P": return ventanaJuego.BUTTON_P;
            case "Q": return ventanaJuego.BUTTON_Q;
            case "R": return ventanaJuego.BUTTON_R;
            case "S": return ventanaJuego.BUTTON_S;
            case "T": return ventanaJuego.BUTTON_T;
            case "U": return ventanaJuego.BUTTON_U;
            case "V": return ventanaJuego.BUTTON_V;
            case "W": return ventanaJuego.BUTTON_W;
            case "X": return ventanaJuego.BUTTON_X;
            case "Y": return ventanaJuego.BUTTON_Y;
            case "Z": return ventanaJuego.BUTTON_Z;
            default: return null;
        }
    }
    
    /**
     * Establece el icono de un botón
     */
    private void setButtonIcon(JButton button, String imagePath, int width, int height) {
        if (button == null || imagePath == null) return;
        
        try {
            ImageIcon icon = resourceLoader.loadImageScaled(imagePath, width, height);
            if (icon != null) {
                button.setIcon(icon);
            }
        } catch (Exception e) {
            logger.warning("Error al cargar imagen: " + imagePath + " - " + e.getMessage());
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!(e.getSource() instanceof JButton)) return;
        
        JButton button = (JButton) e.getSource();
        ButtonImageConfig config = buttonImageMap.get(button);
        
        if (config != null) {
            // Reproducir sonido si es un botón de letra
            if (isLetterButton(button) && sonidoBotones && audioService != null) {
                audioService.playButtonClickSound();
            }
            
            // mouseClicked se ejecuta después de mouseReleased
            // Mantener el estado correcto según si el mouse sigue sobre el botón
            if (button.getModel().isRollover()) {
                setButtonIcon(button, config.entered, config.width, config.height);
            } else {
                setButtonIcon(button, config.normal, config.width, config.height);
            }
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (!(e.getSource() instanceof JButton)) return;
        
        JButton button = (JButton) e.getSource();
        ButtonImageConfig config = buttonImageMap.get(button);
        
        if (config != null) {
            // Sonido hover deshabilitado según requerimiento
            setButtonIcon(button, config.entered, config.width, config.height);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (!(e.getSource() instanceof JButton)) return;
        
        JButton button = (JButton) e.getSource();
        ButtonImageConfig config = buttonImageMap.get(button);
        
        if (config != null) {
            // Caso especial para botón silenciar
            if (button == ventanaJuego.BUTTON_SILENCIAR) {
                String normalPath = pos == 0 ? "/recursos/imagenes/ui/mute.png" : "/recursos/imagenes/ui/muteado.png";
                setButtonIcon(button, normalPath, config.width, config.height);
            } else {
                setButtonIcon(button, config.normal, config.width, config.height);
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (!(e.getSource() instanceof JButton)) return;
        
        JButton button = (JButton) e.getSource();
        ButtonImageConfig config = buttonImageMap.get(button);
        
        if (config != null && config.clicked != null) {
            // Cambiar a estado clicked cuando se presiona
            setButtonIcon(button, config.clicked, config.width, config.height);
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (!(e.getSource() instanceof JButton)) return;
        
        JButton button = (JButton) e.getSource();
        ButtonImageConfig config = buttonImageMap.get(button);
        
        if (config != null) {
            // Volver a estado entered si el mouse sigue sobre el botón
            // Si el mouse salió, mouseExited se encargará de volver a normal
            if (button.getModel().isRollover()) {
                setButtonIcon(button, config.entered, config.width, config.height);
            } else {
                setButtonIcon(button, config.normal, config.width, config.height);
            }
        }
    }
    
    /**
     * Verifica si un botón es de letra
     */
    private boolean isLetterButton(JButton button) {
        return button == ventanaJuego.BUTTON_A || button == ventanaJuego.BUTTON_B ||
               button == ventanaJuego.BUTTON_C || button == ventanaJuego.BUTTON_D ||
               button == ventanaJuego.BUTTON_E || button == ventanaJuego.BUTTON_F ||
               button == ventanaJuego.BUTTON_G || button == ventanaJuego.BUTTON_H ||
               button == ventanaJuego.BUTTON_I || button == ventanaJuego.BUTTON_J ||
               button == ventanaJuego.BUTTON_K || button == ventanaJuego.BUTTON_L ||
               button == ventanaJuego.BUTTON_M || button == ventanaJuego.BUTTON_N ||
               button == ventanaJuego.BUTTON_NI || button == ventanaJuego.BUTTON_O ||
               button == ventanaJuego.BUTTON_P || button == ventanaJuego.BUTTON_Q ||
               button == ventanaJuego.BUTTON_R || button == ventanaJuego.BUTTON_S ||
               button == ventanaJuego.BUTTON_T || button == ventanaJuego.BUTTON_U ||
               button == ventanaJuego.BUTTON_V || button == ventanaJuego.BUTTON_W ||
               button == ventanaJuego.BUTTON_X || button == ventanaJuego.BUTTON_Y ||
               button == ventanaJuego.BUTTON_Z;
    }
    
    /**
     * Verifica si se debe reproducir sonido hover
     */
    private boolean shouldPlayHoverSound(JButton button) {
        return button == ventanaAjustes.BUTTON_AJUSTES_ACEPTAR ||
               button == ventanaAjustes.BUTTON_AJUSTES_CANCELAR ||
               button == ventanaAjustes.BUTTON_AJUSTES_SIGUIENTE ||
               button == ventanaMenu.BUTTON_MENU_SIGUIENTE ||
               button == ventanaMenu.BUTTON_MENU_ANTERIOR ||
               button == ventanaMenu.BUTTON_MENU_JUGAR ||
               button == ventanaMenu.BUTTON_MENU_CAMBIO ||
               button == ventanaMenu.BUTTON_MENU_AJUSTES ||
               button == ventanaMenu.BUTTON_MENU_SALIR ||
               button == ventanaJuego.BUTTON_JUEGO_MENU ||
               button == ventanaJuego.BUTTON_SILENCIAR ||
               button == ventanaJuego.BUTTON_AYUDA ||
               button == ventanaJuego.BUTTON_AUDIO_TEXT ||
               button == ventanaJuego.BUTTON_TUTORIAL ||
               button == ventanaJuego.BUTTON_NEXT;
    }
    
    // Getters y setters
    public int getPos() {
        return pos;
    }
    
    public void setPos(int pos) {
        this.pos = pos;
    }
    
    public boolean isSonidoBotones() {
        return sonidoBotones;
    }
    
    public void setSonidoBotones(boolean sonidoBotones) {
        this.sonidoBotones = sonidoBotones;
    }
    
    public int getAnchoButton() {
        return anchoButton;
    }
    
    public void setAnchoButton(int anchoButton) {
        this.anchoButton = anchoButton;
        updateNavDimensions();
    }
    
    public int getAltoButton() {
        return altoButton;
    }
    
    public void setAltoButton(int altoButton) {
        this.altoButton = altoButton;
        updateNavDimensions();
    }
    
    private void updateNavDimensions() {
        nav100 = altoButton + 20;
        nav100Y = altoButton - 5;
        nav130 = altoButton + 50;
        nav320 = altoButton + 240;
    }
    
    /**
     * Establece el tamaño de los botones y actualiza sus iconos
     */
    public void setTamaño(int x, int y) {
        setAltoButton(y);
        setAnchoButton(x);
        
        // Actualizar todos los botones de letras con nuevos tamaños
        String[] letras = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", 
                          "m", "n", "ni", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        
        for (String letra : letras) {
            JButton button = getButtonByLetter(letra);
            if (button != null) {
                ButtonImageConfig config = buttonImageMap.get(button);
                if (config != null) {
                    // Actualizar configuración con nuevos tamaños
                    ButtonImageConfig newConfig = new ButtonImageConfig(
                        config.normal, config.entered, config.clicked, anchoButton, altoButton);
                    buttonImageMap.put(button, newConfig);
                    // Aplicar el estado normal con el nuevo tamaño
                    setButtonIcon(button, config.normal, anchoButton, altoButton);
                }
            }
        }
        
        // Actualizar botones de navegación con nuevos tamaños
        if (ventanaJuego != null) {
            updateNavigationButtons();
        }
        
        // Actualizar botones del menú con nuevos tamaños
        if (ventanaMenu != null) {
            updateMenuButtons();
        }
        
        // Actualizar botones de ajustes con nuevos tamaños
        if (ventanaAjustes != null) {
            updateAjustesButtons();
        }
    }
    
    /**
     * Actualiza los botones del menú con nuevas dimensiones
     */
    private void updateMenuButtons() {
        if (ventanaMenu == null) return;
        
        ButtonImageConfig config;
        
        if ((config = buttonImageMap.get(ventanaMenu.BUTTON_MENU_SIGUIENTE)) != null) {
            buttonImageMap.put(ventanaMenu.BUTTON_MENU_SIGUIENTE, 
                new ButtonImageConfig(config.normal, config.entered, config.clicked, 300, 300));
            setButtonIcon(ventanaMenu.BUTTON_MENU_SIGUIENTE, config.normal, 300, 300);
        }
        
        if ((config = buttonImageMap.get(ventanaMenu.BUTTON_MENU_ANTERIOR)) != null) {
            buttonImageMap.put(ventanaMenu.BUTTON_MENU_ANTERIOR, 
                new ButtonImageConfig(config.normal, config.entered, config.clicked, 300, 300));
            setButtonIcon(ventanaMenu.BUTTON_MENU_ANTERIOR, config.normal, 300, 300);
        }
        
        if ((config = buttonImageMap.get(ventanaMenu.BUTTON_MENU_JUGAR)) != null) {
            buttonImageMap.put(ventanaMenu.BUTTON_MENU_JUGAR, 
                new ButtonImageConfig(config.normal, config.entered, config.clicked, 610, 80));
            setButtonIcon(ventanaMenu.BUTTON_MENU_JUGAR, config.normal, 610, 80);
        }
        
        if ((config = buttonImageMap.get(ventanaMenu.BUTTON_MENU_CAMBIO)) != null) {
            buttonImageMap.put(ventanaMenu.BUTTON_MENU_CAMBIO, 
                new ButtonImageConfig(config.normal, config.entered, config.clicked, 180, 40));
            setButtonIcon(ventanaMenu.BUTTON_MENU_CAMBIO, config.normal, 180, 40);
        }
        
        if ((config = buttonImageMap.get(ventanaMenu.BUTTON_MENU_AJUSTES)) != null) {
            buttonImageMap.put(ventanaMenu.BUTTON_MENU_AJUSTES, 
                new ButtonImageConfig(config.normal, config.entered, config.clicked, nav100, nav100Y));
            setButtonIcon(ventanaMenu.BUTTON_MENU_AJUSTES, config.normal, nav100, nav100Y);
        }
        
        if ((config = buttonImageMap.get(ventanaMenu.BUTTON_MENU_SALIR)) != null) {
            buttonImageMap.put(ventanaMenu.BUTTON_MENU_SALIR, 
                new ButtonImageConfig(config.normal, config.entered, config.clicked, nav100, nav100Y));
            setButtonIcon(ventanaMenu.BUTTON_MENU_SALIR, config.normal, nav100, nav100Y);
        }
    }
    
    /**
     * Actualiza los botones de ajustes con nuevas dimensiones
     */
    private void updateAjustesButtons() {
        if (ventanaAjustes == null) return;
        
        ButtonImageConfig config;
        
        if ((config = buttonImageMap.get(ventanaAjustes.BUTTON_AJUSTES_SIGUIENTE)) != null) {
            buttonImageMap.put(ventanaAjustes.BUTTON_AJUSTES_SIGUIENTE, 
                new ButtonImageConfig(config.normal, config.entered, config.clicked, 190, 190));
            setButtonIcon(ventanaAjustes.BUTTON_AJUSTES_SIGUIENTE, config.normal, 190, 190);
        }
    }
    
    /**
     * Actualiza los botones de navegación con nuevas dimensiones
     */
    private void updateNavigationButtons() {
        setButtonIcon(ventanaJuego.BUTTON_JUEGO_MENU, "/recursos/imagenes/objetos/habitaciones/casa.png", nav100, nav100Y);
        setButtonIcon(ventanaJuego.BUTTON_SILENCIAR,
            pos == 0 ? "/recursos/imagenes/ui/mute.png" : "/recursos/imagenes/ui/muteado.png", nav100, nav100Y);
        setButtonIcon(ventanaJuego.BUTTON_AYUDA, "/recursos/imagenes/botones/juego/ayuda.png", nav100, nav100Y);
        setButtonIcon(ventanaJuego.BUTTON_AUDIO_TEXT, "/recursos/imagenes/botones/juego/boton.png", nav130, nav100Y);
        setButtonIcon(ventanaJuego.BUTTON_TUTORIAL, "/recursos/imagenes/botones/juego/boton.png", nav320, nav100Y);
        setButtonIcon(ventanaJuego.BUTTON_NEXT, "/recursos/imagenes/botones/menu/derecha_menu.png", nav100, nav100Y);
    }
}

