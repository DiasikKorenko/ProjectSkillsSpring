<%@ page import="com.tms.domain.Transport" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Transport transportJsp = (Transport) request.getAttribute("transport");%>
<html>
<head>
    <title>Transport info</title>
</head>
<body>
<h1> Hello, this is your Transport!</h1>
<h3>Transport id:  <%=transportJsp.getId()%></h3>
<h3>UserId:  <%=transportJsp.getUserId()%></h3>
<h3>TypeTransport:  <%=transportJsp.getTypeTransport()%></h3>
<h3>VolumeTransport:  <%=transportJsp.getVolumeTransport()%></h3>
<h3>WeightTransport:  <%=transportJsp.getWeightTransport()%></h3>
<h3>isDeleted:  <%=transportJsp.isDeleted()%></h3>

</body>
</html>