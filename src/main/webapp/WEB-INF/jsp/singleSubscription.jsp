<%@ page import="com.tms.domain.Subscription" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Subscription subscriptionJsp = (Subscription) request.getAttribute("subscription");%>
<html>
<head>
  <title>Subscription info</title>
</head>
<body>
<h1> Hello, this is your Subscription!</h1>
<h3>Subscription id:  <%=subscriptionJsp.getId()%></h3>
<h3>Subscription expire date:  <%=subscriptionJsp.getExpireDate()%></h3>
<h3>Subscription isActive:  <%=subscriptionJsp.isActiv()%></h3>
</body>
</html>