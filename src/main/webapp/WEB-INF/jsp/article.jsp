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
      <h2>${article.getTitle()}</h2>
    </div>
    <div class="card-body">
      ${article.getBody()}
      <div class="container">
        <div class="row">
          <div class="col-6 text-left">
            <c:if test="${!isMyArticle}">
              <div class="d-inline-block" style="color: green;">
                <a href="/fs/article-like?articleId=${article.id}&isLike=true" style="font-size: 24px; color: green;">
                  <i class="fas fa-thumbs-up"></i>
                </a>
                  ${article.getLikesCount()}
              </div>
              <div class="d-inline-block" style="color: red;">
                <a href="/fs/article-like?articleId=${article.id}&isLike=false" style="font-size: 24px; color: red;">
                  <i class="fas fa-thumbs-down"></i>
                </a>
                  ${article.getDislikesCount()}
              </div>
            </c:if>
          </div>
          <div class="col-6 text-right">
            <c:if test="${isMyArticle}">
              <form method="get" action="/fs/edit/article" class="d-inline-block mr-1">
                <input type="hidden" name="articleId" value="${article.id}">
                <button class="btn btn-warning" type="submit">${edit}</button>
              </form>
              <form method="post" action="/fs/delete/article" class="d-inline-block">
                <input type="hidden" name="articleId" value="${article.id}">
                <button class="btn btn-danger" type="submit">${delete}</button>
              </form>
            </c:if>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@include file='core/footer.jsp' %>