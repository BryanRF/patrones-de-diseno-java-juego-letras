package Vistas;

import Controlador.*;
import Utilidades.Font_letras;
import javax.swing.*;
import java.awt.*;
public class Ventana_ajustes extends JPanel {

    public JButton BUTTON_AJUSTES_ACEPTAR;
    public JButton BUTTON_AJUSTES_ANTERIOR;
    public JButton BUTTON_AJUSTES_CANCELAR;
    public JButton BUTTON_AJUSTES_SIGUIENTE;
    public JComboBox < String > CAJA_IDIOMAS;
    public JComboBox < String > CAJA_TAMAÑO_PANTALLA;
    public JLabel ETIQUETA_AJUSTES_FONDOS;
    public JLabel ETIQUETA_AJUSTES_TITULO;
    public JLabel ETIQUETA_AJUSTES_TITULO_FONDO;
    public JLabel ETIQUETA_AJUSTES_TITULO_IDIOMA;
    public JLabel ETIQUETA_AJUSTES_TITULO_SONIDO;
    public JLabel ETIQUETA_AJUSTES_TITULO_TAMAÑO_PANTALLA;
    public JButton BUTTON_BOTON_SONIDO;
    public JButton BUTTON_MUSICA;

    public Box.Filler filler1;
    public Box.Filler filler2;
    public Box.Filler filler3;
    public Box.Filler filler4;
    public JPanel jPanel1;
    public JPanel jPanel10;
    public JPanel jPanel11;
    public JPanel jPanel12;
    public JPanel jPanel19;
    public JPanel jPanel20;
    public JPanel jPanel21;
    public JPanel jPanel22;
    public JPanel jPanel23;
    public JPanel jPanel24;
    public JPanel jPanel4;
    public JPanel jPanel5;
    public JPanel jPanel6;
    public JPanel jPanel7;
    public JPanel jPanel8;
    public JPanel jPanel9;
    public String[] Listaidioma = {
        "Solo disponible en espanish"
    };
    public String[] ListaTamaños;
    public String Fuente = "Snap ITC";
    public Font_letras F;
    public Ventana_ajustes() {

        F = new Font_letras();
        
        // Obtener tamaño real de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        String tamañoReal = screenSize.width + "x" + screenSize.height;
        
        // Inicializar lista de tamaños con el tamaño real de pantalla
        ListaTamaños = new String[] {
            "Por defecto",
            "Acomodar a mi pantalla",
            tamañoReal,
            "1920x1080",
            "1400x870",
            "1366x768"
        };
        jPanel4 = new JPanel();
        jPanel4.setOpaque(false);
        BUTTON_AJUSTES_ACEPTAR = new JButton();
        BUTTON_AJUSTES_CANCELAR = new JButton();
        jPanel5 = new JPanel();
        jPanel5.setOpaque(false);
        jPanel6 = new JPanel();
        jPanel6.setOpaque(false);
        jPanel19 = new JPanel();
        jPanel19.setOpaque(false);
        jPanel20 = new JPanel();
        jPanel20.add(new JLabel(".                                                          ."));
        jPanel20.setOpaque(false);
        jPanel21 = new JPanel();
        jPanel21.setOpaque(false);
        jPanel22 = new JPanel();
        jPanel22.setOpaque(false);
        ETIQUETA_AJUSTES_TITULO_IDIOMA = new JLabel();
        filler2 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        CAJA_IDIOMAS = new JComboBox < > ();
        filler1 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 400), new Dimension(32767, 30));
        jPanel23 = new JPanel();
        jPanel23.setOpaque(false);
        ETIQUETA_AJUSTES_TITULO_SONIDO = new JLabel();
        BUTTON_MUSICA = new JButton();
        BUTTON_BOTON_SONIDO = new JButton();
        jPanel24 = new JPanel();
        jPanel24.setOpaque(false);
        ETIQUETA_AJUSTES_TITULO_TAMAÑO_PANTALLA = new JLabel();
        filler4 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        CAJA_TAMAÑO_PANTALLA = new JComboBox < > ();
        filler3 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 400), new Dimension(32767, 30));
        jPanel7 = new JPanel();
        jPanel7.setOpaque(false);
        jPanel8 = new JPanel(new FlowLayout());
        jPanel8.setOpaque(false);
        ETIQUETA_AJUSTES_FONDOS = new JLabel();
        jPanel9 = new JPanel();
        jPanel9.setOpaque(false);
        ETIQUETA_AJUSTES_TITULO_FONDO = new JLabel();
        jPanel10 = new JPanel();
        jPanel10.setOpaque(false);
        jPanel11 = new JPanel();
        jPanel11.setOpaque(false);
        BUTTON_AJUSTES_SIGUIENTE = new JButton();
        jPanel12 = new JPanel();
        jPanel12.setOpaque(false);
        BUTTON_AJUSTES_ANTERIOR = new JButton();
        jPanel1 = new JPanel();
        jPanel1.setOpaque(false);
        ETIQUETA_AJUSTES_TITULO = new JLabel();
        setLayout(new BorderLayout());
        jPanel4.add(BUTTON_AJUSTES_ACEPTAR);
        jPanel4.add(BUTTON_AJUSTES_CANCELAR);
        add(jPanel4, BorderLayout.PAGE_END);
        jPanel5.setLayout(new GridLayout()); //matrices 3,3
        jPanel6.setLayout(new BorderLayout());
        jPanel6.add(jPanel19, BorderLayout.LINE_START);
        jPanel6.add(jPanel20, BorderLayout.LINE_END);
        jPanel21.setLayout(new GridLayout(3, 1));
        jPanel22.setLayout(new BoxLayout(jPanel22, BoxLayout.Y_AXIS));
        ETIQUETA_AJUSTES_TITULO_IDIOMA.setText("IDIOMA");
        ETIQUETA_AJUSTES_TITULO_IDIOMA.setFont(F.fuente(Font_letras.BUNN, 0, 27));
        jPanel22.add(ETIQUETA_AJUSTES_TITULO_IDIOMA);
        jPanel22.add(filler2);

        CAJA_IDIOMAS.setModel(new DefaultComboBoxModel < > (Listaidioma));
        jPanel22.add(CAJA_IDIOMAS);
        jPanel22.add(filler1);
        jPanel21.add(jPanel22);
        jPanel23.setLayout(new BoxLayout(jPanel23, BoxLayout.Y_AXIS));
        ETIQUETA_AJUSTES_TITULO_SONIDO.setText("SONIDOS");
        ETIQUETA_AJUSTES_TITULO_SONIDO.setFont(F.fuente(Font_letras.BUNN, 0, 27));
        jPanel23.add(ETIQUETA_AJUSTES_TITULO_SONIDO);
        BUTTON_MUSICA.setText("MUSICA:");
        BUTTON_MUSICA.setFont(F.fuente(Font_letras.BUNN, 0, 18));
        CAJA_IDIOMAS.setFont(F.fuente(Font_letras.BUNN, 0, 18));
        CAJA_TAMAÑO_PANTALLA.setFont(F.fuente(Font_letras.BUNN, 0, 18));
        jPanel23.add(BUTTON_MUSICA);
        BUTTON_BOTON_SONIDO.setText("SONIDO DE BOTONES: ");
        BUTTON_BOTON_SONIDO.setFont(F.fuente(Font_letras.BUNN, 0, 18));
        jPanel23.add(BUTTON_BOTON_SONIDO);
        jPanel21.add(jPanel23);
        jPanel24.setLayout(new BoxLayout(jPanel24, BoxLayout.Y_AXIS));
        ETIQUETA_AJUSTES_TITULO_TAMAÑO_PANTALLA.setText("PANTALLA");
        ETIQUETA_AJUSTES_TITULO_TAMAÑO_PANTALLA.setFont(F.fuente(Font_letras.BUNN, 0, 27));
        jPanel24.add(ETIQUETA_AJUSTES_TITULO_TAMAÑO_PANTALLA);
        jPanel24.add(filler4);
        CAJA_TAMAÑO_PANTALLA.setModel(new DefaultComboBoxModel < > (ListaTamaños));
        jPanel24.add(CAJA_TAMAÑO_PANTALLA);
        jPanel24.add(filler3);
        jPanel21.add(jPanel24);
        jPanel6.add(jPanel21, BorderLayout.CENTER);
        jPanel5.add(jPanel6);
        jPanel7.setLayout(new BorderLayout(10, 10));
        jPanel8.add(ETIQUETA_AJUSTES_FONDOS);
        jPanel8.add(BUTTON_AJUSTES_SIGUIENTE);
        ETIQUETA_AJUSTES_FONDOS.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 9));
        jPanel7.add(jPanel8, BorderLayout.CENTER);
        ETIQUETA_AJUSTES_TITULO_FONDO.setText("FONDO DE PANTALLA");
        ETIQUETA_AJUSTES_TITULO_FONDO.setFont(F.fuente(Font_letras.BUNN, 0, 28));
        jPanel9.add(ETIQUETA_AJUSTES_TITULO_FONDO);
        jPanel7.add(jPanel9, BorderLayout.PAGE_START);
        jPanel7.add(jPanel10, BorderLayout.PAGE_END);
        jPanel11.setLayout(new BoxLayout(jPanel11, BoxLayout.LINE_AXIS));

        //jPanel11.add(BUTTON_AJUSTES_SIGUIENTE);

        jPanel7.add(jPanel12, BorderLayout.LINE_START);
        jPanel5.add(jPanel7);
        add(jPanel5, BorderLayout.CENTER);
        ETIQUETA_AJUSTES_TITULO.setText("AJUSTES");
        jPanel1.add(ETIQUETA_AJUSTES_TITULO);
        add(jPanel1, BorderLayout.PAGE_START);
        ETIQUETA_AJUSTES_TITULO.setFont(F.fuente(Font_letras.BUNN, 0, 80));





        ImageIcon iconoQWE = new ImageIcon(getClass().getResource("/recursos/imagenes/fondos/fondo_pantalla.png"));
        ImageIcon iconoEWQ = new ImageIcon(iconoQWE.getImage().getScaledInstance(220, 220, java.awt.Image.SCALE_DEFAULT));
        ETIQUETA_AJUSTES_FONDOS.setIcon(iconoEWQ);

        ImageIcon icono17 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/menu/izquierda_menu.png"));
        ImageIcon icono7 = new ImageIcon(icono17.getImage().getScaledInstance(190, 190, java.awt.Image.SCALE_DEFAULT));
        BUTTON_AJUSTES_ANTERIOR.setIcon(icono7);
        BUTTON_AJUSTES_ANTERIOR.setOpaque(false);
        BUTTON_AJUSTES_ANTERIOR.setFocusPainted(false);
        BUTTON_AJUSTES_ANTERIOR.setContentAreaFilled(false);
        BUTTON_AJUSTES_ANTERIOR.setBorderPainted(false);

        ImageIcon icono18 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/menu/derecha_menu.png"));
        ImageIcon icono8 = new ImageIcon(icono18.getImage().getScaledInstance(190, 190, java.awt.Image.SCALE_DEFAULT));
        BUTTON_AJUSTES_SIGUIENTE.setIcon(icono8);
        BUTTON_AJUSTES_SIGUIENTE.setOpaque(false);
        BUTTON_AJUSTES_SIGUIENTE.setFocusPainted(false);
        BUTTON_AJUSTES_SIGUIENTE.setContentAreaFilled(false);
        BUTTON_AJUSTES_SIGUIENTE.setBorderPainted(false);


        ImageIcon iconoACEPTAR = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/ajustes/aceptar.png"));
        ImageIcon iconoACEP = new ImageIcon(iconoACEPTAR.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
        BUTTON_AJUSTES_ACEPTAR.setIcon(iconoACEP);
        BUTTON_AJUSTES_ACEPTAR.setOpaque(false);
        BUTTON_AJUSTES_ACEPTAR.setFocusPainted(false);
        BUTTON_AJUSTES_ACEPTAR.setContentAreaFilled(false);
        BUTTON_AJUSTES_ACEPTAR.setBorderPainted(false);
        BUTTON_AJUSTES_ACEPTAR.setHorizontalTextPosition(SwingConstants.CENTER);

        ImageIcon iconoCANCELAR = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/ajustes/cancelar.png"));
        ImageIcon iconoCANEL = new ImageIcon(iconoCANCELAR.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
        BUTTON_AJUSTES_CANCELAR.setIcon(iconoCANEL);
        BUTTON_AJUSTES_CANCELAR.setOpaque(false);
        BUTTON_AJUSTES_CANCELAR.setFocusPainted(false);
        BUTTON_AJUSTES_CANCELAR.setContentAreaFilled(false);
        BUTTON_AJUSTES_CANCELAR.setBorderPainted(false);
        BUTTON_AJUSTES_CANCELAR.setHorizontalTextPosition(SwingConstants.CENTER);

        ImageIcon iconomusica = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/ajustes/boton_verde_radio.png"));
        ImageIcon iconomutedmusis = new ImageIcon(iconomusica.getImage().getScaledInstance(80, 50, java.awt.Image.SCALE_DEFAULT));
        BUTTON_MUSICA.setIcon(iconomutedmusis);
        BUTTON_MUSICA.setOpaque(false);
        BUTTON_MUSICA.setFocusPainted(false);
        BUTTON_MUSICA.setContentAreaFilled(false);
        BUTTON_MUSICA.setBorderPainted(false);
        BUTTON_MUSICA.setHorizontalTextPosition(SwingConstants.LEFT);

        ImageIcon iconosonido_b = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/ajustes/boton_verde_radio.png"));
        ImageIcon iconosonido_bs = new ImageIcon(iconosonido_b.getImage().getScaledInstance(80, 50, java.awt.Image.SCALE_DEFAULT));
        BUTTON_BOTON_SONIDO.setIcon(iconosonido_bs);
        BUTTON_BOTON_SONIDO.setOpaque(false);
        BUTTON_BOTON_SONIDO.setFocusPainted(false);
        BUTTON_BOTON_SONIDO.setContentAreaFilled(false);
        BUTTON_BOTON_SONIDO.setBorderPainted(false);
        BUTTON_BOTON_SONIDO.setHorizontalTextPosition(SwingConstants.LEFT);
    }
    public void conectaFuncionalidad(Funcionalidad c) {

        BUTTON_MUSICA.addActionListener(c);
        BUTTON_BOTON_SONIDO.addActionListener(c);
        CAJA_TAMAÑO_PANTALLA.addActionListener(c);
        CAJA_IDIOMAS.addActionListener(c);
        BUTTON_AJUSTES_ANTERIOR.addActionListener(c);
        BUTTON_AJUSTES_SIGUIENTE.addActionListener(c);
        BUTTON_AJUSTES_CANCELAR.addActionListener(c);
        BUTTON_AJUSTES_ACEPTAR.addActionListener(c);


    }
}