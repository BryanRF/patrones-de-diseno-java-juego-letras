package Utilidades;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Configurador del sistema de logging
 * Configura la codificación UTF-8 y formato de fecha/hora para evitar caracteres extraños
 */
public class LoggingConfig {
    
    /**
     * Configura el sistema de logging con UTF-8 y formato de fecha/hora correcto
     */
    public static void configurar() {
        try {
            // Obtener el logger raíz
            Logger rootLogger = Logger.getLogger("");
            
            // Remover handlers existentes
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                rootLogger.removeHandler(handler);
            }
            
            // Crear nuevo handler con formato personalizado
            ConsoleHandler handler = new ConsoleHandler();
            handler.setLevel(Level.ALL);
            handler.setFormatter(new SimpleFormatter());
            
            rootLogger.addHandler(handler);
            rootLogger.setLevel(Level.INFO);
            
        } catch (Exception e) {
            // Si falla la configuración, usar logging por defecto
            System.err.println("Error al configurar logging: " + e.getMessage());
        }
    }
    
    /**
     * Formatter personalizado que evita caracteres especiales en la fecha/hora
     */
    private static class SimpleFormatter extends Formatter {
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        @Override
        public String format(LogRecord record) {
            StringBuilder sb = new StringBuilder();
            
            // Fecha y hora en formato simple
            sb.append(dateFormat.format(new Date(record.getMillis())));
            sb.append(" ");
            
            // Nivel del log
            sb.append(record.getLevel().getName());
            sb.append(" ");
            
            // Nombre de la clase
            String className = record.getSourceClassName();
            if (className != null) {
                int lastDot = className.lastIndexOf('.');
                if (lastDot >= 0) {
                    className = className.substring(lastDot + 1);
                }
                sb.append(className);
            }
            sb.append(" ");
            
            // Mensaje
            sb.append(formatMessage(record));
            sb.append(System.lineSeparator());
            
            // Excepción si existe
            if (record.getThrown() != null) {
                java.io.StringWriter sw = new java.io.StringWriter();
                java.io.PrintWriter pw = new java.io.PrintWriter(sw);
                record.getThrown().printStackTrace(pw);
                sb.append(sw.toString());
            }
            
            return sb.toString();
        }
    }
}
