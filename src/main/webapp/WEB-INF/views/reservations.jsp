<%@ page import="ie.cit.clouddevproject.Reservation"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<link rel="stylesheet" type="text/css" href="styles/style.css">	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Watergrashill United Training Pitch Reservation Application</title>
	
	<link rel="stylesheet" href="styles/theme/jquery.ui.1.8.20.custom.css">
	<script src="scripts/jquery-1.7.2.js"></script>
	<script src="scripts/ui/jquery.ui.1.8.20.custom.js"></script>
	<script src="scripts/ui/jquery.ui.timepicker.addon.js"></script>

	<script>
	$(function() {	
		$("#datetimepicker").datetimepicker({
		minute: 0,
		showMinute: false,
		//stepMinute: 30,
		hourMin: 9,
		hourMax: 21,
		minDate: 0,
		maxDate: 30,
		showButtonPanel: false,
		timeFormat: "hh:00"});
	});

	function validate(){
		if (document.frmReservationForm.reservationdate.value=="")
		{
			alert ("Unable to make Reservation." + '\n' + "Missing Reservation Date!" );
			return false;
			}
 		else
 			return true;
		};
	</script>
	
</head>




<body background="images/bg.jpg">

<div id="head"> 	

	<img alt="CIT Logo" src="images/upper_logo.jpg">
	
	<hr />

	<c:set var="username"><security:authentication property="principal.username" /></c:set>

	
	<a href="j_spring_security_logout">Logout: <security:authentication
			property="principal.username" />
	</a>
	<p><%=new java.util.Date()%></p>

	<h1>Training Pitch Reservation application</h1>
	</div>

<hr />	

<div id="allreservations">	
	<h2>All Upcoming Reservations</h2>
	
	<table cellpadding="2" border="1">
	
	<tr><th>Reservation Date</th>
		<th>Pitch</th>
		<th>Team</th>
		<th>Reserved by / on </th>
		<th>Comments </th>
	</tr>
	<c:forEach items="${reservations}" var="objReservation" varStatus="row">
	<c:set var="trainer"> ${objReservation.trainer}</c:set>
		<tr>		
			<td>${objReservation.reservationtime}</td>
			<td>${objReservation.pitch}</td>
			<td>${objReservation.team}</td>				
			<td>${objReservation.trainer} on ${objReservation.submittime}</td>
			<td>${objReservation.comments}</td>
	
			<c:if test="${trainer==username}">		
				<td><form method="post">
					<input name="_method" type="hidden" value="delete"> 
					<input name="iReservationID" type="hidden" value="${objReservation.id}">
					<input type="submit" value="Cancel">
					</form>
				</td>
			</c:if>
		</tr>	
		</c:forEach>
	</table>
</div>

<hr />

<div id="allmyreservations">	
	<h2>My Upcoming Reservations</h2>
	
	<table cellpadding="2" border="1">
	
	<tr><th>Reservation Date</th>
		<th>Pitch</th>
		<th>Team</th>
		<th>Reserved by / on </th>
		<th>Comments </th>
	</tr>
	<c:forEach items="${myreservations}" var="objReservation" varStatus="row">
	<c:set var="trainer"> ${objReservation.trainer}</c:set>
		<tr>		
			<td>${objReservation.reservationtime}</td>
			<td>${objReservation.pitch}</td>
			<td>${objReservation.team}</td>				
			<td>${objReservation.trainer} on ${objReservation.submittime}</td>
			<td>${objReservation.comments}</td>
			<td><form method="post">
				<input name="_method" type="hidden" value="delete"> 
				<input name="iReservationID" type="hidden" value="${objReservation.id}">
				<input type="submit" value="Cancel">
				</form>
			</td>
		</tr>	
		</c:forEach>
	</table>
</div>

<hr />
	
<h2>Create new Training-Pitch Reservation</h2>
	
<form method="post" onSubmit= "return validate()" name="frmReservationForm">

	<table  cellpadding="2">
		<tr><td>Date:</td><td><input type="text" name = "reservationdate" id="datetimepicker" ></td></tr>
		<tr><td>Pitch:</td>
		<td><select name="pitch">
    			<option value="Main Pitch" SELECTED>Main Pitch</option>
    			<option value="Training Pitch 1">Training Pitch 1</option>
    			<option value="Training Pitch 2">Training Pitch 2</option>
    			<option value="ASTRO Turf Pitch">ASTRO Turf Pitch</option>
   		</select></td></tr>
		<tr><td>Team:</td>
		<td><select name="team">
    			<option value="U7" SELECTED>Under 7</option>
    			<option value="U8">U8</option>
    			<option value="U9">U9</option>
    			<option value="U10">U10</option>
    			<option value="U11">U11</option>
    			<option value="U12">U12</option>
    			<option value="U14">U14</option>
    			<option value="U16">U6</option>
    			<option value="AUL">AUL</option>
    			<option value="Other">Other</option>    			
   		</select>	
   		</td></tr>
		<tr><td>Comments:</td><td><input name="comments"></td></tr>
   		<tr><td><input type="submit"></td></tr></table>
		
	</form>		
	
</body>
</html>