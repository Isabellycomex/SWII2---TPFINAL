<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formulário de Vendedor</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 50px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>${salesman.id == null ? 'Adicionar Novo Vendedor' : 'Editar Vendedor'}</h2>
    <form action="${salesman == null ? 'add-salesman' : 'update-salesman'}" method="post">
        <input type="hidden" name="id" value="${salesman.id}" />
        <input type="hidden" name="action" value="${salesman.id == null ? 'insert' : 'update'}" />
        <div class="form-group">
            <label for="name">Nome</label>
            <input type="text" class="form-control" id="name" name="name" value="${salesman.name}" required />
        </div>
        <div class="form-group">
            <label for="city">Cidade</label>
            <input type="text" class="form-control" id="city" name="city" value="${salesman.city}" required />
        </div>
        <div class="form-group">
            <label for="commission">Comissão</label>
            <input type="number" class="form-control" id="commission" name="commission" value="${salesman.commission}" required />
        </div>
        <button type="submit" class="btn btn-primary">Salvar</button>
        <a href="${pageContext.request.contextPath}/salesmen/" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
