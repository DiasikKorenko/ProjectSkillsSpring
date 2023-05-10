<%@ page import="com.tms.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User userJsp = (User) request.getAttribute("user");%>
<html>
<head>
    <title>User info</title>
</head>
<body>
<h1> Hello, this is your User!</h1>
<h3>User id:  <%=userJsp.getId()%></h3>
<h3>User first name:  <%=userJsp.getFirstName()%></h3>
<h3>User name:  <%=userJsp.getUserName()%></h3>
<h3>User last name:  <%=userJsp.getLastName()%></h3>
<h3>User email(login):  <%=userJsp.getEmail()%></h3>
<h3>User password:  <%=userJsp.getPasswordUser()%></h3>
<h3>User jobTitle:  <%=userJsp.getJobTitle()%></h3>
<h3>User organizationName;  <%=userJsp.getOrganizationName()%></h3>
<h3>User unpTin;  <%=userJsp.getUnpTin()%></h3>
<h3>User legalAddress;;  <%=userJsp.getLegalAddress()%></h3>
<h3>User countries;  <%=userJsp.getCountries()%></h3>
<h3>User documentation:  <%=userJsp.getDocumentation()%></h3>
<h3>User telephone1:  <%=userJsp.getTelephone1()%></h3>
<h3>User telephone2:  <%=userJsp.getTelephone2()%></h3>
<h3>User telephone3:  <%=userJsp.getTelephone3()%></h3>
</body>
</html>








