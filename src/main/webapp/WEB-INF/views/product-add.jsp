<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="local" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:wrapper title="Product Registration Form">

<h2><local:message code="product.registration" /></h2>

<form method="POST" class="form-shop" enctype="multipart/form-data" action="./new?${_csrf.parameterName}=${_csrf.token}">
  <local:bind path="product.id">
    <input type="hidden" name="${status.expression}" value="${status.value}" class="form-control" id="product.id">
  </local:bind>

  <local:bind path="product.name">
    <div class="form-group ${status.error ? 'has-error' : ''}">
      <label for="product.name"><local:message code="product.name" /></label>
        <input type="text" name="${status.expression}" value="${status.value}" class="form-control" id="product.name" placeholder="Enter name">
        <c:if test="${status.error}">
          <c:forEach items="${status.errorMessages}" var="error">
            <c:out value="${error}"/>
          </c:forEach>
        </c:if>
    </div>
  </local:bind>

  <div class="form-group">
    <label for="product.category.id"><local:message code="categories.category" /></label>
    <local:bind path="product.category.id">
      <select  name="${status.expression}" class="form-control" id="product.category.id">
        <c:forEach items="${categories}" var="category">
          <option value="${category.id}" class="form-control" ${category.id == status.value ? 'selected="selected"' : ''}>${category.name}</option>
        </c:forEach>
      </select>
      <c:if test="${status.error}">
        <c:forEach items="${status.errorMessages}" var="error">
          <c:out value="${error}"/>
        </c:forEach>
      </c:if>
    </local:bind>
  </div>

  <local:bind path="product.price">
    <div class="form-group ${status.error ? 'has-error' : ''}">
      <label for="product.price"><local:message code="product.price" /></label>
        <input type="text" name="${status.expression}" value="${status.value}" class="form-control" id="product.price" placeholder="Enter price">
        <c:if test="${status.error}">
          <c:forEach items="${status.errorMessages}" var="error">
            <c:out value="${error}"/>
          </c:forEach>
        </c:if>
    </div>
  </local:bind>

  <local:bind path="fileBucket.nameFile">
    <div class="form-group ${status.error ? 'has-error' : ''}">
      <label for="fileBucket.nameFile"><local:message code="product.images.name" /></label>
        <input type="text" name="${status.expression}" value="${status.value}" class="form-control" id="fileBucket.nameFile" placeholder="Enter name of picture">
        <c:if test="${status.error}">
          <c:forEach items="${status.errorMessages}" var="error">
            <c:out value="${error}"/>
          </c:forEach>
        </c:if>
    </div>
  </local:bind>

  <local:bind path="fileBucket.file">
    <div class="form-group ${status.error ? 'has-error' : ''}">
      <label for="fileBucket.file"><local:message code="product.images.file" /></label>
        <input type="file" name="${status.expression}" value="${status.value}" class="form-control" id="fileBucket.file">
        <c:if test="${status.error}">
          <c:forEach items="${status.errorMessages}" var="error">
            <c:out value="${error}"/>
          </c:forEach>
        </c:if>
    </div>
  </local:bind>

  <label for="fileBucket.descriptionFile"><local:message code="product.images.description" /></label>
    <div class="form-group">
      <local:bind path="fileBucket.descriptionFile">
      <input type="text" name="${status.expression}" value="${status.value}" class="form-control" id="fileBucket.descriptionFile" placeholder="Enter description of picture">
      <c:if test="${status.error}">
        <c:forEach items="${status.errorMessages}" var="error">
          <c:out value="${error}"/>
        </c:forEach>
      </c:if>
    </div>
  </local:bind>

  <local:bind path="fileBucket.alt">
    <div class="form-group">
      <label for="fileBucket.alt"><local:message code="product.images.alt" /></label>
      <input type="text" name="${status.expression}" value="${status.value}" class="form-control" id="fileBucket.alt" placeholder="Enter alt text">
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
</form>
<br/>
<br/>

</body>
</html>
</t:wrapper>