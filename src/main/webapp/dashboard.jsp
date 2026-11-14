
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard | Gestión de Activos</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            margin: 0;
            background: #f3f6fb;
            font-family: 'Segoe UI', Arial, sans-serif;
            color: #263449;
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

        .welcome {
            background: linear-gradient(120deg, #172b50, #2563eb);
            border-radius: 18px;
            padding: 40px;
            color: white;
            margin-bottom: 35px;
        }

        .welcome h1 {
            font-weight: 700;
        }

        .module-card {
            background: white;
            border-radius: 16px;
            padding: 30px;
            box-shadow: 0 8px 25px rgba(0,0,0,0.05);
            transition: 0.3s;
            height: 100%;
        }

        .module-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 12px 30px rgba(0,0,0,0.1);
        }

        .module-icon {
            background: #e8f0ff;
            color: #2563eb;
            width: 60px;
            height: 60px;
            border-radius: 14px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 28px;
            margin-bottom: 20px;
        }

        .btn-primary {
            background: #2563eb;
            border: none;
            border-radius: 9px;
            padding: 12px 25px;
            font-weight: 600;
        }

        .btn-primary:hover {
            background: #1d4ed8;
        }

        .footer {
            margin-top: 50px;
            color: #94a3b8;
            font-size: 14px;
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

            .welcome {
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

            <a href="${pageContext.request.contextPath}/dashboard.jsp"
               class="active">
                Panel Principal
            </a>

            <a href="${pageContext.request.contextPath}/activos">
                Gestión de Activos
            </a>
        </aside>

        <main class="col-md-9 col-lg-10 main-content">

            <div class="welcome">
                <h1>Panel Principal</h1>
                <p class="mb-0">
                    Bienvenido al Sistema de Gestión de Activos.
                    Administra y consulta los activos registrados
                    en la base de datos.
                </p>
            </div>

            <h4 class="fw-bold mb-4">Módulos del Sistema</h4>

            <div class="row">
                <div class="col-md-6 col-lg-5">

                    <div class="module-card">

                        <div class="module-icon">
                            &#128187;
                        </div>

                        <h4 class="fw-bold">
                            Gestión de Activos
                        </h4>

                        <p class="text-secondary">
                            Consulta el listado de activos,
                            sus características, estados
                            y empleados asignados.
                        </p>

                        <a href="${pageContext.request.contextPath}/activos"
                           class="text-decoration-none">
                            <button type="button"
                                    class="btn btn-primary">
                                Ver Activos
                            </button>
                        </a>

                    </div>
                </div>
            </div>

            <div class="footer">
                Sistema de Gestión de Activos | ISIL 2026
            </div>

        </main>

    </div>
</div>

</body>
</html>
