package Vistas;

import javax.swing.*;
import java.awt.*;

import javax.swing.border.*;
public class Ventana_principal extends JFrame {
    public JPanel contenidoPanel;
    public final static String VENTANA_JUEGO_POS = "JUEGO";
    public final static String VENTANA_MENU_POS = "MENU";
    public final static String VENTANA_AJUSTES_POS = "AJUSTES";
    public final static String VENTANA_INICIO_POS = "INICIO";
    //paneles
    public Ventana_juego VENTANA_JUEGO;
    public Ventana_menu VENTANA_MENU;
    public Ventana_ajustes VENTANA_AJUSTES;
    public Ventana_iniciar VENTANA_INICIO;

    public JPanel PanelCambiante;
    public CardLayout CambiarPanel;
    public Ventana_principal(Ventana_juego VENTANA_JUEGO,
        Ventana_menu VENTANA_MENU,
        Ventana_ajustes VENTANA_AJUSTES,
        Ventana_iniciar VENTANA_INICIO) {
        this.setUndecorated(true);
        this.setBackground(new Color(253, 192, 6)); //naranja
        this.VENTANA_JUEGO = VENTANA_JUEGO;
        this.VENTANA_MENU = VENTANA_MENU;
        this.VENTANA_AJUSTES = VENTANA_AJUSTES;
        this.VENTANA_INICIO = VENTANA_INICIO;
        //this.VENTANA_JUEGO . setOpaque(false);
        this.VENTANA_AJUSTES.setBackground(new Color(112, 215, 126));
        this.VENTANA_INICIO.setBackground(new Color(67, 161, 168));
        contenidoPanel = new JPanel();
        contenidoPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        contenidoPanel.setLayout(new BorderLayout()); //0,0));
        PanelCambiante = new JPanel();
        //add(contenidoPanel);
        PanelCambiante.setOpaque(false);
        contenidoPanel.add(PanelCambiante, BorderLayout.CENTER);
        contenidoPanel.setOpaque(false);
        CambiarPanel = new CardLayout(0, 0); //0,0);
        PanelCambiante.setLayout(CambiarPanel);
        PanelCambiante.add(VENTANA_INICIO, VENTANA_INICIO_POS);
        PanelCambiante.add(VENTANA_MENU, VENTANA_MENU_POS);
        PanelCambiante.add(VENTANA_JUEGO, VENTANA_JUEGO_POS);
        PanelCambiante.add(VENTANA_AJUSTES, VENTANA_AJUSTES_POS);

        add(contenidoPanel);
        this.setContentPane(contenidoPanel);


    }



}
