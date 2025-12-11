package Modelo.Observer;

/**
 * Interfaz Sujeto del patrón Observer
 * Define el contrato para los sujetos observables
 */
public interface Sujeto_Palabra {
    void agregar(Observador O);
    void eliminar(Observador O);
    boolean toEstado();
    void notificar();
}

