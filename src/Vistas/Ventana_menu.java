package Vistas;

import Controlador.*;
import Utilidades.Font_letras;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Ventana_menu extends JPanel {

    public Font_letras F;
    public JButton BUTTON_MENU_AJUSTES, BUTTON_MENU_ANTERIOR, BUTTON_MENU_CAMBIO,
    BUTTON_MENU_JUGAR, BUTTON_MENU_SALIR, BUTTON_MENU_SIGUIENTE;
    public JPanel PANEL_MENU_SUR, PANEL_UBICACION_OBJETOS, PANEL_MENU_SUR1, PANEL_MENU_SUR2, PANEL_MENU_SUR3,
    PANEL_MENU_SUR4, PANEL_MENU_SUR5, PANEL_MENU_SUR6, PANEL_MENU_SUR7, PANEL_MENU_SUR8, PANEL_MENU_SUR9, PANEL_UBICACION_2, PANEL_UBICACION_3, PANEL_UBICACION_4, PANEL_MENU_JUGAR, PANEL_UBICACION_6, PANEL_UBICACION_7, PANEL_UBICACION_8, PANEL_UBICACION_9;
    public JLabel ETIQUETA_ESPACIO3, ETIQUETA_TITULO_MENU,
    LABEL_ESPACIO, LABEL_ESPACIO1,
    ETIQUETA_MENU_TIPO_OBJETO,
    ETIQUETA_MENU_TIPO_CARICATURA1;
    public ImageIcon Icon_TITULO_size;
    public String STRING_JUGAR = "JUGAR";
    public String Fuente = "Snap ITC";

    Font font;

    public Ventana_menu() {
        F = new Font_letras();
        this.setLayout(new BorderLayout());
        NORTE = new JPanel();
        SUR = new JPanel();
        PANEL_CAMBIAR_PERSONAJE = new JPanel();
        PANEL_SALIR = new JPanel();
        PANEL_COMENZAR_JUGAR = new JPanel();
        CENTRO = new JPanel();
        CENTRO_NORTE = new JPanel();
        CENTRO_SUR = new JPanel();
        CENTRO_MID = new JPanel();
        //ESTE = new JPanel();
        jPanel2 = new JPanel();
        //OESTE = new JPanel();
        PANEL_CARICATURA = new JPanel();
        NORTE.setOpaque(false);
        SUR.setOpaque(false);
        PANEL_CAMBIAR_PERSONAJE.setOpaque(false);
        PANEL_SALIR.setOpaque(false);
        PANEL_COMENZAR_JUGAR.setOpaque(false);
        CENTRO.setOpaque(false);
        CENTRO_NORTE.setOpaque(false);
        CENTRO_SUR.setOpaque(false);
        CENTRO_MID.setOpaque(false);
        //ESTE .setOpaque(false);
        jPanel2.setOpaque(false);
        //OESTE .setOpaque(false);
        PANEL_CARICATURA.setOpaque(false);

        BUTTON_MENU_AJUSTES = new JButton();
        BUTTON_MENU_SALIR = new JButton();
        BUTTON_MENU_JUGAR = new JButton();
        ETIQUETA_ESPACIO3 = new JLabel();
        BUTTON_MENU_CAMBIO = new JButton();
        BUTTON_MENU_SIGUIENTE = new JButton();
        BUTTON_MENU_ANTERIOR = new JButton();
        ETIQUETA_MENU_TIPO_OBJETO = new JLabel();
        ETIQUETA_MENU_TIPO_CARICATURA1 = new JLabel();
        ETIQUETA_TITULO_MENU = new JLabel();

        add(NORTE, java.awt.BorderLayout.NORTH);

        SUR.setLayout(new java.awt.BorderLayout());
        PANEL_CAMBIAR_PERSONAJE.setLayout(new BoxLayout(PANEL_CAMBIAR_PERSONAJE, BoxLayout.X_AXIS));
        JLabel xd = new JLabel(".                 .                         .            .           .");
        PANEL_CAMBIAR_PERSONAJE.add(xd);
        xd.setForeground(new Color(0, 0, 0, 0));
        PANEL_CAMBIAR_PERSONAJE.add(BUTTON_MENU_SALIR);
        SUR.add(PANEL_CAMBIAR_PERSONAJE, java.awt.BorderLayout.LINE_END);
        PANEL_SALIR.setLayout(new BoxLayout(PANEL_SALIR, BoxLayout.X_AXIS));

        PANEL_SALIR.add(BUTTON_MENU_AJUSTES);
        PANEL_SALIR.add(BUTTON_MENU_CAMBIO);

        SUR.add(PANEL_SALIR, java.awt.BorderLayout.LINE_START);

        PANEL_COMENZAR_JUGAR.add(BUTTON_MENU_JUGAR);

        SUR.add(PANEL_COMENZAR_JUGAR, java.awt.BorderLayout.CENTER);

        add(SUR, java.awt.BorderLayout.SOUTH);
        CENTRO.setLayout(new java.awt.BorderLayout());
        CENTRO.add(CENTRO_NORTE, java.awt.BorderLayout.NORTH);
        CENTRO.add(CENTRO_SUR, java.awt.BorderLayout.SOUTH);
        CENTRO_MID.add(BUTTON_MENU_ANTERIOR);
        CENTRO_MID.add(ETIQUETA_MENU_TIPO_OBJETO);
        CENTRO_MID.add(ETIQUETA_MENU_TIPO_CARICATURA1);
        ETIQUETA_MENU_TIPO_CARICATURA1.setVisible(false);
        CENTRO_MID.add(BUTTON_MENU_SIGUIENTE);
        CENTRO.add(CENTRO_MID, java.awt.BorderLayout.CENTER);
        add(CENTRO, java.awt.BorderLayout.CENTER);

        BUTTON_MENU_JUGAR.setText(STRING_JUGAR);
        BUTTON_MENU_CAMBIO.setText("PERSONAJE"); // Inicializar con "TEMA" (modo objeto por defecto)
        font = F.fuente(F.BUNN, 0, 13);
        BUTTON_MENU_CAMBIO.setFont(F.fuente(F.BUNN, 0, 16));

        BUTTON_MENU_AJUSTES.setFont(font);
        BUTTON_MENU_SIGUIENTE.setFont(font);
        ETIQUETA_MENU_TIPO_CARICATURA1.setFont(font);
        ETIQUETA_MENU_TIPO_OBJETO.setFont(font);
        BUTTON_MENU_ANTERIOR.setFont(font);
        BUTTON_MENU_JUGAR.setFont(F.fuente(F.BUNN, 0, 40));
        BUTTON_MENU_SALIR.setFont(font);




        java.net.URL urlIzquierda = getClass().getResource("/recursos/imagenes/botones/menu/izquierda_menu.png");
        if (urlIzquierda == null) {
            throw new RuntimeException("No se pudo cargar el recurso: /recursos/imagenes/botones/menu/izquierda_menu.png");
        }
        ImageIcon icono17 = new ImageIcon(urlIzquierda);
        ImageIcon icono7 = new ImageIcon(icono17.getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_DEFAULT));
        BUTTON_MENU_ANTERIOR.setIcon(icono7);
        BUTTON_MENU_ANTERIOR.setOpaque(false);
        BUTTON_MENU_ANTERIOR.setFocusPainted(false);
        BUTTON_MENU_ANTERIOR.setContentAreaFilled(false);
        BUTTON_MENU_ANTERIOR.setBorderPainted(false);

        java.net.URL urlDerecha = getClass().getResource("/recursos/imagenes/botones/menu/derecha_menu.png");
        if (urlDerecha == null) {
            throw new RuntimeException("No se pudo cargar el recurso: /recursos/imagenes/botones/menu/derecha_menu.png");
        }
        ImageIcon icono18 = new ImageIcon(urlDerecha);
        ImageIcon icono8 = new ImageIcon(icono18.getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_DEFAULT));
        BUTTON_MENU_SIGUIENTE.setIcon(icono8);
        BUTTON_MENU_SIGUIENTE.setOpaque(false);
        BUTTON_MENU_SIGUIENTE.setFocusPainted(false);
        BUTTON_MENU_SIGUIENTE.setContentAreaFilled(false);
        BUTTON_MENU_SIGUIENTE.setBorderPainted(false);

        java.net.URL urlBoton1 = getClass().getResource("/recursos/imagenes/botones/juego/boton.png");
        if (urlBoton1 == null) {
            throw new RuntimeException("No se pudo cargar el recurso: /recursos/imagenes/botones/juego/boton.png");
        }
        ImageIcon icono19 = new ImageIcon(urlBoton1);
        ImageIcon icono9 = new ImageIcon(icono19.getImage().getScaledInstance(180, 40, java.awt.Image.SCALE_DEFAULT));
        BUTTON_MENU_CAMBIO.setIcon(icono9);
        BUTTON_MENU_CAMBIO.setOpaque(false);
        BUTTON_MENU_CAMBIO.setFocusPainted(false);
        BUTTON_MENU_CAMBIO.setContentAreaFilled(false);
        BUTTON_MENU_CAMBIO.setBorderPainted(false);
        BUTTON_MENU_CAMBIO.setHorizontalTextPosition(SwingConstants.CENTER);

        java.net.URL urlConfig = getClass().getResource("/recursos/imagenes/botones/ajustes/configuraciones.png");
        if (urlConfig == null) {
            throw new RuntimeException("No se pudo cargar el recurso: /recursos/imagenes/botones/ajustes/configuraciones.png");
        }
        ImageIcon icono16 = new ImageIcon(urlConfig);
        ImageIcon icono6 = new ImageIcon(icono16.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
        BUTTON_MENU_AJUSTES.setIcon(icono6);
        BUTTON_MENU_AJUSTES.setOpaque(false);
        BUTTON_MENU_AJUSTES.setFocusPainted(false);
        BUTTON_MENU_AJUSTES.setContentAreaFilled(false);
        BUTTON_MENU_AJUSTES.setBorderPainted(false);

        java.net.URL urlSalir = getClass().getResource("/recursos/imagenes/botones/menu/salir.png");
        if (urlSalir == null) {
            throw new RuntimeException("No se pudo cargar el recurso: /recursos/imagenes/botones/menu/salir.png");
        }
        ImageIcon icono15 = new ImageIcon(urlSalir);
        ImageIcon icono5 = new ImageIcon(icono15.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
        BUTTON_MENU_SALIR.setIcon(icono5);
        BUTTON_MENU_SALIR.setOpaque(false);
        BUTTON_MENU_SALIR.setFocusPainted(false);
        BUTTON_MENU_SALIR.setContentAreaFilled(false);
        BUTTON_MENU_SALIR.setBorderPainted(false);


        java.net.URL urlBoton2 = getClass().getResource("/recursos/imagenes/botones/juego/boton.png");
        if (urlBoton2 == null) {
            throw new RuntimeException("No se pudo cargar el recurso: /recursos/imagenes/botones/juego/boton.png");
        }
        ImageIcon icono00 = new ImageIcon(urlBoton2);
        ImageIcon icono0 = new ImageIcon(icono00.getImage().getScaledInstance(610, 80, java.awt.Image.SCALE_DEFAULT));
        BUTTON_MENU_JUGAR.setIcon(icono0);
        BUTTON_MENU_JUGAR.setOpaque(false);
        BUTTON_MENU_JUGAR.setFocusPainted(false);
        BUTTON_MENU_JUGAR.setContentAreaFilled(false);
        BUTTON_MENU_JUGAR.setBorderPainted(false);
        BUTTON_MENU_JUGAR.setHorizontalTextPosition(SwingConstants.CENTER);


        ImageIcon Icon_objeto = new ImageIcon(getClass().getResource("/recursos/imagenes/ui/objeto_colores.png"));
        ImageIcon Icon_objeto1 = new ImageIcon(Icon_objeto.getImage().getScaledInstance(470, 400, java.awt.Image.SCALE_DEFAULT));
        ETIQUETA_MENU_TIPO_OBJETO.setIcon(Icon_objeto1);
        ETIQUETA_MENU_TIPO_OBJETO.setOpaque(false);
        //ETIQUETA_MENU_TIPO_OBJETO.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0,0,0), 12));

        //ETIQUETA_MENU_TIPO_CARICATURA1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0), 12));
        ImageIcon Icon_caricatura_menu2 = new ImageIcon(getClass().getResource("/recursos/imagenes/personajes/duende/duende.png"));
        ImageIcon Icon_caricatura_menu12 = new ImageIcon(Icon_caricatura_menu2.getImage().getScaledInstance(470, 400, java.awt.Image.SCALE_DEFAULT));
        ETIQUETA_MENU_TIPO_CARICATURA1.setIcon(Icon_caricatura_menu12);
        ETIQUETA_MENU_TIPO_CARICATURA1.setOpaque(false);

        java.net.URL urlTitulo = getClass().getResource("/recursos/imagenes/ui/titulo.png");
        if (urlTitulo == null) {
            throw new RuntimeException("No se pudo cargar el recurso: /recursos/imagenes/ui/titulo.png");
        }
        ImageIcon Icon_TITULO = new ImageIcon(urlTitulo);
         Icon_TITULO_size = new ImageIcon(Icon_TITULO.getImage().getScaledInstance(670, 200, java.awt.Image.SCALE_DEFAULT));
        ETIQUETA_TITULO_MENU.setIcon(Icon_TITULO_size);
        ETIQUETA_TITULO_MENU.setOpaque(false);
        ETIQUETA_TITULO_MENU.setHorizontalTextPosition(SwingConstants.CENTER);
        imagen = new ImageIcon(getClass().getResource("/recursos/imagenes/fondos/fondo_menu.png")).getImage();
        setBorder(new EmptyBorder(10, 10, 10, 10));
        NORTE.add(ETIQUETA_TITULO_MENU);
        //SUR.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 97, 0), 12));
    }
    public Image imagen;
    public void paint(Graphics g) {
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(),
            this);

        setOpaque(false);
        super.paint(g);
    }
    public void conectaFuncionalidad(Funcionalidad c) {

        BUTTON_MENU_AJUSTES.addActionListener(c);
        BUTTON_MENU_ANTERIOR.addActionListener(c);
        BUTTON_MENU_CAMBIO.addActionListener(c);
        BUTTON_MENU_JUGAR.addActionListener(c);
        BUTTON_MENU_SALIR.addActionListener(c);
        BUTTON_MENU_SIGUIENTE.addActionListener(c);

        BUTTON_MENU_AJUSTES.addMouseListener(c.getAnimation());
        BUTTON_MENU_ANTERIOR.addMouseListener(c.getAnimation());
        BUTTON_MENU_CAMBIO.addMouseListener(c.getAnimation());
        BUTTON_MENU_JUGAR.addMouseListener(c.getAnimation());
        BUTTON_MENU_SALIR.addMouseListener(c.getAnimation());
        BUTTON_MENU_SIGUIENTE.addMouseListener(c.getAnimation());

    }

    private JPanel CENTRO;
    private JPanel CENTRO_MID;
    private JPanel CENTRO_NORTE;
    private JPanel CENTRO_SUR;
    //private JPanel ESTE;
    private JPanel NORTE;

    //private JPanel OESTE;
    private JPanel PANEL_CAMBIAR_PERSONAJE;
    private JPanel PANEL_CARICATURA;
    private JPanel PANEL_COMENZAR_JUGAR;
    private JPanel PANEL_SALIR;
    private JPanel SUR;
    private JPanel jPanel2;


}
