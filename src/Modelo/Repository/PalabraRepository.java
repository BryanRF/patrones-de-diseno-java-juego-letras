package Modelo.Repository;

import Modelo.Database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Repositorio para acceder a las palabras desde la base de datos
 * Patrón Repository - Separa la lógica de acceso a datos
 */
public class PalabraRepository {
    private static final Logger logger = Logger.getLogger(PalabraRepository.class.getName());
    private final DatabaseManager dbManager;

    public PalabraRepository() {
        this.dbManager = DatabaseManager.getInstance();
    }

    /**
     * Obtiene todas las palabras de una categoría específica
     */
    public List<PalabraData> getPalabrasByCategoria(String nombreCategoria) throws SQLException {
        List<PalabraData> palabras = new ArrayList<>();
        String sql = "SELECT p.palabra, p.descripcion, p.ruta_imagen, " +
                     "p.ruta_audio_duende, p.ruta_audio_hada " +
                     "FROM palabras p " +
                     "INNER JOIN categorias c ON p.categoria_id = c.id " +
                     "WHERE c.nombre = ? " +
                     "ORDER BY p.id";

        Connection conn = null;
        try {
            conn = dbManager.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setString(1, nombreCategoria);
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        PalabraData palabra = new PalabraData(
                            rs.getString("palabra"),
                            rs.getString("descripcion"),
                            rs.getString("ruta_imagen"),
                            rs.getString("ruta_audio_duende"),
                            rs.getString("ruta_audio_hada")
                        );
                        palabras.add(palabra);
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener palabras de la categoría: " + nombreCategoria, e);
            throw e;
        } finally {
            if (conn != null) {
                dbManager.returnConnection(conn);
            }
        }

        return palabras;
    }

    /**
     * Clase interna para representar los datos de una palabra
     */
    public static class PalabraData {
        private final String palabra;
        private final String descripcion;
        private final String rutaImagen;
        private final String rutaAudioDuende;
        private final String rutaAudioHada;

        public PalabraData(String palabra, String descripcion, String rutaImagen,
                          String rutaAudioDuende, String rutaAudioHada) {
            this.palabra = palabra;
            this.descripcion = descripcion;
            this.rutaImagen = rutaImagen;
            this.rutaAudioDuende = rutaAudioDuende;
            this.rutaAudioHada = rutaAudioHada;
        }

        public String getPalabra() {
            return palabra;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public String getRutaImagen() {
            return rutaImagen;
        }

        public String getRutaAudioDuende() {
            return rutaAudioDuende;
        }

        public String getRutaAudioHada() {
            return rutaAudioHada;
        }
    }
}

