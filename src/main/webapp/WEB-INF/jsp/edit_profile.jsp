<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file='core/header.jsp' %>
<div class="card text-center mx-auto mt-5" style="max-width: 500px">
  <div class="card-header">
    Edit profile
  </div>
  <div class="card-body">
    <form action="/fs/edit-profile" method="post">
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span class="input-group-text">First name</span>
        </div>
        <input type="text" name="firstName" value="${firstName}" class="form-control" aria-label="Default">
      </div>
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span class="input-group-text">Second name</span>
        </div>
        <input type="text" name="secondName" value="${secondName}" class="form-control" aria-label="Default">
      </div>
      <div class="input-group">
        <div class="input-group-prepend">
          <span class="input-group-text">New password</span>
        </div>
        <input type="text" name="newPassword" class="form-control" aria-label="Default">
      </div>
      <div>
        <button type="submit" class="btn btn-primary mt-1">Apply</button>
      </div>
    </form>
  </div>
</div>
<%@include file='core/footer.jsp' %>