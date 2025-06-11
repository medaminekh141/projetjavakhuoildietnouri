<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <title>Liste des Fermes</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <h2>Liste des Fermes</h2>
  <a href="fermes?action=new" class="btn btn-primary mb-3">Ajouter une Ferme</a>

  <table class="table table-striped">
    <thead>
    <tr>
      <th>ID</th>
      <th>Localisation</th>
      <th>ID Fermier</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="ferme" items="${fermes}">
      <tr>
        <td>${ferme.id}</td>
        <td>${ferme.localisation}</td>
        <td>${ferme.fermierId}</td>
        <td>
          <a href="fermes?action=edit&id=${ferme.id}" class="btn btn-sm btn-warning">Modifier</a>
          <a href="fermes?action=delete&id=${ferme.id}" class="btn btn-sm btn-danger" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette ferme?')">Supprimer</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>