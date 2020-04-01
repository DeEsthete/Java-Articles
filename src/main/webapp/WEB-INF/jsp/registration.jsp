<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="app">
    <fmt:message key="registration.title" var="title"/>
    <fmt:message key="registration.retype.password" var="retype_password"/>
    <fmt:message key="registration.sign_up" var="sign_up"/>
    <fmt:message key="user.login" var="login"/>
    <fmt:message key="user.password" var="password"/>
    <fmt:message key="user.first_name" var="first_name"/>
    <fmt:message key="user.second_name" var="second_name"/>
</fmt:bundle>

<%@include file='core/header.jsp' %>
<div class="card text-center mx-auto mt-5" style="max-width: 500px">
    <div class="card-header">
        ${title}
    </div>
    <div class="card-body">
        <form action="/fs/registration" method="post">
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
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">${retype_password}</span>
                </div>
                <input type="password" name="password2" class="form-control" aria-label="Default">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">${first_name}</span>
                </div>
                <input type="text" name="firstName" class="form-control" aria-label="Default">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">${second_name}</span>
                </div>
                <input type="text" name="secondName" class="form-control" aria-label="Default">
            </div>
            <button type="submit" class="btn btn-primary mt-1">${sign_up}</button>
        </form>
        <c:if test="${not empty message}">
            <div class="alert alert-danger" role="alert">
                <c:out value="${message}" default="" escapeXml="false"/>
            </div>
        </c:if>
    </div>
</div>
<%@include file='core/footer.jsp' %>