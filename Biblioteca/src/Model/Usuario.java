package Model;

/**
 * Clase que representa un usuario del sistema de biblioteca.
 * Contiene información personal del usuario, su tipo y multas acumuladas.
 */
public class Usuario {
    
    private String nombre;
    private String identificacion;
    private String tipoUsuario; // estudiante, profesor, ciudadano, administrador
    private double multaAcumulada;

    /**
     * Constructor para crear un nuevo usuario.
     * @param nombre Nombre completo del usuario (no puede ser nulo o vacío)
     * @param identificacion Identificación única del usuario (no puede ser nulo o vacío)
     * @param tipoUsuario Tipo de usuario (estudiante, profesor, ciudadano o administrador)
     */
    public Usuario(String nombre, String identificacion, String tipoUsuario) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.tipoUsuario = tipoUsuario;
        this.multaAcumulada = 0.0;
    }

    /**
     * Verifica si el usuario tiene privilegios de administrador.
     * @return true si el usuario es administrador, false en caso contrario
     */
    public boolean esAdmin() {
        return "Administrador".equalsIgnoreCase(this.tipoUsuario);
    }
    
    /**
     * Obtiene el nombre del usuario.
     * @return Nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * @param nombre Nuevo nombre (no puede ser nulo o vacío)
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la identificación del usuario.
     * @return Identificación única del usuario
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * Establece la identificación del usuario.
     * @param identificacion Nueva identificación (no puede ser nulo o vacío)
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * Obtiene el tipo de usuario.
     * @return Tipo de usuario (estudiante, profesor, ciudadano o administrador)
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Establece el tipo de usuario.
     * @param tipoUsuario Nuevo tipo de usuario (debe ser uno de los valores permitidos)
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Agrega una multa al acumulado del usuario.
     * @param monto Monto de la multa a agregar (debe ser positivo)
     */
    public void agregarMulta(double monto) {
        this.multaAcumulada += monto;
    }

    /**
     * Obtiene el total de multas acumuladas.
     * @return Monto total de multas pendientes
     */
    public double getMultaAcumulada() {
        return multaAcumulada;
    }

    /**
     * Registra un pago hacia las multas acumuladas.
     * @param monto Monto a pagar (debe ser positivo)
     */
    public void pagarMulta(double monto) {
        this.multaAcumulada = Math.max(0, this.multaAcumulada - monto);
    }

    /**
     * Devuelve una representación en cadena del usuario.
     * @return Cadena con la información básica del usuario
     */
    @Override
    public String toString() {
        return "El usuario es, nombre:" + nombre + 
               ", identificacion:" + identificacion + 
               ", tipo de usuario:" + tipoUsuario;
    }
}