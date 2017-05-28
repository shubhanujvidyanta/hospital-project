<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Appointment Successful</title>
</head>
<body>
<c:if test="${ not empty appointmentDetails}">
Please note your Transaction ID: <b>${appointmentDetails.transactionId} </b><br>
AppointmentDate: <b>${appointmentDetails.appointmentDate }</b><br>
Appointment Time: <b>${appointmentDetails.appointmentSlot }</b><br>
</c:if>
<a href="/Hospital_Management_System/welcome">Back to Homepage</a>

</body>
</html>