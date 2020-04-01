<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="app">
  <fmt:message key="article.delete" var="delete"/>
  <fmt:message key="article.edit" var="edit"/>
</fmt:bundle>

<%@include file='core/header.jsp' %>
<div class="text-center">
  <div class="card">
    <div class="card-header">
      <h2>${article.title}</h2>
    </div>
    <div class="card-body">
      ${article.body}
      <form method="post" action="/fs/delete/article" class="w-100 text-right">
        <input type="hidden" name="articleId" value="${article.id}">
        <button class="btn btn-warning" type="submit">${edit}</button>
        <button class="btn btn-danger" type="submit">${delete}</button>
      </form>
    </div>
  </div>
</div>
<%@include file='core/footer.jsp' %>