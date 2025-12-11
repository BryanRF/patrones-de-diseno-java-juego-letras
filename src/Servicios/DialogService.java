package Servicios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

/**
 * Servicio para crear y mostrar ventanas modales (diálogos)
 * Centraliza la creación de ventanas temporales
 */
public class DialogService {
    
    /**
     * Muestra una ventana de ayuda con una letra
     * @param letra Letra a mostrar
     * @param duracionMs Duración en milisegundos
     */
    public static void mostrarAyuda(char letra, int duracionMs) {
        JWindow window = new JWindow();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBackground(new Color(253, 192, 6));
        
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setText(String.valueOf(letra));
        label.setFont(new Font("Snap ITC", Font.BOLD, 120));
        label.setForeground(Color.WHITE);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        
        ImageIcon icono = new ImageIcon(DialogService.class.getResource("/recursos/imagenes/ui/marco.png"));
        ImageIcon iconoEscalado = new ImageIcon(icono.getImage().getScaledInstance(220, 220, java.awt.Image.SCALE_DEFAULT));
        label.setIcon(iconoEscalado);
        
        panel.add(label, BorderLayout.CENTER);
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                window.dispose();
            }
        }, duracionMs);
    }
    
    /**
     * Muestra una ventana de tutorial con un GIF
     * @param duracionMs Duración en milisegundos
     */
    public static void mostrarTutorial(int duracionMs) {
        JWindow window = new JWindow();
        window.setOpacity(0.9f);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(253, 192, 6));
        
        JLabel label = new JLabel();
        ImageIcon icono = new ImageIcon(DialogService.class.getResource("/recursos/imagenes/videos/tutorial.gif"));
        ImageIcon iconoEscalado = new ImageIcon(icono.getImage().getScaledInstance(450, 450, java.awt.Image.SCALE_DEFAULT));
        label.setIcon(iconoEscalado);
        
        panel.add(label, BorderLayout.CENTER);
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                window.dispose();
            }
        }, duracionMs);
    }
    
    /**
     * Muestra una ventana de finalización de categoría
     * @param duracionMs Duración en milisegundos
     */
    public static void mostrarFinalizacionCategoria(int duracionMs) {
        JWindow window = new JWindow();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(253, 192, 6));
        
        JLabel label = new JLabel();
        ImageIcon icono = new ImageIcon(DialogService.class.getResource("/recursos/imagenes/ui/buho.png"));
        ImageIcon iconoEscalado = new ImageIcon(icono.getImage().getScaledInstance(450, 450, java.awt.Image.SCALE_DEFAULT));
        label.setIcon(iconoEscalado);
        
        panel.add(label, BorderLayout.CENTER);
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                window.dispose();
            }
        }, duracionMs);
    }
}

