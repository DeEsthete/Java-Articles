<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="app">
    <fmt:message key="auth.title" var="title"/>
    <fmt:message key="auth.sign_in" var="sign_in"/>
    <fmt:message key="user.login" var="login"/>
    <fmt:message key="user.password" var="password"/>
    <fmt:message key="registration.sign_up" var="sign_up"/>
</fmt:bundle>

<%@include file='core/header.jsp' %>
<div class="card text-center mx-auto mt-5" style="max-width: 500px">
    <div class="card-header">
        ${title}
    </div>
    <div class="card-body">
        <form action="/fs/authorization" method="post">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">${login}</span>
                </div>
                <input type="text" name="login" class="form-control" aria-label="Default">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">${password}</span>
                </div>
                <input type="password" name="password" class="form-control" aria-label="Default">
            </div>
            <div>
                <button type="submit" class="btn btn-primary mt-1">${sign_in}</button>
            </div>
            <div>
                <a href="/fs/registration">${sign_up}</a>
            </div>
        </form>
        <c:if test="${not empty login_error}">
            <div class="alert alert-danger" role="alert">
                User not found
            </div>
        </c:if>
    </div>
</div>
<%@include file='core/footer.jsp' %>