<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Welcome to Hospital Management System</title>
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="<c:url value="/resources/js/weather.js" />"></script>
    <script src="<c:url value="/resources/js/location.js" />"></script>
    <script src="<c:url value="/resources/js/tabs.js" />"></script>
    <script src="<c:url value="/resources/js/updateDoctorList.js" />"></script>
   <%--  <script src="<c:url value="/resources/js/validateForm.js" />"></script> --%>
    <link rel="stylesheet" href="<c:url value="/resources/css/tab-background.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/mapStyle.css" />">
    <script src="<c:url value="/resources/js/datepicker.js" />"></script> 
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDAP1ID1u7OICF98_TV7_dhVV_PFnuKYvw&callback=getLocation"></script>         
</head>
<body>
${message}
<div id = "tabs">
         <ul>
            <li><a href = "#registrationTab">Registration</a></li>
            <li><a href = "#appointmentTab">Appointment</a></li>
            <li><a href = "#weatherTab">Weather</a></li>
         </ul>
         <div id = "registrationTab" style="background:#1499B9; color:#04161A">
         <c:if test="${not empty error }">
         <div id="registrationError">${error }</div></c:if>
            <form:form id="registrationForm" method="POST" action="/Hospital_Management_System/registration" modelAttribute="patientRegistration">
            	<div id="registrationFormError"></div>
            	<table>
					<tr>
						<td><form:label path="firstName">First Name:</form:label></td>
						<td><form:input id="firstName" path="firstName" required="true"/></td>
					</tr>
					<tr>
						<td><form:label path="lastName">Last Name:</form:label></td>
						<td><form:input id="lastName" path="lastName" required="true"/></td>
					</tr>
					<tr>
						<td><form:label path="email">Email Id:</form:label></td>
						<td><form:input id="email" type="email" path="email" required="true"/></td>
					</tr>
					<tr>
						<td><form:label path="phone">Phone:</form:label></td>
						<td><form:input id="phone" path="phone" required="true" pattern="\d{10}" maxlength="10" placeholder="eg. 1234567890"/></td>
					</tr>
					<tr>
						<td><form:label path="password">Password:</form:label></td>
						<td><form:password id="password" path="password" required="true"/></td>
					</tr>
					<tr>
						<td><form:label path="confPassword">Confirm Password:</form:label></td>
						<td><form:password id="confPassword" path="confPassword" required="true"/></td>
					</tr>
					<tr>
						<td></td><td><input id="registrationSubmit" type="submit" value="Submit"/></td>
					</tr>
				</table>
            </form:form> 
         </div>
         <div id = "appointmentTab" style="background:#1499B9; color:#04161A">
         <c:if test="${not empty errorMsg }">
         	${errorMsg }
         </c:if>
            <form:form id="appointmentForm" action="/Hospital_Management_System/appointment" method="POST" modelAttribute="patientAppointment"> 
    			 <table>
					<tr>
						<td><form:label path="patientId">Patient ID</form:label></td>
						<td><form:input path="patientId" required="true"/></td>
					</tr>
					<tr>
						<td><form:label path="department">Department</form:label></td>
						<td><form:select id="department" path="department" required="true">
							<form:options items="${departmentList}" itemValue="departmentId" itemLabel="departmentName"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td><form:label  path="doctor">Doctor</form:label></td>
						<td><form:select id="doctor" path="doctor" required="true">
							<form:options items="${doctorList}" itemValue="doctorId" itemLabel="doctorName"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td><form:label path="appointmentDate">Appointment Date</form:label></td>
						<td><form:input id="appointmentDate" path="appointmentDate" readonly="true" required="true"/></td>
					</tr>
					<tr>
						<td><form:label path="appointmentSlot"> AppointmentSlot</form:label></td>
						<td><form:select id="appointmentSlot" path="appointmentSlot" required="true">
							<form:options items="${appointmentSlots }" itemValue="appointmentSlotId" itemLabel="appointmentTime"/>
							</form:select>
						</td>
					</tr>
    			 	<tr>
    			 		<td>Consultation charges:</td>
    			 		<td><b>${consultationCharges}</b></td>
    			 	</tr>
    			 	<tr>
						<td></td><td><input type="submit" value="Submit"/></td>
					</tr>
    			 </table>
			</form:form>
         </div>
         <div id = "weatherTab" style="background:#1499B9; color:#04161A" >
         <button id="getWeatherButton" onclick="getLocationWeatherInfo();"> Get weather!</button>
         <button id="refreshButton" onclick="reloadWeather();"> Refresh</button>
            <div id="weatherInfo" >
            	<div id="map" style="width:100%;height:350px;"></div>
         		<div id="temp" align="right" style="font-size:40px"></div>
         		<div id="weather" align="right"></div>
         		<div id="humidity" align="right"></div>
         		<div id="pressure" align="right"></div>
         		<div id="location" style="font-size:28px"></div>
         		<div id="city" style="display:inline-block"></div>
         		<div id="state" style="display:inline-block"></div>
         		<div id="country" style="display:inline-block"></div>
         		<div id="lastUpdate" align="right" style="font-size:12px"></div>
         	</div>
         
         </div>
      </div>

</body>
</html>