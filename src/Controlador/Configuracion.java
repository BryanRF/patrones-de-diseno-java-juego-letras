package Controlador;

import Servicios.ConfiguracionService;

/**
 * Clase de compatibilidad - delegando a ConfiguracionService
 * @deprecated Usar Servicios.ConfiguracionService directamente
 */
@Deprecated
public class Configuracion {
    private final ConfiguracionService service;
    
    public Configuracion() {
        this.service = new ConfiguracionService();
    }
    
    /**
     * @deprecated Usar ConfiguracionService.obtenerModoVentana()
     */
    @Deprecated
    public boolean configuracion() {
        return service.configuracion();
    }
    
    /**
     * @deprecated Usar ConfiguracionService.cambiarModoVentana()
     */
    @Deprecated
    public void cambiarConfiguracion() {
        service.cambiarConfiguracion();
    }
}
