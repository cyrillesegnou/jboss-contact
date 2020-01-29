<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contacts</title>
</head>
<body>
<h2>Adresses de ${contact.civilite } ${contact.prenom } ${contact.nom }</h2>

<c:if test="${empty adresses }">
	<h3>Pas d'adresses pour ce contact</h3>
</c:if>
<c:if test="${not empty adresses }">
	<ul>
	<c:forEach items="${adresses }" var="adr">
		<li>${adr.rue } ${adr.codePostal } ${adr.ville } ${adr.pays }</li>
	</c:forEach>
	</ul>
</c:if>
</body>
</html>