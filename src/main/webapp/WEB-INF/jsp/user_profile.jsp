<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="app">
  <fmt:message key="articles.title" var="title"/>
  <fmt:message key="articles.open" var="open"/>
  <fmt:message key="articles.goto" var="goto"/>
</fmt:bundle>

<%@include file='core/header.jsp' %>
<div class="text-center">
  <div class="container">
    <div class="row">
      <div class="col-12">
        <h2>${profile.getFistName()}</h2>
      </div>
      <div class="col-12 text-center">
        <span class="mx-2" style="color: green;">
          <i class="fas fa-thumbs-up"></i>
          ${profile.getCountLikes()}
        </span>
        <span class="mx-2" style="color: red;">
          <i class="fas fa-thumbs-down"></i>
          ${profile.getCountDislikes()}
        </span>
        <hr>
      </div>
      <div class="col-12">
        <c:if test="${not empty user && user.getId() == profile.getId()}">
          <a href="/fs/edit-profile">
            <button class="btn btn-warning" type="submit">Edit</button>
          </a>
        </c:if>
      </div>
      <div class="col-12">
        <h3>${title}</h3>
        <div class="container">
          <div class="row">
            <c:forEach items="${profile.getArticles()}" var="item">
              <div class="col-12">
                <div class="article card">
                  <div class="card-header d-flex" style="justify-content: space-between">
                    <div class="py-2">${item.title}</div>
                    <div>
                      <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample${item.id}"
                         role="button"
                         aria-expanded="false" aria-controls="collapseExample">
                          ${open}
                      </a>
                    </div>
                  </div>
                  <div class="collapse" id="collapseExample${item.id}">
                    <div class="card-body">
                        ${item.body}
                      <div>
                        <a href="/fs/article?articleId=${item.id}">Go to this article</a>
                      </div>
                    </div>
                  </div>
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