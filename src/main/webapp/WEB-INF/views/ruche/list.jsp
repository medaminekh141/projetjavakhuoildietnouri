<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Ruches</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Liste des Ruches</h2>
    <a href="ruches?action=new" class="btn btn-primary mb-3">Ajouter une Ruche</a>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Code</th>
            <th>Ferme ID</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ruche" items="${ruches}">
            <tr>
                <td>${ruche.id}</td>
                <td>${ruche.code}</td>
                <td>${ruche.fermeId}</td>
                <td>
                    <a href="ruches?action=edit&id=${ruche.id}" class="btn btn-sm btn-warning">Modifier</a>
                    <a href="ruches?action=delete&id=${ruche.id}" class="btn btn-sm btn-danger"
                       onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette ruche?')">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>