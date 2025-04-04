package Model;

/**
 * Clase que representa un libro en el sistema de biblioteca.
 * Contiene información básica del libro y maneja la disponibilidad de ejemplares.
 */
public class Libro {
    
    private String titulo;
    private String autor;
    private int añoPublicacion;
    private String isbn;
    private int ejemplaresDisponibles;

    /**
     * Constructor para crear un nuevo libro.
     * @param titulo Título del libro (no puede ser nulo o vacío)
     * @param autor Autor del libro (no puede ser nulo o vacío)
     * @param añoPublicacion Año de publicación del libro (debe ser un año válido)
     * @param isbn Número ISBN único del libro (no puede ser nulo o vacío)
     * @param ejemplaresDisponibles Cantidad de ejemplares disponibles (debe ser >= 0)
     */
    public Libro(String titulo, String autor, int añoPublicacion, String isbn, int ejemplaresDisponibles) {
        this.titulo = titulo;
        this.autor = autor;
        this.añoPublicacion = añoPublicacion;
        this.isbn = isbn;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }
    
    /**
     * Obtiene el título del libro.
     * @return Título del libro
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del libro.
     * @param titulo Nuevo título del libro (no debe ser nulo o vacío)
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el autor del libro.
     * @return Autor del libro
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Establece el autor del libro.
     * @param autor Nuevo autor del libro (no debe ser nulo o vacío)
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtiene el año de publicación del libro.
     * @return Año de publicación
     */
    public int getAñoPublicacion() {
        return añoPublicacion;
    }

    /**
     * Establece el año de publicación del libro.
     * @param añoPublicacion Nuevo año de publicación (debe ser un año válido)
     */
    public void setAñoPublicacion(int añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    /**
     * Obtiene la cantidad de ejemplares disponibles.
     * @return Número de ejemplares disponibles
     */
    public int getEjemplaresDisponibles() {
        return ejemplaresDisponibles;
    }

    /**
     * Establece la cantidad de ejemplares disponibles.
     * @param ejemplaresDisponibles Nueva cantidad de ejemplares (debe ser >= 0)
     */
    public void setEjemplaresDisponibles(int ejemplaresDisponibles) {
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    /**
     * Registra el préstamo de un ejemplar del libro.
     * Disminuye en 1 la cantidad de ejemplares disponibles si hay existencias.
     */
    public void prestar() {
        if (ejemplaresDisponibles > 0) {
            ejemplaresDisponibles--;
        }
    }

    /**
     * Registra la devolución de un ejemplar del libro.
     * Aumenta en 1 la cantidad de ejemplares disponibles.
     */
    public void devolver() {
        ejemplaresDisponibles++;
    }

    /**
     * Obtiene el ISBN del libro.
     * @return ISBN del libro
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Establece el ISBN del libro.
     * @param isbn Nuevo ISBN (no puede ser nulo o vacío)
     */
    public void setIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            System.out.println("Intento de asignar un ISBN nulo o vacío.");
        } else {
            this.isbn = isbn;
        }
    }

    /**
     * Devuelve una representación en cadena del libro.
     * @return Cadena con toda la información del libro
     */
    @Override
    public String toString() {
        return "Libro:" +
               "titulo:" + titulo +
               ", autor:" + autor +
               ", añoPublicacion:" + añoPublicacion +
               ", isbn:" + isbn +
               ", ejemplaresDisponibles:" + ejemplaresDisponibles;
    }
}