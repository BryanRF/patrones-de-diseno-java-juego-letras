package Modelo.Repository;

import Modelo.Database.DatabaseManager;
import Modelo.Entities.Personaje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Repositorio para acceder a los personajes desde la base de datos
 * Patrón Repository - Separa la lógica de acceso a datos
 */
public class PersonajeRepository {
    private static final Logger logger = Logger.getLogger(PersonajeRepository.class.getName());
    private final DatabaseManager dbManager;

    public PersonajeRepository() {
        this.dbManager = DatabaseManager.getInstance();
    }

    /**
     * Obtiene todos los personajes ordenados por índice
     * @return Lista de personajes
     */
    public List<Personaje> getAllPersonajes() throws SQLException {
        List<Personaje> personajes = new ArrayList<>();
        String sql = "SELECT id, nombre, ruta_imagen, ruta_audio_bienvenida, indice " +
                     "FROM personajes " +
                     "ORDER BY indice ASC";

        Connection conn = null;
        try {
            conn = dbManager.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                
                while (rs.next()) {
                    Personaje personaje = new Personaje(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ruta_imagen"),
                        rs.getString("ruta_audio_bienvenida"),
                        rs.getInt("indice")
                    );
                    personajes.add(personaje);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener personajes", e);
            throw e;
        } finally {
            if (conn != null) {
                dbManager.returnConnection(conn);
            }
        }

        return personajes;
    }

    /**
     * Obtiene un personaje por su índice
     * @param indice Índice del personaje
     * @return Personaje o null si no existe
     */
    public Personaje getPersonajeByIndice(int indice) throws SQLException {
        String sql = "SELECT id, nombre, ruta_imagen, ruta_audio_bienvenida, indice " +
                     "FROM personajes " +
                     "WHERE indice = ?";

        Connection conn = null;
        try {
            conn = dbManager.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, indice);
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return new Personaje(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("ruta_imagen"),
                            rs.getString("ruta_audio_bienvenida"),
                            rs.getInt("indice")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener personaje por índice: " + indice, e);
            throw e;
        } finally {
            if (conn != null) {
                dbManager.returnConnection(conn);
            }
        }

        return null;
    }

    /**
     * Obtiene un personaje por su ID
     * @param id ID del personaje
     * @return Personaje o null si no existe
     */
    public Personaje getPersonajeById(int id) throws SQLException {
        String sql = "SELECT id, nombre, ruta_imagen, ruta_audio_bienvenida, indice " +
                     "FROM personajes " +
                     "WHERE id = ?";

        Connection conn = null;
        try {
            conn = dbManager.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return new Personaje(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("ruta_imagen"),
                            rs.getString("ruta_audio_bienvenida"),
                            rs.getInt("indice")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener personaje por ID: " + id, e);
            throw e;
        } finally {
            if (conn != null) {
                dbManager.returnConnection(conn);
            }
        }

        return null;
    }

    /**
     * Verifica si existe un personaje con el índice especificado
     * @param indice Índice a verificar
     * @return true si existe, false en caso contrario
     */
    public boolean existePersonajePorIndice(int indice) throws SQLException {
        String sql = "SELECT COUNT(*) as count FROM personajes WHERE indice = ?";

        Connection conn = null;
        try {
            conn = dbManager.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, indice);
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("count") > 0;
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al verificar existencia de personaje: " + indice, e);
            throw e;
        } finally {
            if (conn != null) {
                dbManager.returnConnection(conn);
            }
        }

        return false;
    }
}
