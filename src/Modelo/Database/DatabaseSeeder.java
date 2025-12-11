package Modelo.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Seeder para poblar la base de datos con datos iniciales
 * Implementa los datos que estaban en los archivos de texto
 */
public class DatabaseSeeder {
    private static final Logger logger = Logger.getLogger(DatabaseSeeder.class.getName());
    private final DatabaseManager dbManager;

    public DatabaseSeeder() {
        this.dbManager = DatabaseManager.getInstance();
    }

    /**
     * Ejecuta el seeder para poblar la base de datos
     */
    public void seed() {
        try {
            if (!dbManager.isDatabaseEmpty()) {
                logger.info("La base de datos ya contiene datos. Seeder omitido.");
                return;
            }

            Connection conn = dbManager.getConnection();
            conn.setAutoCommit(false); // Desactivar autocommit para transacciones

            // Insertar categorías
            int categoriaColoresId = insertCategoria(conn, "Colores");
            int categoriaHabitacionesId = insertCategoria(conn, "Habitaciones");
            int categoriaPaisesId = insertCategoria(conn, "Paises");

            // Insertar palabras de Colores
            insertPalabra(conn, categoriaColoresId, "CELESTE",
                "<html><center><p style=\"width:535px\">¿Que color sale si mezclamos azul con blanco?</p></html>",
                "/recursos/imagenes/objetos/colores/celestes.png",
                "/recursos/audios/palabras/colores/celeste_d.wav",
                "/recursos/audios/palabras/colores/celeste_h.wav");

            insertPalabra(conn, categoriaColoresId, "ROJO",
                "<html><center><p style=\"width:535px\">¿Cual es el color de las fresas?</p></html>",
                "/recursos/imagenes/objetos/colores/rojo.png",
                "/recursos/audios/palabras/colores/rojo_d.wav",
                "/recursos/audios/palabras/colores/rojo_h.wav");

            insertPalabra(conn, categoriaColoresId, "VERDE",
                "<html><center><p style=\"width:535px\">¿Cual es el color mas comun de las plantas?</p></html>",
                "/recursos/imagenes/objetos/colores/verde.png",
                "/recursos/audios/palabras/colores/verde_d.wav",
                "/recursos/audios/palabras/colores/verde_h.wav");

            insertPalabra(conn, categoriaColoresId, "AMARILLO",
                "<html><center><p style=\"width:535px\">¿Color del sol?</p></html>",
                "/recursos/imagenes/objetos/colores/amarillo.png",
                "/recursos/audios/palabras/colores/amarillo_d.wav",
                "/recursos/audios/palabras/colores/amarillo_h.wav");

            insertPalabra(conn, categoriaColoresId, "ANARANJADO",
                "<html><center><p style=\"width:535px\">¿Resultado de la combinacion de Amarillo con rojo?</p></html>",
                "/recursos/imagenes/objetos/colores/anaranjado.png",
                "/recursos/audios/palabras/colores/anaranjado_d.wav",
                "/recursos/audios/palabras/colores/anaranjado_h.wav");

            // Insertar palabras de Habitaciones
            insertPalabra(conn, categoriaHabitacionesId, "DORMITORIO",
                "<html><center><p style=\"width:535px\">Lugar en donde puedes dormir</p></html>",
                "/recursos/imagenes/objetos/habitaciones/habitacion.png",
                "/recursos/audios/palabras/habitaciones/dormitorio_d.wav",
                "/recursos/audios/palabras/habitaciones/dormitorio_h.wav");

            insertPalabra(conn, categoriaHabitacionesId, "COCINA",
                "<html><center><p style=\"width:535px\">Lugar donde se preparan los alimentos </p></html>",
                "/recursos/imagenes/objetos/habitaciones/cocina.png",
                "/recursos/audios/palabras/habitaciones/cocina_d.wav",
                "/recursos/audios/palabras/habitaciones/cocina_h.wav");

            insertPalabra(conn, categoriaHabitacionesId, "GARAJE",
                "<html><center><p style=\"width:535px\">Lugar donde se guardan los autos</p></html>",
                "/recursos/imagenes/objetos/habitaciones/garaje.png",
                "/recursos/audios/palabras/habitaciones/garaje_d.wav",
                "/recursos/audios/palabras/habitaciones/garaje_h.wav");

            insertPalabra(conn, categoriaHabitacionesId, "BAÑO",
                "<html><center><p style=\"width:535px\">Lugar para miar xd</p></html>",
                "/recursos/imagenes/objetos/habitaciones/banio.png",
                "/recursos/audios/palabras/habitaciones/banio_d.wav",
                "/recursos/audios/palabras/habitaciones/banio_h.wav");

            insertPalabra(conn, categoriaHabitacionesId, "SALA",
                "<html><center><p style=\"width:535px\"> Habitación de una vivienda destinada a hacer vida familiar o social</p></html>",
                "/recursos/imagenes/objetos/habitaciones/sala.png",
                "/recursos/audios/palabras/habitaciones/sala_d.wav",
                "/recursos/audios/palabras/habitaciones/sala_h.wav");

            // Insertar palabras de Paises
            insertPalabra(conn, categoriaPaisesId, "PERU",
                "<html><center><p style=\"width:535px\">En que pais esta ubicado MachuPicchu</p></html>",
                "/recursos/imagenes/objetos/paises/peru.png",
                "/recursos/audios/palabras/paises/peru_d.wav",
                "/recursos/audios/palabras/paises/peru_h.wav");

            insertPalabra(conn, categoriaPaisesId, "BOLIVIA",
                "<html><center><p style=\"width:535px\">Pais sudamericado sin mar</p></html>",
                "/recursos/imagenes/objetos/paises/bolivia.png",
                "/recursos/audios/palabras/paises/bolivia_d.wav",
                "/recursos/audios/palabras/paises/bolivia_h.wav");

            insertPalabra(conn, categoriaPaisesId, "CHILE",
                "<html><center><p style=\"width:535px\">El pais mas largo de sudamerica</p></html>",
                "/recursos/imagenes/objetos/paises/chile.png",
                "/recursos/audios/palabras/paises/chile_d.wav",
                "/recursos/audios/palabras/paises/chile_h.wav");

            insertPalabra(conn, categoriaPaisesId, "ARGENTINA",
                "<html><center><p style=\"width:535px\">Pais donde nacio messi</p></html>",
                "/recursos/imagenes/objetos/paises/argentina.png",
                "/recursos/audios/palabras/paises/argentina_d.wav",
                "/recursos/audios/palabras/paises/argentina_h.wav");

            insertPalabra(conn, categoriaPaisesId, "EGIPTO",
                "<html><center><p style=\"width:535px\">Pais con piramides y esfinges</p></html>",
                "/recursos/imagenes/objetos/paises/egipto.png",
                "/recursos/audios/palabras/paises/egipto_d.wav",
                "/recursos/audios/palabras/paises/egipto_h.wav");

            // Insertar personajes
            insertPersonaje(conn, "Duende", 
                "/recursos/imagenes/personajes/duende/duende.png",
                "/recursos/audios/personajes/duende/duende_bienvenido.wav",
                0); // INDICE_DUENDE

            insertPersonaje(conn, "Hada",
                "/recursos/imagenes/personajes/hada/hada.png",
                "/recursos/audios/personajes/hada/hada_bienvenido.wav",
                1); // INDICE_HADA

            conn.commit();
            logger.info("Base de datos poblada correctamente con datos iniciales");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al poblar la base de datos", e);
            try {
                Connection conn = dbManager.getConnection();
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException rollbackEx) {
                logger.log(Level.SEVERE, "Error al hacer rollback", rollbackEx);
            }
        }
    }

