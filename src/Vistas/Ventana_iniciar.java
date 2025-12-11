package Vistas;
import Controlador.*;
import static java.applet.Applet.newAudioClip;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Toolkit.getDefaultToolkit;
import javax.swing.*;
public class Ventana_iniciar extends javax.swing.JPanel {
    public javax.swing.JLabel ETIQUETA_INCIO_VIDEO;
    public javax.swing.JProgressBar ITEM_BARRA;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public AudioClip sonido_inicio;
    public Ventana_iniciar() {
        Font font = new Font("BUNGEE", Font.BOLD, 10);
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        ITEM_BARRA = new javax.swing.JProgressBar();
        ETIQUETA_INCIO_VIDEO = new javax.swing.JLabel();
        ImageIcon iconoicongifg = new ImageIcon(getClass().getResource("/recursos/imagenes/videos/video_intro.gif"));
        ImageIcon icongif = new ImageIcon(iconoicongifg.getImage().getScaledInstance(getDefaultToolkit().getScreenSize().width,
            getDefaultToolkit().getScreenSize().height, java.awt.Image.SCALE_DEFAULT));
        ETIQUETA_INCIO_VIDEO.setIcon(icongif);
        setLayout(new java.awt.BorderLayout());
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(ITEM_BARRA, java.awt.BorderLayout.CENTER);
        ITEM_BARRA.setBackground(new Color(0, 153, 153));
        ITEM_BARRA.setForeground(Color.red);
        ITEM_BARRA.setStringPainted(true);
        ITEM_BARRA.setFont(font);
        sonido_inicio = newAudioClip(getClass().getResource("/recursos/audios/musica/A_sonido_intro.wav"));
        sonido_inicio.play();
        add(jPanel1, java.awt.BorderLayout.SOUTH);
        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.add(ETIQUETA_INCIO_VIDEO, BorderLayout.CENTER);
        add(jPanel2, java.awt.BorderLayout.CENTER);
        jPanel2.setOpaque(false);
        jPanel1.setOpaque(false);
        ETIQUETA_INCIO_VIDEO.setOpaque(false);

    }

    public void conectaFuncionalidad(Funcionalidad c) {

    }

}