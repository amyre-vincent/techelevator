<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<title>Park Details</title>

<section id="content">

	<h1><c:out value ="${park.name }"></c:out></h1>
	<c:set var="string1" value="${park.parkCode }" />
	<c:set var="string2" value="${fn:toLowerCase(string1)}" />
	<img src="img/parks/${string2 }.jpg" />
	<h3><c:out value="'${park.inspirationalQuote }'"></c:out></h3>
	<h3><c:out value="~${park.inspirationalQuoteSource }"></c:out></h3>
	<br>
	<p><c:out value="Park Description: ${park.description }"></c:out></p>
	<p><c:out value="Founded in ${park.yearFounded }, ${park.name } has a climate
		type of ${park.climate}, ${park.milesOfTrail} miles of trail on a
		total acreage of ${park.acreage}, which sits at an elevation of
		${park.elevation} feet. Every year, ${park.annualVisitorCount}
		visitors enjoy camping at one of our ${park.numberOfCampsites }
		camp-sites! On a lucky day, they just may see one of our
		${park.numberOfAnimalSpecies } animal species! All of this for the
		entry fee of $ ${park.entryFee }!"></c:out></p>
	<div>
		<table>
			<tr>
				<c:set var="displayCelcius" value="${ sessionScope.inCelcius }" />
				<c:forEach items="${weathers}" var="weather">
					<c:set var="nameWithSpace" value="${weather.forecast }" />
					<c:set var="nameWithoutSpace" value="${fn:replace(nameWithSpace,' ','')}" />

					<td><img id="weather" src="img/weather/${nameWithoutSpace }.png" /></td>
					<td><c:out value="High of: ${displayCelcius ? weather.celsiusHigh : weather.fahrenheitHigh }"></c:out></td>
					<td><c:out value="Low of: ${displayCelcius ? weather.celsiusLow : weather.fahrenheitLow }"></c:out></td>

				</c:forEach>
			</tr>
		</table>
		<c:url var="tempSubmit" value="/ParkDetailList" />
		<form action="${ tempSubmit }" method="POST">
			<select name="tempScale">
				<option value="${ false }">Fahrenheit</option>
				<option value="${ true }">Celsius</option>
			</select> <input type="hidden" name="oldParkCode" value="${park.parkCode }">
			<input type="submit" value="Submit">
		</form>
		<c:forEach items="${forecasts }" var="forcast">
			<h3><c:out value="${forcast}"></c:out></h3>
		</c:forEach>
		<c:forEach items="${advisory }" var="adv">
			<h3><c:out value="${adv}"></c:out></h3>
		</c:forEach>
		<c:forEach items="${tempAdvisory }" var="tempAdv">
			<h3><c:out value="${tempAdv}"></c:out></h3>
		</c:forEach>
	</div>
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />