<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${fermier == null ? 'Ajouter' : 'Modifier'} un Fermier</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>${fermier == null ? 'Ajouter un' : 'Modifier le'} Fermier</h2>

    <form action="fermiers" method="post">
        <input type="hidden" name="action" value="${fermier == null ? 'insert' : 'update'}">

        <c:if test="${fermier != null}">
            <input type="hidden" name="id" value="${fermier.id}">
        </c:if>

        <div class="mb-3">
            <label for="nom" class="form-label">Nom</label>
            <input type="text" class="form-control" id="nom" name="nom"
                   value="${fermier != null ? fermier.nom : ''}" required>
        </div>

        <button type="submit" class="btn btn-primary">${fermier == null ? 'Ajouter' : 'Modifier'}</button>
        <a href="fermiers" class="btn btn-secondary">Annuler</a>
    </form>
</div>
</body>
</html>