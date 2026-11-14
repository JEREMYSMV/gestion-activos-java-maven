package proyect.packages.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyect.packages.dao.ActivoDAO;
import proyect.packages.model.Activo;

import java.io.IOException;
import java.util.List;

@WebServlet("/activos")
public class ActivoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ActivoDAO activoDAO = new ActivoDAO();
        List<Activo> activos = activoDAO.getAll();

        req.setAttribute("activos", activos);
        req.getRequestDispatcher("/activos.jsp").forward(req, resp);

    }
}
