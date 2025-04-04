package Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import Model.Biblioteca;
import Model.Libro;
import Model.Usuario;
import Model.Prestamo;

/**
 * Clase controladora que actúa como intermediario entre la vista y el modelo.
 * Gestiona todas las operaciones del sistema de biblioteca.
 */
public class Control {
    private Biblioteca biblioteca;

    /**
     * Constructor que inicializa el controlador con una instancia de Biblioteca.
     * @param biblioteca Instancia de Biblioteca a gestionar
     */
    public Control(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    // Métodos para usuarios

    /**
     * Registra un nuevo usuario en el sistema.
     * @param nombre Nombre del usuario
     * @param identificacion Identificación única del usuario
     * @param tipoUsuario Tipo de usuario (estudiante, profesor, ciudadano, administrador)
     * @return true si el registro fue exitoso, false en caso contrario
     */
    public boolean registrarUsuario(String nombre, String identificacion, String tipoUsuario) {
        if (nombre == null || nombre.isEmpty() || identificacion == null || identificacion.isEmpty()) {
            return false;
        }
        
        Usuario usuario = new Usuario(nombre, identificacion, tipoUsuario);
        return biblioteca.registrarUsuario(usuario);
    }

    /**
     * Elimina un usuario por su identificación.
     * @param identificacion Identificación del usuario a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminarUsuarioPorId(String identificacion) {
        return biblioteca.eliminarUsuarioPorId(identificacion);
    }

    /**
     * Actualiza el tipo de un usuario existente.
     * @param identificacion Identificación del usuario a actualizar
     * @param nuevoTipo Nuevo tipo de usuario
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizarTipoDeUsuario(String identificacion, String nuevoTipo) {
        return biblioteca.actualizarTipoDeUsuario(identificacion, nuevoTipo);
    }

    /**
     * Busca un usuario por su identificación.
     * @param identificacion Identificación del usuario a buscar
     * @return Usuario encontrado o null si no existe
     */
    public Usuario buscarUsuarioPorId(String identificacion) {
        return biblioteca.buscarUsuarioPorId(identificacion);
    }

    // Métodos para libros

    /**
     * Agrega un nuevo libro al sistema.
     * @param titulo Título del libro
     * @param autor Autor del libro
     * @param añoPublicacion Año de publicación del libro
     * @param isbn ISBN único del libro
     * @param ejemplaresDisponibles Cantidad de ejemplares disponibles
     * @return true si el libro fue agregado exitosamente, false en caso contrario
     */
    public boolean agregarLibro(String titulo, String autor, int añoPublicacion, String isbn, int ejemplaresDisponibles) {
        if (titulo == null || titulo.isEmpty() || isbn == null || isbn.isEmpty() || ejemplaresDisponibles < 0) {
            return false;
        }
        Libro libro = new Libro(titulo, autor, añoPublicacion, isbn, ejemplaresDisponibles);
        return biblioteca.agregarLibro(libro);
    }

    /**
     * Elimina un libro por su ISBN.
     * @param isbn ISBN del libro a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminarLibroPorIsbn(String isbn) {
        return biblioteca.eliminarLibroPorIsbn(isbn);
    }

    /**
     * Actualiza la información de un libro existente.
     * @param isbn ISBN del libro a actualizar
     * @param nuevoTitulo Nuevo título (cadena vacía para no modificar)
     * @param nuevoAutor Nuevo autor (cadena vacía para no modificar)
     * @param nuevoAñoPublicacion Nuevo año de publicación (0 o negativo para no modificar)
     * @param nuevosEjemplares Nueva cantidad de ejemplares (negativo para no modificar)
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizarLibro(String isbn, String nuevoTitulo, String nuevoAutor, 
                                 int nuevoAñoPublicacion, int nuevosEjemplares) {
        return biblioteca.actualizarLibro(isbn, 
            nuevoTitulo.isEmpty() ? null : nuevoTitulo,
            nuevoAutor.isEmpty() ? null : nuevoAutor,
            nuevoAñoPublicacion <= 0 ? -1 : nuevoAñoPublicacion,
            nuevosEjemplares < 0 ? -1 : nuevosEjemplares);
    }

    /**
     * Obtiene una representación formateada de todos los libros.
     * @return Cadena con la información de todos los libros
     */
    public String mostrarLibros() {
        return biblioteca.mostrarLibros();
    }

    /**
     * Busca un libro por su ISBN.
     * @param isbn ISBN del libro a buscar
     * @return Libro encontrado o null si no existe
     */
    public Libro buscarLibroPorIsbn(String isbn) {
        return biblioteca.buscarLibroPorIsbn(isbn);
    }

    /**
     * Busca libros por título (coincidencia exacta).
     * @param titulo Título a buscar
     * @return Cadena formateada con los libros encontrados
     */
    public String buscarLibrosPorTitulo(String titulo) {
        ArrayList<Libro> libros = biblioteca.buscarLibrosPorTitulo(titulo);
        return formatearListaLibros(libros);
    }

    /**
     * Busca libros por autor (coincidencia parcial).
     * @param autor Autor a buscar
     * @return Cadena formateada con los libros encontrados
     */
    public String buscarLibrosPorAutor(String autor) {
        ArrayList<Libro> libros = biblioteca.buscarLibrosPorAutor(autor);
        return formatearListaLibros(libros);
    }

    /**
     * Verifica la disponibilidad de un libro.
     * @param isbn ISBN del libro a verificar
     * @return Mensaje indicando la disponibilidad del libro
     */
    public String verificarDisponibilidad(String isbn) {
        Libro libro = biblioteca.buscarLibroPorIsbn(isbn);
        if (libro == null) {
            return "Libro no encontrado.";
        }
        return libro.getEjemplaresDisponibles() > 0 ? 
            "Disponible (" + libro.getEjemplaresDisponibles() + " ejemplares)" :
            "No disponible en este momento";
    }

    // Métodos para préstamos

    /**
     * Realiza el préstamo de un libro a un usuario.
     * @param isbn ISBN del libro a prestar
     * @param identificacion Identificación del usuario
     * @return true si el préstamo fue exitoso, false en caso contrario
     */
    public boolean prestarLibro(String isbn, String identificacion) {
        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaDevolucion = fechaPrestamo.plusDays(30); // Préstamo por 30 días
        return biblioteca.prestarLibro(isbn, identificacion, fechaPrestamo, fechaDevolucion);
    }

    /**
     * Registra la devolución de un libro prestado.
     * @param isbn ISBN del libro a devolver
     * @param identificacion Identificación del usuario
     * @return true si la devolución fue exitosa, false en caso contrario
     */
    public boolean devolverLibro(String isbn, String identificacion) {
        LocalDate fechaDevolucion = LocalDate.now();
        return biblioteca.devolverLibro(isbn, identificacion, fechaDevolucion);
    }

    /**
     * Calcula la multa por retraso en la devolución de un libro.
     * @param isbn ISBN del libro
     * @param identificacion Identificación del usuario
     * @return Monto de la multa calculada
     */
    public double calcularMulta(String isbn, String identificacion) {
        LocalDate fechaDevolucion = LocalDate.now();
        return biblioteca.calcularMulta(isbn, identificacion, fechaDevolucion);
    }

    /**
     * Obtiene una lista formateada de todos los préstamos activos.
     * @return Cadena con la información de los préstamos activos
     */
    public String mostrarPrestamosActivos() {
        ArrayList<Prestamo> prestamos = biblioteca.getPrestamos();
        if (prestamos.isEmpty()) {
            return "No hay préstamos activos.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("\nPréstamos activos:\n");
        for (Prestamo prestamo : prestamos) {
            sb.append("----------------------------------------\n");
            sb.append("Usuario: ").append(prestamo.getUsuario().getNombre()).append("\n");
            sb.append("Libro: ").append(prestamo.getLibro().getTitulo()).append("\n");
            sb.append("ISBN: ").append(prestamo.getLibro().getIsbn()).append("\n");
            sb.append("Fecha préstamo: ").append(prestamo.getFechaPrestamo()).append("\n");
            sb.append("Fecha devolución: ").append(prestamo.getFechaDevolucion()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Obtiene los préstamos activos de un usuario.
     * @param identificacion Identificación del usuario
     * @return Cadena formateada con los préstamos del usuario
     */
    public String obtenerPrestamosUsuario(String identificacion) {
        ArrayList<Libro> libros = biblioteca.obtenerLibrosPrestadosPorUsuario(identificacion);
        if (libros.isEmpty()) {
            return "No tiene préstamos activos.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("\nPréstamos activos:\n");
        for (Libro libro : libros) {
            sb.append("----------------------------------------\n");
            sb.append("Título: ").append(libro.getTitulo()).append("\n");
            sb.append("ISBN: ").append(libro.getIsbn()).append("\n");
            sb.append("Autor: ").append(libro.getAutor()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Consulta la multa acumulada de un usuario.
     * @param identificacion Identificación del usuario
     * @return Monto de la multa acumulada
     */
    public double consultarMultaUsuario(String identificacion) {
        Usuario usuario = buscarUsuarioPorId(identificacion);
        return usuario != null ? usuario.getMultaAcumulada() : 0.0;
    }

    /**
     * Registra el pago de una multa por parte de un usuario.
     * @param identificacion Identificación del usuario
     * @param monto Monto a pagar
     * @return true si el pago fue registrado, false en caso contrario
     */
    public boolean pagarMulta(String identificacion, double monto) {
        Usuario usuario = buscarUsuarioPorId(identificacion);
        if (usuario != null && monto > 0) {
            usuario.pagarMulta(monto);
            return true;
        }
        return false;
    }

    /**
     * Verifica si un usuario tiene permisos de administrador.
     * @param idUsuario Identificación del usuario a verificar
     * @return true si el usuario es administrador, false en caso contrario
     */
    public boolean verificarPermisosAdmin(String idUsuario) {
        Usuario usuario = biblioteca.buscarUsuarioPorId(idUsuario);
        return usuario != null && usuario.esAdmin();
    }

    // Métodos auxiliares

    /**
     * Formatea una lista de libros para su visualización.
     * @param libros Lista de libros a formatear
     * @return Cadena formateada con la información de los libros
     */
    private String formatearListaLibros(ArrayList<Libro> libros) {
        if (libros == null || libros.isEmpty()) {
            return "No se encontraron libros.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("\nLibros encontrados (").append(libros.size()).append("):\n");
        for (Libro libro : libros) {
            sb.append("----------------------------------------\n");
            sb.append("Título: ").append(libro.getTitulo()).append("\n");
            sb.append("Autor: ").append(libro.getAutor()).append("\n");
            sb.append("Año: ").append(libro.getAñoPublicacion()).append("\n");
            sb.append("ISBN: ").append(libro.getIsbn()).append("\n");
            sb.append("Disponibles: ").append(libro.getEjemplaresDisponibles()).append("\n");
        }
        sb.append("----------------------------------------\n");
        return sb.toString();
    }
}