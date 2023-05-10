<%@ page import="com.tms.domain.Cargo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Cargo cargoJsp = (Cargo) request.getAttribute("cargo");%>
<html>
<head>
    <title>Cargo info</title>
</head>
<body>
<h1> Hello, this is your Transport!</h1>
<h3>Cargo id:  <%=cargoJsp.getId()%></h3>
<h3>UserId:  <%=cargoJsp.getUserId()%></h3>
<h3>WeightCargo:  <%=cargoJsp.getWeightCargo()%></h3>
<h3>WidthCargo:  <%=cargoJsp.getWidthCargo()%></h3>
<h3>LenghtCargo:  <%=cargoJsp.getLenghtCargo()%></h3>
<h3>Hight:  <%=cargoJsp.getHight()%></h3>
<h3>States:  <%=cargoJsp.getStates()%></h3>
<h3>isDeleted:  <%=cargoJsp.isDeleted()%></h3>

</body>
</html>