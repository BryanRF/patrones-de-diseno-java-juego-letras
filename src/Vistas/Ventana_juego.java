package Vistas;

import Controlador.*;
import java.awt.*;
import Utilidades.Font_letras;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class Ventana_juego extends JPanel {
    public Font font;
    public String Fuente = "Snap ITC";
    public JButton BUTTON_Q, BUTTON_W, BUTTON_E, BUTTON_R, BUTTON_T, BUTTON_Y, BUTTON_U, BUTTON_I,
    BUTTON_O, BUTTON_P, BUTTON_A, BUTTON_S, BUTTON_D, BUTTON_F, BUTTON_G, BUTTON_H, BUTTON_J,
    BUTTON_K, BUTTON_L, BUTTON_NI, BUTTON_Z, BUTTON_X,
    BUTTON_C, BUTTON_V, BUTTON_B, BUTTON_N, BUTTON_M, BUTTON_NEXT,
    BUTTON_AUDIO_TEXT, BUTTON_TUTORIAL, BUTTON_AYUDA, BUTTON_SILENCIAR, BUTTON_JUEGO_MENU;
    public JLabel ETIQUETA_CARICATURA, JLabel,
    ETIQUETA_PALABRA_SECRETA, ETIQUETA_DESCRIPCION,
    ETIQUETA_TITULO, ETIQUETA_IMAGEN, ETIQUETA_INCOGNITA;
    public Font_letras F;
    public int  anchoButton= 90;
    public int  altoButton=80;
    public Ventana_juego() {
        F = new Font_letras();
        //etiquetas----------------------------------------
        ETIQUETA_CARICATURA = new JLabel();
        ETIQUETA_TITULO = new JLabel();
        ETIQUETA_PALABRA_SECRETA = new JLabel();
        ETIQUETA_DESCRIPCION = new JLabel();
        ETIQUETA_IMAGEN = new JLabel();
        ETIQUETA_INCOGNITA = new JLabel();
        //botones------------------------------------------
        BUTTON_A = new JButton();
        BUTTON_B = new JButton();
        BUTTON_C = new JButton();
        BUTTON_D = new JButton();
        BUTTON_E = new JButton();
        BUTTON_F = new JButton();
        BUTTON_G = new JButton();
        BUTTON_H = new JButton();
        BUTTON_I = new JButton();
        BUTTON_K = new JButton();
        BUTTON_J = new JButton();
        BUTTON_L = new JButton();
        BUTTON_M = new JButton();
        BUTTON_N = new JButton();
        BUTTON_NI = new JButton();
        BUTTON_O = new JButton();
        BUTTON_P = new JButton();
        BUTTON_Q = new JButton();
        BUTTON_R = new JButton();
        BUTTON_S = new JButton();
        BUTTON_T = new JButton();
        BUTTON_U = new JButton();
        BUTTON_V = new JButton();
        BUTTON_W = new JButton();
        BUTTON_X = new JButton();
        BUTTON_Y = new JButton();
        BUTTON_Z = new JButton();
        BUTTON_NEXT = new JButton();
        BUTTON_AUDIO_TEXT = new JButton();
        BUTTON_TUTORIAL = new JButton();
        BUTTON_AYUDA = new JButton();
        BUTTON_SILENCIAR = new JButton();
        BUTTON_JUEGO_MENU = new JButton();
        //.-------------------------
        NORTE = new JPanel();
        SUR = new JPanel();
        SUR_BOTONES_MENU = new JPanel();
        SUR_BOTONES_DERECHA = new JPanel();
        SUR_MID_JUEGO = new JPanel();
        CENTRO = new JPanel();
        MID_DERECHA = new JPanel();
        PANEL_CARICATURA = new JPanel();
        PANE_ABAJO_CARICATURA = new JPanel();
        MID_CENTRO = new JPanel();
        PANEL_SUB_TECLADO = new JPanel();
        PANEL_TECLADO = new JPanel();
        jPanel6 = new JPanel();
        PANEL_PALABRA = new JPanel();
        PANEL_DESCRIPCION = new JPanel();
        NORTE.setOpaque(false);
        SUR.setOpaque(false);
        /////////////////////////////////SUR.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 97, 0), 9));
        SUR_BOTONES_MENU.setOpaque(false);
        SUR_BOTONES_DERECHA.setOpaque(false);
        SUR_MID_JUEGO.setOpaque(false);
        CENTRO.setOpaque(false);
        MID_DERECHA.setOpaque(false);
        PANEL_CARICATURA.setOpaque(false);
        PANE_ABAJO_CARICATURA.setOpaque(false);
        MID_CENTRO.setOpaque(false);
        PANEL_SUB_TECLADO.setOpaque(false);
        PANEL_TECLADO.setOpaque(false);
        jPanel6.setOpaque(false);
        PANEL_PALABRA.setOpaque(false);
        PANEL_DESCRIPCION.setOpaque(false);


        NORTE.add(ETIQUETA_TITULO);



        SUR.setLayout(new java.awt.BorderLayout());

        SUR_BOTONES_MENU.setLayout(new BoxLayout(SUR_BOTONES_MENU, BoxLayout.X_AXIS));
        SUR_BOTONES_MENU.add(BUTTON_JUEGO_MENU);
        SUR_BOTONES_MENU.add(BUTTON_SILENCIAR);

        SUR.add(SUR_BOTONES_MENU, java.awt.BorderLayout.WEST);

        SUR_BOTONES_DERECHA.setLayout(new BoxLayout(SUR_BOTONES_DERECHA, BoxLayout.Y_AXIS));


        SUR_BOTONES_DERECHA.add(BUTTON_TUTORIAL);

        SUR.add(SUR_BOTONES_DERECHA, java.awt.BorderLayout.EAST);

        SUR_MID_JUEGO.add(BUTTON_AYUDA);
        SUR_MID_JUEGO.add(BUTTON_AUDIO_TEXT);
        SUR_MID_JUEGO.add(BUTTON_NEXT);
        //SUR_MID_JUEGO.setLayout(new BoxLayout(SUR_BOTONES_DERECHA, BoxLayout.X_AXIS));
        SUR.add(SUR_MID_JUEGO, java.awt.BorderLayout.CENTER);

        CENTRO.setLayout(new java.awt.BorderLayout());
        CENTRO.setBorder(new EmptyBorder(5, 0, 5, 0));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        MID_DERECHA.setLayout(new java.awt.GridLayout(2, 1));
        //MID_DERECHA.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 97, 0), 9));
        MID_DERECHA.add(PANEL_CARICATURA);
        PANEL_CARICATURA.setLayout(new FlowLayout());
        PANEL_CARICATURA.add(ETIQUETA_CARICATURA);
        ImageIcon icon_caricatura = new ImageIcon(getClass().getResource("/recursos/imagenes/personajes/duende/duende.png"));
        ImageIcon caricatura = new ImageIcon(icon_caricatura.getImage().getScaledInstance(220, 220, java.awt.Image.SCALE_DEFAULT));
        ETIQUETA_CARICATURA.setIcon(caricatura);
        //ETIQUETA_CARICATURA.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 97, 0), 9));
        MID_DERECHA.add(PANE_ABAJO_CARICATURA);
        PANE_ABAJO_CARICATURA.setLayout(new FlowLayout());
        PANE_ABAJO_CARICATURA.add(ETIQUETA_IMAGEN);
        //ETIQUETA_IMAGEN.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 97, 0), 9));
        ETIQUETA_IMAGEN.setVisible(false);
        PANE_ABAJO_CARICATURA.add(ETIQUETA_INCOGNITA);
        //ETIQUETA_INCOGNITA.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 97, 0), 9));
        ETIQUETA_INCOGNITA.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/fondos/fondo_celeste.png")).getImage().getScaledInstance(220, 220, java.awt.Image.SCALE_DEFAULT)));
        ETIQUETA_INCOGNITA.setFont(new Font(Fuente, 1, 120));
        ETIQUETA_INCOGNITA.setHorizontalTextPosition(SwingConstants.CENTER);
        CENTRO.add(MID_DERECHA, java.awt.BorderLayout.EAST);

        MID_CENTRO.setLayout(new java.awt.BorderLayout());
        //MID_CENTRO.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 97, 0), 9));
        //MID_DERECHA.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 97, 0), 9));

        //
        PANEL_SUB_TECLADO.setLayout(new GridLayout(4, 7));

        PANEL_SUB_TECLADO.add(BUTTON_Q);
        PANEL_SUB_TECLADO.add(BUTTON_W);
        PANEL_SUB_TECLADO.add(BUTTON_E);
        PANEL_SUB_TECLADO.add(BUTTON_R);
        PANEL_SUB_TECLADO.add(BUTTON_T);
        PANEL_SUB_TECLADO.add(BUTTON_Y);
        PANEL_SUB_TECLADO.add(BUTTON_U);
        PANEL_SUB_TECLADO.add(BUTTON_I);
        PANEL_SUB_TECLADO.add(BUTTON_O);
        PANEL_SUB_TECLADO.add(BUTTON_P);
        PANEL_SUB_TECLADO.add(BUTTON_A);
        PANEL_SUB_TECLADO.add(BUTTON_S);
        PANEL_SUB_TECLADO.add(BUTTON_D);
        PANEL_SUB_TECLADO.add(BUTTON_F);
        PANEL_SUB_TECLADO.add(BUTTON_G);
        PANEL_SUB_TECLADO.add(BUTTON_H);
        PANEL_SUB_TECLADO.add(BUTTON_J);
        PANEL_SUB_TECLADO.add(BUTTON_K);
        PANEL_SUB_TECLADO.add(BUTTON_L);
        PANEL_SUB_TECLADO.add(BUTTON_NI);
        PANEL_SUB_TECLADO.add(BUTTON_Z);
        PANEL_SUB_TECLADO.add(BUTTON_X);
        PANEL_SUB_TECLADO.add(BUTTON_C);
        PANEL_SUB_TECLADO.add(BUTTON_V);
        PANEL_SUB_TECLADO.add(BUTTON_B);
        PANEL_SUB_TECLADO.add(BUTTON_N);
        PANEL_SUB_TECLADO.add(BUTTON_M);

     

        //PANEL_SUB_TECLADO.add(PANEL_TECLADO);
        MID_CENTRO.add(PANEL_SUB_TECLADO, java.awt.BorderLayout.PAGE_END);
         panel_pizarra.setLayout(new java.awt.BorderLayout());
         panel_pizarra.setBorder(new EmptyBorder(25, 25, 25, 25));

        PANEL_PALABRA.add(ETIQUETA_PALABRA_SECRETA);
        panel_pizarra.add(PANEL_PALABRA, java.awt.BorderLayout.SOUTH);
        PANEL_DESCRIPCION.setLayout(new FlowLayout());
        PANEL_DESCRIPCION.add(ETIQUETA_DESCRIPCION);
        panel_pizarra.add(PANEL_DESCRIPCION, java.awt.BorderLayout.NORTH);
        MID_CENTRO.add(panel_pizarra, java.awt.BorderLayout.CENTER);
        CENTRO.add(MID_CENTRO, java.awt.BorderLayout.CENTER);



        font = new Font(Fuente, 0, 30);
        BUTTON_TUTORIAL.setText("TUTORIAL");
        ETIQUETA_TITULO.setText("COMIENZA A JUGAR");
        ETIQUETA_PALABRA_SECRETA.setText("ETIQUETA_PALABRA_SECRETA");
        ETIQUETA_DESCRIPCION.setText("ETIQUETA_DESCRIPCION");
        
        BUTTON_TUTORIAL.setFont(F.fuente(Font_letras.BUNN, 0, 35));
        ETIQUETA_PALABRA_SECRETA.setFont(new Font(Fuente, 0, 40));
        ETIQUETA_DESCRIPCION.setFont(new Font(Fuente, 1, 25));
        ETIQUETA_DESCRIPCION.setForeground(Color.white);
        ETIQUETA_TITULO.setFont(F.fuente(Font_letras.BUNN, 0, 48));
        BUTTON_JUEGO_MENU.setFont(font);
        BUTTON_NEXT.setFont(font);
        BUTTON_SILENCIAR.setFont(font);
        BUTTON_AUDIO_TEXT.setFont(F.fuente(Font_letras.BUNN, 0, 26));
        BUTTON_AYUDA.setFont(font);

        ImageIcon icono = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/a.png"));
        ImageIcon iconoa = new ImageIcon(icono.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono1 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/b.png"));
        ImageIcon iconob = new ImageIcon(icono1.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono2 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/c.png"));
        ImageIcon iconoc = new ImageIcon(icono2.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono3 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/d.png"));
        ImageIcon iconod = new ImageIcon(icono3.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono4 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/e.png"));
        ImageIcon iconoe = new ImageIcon(icono4.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono5 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/f.png"));
        ImageIcon iconof = new ImageIcon(icono5.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono6 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/g.png"));
        ImageIcon iconog = new ImageIcon(icono6.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono7 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/h.png"));
        ImageIcon iconoh = new ImageIcon(icono7.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono8 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/i.png"));
        ImageIcon iconoi = new ImageIcon(icono8.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono9 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/j.png"));
        ImageIcon iconoj = new ImageIcon(icono9.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono10 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/k.png"));
        ImageIcon iconok = new ImageIcon(icono10.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono11 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/m.png"));
        ImageIcon iconom = new ImageIcon(icono11.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono12 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/n.png"));
        ImageIcon iconon = new ImageIcon(icono12.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono13 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/ni.png"));
        ImageIcon iconoñ = new ImageIcon(icono13.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono14 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/l.png"));
        ImageIcon iconol = new ImageIcon(icono14.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono15 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/o.png"));
        ImageIcon iconoo = new ImageIcon(icono15.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono16 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/p.png"));
        ImageIcon iconop = new ImageIcon(icono16.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono17 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/q.png"));
        ImageIcon iconoq = new ImageIcon(icono17.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono18 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/r.png"));
        ImageIcon iconor = new ImageIcon(icono18.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono19 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/s.png"));
        ImageIcon iconos = new ImageIcon(icono19.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono20 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/t.png"));
        ImageIcon iconot = new ImageIcon(icono20.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono21 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/u.png"));
        ImageIcon iconou = new ImageIcon(icono21.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono22 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/v.png"));
        ImageIcon iconov = new ImageIcon(icono22.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono23 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/w.png"));
        ImageIcon iconow = new ImageIcon(icono23.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono24 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/x.png"));
        ImageIcon iconox = new ImageIcon(icono24.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono25 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/y.png"));
        ImageIcon iconoy = new ImageIcon(icono25.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));
        ImageIcon icono26 = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/teclado/z.png"));
        ImageIcon iconoz = new ImageIcon(icono26.getImage().getScaledInstance(anchoButton,altoButton, java.awt.Image.SCALE_DEFAULT));


        BUTTON_Q.setIcon(iconoq);
        BUTTON_W.setIcon(iconow);
        BUTTON_E.setIcon(iconoe);
        BUTTON_R.setIcon(iconor);
        BUTTON_T.setIcon(iconot);
        BUTTON_Y.setIcon(iconoy);
        BUTTON_U.setIcon(iconou);
        BUTTON_I.setIcon(iconoi);
        BUTTON_O.setIcon(iconoo);
        BUTTON_P.setIcon(iconop);
        BUTTON_A.setIcon(iconoa);
        BUTTON_S.setIcon(iconos);
        BUTTON_D.setIcon(iconod);
        BUTTON_F.setIcon(iconof);
        BUTTON_G.setIcon(iconog);
        BUTTON_H.setIcon(iconoh);
        BUTTON_J.setIcon(iconoj);
        BUTTON_K.setIcon(iconok);
        BUTTON_L.setIcon(iconol);
        BUTTON_NI.setIcon(iconoñ);
        BUTTON_Z.setIcon(iconoz);
        BUTTON_X.setIcon(iconox);
        BUTTON_C.setIcon(iconoc);
        BUTTON_V.setIcon(iconov);
        BUTTON_B.setIcon(iconob);
        BUTTON_N.setIcon(iconon);
        BUTTON_M.setIcon(iconom);


        BUTTON_Q.setBorderPainted(false);
        BUTTON_W.setBorderPainted(false);
        BUTTON_E.setBorderPainted(false);
        BUTTON_R.setBorderPainted(false);
        BUTTON_T.setBorderPainted(false);
        BUTTON_Y.setBorderPainted(false);
        BUTTON_U.setBorderPainted(false);
        BUTTON_I.setBorderPainted(false);
        BUTTON_O.setBorderPainted(false);
        BUTTON_P.setBorderPainted(false);
        BUTTON_A.setBorderPainted(false);
        BUTTON_S.setBorderPainted(false);
        BUTTON_D.setBorderPainted(false);
        BUTTON_F.setBorderPainted(false);
        BUTTON_G.setBorderPainted(false);
        BUTTON_H.setBorderPainted(false);
        BUTTON_J.setBorderPainted(false);
        BUTTON_K.setBorderPainted(false);
        BUTTON_L.setBorderPainted(false);
        BUTTON_NI.setBorderPainted(false);
        BUTTON_Z.setBorderPainted(false);
        BUTTON_X.setBorderPainted(false);
        BUTTON_C.setBorderPainted(false);
        BUTTON_V.setBorderPainted(false);
        BUTTON_B.setBorderPainted(false);
        BUTTON_N.setBorderPainted(false);
        BUTTON_M.setBorderPainted(false);

        BUTTON_Q.setContentAreaFilled(false);
        BUTTON_W.setContentAreaFilled(false);
        BUTTON_E.setContentAreaFilled(false);
        BUTTON_R.setContentAreaFilled(false);
        BUTTON_T.setContentAreaFilled(false);
        BUTTON_Y.setContentAreaFilled(false);
        BUTTON_U.setContentAreaFilled(false);
        BUTTON_I.setContentAreaFilled(false);
        BUTTON_O.setContentAreaFilled(false);
        BUTTON_P.setContentAreaFilled(false);
        BUTTON_A.setContentAreaFilled(false);
        BUTTON_S.setContentAreaFilled(false);
        BUTTON_D.setContentAreaFilled(false);
        BUTTON_F.setContentAreaFilled(false);
        BUTTON_G.setContentAreaFilled(false);
        BUTTON_H.setContentAreaFilled(false);
        BUTTON_J.setContentAreaFilled(false);
        BUTTON_K.setContentAreaFilled(false);
        BUTTON_L.setContentAreaFilled(false);
        BUTTON_NI.setContentAreaFilled(false);
        BUTTON_Z.setContentAreaFilled(false);
        BUTTON_X.setContentAreaFilled(false);
        BUTTON_C.setContentAreaFilled(false);
        BUTTON_V.setContentAreaFilled(false);
        BUTTON_B.setContentAreaFilled(false);
        BUTTON_N.setContentAreaFilled(false);
        BUTTON_M.setContentAreaFilled(false);

        BUTTON_Q.setFocusPainted(false);
        BUTTON_W.setFocusPainted(false);
        BUTTON_E.setFocusPainted(false);
        BUTTON_R.setFocusPainted(false);
        BUTTON_T.setFocusPainted(false);
        BUTTON_Y.setFocusPainted(false);
        BUTTON_U.setFocusPainted(false);
        BUTTON_I.setFocusPainted(false);
        BUTTON_O.setFocusPainted(false);
        BUTTON_P.setFocusPainted(false);
        BUTTON_A.setFocusPainted(false);
        BUTTON_S.setFocusPainted(false);
        BUTTON_D.setFocusPainted(false);
        BUTTON_F.setFocusPainted(false);
        BUTTON_G.setFocusPainted(false);
        BUTTON_H.setFocusPainted(false);
        BUTTON_J.setFocusPainted(false);
        BUTTON_K.setFocusPainted(false);
        BUTTON_L.setFocusPainted(false);
        BUTTON_NI.setFocusPainted(false);
        BUTTON_Z.setFocusPainted(false);
        BUTTON_X.setFocusPainted(false);
        BUTTON_C.setFocusPainted(false);
        BUTTON_V.setFocusPainted(false);
        BUTTON_B.setFocusPainted(false);
        BUTTON_N.setFocusPainted(false);
        BUTTON_M.setFocusPainted(false);

        BUTTON_Q.setOpaque(false);
        BUTTON_W.setOpaque(false);
        BUTTON_E.setOpaque(false);
        BUTTON_R.setOpaque(false);
        BUTTON_T.setOpaque(false);
        BUTTON_Y.setOpaque(false);
        BUTTON_U.setOpaque(false);
        BUTTON_I.setOpaque(false);
        BUTTON_O.setOpaque(false);
        BUTTON_P.setOpaque(false);
        BUTTON_A.setOpaque(false);
        BUTTON_S.setOpaque(false);
        BUTTON_D.setOpaque(false);
        BUTTON_F.setOpaque(false);
        BUTTON_G.setOpaque(false);
        BUTTON_H.setOpaque(false);
        BUTTON_J.setOpaque(false);
        BUTTON_K.setOpaque(false);
        BUTTON_L.setOpaque(false);
        BUTTON_NI.setOpaque(false);
        BUTTON_Z.setOpaque(false);
        BUTTON_X.setOpaque(false);
        BUTTON_C.setOpaque(false);
        BUTTON_V.setOpaque(false);
        BUTTON_B.setOpaque(false);
        BUTTON_N.setOpaque(false);
        BUTTON_M.setOpaque(false);
        //BOTONES DE JUEGO
        ImageIcon icono00 = new ImageIcon(getClass().getResource("/recursos/imagenes/objetos/habitaciones/casa.png"));
        ImageIcon icono0 = new ImageIcon(icono00.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
        BUTTON_JUEGO_MENU.setIcon(icono0);
        BUTTON_JUEGO_MENU.setOpaque(false);
        BUTTON_JUEGO_MENU.setFocusPainted(false);
        BUTTON_JUEGO_MENU.setContentAreaFilled(false);
        BUTTON_JUEGO_MENU.setBorderPainted(false);
        BUTTON_JUEGO_MENU.setHorizontalTextPosition(SwingConstants.CENTER);

        java.net.URL urlMute = getClass().getResource("/recursos/imagenes/ui/mute.png");
        if (urlMute == null) {
            throw new RuntimeException("No se pudo cargar el recurso: /recursos/imagenes/ui/mute.png");
        }
        ImageIcon icono01 = new ImageIcon(urlMute);
        ImageIcon iconoMUTED = new ImageIcon(icono01.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
        BUTTON_SILENCIAR.setIcon(iconoMUTED);
        BUTTON_SILENCIAR.setOpaque(false);
        BUTTON_SILENCIAR.setFocusPainted(false);
        BUTTON_SILENCIAR.setContentAreaFilled(false);
        BUTTON_SILENCIAR.setBorderPainted(false);
        BUTTON_SILENCIAR.setHorizontalTextPosition(SwingConstants.CENTER);

        java.net.URL urlAyuda = getClass().getResource("/recursos/imagenes/botones/juego/ayuda.png");
        if (urlAyuda == null) {
            throw new RuntimeException("No se pudo cargar el recurso: /recursos/imagenes/botones/juego/ayuda.png");
        }
        ImageIcon iconoHELP = new ImageIcon(urlAyuda);
        ImageIcon iconoAYUDA = new ImageIcon(iconoHELP.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
        BUTTON_AYUDA.setIcon(iconoAYUDA);
        BUTTON_AYUDA.setOpaque(false);
        BUTTON_AYUDA.setFocusPainted(false);
        BUTTON_AYUDA.setContentAreaFilled(false);
        BUTTON_AYUDA.setBorderPainted(false);
        BUTTON_AYUDA.setHorizontalTextPosition(SwingConstants.CENTER);

        java.net.URL urlBoton = getClass().getResource("/recursos/imagenes/botones/juego/boton.png");
        if (urlBoton == null) {
            throw new RuntimeException("No se pudo cargar el recurso: /recursos/imagenes/botones/juego/boton.png");
        }
        ImageIcon iconoAUDIO = new ImageIcon(urlBoton);
        ImageIcon iconoAUDIOTEXT = new ImageIcon(iconoAUDIO.getImage().getScaledInstance(130, 100, java.awt.Image.SCALE_DEFAULT));
        BUTTON_AUDIO_TEXT.setIcon(iconoAUDIOTEXT);
        BUTTON_AUDIO_TEXT.setOpaque(false);
        BUTTON_AUDIO_TEXT.setFocusPainted(false);
        BUTTON_AUDIO_TEXT.setContentAreaFilled(false);
        BUTTON_AUDIO_TEXT.setBorderPainted(false);
        BUTTON_AUDIO_TEXT.setText("LEER");
        BUTTON_AUDIO_TEXT.setHorizontalTextPosition(SwingConstants.CENTER);

        java.net.URL urlDerecha = getClass().getResource("/recursos/imagenes/botones/menu/derecha_menu.png");
        if (urlDerecha == null) {
            throw new RuntimeException("No se pudo cargar el recurso: /recursos/imagenes/botones/menu/derecha_menu.png");
        }
        ImageIcon iconoNEXT = new ImageIcon(urlDerecha);
        ImageIcon iconoNEXTS = new ImageIcon(iconoNEXT.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
        BUTTON_NEXT.setIcon(iconoNEXTS);
        BUTTON_NEXT.setOpaque(false);
        BUTTON_NEXT.setFocusPainted(false);
        BUTTON_NEXT.setContentAreaFilled(false);
        BUTTON_NEXT.setBorderPainted(false);
        BUTTON_NEXT.setHorizontalTextPosition(SwingConstants.CENTER);

        java.net.URL urlBoton2 = getClass().getResource("/recursos/imagenes/botones/juego/boton.png");
        if (urlBoton2 == null) {
            throw new RuntimeException("No se pudo cargar el recurso: /recursos/imagenes/botones/juego/boton.png");
        }
        ImageIcon iconoTuto = new ImageIcon(urlBoton2);
        ImageIcon iconoTutorial = new ImageIcon(iconoTuto.getImage().getScaledInstance(320, 100, java.awt.Image.SCALE_DEFAULT));
        BUTTON_TUTORIAL.setIcon(iconoTutorial);
        BUTTON_TUTORIAL.setOpaque(false);
        BUTTON_TUTORIAL.setFocusPainted(false);
        BUTTON_TUTORIAL.setContentAreaFilled(false);
        BUTTON_TUTORIAL.setBorderPainted(false);
        BUTTON_TUTORIAL.setHorizontalTextPosition(SwingConstants.CENTER);

        
        ETIQUETA_PALABRA_SECRETA.setOpaque(false);
        ETIQUETA_PALABRA_SECRETA.setForeground(Color.white);
        ETIQUETA_PALABRA_SECRETA.setHorizontalTextPosition(SwingConstants.CENTER);

  
        //ETIQUETA_DESCRIPCION.setOpaque(false);
        ETIQUETA_DESCRIPCION.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setLayout(new BorderLayout());

        java.net.URL urlFondo = getClass().getResource("/recursos/imagenes/fondos/fondo_pantalla.png");
        if (urlFondo == null) {
            throw new RuntimeException("No se pudo cargar el recurso: /recursos/imagenes/fondos/fondo_pantalla.png");
        }
        imagen = new ImageIcon(urlFondo).getImage();
        //imagen1 = new ImageIcon(getClass().getResource("/recursos/imagenes/fondo_sur.png")).getImage();
        add(NORTE, java.awt.BorderLayout.NORTH);
        add(CENTRO, java.awt.BorderLayout.CENTER);
        add(SUR, java.awt.BorderLayout.SOUTH);


    }
    //"/recursos/imagenes/pizarra.png"
    public Image imagen;
    public Image imagen1;
    @Override
    public void paint(Graphics g) {
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(),
            this);
        setOpaque(false);

        super.paint(g);
    }
    public void paint1(Graphics g) {
        g.drawImage(imagen1, 0, 0, getWidth(), getHeight(),
            this.SUR);
        SUR.setOpaque(false);
        super.paint(g);
    }



    private JPanel CENTRO;
    private JPanel MID_DERECHA;
    private JPanel NORTE;
    private JPanel PANEL_CARICATURA;
    private JPanel PANEL_DESCRIPCION;
    private JPanel PANEL_PALABRA;
    private JPanel PANEL_SUB_TECLADO;
    private JPanel PANEL_TECLADO;
    private JPanel PANE_ABAJO_CARICATURA;
    private JPanel SUR;
    private JPanel SUR_BOTONES_DERECHA;
    private JPanel SUR_BOTONES_MENU;
    private JPanel SUR_MID_JUEGO;
    private JPanel MID_CENTRO;
    private JPanel jPanel6;
    public void conectaFuncionalidad(Funcionalidad c) {


        BUTTON_Q.addActionListener(c);
        BUTTON_G.addActionListener(c);
        BUTTON_W.addActionListener(c);
        BUTTON_H.addActionListener(c);
        BUTTON_E.addActionListener(c);
        BUTTON_J.addActionListener(c);
        BUTTON_R.addActionListener(c);
        BUTTON_K.addActionListener(c);
        BUTTON_T.addActionListener(c);
        BUTTON_L.addActionListener(c);
        BUTTON_Y.addActionListener(c);
        BUTTON_NI.addActionListener(c);
        BUTTON_U.addActionListener(c);
        BUTTON_Z.addActionListener(c);
        BUTTON_I.addActionListener(c);
        BUTTON_X.addActionListener(c);
        BUTTON_O.addActionListener(c);
        BUTTON_C.addActionListener(c);
        BUTTON_P.addActionListener(c);
        BUTTON_V.addActionListener(c);
        BUTTON_A.addActionListener(c);
        BUTTON_B.addActionListener(c);
        BUTTON_S.addActionListener(c);
        BUTTON_N.addActionListener(c);
        BUTTON_D.addActionListener(c);
        BUTTON_M.addActionListener(c);
        BUTTON_F.addActionListener(c);
        BUTTON_NEXT.addActionListener(c);
        BUTTON_AYUDA.addActionListener(c);
        BUTTON_AUDIO_TEXT.addActionListener(c);
        BUTTON_SILENCIAR.addActionListener(c);
        BUTTON_JUEGO_MENU.addActionListener(c);
        BUTTON_TUTORIAL.addActionListener(c);

        BUTTON_Q.addMouseListener(c.getAnimation());
        BUTTON_G.addMouseListener(c.getAnimation());
        BUTTON_W.addMouseListener(c.getAnimation());
        BUTTON_H.addMouseListener(c.getAnimation());
        BUTTON_E.addMouseListener(c.getAnimation());
        BUTTON_J.addMouseListener(c.getAnimation());
        BUTTON_R.addMouseListener(c.getAnimation());
        BUTTON_K.addMouseListener(c.getAnimation());
        BUTTON_T.addMouseListener(c.getAnimation());
        BUTTON_L.addMouseListener(c.getAnimation());
        BUTTON_Y.addMouseListener(c.getAnimation());
        BUTTON_NI.addMouseListener(c.getAnimation());
        BUTTON_U.addMouseListener(c.getAnimation());
        BUTTON_Z.addMouseListener(c.getAnimation());
        BUTTON_I.addMouseListener(c.getAnimation());
        BUTTON_X.addMouseListener(c.getAnimation());
        BUTTON_O.addMouseListener(c.getAnimation());
        BUTTON_C.addMouseListener(c.getAnimation());
        BUTTON_P.addMouseListener(c.getAnimation());
        BUTTON_V.addMouseListener(c.getAnimation());
        BUTTON_A.addMouseListener(c.getAnimation());
        BUTTON_B.addMouseListener(c.getAnimation());
        BUTTON_S.addMouseListener(c.getAnimation());
        BUTTON_N.addMouseListener(c.getAnimation());
        BUTTON_D.addMouseListener(c.getAnimation());
        BUTTON_M.addMouseListener(c.getAnimation());
        BUTTON_F.addMouseListener(c.getAnimation());
        BUTTON_NEXT.addMouseListener(c.getAnimation());
        BUTTON_AYUDA.addMouseListener(c.getAnimation());
        BUTTON_AUDIO_TEXT.addMouseListener(c.getAnimation());
        BUTTON_SILENCIAR.addMouseListener(c.getAnimation());
        BUTTON_JUEGO_MENU.addMouseListener(c.getAnimation());
        BUTTON_TUTORIAL.addMouseListener(c.getAnimation());


    }
      public Image fondo;
     public JPanel panel_pizarra = new JPanel(){
        @Override
        public void paint(Graphics g) {
            this.setOpaque(false);
        java.net.URL urlPizarra = getClass().getResource("/recursos/imagenes/ui/pizarra.png");
        if (urlPizarra == null) {
            throw new RuntimeException("No se pudo cargar el recurso: /recursos/imagenes/ui/pizarra.png");
        }
        fondo = new ImageIcon(urlPizarra).getImage();
        g.drawImage(fondo, 0, 0, getWidth(),getHeight(),
                        null);
 
        super.paint(g);

        }};   
}
