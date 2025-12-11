package Utilidades;

import Vistas.Ventana_ajustes;
import Vistas.Ventana_juego;
import Vistas.Ventana_menu;
import Servicios.CargarRecursos;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

/**
 * Gestor de animaciones y sonidos de botones
 * Responsabilidad: Manejar efectos visuales y sonoros de los botones
 */
public class ButtonAnimationManager implements MouseListener {
    private Ventana_juego VENTANA_JUEGO;
    private Ventana_menu VENTANA_MENU;
    private Ventana_ajustes VENTANA_AJUSTES;
    private CargarRecursos recursos;
    public int anchoButton = 90;
    public int altoButton = 80;

    public int Nav100 = altoButton + 20;
    public int Nav100Y = altoButton - 5;
    public int Nav130 = altoButton + 50;
    public int Nav320 = altoButton + 240;
    public int pos = 0;
    public boolean SonidoBotones = true;
    
    public ButtonAnimationManager(Ventana_juego VENTANA_JUEGO,
        Ventana_menu VENTANA_MENU,
        Ventana_ajustes VENTANA_AJUSTES,
        CargarRecursos recursos,
        boolean SonidoBotones, int pos) {
        this.VENTANA_JUEGO = VENTANA_JUEGO;
        this.VENTANA_MENU = VENTANA_MENU;
        this.VENTANA_AJUSTES = VENTANA_AJUSTES;
        this.recursos = recursos;
        this.pos = pos;
        this.SonidoBotones = SonidoBotones;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public boolean isSonidoBotones() {
        return SonidoBotones;
    }

    public void setSonidoBotones(boolean SonidoBotones) {
        this.SonidoBotones = SonidoBotones;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (recursos != null && recursos.getBlup() != null) {
            recursos.getBlup().setFramePosition(0);
        }
        if (e.getSource() == VENTANA_MENU.BUTTON_MENU_SIGUIENTE) {
            VENTANA_MENU.BUTTON_MENU_SIGUIENTE.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/menu/derecha_menu_click.png")).getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_MENU.BUTTON_MENU_ANTERIOR) {
            VENTANA_MENU.BUTTON_MENU_ANTERIOR.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/menu/izquierda_menu_click.png")).getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_JUEGO.BUTTON_AYUDA) {
            VENTANA_JUEGO.BUTTON_AYUDA.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/juego/ayuda_click.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
        }
        // Animaciones de teclado (letras)
        aplicarAnimacionClickTeclado(e);
    }
    
    @Override 
    public void mousePressed(MouseEvent e) {}
    
    @Override 
    public void mouseEntered(MouseEvent e) {
        if (recursos != null && recursos.getBlop() != null) {
            recursos.getBlop().setFramePosition(0);
        }
        aplicarAnimacionHover(e);
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        aplicarAnimacionExit(e);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        aplicarAnimacionReleased(e);
    }

    private void aplicarAnimacionClickTeclado(MouseEvent e) {
        if (SonidoBotones && recursos != null && recursos.getBlup() != null) {
            recursos.getBlup().start();
        }
        
        // Mapeo de botones a imágenes click
        String[][] botonesTeclado = {
            {"A", "/recursos/imagenes/a_click.png"},
            {"B", "/recursos/imagenes/b_click.png"},
            {"C", "/recursos/imagenes/c_click.png"},
            {"D", "/recursos/imagenes/d_click.png"},
            {"E", "/recursos/imagenes/e_click.png"},
            {"F", "/recursos/imagenes/f_click.png"},
            {"G", "/recursos/imagenes/g_click.png"},
            {"H", "/recursos/imagenes/h_click.png"},
            {"I", "/recursos/imagenes/i_click.png"},
            {"J", "/recursos/imagenes/j_click.png"},
            {"K", "/recursos/imagenes/k_click.png"},
            {"L", "/recursos/imagenes/l_click.png"},
            {"M", "/recursos/imagenes/m_click.png"},
            {"N", "/recursos/imagenes/n_click.png"},
            {"NI", "/recursos/imagenes/ni_click.png"},
            {"O", "/recursos/imagenes/o_click.png"},
            {"P", "/recursos/imagenes/p_click.png"},
            {"Q", "/recursos/imagenes/q_click.png"},
            {"R", "/recursos/imagenes/r_click.png"},
            {"S", "/recursos/imagenes/s_click.png"},
            {"T", "/recursos/imagenes/t_click.png"},
            {"U", "/recursos/imagenes/u_click.png"},
            {"V", "/recursos/imagenes/v_click.png"},
            {"W", "/recursos/imagenes/w_click.png"},
            {"X", "/recursos/imagenes/x_click.png"},
            {"Y", "/recursos/imagenes/y_click.png"},
            {"Z", "/recursos/imagenes/z_click.png"}
        };
        
        aplicarAnimacionBoton(e, VENTANA_JUEGO, botonesTeclado, anchoButton, altoButton);
    }
    
    private void aplicarAnimacionHover(MouseEvent e) {
        // Sonido hover deshabilitado según requerimiento
        
        // Botones de menú
        if (e.getSource() == VENTANA_AJUSTES.BUTTON_AJUSTES_ACEPTAR || 
            e.getSource() == VENTANA_AJUSTES.BUTTON_AJUSTES_CANCELAR) {
            // Solo sonido, sin cambio de imagen
        }
        if (e.getSource() == VENTANA_AJUSTES.BUTTON_AJUSTES_SIGUIENTE) {
            VENTANA_AJUSTES.BUTTON_AJUSTES_SIGUIENTE.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/menu/derecha_menu_entered.png")).getImage().getScaledInstance(190, 190, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_MENU.BUTTON_MENU_SIGUIENTE) {
            VENTANA_MENU.BUTTON_MENU_SIGUIENTE.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/menu/derecha_menu_entered.png")).getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_MENU.BUTTON_MENU_ANTERIOR) {
            VENTANA_MENU.BUTTON_MENU_ANTERIOR.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/menu/izquierda_menu_entered.png")).getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_MENU.BUTTON_MENU_JUGAR) {
            VENTANA_MENU.BUTTON_MENU_JUGAR.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/juego/boton_entered.png")).getImage().getScaledInstance(610, 80, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_MENU.BUTTON_MENU_CAMBIO) {
            VENTANA_MENU.BUTTON_MENU_CAMBIO.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/juego/boton_entered.png")).getImage().getScaledInstance(180, 40, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_MENU.BUTTON_MENU_AJUSTES) {
            VENTANA_MENU.BUTTON_MENU_AJUSTES.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/ajustes/configuraciones_entered.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_MENU.BUTTON_MENU_SALIR) {
            VENTANA_MENU.BUTTON_MENU_SALIR.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/menu/salir_entered.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
        }
        // Botones de juego
        if (e.getSource() == VENTANA_JUEGO.BUTTON_JUEGO_MENU) {
            VENTANA_JUEGO.BUTTON_JUEGO_MENU.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/objetos/habitaciones/casa_entered.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_JUEGO.BUTTON_SILENCIAR) {
            VENTANA_JUEGO.BUTTON_SILENCIAR.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/ui/mute_entered.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_JUEGO.BUTTON_AYUDA) {
            VENTANA_JUEGO.BUTTON_AYUDA.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/juego/ayuda_entered.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_JUEGO.BUTTON_AUDIO_TEXT) {
            VENTANA_JUEGO.BUTTON_AUDIO_TEXT.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/juego/boton_entered.png")).getImage().getScaledInstance(Nav130, Nav100, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_JUEGO.BUTTON_TUTORIAL) {
            VENTANA_JUEGO.BUTTON_TUTORIAL.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/juego/boton_entered.png")).getImage().getScaledInstance(Nav320, Nav100, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_JUEGO.BUTTON_NEXT) {
            VENTANA_JUEGO.BUTTON_NEXT.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/menu/derecha_menu_entered.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
        }

        // Animaciones de teclado (hover)
        aplicarAnimacionHoverTeclado(e);
    }
    
    private void aplicarAnimacionHoverTeclado(MouseEvent e) {
        String[][] botonesTeclado = {
            {"A", "/recursos/imagenes/a_entered.png"},
            {"B", "/recursos/imagenes/b_entered.png"},
            {"C", "/recursos/imagenes/c_entered.png"},
            {"D", "/recursos/imagenes/d_entered.png"},
            {"E", "/recursos/imagenes/e_entered.png"},
            {"F", "/recursos/imagenes/f_entered.png"},
            {"G", "/recursos/imagenes/g_entered.png"},
            {"H", "/recursos/imagenes/h_entered.png"},
            {"I", "/recursos/imagenes/i_entered.png"},
            {"J", "/recursos/imagenes/j_entered.png"},
            {"K", "/recursos/imagenes/k_entered.png"},
            {"L", "/recursos/imagenes/l_entered.png"},
            {"M", "/recursos/imagenes/m_entered.png"},
            {"N", "/recursos/imagenes/n_entered.png"},
            {"NI", "/recursos/imagenes/ni_entered.png"},
            {"O", "/recursos/imagenes/o_entered.png"},
            {"P", "/recursos/imagenes/p_entered.png"},
            {"Q", "/recursos/imagenes/q_entered.png"},
            {"R", "/recursos/imagenes/r_entered.png"},
            {"S", "/recursos/imagenes/s_entered.png"},
            {"T", "/recursos/imagenes/t_entered.png"},
            {"U", "/recursos/imagenes/u_entered.png"},
            {"V", "/recursos/imagenes/v_entered.png"},
            {"W", "/recursos/imagenes/w_entered.png"},
            {"X", "/recursos/imagenes/x_entered.png"},
            {"Y", "/recursos/imagenes/y_entered.png"},
            {"Z", "/recursos/imagenes/z_entered.png"}
        };
        
        aplicarAnimacionBoton(e, VENTANA_JUEGO, botonesTeclado, anchoButton, altoButton);
    }
    
    private void aplicarAnimacionExit(MouseEvent e) {
        if (e.getSource() == VENTANA_AJUSTES.BUTTON_AJUSTES_SIGUIENTE) {
            VENTANA_AJUSTES.BUTTON_AJUSTES_SIGUIENTE.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/menu/derecha_menu.png")).getImage().getScaledInstance(190, 190, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_JUEGO.BUTTON_SILENCIAR) {
            if (pos == 0) {
                VENTANA_JUEGO.BUTTON_SILENCIAR.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/ui/mute.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
            } else if (pos == 1) {
                VENTANA_JUEGO.BUTTON_SILENCIAR.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/ui/muteado.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
            }
        }
        if (e.getSource() == VENTANA_JUEGO.BUTTON_NEXT) {
            VENTANA_JUEGO.BUTTON_NEXT.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/menu/derecha_menu.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_MENU.BUTTON_MENU_SIGUIENTE) {
            VENTANA_MENU.BUTTON_MENU_SIGUIENTE.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/menu/derecha_menu.png")).getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_MENU.BUTTON_MENU_ANTERIOR) {
            VENTANA_MENU.BUTTON_MENU_ANTERIOR.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/menu/izquierda_menu.png")).getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_MENU.BUTTON_MENU_JUGAR) {
            VENTANA_MENU.BUTTON_MENU_JUGAR.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/juego/boton.png")).getImage().getScaledInstance(610, 80, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_MENU.BUTTON_MENU_CAMBIO) {
            VENTANA_MENU.BUTTON_MENU_CAMBIO.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/juego/boton.png")).getImage().getScaledInstance(180, 40, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_MENU.BUTTON_MENU_AJUSTES) {
            VENTANA_MENU.BUTTON_MENU_AJUSTES.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/ajustes/configuraciones.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_MENU.BUTTON_MENU_SALIR) {
            VENTANA_MENU.BUTTON_MENU_SALIR.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/menu/salir.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_JUEGO.BUTTON_JUEGO_MENU) {
            VENTANA_JUEGO.BUTTON_JUEGO_MENU.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/objetos/habitaciones/casa.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_JUEGO.BUTTON_AYUDA) {
            VENTANA_JUEGO.BUTTON_AYUDA.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/juego/ayuda.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_JUEGO.BUTTON_AUDIO_TEXT) {
            VENTANA_JUEGO.BUTTON_AUDIO_TEXT.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/juego/boton.png")).getImage().getScaledInstance(Nav130, Nav100, java.awt.Image.SCALE_DEFAULT)));
        }
        if (e.getSource() == VENTANA_JUEGO.BUTTON_TUTORIAL) {
            VENTANA_JUEGO.BUTTON_TUTORIAL.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/juego/boton.png")).getImage().getScaledInstance(Nav320, Nav100, java.awt.Image.SCALE_DEFAULT)));
        }

        // Animaciones de teclado (exit)
        aplicarAnimacionExitTeclado(e);
    }
    
    private void aplicarAnimacionExitTeclado(MouseEvent e) {
        String[][] botonesTeclado = {
            {"A", "/recursos/imagenes/a.png"},
            {"B", "/recursos/imagenes/b.png"},
            {"C", "/recursos/imagenes/c.png"},
            {"D", "/recursos/imagenes/d.png"},
            {"E", "/recursos/imagenes/e.png"},
            {"F", "/recursos/imagenes/f.png"},
            {"G", "/recursos/imagenes/g.png"},
            {"H", "/recursos/imagenes/h.png"},
            {"I", "/recursos/imagenes/i.png"},
            {"J", "/recursos/imagenes/j.png"},
            {"K", "/recursos/imagenes/k.png"},
            {"L", "/recursos/imagenes/l.png"},
            {"M", "/recursos/imagenes/m.png"},
            {"N", "/recursos/imagenes/n.png"},
            {"NI", "/recursos/imagenes/ni.png"},
            {"O", "/recursos/imagenes/o.png"},
            {"P", "/recursos/imagenes/p.png"},
            {"Q", "/recursos/imagenes/q.png"},
            {"R", "/recursos/imagenes/r.png"},
            {"S", "/recursos/imagenes/s.png"},
            {"T", "/recursos/imagenes/t.png"},
            {"U", "/recursos/imagenes/u.png"},
            {"V", "/recursos/imagenes/v.png"},
            {"W", "/recursos/imagenes/w.png"},
            {"X", "/recursos/imagenes/x.png"},
            {"Y", "/recursos/imagenes/y.png"},
            {"Z", "/recursos/imagenes/z.png"}
        };
        
        aplicarAnimacionBoton(e, VENTANA_JUEGO, botonesTeclado, anchoButton, altoButton);
    }
    
    private void aplicarAnimacionReleased(MouseEvent e) {
        // Similar a hover pero para released
        aplicarAnimacionHover(e);
    }
    
    private void aplicarAnimacionBoton(MouseEvent e, Ventana_juego ventana, String[][] mapeo, int ancho, int alto) {
        for (String[] boton : mapeo) {
            String nombreBoton = "BUTTON_" + boton[0];
            try {
                java.lang.reflect.Field field = ventana.getClass().getField(nombreBoton);
                javax.swing.JButton button = (javax.swing.JButton) field.get(ventana);
                if (e.getSource() == button) {
                    button.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(boton[1])).getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_DEFAULT)));
                    break;
                }
            } catch (Exception ex) {
                // Ignorar si no se encuentra el botón
            }
        }
    }

    public int getAnchoButton() {
        return anchoButton;
    }

    public void setAnchoButton(int anchoButton) {
        this.anchoButton = anchoButton;
    }

    public int getAltoButton() {
        return altoButton;
    }

    public void setAltoButton(int altoButton) {
        this.altoButton = altoButton;
    }

    public void setTamaño(int x, int y) {
        setAltoButton(y);
        setAnchoButton(x);
        actualizarIconosTeclado();
        actualizarIconosControles();
    }
    
    private void actualizarIconosTeclado() {
        String[][] botonesTeclado = {
            {"A", "/recursos/imagenes/a.png"}, {"B", "/recursos/imagenes/b.png"}, {"C", "/recursos/imagenes/c.png"},
            {"D", "/recursos/imagenes/d.png"}, {"E", "/recursos/imagenes/e.png"}, {"F", "/recursos/imagenes/f.png"},
            {"G", "/recursos/imagenes/g.png"}, {"H", "/recursos/imagenes/h.png"}, {"I", "/recursos/imagenes/i.png"},
            {"J", "/recursos/imagenes/j.png"}, {"K", "/recursos/imagenes/k.png"}, {"L", "/recursos/imagenes/l.png"},
            {"M", "/recursos/imagenes/m.png"}, {"N", "/recursos/imagenes/n.png"}, {"NI", "/recursos/imagenes/ni.png"},
            {"O", "/recursos/imagenes/o.png"}, {"P", "/recursos/imagenes/p.png"}, {"Q", "/recursos/imagenes/q.png"},
            {"R", "/recursos/imagenes/r.png"}, {"S", "/recursos/imagenes/s.png"}, {"T", "/recursos/imagenes/t.png"},
            {"U", "/recursos/imagenes/u.png"}, {"V", "/recursos/imagenes/v.png"}, {"W", "/recursos/imagenes/w.png"},
            {"X", "/recursos/imagenes/x.png"}, {"Y", "/recursos/imagenes/y.png"}, {"Z", "/recursos/imagenes/z.png"}
        };
        
        for (String[] boton : botonesTeclado) {
            try {
                java.lang.reflect.Field field = VENTANA_JUEGO.getClass().getField("BUTTON_" + boton[0]);
                javax.swing.JButton button = (javax.swing.JButton) field.get(VENTANA_JUEGO);
                button.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(boton[1])).getImage().getScaledInstance(anchoButton, altoButton, java.awt.Image.SCALE_DEFAULT)));
            } catch (Exception ex) {
                // Ignorar si no se encuentra el botón
            }
        }
    }
    
    private void actualizarIconosControles() {
        VENTANA_JUEGO.BUTTON_JUEGO_MENU.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/objetos/habitaciones/casa.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
        VENTANA_JUEGO.BUTTON_SILENCIAR.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/ui/mute.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
        VENTANA_JUEGO.BUTTON_AYUDA.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/juego/ayuda.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
        VENTANA_JUEGO.BUTTON_AUDIO_TEXT.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/juego/boton.png")).getImage().getScaledInstance(Nav130, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
        VENTANA_JUEGO.BUTTON_TUTORIAL.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/juego/boton.png")).getImage().getScaledInstance(Nav320, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
        VENTANA_JUEGO.BUTTON_NEXT.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/menu/derecha_menu.png")).getImage().getScaledInstance(Nav100, Nav100Y, java.awt.Image.SCALE_DEFAULT)));
    }
}

