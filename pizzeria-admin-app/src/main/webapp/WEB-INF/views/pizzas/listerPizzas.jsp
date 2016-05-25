<%@page import="fr.pizzeria.model.Pizza"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des pizzas</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Liste des pizzas</h1>
		
		<!-- ${pageContext.request.contextPath} -->
		<a class="btn btn-default" href="<%= request.getContextPath() %>/pizzas/new" role="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Nouvelle Pizza</a><br><br>
		
		<table class="table table-striped">
			<thead>
				<tr>
					<td>CODE</td>
					<td>NOM</td>
					<td>PRIX</td>
					<td>CATEGORIE</td>
					<td>IMAGE</td>
					<td></td>
				</tr>
			</thead>
		
			<tbody>
				<c:forEach var="pizza" items="${pizzas }">
					<tr>
						<td>${pizza.code}</td>
						<td>${pizza.nom}</td>
						<td>${pizza.prix}</td>
						<td>${pizza.categorie.libelle}</td>
						<td><img src="http://placehold.it/150x150"></td>
						<td><a class="btn btn-primary" href="<%= request.getContextPath() %>/pizzas/edit?code=${pizza.code}" role="button"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Editer</a>
						<a class="btn btn-danger" href="<%= request.getContextPath() %>/pizzas/supprimer?code=${pizza.code}" role="button"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Supprimer</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>