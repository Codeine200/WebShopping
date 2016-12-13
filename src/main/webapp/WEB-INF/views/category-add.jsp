<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="local" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Category Registration Form">

<h2><local:message code="categories.registration" /></h2>

<form:form method="POST" modelAttribute="category" class="form-shop">
  <form:input type="hidden" path="id" id="id"/>

  <local:bind path="name">
    <div class="form-group ${status.error ? 'has-error' : ''}">
      <label for="name"><local:message code="categories.name" /></label>
      <input type="text" name="${status.expression}" value="${status.value}" class="form-control" id="name" placeholder="Enter name">
      <c:if test="${status.error}">
        <c:forEach items="${status.errorMessages}" var="error">
          <c:out value="${error}"/>
        </c:forEach>
      </c:if>
    </div>
  </local:bind>

  <div class="form-group">
    <c:choose>
      <c:when test="${edit}">
        <button type="submit" class="btn btn-lg btn-primary btn-block"><local:message code="common.update" /></button>
      </c:when>
      <c:otherwise>
        <button type="submit" class="btn btn-lg btn-primary btn-block"><local:message code="common.add" /></button>
      </c:otherwise>
    </c:choose>
  </div>
</form:form>
<br/>
<br/>

</t:wrapper>