package proyect.packages.model;

public class EstadoActivo {

    private int idEstado;
    private String nombre;
    private String descripcion;

    // CONSTRUCTOR VACÍO
    public EstadoActivo() {
    }

    // CONSTRUCTOR CON PARÁMETROS
    public EstadoActivo(int idEstado, String nombre, String descripcion) {
        this.idEstado = idEstado;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // GETTER Y SETTER DE ID
    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    // GETTER Y SETTER DE NOMBRE
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // GETTER Y SETTER DE DESCRIPCIÓN
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // MUESTRA LA INFORMACIÓN DEL ESTADO COMO TEXTO
    @Override
    public String toString() {
        return "EstadoActivo(" +
                "IdEstado = " + idEstado +
                ", Nombre = '" + nombre + '\'' +
                ", Descripcion = '" + descripcion + '\'' +
                ')';
    }
}