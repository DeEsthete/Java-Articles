<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="app">
  <fmt:message key="add.article.title" var="title"/>
  <fmt:message key="article.prop.title" var="articleTitle"/>
  <fmt:message key="article.prop.body" var="articleBody"/>
  <fmt:message key="update.article.submit" var="update"/>
</fmt:bundle>

<%@include file='core/header.jsp' %>
<div class="card text-center mx-auto mt-5" style="max-width: 500px">
  <div class="card-header">
    ${title}
  </div>
  <div class="card-body">
    <form action="/fs/edit/article" method="post">
      <input type="hidden" name="articleId" class="form-control" aria-label="Default" value="${article.id}">
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span class="input-group-text">${articleTitle}</span>
        </div>
        <input type="text" name="title" value="${article.title}" class="form-control" aria-label="Default">
      </div>
      <div class="input-group">
        <div class="input-group-prepend">
          <span class="input-group-text">${articleBody}</span>
        </div>
        <textarea name="body" class="form-control" aria-label="With textarea">${article.body}</textarea>
      </div>
      <div>
        <button type="submit" class="btn btn-primary mt-1">${update}</button>
      </div>
    </form>
  </div>
</div>
<%@include file='core/footer.jsp' %>