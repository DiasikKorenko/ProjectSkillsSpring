<%@ page import="com.tms.domain.Transport" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
  <title>Transport info</title>
</head>
<body>
<h1> Hello, there are our Cargos!</h1>



<table>

  <%=request.getAttribute("cargo")%>

</table>
</body>
</html>