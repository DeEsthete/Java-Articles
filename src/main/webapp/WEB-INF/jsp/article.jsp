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
      <div class="container">
        <div class="row">
          <div class="col-12">
            ${article.getBody()}
          </div>
        </div>
        <div class="row text-left">
          <div class="col-12">
            <h6>
              <c:forEach var="item" items="${article.getTags()}">
                <span class="badge badge-secondary">${item.getName()} ${item.getCount()}</span>
              </c:forEach>
            </h6>
          </div>
          <div class="col-12">
            <form method="post" action="/fs/add-tag" class="d-inline-block mr-1">
              <input type="hidden" name="articleId" value="${article.id}">
              <div class="input-group">
                <input type="text" name="tagName" class="form-control" aria-label="Default">
                <button class="btn btn-light" type="submit">Add</button>
              </div>
            </form>
          </div>
        </div>
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
        <div class="row">
          <div class="col-12 text-left">
            <hr>
            Commentaries:
          </div>
          <div class="col-12">
            <div class="w-100 mb-3">
              <form method="post" action="/fs/add-commentary" class="input-group" target="none">
                <input type="hidden" name="listId" value="${article.getCommentaryListId()}">
                <input type="text" name="content" class="form-control" placeholder="Commentary content"
                       aria-label="Commentary content" aria-describedby="basic-addon2">
                <div class="input-group-append">
                  <button class="btn btn-outline-secondary" type="submit"
                          onclick="setTimeout(location.reload(), 1000);">Write
                  </button>
                </div>
              </form>
            </div>
            <hr>
          </div>
          <div class="col-12">
            <c:forEach items="${article.getCommentaries()}" var="commentary">
              <div class="card w-100">
                <div class="card-header">
                    ${commentary.getUserNickname()}
                </div>
                <div class="card-body">
                  <p class="card-text">${commentary.getContent()}</p>
                </div>
              </div>
            </c:forEach>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@include file='core/footer.jsp' %>