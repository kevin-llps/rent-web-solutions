<%@ page import="fr.esgi.rent.beans.RentalProperty" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RentalProperty rentalProperty = (RentalProperty) request.getAttribute("rentalProperty");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Bien en location n°<% out.println(rentalProperty.referenceId()); %></title>
</head>
<body>
<h1>Bien en location n°<% out.println(rentalProperty.referenceId()); %></h1>
<p>
<ul>
    <li>
        <% out.println(String.format("%s à louer", rentalProperty.propertyType().getDesignation())); %>
    </li>
    <li><% out.println(String.format("Loyer mensuel : %s €", rentalProperty.rentAmount())); %></li>
    <li><% out.println(String.format("Surface : %s m²", rentalProperty.area())); %></li>
    <li><% out.println(String.format("Nombre de chambres : %s", rentalProperty.bedroomsCount())); %></li>
</ul>
</p>
</body>
</html>
