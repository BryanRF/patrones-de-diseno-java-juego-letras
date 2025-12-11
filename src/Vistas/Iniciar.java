
package Vistas;

import Controlador.*;
import Utilidades.LoggingConfig;

public class Iniciar {
    
    public Iniciar (){
        
    }
    public void ejecutar (boolean ventana){
       Ventana_juego J= new Ventana_juego();
            Ventana_menu  M= new Ventana_menu();
            Ventana_ajustes A= new Ventana_ajustes();  
            Ventana_iniciar I= new Ventana_iniciar();
            //J-FRAME VENTANA
            Ventana_principal V= new Ventana_principal(J,M,A,I); 
            V.setUndecorated(ventana);
                        V.setLocationRelativeTo(null); 

            //CLASE FUNCIONALIDAD
            Funcionalidad F=new Funcionalidad(V,J,M,A,I);

          J.conectaFuncionalidad(F);
          M.conectaFuncionalidad(F);
          A.conectaFuncionalidad(F);
                      V.setLocationRelativeTo(null); 

    }


    public static void main(String[] args) {
        // Configurar logging con codificación UTF-8 y formato correcto
        LoggingConfig.configurar();
        
        Iniciar runner= new Iniciar();
        Configuracion confi= new Configuracion();
        runner.ejecutar(confi.configuracion());
          

    } 


             
        
}
