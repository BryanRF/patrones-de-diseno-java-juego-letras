package Estrategias;

import Modelo.Entities.Objeto;

/**
 * Interfaz Strategy para diferentes categorías de objetos
 * Permite extender fácilmente con nuevas categorías sin modificar código existente
 */
public interface CategoriaStrategy {
    /**
     * Obtiene el nombre de la categoría
     * @return Nombre de la categoría
     */
    String getNombreCategoria();
    
    /**
     * Construye y retorna el objeto de la categoría
     * @return Objeto con los datos de la categoría
     */
    Objeto construirObjeto();
    
    /**
     * Obtiene la ruta de la imagen representativa de la categoría
     * @return Ruta de la imagen
     */
    String getRutaImagen();
}

