<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="local" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:wrapper title="Product Registration Form">

<h2><local:message code="product.registration" /></h2>

<form:form method="POST" modelAttribute="product">
  <form:input type="hidden" path="id" id="id"/>
  <table>
    <tr>
      <td><label for="name"><local:message code="product.name" />: </label> </td>
      <td><form:input path="name" id="name"/></td>
      <td><form:errors path="name" cssClass="error"/></td>
    </tr>

    <tr>
      <td><label for="category.id"><local:message code="categories.category" />: </label> </td>
      <td>
        <form:select path="category.id">
          <form:options items="${categories}" itemValue="id" itemLabel="name"></form:options>
        </form:select>
      </td>
      <td><form:errors path="category.id" cssClass="error"/></td>
    </tr>

    <tr>
      <td><label for="price"><local:message code="product.price" />: </label> </td>
      <td><form:input path="price" id="price"/></td>
      <td><form:errors path="price" cssClass="error"/></td>
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

</body>
</html>
</t:wrapper>