
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="proyect.packages.model.Activo" %>

<%
    List<Activo> activos = (List<Activo>) request.getAttribute("activos");
    int total = activos != null ? activos.size() : 0;
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Activos | ActivoSystem</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: #f3f6fb;
            font-family: 'Segoe UI', Arial, sans-serif;
            color: #263449;
            margin: 0;
        }

        .sidebar {
            background: #111c35;
            min-height: 100vh;
            padding: 30px 20px;
            color: white;
        }

        .sidebar h3 {
            font-weight: 700;
            margin-bottom: 45px;
        }

        .sidebar a {
            display: block;
            color: #cbd5e1;
            text-decoration: none;
            padding: 14px 18px;
            border-radius: 10px;
            margin-bottom: 12px;
            transition: 0.3s;
        }

        .sidebar a:hover,
        .sidebar a.active {
            background: #2563eb;
            color: white;
        }

        .main-content {
            padding: 40px;
        }

        .page-header {
            background: linear-gradient(120deg, #172b50, #2563eb);
            color: white;
            padding: 35px;
            border-radius: 18px;
            margin-bottom: 30px;
        }

        .page-header h1 {
            font-weight: 700;
        }

        .stat-card {
            background: white;
            border-radius: 15px;
            padding: 25px;
            margin-bottom: 25px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.04);
        }

        .stat-number {
            color: #2563eb;
            font-size: 32px;
            font-weight: 700;
        }

        .table-card {
            background: white;
            border-radius: 16px;
            padding: 25px;
            box-shadow: 0 8px 25px rgba(0,0,0,0.05);
        }

        .search-input {
            border: 1px solid #dbe2ec;
            border-radius: 10px;
            padding: 12px 16px;
            width: 100%;
            max-width: 350px;
        }

        .search-input:focus {
            outline: none;
            border-color: #2563eb;
            box-shadow: 0 0 0 3px #dbeafe;
        }

        .table {
            margin-top: 20px;
            vertical-align: middle;
        }

        .table thead th {
            background: #edf2fa;
            color: #334155;
            padding: 16px;
            border: none;
            font-weight: 600;
            white-space: nowrap;
        }

        .table tbody td {
            padding: 16px;
            border-color: #edf2f7;
        }

        .table tbody tr:hover td {
            background: #f8faff;
        }

        .estado {
            padding: 7px 13px;
            border-radius: 20px;
            font-size: 12px;
            font-weight: 600;
            display: inline-block;
        }

        .estado-activo {
            background: #dcfce7;
            color: #166534;
        }

        .estado-inactivo {
            background: #fee2e2;
            color: #991b1b;
        }

        .estado-mantenimiento {
            background: #fef3c7;
            color: #92400e;
        }

        .estado-otro {
            background: #e2e8f0;
            color: #475569;
        }

        .btn-volver {
            background: white;
            color: #2563eb;
            border-radius: 9px;
            padding: 10px 20px;
            text-decoration: none;
            font-weight: 600;
            display: inline-block;
        }

        .btn-volver:hover {
            background: #e8f0ff;
        }

        @media (max-width: 767px) {
            .sidebar {
                min-height: auto;
                padding: 20px;
            }

            .sidebar h3 {
                margin-bottom: 20px;
            }

            .main-content {
                padding: 20px;
            }

            .page-header {
                padding: 25px;
            }
        }
    </style>
</head>

<body>
<div class="container-fluid">
    <div class="row">

        <aside class="col-md-3 col-lg-2 sidebar">
            <h3>ActivoSystem</h3>

            <a href="${pageContext.request.contextPath}/dashboard.jsp">
                Panel Principal
            </a>

            <a href="${pageContext.request.contextPath}/activos"
               class="active">
                Gestión de Activos
            </a>
        </aside>

        <main class="col-md-9 col-lg-10 main-content">

            <div class="page-header">
                <h1>Gestión de Activos</h1>
                <p>
                    Consulta y visualiza los activos
                    registrados en el sistema.
                </p>

                <a href="${pageContext.request.contextPath}/dashboard.jsp"
                   class="btn-volver">
                    Volver al Dashboard
                </a>
            </div>

            <div class="stat-card">
                <p class="text-secondary mb-1">
                    Total de activos registrados
                </p>

                <div class="stat-number">
                    <%= total %>
                </div>
            </div>

            <div class="table-card">

                <div class="d-flex flex-wrap justify-content-between align-items-center gap-3">
                    <h4 class="fw-bold mb-0">
                        Listado de Activos
                    </h4>

                    <input type="text"
                           id="buscador"
                           class="search-input"
                           placeholder="Buscar activo..."
                           onkeyup="buscarActivos()">
                </div>

                <div class="table-responsive">
                    <table class="table" id="tablaActivos">

                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Marca</th>
                                <th>N. Serie</th>
                                <th>Estado</th>
                                <th>Empleado</th>
                            </tr>
                        </thead>

                        <tbody>
                        <%
                            if (activos != null && !activos.isEmpty()) {
                                for (Activo emp : activos) {

                                    String estado = emp.getEstado() != null
                                        ? emp.getEstado().getNombre()
                                        : "Sin estado";

                                    if (estado == null) {
                                        estado = "Sin estado";
                                    }

                                    String claseEstado = "estado-otro";
                                    String estadoNormalizado = estado.toLowerCase().trim();

                                    if (estadoNormalizado.equals("activo")
                                            || estadoNormalizado.equals("disponible")) {
                                        claseEstado = "estado-activo";
                                    } else if (estadoNormalizado.equals("inactivo")
                                            || estadoNormalizado.equals("baja")) {
                                        claseEstado = "estado-inactivo";
                                    } else if (estadoNormalizado.contains("mantenimiento")
                                            || estadoNormalizado.contains("reparacion")
                                            || estadoNormalizado.contains("reparación")) {
                                        claseEstado = "estado-mantenimiento";
                                    }
                        %>

                            <tr>
                                <td><%= emp.getIdActivo() %></td>

                                <td class="fw-semibold">
                                    <%= emp.getNombre() %>
                                </td>

                                <td><%= emp.getMarca() %></td>

                                <td><%= emp.getNroSerie() %></td>

                                <td>
                                    <span class="estado <%= claseEstado %>">
                                        <%= estado %>
                                    </span>
                                </td>

                                <td>
                                    <%= emp.getEmpleado() != null
                                        ? emp.getEmpleado().getNombre()
                                        : "No asignado" %>
                                </td>
                            </tr>

                        <%
                                }
                            } else {
                        %>

                            <tr>
                                <td colspan="6"
                                    class="text-center text-secondary py-4">
                                    No hay activos registrados.
                                </td>
                            </tr>

                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>

                <p id="sinResultados"
                   class="text-center text-secondary mt-3"
                   style="display:none;">
                    No se encontraron resultados.
                </p>

            </div>

            <p class="text-center text-secondary mt-4">
                Sistema de Gestión de Activos | ISIL 2026
            </p>

        </main>
    </div>
</div>

<script>
    function buscarActivos() {
        const texto = document.getElementById("buscador")
            .value.toLowerCase();

        const filas = document.querySelectorAll(
            "#tablaActivos tbody tr"
        );

        let encontrados = 0;

        filas.forEach(fila => {
            const contenido = fila.textContent.toLowerCase();
            const coincide = contenido.includes(texto);

            fila.style.display = coincide ? "" : "none";

            if (coincide) {
                encontrados++;
            }
        });

        document.getElementById("sinResultados").style.display =
            encontrados === 0 && texto.length > 0
                ? "block"
                : "none";
    }
</script>

</body>
</html>
