package proyect.packages.model;

public class Activo {
    private int idActivo;
    private String nombre;
    private String marca;
    private String nroSerie;
    private EstadoActivo estado;
    private Empleado empleado;

    public Activo() {
    }

    public Activo(int idActivo, String nombre, String marca, String nroSerie, EstadoActivo estado, Empleado empleado) {
        this.idActivo = idActivo;
        this.nombre = nombre;
        this.marca = marca;
        this.nroSerie = nroSerie;
        this.estado = estado;
        this.empleado = empleado;
    }

    public int getIdActivo() {
        return idActivo;
    }

    public void setIdActivo(int idActivo) {
        this.idActivo = idActivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    public EstadoActivo getEstado() {
        return estado;
    }

    public void setEstado(EstadoActivo estado) {
        this.estado = estado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String informacion() {
        String informacion;

        informacion = "ID: " + idActivo + "\n" +
                "Nombre: " + nombre + "\n" +
                "Marca: " + marca + "\n" +
                "NroSerie: " + nroSerie + "\n" +
                "Estado: " + estado.getNombre() + "\n" +
                "Descripción del Estado: " + estado.getDescripcion() + "\n" +
                "Empleado: " + (empleado != null ? empleado.getNombre() : "NO ASIGNADO");

        return  informacion;
    }
}