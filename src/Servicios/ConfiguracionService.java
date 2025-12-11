package Servicios;

import Modelo.Database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servicio para gestión de configuración usando SQLite
 * Responsabilidad: Gestionar la configuración de la aplicación
 * Movido de Controlador a Servicios siguiendo principios SOLID
 */
public class ConfiguracionService {
    private static final Logger logger = Logger.getLogger(ConfiguracionService.class.getName());
    private final DatabaseManager dbManager;

    public ConfiguracionService() {
        this.dbManager = DatabaseManager.getInstance();
    }
    
    /**
     * Obtiene el valor de configuración para modo_ventana
     * @return true si la ventana debe ser sin decoración, false en caso contrario
     */
    public boolean obtenerModoVentana() {
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "SELECT valor FROM configuracion WHERE clave = ?")) {
            
            pstmt.setString(1, "modo_ventana");
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Boolean.parseBoolean(rs.getString("valor"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al leer configuración. " + 
                "Asegúrate de que sqlite-jdbc-3.44.1.0.jar esté en dist/lib/ y agregado a las librerías del proyecto.", e);
            // Si el error es por falta del driver, mostrar mensaje más claro
            if (e.getMessage() != null && e.getMessage().contains("Driver SQLite no encontrado")) {
                System.err.println("\n==========================================");
                System.err.println("ERROR: Driver SQLite no encontrado");
                System.err.println("==========================================");
                System.err.println("Por favor ejecuta:");
                System.err.println("  download_sqlite.bat");
                System.err.println("O descarga manualmente desde:");
                System.err.println("  https://github.com/xerial/sqlite-jdbc/releases");
                System.err.println("Y coloca sqlite-jdbc-3.44.1.0.jar en dist/lib/");
                System.err.println("==========================================\n");
            }
        }
        // Valor por defecto
        return true;
    }
    
    /**
     * Cambia el valor de modo_ventana
     */
    public void cambiarModoVentana() {
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "UPDATE configuracion SET valor = ? WHERE clave = ?")) {
            
            boolean currentValue = obtenerModoVentana();
            pstmt.setString(1, String.valueOf(!currentValue));
            pstmt.setString(2, "modo_ventana");
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al cambiar configuración", e);
        }
    }
    
    /**
     * Método de compatibilidad con código antiguo
     * @deprecated Usar obtenerModoVentana() en su lugar
     */
    @Deprecated
    public boolean configuracion() {
        return obtenerModoVentana();
    }
    
    /**
     * Método de compatibilidad con código antiguo
     * @deprecated Usar cambiarModoVentana() en su lugar
     */
    @Deprecated
    public void cambiarConfiguracion() {
        cambiarModoVentana();
    }
}
