<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<title>Home Page</title>
<h1><c:out value="All National Parks"></c:out></h1>
<section id="content">
	<table>
		<c:forEach items="${parks }" var="park">
			<tr>
				<td><a class="product-image"
					href="<c:url value="/ParkDetailList?parkCode=${park.parkCode}"/>">
						<c:set var="nameInUpperCase" value="${park.parkCode }" /> <c:set
							var="nameInLowerCase" value="${fn:toLowerCase(nameInUpperCase)}" /> <img
						src="img/parks/${nameInLowerCase}.jpg" />
				</a></td>
				<td>
					<h3><c:out value="${park.name }"></c:out></h3>
				</td>
				<td>
					<p><c:out value="${park.description }"></c:out></p>
				</td>
			</tr>
		</c:forEach>
	</table>
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />