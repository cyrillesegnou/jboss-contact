<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>contacts</title>
</head>
<body>
<form action="/MultiPartFormDataServlet" enctype="multipart/form-data" method="post">
<select name="civilite">
<c:forEach items="${civilites }" var="civilite">
	<c:if test="${contact.civilite eq civilite}">
		<option selected="selected">${civilite}</option>
	</c:if>
	<c:if test="${contact.civilite ne  civilite}">
		<option>${civilite}</option>
	</c:if>
</c:forEach>
</select>
<input placeholder="Prénom" name="prenom" value="${contact.prenom }"/>
<input placeholder="Nom" name="nom" value="${contact.nom }"/>
<input name="id" value="${contact.id }" type="hidden">
<input type= "file" name="avatar">
<button>Modifier</button>
<button formaction="AllContactsServlet">Annuler</button>
</form>
</body>
</html>