package Controlador;
import java.awt.event.*;
import Vistas.*;
import Modelo.Builder.*;
import Modelo.Observer.*;
import Modelo.Singleton.Singleton_ajustes;
import Servicios.CargarRecursos;
import Utilidades.ButtonAnimationManager;
import javax.sound.sampled.Clip;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public final class Funcionalidad implements ActionListener{
    // Vistas (nombres descriptivos en camelCase)
    private final Ventana_principal ventanaPrincipal;
    private final Ventana_juego ventanaJuego;
    private final Ventana_menu ventanaMenu;
    private final Ventana_ajustes ventanaAjustes;
    private final Ventana_iniciar ventanaInicio;
    
    // Builder Pattern - objetos de construcción
    private final Habitaciones objetoHabitaciones;
    private final Colores objetoColores;
    private final Paises objetoPaises;
    
    // Observer Pattern
    private final Palabra palabra;
    private final ObservadorDepalabras observadorPalabras;
    
    // Singleton Pattern
    private final Singleton_ajustes singletonAjustes;
    
    // Estado del juego (delegado a controladores cuando sea posible)
    private boolean palabraCompletada = false;
    private boolean cambiosImagenDescripcion = false;
    
    // Configuración y recursos
    private final CargarRecursos recursos;
    private final ButtonAnimationManager animation;
    
    // Estado de audio/música (debería estar en AudioController)
    private int posicionMusica = 0; // 0 = música activa, 1 = música desactivada
    private boolean sonidoBotonesHabilitado = true;
    
    // Estado de inicialización
    private int iniciarConDuende = 0;
    
    // Controladores especializados (SOLID - Single Responsibility)
    private final JuegoController juegoController;
    private final MenuController menuController;
    private final AjustesController ajustesController;
    private final VistaController vistaController;
    private final AudioController audioController;

    public ButtonAnimationManager getAnimation() {
        return animation;
    }
    
    public Funcionalidad(Ventana_principal ventanaPrincipal,
        Ventana_juego ventanaJuego,
        Ventana_menu ventanaMenu,
        Ventana_ajustes ventanaAjustes,
        Ventana_iniciar ventanaInicio) {
        // Inicializar vistas
        this.ventanaPrincipal = ventanaPrincipal;
        this.ventanaJuego = ventanaJuego;
        this.ventanaMenu = ventanaMenu;
        this.ventanaAjustes = ventanaAjustes;
        this.ventanaInicio = ventanaInicio;
        
        // Inicializar Builder Pattern
        this.objetoHabitaciones = new Habitaciones();
        this.objetoColores = new Colores();
        this.objetoPaises = new Paises();
        
        // Inicializar Observer Pattern
        this.palabra = new Palabra();
        this.observadorPalabras = new ObservadorDepalabras(palabra);
        palabra.agregar(observadorPalabras);
        
        // Inicializar Singleton Pattern
        this.singletonAjustes = Singleton_ajustes.getpantalla();
        this.ventanaJuego.imagen = new ImageIcon(singletonAjustes.obtenerSiguienteFondo()).getImage();
        
        // Inicializar recursos
        this.recursos = new CargarRecursos();
        
        // Inicializar controladores especializados (SOLID)
        this.audioController = new AudioController(recursos);
        this.vistaController = new VistaController(ventanaPrincipal);
        this.juegoController = new JuegoController(ventanaJuego);
        this.menuController = new MenuController(ventanaMenu);
        this.ajustesController = new AjustesController(ventanaAjustes);
        
        // Configurar referencias cruzadas necesarias
        menuController.setVentanaJuego(ventanaJuego);
        menuController.setAudioController(audioController);
        ajustesController.setVentanaJuego(ventanaJuego);
        
        // Configurar observador para usar AudioController y obtener índice del personaje
        // (debe hacerse después de crear los controladores)
        observadorPalabras.setAudioController(audioController);
        observadorPalabras.setObtenerIndicePersonaje(() -> menuController.getPosicionPersonaje());
        
        // Inicializar animaciones
        this.animation = new ButtonAnimationManager(
            ventanaJuego, ventanaMenu, ventanaAjustes, 
            recursos, sonidoBotonesHabilitado, posicionMusica);

        // Configurar ventana principal
        this.ventanaPrincipal.setVisible(true);
        this.ventanaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaJuego.BUTTON_NEXT.setEnabled(false);
        ventanaJuego.BUTTON_AUDIO_TEXT.setEnabled(false);
        
        // Iniciar pantalla de carga
        iniciarPantallaCarga();
                
        // Ajustar para pantallas pequeñas
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    double height = screenSize.getHeight();
        if(height < 770){
                        animation.setTamaño(50, 60);
            ventanaPrincipal.VENTANA_MENU.ETIQUETA_TITULO_MENU.setIcon(
                new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/ui/titulo.png"))
                    .getImage().getScaledInstance(470,130, java.awt.Image.SCALE_DEFAULT)));
                    }
                    
        this.ventanaPrincipal.setLocationRelativeTo(null); 
    }
    //-------------------------- AQUI COMIENZA ACCIONES ---------------
    @Override
    public void actionPerformed(ActionEvent e) {
        //----------------------ACIONES DE MENU---------------------------------
        if (this.ventanaMenu.BUTTON_MENU_JUGAR == e.getSource()) {
            audioController.detenerTutorial();
            vistaController.mostrarJuego();
            juegoController.getButtonManager().activarTodos();
            
            // Inicializar palabra usando JuegoController
            Tipo_Objeto tipoObjeto = juegoController.obtenerTipoObjetoPorPosicion(
                new Tipo_Objeto[]{objetoColores, objetoHabitaciones, objetoPaises}, 
                menuController.getPosicionTipoObjeto());
            juegoController.inicializarPalabraDesdeObjeto(tipoObjeto, palabra);
            
            if (iniciarConDuende == 0) {
                audioController.reproducirHola(menuController.getPosicionPersonaje());
                iniciarConDuende = 1;
            } else {
                audioController.reproducirJugar(menuController.getPosicionPersonaje());
            }
            this.ventanaJuego.BUTTON_AUDIO_TEXT.setEnabled(false);
            this.ventanaJuego.BUTTON_NEXT.setEnabled(false);

        } else
        if (this.ventanaMenu.BUTTON_MENU_ANTERIOR == e.getSource()) {
            audioController.reproducirSonidoBoton();
            menuController.anterior();
            if (!menuController.isBaseCambio()) {
                juegoController.resetearPosicionPalabra();
            }
            ventanaJuego.BUTTON_NEXT.setEnabled(false);
            ventanaJuego.BUTTON_AUDIO_TEXT.setEnabled(false);
            menuController.cambiarObjetoImagen();
            menuController.cambiarPersonaje();

        } else
        if (this.ventanaMenu.BUTTON_MENU_SIGUIENTE == e.getSource()) {
            audioController.reproducirSonidoBoton();
            menuController.siguiente();
            if (!menuController.isBaseCambio()) {
                juegoController.resetearPosicionPalabra();
                ventanaJuego.BUTTON_NEXT.setEnabled(false);
                ventanaJuego.BUTTON_AUDIO_TEXT.setEnabled(false);
            }
            menuController.cambiarPersonaje();
            menuController.cambiarObjetoImagen();

        } else
        if (this.ventanaMenu.BUTTON_MENU_CAMBIO == e.getSource()) {
            audioController.reproducirSonidoBoton();
            menuController.cambiarModo();
            // Actualizar el personaje visualmente después de cambiar el modo
            menuController.cambiarPersonaje();
            menuController.cambiarObjetoImagen();
        } else
        if (this.ventanaMenu.BUTTON_MENU_SALIR == e.getSource()) {
            audioController.reproducirSonidoBoton();
            audioController.reproducirAdios(menuController.getPosicionPersonaje());
            // Usar SwingUtilities para delay sin bloquear el hilo principal
            javax.swing.SwingUtilities.invokeLater(() -> {
                try {
                    java.util.Timer timer = new java.util.Timer();
                    timer.schedule(new java.util.TimerTask() {
                        @Override
                        public void run() {
                            System.exit(0);
                        }
                    }, 500);
                } catch (Exception ex) {
                    System.exit(0);
                }
            });
        } else
        if (this.ventanaMenu.BUTTON_MENU_AJUSTES == e.getSource()) {
            audioController.reproducirSonidoBoton();
            vistaController.mostrarAjustes();
            audioController.detenerTutorial();
            audioController.reproducirAjustes(menuController.getPosicionPersonaje());
        }
        //----------------------ACIONES DE JUEGO--------------------------------
        else
        if (this.ventanaJuego.BUTTON_NEXT == e.getSource()) {
            audioController.reproducirSonidoBoton();
            audioController.detenerTutorial();
            
            // Pasar a siguiente palabra usando JuegoController
            Tipo_Objeto tipoObjeto = juegoController.obtenerTipoObjetoPorPosicion(
                new Tipo_Objeto[]{objetoColores, objetoHabitaciones, objetoPaises}, 
                menuController.getPosicionTipoObjeto());
            juegoController.siguientePalabraDesdeObjeto(tipoObjeto, palabra);
            
            palabraCompletada = false;
            juegoController.prepararSiguientePalabra();
            cambiosImagenDescripcion = !cambiosImagenDescripcion;
            juegoController.getButtonManager().activarTodos();

        } else
        if (this.ventanaJuego.BUTTON_AYUDA == e.getSource()) {
            audioController.reproducirSonidoBoton();
            audioController.detenerTutorial();
            ventanaJuego.BUTTON_AYUDA.setEnabled(false);
            audioController.reproducirAyuda(menuController.getPosicionPersonaje());
            juegoController.mostrarAyuda();

        } else
        if (this.ventanaJuego.BUTTON_AUDIO_TEXT == e.getSource()) {
            audioController.reproducirSonidoBoton();
            audioController.detenerTutorial();
            // Usar constantes en lugar de 0/1
            Clip lector = (menuController.getPosicionPersonaje() == Utilidades.GameConstants.INDICE_DUENDE) 
                ? palabra.getAudio_duende() 
                : palabra.getAudio_hada();
            audioController.reproducirClip(lector);
        } else
        if (this.ventanaJuego.BUTTON_SILENCIAR == e.getSource()) {
            audioController.reproducirSonidoBoton();
            audioController.detenerTutorial();
            posicionMusica = (posicionMusica + 1) % 2;
            animation.setPos(posicionMusica);
            if (posicionMusica == 0) {
                audioController.iniciarMusicaAmbiente();
                ajustesController.actualizarIconoMusica(true);
                audioController.reproducirMusicaON(menuController.getPosicionPersonaje());
            } else {
                audioController.detenerMusicaAmbiente();
                ajustesController.actualizarIconoMusica(false);
                audioController.reproducirMusicaOFF(menuController.getPosicionPersonaje());
            }

        } else
        if (this.ventanaJuego.BUTTON_TUTORIAL == e.getSource()) {
            audioController.reproducirSonidoBoton();
            audioController.reproducirTutorial();
            juegoController.mostrarTutorial();

        } else
        if (this.ventanaJuego.BUTTON_JUEGO_MENU == e.getSource()) {
            audioController.reproducirSonidoBoton();
            audioController.detenerTutorial();
            vistaController.mostrarMenu();
            palabraCompletada = false;
            juegoController.getButtonManager().activarTodos();
            ventanaJuego.ETIQUETA_INCOGNITA.setVisible(true);
            ventanaJuego.ETIQUETA_IMAGEN.setVisible(false);
            ventanaJuego.BUTTON_AYUDA.setEnabled(true);
            ventanaJuego.BUTTON_AUDIO_TEXT.setEnabled(false);
            audioController.detenerAudiosPalabraCorrecta();
            audioController.reproducirMenu(menuController.getPosicionPersonaje());

        } else
            //----------------------ACIONES DE AJUSTE------------------------------- 
            if (this.ventanaAjustes.BUTTON_AJUSTES_ACEPTAR == e.getSource()) {
                audioController.reproducirSonidoBoton();
                vistaController.mostrarMenu();
                audioController.reproducirAceptar(menuController.getPosicionPersonaje());
            } else
        if (this.ventanaAjustes.BUTTON_AJUSTES_CANCELAR == e.getSource()) {
            audioController.reproducirSonidoBoton();
            vistaController.mostrarMenu();
            audioController.reproducirCancelar(menuController.getPosicionPersonaje());
        } else
        if (this.ventanaAjustes.BUTTON_AJUSTES_SIGUIENTE == e.getSource()) {
            audioController.reproducirSonidoBoton();
            int totalFondos = recursos.getFondo().size();
            int posicionFondo = ajustesController.siguienteFondo(totalFondos);
            this.ventanaPrincipal.VENTANA_JUEGO.imagen = new ImageIcon(
                singletonAjustes.obtenerSiguienteFondo()).getImage();
            ImageIcon iconoFondo = new ImageIcon(recursos.getFondo().get(posicionFondo));
            ImageIcon iconoFondoEscalado = new ImageIcon(
                iconoFondo.getImage().getScaledInstance(220, 220, java.awt.Image.SCALE_DEFAULT));
            this.ventanaPrincipal.VENTANA_AJUSTES.ETIQUETA_AJUSTES_FONDOS.setIcon(iconoFondoEscalado);

        } else
        if (this.ventanaAjustes.CAJA_TAMAÑO_PANTALLA == e.getSource()) {
            audioController.reproducirSonidoBoton();
            String opcion = ventanaAjustes.CAJA_TAMAÑO_PANTALLA.getSelectedItem().toString();
            ajustesController.cambiarTamañoVentana(opcion, ventanaPrincipal);
            
            // Calcular escala dinámica basada en el tamaño de pantalla
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double screenHeight = screenSize.getHeight();
            double screenWidth = screenSize.getWidth();
            
            // Determinar tamaño de botones basado en altura de pantalla
            int anchoButton, altoButton;
            if (screenHeight < 770) {
                // Pantalla muy pequeña
                anchoButton = 50;
                altoButton = 60;
            } else if (screenHeight < 900) {
                // Pantalla pequeña
                anchoButton = 70;
                altoButton = 65;
            } else if (screenHeight < 1080) {
                // Pantalla mediana
                anchoButton = 85;
                altoButton = 75;
            } else {
                // Pantalla grande (default)
                anchoButton = 90;
                altoButton = 80;
            }
            
            // Aplicar escala solo si es pantalla pequeña o "Acomodar a mi pantalla"
            if (opcion.equals("Acomodar a mi pantalla") || 
                (opcion.matches("\\d+x\\d+") && screenHeight < 770) ||
                opcion.equals("1366x768")) {
                animation.setTamaño(anchoButton, altoButton);
                
                // Escalar título del menú proporcionalmente
                int anchoTitulo = (int)(470 * (screenWidth / 1920.0));
                int altoTitulo = (int)(130 * (screenHeight / 1080.0));
                if (anchoTitulo < 300) anchoTitulo = 300;
                if (altoTitulo < 80) altoTitulo = 80;
                
                ventanaPrincipal.VENTANA_MENU.ETIQUETA_TITULO_MENU.setIcon(
                    new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/ui/titulo.png"))
                        .getImage().getScaledInstance(anchoTitulo, altoTitulo, java.awt.Image.SCALE_DEFAULT)));
            } else if (!opcion.equals("Por defecto")) {
                // Para otros tamaños, usar escala proporcional
                double escala = Math.min(screenWidth / 1920.0, screenHeight / 1080.0);
                anchoButton = (int)(90 * escala);
                altoButton = (int)(80 * escala);
                if (anchoButton < 50) anchoButton = 50;
                if (altoButton < 60) altoButton = 60;
                animation.setTamaño(anchoButton, altoButton);
            }

        } else
        if (this.ventanaAjustes.BUTTON_BOTON_SONIDO == e.getSource()) {
            audioController.reproducirSonidoBoton();
            sonidoBotonesHabilitado = !sonidoBotonesHabilitado;
            audioController.setSonidoBotonesHabilitado(sonidoBotonesHabilitado);
            animation.setSonidoBotones(sonidoBotonesHabilitado);
            if (sonidoBotonesHabilitado) {
                audioController.reproducirSilenciarBotonesON(menuController.getPosicionPersonaje());
                ventanaAjustes.BUTTON_BOTON_SONIDO.setIcon(
                    new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/ajustes/boton_verde_radio.png"))
                        .getImage().getScaledInstance(80, 50, java.awt.Image.SCALE_DEFAULT)));
            } else {
                audioController.reproducirSilenciarBotonesOFF(menuController.getPosicionPersonaje());
                ventanaAjustes.BUTTON_BOTON_SONIDO.setIcon(
                    new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/ajustes/boton_rojo_radio.png"))
                        .getImage().getScaledInstance(80, 50, java.awt.Image.SCALE_DEFAULT)));
            }

        } else
        if (this.ventanaAjustes.BUTTON_MUSICA == e.getSource()) {
            audioController.reproducirSonidoBoton();
            posicionMusica = (posicionMusica + 1) % 2;
            animation.setPos(posicionMusica);
            if (posicionMusica == 0) {
                audioController.iniciarMusicaAmbiente();
                ajustesController.actualizarIconoMusica(true);
                audioController.reproducirMusicaON(menuController.getPosicionPersonaje());
            } else {
                audioController.detenerMusicaAmbiente();
                ajustesController.actualizarIconoMusica(false);
                audioController.reproducirMusicaOFF(menuController.getPosicionPersonaje());
            }

        } else {
            //------------------TECLADO------------------TECLADO------------------TECLADO------------------TECLADO------------------TECLADO
            // Procesar letras usando JuegoController (SOLID - delegación de responsabilidades)
            char letraSeleccionada = juegoController.obtenerLetraDelBoton(e.getSource());
            if (letraSeleccionada != 0) {
                // Procesar letra usando JuegoController
                juegoController.procesarLetra(letraSeleccionada);
                
                // Verificar si la palabra está completa
                if (juegoController.isPalabraCompletada()) {
                    juegoController.getButtonManager().desactivarTodos();
                    ventanaJuego.BUTTON_AYUDA.setEnabled(false);
                    juegoController.incrementarPosicionPalabra();
                    if (!palabraCompletada) {
                        palabraCompletada = true;
                        juegoController.mostrarPalabraCompleta();
                        audioController.reproducirPalabraCorrecta(menuController.getPosicionPersonaje());
                        
                        if (juegoController.getPosicionPalabra() == palabra.totalPalabras) {
                            juegoController.finalizarCategoria(() -> {
                                vistaController.mostrarMenu();
                            });
                        }
                    }
                }
            }
        }

    }
    
    /**
     * Inicia la pantalla de carga con barra de progreso
     */
    private void iniciarPantallaCarga() {
        // Usar Timer en lugar de Thread.sleep para no bloquear el hilo principal
        java.util.Timer timer = new java.util.Timer();
        final int[] progreso = {0};
        
        timer.scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {
                javax.swing.SwingUtilities.invokeLater(() -> {
                    progreso[0]++;
                    if (ventanaInicio != null) {
                        ventanaInicio.ITEM_BARRA.setValue(progreso[0]);
                    }
                    
                    if (progreso[0] >= 100) {
                        timer.cancel();
                        vistaController.mostrarMenu();
                        audioController.iniciarMusicaAmbiente();
                        audioController.reproducirBienvenida(menuController.getPosicionPersonaje());
                    }
                });
            }
        }, 0, 33); // 33ms entre actualizaciones
    }

 

 


}