    /**
     * Inserta una categoría en la base de datos
     */
    private int insertCategoria(Connection conn, String nombre) throws SQLException {
        String sql = "INSERT INTO categorias (nombre) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
            
            try (java.sql.ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Error al obtener el ID de la categoría insertada");
                }
            }
        }
    }

    /**
     * Inserta una palabra en la base de datos
     */
    private void insertPalabra(Connection conn, int categoriaId, String palabra, 
                               String descripcion, String rutaImagen, 
                               String rutaAudioDuende, String rutaAudioHada) throws SQLException {
        String sql = "INSERT INTO palabras (categoria_id, palabra, descripcion, ruta_imagen, ruta_audio_duende, ruta_audio_hada) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, categoriaId);
            pstmt.setString(2, palabra);
            pstmt.setString(3, descripcion);
            pstmt.setString(4, rutaImagen);
            pstmt.setString(5, rutaAudioDuende);
            pstmt.setString(6, rutaAudioHada);
            pstmt.executeUpdate();
        }
    }

    /**
     * Inserta un personaje en la base de datos
     */
    private void insertPersonaje(Connection conn, String nombre, String rutaImagen, 
                                String rutaAudioBienvenida, int indice) throws SQLException {
        String sql = "INSERT OR IGNORE INTO personajes (nombre, ruta_imagen, ruta_audio_bienvenida, indice) " +
                     "VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, rutaImagen);
            pstmt.setString(3, rutaAudioBienvenida);
            pstmt.setInt(4, indice);
            pstmt.executeUpdate();
        }
    }
}

