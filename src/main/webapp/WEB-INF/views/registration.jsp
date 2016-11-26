<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="local" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:template title="Create your account">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
      <h2 class="form-signin-heading">Create your account</h2>
      <spring:bind path="username">
        <div class="form-group ${status.error ? 'has-error' : ''}">
          <form:input type="text" path="username" class="form-control" placeholder="Username"
                      autofocus="true"></form:input>
          <form:errors path="username"></form:errors>
        </div>
      </spring:bind>

      <spring:bind path="password">
        <div class="form-group ${status.error ? 'has-error' : ''}">
          <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
          <form:errors path="password"></form:errors>
        </div>
      </spring:bind>

      <spring:bind path="confirmPassword">
        <div class="form-group ${status.error ? 'has-error' : ''}">
          <form:input type="password" path="confirmPassword" class="form-control"
                      placeholder="Confirm your password"></form:input>
          <form:errors path="confirmPassword"></form:errors>
        </div>
      </spring:bind>

      <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

  </form>

</t:template>
