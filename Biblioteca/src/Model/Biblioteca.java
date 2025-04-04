package Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Clase que representa el sistema de gestión de una biblioteca.
 * Permite administrar libros, usuarios y préstamos.
 */
public class Biblioteca {
    private ArrayList<Libro> libros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Prestamo> prestamos;

    /**
     * Constructor que inicializa las listas de libros, usuarios y préstamos,
     * y carga datos iniciales en el sistema.
     */
    public Biblioteca() {
        this.libros = new ArrayList<Libro>();
        this.usuarios = new ArrayList<Usuario>();
        this.prestamos = new ArrayList<Prestamo>();
        inicializarDatos();
    }

    /**
     * Método privado para inicializar datos de prueba en el sistema.
     * Agrega libros y un usuario administrador por defecto.
     */
    private void inicializarDatos() {
        agregarLibro(new Libro("El Aleph", "Jorge Luis Borges", 1949, "978-950-04-0406-9", 3));
        agregarLibro(new Libro("Cien Años de Soledad", "Gabriel García Márquez", 1967, "978-84-376-0494-7", 5));
        agregarLibro(new Libro("1984", "George Orwell", 1949, "978-0451524935", 3));
        agregarLibro(new Libro("El Principito", "Antoine de Saint-Exupéry", 1943, "978-0156012195", 4));
        agregarLibro(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", 1605, "978-8491050767", 2));
        agregarLibro(new Libro("Crimen y Castigo", "Fiódor Dostoyevski", 1866, "978-0140449136", 3));
        
        registrarUsuario(new Usuario("Admin", "123", "Administrador"));
    }

    // Getters y setters con validación
    
    /**
     * Obtiene una copia de la lista de libros.
     * @return ArrayList con todos los libros del sistema
     */
    public ArrayList<Libro> getLibros() {
        return new ArrayList<Libro>(libros); // Devuelve copia para evitar modificaciones externas
    }

    /**
     * Establece la lista de libros.
     * @param libros Lista de libros a establecer (no nula)
     */
    public void setLibros(ArrayList<Libro> libros) {
        if (libros != null) {
            this.libros = new ArrayList<Libro>(libros);
        }
    }

    /**
     * Obtiene una copia de la lista de usuarios.
     * @return ArrayList con todos los usuarios del sistema
     */
    public ArrayList<Usuario> getUsuarios() {
        return new ArrayList<Usuario>(usuarios);
    }

    /**
     * Establece la lista de usuarios.
     * @param usuarios Lista de usuarios a establecer (no nula)
     */
    public void setUsuarios(ArrayList<Usuario> usuarios) {
        if (usuarios != null) {
            this.usuarios = new ArrayList<Usuario>(usuarios);
        }
    }

    /**
     * Obtiene una copia de la lista de préstamos.
     * @return ArrayList con todos los préstamos activos
     */
    public ArrayList<Prestamo> getPrestamos() {
        return new ArrayList<Prestamo>(prestamos);
    }

    /**
     * Establece la lista de préstamos.
     * @param prestamos Lista de préstamos a establecer (no nula)
     */
    public void setPrestamos(ArrayList<Prestamo> prestamos) {
        if (prestamos != null) {
            this.prestamos = new ArrayList<Prestamo>(prestamos);
        }
    }

    // Métodos para usuarios
    
    /**
     * Registra un nuevo usuario en el sistema.
     * @param usuario Usuario a registrar
     * @return true si se registró correctamente, false si el usuario es nulo, ya existe o tiene tipo inválido
     */
    public boolean registrarUsuario(Usuario usuario) {
        if (usuario == null || existeUsuario(usuario.getIdentificacion())) {
            return false;
        }
        if(usuario.getTipoUsuario() == null || usuario.getTipoUsuario().isEmpty()) {
            return false; 
        }
        if (!usuario.getTipoUsuario().equalsIgnoreCase("estudiante") &&
        !usuario.getTipoUsuario().equalsIgnoreCase("profesor") &&
        !usuario.getTipoUsuario().equalsIgnoreCase("ciudadano") &&
        !usuario.getTipoUsuario().equalsIgnoreCase("administrador")) {
        return false;
        }
        usuarios.add(usuario);
        return true;
    }

    /**
     * Elimina un usuario por su identificación.
     * @param identificacion Identificación del usuario a eliminar
     * @return true si se eliminó correctamente, false si no se encontró el usuario
     */
    public boolean eliminarUsuarioPorId(String identificacion) {
        Usuario usuario = buscarUsuarioPorId(identificacion);
        if (usuario != null) {
            return usuarios.remove(usuario);
        }
        return false;
    }

    /**
     * Actualiza el tipo de un usuario existente.
     * @param identificacion Identificación del usuario a actualizar
     * @param nuevoTipo Nuevo tipo de usuario
     * @return true si se actualizó correctamente, false si no se encontró el usuario
     */
    public boolean actualizarTipoDeUsuario(String identificacion, String nuevoTipo) {
        Usuario usuario = buscarUsuarioPorId(identificacion);
        if (usuario != null) {
            usuario.setTipoUsuario(nuevoTipo);
            return true;
        }
        return false;
    }

    /**
     * Busca un usuario por su identificación.
     * @param identificacion Identificación del usuario a buscar
     * @return Usuario encontrado o null si no existe
     */
    public Usuario buscarUsuarioPorId(String identificacion) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdentificacion().equals(identificacion)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Verifica si un usuario ya está registrado.
     * @param identificacion Identificación del usuario a verificar
     * @return true si el usuario existe, false en caso contrario
     */
    private boolean existeUsuario(String identificacion) {
        return usuarios.stream()
            .anyMatch(u -> u.getIdentificacion().equals(identificacion));
    }

    // Métodos para libros
    
    /**
     * Agrega un nuevo libro al sistema.
     * @param libro Libro a agregar
     * @return true si se agregó correctamente, false si el libro es nulo, ya existe o tiene año inválido
     */
    public boolean agregarLibro(Libro libro) {
        if (libro == null || existeLibro(libro.getIsbn())) {
            return false;
        }
        
        // Validar que el año no sea futuro
        if (libro.getAñoPublicacion() > LocalDate.now().getYear()) {
            return false;
        }
        
        libros.add(libro);
        return true;
    }

    /**
     * Elimina un libro por su ISBN.
     * @param isbn ISBN del libro a eliminar
     * @return true si se eliminó correctamente, false si no se encontró el libro
     */
    public boolean eliminarLibroPorIsbn(String isbn) {
        Libro libro = buscarLibroPorIsbn(isbn);
        if (libro != null) {
            return libros.remove(libro);
        }
        return false;
    }

    /**
     * Actualiza la información de un libro existente.
     * @param isbn ISBN del libro a actualizar
     * @param nuevoTitulo Nuevo título (opcional, puede ser null)
     * @param nuevoAutor Nuevo autor (opcional, puede ser null)
     * @param nuevoAñoPublicacion Nuevo año de publicación (opcional, debe ser > 0)
     * @param nuevosEjemplares Nuevo número de ejemplares (debe ser >= 0)
     * @return true si se actualizó correctamente, false si no se encontró el libro
     */
    public boolean actualizarLibro(String isbn, String nuevoTitulo, String nuevoAutor, int nuevoAñoPublicacion, int nuevosEjemplares) {
        Libro libro = buscarLibroPorIsbn(isbn);
        if (libro != null) {
            if (nuevoTitulo != null && !nuevoTitulo.isEmpty()) {
                libro.setTitulo(nuevoTitulo);
            }
            if (nuevoAutor != null && !nuevoAutor.isEmpty()) {
                libro.setAutor(nuevoAutor);
            }
            if (nuevoAñoPublicacion > 0) {
                libro.setAñoPublicacion(nuevoAñoPublicacion);
            }
            if (nuevosEjemplares >= 0) {
                libro.setEjemplaresDisponibles(nuevosEjemplares);
            }
            return true;
        }
        return false;
    }

    /**
     * Genera una representación en cadena de todos los libros del sistema.
     * @return Cadena formateada con la información de todos los libros
     */
    public String mostrarLibros() {
        if (libros.isEmpty()) {
            return "No hay libros registrados en el sistema.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n LISTA COMPLETA DE LIBROS (").append(libros.size()).append("):\n");
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

    /**
     * Busca un libro por su ISBN.
     * @param isbn ISBN del libro a buscar
     * @return Libro encontrado o null si no existe
     */
    public Libro buscarLibroPorIsbn(String isbn) {
        for (Libro libro : libros) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }

    /**
     * Busca libros por título (coincidencia exacta, insensible a mayúsculas).
     * @param titulo Título a buscar
     * @return Lista de libros que coinciden con el título
     */
    public ArrayList<Libro> buscarLibrosPorTitulo(String titulo) {
        ArrayList<Libro> resultados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    /**
     * Busca libros por autor (coincidencia parcial, insensible a mayúsculas y acentos).
     * @param autor Autor a buscar
     * @return Lista de libros cuyo autor coincide con el parámetro
     */
    public ArrayList<Libro> buscarLibrosPorAutor(String autor) {
        ArrayList<Libro> resultados = new ArrayList<>();
        String autorBusqueda = normalizarTexto(autor); // Normaliza el texto de búsqueda
        
        for (Libro libro : libros) {
            String autorLibro = normalizarTexto(libro.getAutor());
            if (autorLibro.contains(autorBusqueda)) {
                resultados.add(libro);
            }
        }
        return resultados;
    }
    
    /**
     * Normaliza texto para búsquedas (quita acentos y convierte a minúsculas).
     * @param texto Texto a normalizar
     * @return Texto normalizado
     */
    private String normalizarTexto(String texto) {
        return texto.toLowerCase()
                   .replace("á", "a")
                   .replace("é", "e")
                   .replace("í", "i")
                   .replace("ó", "o")
                   .replace("ú", "u");
    }

    /**
     * Verifica si un libro ya está registrado.
     * @param isbn ISBN del libro a verificar
     * @return true si el libro existe, false en caso contrario
     */
    private boolean existeLibro(String isbn) {
        return buscarLibroPorIsbn(isbn) != null;
    } 

    /**
     * Verifica si un usuario puede pedir más libros prestados (límite de 3).
     * @param identificacion Identificación del usuario
     * @return true si puede pedir más libros, false si ya alcanzó el límite
     */
    public boolean puedePrestarMasLibros(String identificacion) {
        int contador = 0;
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getUsuario().getIdentificacion().equals(identificacion)) {
                contador++;
                if (contador >= 3) {
                    return false;
                }
            }
        }
        return true;
    }

    // Métodos para préstamos
    
    /**
     * Realiza el préstamo de un libro a un usuario.
     * @param isbn ISBN del libro a prestar
     * @param identificacion Identificación del usuario
     * @param fechaPrestamo Fecha en que se realiza el préstamo
     * @param fechaDevolucion Fecha límite para devolución
     * @return true si el préstamo se realizó correctamente, false en caso contrario
     */
    public boolean prestarLibro(String isbn, String identificacion, 
            LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        Libro libro = buscarLibroPorIsbn(isbn);
        Usuario usuario = buscarUsuarioPorId(identificacion);

        if (libro == null || usuario == null) {
            return false;
        }

        if (!puedePrestarMasLibros(identificacion)) {
            return false;
        }

        if (usuario.getMultaAcumulada() > 0) {
            return false;
        }

        if (libro.getEjemplaresDisponibles() > 0) {
            Prestamo prestamo = new Prestamo(usuario, libro, fechaPrestamo, fechaDevolucion);
            prestamos.add(prestamo);
            libro.prestar();
            return true;
        }

        return false;
    }

    /**
     * Registra la devolución de un libro prestado.
     * @param isbn ISBN del libro a devolver
     * @param identificacion Identificación del usuario
     * @param fechaDevolucion Fecha real de devolución
     * @return true si la devolución se registró correctamente, false en caso contrario
     */
    public boolean devolverLibro(String isbn, String identificacion, LocalDate fechaDevolucion) {
        Prestamo prestamo = buscarPrestamoActivo(isbn, identificacion);
        if (prestamo != null) {
            prestamos.remove(prestamo);
            prestamo.getLibro().devolver();
            
            if (fechaDevolucion.isAfter(prestamo.getFechaDevolucion())) {
                long diasRetraso = ChronoUnit.DAYS.between(
                    prestamo.getFechaDevolucion(), 
                    fechaDevolucion
                );
                double multa = diasRetraso * 1000;
                prestamo.getUsuario().agregarMulta(multa);
            }
            return true;
        }
        return false;
    }

    /**
     * Busca un préstamo activo por ISBN e identificación de usuario.
     * @param isbn ISBN del libro prestado
     * @param identificacion Identificación del usuario
     * @return Préstamo activo o null si no se encuentra
     */
    public Prestamo buscarPrestamoActivo(String isbn, String identificacion) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getLibro().getIsbn().equals(isbn) && 
                prestamo.getUsuario().getIdentificacion().equals(identificacion)) {
                return prestamo;
            }
        }
        return null;
    }

    /**
     * Obtiene la lista de libros prestados a un usuario.
     * @param identificacion Identificación del usuario
     * @return Lista de libros prestados al usuario
     */
    public ArrayList<Libro> obtenerLibrosPrestadosPorUsuario(String identificacion) {
        ArrayList<Libro> librosPrestados = new ArrayList<>();
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getUsuario().getIdentificacion().equals(identificacion)) {
                librosPrestados.add(prestamo.getLibro());
            }
        }
        return librosPrestados;
    }

    /**
     * Calcula la multa por retraso en la devolución de un libro.
     * @param isbn ISBN del libro
     * @param identificacion Identificación del usuario
     * @param fechaDevolucionReal Fecha real de devolución
     * @return Monto de la multa (0 si no hay retraso)
     */
    public double calcularMulta(String isbn, String identificacion, LocalDate fechaDevolucionReal) {
        Prestamo prestamo = buscarPrestamoActivo(isbn, identificacion);
        if (prestamo == null) {
            return 0.0;
        }
        
        if (fechaDevolucionReal.isAfter(prestamo.getFechaDevolucion())) {
            long diasRetraso = ChronoUnit.DAYS.between(prestamo.getFechaDevolucion(), fechaDevolucionReal);
            return diasRetraso * 1000; // $1000 por día de retraso
        }
        return 0.0;
    }

    /**
     * Registra una multa a un usuario.
     * @param identificacion Identificación del usuario
     * @param monto Monto de la multa
     * @return true si se registró correctamente, false si no se encontró el usuario
     */
    public boolean registrarMulta(String identificacion, double monto) {
        Usuario usuario = buscarUsuarioPorId(identificacion);
        if (usuario != null) {
            usuario.agregarMulta(monto);
            return true;
        }
        return false;
    }
}