<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="app">
  <fmt:message key="articles.title" var="articles"/>
  <fmt:message key="navbar.language" var="language"/>
  <fmt:message key="navbar.logout" var="logout"/>
  <fmt:message key="auth.sign_in" var="sign_in"/>
</fmt:bundle>

<html>
<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
          integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
          crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
          integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
          crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
          integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
          crossorigin="anonymous"></script>
  <script src="https://kit.fontawesome.com/89135359a3.js" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="/fs/articles">RedLine</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="/fs/articles">${articles}</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
           aria-haspopup="true" aria-expanded="false">
          ${language}
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="/fs/set-language?lang=ru">Русский</a>
          <a class="dropdown-item" href="/fs/set-language?lang=en">English</a>
        </div>
      </li>
    </ul>

    <ul class="navbar-nav ml-auto">
      <li class="nav-item float-right">
        <a href="/fs/add-article" style="font-size: 24px; color: gray;">
          <i class="fas fa-plus-square"></i>
        </a>
      </li>

      <li class="nav-item float-right pl-2 ml-2" style="border-left: 1px solid lightgray">
        <c:if test="${empty user}">
          <a href="/fs/authorization">
              ${sign_in}
          </a>
        </c:if>
        <c:if test="${not empty user}">
          <a href="/fs/logout">
              ${logout}
          </a>
        </c:if>
      </li>
    </ul>
  </div>
</nav>
<div class="mx-auto my-2" style="max-width: 1150px;">