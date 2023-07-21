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
	<h1>Formulaire</h1>
	
<c:if test="${user == null}">
    <form action="insert" method="get">
        <label>Nom : </label><br>
        <input type="text" name="nom" value='${user.nom}'required="required"><br><br>
        <label>Prenom : </label><br>
        <input type="text" name="prenom" value='${user.prenom}' required="required"><br><br>
        <label>Tache : </label><br>
        <input type="text" name="tache" value='${user.tache}'required="required"><br><br>
        <input type="submit" value="Save">
    </form>
</c:if>
	
	<c:if test ="${user != null}">
		<form action ="update" method = "get">
		<input type="hidden" name="id" value='${user.id}'required="required"><br><br>
     	<label>Nom : </label><br>
        <input type="text" name="nom" value='${user.nom}'required="required"><br><br>
        <label>Prenom : </label><br>
        <input type="text" name="prenom" value='${user.prenom}' required="required"><br><br>
        <label>Tache : </label><br>
        <input type="text" name="tache" value='${user.tache}'required="required"><br><br>
        <input type="submit" value="Save">

		</form>
	</c:if>

</body>
</html>