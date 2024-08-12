<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listagem de vendedores</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 50px;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .header h2 {
            margin: 0;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h2>Listagem de vendedores</h2>
        <a href="${pageContext.request.contextPath}" class="btn btn-secondary">Voltar ao início</a>
        <a href="${pageContext.request.contextPath}/salesmen/new" class="btn btn-primary">Adicionar novo vendedor</a>
    </div>
    <c:choose>
        <c:when test="${not empty salesmen}">
            <table class="table table-striped table-hover mt-3">
                <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Cidade</th>
                    <th>Comissão</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="salesman" items="${salesmen}">
                    <tr>
                        <td>${salesman.id}</td>
                        <td>${salesman.name}</td>
                        <td>${salesman.city}</td>
                        <td>${salesman.commission}%</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/salesmen/edit?id=${salesman.id}" class="btn btn-sm btn-warning">Editar</a>
                            <a href="${pageContext.request.contextPath}/salesmen/delete?id=${salesman.id}" class="btn btn-sm btn-danger" onclick="return confirm('Você tem certeza?')">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info mt-3" role="alert">
                Nenhum vendedor disponível. Clique em "adicionar novo vendedor" para criar um.
            </div>
        </c:otherwise>
    </c:choose>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
