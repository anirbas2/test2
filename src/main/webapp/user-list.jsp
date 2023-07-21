<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Liste des Utilisateurs</h1>
	<form action="/projet/new" method="post">
		<button> Add New</button>
	</form>
		<table>
			<thead>
				<tr>
					<td><h3>Id</h3></td>
					<td><h3>Nom</h3></td>
					<td><h3>Prenom</h3></td>
					<td><h3>Tache</h3></td>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var ="user" items="${listUser}">
					<tr>
						<td><c:out value="${user.id}"/></td>
						<td><c:out value="${user.nom}"/></td>
						<td><c:out value="${user.prenom}"/></td>
						<td onclick="toggle()"><c:out value="${user.tache}"/></td>
						<td><a href="edit?id=<c:out value='${user.id}'/>">Edit</a></td>
						<td><a href="delete?id=<c:out value='${user.id}'/>">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class=popup>
			<p><c:out value="${user.description}"/></p>
		</div>
		
		 <script type="text/javascript" src="popup.js"></script>
</body>
</html>