<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Formulário de Ordem</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 50px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>${order.id == null ? 'Adicionar Novo Ordem' : 'Editar Ordem'}</h2>
    <form action="${order == null ? 'add-order' : 'update-order'}" method="post">
        <input type="hidden" name="id" value="${order.id}" />
        <input type="hidden" name="action" value="${order.id == null ? 'insert' : 'update'}" />
        <div class="form-group">
            <label for="purchaseAmount">Total da compra</label>
            <input type="number" class="form-control" id="purchaseAmount" name="purchaseAmount" value="${order.purchaseAmount}" required />
        </div>
        <c:if test="${order.id != null}">
        <div class="form-group">
            <label for="createdAt">Data de criação</label>
            <input type="text" class="form-control" id="createdAt" name="createdAt" value="${order.createdAt}" readonly/>
        </div>
        </c:if>
        <div class="form-group">
            <label for="salesmanId">ID do vendedor</label>
            <input type="text" class="form-control" id="salesmanId" name="salesmanId" value="${order.salesmanId}" required />
        </div>
        <div class="form-group">
            <label for="customerId">ID do cliente</label>
            <input type="number" class="form-control" id="customerId" name="customerId" value="${order.customerId}" required />
        </div>
        <button type="submit" class="btn btn-primary">Salvar</button>
        <a href="${pageContext.request.contextPath}/orders/" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
