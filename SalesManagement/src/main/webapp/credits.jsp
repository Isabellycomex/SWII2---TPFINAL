<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Credits</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 50px;
        }
        .credits {
            text-align: center;
        }
        .credits h2 {
            margin-bottom: 30px;
        }
        .credits p {
            font-size: 1.2em;
            line-height: 1.5em;
        }
        .back-button {
            margin-top: 30px;
        }
    </style>
</head>
<body>
<div class="container credits">
    <h2>Créditos</h2>
    <p>Isabelly Barbosa Gonçalves CB3021467</p>
    <p>Lucas Aragão Almeida CB3013146</p>
    <div class="back-button">
        <a href="${pageContext.request.contextPath}/" class="btn btn-primary btn-lg">Voltar a menu principal</a>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
