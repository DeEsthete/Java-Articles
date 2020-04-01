<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="app">
  <fmt:message key="add.article.title" var="title"/>
  <fmt:message key="article.prop.title" var="articleTitle"/>
  <fmt:message key="article.prop.body" var="articleBody"/>
  <fmt:message key="add.article.submit" var="write"/>
</fmt:bundle>

<%@include file='core/header.jsp'%>
<div class="card text-center mx-auto mt-5" style="max-width: 500px">
  <div class="card-header">
    ${title}
  </div>
  <div class="card-body">
    <form action="/fs/add-article" method="post">
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span class="input-group-text">${articleTitle}</span>
        </div>
        <input type="text" name="title" class="form-control" aria-label="Default">
      </div>
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span class="input-group-text">${articleBody}</span>
        </div>
        <input type="text" name="body" class="form-control" aria-label="Default">
      </div>
      <div>
        <button type="submit" class="btn btn-primary mt-1">${write}</button>
      </div>
    </form>
  </div>
</div>
<%@include file='core/footer.jsp'%>