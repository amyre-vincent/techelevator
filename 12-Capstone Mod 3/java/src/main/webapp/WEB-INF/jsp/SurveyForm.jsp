<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />


<title>National Park Survey</title>
<style type ="text/css">
.error {
	color: red;
}
</style>

<section id="content">
	<h1><c:out value="Favorite Park Survey"></c:out></h1>
	<c:url value="SurveyForm" var="SurveyUrl" />
	<form:form modelAttribute="SurveyForm" action="${SurveyUrl}"
		method="POST">
		<label for="parkCode"><c:out value="Favorite National Park:"></c:out></label>
		<form:select path="parkCode">
			<form:option value=""></form:option>
			<form:option value="CVNP"><c:out value="Cuyahoga Valley National Park"></c:out></form:option>
			<form:option value="ENP"><c:out value="Everglades National Park"></c:out></form:option>
			<form:option value="GNP"><c:out value="Glacier National Park"></c:out></form:option>
			<form:option value="GCNP"><c:out value="Grand Canyon National Park"></c:out></form:option>
			<form:option value="GTNP"><c:out value="Grand Teton National Park"></c:out></form:option>
			<form:option value="GSMNP"><c:out value="Great Smoky Mountain National Park"></c:out></form:option>
			<form:option value="MRNP"><c:out value="Mount Ranier National Park"></c:out></form:option>
			<form:option value="RMNP"><c:out value="Rocky Mountain National Park"></c:out></form:option>
			<form:option value="YNP"><c:out value="Yellowstone National Park"></c:out></form:option>
			<form:option value="YNP2"><c:out value="Yosemite National Park"></c:out></form:option>
		</form:select>
		<form:errors path="parkCode" cssClass="error" />
		<br>
		<label for="emailAddress"><c:out value= "Email Address:"></c:out></label>
		<form:input path="emailAddress"/>
		<form:errors path="emailAddress" cssClass="error" />
		<br>

		<label for="state"><c:out value="State of Residence:"></c:out></label>
		<form:select path="state">
			<form:option value=""></form:option>
			<form:option value="AL"><c:out value="Alabama"></c:out></form:option>
			<form:option value="AK"><c:out value="Alaska"></c:out></form:option>
			<form:option value="AZ"><c:out value="Arizona"></c:out></form:option>
			<form:option value="AR"><c:out value="Arkansas"></c:out></form:option>
			<form:option value="CA"><c:out value="California"></c:out></form:option>
			<form:option value="CO"><c:out value="Colorado"></c:out></form:option>
			<form:option value="CT"><c:out value="Connecticut"></c:out></form:option>
			<form:option value="DE"><c:out value="Delaware"></c:out></form:option>
			<form:option value="DC"><c:out value="District Of Columbia"></c:out></form:option>
			<form:option value="FL"><c:out value="Florida"></c:out></form:option>
			<form:option value="GA"><c:out value="Georgia"></c:out></form:option>
			<form:option value="HI"><c:out value="Hawaii"></c:out></form:option>
			<form:option value="ID"><c:out value="Idaho"></c:out></form:option>
			<form:option value="IL"><c:out value="Illinois"></c:out></form:option>
			<form:option value="IN"><c:out value="Indiana"></c:out></form:option>
			<form:option value="IA"><c:out value="Iowa"></c:out></form:option>
			<form:option value="KS"><c:out value="Kansas"></c:out></form:option>
			<form:option value="KY"><c:out value="Kentucky"></c:out></form:option>
			<form:option value="LA"><c:out value="Louisiana"></c:out></form:option>
			<form:option value="ME"><c:out value="Maine"></c:out></form:option>
			<form:option value="MD"><c:out value="Maryland"></c:out></form:option>
			<form:option value="MA"><c:out value="Massachusetts"></c:out></form:option>
			<form:option value="MI"><c:out value="Michigan"></c:out></form:option>
			<form:option value="MN"><c:out value="Minnesota"></c:out></form:option>
			<form:option value="MS"><c:out value="Mississippi"></c:out></form:option>
			<form:option value="MO"><c:out value="Missouri"></c:out></form:option>
			<form:option value="MT"><c:out value="Montana"></c:out></form:option>
			<form:option value="NE"><c:out value="Nebraska"></c:out></form:option>
			<form:option value="NV"><c:out value="Nevada"></c:out></form:option>
			<form:option value="NH"><c:out value="New Hampshire"></c:out></form:option>
			<form:option value="NJ"><c:out value="New Jersey"></c:out></form:option>
			<form:option value="NM"><c:out value="New Mexico"></c:out></form:option>
			<form:option value="NY"><c:out value="New York"></c:out></form:option>
			<form:option value="NC"><c:out value="North Carolina"></c:out></form:option>
			<form:option value="ND"><c:out value="North Dakota"></c:out></form:option>
			<form:option value="OH"><c:out value="Ohio"></c:out></form:option>
			<form:option value="OK"><c:out value="Oklahoma"></c:out></form:option>
			<form:option value="OR"><c:out value="Oregon"></c:out></form:option>
			<form:option value="PA"><c:out value="Pennsylvania"></c:out></form:option>
			<form:option value="RI"><c:out value="Rhode Island"></c:out></form:option>
			<form:option value="SC"><c:out value="South Carolina"></c:out></form:option>
			<form:option value="SD"><c:out value="South Dakota"></c:out></form:option>
			<form:option value="TN"><c:out value="Tennessee"></c:out></form:option>
			<form:option value="TX"><c:out value="Texas"></c:out></form:option>
			<form:option value="UT"><c:out value="Utah"></c:out></form:option>
			<form:option value="VT"><c:out value="Vermont"></c:out></form:option>
			<form:option value="VA"><c:out value="Virginia"></c:out></form:option>
			<form:option value="WA"><c:out value="Washington"></c:out></form:option>
			<form:option value="WV"><c:out value="West Virginia"></c:out></form:option>
			<form:option value="WI"><c:out value="Wisconsin"></c:out></form:option>
			<form:option value="WY"><c:out value="Wyoming"></c:out></form:option>
		</form:select>
		<form:errors path="state" cssClass="error" />
		<br>

		<label for="activityLevel"><c:out value="Activity Level:"></c:out></label>
		<form:select path="activityLevel">
			<form:option value=""></form:option>
			<form:option value="inactive"><c:out value="Inactive"></c:out></form:option>
			<form:option value="sedentary"><c:out value="Sedentary"></c:out></form:option>
			<form:option value="active"><c:out value="Active"></c:out></form:option>
			<form:option value="extremelyActive"><c:out value="Extremely Active"></c:out></form:option>
		</form:select>
		<form:errors path="activityLevel" cssClass="error" />
		<br>
		<input type="submit" value="Submit" path="/" />
	</form:form>

</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />