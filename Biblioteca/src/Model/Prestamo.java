package Model;

import java.time.LocalDate;

/**
 * Clase que representa un préstamo de libro en el sistema de biblioteca.
 * Contiene información sobre el usuario, libro prestado y fechas relevantes.
 */
public class Prestamo {
    
    private Usuario usuario;
    private Libro libro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    /**
     * Constructor para crear un nuevo préstamo.
     * @param usuario Usuario que realiza el préstamo (no puede ser nulo)
     * @param libro Libro que se presta (no puede ser nulo)
     * @param fechaPrestamo Fecha en que se realiza el préstamo (no puede ser nula)
     * @param fechaDevolucion Fecha límite para devolución (no puede ser nula y debe ser posterior a fechaPrestamo)
     */
    public Prestamo(Usuario usuario, Libro libro, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    /**
     * Obtiene el usuario asociado al préstamo.
     * @return Usuario que realizó el préstamo
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado al préstamo.
     * @param usuario Nuevo usuario (no puede ser nulo)
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el libro prestado.
     * @return Libro asociado al préstamo
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * Establece el libro asociado al préstamo.
     * @param libro Nuevo libro (no puede ser nulo)
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * Obtiene la fecha en que se realizó el préstamo.
     * @return Fecha de préstamo
     */
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**
     * Establece la fecha en que se realizó el préstamo.
     * @param fechaPrestamo Nueva fecha de préstamo (no puede ser nula)
     */
    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    /**
     * Obtiene la fecha límite para devolución.
     * @return Fecha de devolución
     */
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * Establece la fecha límite para devolución.
     * @param fechaDevolucion Nueva fecha de devolución (no puede ser nula y debe ser posterior a fechaPrestamo)
     */
    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
