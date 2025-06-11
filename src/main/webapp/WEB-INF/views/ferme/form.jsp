<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${ferme == null ? 'Ajouter' : 'Modifier'} une Ferme</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    ...
</div>
<div class="container mt-4">
    <h2>${ferme == null ? 'Ajouter une' : 'Modifier la'} Ferme</h2>

    <form action="fermes" method="post">
        <input type="hidden" name="action" value="${ferme == null ? 'insert' : 'update'}">

        <c:if test="${ferme != null}">
            <input type="hidden" name="id" value="${ferme.id}">
        </c:if>

        <div class="mb-3">
            <label for="localisation" class="form-label">Localisation</label>
            <input type="text" class="form-control" id="localisation" name="localisation"
                   value="${ferme != null ? ferme.localisation : ''}" required>
        </div>

        <div class="mb-3">
            <label for="fermierId" class="form-label">ID Fermier</label>
            <input type="number" class="form-control" id="fermierId" name="fermierId"
                   value="${ferme != null ? ferme.fermierId : ''}" required>
        </div>

        <button type="submit" class="btn btn-primary">${ferme == null ? 'Ajouter' : 'Modifier'}</button>
        <a href="fermes" class="btn btn-secondary">Annuler</a>
    </form>
</div>
</body>
</html>