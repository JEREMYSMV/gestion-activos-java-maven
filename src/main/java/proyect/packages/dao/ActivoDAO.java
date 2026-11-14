package proyect.packages.dao;

import proyect.packages.config.ConexionDB;
import proyect.packages.model.Activo;
import proyect.packages.model.Empleado;
import proyect.packages.model.EstadoActivo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivoDAO {

    private static final int ID_ESTADO_DEFECTO = 1;

    public void insert(Activo activo){
        String sql = "INSERT INTO Activo (nombre, marca, nro_serie, estado, id_empleado, id_estado) VALUES (?, ?, ?, ?, ?, ?)";

        EstadoActivo estado = activo.getEstado();

        //Verificar si activo tiene en realidad un Estado, si no entonces darle por defecto el estado e "EN ALMACÉN"
        if(estado == null){
            estado = new EstadoActivoDAO().getById(ID_ESTADO_DEFECTO); //Obtenemos el valor con id 1
            activo.setEstado(estado);//Aqui le damos el valor 1 como estado - EN ALMACÉN
        }

        //Operador terniario para definir los valores de ambas variables
        int idEstado = estado != null ? estado.getIdEstado() : ID_ESTADO_DEFECTO;
        String nombreEstado = estado != null ? estado.getNombre() : null;

        try(Connection connection = ConexionDB.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);){

            ps.setString(1, activo.getNombre());
            ps.setString(2, activo.getMarca());
            ps.setString(3, activo.getNroSerie());
            ps.setString(4, nombreEstado);


            if (activo.getEmpleado() != null){
                ps.setInt(5, activo.getEmpleado().getIdEmpleado());
            }else{
                ps.setNull(5, Types.INTEGER);
            }

            ps.setInt(6, idEstado);

            ps.executeUpdate();

            System.out.println("ACTIVO " + activo.getNombre() + " REGISTRADO CON EXITO!!");

        }catch(SQLException e){
            System.err.println("Error al registrar el activo.");
            System.err.println("Detalle: " + e.getMessage() + "\n ---------------------------------------");
            e.printStackTrace();
        }

    }

    //===================================================================================================================================================================

    public List<Activo> getAll(){

        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        EstadoActivoDAO estadoActivoDAO = new EstadoActivoDAO();
        List<Activo> activos = new ArrayList<>();
        String sql = "SELECT  * FROM Activo";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){

                Activo activo = new Activo();

                activo.setIdActivo(rs.getInt("id_activo"));
                activo.setNombre(rs.getString("nombre"));
                activo.setMarca(rs.getString("marca"));
                activo.setNroSerie(rs.getString("nro_serie"));
                //activo.setEstado(rs.getString("os"));

                int idEstado = rs.getInt("id_estado");

                if(idEstado > 0){
                    EstadoActivo estadoActivo = estadoActivoDAO.getById(idEstado);
                    activo.setEstado(estadoActivo);
                }else{
                    activo.setEstado(null);
                }


                int idEmpleado = rs.getInt("id_empleado");

                if(!rs.wasNull() && idEmpleado > 0){
                    Empleado empleado = empleadoDAO.getById(idEmpleado);
                    activo.setEmpleado(empleado);
                }else {
                    activo.setEmpleado(null);
                }

                activos.add(activo);

            }

        } catch (SQLException e) {
            System.err.println("Error al intentar obtener todos los registros de activo.");
            System.err.println("Detalle: " + e.getMessage() + "\n ---------------------------------------");
            e.printStackTrace();
        }

        return activos;
    }

    //===================================================================================================================================================================

    public Activo getById(int idActivo){

        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        EstadoActivoDAO estadoActivoDAO = new EstadoActivoDAO();
        Activo activo = null;
        String sql = "SELECT * FROM Activo WHERE id_activo = ?";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, idActivo);

            try(ResultSet rs = ps.executeQuery()){

                if(rs.next()){
                    activo = new Activo();

                    activo.setIdActivo(rs.getInt("id_activo"));
                    activo.setNombre(rs.getString("nombre"));
                    activo.setMarca(rs.getString("marca"));
                    activo.setNroSerie(rs.getString("nro_serie"));

                    int idEstado = rs.getInt("id_estado");

                    if(idEstado > 0){
                        EstadoActivo estadoActivo = estadoActivoDAO.getById(idEstado);
                        activo.setEstado(estadoActivo);
                    }

                    int idEmpleado = rs.getInt("id_empleado");

                    if(!rs.wasNull() && idEmpleado > 0){
                        Empleado empleado = empleadoDAO.getById(idEmpleado);
                        activo.setEmpleado(empleado);
                    }else {
                        activo.setEmpleado(null);
                    }

                }

            }

        }catch(SQLException e){

            System.err.println("Error al intentar obtener el activo con ID: " + idActivo);
            System.err.println("Detalle: " + e.getMessage() + "\n ---------------------------------------");
            e.printStackTrace();

        }

        return activo;
    }

    //===================================================================================================================================================================

    public void update(Activo activo){

        String sql = "UPDATE Activo SET nombre = ?, marca= ?, nro_serie = ?, estado = ?, id_empleado = ?, id_estado = ? WHERE id_activo = ?";

        EstadoActivo estado = activo.getEstado();

        if(estado == null){
            estado = new EstadoActivoDAO().getById(ID_ESTADO_DEFECTO);
            activo.setEstado(estado);
        }

        int idEstado = estado != null ? estado.getIdEstado() : ID_ESTADO_DEFECTO;
        String nombreEstado = estado != null ? estado.getNombre() : null;


        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, activo.getNombre());
            ps.setString(2, activo.getMarca());
            ps.setString(3, activo.getNroSerie());
            ps.setString(4, nombreEstado);

            if(activo.getEmpleado() != null){
                ps.setInt(5, activo.getEmpleado().getIdEmpleado());
            }else{
                ps.setNull(5, Types.INTEGER);
            }

            ps.setInt(6, idEstado);

            ps.setInt(7, activo.getIdActivo());

            ps.executeUpdate();

            System.out.println("ACTIVO ACTUALIZADO!!");

        }catch(SQLException e){
            System.err.println("Error al intentar actualizar el activo.");
            System.err.println("Detalle: " + e.getMessage() + "\n ---------------------------------------");
            e.printStackTrace();
        }

    }

    //===================================================================================================================================================================

    public void delete(int idActivo){

        String sql = "UPDATE Activo SET estado = ?, id_estado = ? WHERE id_activo = ?";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            EstadoActivoDAO estadoActivoDAO = new EstadoActivoDAO();

            EstadoActivo estadoActivo = estadoActivoDAO.getById(5);

            ps.setString(1, estadoActivo.getNombre());
            ps.setInt(2, estadoActivo.getIdEstado());
            ps.setInt(3, idActivo);

            ps.executeUpdate();

            System.out.println("ACTIVO DADO DE BAJA");

        }catch(SQLException e){
            System.err.println("Error al registrar el activo con ID: " + idActivo);
            System.err.println("Detalle: " + e.getMessage() + "\n ---------------------------------------");
            e.printStackTrace();
        }

    }

}

