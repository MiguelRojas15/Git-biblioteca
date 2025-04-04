package View;

import Controller.Control;

/**
 * Clase principal que inicia la aplicación del sistema de biblioteca.
 * <p>
 * Esta clase contiene el método main que sirve como punto de entrada
 * para la ejecución del programa.
 * </p>
 */
public class App {
    
    /**
     * Método principal que inicia la aplicación.
     * <p>
     * Crea las instancias necesarias del controlador y modelo,
     * e inicia la interfaz de usuario mediante la clase LogicaVista.
     * </p>
     * 
     * @param args Argumentos de línea de comandos (no utilizados en esta aplicación)
     */
    public static void main(String[] args) {
        // Inicializa el controlador con una nueva instancia de Biblioteca
        Control control = new Control(new Model.Biblioteca());
        
        // Inicia la lógica de la vista (interfaz de usuario)
        LogicaVista.iniciarAplicacion(control);
    }
}