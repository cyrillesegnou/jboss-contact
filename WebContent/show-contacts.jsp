<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contacts</title>
</head>
<body>
	<div><img id="duke" src="images/avion.jpg"/></div>
<h2>Liste des contacts</h2>
<ul>

	<c:forEach items="${contacts }" var="contact" varStatus="index">
		<li>
		<c:if test="${empty contact.image}">
		
			<c:if test="${contact.civilite eq 'Mme' or contact.civilite eq 'Mlle'}">
					<img src="avatars/woman.png" width= "50px" height="50px"/>
			</c:if>
			
		<c:if test="${contact.civilite ne 'Mme' and contact.civilite ne 'Mlle'}">
			<img src="avatars/man.png" width= "50px" height="50px"/>
			</c:if>
				</c:if>
		<c:if test="${ not empty contact.image}">
				<img src = "avatars/${contact.image }" width= "50px" height="50px"/>
		</c:if>
		${contact.image} - 
		${contact.civilite } ${contact.prenom } ${contact.nom } 
			<a href="ShowAdresses?id=${contact.id }">adresses</a>
			<a href="ShowFormContact?id=${contact.id }">modifier</a>  	
			<a href="SupprimerContact?id=${contact.id }">supprimer</a>  	
		</li>
	</c:forEach>
</ul>
</body>
</html>