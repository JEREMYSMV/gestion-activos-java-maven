package proyect.packages.dao;

import proyect.packages.config.ConexionDB;
import proyect.packages.model.EstadoActivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoActivoDAO {

    public boolean crear(EstadoActivo estado) {
        if (estado == null || estado.getNombre() == null
                || estado.getNombre().trim().isEmpty()) {
            System.out.println("No se pudo crear: el nombre es obligatorio.");
            return false;
        }

        String sql = "INSERT INTO Estados_Activo (nombre, descripcion) VALUES (?, ?)";

        try (Connection con = ConexionDB.getConnection()) {
            if (con == null) {
                System.out.println("Error: no hay conexion a la base de datos.");
                return false;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, estado.getNombre().trim());
                ps.setString(2, estado.getDescripcion());

                boolean resultado = ps.executeUpdate() > 0;
                System.out.println(resultado
                        ? "Estado creado correctamente."
                        : "No se pudo crear el estado.");

                return resultado;
            }

        } catch (SQLException e) {
            System.err.println("Error al crear el estado: " + e.getMessage());
            return false;
        }
    }

    public List<EstadoActivo> listar() {
        List<EstadoActivo> estados = new ArrayList<>();

        String sql = "SELECT id_estado, nombre, descripcion FROM Estados_Activo ORDER BY id_estado";

        try (Connection con = ConexionDB.getConnection()) {
            if (con == null) {
                System.out.println("Error: no hay conexion a la base de datos.");
                return estados;
            }

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    EstadoActivo estado = new EstadoActivo(
                            rs.getInt("id_estado"),
                            rs.getString("nombre"),
                            rs.getString("descripcion")
                    );

                    estados.add(estado);
                }

                System.out.println("Estados consultados: " + estados.size());
            }

        } catch (SQLException e) {
            System.err.println("Error al listar estados: " + e.getMessage());
        }

        return estados;
    }

    public EstadoActivo getById(int idEstado) {
        String sql = "SELECT id_estado, nombre, descripcion FROM Estados_Activo WHERE id_estado = ?";

        try (Connection con = ConexionDB.getConnection()) {
            if (con == null) {
                System.out.println("Error: no hay conexion a la base de datos.");
                return null;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idEstado);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        EstadoActivo estado = new EstadoActivo(
                                rs.getInt("id_estado"),
                                rs.getString("nombre"),
                                rs.getString("descripcion")
                        );

                        System.out.println("Estado encontrado: " + estado.getNombre());
                        return estado;
                    } else {
                        System.out.println("No se encontro el estado con ID: " + idEstado);
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar el estado: " + e.getMessage());
        }

        return null;
    }

    public boolean actualizar(EstadoActivo estado) {
        if (estado == null || estado.getIdEstado() <= 0
                || estado.getNombre() == null
                || estado.getNombre().trim().isEmpty()) {
            System.out.println("No se pudo actualizar: datos no validos.");
            return false;
        }

        String sql = "UPDATE Estados_Activo SET nombre = ?, descripcion = ? WHERE id_estado = ?";

        try (Connection con = ConexionDB.getConnection()) {
            if (con == null) {
                System.out.println("Error: no hay conexion a la base de datos.");
                return false;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, estado.getNombre().trim());
                ps.setString(2, estado.getDescripcion());
                ps.setInt(3, estado.getIdEstado());

                boolean resultado = ps.executeUpdate() > 0;
                System.out.println(resultado
                        ? "Estado actualizado correctamente."
                        : "No se encontro el estado para actualizar.");

                return resultado;
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar el estado: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idEstado) {
        String sql = "DELETE FROM Estados_Activo WHERE id_estado = ?";

        try (Connection con = ConexionDB.getConnection()) {
            if (con == null) {
                System.out.println("Error: no hay conexion a la base de datos.");
                return false;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idEstado);

                boolean resultado = ps.executeUpdate() > 0;
                System.out.println(resultado
                        ? "Estado eliminado correctamente."
                        : "No se encontro el estado para eliminar.");

                return resultado;
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar el estado: " + e.getMessage());
            return false;
        }
    }
}