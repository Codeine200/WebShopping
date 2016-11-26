<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="local" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Category Registration Form">
<jsp:include page="/WEB-INF/views/menu.jsp"/>
<h2><local:message code="categories.registration" /></h2>

<form:form method="POST" modelAttribute="category">
  <form:input type="hidden" path="id" id="id"/>
  <table>
    <tr>
      <td><label for="name"><local:message code="categories.name" />: </label> </td>
      <td><form:input path="name" id="name"/></td>
      <td><form:errors path="name" cssClass="error"/></td>
    </tr>

    <tr>
      <td colspan="3">
        <c:choose>
          <c:when test="${edit}">
            <input type="submit" value="<local:message code="common.update" />"/>
          </c:when>
          <c:otherwise>
            <input type="submit" value="<local:message code="common.add" />"/>
          </c:otherwise>
        </c:choose>
      </td>
    </tr>
  </table>
</form:form>
<br/>
<br/>

</t:wrapper>