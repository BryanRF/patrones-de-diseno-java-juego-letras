package Utilidades;

/**
 * Constantes del juego centralizadas
 * Facilita el mantenimiento y evita números mágicos
 */
public final class GameConstants {
    
    // Constructor privado para evitar instanciación
    private GameConstants() {
        throw new AssertionError("No se debe instanciar GameConstants");
    }
    
    // Configuración de ventana
    public static final int ANCHO_BUTTON_DEFAULT = 90;
    public static final int ALTO_BUTTON_DEFAULT = 80;
    public static final int ANCHO_BUTTON_PEQUENO = 50;
    public static final int ALTO_BUTTON_PEQUENO = 60;
    
    // Tamaños de imágenes
    public static final int TAMANO_IMAGEN_PALABRA = 200;
    public static final int TAMANO_CARICATURA = 220;
    public static final int TAMANO_TITULO_ANCHO = 470;
    public static final int TAMANO_TITULO_ALTO = 130;
    public static final int TAMANO_MARCO_AYUDA = 220;
    public static final int TAMANO_TUTORIAL = 450;
    public static final int TAMANO_BOTON_ICONO = 100;
    public static final int TAMANO_BOTON_AUDIO_ANCHO = 130;
    public static final int TAMANO_BOTON_TUTORIAL_ANCHO = 320;
    
    // Resoluciones de pantalla
    public static final int RESOLUCION_1920x1080_ANCHO = 1920;
    public static final int RESOLUCION_1920x1080_ALTO = 1080;
    public static final int RESOLUCION_1400x870_ANCHO = 1400;
    public static final int RESOLUCION_1400x870_ALTO = 870;
    public static final int RESOLUCION_1366x768_ANCHO = 1366;
    public static final int RESOLUCION_1366x768_ALTO = 768;
    public static final double ALTURA_MINIMA_PANTALLA = 770.0;
    
    // Categorías
    public static final int TOTAL_CATEGORIAS = 3;
    public static final int TOTAL_PERSONAJES = 2;
    public static final int INDICE_COLORES = 0;
    public static final int INDICE_HABITACIONES = 1;
    public static final int INDICE_PAISES = 2;
    public static final int INDICE_DUENDE = 0;
    public static final int INDICE_HADA = 1;
    
    // Nombres de categorías
    public static final String CATEGORIA_COLORES = "Colores";
    public static final String CATEGORIA_HABITACIONES = "Habitaciones";
    public static final String CATEGORIA_PAISES = "Paises";
    
    // Rutas de recursos - Base path
    public static final String BASE_RECURSOS = "/recursos";
    public static final String BASE_IMAGENES = BASE_RECURSOS + "/imagenes";
    public static final String BASE_AUDIOS = BASE_RECURSOS + "/audios";
    public static final String BASE_FUENTES = BASE_RECURSOS + "/fuentes";
    public static final String BASE_VIDEOS = BASE_RECURSOS + "/videos";
    
    // Rutas de UI
    public static final String RUTA_TITULO = BASE_IMAGENES + "/ui/titulo.png";
    public static final String RUTA_MARCO = BASE_IMAGENES + "/ui/marco.png";
    public static final String RUTA_BUHO = BASE_IMAGENES + "/ui/buho.png";
    public static final String RUTA_PIZARRA = BASE_IMAGENES + "/ui/pizarra.png";
    public static final String RUTA_MUTE = BASE_IMAGENES + "/ui/mute.png";
    public static final String RUTA_MUTEADO = BASE_IMAGENES + "/ui/muteado.png";
    
    // Rutas de personajes
    public static final String RUTA_DUENDE = BASE_IMAGENES + "/personajes/duende/duende.png";
    public static final String RUTA_HADA = BASE_IMAGENES + "/personajes/hada/hada.png";
    
    // Rutas de botones
    public static final String RUTA_AYUDA = BASE_IMAGENES + "/botones/juego/ayuda.png";
    public static final String RUTA_BOTON = BASE_IMAGENES + "/botones/juego/boton.png";
    public static final String RUTA_DERECHA_MENU = BASE_IMAGENES + "/botones/menu/derecha_menu.png";
    public static final String RUTA_BOTON_VERDE_RADIO = BASE_IMAGENES + "/botones/ajustes/boton_verde_radio.png";
    public static final String RUTA_BOTON_ROJO_RADIO = BASE_IMAGENES + "/botones/ajustes/boton_rojo_radio.png";
    
