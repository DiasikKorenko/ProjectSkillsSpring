<%@ page import="com.tms.domain.Transport" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Transport info</title>
</head>
<body>
<h1> Hello, there are our TransportsID!</h1>



<table>



    <%=request.getAttribute("transport")%>

</table>
</body>
</html>