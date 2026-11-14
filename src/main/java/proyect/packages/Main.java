package proyect.packages;

import proyect.packages.dao.ActivoDAO;
import proyect.packages.dao.EstadoActivoDAO;
import proyect.packages.model.Activo;
import proyect.packages.model.EstadoActivo;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        EstadoActivoDAO estadoDAO = new EstadoActivoDAO();
        ActivoDAO activoDAO = new ActivoDAO();

        // 1. Crear los estados
        EstadoActivo estado1 = new EstadoActivo(0, "En Almacén", "El activo se encuentra operativo y en el almacén."
        );

        EstadoActivo estado2 = new EstadoActivo(0, "Asignado", "El activo se encuentra asignado a un colaborador."
        );

        EstadoActivo estado3 = new EstadoActivo(0, "En Mantenimiento", "El activo se encuentra en mantenimiento preventivo o correctivo."
        );

        EstadoActivo estado4 = new EstadoActivo(0, "Retirado", "El activo ya cumplió con su ciclo de vida y se encuentra en el almacén."
        );

        EstadoActivo estado5 = new EstadoActivo(0, "De Baja", "El activo ya no forma parte de la organización."
        );

        estadoDAO.crear(estado1);
        estadoDAO.crear(estado2);
        estadoDAO.crear(estado3);
        estadoDAO.crear(estado4);
        estadoDAO.crear(estado5);


        // 2. Mostrar los estados
        System.out.println("\n===== ESTADOS =====");

        List<EstadoActivo> estados = estadoDAO.listar();

        for (EstadoActivo estado : estados) {
            System.out.println(
                    "ID: " + estado.getIdEstado()
                            + " | Estado: " + estado.getNombre()
                            + " | Descripcion: " + estado.getDescripcion()
            );
        }


        // Crear 2 activos para hacer la prueba
        Activo activo1 = new Activo();
        activo1.setNombre("Laptop");
        activo1.setMarca("Lenovo");
        activo1.setNroSerie("001");
        activo1.setEstado(null);
        activo1.setEmpleado(null);

        Activo activo2 = new Activo();
        activo2.setNombre("Monitor");
        activo2.setMarca("Samsung");
        activo2.setNroSerie("002");
        activo2.setEstado(null);
        activo2.setEmpleado(null);

        activoDAO.insert(activo1);
        activoDAO.insert(activo2);


        // 3. Mostrar los activos con su estado
        System.out.println("\n===== ACTIVOS REGISTRADOS =====");

        List<Activo> activos = activoDAO.getAll();

        for (Activo activo : activos) {
            System.out.println(activo.informacion());
            System.out.println("-------------------------");
        }


        // 4. Cambiar el estado de 2 activos
        if (activos.size() >= 2) {

            EstadoActivo asignado = estadoDAO.getById(2);
            EstadoActivo mantenimiento = estadoDAO.getById(3);

            Activo primerActivo = activos.get(0);
            Activo segundoActivo = activos.get(1);

            primerActivo.setEstado(asignado);
            segundoActivo.setEstado(mantenimiento);

            activoDAO.update(primerActivo);
            activoDAO.update(segundoActivo);
        }


        // Mostrar los activos actualizados
        System.out.println("\n===== ACTIVOS ACTUALIZADOS =====");

        activos = activoDAO.getAll();

        for (Activo activo : activos) {
            System.out.println(activo.informacion());
            System.out.println("-------------------------");
        }
    }
}