    // Rutas de objetos del menú
    public static final String RUTA_OBJETO_COLORES = BASE_IMAGENES + "/ui/objeto_colores.png";
    public static final String RUTA_CASA_OBJ = BASE_IMAGENES + "/objetos/habitaciones/casa_obj.png";
    public static final String RUTA_PAISES = BASE_IMAGENES + "/objetos/paises/paises.png";
    public static final String RUTA_CASA = BASE_IMAGENES + "/objetos/habitaciones/casa.png";
    
    // Rutas de fondos
    public static final String RUTA_FONDO_BASE = BASE_IMAGENES + "/fondos/fondo_pantalla.png";
    public static final String FORMATO_FONDO = BASE_IMAGENES + "/fondos/fondo_pantalla_%d.png";
    public static final String RUTA_FONDO_CELESTE = BASE_IMAGENES + "/fondos/fondo_celeste.png";
    public static final int TOTAL_FONDOS = 12;
    
    // Rutas de videos/GIFs
    public static final String RUTA_TUTORIAL = BASE_IMAGENES + "/videos/tutorial.gif";
    
    // Rutas de audio - Música
    public static final String RUTA_AUDIO_AMBIENTE = BASE_AUDIOS + "/musica/M_J_bucle_ambiente.wav";
    public static final String RUTA_AUDIO_INICIO = BASE_AUDIOS + "/musica/M_J_inicio_bucle.wav";
    
    // Rutas de audio - Personajes
    public static final String RUTA_AUDIO_BIENVENIDA_DUENDE = BASE_AUDIOS + "/personajes/duende/duende_bienvenido.wav";
    public static final String RUTA_AUDIO_BIENVENIDA_HADA = BASE_AUDIOS + "/personajes/hada/hada_bienvenido.wav";
    public static final String RUTA_AUDIO_TUTORIAL = BASE_AUDIOS + "/efectos/tutorial_genio.wav";
    
    // Rutas de audio - Efectos
    public static final String RUTA_AUDIO_BLUP = BASE_AUDIOS + "/efectos/blup.wav";
    public static final String RUTA_AUDIO_BLOP = BASE_AUDIOS + "/efectos/blop.wav";
    
    // Fuentes
    public static final String FUENTE_SNAP_ITC = "Snap ITC";
    public static final int TAMANO_FUENTE_TITULO = 48;
    public static final int TAMANO_FUENTE_PALABRA = 40;
    public static final int TAMANO_FUENTE_DESCRIPCION = 25;
    public static final int TAMANO_FUENTE_BOTON = 30;
    public static final int TAMANO_FUENTE_BOTON_TUTORIAL = 35;
    public static final int TAMANO_FUENTE_BOTON_AUDIO = 26;
    public static final int TAMANO_FUENTE_AYUDA = 120;
    
    // Colores
    public static final java.awt.Color COLOR_NARANJA = new java.awt.Color(253, 192, 6);
    public static final java.awt.Color COLOR_VERDE = new java.awt.Color(112, 215, 126);
    public static final java.awt.Color COLOR_AZUL = new java.awt.Color(67, 161, 168);
    public static final java.awt.Color COLOR_BLANCO = java.awt.Color.WHITE;
    
    // Delays y tiempos
    public static final int DELAY_VENTANA_AYUDA_MS = 2000;
    public static final int DELAY_VENTANA_TUTORIAL_MS = 27200;
    public static final int DELAY_VENTANA_FIN_MS = 2000;
    public static final int DELAY_SALIR_MS = 500;
    public static final int DELAY_BARRA_PROGRESO_MS = 33;
    public static final int PROGRESO_MAXIMO = 100;
    
    // Opacidad
    public static final float OPACIDAD_VENTANA_TUTORIAL = 0.9f;
    
    // Idioma
    public static final String IDIOMA_DEFAULT = "ES-espanish";
    
    // Posiciones de CardLayout
    public static final String POS_JUEGO = "JUEGO";
    public static final String POS_MENU = "MENU";
    public static final String POS_AJUSTES = "AJUSTES";
    public static final String POS_INICIO = "INICIO";
}

