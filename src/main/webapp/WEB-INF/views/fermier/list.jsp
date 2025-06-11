<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Fermiers</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Liste des Fermiers</h2>
    <a href="fermiers?action=new" class="btn btn-primary mb-3">Ajouter un Fermier</a>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="fermier" items="${fermiers}">
            <tr>
                <td>${fermier.id}</td>
                <td>${fermier.nom}</td>
                <td>
                    <a href="fermiers?action=edit&id=${fermier.id}" class="btn btn-sm btn-warning">Modifier</a>
                    <a href="fermiers?action=delete&id=${fermier.id}" class="btn btn-sm btn-danger"
                       onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce fermier?')">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>