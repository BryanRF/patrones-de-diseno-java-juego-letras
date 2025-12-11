package Modelo.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gestor de base de datos SQLite usando patrón Singleton
 * Maneja la conexión y creación del esquema de base de datos
 * Implementa un pool simple de conexiones para mejorar rendimiento
 */
public class DatabaseManager {
    private static DatabaseManager instance;
    private static final String DB_URL = "jdbc:sqlite:abcdgame.db";
    private static final int MAX_POOL_SIZE = 5;
    private final BlockingQueue<Connection> connectionPool;
    private int activeConnections = 0;
    private static final Logger logger = Logger.getLogger(DatabaseManager.class.getName());

    private DatabaseManager() {
        this.connectionPool = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
        initializeDatabase();
    }

    /**
     * Obtiene la instancia única del DatabaseManager (Singleton)
     */
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    /**
     * Verifica si el driver SQLite está disponible
     */
    private boolean isDriverAvailable() {
        try {
            Class.forName("org.sqlite.JDBC");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    
    /**
     * Obtiene una conexión del pool o crea una nueva si es necesario
     */
    public Connection getConnection() throws SQLException {
        // Verificar driver antes de intentar conectar
        if (!isDriverAvailable()) {
            String errorMsg = "Driver SQLite no encontrado.\n" +
                "Por favor:\n" +
                "1. Ejecuta: download_sqlite.bat (o download_sqlite.sh en Linux/Mac)\n" +
                "2. O descarga manualmente desde: https://github.com/xerial/sqlite-jdbc/releases\n" +
                "3. Coloca sqlite-jdbc-3.44.1.0.jar en dist/lib/\n" +
                "4. Agrégalo a las librerías del proyecto en NetBeans (Properties → Libraries)";
            logger.log(Level.SEVERE, errorMsg);
            throw new SQLException(errorMsg);
        }
        
        try {
            // Intentar obtener conexión del pool
            Connection conn = connectionPool.poll();
            
            if (conn != null && !conn.isClosed()) {
                return conn;
            }
            
            // Crear nueva conexión si el pool está vacío y no excedemos el límite
            if (activeConnections < MAX_POOL_SIZE) {
                conn = DriverManager.getConnection(DB_URL);
                activeConnections++;
                logger.fine("Nueva conexión creada. Total activas: " + activeConnections);
                return conn;
            }
            
            // Si el pool está lleno, crear conexión temporal
            logger.warning("Pool de conexiones lleno, creando conexión temporal");
            return DriverManager.getConnection(DB_URL);
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al crear conexión a la base de datos", e);
            throw e;
        }
    }
    
    /**
     * Devuelve una conexión al pool para reutilización
     * @param connection Conexión a devolver al pool
     */
    public void returnConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        
        try {
            if (!connection.isClosed()) {
                // Resetear la conexión antes de devolverla al pool
                connection.setAutoCommit(true);
                if (connectionPool.offer(connection)) {
                    logger.fine("Conexión devuelta al pool");
                } else {
                    // Pool lleno, cerrar la conexión
                    connection.close();
                    activeConnections--;
                    logger.fine("Pool lleno, conexión cerrada. Total activas: " + activeConnections);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error al devolver conexión al pool", e);
            try {
                connection.close();
                activeConnections--;
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "Error al cerrar conexión", ex);
            }
        }
    }
    
    /**
     * Cierra todas las conexiones del pool
     */
    public void closeAllConnections() {
        Connection conn;
        while ((conn = connectionPool.poll()) != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                    activeConnections--;
                }
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error al cerrar conexión del pool", e);
            }
        }
        logger.info("Todas las conexiones cerradas. Pool vacío");
    }

    /**
     * Inicializa la base de datos creando las tablas si no existen
     */
    private void initializeDatabase() {
        // Verificar driver antes de intentar inicializar
        if (!isDriverAvailable()) {
            logger.severe("No se puede inicializar la base de datos: Driver SQLite no encontrado");
            return;
        }
        
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(true); // Usar autocommit para DDL
            
            try (Statement stmt = conn.createStatement()) {
                // Crear tabla de categorías
                String createCategoriasTable = 
                    "CREATE TABLE IF NOT EXISTS categorias (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT NOT NULL UNIQUE" +
                    ")";

                // Crear tabla de palabras
                String createPalabrasTable = 
                    "CREATE TABLE IF NOT EXISTS palabras (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "categoria_id INTEGER NOT NULL, " +
                    "palabra TEXT NOT NULL, " +
                    "descripcion TEXT NOT NULL, " +
                    "ruta_imagen TEXT NOT NULL, " +
                    "ruta_audio_duende TEXT NOT NULL, " +
                    "ruta_audio_hada TEXT NOT NULL, " +
                    "FOREIGN KEY (categoria_id) REFERENCES categorias(id) ON DELETE CASCADE" +
                    ")";

                // Crear tabla de configuración
                String createConfiguracionTable = 
                    "CREATE TABLE IF NOT EXISTS configuracion (" +
                    "clave TEXT PRIMARY KEY, " +
                    "valor TEXT NOT NULL" +
                    ")";

                // Crear tabla de personajes
                String createPersonajesTable = 
                    "CREATE TABLE IF NOT EXISTS personajes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT NOT NULL UNIQUE, " +
                    "ruta_imagen TEXT NOT NULL, " +
                    "ruta_audio_bienvenida TEXT NOT NULL, " +
                    "indice INTEGER NOT NULL UNIQUE" +
                    ")";

                // Crear índices para mejorar el rendimiento
                String createIndex = 
                    "CREATE INDEX IF NOT EXISTS idx_palabras_categoria ON palabras(categoria_id)";
                String createPersonajesIndex = 
                    "CREATE INDEX IF NOT EXISTS idx_personajes_indice ON personajes(indice)";

                stmt.execute(createCategoriasTable);
                stmt.execute(createPalabrasTable);
                stmt.execute(createConfiguracionTable);
                stmt.execute(createPersonajesTable);
                stmt.execute(createIndex);
                stmt.execute(createPersonajesIndex);
                
                // Inicializar configuración por defecto si no existe
                stmt.execute("INSERT OR IGNORE INTO configuracion (clave, valor) VALUES ('modo_ventana', 'true')");
                
                logger.info("Base de datos inicializada correctamente");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al inicializar la base de datos", e);
        } finally {
            if (conn != null) {
                returnConnection(conn);
            }
        }
    }

    /**
     * Verifica si la base de datos está vacía
     */
    public boolean isDatabaseEmpty() throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            try (Statement stmt = conn.createStatement();
                 java.sql.ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM categorias")) {
                
                if (rs.next()) {
                    return rs.getInt("count") == 0;
                }
                return true;
            }
        } finally {
            if (conn != null) {
                returnConnection(conn);
            }
        }
    }
}

