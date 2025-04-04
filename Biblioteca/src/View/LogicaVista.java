package View;

import java.util.Scanner;
import java.time.LocalDate;
import Controller.Control;
import Model.Usuario;
import Model.Libro;

/**
 * Clase que maneja la lógica de presentación y la interacción con el usuario.
 * <p>
 * Esta clase contiene todos los métodos necesarios para mostrar menús,
 * capturar entradas del usuario y mostrar resultados.
 * </p>
 */
public class LogicaVista {
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Inicia la aplicación mostrando el menú de inicio de sesión.
     * @param control Instancia del controlador para manejar la lógica de negocio
     */
    public static void iniciarAplicacion(Control control) {
        System.out.println("\n--- INICIO DE SESIÓN ---");
        System.out.print("Ingrese su ID de usuario: ");
        String idUsuario = scanner.nextLine();
        
        Usuario usuario = control.buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        
        if (usuario.esAdmin()) {
            mostrarMenuAdmin(control, idUsuario);
        } else {
            mostrarMenuUsuario(control, idUsuario);
        }
    }

    /**
     * Muestra el menú principal para administradores.
     * @param control Instancia del controlador
     * @param idAdmin Identificación del administrador
     */
    private static void mostrarMenuAdmin(Control control, String idAdmin) {
        while (true) {
            System.out.println("\n--- MENÚ ADMINISTRADOR ---");
            System.out.println("1. Gestión de Usuarios");
            System.out.println("2. Gestión de Libros");
            System.out.println("3. Gestión de Préstamos");
            System.out.println("4. Consultar multas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = leerEntero();
            scanner.nextLine();
            
            switch (opcion) {
                case 1: menuGestionUsuarios(control); break;
                case 2: menuGestionLibrosAdmin(control); break;
                case 3: menuGestionPrestamosAdmin(control); break;
                case 4: consultarMultas(control); break;
                case 0: return;
                default: System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Muestra el menú principal para usuarios regulares.
     * @param control Instancia del controlador
     * @param idUsuario Identificación del usuario
     */
    private static void mostrarMenuUsuario(Control control, String idUsuario) {
        while (true) {
            System.out.println("\n--- MENÚ USUARIO ---");
            System.out.println("1. Buscar libros");
            System.out.println("2. Mis préstamos actuales");
            System.out.println("3. Pagar multas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = leerEntero();
            scanner.nextLine();
            
            switch (opcion) {
                case 1: menuBusquedas(control); break;
                case 2: mostrarPrestamosUsuario(control, idUsuario); break;
                case 3: menuPagarMultas(control, idUsuario); break;
                case 0: return;
                default: System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Muestra el submenú para gestión de usuarios.
     * @param control Instancia del controlador
     */
    private static void menuGestionUsuarios(Control control) {
        while (true) {
            System.out.println("\n--- GESTIÓN DE USUARIOS ---");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Actualizar tipo de usuario");
            System.out.println("4. Buscar usuario");
            System.out.println("0. Volver");
            System.out.print("Selección: ");
            
            int opcion = leerEntero();
            scanner.nextLine();
            
            switch (opcion) {
                case 1: registrarUsuario(control); break;
                case 2: eliminarUsuario(control); break;
                case 3: actualizarTipoUsuario(control); break;
                case 4: buscarUsuario(control); break;
                case 0: return;
                default: System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * @param control Instancia del controlador
     */
    private static void registrarUsuario(Control control) {
        System.out.println("\nREGISTRAR NUEVO USUARIO");
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Número de identificación: ");
        String id = scanner.nextLine();
        System.out.print("Tipo de usuario (Estudiante/Profesor/Ciudadano/Administrador): ");
        String tipo = scanner.nextLine();
        
        if (control.registrarUsuario(nombre, id, tipo)) {
            System.out.println("Usuario registrado exitosamente!");
        } else {
            System.out.println("Error al registrar usuario. Verifique los datos.");
        }
    }

    /**
     * Elimina un usuario del sistema.
     * @param control Instancia del controlador
     */
    private static void eliminarUsuario(Control control) {
        System.out.println("\nELIMINAR USUARIO");
        System.out.print("Ingrese ID del usuario a eliminar: ");
        String id = scanner.nextLine();
        
        if (control.eliminarUsuarioPorId(id)) {
            System.out.println("Usuario eliminado correctamente!");
        } else {
            System.out.println("Usuario no encontrado o no se pudo eliminar.");
        }
    }

    /**
     * Muestra el submenú para gestión de libros (administrador).
     * @param control Instancia del controlador
     */
    private static void menuGestionLibrosAdmin(Control control) {
        while (true) {
            System.out.println("\n--- GESTIÓN DE LIBROS (ADMIN) ---");
            System.out.println("1. Agregar libro");
            System.out.println("2. Eliminar libro");
            System.out.println("3. Actualizar libro");
            System.out.println("4. Mostrar todos");
            System.out.println("0. Volver");
            System.out.print("Selección: ");
            
            int opcion = leerEntero();
            scanner.nextLine();
            
            switch (opcion) {
                case 1: agregarLibro(control); break;
                case 2: eliminarLibro(control); break;
                case 3: actualizarLibro(control); break;
                case 4: mostrarTodosLibros(control); break;
                case 0: return;
                default: System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Agrega un nuevo libro al sistema.
     * @param control Instancia del controlador
     */
    private static void agregarLibro(Control control) {
        System.out.println("\nAGREGAR NUEVO LIBRO");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        
        System.out.print("Año publicación: ");
        int año = leerEntero();
        scanner.nextLine();
        
        if (año > LocalDate.now().getYear()) {
            System.out.println("Error: No se puede agregar un libro con año futuro.");
            return;
        }
        
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        
        System.out.print("Ejemplares disponibles: ");
        int ejemplares = leerEntero();
        scanner.nextLine();
        
        if (control.agregarLibro(titulo, autor, año, isbn, ejemplares)) {
            System.out.println("Libro agregado exitosamente!");
        } else {
            System.out.println("Error al agregar libro. Verifique los datos.");
        }
    }

    /**
     * Elimina un libro del sistema.
     * @param control Instancia del controlador
     */
    private static void eliminarLibro(Control control) {
        System.out.println("\nELIMINAR LIBRO");
        System.out.print("Ingrese ISBN del libro a eliminar: ");
        String isbn = scanner.nextLine();
        
        if (control.eliminarLibroPorIsbn(isbn)) {
            System.out.println("Libro eliminado correctamente!");
        } else {
            System.out.println("Libro no encontrado o no se pudo eliminar.");
        }
    }

    /**
     * Actualiza la información de un libro existente.
     * @param control Instancia del controlador
     */
    private static void actualizarLibro(Control control) {
        System.out.println("\nACTUALIZAR LIBRO");
        System.out.print("Ingrese ISBN del libro a actualizar: ");
        String isbn = scanner.nextLine();
        
        System.out.print("Nuevo título (dejar vacío para no cambiar): ");
        String nuevoTitulo = scanner.nextLine();
        
        System.out.print("Nuevo autor (dejar vacío para no cambiar): ");
        String nuevoAutor = scanner.nextLine();
        
        System.out.print("Nuevo año de publicación (0 para no cambiar): ");
        int nuevoAño = leerEntero();
        scanner.nextLine();
        
        System.out.print("Nuevo número de ejemplares (0 para no cambiar): ");
        int nuevosEjemplares = leerEntero();
        scanner.nextLine();
        
        if (control.actualizarLibro(isbn, 
            nuevoTitulo.isEmpty() ? null : nuevoTitulo,
            nuevoAutor.isEmpty() ? null : nuevoAutor,
            nuevoAño == 0 ? -1 : nuevoAño,
            nuevosEjemplares == 0 ? -1 : nuevosEjemplares)) {
            System.out.println("Libro actualizado correctamente!");
        } else {
            System.out.println("No se pudo actualizar. Verifique el ISBN.");
        }
    }

    /**
     * Muestra todos los libros registrados en el sistema.
     * @param control Instancia del controlador
     */
    private static void mostrarTodosLibros(Control control) {
        System.out.println("\nLISTA COMPLETA DE LIBROS");
        String listaLibros = control.mostrarLibros();
        System.out.println(listaLibros.isEmpty() ? "No hay libros registrados." : listaLibros);
    }

    /**
     * Muestra el submenú para gestión de préstamos (administrador).
     * @param control Instancia del controlador
     */
    private static void menuGestionPrestamosAdmin(Control control) {
        while (true) {
            System.out.println("\n--- GESTIÓN DE PRÉSTAMOS (ADMIN) ---");
            System.out.println("1. Registrar préstamo");
            System.out.println("2. Registrar devolución");
            System.out.println("3. Ver préstamos activos");
            System.out.println("0. Volver");
            System.out.print("Selección: ");
            
            int opcion = leerEntero();
            scanner.nextLine();
            
            switch (opcion) {
                case 1: registrarPrestamo(control); break;
                case 2: registrarDevolucion(control); break;
                case 3: mostrarPrestamosActivos(control); break;
                case 0: return;
                default: System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Registra un nuevo préstamo en el sistema.
     * @param control Instancia del controlador
     */
    private static void registrarPrestamo(Control control) {
        System.out.println("\nREGISTRAR PRÉSTAMO");
        System.out.print("ISBN del libro: ");
        String isbn = scanner.nextLine();
        
        System.out.print("ID del usuario: ");
        String id = scanner.nextLine();
        
        if (control.prestarLibro(isbn, id)) {
            System.out.println("Préstamo registrado con éxito!");
            System.out.println("Fecha de devolución: " + LocalDate.now().plusDays(30));
        } else {
            System.out.println("No se pudo registrar el préstamo. Verifique:");
            System.out.println("- Disponibilidad del libro");
            System.out.println("- ID de usuario válido");
            System.out.println("- Usuario no excede límite de préstamos (3)");
            System.out.println("- Usuario no tiene multas pendientes");
        }
    }

    /**
     * Registra la devolución de un libro prestado.
     * @param control Instancia del controlador
     */
    private static void registrarDevolucion(Control control) {
        System.out.println("\nREGISTRAR DEVOLUCIÓN");
        System.out.print("ISBN del libro: ");
        String isbn = scanner.nextLine();
        
        System.out.print("ID del usuario: ");
        String id = scanner.nextLine();
        
        System.out.println("Fecha de devolución registrada: " + LocalDate.now());
        
        if (control.devolverLibro(isbn, id)) {
            System.out.println("Devolución registrada con éxito!");
            
            double multa = control.calcularMulta(isbn, id);
            if (multa > 0) {
                System.out.printf("¡Atención! Multa generada: $%.2f%n", multa);
            }
        } else {
            System.out.println("No se pudo registrar la devolución. Verifique:");
            System.out.println("- El libro estaba prestado a este usuario");
            System.out.println("- Los datos son correctos");
        }
    }

    /**
     * Muestra todos los préstamos activos en el sistema.
     * @param control Instancia del controlador
     */
    private static void mostrarPrestamosActivos(Control control) {
        System.out.println("\nPRÉSTAMOS ACTIVOS");
        String prestamos = control.mostrarPrestamosActivos();
        System.out.println(prestamos.isEmpty() ? "No hay préstamos activos." : prestamos);
    }

    /**
     * Muestra los préstamos activos de un usuario específico.
     * @param control Instancia del controlador
     * @param idUsuario Identificación del usuario
     */
    private static void mostrarPrestamosUsuario(Control control, String idUsuario) {
        System.out.println("\n--- MIS PRÉSTAMOS ---");
        String prestamos = control.obtenerPrestamosUsuario(idUsuario);
        System.out.println(prestamos.isEmpty() ? "No tiene préstamos activos." : prestamos);
    }

    /**
     * Consulta las multas de un usuario específico.
     * @param control Instancia del controlador
     */
    private static void consultarMultas(Control control) {
        System.out.print("\nIngrese ID del usuario: ");
        String id = scanner.nextLine();
        
        double multa = control.consultarMultaUsuario(id);
        if (multa > 0) {
            System.out.printf("Multa pendiente: $%.2f%n", multa);
        } else {
            System.out.println("El usuario no tiene multas pendientes.");
        }
    }

    /**
     * Muestra el menú para pagar multas.
     * @param control Instancia del controlador
     * @param idUsuario Identificación del usuario
     */
    private static void menuPagarMultas(Control control, String idUsuario) {
        double multa = control.consultarMultaUsuario(idUsuario);
        if (multa <= 0) {
            System.out.println("\nNo tiene multas pendientes.");
            return;
        }
        
        System.out.printf("\nMulta pendiente: $%.2f%n", multa);
        System.out.print("Ingrese monto a pagar: ");
        double pago = scanner.nextDouble();
        scanner.nextLine();
        
        if (control.pagarMulta(idUsuario, pago)) {
            System.out.println("Pago realizado exitosamente!");
        } else {
            System.out.println("Error al procesar el pago.");
        }
    }

    /**
     * Muestra el submenú para búsqueda de libros.
     * @param control Instancia del controlador
     */
    private static void menuBusquedas(Control control) {
        while (true) {
            System.out.println("\n--- BÚSQUEDAS ---");
            System.out.println("1. Buscar libro por ISBN");
            System.out.println("2. Buscar libros por título");
            System.out.println("3. Buscar libros por autor");
            System.out.println("4. Verificar disponibilidad");
            System.out.println("0. Volver");
            System.out.print("Selección: ");
            
            int opcion = leerEntero();
            scanner.nextLine();
            
            switch (opcion) {
                case 1: buscarPorISBN(control); break;
                case 2: buscarPorTitulo(control); break;
                case 3: buscarPorAutor(control); break;
                case 4: verificarDisponibilidad(control); break;
                case 0: return;
                default: System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Busca un libro por su ISBN.
     * @param control Instancia del controlador
     */
    private static void buscarPorISBN(Control control) {
        System.out.println("\nBUSCAR POR ISBN");
        System.out.print("Ingrese ISBN: ");
        String isbn = scanner.nextLine();
        
        Libro libro = control.buscarLibroPorIsbn(isbn);
        if (libro != null) {
            mostrarDetallesLibro(libro);
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    /**
     * Busca libros por título.
     * @param control Instancia del controlador
     */
    private static void buscarPorTitulo(Control control) {
        System.out.println("\nBUSCAR POR TÍTULO");
        System.out.print("Ingrese título: ");
        String titulo = scanner.nextLine();
        
        String resultado = control.buscarLibrosPorTitulo(titulo);
        System.out.println(resultado.isEmpty() ? "No se encontraron libros." : resultado);
    }

    /**
     * Busca libros por autor.
     * @param control Instancia del controlador
     */
    private static void buscarPorAutor(Control control) {
        System.out.println("\nBUSCAR POR AUTOR");
        System.out.print("Ingrese autor: ");
        String autor = scanner.nextLine();
        
        String resultado = control.buscarLibrosPorAutor(autor);
        System.out.println(resultado.isEmpty() ? "No se encontraron libros." : resultado);
    }

    /**
     * Verifica la disponibilidad de un libro.
     * @param control Instancia del controlador
     */
    private static void verificarDisponibilidad(Control control) {
        System.out.println("\nVERIFICAR DISPONIBILIDAD");
        System.out.print("Ingrese ISBN: ");
        String isbn = scanner.nextLine();
        
        String disponibilidad = control.verificarDisponibilidad(isbn);
        System.out.println(disponibilidad);
    }

    /**
     * Muestra los detalles de un libro específico.
     * @param libro Libro a mostrar
     */
    private static void mostrarDetallesLibro(Libro libro) {
        System.out.println("\nDETALLES DEL LIBRO");
        System.out.println("Título: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor());
        System.out.println("Año: " + libro.getAñoPublicacion());
        System.out.println("ISBN: " + libro.getIsbn());
        System.out.println("Disponibles: " + libro.getEjemplaresDisponibles());
        System.out.println("Estado: " + (libro.getEjemplaresDisponibles() > 0 ? "Disponible" : "Agotado"));
    }

    /**
     * Lee un número entero desde la entrada estándar con validación.
     * @return Entero válido ingresado por el usuario
     */
    private static int leerEntero() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
                scanner.next();
            }
        }
    }

    /**
     * Actualiza el tipo de un usuario existente.
     * @param control Instancia del controlador
     */
    private static void actualizarTipoUsuario(Control control) {
        System.out.print("Ingrese la identificación del usuario: ");
        String identificacion = scanner.nextLine();
        
        Usuario usuario = control.buscarUsuarioPorId(identificacion);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
    
        System.out.print("Ingrese el nuevo tipo de usuario: ");
        String nuevoTipo = scanner.nextLine();
        usuario.setTipoUsuario(nuevoTipo);
        
        System.out.println("Tipo de usuario actualizado con éxito.");
    }

    /**
     * Busca un usuario por su identificación.
     * @param control Instancia del controlador
     */
    private static void buscarUsuario(Control control) {
        System.out.print("Ingrese la identificación del usuario: ");
        String identificacion = scanner.nextLine();
        
        Usuario usuario = control.buscarUsuarioPorId(identificacion);
        if (usuario != null) {
            System.out.println("Usuario encontrado: " + usuario);
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}