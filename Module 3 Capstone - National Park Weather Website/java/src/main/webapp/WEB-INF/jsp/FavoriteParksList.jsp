<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<title>Favorite Parks</title>
<section id="content">
	<h1><c:out value="Our Favorite Parks"></c:out></h1>
	<table>
		<c:forEach items="${faveParks }" var="favePark">
		<tr>
			<c:set var="string1" value="${favePark.parkCode }" />
			<c:set var="string2" value="${fn:toLowerCase(string1)}" />
			<td><img src="img/parks/${string2 }.jpg" /></td>
			<td><c:out value="${favePark.name } is ${favePark.numOfSurvey } people's favorite park!"></c:out></td>
			
			</tr>
		</c:forEach>
	</table>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />