<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="app">
  <fmt:message key="forbid.authorization" var="authorization"/>
</fmt:bundle>

<%@include file='core/header.jsp'%>
<div class="text-center">
  <h1>403</h1>
  <a href="/fs/authorization">${authorization}</a>
</div>
<%@include file='core/footer.jsp'%>