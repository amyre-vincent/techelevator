<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>National Park Geek</title>
<c:url value="/css/parkStyle.css" var="cssHref" />
<link rel="stylesheet" href="${cssHref}">
<link
	href="https://fonts.googleapis.com/css?family=Jomolhari&display=swap"
	rel="stylesheet">
</head>


<body>
	<header>
		<nav>
			<c:url value="/" var="homePageHref" />
			<c:url value="/img/logo.png" var="logoSrc" />
			<img src="${logoSrc}" alt="National Park Geek logo" />
			<ul>
				<c:url value="/" var="homePageURL" />
				<li><a href="${ homePageURL}">Home</a></li>
				<c:url value="/SurveyForm" var="surveyURL" />
				<li><a href="${ surveyURL}">Survey</a></li>
			</ul>
		</nav>
	</header>

</body>

</html>