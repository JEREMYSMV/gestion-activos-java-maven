package proyect.packages.dao;

import proyect.packages.config.ConexionDB;
import proyect.packages.model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    // CREACION DE LOS EMPLEADOS
    public int crear(Empleado empleado) {

        String sql = "INSERT INTO Empleados (nombre, area, sueldo, estado) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getArea());
            ps.setDouble(3, empleado.getSueldo());
            ps.setString(4, empleado.getEstado());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("Error al crear empleado: " + e.getMessage());
        }

        return -1;
    }

    // LEER LOS DATOS DE LOS EMPLEADOS
    public List<Empleado> listar() {

        List<Empleado> empleados = new ArrayList<>();

        String sql = "SELECT id_empleado, nombre, area, sueldo, estado FROM Empleados";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Empleado empleado = new Empleado();

                empleado.setIdEmpleado(rs.getInt("id_empleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setArea(rs.getString("area"));
                empleado.setSueldo(rs.getDouble("sueldo"));
                empleado.setEstado(rs.getString("estado"));

                empleados.add(empleado);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar empleados: " + e.getMessage());
        }

        return empleados;
    }
    public Empleado getById(int idEmpleado) {

        String sql = "SELECT id_empleado, nombre, area, sueldo, estado " +
                "FROM Empleados WHERE id_empleado = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpleado);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Empleado empleado = new Empleado();

                    empleado.setIdEmpleado(rs.getInt("id_empleado"));
                    empleado.setNombre(rs.getString("nombre"));
                    empleado.setArea(rs.getString("area"));
                    empleado.setSueldo(rs.getDouble("sueldo"));
                    empleado.setEstado(rs.getString("estado"));

                    return empleado;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar empleado: " + e.getMessage());
        }

        return null;
    }

    // ACTUALIZACION DEL SUELDO
    public void actualizarSueldo(int idEmpleado, double nuevoSueldo) {

        String sql = "UPDATE Empleados SET sueldo = ? WHERE id_empleado = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, nuevoSueldo);
            ps.setInt(2, idEmpleado);

            ps.executeUpdate();

            System.out.println("Sueldo actualizado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al actualizar sueldo: " + e.getMessage());
        }
    }

    // ACTUALIZACION DEL ESTADO
    public void actualizarEstado(int idEmpleado, String nuevoEstado) {

        String sql = "UPDATE Empleados SET estado = ? WHERE id_empleado = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setInt(2, idEmpleado);

            ps.executeUpdate();

            System.out.println("Estado actualizado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al actualizar estado: " + e.getMessage());
        }
    }

    // ELIMINAR EMPLEADO
    public void eliminar(int idEmpleado) {

        String sql = "DELETE FROM Empleados WHERE id_empleado = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpleado);

            ps.executeUpdate();

            System.out.println("Empleado eliminado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al eliminar empleado: " + e.getMessage());
        }
    }
}