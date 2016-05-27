<%@page import="fr.pizzeria.model.CategoriePizza"%>
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
<title>Editer une pizzas</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Editer une pizza</h1>
	
		<form class="form-horizontal" action="<%= request.getContextPath() %>/pizzas/edit?code=${pizza.code}" method="POST">
			<fieldset>
			
			<!-- Form Name -->
			<legend>Edition</legend>
			
			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-1 control-label" for="code">Code</label>  
			  <div class="col-md-4">
			  <input id="code" name="code" type="text" value="${pizza.code}" readonly placeholder="" class="form-control input-md">
			    
			  </div>
			</div>
			
			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-1 control-label" for="nom">Nom</label>  
			  <div class="col-md-4">
			  <input id="nom" name="nom" type="text" value="${pizza.nom}" placeholder="" class="form-control input-md">
			    
			  </div>
			</div>
			
			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-1 control-label" for="prix">Prix</label>  
			  <div class="col-md-4">
			  <input id="prix" name="prix" type="text" value="${pizza.prix}" placeholder="" class="form-control input-md">
			    
			  </div>
			</div>
			
			<!-- Select Basic -->
			<div class="form-group">
			  <label class="col-md-1 control-label" for="categorie">Catégorie</label>
			  <div class="col-md-4">
			    <select id="categorie" name="categorie" class="form-control">
			      <option value="${CategoriePizza[0]}" <c:if test="${pizza.categorie.equals(CategoriePizza[0])}">selected</c:if>>${CategoriePizza[0].libelle}</option>
			      <option value="${CategoriePizza[1]}" <c:if test="${pizza.categorie.equals(CategoriePizza[1])}">selected</c:if>>${CategoriePizza[1].libelle}</option>
			      <option value="${CategoriePizza[2]}" <c:if test="${pizza.categorie.equals(CategoriePizza[2])}">selected</c:if>>${CategoriePizza[2].libelle}</option>
			    </select>
			  </div>
			</div>
			
			<!-- Button -->
			<div class="form-group">
			  <label class="col-md-1 control-label" for="envoyer"></label>
			  <div class="col-md-4">
			    <button id="envoyer" name="envoyer" type="submit" class="btn btn-primary">Envoyer</button>
			  </div>
			</div>
			
			</fieldset>
		</form>
	</div>
</body>
</html>