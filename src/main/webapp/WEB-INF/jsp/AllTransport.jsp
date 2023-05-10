<%@ page import="com.tms.domain.Transport" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Transport info</title>
</head>
<body>
<h1> Hello, there are our Transports!</h1>



<table>
    <tr>
        <th>Transport id:</th>
        <th>UserId:</th>
        <th>TypeTransport: </th>
        <th>VolumeTransport:</th>
        <th>WeightTransport:</th>
    </tr>



    <%=request.getAttribute("transport")%>

</table>
</body>
</html>