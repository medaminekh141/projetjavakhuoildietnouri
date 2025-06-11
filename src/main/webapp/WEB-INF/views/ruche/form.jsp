<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${ruche == null ? 'Ajouter' : 'Modifier'} une Ruche</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>${ruche == null ? 'Ajouter une' : 'Modifier la'} Ruche</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="ruches" method="post">
        <input type="hidden" name="action" value="${ruche == null ? 'insert' : 'update'}">

        <c:if test="${ruche != null}">
            <input type="hidden" name="id" value="${ruche.id}">
        </c:if>

        <div class="mb-3">
            <label for="code" class="form-label">Code</label>
            <input type="text" class="form-control" id="code" name="code"
                   value="${ruche != null ? ruche.code : ''}" required>
        </div>

        <div class="mb-3">
            <label for="fermeId" class="form-label">Ferme</label>
            <select class="form-select" id="fermeId" name="fermeId" required>
                <option value="">-- Sélectionnez une ferme --</option>
                <c:forEach var="ferme" items="${fermes}">
                    <option value="${ferme.id}" ${ruche != null && ruche.fermeId == ferme.id ? 'selected' : ''}>
                            ${ferme.localisation} (ID: ${ferme.id})
                    </option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">${ruche == null ? 'Ajouter' : 'Modifier'}</button>
        <a href="ruches" class="btn btn-secondary">Annuler</a>
    </form>
</div>
</body>
</html>