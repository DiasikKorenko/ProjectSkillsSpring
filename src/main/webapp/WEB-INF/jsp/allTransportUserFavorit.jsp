<%--
  Created by IntelliJ IDEA.
  User: Siarhey.Pavirayeu
  Date: 2/8/2023
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>All movies!!!</title>
</head>
<body>
This is your transport:
<%=request.getAttribute("transportList")%>
<p></p>
This is your cargo:
<%=request.getAttribute("cargoList")%>
</body>
</html>