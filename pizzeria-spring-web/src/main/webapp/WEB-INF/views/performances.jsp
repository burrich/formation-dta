<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">

	<!-- Bootstrap css (CDN) -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
</head>

<body>
	<div class="container">
		<h1>Liste des performances</h1>
		
		<p>${param.msg}</p><br>
		
		<c:url var="delete_all_url"  value="/mvc/performance/deleteall" />
		<c:url var="delete_url"  value="/mvc/performance/delete" />
		
		<c:choose>
			<%-- if performances --%>
			<c:when test="${!empty performances}">
				<form action="${delete_all_url}" method="post">
				    <button type="submit" class="btn btn-danger">Tout supprimer</button>
				</form>
				
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Service</th>
							<th>Date</th>
							<th>Temps d'exécution (ms)</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${performances}" var="perf">
							<tr>
								<td>${perf.service}</td>
								<td>${perf.date}</td>
								<td>${perf.tempsExecution}</td>
								<td>
									<form action="${delete_url}" method="post">
										<input type="hidden" name="id" value="${perf.id}">
		                    			<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Supprimer</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
				
			<%-- else --%>
			<c:otherwise>
				<p>Table performance vide</p>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
