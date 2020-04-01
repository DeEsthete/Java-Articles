<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored = "false" %>

<fmt:bundle basename="app">
  <fmt:message key="welcome.title" var="title"/>
  <fmt:message key="welcome.registration" var="registration"/>
  <fmt:message key="welcome.authorization" var="authorization"/>
</fmt:bundle>

<%@include file='core/header.jsp'%>
<div class="text-center">
  <h2>${title}</h2>
  <a href="/fs/registration">${registration}</a>
  <a href="/fs/authorization">${authorization}</a>
</div>
<%@include file='core/footer.jsp'%>